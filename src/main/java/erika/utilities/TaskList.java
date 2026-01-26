package erika.utilities;

import erika.entities.Task;
import erika.exceptions.ErikaIOException;

import java.util.ArrayList;
import java.util.function.Predicate;

/** A class representing a list of tasks */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage database;

    public TaskList() throws ErikaIOException {
        database = new Storage();
        tasks = database.load();
    }


    /**
     * Adds a task to the list and to the database
     *
     * @throws ErikaIOException if the I/O fails
     */
    public void add(Task task) throws ErikaIOException {
        database.store(task);
        tasks.add(task);
    }

    /** Checks if the specified index is within boundary */
    public boolean isWithinBounds(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Removes task from the list and overwrites the database
     *
     * @return the task being removed
     * @throws ErikaIOException if the I/O fails
     */
    public Task remove(int index) throws ErikaIOException {
        Task task = tasks.remove(index);
        try {
            tasks = database.overwrite(tasks);
        } catch (ErikaIOException e) {
            tasks.add(index, task);
            throw e;
        }
        return task;
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

    /**
     * Marks the status of a task and overwrites the database
     *
     * @throws ErikaIOException if the I/O fails
     */
    public void mark(int taskIndex, boolean status) throws ErikaIOException {
        tasks.get(taskIndex).setDone(status);
        tasks = database.overwrite(tasks);
    }

    /** Prints elements of the list */
    public void display() {
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task);
        }
    }

    /** Prints elements of the list that satisfy the predicate */
    public void display(Predicate<Task> predicate) {
        int taskDisplayed = 0;
        for (Task task : tasks) {
            if (predicate.test(task)) {
                taskDisplayed++;
                System.out.println(taskDisplayed + ". " + task);
            }
        }
    }
}
