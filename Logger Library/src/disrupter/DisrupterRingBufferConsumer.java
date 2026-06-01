package disrupter;

import appender.Appender;

import java.util.UUID;

public class DisrupterRingBufferConsumer {

    private Appender appender;
    private DisrupterRingBuffer ringBuffer;
    private String consumerId;
    private Thread thread;

    public DisrupterRingBufferConsumer(Appender appender, DisrupterRingBuffer disrupterRingBuffer) {
        this.appender = appender;
        this.ringBuffer = disrupterRingBuffer;
        this.consumerId = UUID.randomUUID().toString();
        this.ringBuffer.subscribe(this.consumerId);

        this.thread = new Thread(() -> {
            while (true) {
                String message = ringBuffer.consume(consumerId);
                appender.appendLog(message);
            }
        });
        this.thread.start();
    }

    public String getConsumerId() {
        return consumerId;
    }

    public Thread getThread() {
        return thread;
    }
}
