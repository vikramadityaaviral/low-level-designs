package disrupter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DisrupterRingBuffer {

    private final int capacity;
    private final String[] buffer;
    private final Map<Integer, Long> consumerOffsets = new HashMap<>();

    private final AtomicLong writeOffset = new AtomicLong(0L);
    private final AtomicInteger nextConsumerId = new AtomicInteger(0);
    private final AtomicBoolean shutdown = new AtomicBoolean(false);

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public DisrupterRingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.buffer = new String[capacity];
    }

    public int subscribe() {
        lock.lock();
        try {
            int consumerId = nextConsumerId.getAndIncrement();
            consumerOffsets.put(consumerId, writeOffset.get());
            return consumerId;
        } finally {
            lock.unlock();
        }
    }

    public void publish(String message) {
        lock.lock();
        try {
            if (shutdown.get()) {
                return;
            }

            while (!shutdown.get() && isFull()) {
                notFull.await();
            }

            if (shutdown.get()) {
                return;
            }

            int index = (int) (writeOffset.get() % capacity);
            buffer[index] = message;
            writeOffset.incrementAndGet();

            notEmpty.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public String consume(int consumerId) {
        lock.lock();
        try {
            Long readOffset = consumerOffsets.get(consumerId);
            if (readOffset == null) {
                return null;
            }

            while (readOffset >= writeOffset.get() && !shutdown.get()) {
                notEmpty.await();
            }

            if (readOffset >= writeOffset.get()) {
                return null;
            }

            int index = (int) (readOffset % capacity);
            String value = buffer[index];
            consumerOffsets.put(consumerId, readOffset + 1);

            notFull.signalAll();
            return value;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public boolean hasPending(int consumerId) {
        lock.lock();
        try {
            Long readOffset = consumerOffsets.get(consumerId);
            return readOffset != null && readOffset < writeOffset.get();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            shutdown.set(true);
            notFull.signalAll();
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        long slowestReadOffset = getSlowestReadOffset();
        return writeOffset.get() - slowestReadOffset >= capacity;
    }

    private long getSlowestReadOffset() {
        long slowest = writeOffset.get();
        for (long offset : consumerOffsets.values()) {
            if (offset < slowest) {
                slowest = offset;
            }
        }
        return slowest;
    }
}
