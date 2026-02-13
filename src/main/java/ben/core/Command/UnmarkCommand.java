package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.Ui;
import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyNotDoneException;

/**
 * The class for the unmark command.
 */
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
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws BenMarkAlreadyNotDoneException, BenIndexOutOfRangeException {
        // Get index
        int index = indexNumber - 1;

        tasks.markNotDone(index);

        // Construct message
        StringBuilder messageBuilder = new StringBuilder("Ok, I've marked this task as not done yet:");
        messageBuilder.append(System.lineSeparator());
        messageBuilder.append("  " + tasks.getTask(index));

        return messageBuilder.toString();
    }
}
