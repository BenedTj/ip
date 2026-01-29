package ben.task;

// Class to represent tasks without any date or time attached
public class Todo extends Task {
    /**
     * Initializes a Todo object with the description and a "false" value
     * on isDone.
     *
     * @param description Text description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes a Todo object with the description
     * and isDone value.
     *
     * @param description Text description of the Todo task.
     * @param isDone Initial isDone value of the Todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toRepresentation() {
        return "T|" + super.toRepresentation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
