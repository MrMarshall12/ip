package erika.utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import erika.entities.Task;
import erika.exceptions.ErikaIoException;
import erika.utilities.enums.Priority;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage database;

    /**
     * Instantiates an instance of TaskList.
     */
    public TaskList() throws ErikaIoException {
        database = new Storage();
        tasks = database.load();
        tasks.sort(Comparator.comparing(Task::getPriority));
    }


    /**
     * Adds a task to the list and to the database.
     *
     * @throws ErikaIoException if the I/O fails.
     */
    public void add(Task task) throws ErikaIoException {
        database.store(task);
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getPriority));
    }

    /**
     * Checks if the specified index is within the boundary.
     */
    public boolean isWithinBounds(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Removes a task from the list and overwrites the database.
     *
     * @return the task being removed.
     * @throws ErikaIoException if the I/O fails.
     */
    public Task remove(int index) throws ErikaIoException {
        Task task = tasks.remove(index);
        try {
            tasks = database.overwrite(tasks);
        } catch (ErikaIoException e) {
            tasks.add(index, task);
            throw e;
        }
        return task;
    }

    /**
     * Checks if the list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks the number of tasks contained.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the status of a task and overwrites the database.
     *
     * @throws ErikaIoException if the I/O fails.
     */
    public void mark(int taskIndex, boolean status) throws ErikaIoException {
        tasks.get(taskIndex).setDone(status);
        tasks = database.overwrite(tasks);
    }

    /**
     * Sets the priority of a task and overwrites the database.
     */
    public Task setPriority(int taskIndex, Priority priority) throws ErikaIoException {
        Task task = tasks.get(taskIndex).setPriority(priority);
        tasks = database.overwrite(tasks);
        tasks.sort(Comparator.comparing(Task::getPriority));
        return task;
    }

    /**
     * Prints elements of the list and returns it for GUI purpose.
     */
    public String display() {
        StringBuffer sb = new StringBuffer();
        for (Task task : tasks) {
            String taskString = (tasks.indexOf(task) + 1) + ". " + task;
            System.out.println(taskString);
            sb.append(taskString + "\n");
        }
        return sb.toString();
    }

    /**
     * Prints elements of the list that satisfy the predicate and returns it for GUI purpose.
     */
    public String display(Predicate<Task> predicate) {
        StringBuffer sb = new StringBuffer();
        int taskDisplayed = 0;
        for (Task task : tasks) {
            if (predicate.test(task)) {
                taskDisplayed++;
                String taskString = taskDisplayed + ". " + task;
                System.out.println(taskString);
                sb.append(taskString + "\n");
            }
        }
        return sb.toString();
    }
}
