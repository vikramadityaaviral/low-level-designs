package appender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender {

    private final String filePath;
    private final BufferedWriter writer;
    private volatile boolean closed = false;

    public FileAppender(String filePath) {
        this.filePath = filePath;
        try {
            this.writer = new BufferedWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to open log file: " + filePath, e);
        }
    }

    public FileAppender() {
        this("logs.txt");
    }

    @Override
    public synchronized void appendLog(String formattedLogMessage) {
        if (closed) {
            return;
        }

        try {
            writer.write(formattedLogMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    @Override
    public synchronized void close() {
        if (closed) {
            return;
        }

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Failed to close log file: " + e.getMessage());
        } finally {
            closed = true;
        }
    }
}
