package erika.utilities;

import erika.entities.Deadlines;
import erika.entities.Task;
import erika.entities.ToDos;

import java.io.File;
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
    File storage;
    File storageTemp;

    public Database() throws IOException {
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
            throw new IOException("Database creation failed");
        }

        storage = path.toFile();
        storageTemp = pathTemp.toFile();
    }

    /** Stores task to storage file */
    public void store(Task task) throws IOException {
        FileWriter fileWriterAppend = new FileWriter(storage, true);
        try {
            fileWriterAppend.write(task.formatToStorageString() + "\n");
        } catch (IOException e) {
            throw new IOException("Database write failed");
        } finally {
            fileWriterAppend.close();
        }
    }

    /**
     * Loads tasks from storage file
     *
     * @return A list of Tasks
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
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
                task = new Deadlines(items[2],  items[3]);
            } else if (items.length == 5 && items[0].equals("event")) {
                isDone = items[1].equals("[X]");
                task = new Deadlines(items[2],  items[3]);
            } else {
                break; // Content not in the expected format
            }
            task.setDone(isDone);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Overwrites tasks from storage file
     *
     * @return A new list of Tasks
     */
    public ArrayList<Task> overwrite(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriterOverwrite = new FileWriter(storageTemp);
        try {
            for (Task task : tasks) {
                fileWriterOverwrite.write(task.formatToStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Database write failed");
        } finally {
            fileWriterOverwrite.close();
        }
        Files.move(storageTemp.toPath(), storage.toPath(),
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.ATOMIC_MOVE);
        return load();
    }
}
