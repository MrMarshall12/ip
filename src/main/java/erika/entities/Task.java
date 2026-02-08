package erika.entities;

import erika.utilities.enums.Priority;

/**
 * A class representing a task.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private Priority priority;

    /**
     * Instantiates an instance of Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.priority = Priority.NONE;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Task setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the completion status of a task.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Formats a task into a storable string.
     */
    public String formatToStorageString() {
        return isDone
                ? "[X]," + priority.formatStoragePriority() + ","
                : "[ ]," + priority.formatStoragePriority() + ",";
    }

    @Override
    public String toString() {
        String mark = isDone ? "[X]" : "[ ]";
        return mark + priority.toString() + " " + taskName;
    }
}
