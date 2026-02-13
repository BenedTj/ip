package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.task.Todo;

/**
 * The class for the todo command.
 */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTask = new Todo(this.todoDescription);
        tasks.addTask(newTask);

        String message = ui.showTaskAddedMessageBase(newTask, tasks.getTasksLength());
        return message;
    }
}
