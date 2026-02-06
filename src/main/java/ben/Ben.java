package ben;

import ben.core.Command.Command;
import ben.core.Parser;
import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.exception.BenException;
import javafx.application.Application;
import javafx.stage.Stage;

// The class that runs the Ben program.
public class Ben extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a Ben object with the
     * default saving file.
     */
    public Ben() {
        this("./data/ben/tasks.txt");
    }

    /**
     * Initializes a Ben object
     * and loads tasks data from saved file.
     *
     * @param filePath The path of the file where
     *                 tasks data is saved.
     */
    public Ben(String filePath) {
        // Initializes Ui, Storage and TaskList object.
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();

        try {
            // Initialize tasks data and load to tasks
            String fileContent = this.storage.initializeRawData();
            if (!fileContent.equals("")) {
                tasks = new TaskList(this.storage.loadSavedTasks(fileContent));
            }
        } catch (BenException e) {
            // If an exception is thrown, fallback to an empty tasks.
            ui.showBenException(e);
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        // Actual JavaFX starting code needs to be run here
        new Ben().run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        // Show welcome message
        this.ui.showWelcome();
        this.ui.showLine();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                String tasksRepresentation = this.tasks.getTasksRepresentation();
                this.storage.overwriteRawData(tasksRepresentation);
                isExit = c.isExit();
            } catch (BenException e) {
                this.ui.showBenException(e);
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Ben().run();
    }
}
