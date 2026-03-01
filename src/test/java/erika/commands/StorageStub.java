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
     * Instantiates a stub of the Storage class.
     */
    public StorageStub() throws ErikaIoException {

    }

    /**
     * Provides a stub implementation of the store method.
     */
    @Override
    protected void store(Task task) {

    }

    /**
     * Provides a stub implementation of the load method.
     *
     * @return A list of Tasks stored in the storage file.
     */
    @Override
    protected ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        return tasks;
    }

    /**
     * provides a stub implementation of the overwrite method.
     *
     * @return A new list of Tasks.
     * @throws ErikaIoException if the I/O fails.
     */
    @Override
    protected ArrayList<Task> overwrite(ArrayList<Task> tasks) {
        return load();
    }
}
