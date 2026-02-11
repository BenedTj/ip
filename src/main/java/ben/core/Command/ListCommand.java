package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;
import ben.task.Task;

import java.util.ArrayList;

/**
 * The class for the list command.
 */
public class ListCommand extends Command {
    /**
     * Initializes a ListCommand object
     * where isExit is initially false.
     */
    public ListCommand() {
        super();
    }

    @Override
    public String executeBase(TaskList tasks, BaseUi ui, Storage storage) {
        // Get array list of tasks
        ArrayList<Task> taskAsArrayList = tasks.getTasks();
        int tasksLength = taskAsArrayList.size();

        // Construct message
        StringBuilder messageBuilder = new StringBuilder("Here are the tasks in your list:");
        for (int index = 0; index < tasksLength; index++) {
            int currentIndex = index + 1;
            messageBuilder.append(System.lineSeparator());
            messageBuilder.append(currentIndex + "." + taskAsArrayList.get(index));
        }

        return messageBuilder.toString();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = this.executeBase(tasks, ui, storage);
        ui.showMessage(message);
    }
}
