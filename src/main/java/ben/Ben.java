package ben;

import ben.core.Parser;
import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.core.command.Command;
import ben.exception.BenException;

/**
 * The class that runs the Ben program.
 */
public class Ben {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Command lastCommand;
    private BenException startupException;

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

        this.lastCommand = null;
        this.startupException = null;

        try {
            // Initialize tasks data and load to tasks
            String fileContent = this.storage.initializeRawData();
            if (!fileContent.equals("")) {
                tasks = new TaskList(this.storage.loadSavedTasks(fileContent));
            }
        } catch (BenException e) {
            // If an exception is thrown, fallback to an empty tasks
            tasks = new TaskList();

            this.startupException = e;
        }
    }

    /**
     * Returns response for a given userInput.
     *
     * @param userInput The user input string.
     * @return The chatbot response as a string.
     */
    public String getResponse(String userInput) {
        try {
            assert !userInput.equals(userInput);
            // Execute and save last command
            Command c = Parser.parse(userInput);
            String responseMessage = c.execute(this.tasks, this.ui, this.storage);
            this.lastCommand = c;

            // Save contents of the task list to file
            String tasksRepresentation = this.tasks.getTasksRepresentation();
            this.storage.overwriteRawData(tasksRepresentation);
            return ui.showMessageBase(responseMessage);
        } catch (BenException e) {
            return ui.showBenExceptionBase(e);
        }
    }

    /**
     * Returns whether the program should exit.
     *
     * @return The boolean value for whether the program should exit.
     */
    public boolean getIsExit() {
        if (this.lastCommand == null) {
            return false;
        } else {
            return this.lastCommand.isExit();
        }
    }

    /**
     * Returns the welcome message for chatbot.
     *
     * @return The welcome message as a string.
     */
    public String getWelcomeMessage() {
        return this.ui.showWelcomeBase();
    }

    /**
     * Returns the startup exception that is a BenException.
     *
     * @return the startup exception.
     */
    public BenException getStartupException() {
        return this.startupException;
    }
}
