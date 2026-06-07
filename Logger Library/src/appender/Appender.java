package appender;

public interface Appender extends AutoCloseable {

    void appendLog(String formattedLogMessage);

    @Override
    void close();
}
