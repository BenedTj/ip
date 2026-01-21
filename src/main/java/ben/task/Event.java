package ben.task;

// Class to represent tasks that need to start and end at specific dates/time
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initializes a Event object with the description,
     * a date/time to start, a date/time to end
     * and a "false" value on isDone
     *
     * @param description text description of the Deadline task
     * @param from the date/time when the Event task
     *             should start as a string
     * @param to the date/time when the Event task
     *            should end as a string
     */
    public Event(String description, String from, String to) {
        super(description);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
