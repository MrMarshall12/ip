package erika.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task representing deadline task.
 */
public class Deadlines extends Task {
    /**
     * Instantiates an instance of Deadlines.
     */
    private LocalDateTime deadline;

    public Deadlines(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String formatToStorageString() {
        return "deadline,"
                + super.formatToStorageString()
                + super.getTaskName()
                + ","
                + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
                + ")";
    }
}
