package factory;

import disrupter.DisrupterRingBuffer;
import disrupter.DisrupterRingBufferConsumer;
import logger.AsyncLogger;
import logger.Logger;
import appender.Appender;
import appender.ConsoleAppender;
import appender.FileAppender;
import enums.LogLevel;
import logger.SyncLogger;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class LoggerFactory {

    private static final Map<String, Logger> loggerRegistry = new ConcurrentHashMap<>();

    private static Appender getAppenderByType(String type) {
        switch (type) {
            case "CONSOLE":
                return new ConsoleAppender();
            case "FILE":
                return new FileAppender();
            default:
                throw new IllegalArgumentException("Appender type not supported");
        }
    }

    public static Logger getSyncLogger(String logLevel, String datetimeFormat, List<String> appenders) {
        String key = "SYNC-" + logLevel + "-" + datetimeFormat + "-" + appenders.toString();
        return loggerRegistry.computeIfAbsent(key, k -> {
            List<Appender> appenderList = appenders.stream().map(LoggerFactory::getAppenderByType).toList();
            LogLevel level = LogLevel.valueOf(logLevel);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat).withZone(ZoneId.of("UTC"));
            return new SyncLogger(level, dateTimeFormatter, appenderList);
        });
    }

    public static Logger getAsyncLogger(String logLevel, String datetimeFormat, List<String> appenders, int bufferCapacity) {
        String key = "ASYNC-" + logLevel + "-" + datetimeFormat + "-" + appenders.toString() + "-" + bufferCapacity;
        return loggerRegistry.computeIfAbsent(key, k -> {
            DisrupterRingBuffer ringBuffer = new DisrupterRingBuffer(bufferCapacity);
            List<Appender> appenderList = appenders.stream().map(LoggerFactory::getAppenderByType).toList();
            // Start consumers
            appenderList.forEach(appender -> new DisrupterRingBufferConsumer(appender, ringBuffer));

            LogLevel level = LogLevel.valueOf(logLevel);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat).withZone(ZoneId.of("UTC"));

            return new AsyncLogger(level, dateTimeFormatter, ringBuffer);
        });
    }
}
