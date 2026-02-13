package ben.core.command;

import java.time.LocalDateTime;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.Ui;
import ben.task.Deadline;
/**
 * The class for the deadline command.
 */
public class DeadlineCommand extends Command {
    private String deadlineDescription;
    private LocalDateTime by;

    /**
     * Initializes a DeadlineCommand object
     * where isExit is initially false
     * and there is a deadlineDescription and by parameter.
     *
     * @param deadlineDescription The description of the Deadline
     *                        object.
     * @param by The deadline for the Deadline object.
     */
    public DeadlineCommand(String deadlineDescription, LocalDateTime by) {
        super();

        this.deadlineDescription = deadlineDescription;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.deadlineDescription, this.by);
        tasks.addTask(newTask);

        String message = ui.showTaskAddedMessageBase(newTask, tasks.getTasksLength());
        return message;
    }
}
