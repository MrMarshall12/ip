/** A class representing a list */
public class List {
    private Task[] tasks;
    private int index;

    public List() {
        tasks = new Task[100];
        index = 0;
    }

    /**
     * Adds string to strings array
     *
     * @return the added string
     */
    public String add(String taskName) {
        Task task = new Task(taskName);
        tasks[index] = task;
        index++;
        return taskName;
    }

    /** Mark the status of a task */
    public void mark(int taskIndex, boolean status) {
        tasks[taskIndex].setDone(status);
    }

    /** Prints non-empty elements of the list */
    public void display() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
