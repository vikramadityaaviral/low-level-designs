package disrupter;

import appender.Appender;

public class DisrupterRingBufferConsumer {

    private final Appender appender;
    private final DisrupterRingBuffer ringBuffer;
    private final int consumerId;
    private final Thread thread;
    private volatile boolean running = true;

    public DisrupterRingBufferConsumer(Appender appender, DisrupterRingBuffer ringBuffer) {
        this.appender = appender;
        this.ringBuffer = ringBuffer;
        this.consumerId = this.ringBuffer.subscribe();

        this.thread = new Thread(this::run, "logger-consumer-" + consumerId);
        this.thread.start();
    }

    private void run() {
        try {
            while (running || ringBuffer.hasPending(consumerId)) {
                String message = ringBuffer.consume(consumerId);
                if (message == null) {
                    continue;
                }

                try {
                    appender.appendLog(message);
                } catch (Exception e) {
                    System.err.println("Appender failed: " + e.getMessage());
                }
            }
        } finally {
            try {
                appender.close();
            } catch (Exception e) {
                System.err.println("Failed to close appender: " + e.getMessage());
            }
        }
    }

    public void requestStop() {
        running = false;
    }

    public void join() throws InterruptedException {
        thread.join();
    }
}
