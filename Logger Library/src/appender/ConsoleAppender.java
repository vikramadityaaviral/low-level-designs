package appender;

public class ConsoleAppender implements Appender {

    @Override
    public void appendLog(String formattedLogMessage) {
        System.out.println(formattedLogMessage);
    }

    @Override
    public void close() {

    }


}
