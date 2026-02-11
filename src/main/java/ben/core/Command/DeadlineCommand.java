package ben.core.command;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.BaseUi;
import ben.core.ui.Ui;
import ben.task.Deadline;

import java.time.LocalDateTime;

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
    public String executeBase(TaskList tasks, BaseUi ui, Storage storage) {
        Deadline newTask = new Deadline(this.deadlineDescription, this.by);
        tasks.addTask(newTask);

        String message = ui.showTaskAddedMessageBase(newTask, tasks.getTasksLength());
        return message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = this.executeBase(tasks, ui, storage);
        ui.showMessage(message);
    }
}
