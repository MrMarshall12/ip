package erika.commands;

import java.util.ArrayList;

import erika.entities.Task;
import erika.exceptions.ErikaIoException;
import erika.utilities.Storage;


/**
 * A class representing a storage stub.
 */
public class StorageStub extends Storage {

    /**
     * Provides a stub implementation of the Storage class.
     */
    public StorageStub() throws ErikaIoException {

    }

    /**
     * Provides a stub implementation of the store method.
     *
     * @throws ErikaIoException if the I/O fails.
     */
    protected void store(Task task) throws ErikaIoException {

    }

    /**
     * Provides a stub implementation of the load method.
     *
     * @return A list of Tasks stored in the storage file.
     * @throws ErikaIoException if the I/O fails.
     */
    protected ArrayList<Task> load() throws ErikaIoException {
        ArrayList<Task> tasks = new ArrayList<>();
        return tasks;
    }

    /**
     * provides a stub implementation of the overwrite method.
     *
     * @return A new list of Tasks.
     * @throws ErikaIoException if the I/O fails.
     */
    protected ArrayList<Task> overwrite(ArrayList<Task> tasks) throws ErikaIoException {
        return load();
    }
}
