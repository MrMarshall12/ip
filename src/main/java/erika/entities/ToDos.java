package erika.entities;

public class ToDos extends Task {
    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String formatToStorageString() {
        return super.formatToStorageString()
                + "todo,"
                + super.getTaskName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
