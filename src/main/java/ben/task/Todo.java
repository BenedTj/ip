package ben.task;

// Class to represent tasks without any date or time attached
public class Todo extends Task {
    /**
     * Initializes a Todo object with the description and a "false" value
     * on isDone
     *
     * @param description text description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
