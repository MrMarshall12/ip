package erika.utilities.enums;

/**
 * An enumerated type representing the priority of a task.
 */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    NONE("no");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public String formatStoragePriority() {
        return priority;
    }

    public static Priority convertToPriority(String priority) {
        switch (priority) {
        case "high":
            return HIGH;
        case "medium":
            return MEDIUM;
        case "low":
            return LOW;
        default:
            return NONE;
        }
    }

    @Override
    public String toString() {
        return "[" + priority + " priority]";
    }
}
