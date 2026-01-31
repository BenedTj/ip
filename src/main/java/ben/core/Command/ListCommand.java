package ben.core.Command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    /**
     * Initializes a ListCommand object
     * where isExit is initially false.
     */
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Get array list of tasks
        ArrayList<Task> taskAsArrayList = tasks.getTasks();
        int tasksLength = taskAsArrayList.size();

        // Print messages
        ui.showMessage("Here are the tasks in your list:");
        for (int index = 0; index < tasksLength; index++) {
            int currentIndex = index + 1;
            ui.showMessage(currentIndex + "." + taskAsArrayList.get(index));
        }
    }
}
