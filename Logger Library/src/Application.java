import factory.LoggerFactory;
import logger.Logger;

import java.util.List;

public class Application {

    static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getAsyncLogger(
                "INFO",
                "yyyy-MM-dd HH:mm:ss",
                List.of("CONSOLE", "FILE"),
                20
        );

        logger.info("first info log");
        logger.debug("second one debug log");
        logger.error("the error log");
        logger.trace("the sample trace log");
        logger.warn("the warn log");

        Thread.sleep(10000);
    }
}
