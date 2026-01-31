package ben.core;

import ben.exception.BenFileIOException;
import ben.exception.BenInvalidFileFormatException;
import ben.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    /**
     * Initializes a Storage object whose files
     * are stored in the filePath
     *
     * @param filePath The file path for storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the contents of the file with the filePath
     * and create the file if it does not exist.
     *
     * @return The text content of the file.
     * @throws BenFileIOException If an input/output runtime exception occurs.
     */
    public String initializeRawData()
            throws BenFileIOException {
        File file = new File(this.filePath);
        try {
            if (file.exists()) {
                StringBuilder contentBuilder = new StringBuilder();

                Scanner fileScanner = new Scanner(file);

                // Get first line
                if (fileScanner.hasNextLine()) {
                    contentBuilder.append(fileScanner.nextLine());
                }

                // Get remaining lines
                while (fileScanner.hasNextLine()) {
                    contentBuilder.append(System.lineSeparator());
                    contentBuilder.append(fileScanner.nextLine());
                }

                return contentBuilder.toString();
            } else {
                Path path = Path.of(this.filePath);

                // Create directories
                Files.createDirectories(path.getParent());

                // Create file
                file.createNewFile();

                return "";
            }
        } catch (IOException e) {
            throw new BenFileIOException();
        }
    }

    /**
     * Writes the content of the file found in filePath with
     * the string content.
     *
     * @param content The content to overwrite the file with.
     * @throws BenFileIOException If a runtime input/output exception is thrown.
     */
    public void overwriteRawData(String content) throws BenFileIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            throw new BenFileIOException();
        }
    }

    /**
     * Returns an array list of Task object
     * given the content of the saved file.
     *
     * @param content The content to translate
     *                into an array list of Task objects.
     * @ return The tasks loaded up from the saved file.
     * @throws BenInvalidFileFormatException If one of the lines in the content
     *                                       has an incorrect format.
     */
    public ArrayList<Task> loadSavedTasks(String content)
            throws BenInvalidFileFormatException {
        // Initialize empty tasks
        ArrayList<Task> tasks = new ArrayList<>();

        // Split by
        String[] lines = content.split(System.lineSeparator());
        int linesLength = lines.length;

        for (int i = 0; i < linesLength; i++) {
            // Add task to tasks
            Task task = Task.toTask(lines[i]);
            tasks.add(task);
        }

        return tasks;
    }
}
