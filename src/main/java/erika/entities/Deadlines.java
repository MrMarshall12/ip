package erika.entities;

/** A subclass of Task representing deadline task */
public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String formatToStorageString() {
        return "deadline,"
                + super.formatToStorageString()
                + super.getTaskName()
                + ","
                + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
