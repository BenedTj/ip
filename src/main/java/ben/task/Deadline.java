package ben.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class to represent tasks that need to be done before a deadline
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initializes a Deadline object with the description,
     * a deadline, and a "false" value on isDone.
     *
     * @param description Text description of the Deadline task.
     * @param by The deadline of the Deadline task as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes a Deadline object with the description,
     * isDone value, and a deadline.
     *
     * @param description Text description of the Deadline task.
     * @param isDone Initial isDone value of the Deadline task.
     * @param by The deadline of the Deadline task as a LocalDateTime object.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toRepresentation() {
        return "D|" + super.toRepresentation() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
