package erika.entities;

/**
 * A subclass of Task representing todo task.
 */
public class ToDos extends Task {
    /**
     * Instantiates an instance of ToDos.
     */
    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String formatToStorageString() {
        return "todo,"
                + super.formatToStorageString()
                + super.getTaskName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
