/** A class representing a list */
public class List {
    private Task[] tasks;
    private int index;

    public List() {
        tasks = new Task[100];
        index = 0;
    }


    /** Adds string to strings array */
    public void add(Task task) {
        tasks[index] = task;
        index++;
    }

    /** Check if the list is empty */
    public boolean isEmpty() {
        return index == 0;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    /** Marks the status of a task */
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
