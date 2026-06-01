package enums;

public enum LogLevel {
    TRACE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);


    private int priority;

    public int getPriority() {
        return priority;
    }

    LogLevel(int priority) {
        this.priority = priority;
    }
}
