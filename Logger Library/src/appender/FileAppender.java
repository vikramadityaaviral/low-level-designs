package appender;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileAppender implements Appender {

    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    public FileAppender() {
        this("logs.txt");
    }

    @Override
    public void appendLog(String formattedLogMessage) {
        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(formattedLogMessage);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
