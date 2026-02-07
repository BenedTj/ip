package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;
import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyDoneException;

// The class for the mark command.
public class MarkCommand extends Command {
    private int indexNumber;

    /**
     * Initializes a MarkCommand object
     * where isExit is initially false
     * and there is a certain indexNumber.
     *
     * @param indexNumber The index number of task to
     *                    mark done.
     */
    public MarkCommand(int indexNumber) {
        super();

        this.indexNumber = indexNumber;
    }

    @Override
    public String executeBase(TaskList tasks, BaseUi ui, Storage storage)
            throws BenMarkAlreadyDoneException, BenIndexOutOfRangeException {
        // Get index
        int index = indexNumber - 1;

        tasks.markDone(index);

        // Build confirmation message
        StringBuilder messageBuilder = new StringBuilder("Ok, I've marked this task as not done yet:");
        messageBuilder.append(System.lineSeparator());
        messageBuilder.append("  " + tasks.getTask(index));

        return messageBuilder.toString();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws BenMarkAlreadyDoneException, BenIndexOutOfRangeException {
        String message = this.executeBase(tasks, ui, storage);
        ui.showMessage(message);
    }
}
