package ben.task;

// Class to represent tasks for the list tasks
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Initializes a Task object with the description and a "false" value
     * on isDone
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        // Only display "X" if this.isDone is true
        return "[" + (this.isDone ? "X" : "") + "] " + this.description;
    }
}
