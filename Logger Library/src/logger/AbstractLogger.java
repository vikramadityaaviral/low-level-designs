package logger;

import enums.LogLevel;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public abstract class AbstractLogger implements Logger {

    private final LogLevel logLevel;
    private final DateTimeFormatter dateTimeFormatter;

    public AbstractLogger(LogLevel logLevel, DateTimeFormatter dateTimeFormatter) {
        this.logLevel = logLevel;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    private boolean isLogLevelAllowed(LogLevel requestedLogLevel) {
        return  requestedLogLevel.getPriority() >= logLevel.getPriority();
    }

    protected String checkAndGetLogString(String message, LogLevel requestedLogLevel) {
        if (!isLogLevelAllowed(requestedLogLevel)) {
            return null;
        }
        String threadName = Thread.currentThread().getName();
        return String.format("%s [%s] [%s] - %s",
                dateTimeFormatter.format(Instant.now()),
                requestedLogLevel,
                threadName,
                message);
    }
}
