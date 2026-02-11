package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;

/**
 The class for the bye command
 */
public class ByeCommand extends Command {
    /**
     * Initializes a ByeCommand object
     * where isExit is initially false.
     */
    public ByeCommand() {
        super();
    }

    @Override
    public String executeBase(TaskList tasks, BaseUi ui, Storage storage) {
        String message = "Bye. Hope to see you again soon!";
        this.isExit = true;

        return message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = this.executeBase(tasks, ui, storage);
        ui.showMessage(message);
    }
}
