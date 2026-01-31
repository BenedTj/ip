package ben.core.Command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyNotDoneException;

public class UnmarkCommand extends Command {
    private int indexNumber;

    /**
     * Initializes an UnmarkCommand object
     * where isExit is initially false
     * and there is a certain indexNumber.
     *
     * @param indexNumber The index number of task to
     *                    mark done.
     */
    public UnmarkCommand(int indexNumber) {
        super();

        this.indexNumber = indexNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws BenMarkAlreadyNotDoneException, BenIndexOutOfRangeException {
        // Get index
        int index = indexNumber - 1;

        tasks.markNotDone(index);

        // Print confirmation message
        ui.showMessage("Ok, I've marked this task as not done yet:");
        ui.showMessage("  " + tasks.getTask(index));
    }
}
