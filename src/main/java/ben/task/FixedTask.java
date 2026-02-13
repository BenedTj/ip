package ben.task;

/**
 * Class to represent tasks that need to be done for a certain duration.
 */
public class FixedTask extends Task {
    protected String duration;

    /**
     * Initializes a Deadline object with the description,
     * a duration, and a "false" value on isDone.
     *
     * @param description Text description of the Deadline task.
     * @param duration The duration of the FixedTask task.
     */
    public FixedTask(String description, String duration) {
        super(description);

        assert duration != null;

        this.duration = duration;
    }

    /**
     * Initializes a FixedTask object with the description,
     * isDone value, and a duration.
     *
     * @param description Text description of the Deadline task.
     * @param isDone Initial isDone value of the Deadline task.
     * @param duration The duration of the FixedTask task.
     */
    public FixedTask(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    @Override
    public String toRepresentation() {
        return "F|" + super.toRepresentation() + "|" + this.duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + this.duration + ")";
    }
}
