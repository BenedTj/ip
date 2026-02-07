package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;
import ben.task.Task;

import java.util.ArrayList;
import java.util.List;

// The class for the find command.
public class FindCommand extends Command {
    private String findQuery;

    /**
     * Initializes a FindCommand object
     * where isExit is initially false
     * and there is a certain findQuery.
     *
     * @param findQuery The query for filtering
     *                  using find.
     */
    public FindCommand(String findQuery) {
        this.findQuery = findQuery;
    }

    @Override
    public String executeBase(TaskList tasks, BaseUi ui, Storage storage) {
        // Get array list of tasks
        ArrayList<Task> taskAsArrayList = tasks.getTasks();

        // Get tasks filtered with findQuery
        List<Task> filteredTasks = taskAsArrayList
                .stream()
                .filter(task -> task.getDescription()
                        .contains(this.findQuery))
                .toList();

        // Get length of the filtered list
        int filteredTasksLength = filteredTasks.size();

        // Construct message
        StringBuilder messageBuilder = new StringBuilder("Here are the matching tasks in your list:");
        for (int index = 0; index < filteredTasksLength; index++) {
            int currentIndex = index + 1;
            messageBuilder.append(System.lineSeparator());
            messageBuilder.append(currentIndex + "." + filteredTasks.get(index));
        }

        return messageBuilder.toString();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = this.executeBase(tasks, ui, storage);
        ui.showMessage(message);
    }
}
