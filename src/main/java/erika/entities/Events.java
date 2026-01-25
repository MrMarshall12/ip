package erika.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** A subclass of Task representing event task */
public class Events extends Task {
    private LocalDateTime begin;
    private LocalDateTime end;

    public Events(String taskName, LocalDateTime begin, LocalDateTime end) {
        super(taskName);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String formatToStorageString() {
        return "event,"
                + super.formatToStorageString()
                + super.getTaskName()
                + ","
                + begin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                + ","
                + end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + begin.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))
                + ")";
    }
}
