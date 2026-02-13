package ben.core.command;

import java.util.ArrayList;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.task.Task;

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
    public String execute(TaskList tasks, BaseUi ui, Storage storage) {
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
}
