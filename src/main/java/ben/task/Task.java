package ben.task;

import ben.exception.BenInvalidFileFormatException;
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
     * Returns the task represented by taskRepresentation
     *
     * @param taskRepresentation the raw string representation of the task
     * @return the Task object that is represented by the string
     * @throws BenInvalidFileFormatException If the format of the string is invalid
     */
    public static Task toTask(String taskRepresentation) throws BenInvalidFileFormatException {
        String[] sections = taskRepresentation.split("|");

        if (sections[0].equals("T")) {
            return new Todo(sections[1]);
        } else if (sections[0].equals("D")) {
            return new Deadline(sections[1], sections[2]);
        } else if (sections[0].equals("E")) {
            return new Event(sections[1], sections[2], sections[3]);
        }

        throw new BenInvalidFileFormatException(taskRepresentation);
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

    /**
     * Returns the representation of the task
     * to be used in hard disk storage.
     *
     * @return the string representation
     */
    public String toRepresentation() {
        return this.getStatusIcon() + "|" + this.description;
    }

    @Override
    public String toString() {
        // Only display "X" if this.isDone is true
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
