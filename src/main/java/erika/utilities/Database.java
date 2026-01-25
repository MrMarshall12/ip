package erika.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Database {
    File storage;
    Scanner scanner;
    FileWriter fileWriter;

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
        fileWriter = new FileWriter(storage);
    }
}
