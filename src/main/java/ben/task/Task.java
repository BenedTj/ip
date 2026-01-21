package ben.task;

// Class to represent tasks for the list tasks
public class Task {
    protected String description;
    protected boolean isDone;

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

    /**
     * Returns "X" if the task is done or whitespace if not
     *
     * @return single character string depending on whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        // Only display "X" if this.isDone is true
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
