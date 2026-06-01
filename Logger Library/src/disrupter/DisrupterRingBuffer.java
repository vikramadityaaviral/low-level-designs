package disrupter;

import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DisrupterRingBuffer {

    private final int capacity;
    private int size;
    private long writeOffset;
    private final String[] logBuffer;

    // To efficiently track the slowest consumer
    private final TreeMap<Long, Integer> offsetCounts = new TreeMap<>();
    private final java.util.Map<String, Long> consumerIdvsReadOffset = new java.util.HashMap<>();

    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();

    public DisrupterRingBuffer(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.writeOffset = 0L;
        this.logBuffer = new String[capacity];
    }

    public void subscribe(String consumerId) {
        lock.lock();
        try {
            consumerIdvsReadOffset.put(consumerId, 0L);
            offsetCounts.put(0L, offsetCounts.getOrDefault(0L, 0) + 1);
        } finally {
            lock.unlock();
        }
    }

    public void publish(String message) {
        lock.lock();
        try {
            while (size == capacity) {
                full.await();
            }

            int index = (int) (writeOffset % capacity);
            logBuffer[index] = message;
            writeOffset++;
            size++;
            empty.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public String consume(String consumerId) {
        lock.lock();
        try {
            Long oldOffset = consumerIdvsReadOffset.get(consumerId);
            while (writeOffset == oldOffset) {
                empty.await();
            }

            int index = (int) (oldOffset % capacity);
            String value = logBuffer[index];

            long newOffset = oldOffset + 1;
            consumerIdvsReadOffset.put(consumerId, newOffset);
            
            // Update TreeMap to track minimum offset
            updateOffsetCount(oldOffset, newOffset);

            // Size is the difference between writeOffset and the smallest readOffset
            long minOffset = offsetCounts.firstKey();
            size = (int) (writeOffset - minOffset);

            full.signalAll();
            return value;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    private void updateOffsetCount(long oldOffset, long newOffset) {
        Integer count = offsetCounts.get(oldOffset);
        if (count == 1) {
            offsetCounts.remove(oldOffset);
        } else {
            offsetCounts.put(oldOffset, count - 1);
        }
        offsetCounts.put(newOffset, offsetCounts.getOrDefault(newOffset, 0) + 1);
    }
}
