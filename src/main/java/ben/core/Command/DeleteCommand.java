package ben.core.Command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.exception.BenFileIOException;
import ben.exception.BenIndexOutOfRangeException;
import ben.task.Task;

// The class for the delete command.
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
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws BenIndexOutOfRangeException, BenFileIOException {
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

        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + taskToDelete);
        ui.showMessage("Now you have " + tasks.getTasksLength() + " tasks in the list.");

        // Update saved tasks
        String savedContent = tasks.getTasksRepresentation();
        storage.overwriteRawData(savedContent);
    }
}
