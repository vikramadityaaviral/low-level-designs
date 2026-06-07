package logger;

import disrupter.DisrupterRingBuffer;
import disrupter.DisrupterRingBufferConsumer;
import enums.LogLevel;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AsyncLogger extends AbstractLogger {

    private final DisrupterRingBuffer ringBuffer;
    private final List<DisrupterRingBufferConsumer> consumers;
    private volatile boolean running = true;

    public AsyncLogger(LogLevel logLevel, DateTimeFormatter dateTimeFormatter, DisrupterRingBuffer disrupterRingBuffer, List<DisrupterRingBufferConsumer> consumers) {
        super(logLevel, dateTimeFormatter);
        this.ringBuffer = disrupterRingBuffer;
        this.consumers = consumers;
    }

    private void processLog(LogLevel logLevel, String message) {
        if (!running) {
            return;
        }

        String formattedLogMessage = checkAndGetLogString(message, logLevel);
        if (formattedLogMessage == null) {
            return;
        }

        ringBuffer.publish(formattedLogMessage);
    }


    @Override
    public void trace(String message) {
        processLog(LogLevel.TRACE, message);
    }

    @Override
    public void debug(String message) {
        processLog(LogLevel.DEBUG, message);
    }

    @Override
    public void info(String message) {
        processLog(LogLevel.INFO, message);
    }

    @Override
    public void warn(String message) {
        processLog(LogLevel.WARN, message);
    }

    @Override
    public void error(String message) {
        processLog(LogLevel.ERROR, message);
    }

    @Override
    public void shutdown() {
        running = false;

        ringBuffer.shutdown();

        for (DisrupterRingBufferConsumer consumer : consumers) {
            consumer.requestStop();
        }

        for (DisrupterRingBufferConsumer consumer : consumers) {
            try {
                consumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
