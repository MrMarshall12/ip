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
import java.util.ArrayList;
import java.util.Scanner;

/** A class representing a database */
public class Database {
    File storage;
    Scanner scanner;
    FileWriter fileWriterOverwrite;
    FileWriter fileWriterAppend;

    public Database() throws IOException {
        Path path = Paths.get("data", "ErikaDatabase.txt");

        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new IOException("Database creation failed");
        }

        storage = path.toFile();
        scanner = new Scanner(storage);
        fileWriterOverwrite = new FileWriter(storage);
        fileWriterAppend = new FileWriter(storage, true);
    }

    /** Stores task to storage file */
    public void store(Task task) throws IOException {
        try {
            fileWriterOverwrite.write(task.formatToStorageString());
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
}
