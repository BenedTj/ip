package ben.task;

import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;

// Class to represent tasks for the list tasks
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task object with the description and a "false" value
     * on isDone
     *
     * @param description text description of the task
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
     *
     * @throws BenMarkAlreadyDoneException If this.isDone is already true
     */
    public void markAsDone() throws BenMarkAlreadyDoneException {
        if (this.isDone) {
            throw new BenMarkAlreadyDoneException();
        } else {
            this.isDone = true;
        }
    }

    /**
     * Marks the task as undone
     *
     * @throws BenMarkAlreadyNotDoneException If this.isDone is already false
     */
    public void markAsUndone() throws BenMarkAlreadyNotDoneException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new BenMarkAlreadyNotDoneException();
        }
    }

    @Override
    public String toString() {
        // Only display "X" if this.isDone is true
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
