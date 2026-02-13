package ben.core.command;

import java.time.LocalDateTime;

import ben.core.Storage;
import ben.core.TaskList;
import ben.core.ui.Ui;
import ben.task.Event;

/**
 * The class for the event command.
 */
public class EventCommand extends Command {
    private String eventDescription;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes an EventCommand object
     * where isExit is initially false
     * and there is an eventDescription, and from and to parameters.
     *
     * @param eventDescription The description of the Event
     *                         object.
     * @param from The start date and time for the Event object.
     * @param to The end date and time for the Event object.
     */
    public EventCommand(String eventDescription, LocalDateTime from, LocalDateTime to) {
        super();

        this.eventDescription = eventDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(this.eventDescription, this.from, this.to);
        tasks.addTask(newTask);

        String message = ui.showTaskAddedMessageBase(newTask, tasks.getTasksLength());
        return message;
    }
}
