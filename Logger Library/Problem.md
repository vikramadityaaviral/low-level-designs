# Logger Library - Machine Coding Problem Statement

## Problem
Design and implement a logger library that supports both synchronous and asynchronous logging.

The library should allow application code to create a logger with:

- a minimum log level
- a timestamp format
- one or more appenders
- optional async buffer capacity for asynchronous mode

The logger must filter messages by log level and format each log entry consistently before sending it to the configured appenders.

## Functional Requirements

1. Support the following log levels:
   - `TRACE`
   - `DEBUG`
   - `INFO`
   - `WARN`
   - `ERROR`

2. Filter logs based on the configured minimum log level.

3. Support multiple appenders per logger.

4. Support at least these appenders:
   - console appender
   - file appender

5. Support two logger modes:
   - `SyncLogger`: writes directly to appenders on the caller thread
   - `AsyncLogger`: publishes logs into a buffer and appends them from background consumer threads

6. Format each log line with:
   - timestamp
   - log level
   - thread name
   - message

7. Provide graceful shutdown:
   - stop accepting new async logs
   - drain already published async logs
   - close resources cleanly

## Expected Behavior

- A log below the configured minimum level should be ignored.
- Sync logging should write immediately to all configured appenders.
- Async logging should return quickly to the caller and process logs in the background.
- The async buffer should provide backpressure when it becomes full.
- Shutdown should not drop already accepted logs.

## Suggested API

```java
Logger logger = LoggerFactory.getSyncLogger(
    "INFO",
    "yyyy-MM-dd HH:mm:ss",
    List.of("CONSOLE", "FILE")
);

Logger asyncLogger = LoggerFactory.getAsyncLogger(
    "DEBUG",
    "yyyy-MM-dd HH:mm:ss",
    List.of("CONSOLE", "FILE"),
    100
);

logger.info("hello");
asyncLogger.error("something failed");
asyncLogger.shutdown();
```

## Non-Functional Expectations

- Keep the design simple enough for a machine coding round.
- Use clean object-oriented design.
- Make the async implementation thread-safe.
- Keep the implementation easy to extend with new appenders or logger modes.

## Discussion Points for the Interview

- How do you handle backpressure in async mode?
- How do you ensure no published log is lost during shutdown?
- How would you add a new appender type?
- How would you make the async buffer more efficient?
- How would you test log filtering and shutdown behavior?

