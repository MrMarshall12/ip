package erika.utilities;

import erika.entities.Task;

import java.io.IOException;
import java.util.ArrayList;

/** A class representing a list */
public class List {
    private ArrayList<Task> tasks;
    private Database database;

    public List() {
        try {
            database = new Database();
            tasks = database.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /** Adds a task to the list and to the database */
    public void add(Task task) {
        try {
            database.store(task);
            tasks.add(task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Checks if the specified index is within boundary */
    public boolean isWithinBounds(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Removes task from the list and overwrites the database
     *
     * @return the task being removed
     */
    public Task remove(int index) {
        Task task = tasks.remove(index);
        try {
            database.overwrite(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks.add(index, task);
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

    /** Marks the status of a task and overwrites the database*/
    public void mark(int taskIndex, boolean status) {
        try {
            tasks.get(taskIndex).setDone(status);
            database.overwrite(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Prints non-empty elements of the list */
    public void display() {
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + ". " + task);
        }
    }
}
