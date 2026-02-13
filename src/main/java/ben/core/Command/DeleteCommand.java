package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.exception.BenFileIoException;
import ben.exception.BenIndexOutOfRangeException;
import ben.task.Task;

/**
 * The class for the delete command.
 */
public class DeleteCommand extends Command {
    private int indexNumber;

    /**
     * Initializes a MarkCommand object
     * where isExit is initially false
     * and there is a certain indexNumber.
     *
     * @param indexNumber The index number of task to
     *                    mark done.
     */
    public DeleteCommand(int indexNumber) {
        super();

        this.indexNumber = indexNumber;
    }

    @Override
    public String execute(TaskList tasks, BaseUi ui, Storage storage)
            throws BenIndexOutOfRangeException, BenFileIoException {
        // Get index and task to delete
        int index = indexNumber - 1;
        Task taskToDelete;

        try {
            // Get task to delete
            taskToDelete = tasks.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new BenIndexOutOfRangeException();
        }

        // Delete task
        tasks.deleteTask(index);

        // Update saved tasks
        String savedContent = tasks.getTasksRepresentation();
        storage.overwriteRawData(savedContent);

        // Construct message
        StringBuilder messageBuilder = new StringBuilder("Noted. I've removed this task:");
        messageBuilder.append(System.lineSeparator());
        messageBuilder.append("  " + taskToDelete);
        messageBuilder.append(System.lineSeparator());
        messageBuilder.append("Now you have " + tasks.getTasksLength() + " tasks in the list.");

        return messageBuilder.toString();
    }
}
