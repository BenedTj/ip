package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;
import ben.exception.BenFileIoException;
import ben.exception.BenIndexOutOfRangeException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;

/**
 * The class that is the base class for commands.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Initializes a Command object
     * where isExit is initially false.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command using interactions with other
     * components of program and return the response as a String.
     *
     * @param tasks The task list component.
     * @param ui The UI component.
     * @param storage The storage component.
     * @return The response message.
     * @throws BenMarkAlreadyDoneException If a task is trying to be marked done
     *                                     when it has already been marked as done.
     * @throws BenMarkAlreadyNotDoneException If a task is trying to be marked not done
     *                                        when it has already been marked as not done.
     * @throws BenIndexOutOfRangeException If a command is trying to access an index that is
     *                                     out of the range of values available in tasks.
     */
    public abstract String executeBase(TaskList tasks, BaseUi ui, Storage storage)
            throws BenMarkAlreadyDoneException,
            BenMarkAlreadyNotDoneException,
            BenIndexOutOfRangeException,
            BenFileIoException;

    /**
     * Executes the command using interactions with other
     * components of program.
     *
     * @param tasks The task list component.
     * @param ui The UI component.
     * @param storage The storage component.
     * @throws BenMarkAlreadyDoneException If a task is trying to be marked done
     *                                     when it has already been marked as done.
     * @throws BenMarkAlreadyNotDoneException If a task is trying to be marked not done
     *                                        when it has already been marked as not done.
     * @throws BenIndexOutOfRangeException If a command is trying to access an index that is
     *                                     out of the range of values available in tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws BenMarkAlreadyDoneException,
            BenMarkAlreadyNotDoneException,
            BenIndexOutOfRangeException,
            BenFileIoException;

    /**
     * Returns whether the program should exit.
     *
     * @return Whether the program should exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
