package erika.utilities.enums;

/**
 * An enumerated type representing the priority of a task.
 */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    NONE("none");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "priority: " + priority;
    }
}
