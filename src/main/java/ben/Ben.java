package ben;

import ben.core.Parser;
import ben.core.Storage;
import ben.core.TaskList;
import ben.core.command.Command;
import ben.core.ui.BaseUi;
import ben.exception.BenException;

// The class that runs the Ben program.
public class Ben {
    private Storage storage;
    private TaskList tasks;
    private BaseUi ui;

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
        this.ui = new BaseUi();
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
            ui.showBenExceptionBase(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            String responseMessage = c.executeBase(this.tasks, this.ui, this.storage);
            String tasksRepresentation = this.tasks.getTasksRepresentation();
            this.storage.overwriteRawData(tasksRepresentation);
            return ui.showMessageBase(responseMessage);
        } catch (BenException e) {
            return ui.showBenExceptionBase(e);
        }
    }
}
