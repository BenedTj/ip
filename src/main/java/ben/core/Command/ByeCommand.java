package ben.core.Command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;

// The class for the bye command
public class ByeCommand extends Command {
    /**
     * Initializes a ByeCommand object
     * where isExit is initially false.
     */
    public ByeCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");

        this.isExit = true;
    }
}
