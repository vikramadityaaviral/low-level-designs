import factory.LoggerFactory;
import logger.Logger;

import java.util.List;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        runSyncLoggerScenario();
        runAsyncLoggerScenario();
        runAsyncShutdownScenario();
    }

    private static void runSyncLoggerScenario() {
        System.out.println("=== Sync logger scenario ===");

        Logger logger = LoggerFactory.getSyncLogger(
                "INFO",
                "yyyy-MM-dd HH:mm:ss",
                List.of("CONSOLE", "FILE")
        );

        logger.trace("this trace should be filtered out");
        logger.debug("this debug should also be filtered out");
        logger.info("sync info log");
        logger.warn("sync warn log");
        logger.error("sync error log");
        logger.shutdown();
    }

    private static void runAsyncLoggerScenario() throws InterruptedException {
        System.out.println("=== Async logger scenario ===");

        Logger logger = LoggerFactory.getAsyncLogger(
                "DEBUG",
                "yyyy-MM-dd HH:mm:ss",
                List.of("CONSOLE", "FILE"),
                8
        );

        logger.trace("this trace should be filtered out");
        logger.debug("async debug log");
        logger.info("async info log");
        logger.warn("async warn log");
        logger.error("async error log");

        Thread.sleep(500);
        logger.shutdown();
    }

    private static void runAsyncShutdownScenario() throws InterruptedException {
        System.out.println("=== Async shutdown scenario ===");

        Logger logger = LoggerFactory.getAsyncLogger(
                "INFO",
                "yyyy-MM-dd HH:mm:ss",
                List.of("CONSOLE"),
                4
        );

        for (int i = 1; i <= 10; i++) {
            logger.info("burst log " + i);
        }

        Thread.sleep(500);
        logger.shutdown();
    }
}
