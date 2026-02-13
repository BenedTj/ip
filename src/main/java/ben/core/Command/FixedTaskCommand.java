package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.task.FixedTask;

/**
 * The class for the fixedtask command.
 */
public class FixedTaskCommand extends Command {
    private String fixedTaskDescription;
    private String duration;

    /**
     * Initializes a FixedTaskCommand object
     * where isExit is initially false
     * and there is a deadlineDescription and by parameter.
     *
     * @param fixedTaskDescription The description of the FixedTask
     *                             object.
     * @param duration The duration for the FixedTask object.
     */
    public FixedTaskCommand(String fixedTaskDescription, String duration) {
        super();

        this.fixedTaskDescription = fixedTaskDescription;
        this.duration = duration;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        FixedTask newTask = new FixedTask(this.fixedTaskDescription, this.duration);
        tasks.addTask(newTask);

        String message = ui.showTaskAddedMessageBase(newTask, tasks.getTasksLength());
        return message;
    }
}
