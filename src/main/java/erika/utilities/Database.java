package erika.utilities;

import erika.entities.Deadlines;
import erika.entities.Task;
import erika.entities.ToDos;
import erika.exceptions.ErikaIOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/** A class representing a database */
public class Database {
    private File storage;
    private File storageTemp;

    public Database() throws ErikaIOException {
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
            throw new ErikaIOException("Database creation failed");
        }

        storage = path.toFile();
        storageTemp = pathTemp.toFile();
    }

    /** Stores task to storage file */
    public void store(Task task) throws ErikaIOException {
        try (FileWriter fileWriterAppend = new FileWriter(storage, true)) {
            fileWriterAppend.write(task.formatToStorageString() + "\n");
        } catch (IOException e) {
            throw new ErikaIOException("Database write failed");
        }
    }

    /**
     * Loads tasks from storage file
     *
     * @return A list of Tasks
     */
    public ArrayList<Task> load() throws ErikaIOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(storage);
            Task task = null;
            boolean isDone = false;
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                String[] items = item.split(",");
                if (items.length == 3 && items[0].equals("todo")) {
                    isDone = items[1].equals("[X]");
                    task = new ToDos(items[2]);
                } else if (items.length == 4 && items[0].equals("deadline")) {
                    isDone = items[1].equals("[X]");
                    task = new Deadlines(items[2], items[3]);
                } else if (items.length == 5 && items[0].equals("event")) {
                    isDone = items[1].equals("[X]");
                    task = new Deadlines(items[2], items[3]);
                } else {
                    break; // Content not in the expected format
                }
                task.setDone(isDone);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new ErikaIOException("Database file not found");
        }
        return tasks;
    }

    /**
     * Overwrites tasks from storage file
     *
     * @return A new list of Tasks
     */
    public ArrayList<Task> overwrite(ArrayList<Task> tasks) throws ErikaIOException {
        try (FileWriter fileWriterOverwrite = new FileWriter(storageTemp)) {
            for (Task task : tasks) {
                fileWriterOverwrite.write(task.formatToStorageString() + "\n");
            }
            Files.move(storageTemp.toPath(), storage.toPath(),
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new ErikaIOException("Database write failed");
        }
        return load();
    }
}
