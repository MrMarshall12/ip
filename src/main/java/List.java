import erika.entities.Task;

import java.util.ArrayList;

/** A class representing a list */
public class List {
    private ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }


    /** Adds a task to the list */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Checks if the specified index is within boundary */
    public boolean isWithinBounds(int index) {
        return index >= 0 && index < tasks.size();
    }

    /** Removes a task from the list */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /** Checks if the list is empty */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /** Checks the number of task contained */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /** Marks the status of a task */
    public void mark(int taskIndex, boolean status) {
        tasks.get(taskIndex).setDone(status);
    }

    /** Prints non-empty elements of the list */
    public void display() {
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task);
        }
    }
}
