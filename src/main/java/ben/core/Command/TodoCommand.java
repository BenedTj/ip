package ben.core.Command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.task.Todo;

public class TodoCommand extends Command {
    private String todoDescription;

    /**
     * Initializes a TodoCommand object
     * where isExit is initially false
     * and there is a todoDescription.
     *
     * @param todoDescription The description of the Todo
     *                        object.
     */
    public TodoCommand(String todoDescription) {
        super();

        this.todoDescription = todoDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(this.todoDescription);
        tasks.addTask(newTask);

        ui.showTaskAddedMessage(newTask, tasks.getTasksLength());
    }
}
