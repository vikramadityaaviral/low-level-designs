package logger;

import disrupter.DisrupterRingBuffer;
import enums.LogLevel;

import java.time.format.DateTimeFormatter;

public class AsyncLogger extends AbstractLogger {

    private DisrupterRingBuffer ringBuffer;

    public AsyncLogger(LogLevel logLevel, DateTimeFormatter dateTimeFormatter, DisrupterRingBuffer disrupterRingBuffer) {
        super(logLevel, dateTimeFormatter);
        this.ringBuffer = disrupterRingBuffer;
    }

    private void processLog(LogLevel logLevel, String message) {
        String formattedLogMessage = checkAndGetLogString(message, logLevel);
        if (formattedLogMessage == null)
            return;

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
}
