package erika.entities;

/** A class representing a task */
public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /** Formats a task into a storable string */
    public String formatToStorageString() {
        return isDone ? "[X]," : "[ ],";
    }

    @Override
    public String toString() {
        String mark = isDone ? "[X]" : "[ ]";
        return mark + " " + taskName;
    }
}
