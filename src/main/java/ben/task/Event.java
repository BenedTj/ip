package ben.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class to represent tasks that need to start and end at specific dates/time
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initializes an Event object with the description,
     * a date/time to start, a date/time to end
     * and a "false" value on isDone.
     *
     * @param description Text description of the Deadline task.
     * @param from The date/time when the Event task
     *             should start as a LocalDateTime object.
     * @param to The date/time when the Event task
     *            should end as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);

        this.from = from;
        this.to = to;
    }

    /**
     * Initializes an Event object with the description,
     * isDone value, a date/time to start,
     * a date/time to end, and a "false" value on isDone
     *
     * @param description Text description of the Deadline task.
     * @param isDone Initial isDone value of the Deadline task.
     * @param from The date/time when the Event task
     *             should start as a LocalDateTime object.
     * @param to The date/time when the Event task
     *            should end as a LocalDateTime object.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toRepresentation() {
        return "E|" + super.toRepresentation() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy  HHmm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
