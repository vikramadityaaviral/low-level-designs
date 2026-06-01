package logger;

import appender.Appender;
import enums.LogLevel;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SyncLogger extends AbstractLogger {

    private List<Appender> appenders;

    public SyncLogger(LogLevel logLevel, DateTimeFormatter dateTimeFormatter, List<Appender> appenders) {
        super(logLevel, dateTimeFormatter);
        this.appenders = appenders;
    }

    private void processLog(LogLevel logLevel, String message) {
        String formattedLog = checkAndGetLogString(message, logLevel);
        if (formattedLog == null) {
            return;
        }

        for (Appender appender: appenders){
            appender.appendLog(formattedLog);
        }
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
