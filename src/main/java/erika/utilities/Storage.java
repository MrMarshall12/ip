package erika.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import erika.entities.Deadlines;
import erika.entities.Events;
import erika.entities.Task;
import erika.entities.ToDos;
import erika.exceptions.ErikaIoException;
import erika.utilities.enums.Priority;

/**
 * A class representing a Storage.
 */
public class Storage {
    private File storage;
    private File storageTemp;

    /**
     * Instantiates an instance of Storage.
     */
    protected Storage() throws ErikaIoException {
        Path path = Paths.get("data", "ErikaDatabase.txt");
        Path pathTemp = Paths.get("data", "ErikaDatabaseTemp.tmp");

        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            if (!Files.exists(pathTemp)) {
                Files.createFile(pathTemp);
            }
        } catch (IOException e) {
            throw new ErikaIoException("Database creation failed");
        }

        storage = path.toFile();
        storageTemp = pathTemp.toFile();
    }

    /**
     * Stores task to a storage file.
     *
     * @throws ErikaIoException if the I/O fails.
     */
    protected void store(Task task) throws ErikaIoException {
        assert Objects.nonNull(task) : "task cannot be null";
        try (FileWriter fileWriterAppend = new FileWriter(storage, true)) {
            fileWriterAppend.write(task.formatToStorageString() + "\n");
        } catch (IOException e) {
            throw new ErikaIoException("Database write failed");
        }
    }

    /**
     * Checks if the stored string value is a todo task
     * The string passed to this method has been split by comma (,)
     */
    private boolean isTodo(String[] items) {
        return items.length == 4 && items[0].equals("todo");
    }

    /**
     * Checks if the stored string value is a deadline task
     * The string passed to this method has been split by comma (,)
     */
    private boolean isDeadline(String[] items) {
        return items.length == 5 && items[0].equals("deadline");
    }

    /**
     * Checks if the stored string value is an event task
     * The string passed to this method has been split by comma (,)
     */
    private boolean isEvent(String[] items) {
        return items.length == 6 && items[0].equals("event");
    }

    /**
     * Instantiates an instance of task based on the stored string value.
     */
    private Task instantiateTask(String item) throws ErikaIoException {
        String[] items = item.split(",");
        boolean isDone = false;
        Task task;
        if (isTodo(items)) {
            isDone = items[1].equals("[X]");
            task = new ToDos(items[3]).setPriority(Priority.convertToPriority(items[2]));
        } else if (isDeadline(items)) {
            isDone = items[1].equals("[X]");
            task = new Deadlines(items[3],
                    LocalDateTime.parse(items[4], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                    .setPriority(Priority.convertToPriority(items[2]));;
        } else if (isEvent(items)) {
            isDone = items[1].equals("[X]");
            task = new Events(items[3],
                    LocalDateTime.parse(items[4], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    LocalDateTime.parse(items[5], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                    .setPriority(Priority.convertToPriority(items[2]));;
        } else {
            throw new ErikaIoException("Database file is probably corrupted or improperly formatted");
        }
        task.setDone(isDone);
        return task;
    }

    /**
     * Loads tasks from a storage file.
     *
     * @return A list of Tasks stored in the storage file.
     * @throws ErikaIoException if the I/O fails.
     */
    protected ArrayList<Task> load() throws ErikaIoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storage);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                tasks.add(instantiateTask(item));
            }
        } catch (FileNotFoundException e) {
            throw new ErikaIoException("Database file not found");
        }
        return tasks;
    }

    /**
     * Overwrites tasks from a storage file.
     *
     * @return A new list of Tasks.
     * @throws ErikaIoException if the I/O fails.
     */
    protected ArrayList<Task> overwrite(ArrayList<Task> tasks) throws ErikaIoException {
        assert Objects.nonNull(tasks) : "tasks cannot be null";
        try (FileWriter fileWriterOverwrite = new FileWriter(storageTemp)) {
            for (Task task : tasks) {
                fileWriterOverwrite.write(task.formatToStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new ErikaIoException("Database write failed");
        }
        try {
            Files.move(storageTemp.toPath(), storage.toPath(),
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            // Fallback to non-atomic move if ATOMIC_MOVE is unsupported
            // Suggested by Claude Opus 4.6
            try {
                Files.move(storageTemp.toPath(), storage.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new ErikaIoException("Database write failed");
            }
        }
        return load();
    }
}
