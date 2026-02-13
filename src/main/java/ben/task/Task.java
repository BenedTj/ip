package ben.task;

import java.time.LocalDateTime;

import ben.exception.BenInvalidFileFormatException;
import ben.exception.BenMarkAlreadyDoneException;
import ben.exception.BenMarkAlreadyNotDoneException;

/**
 * Class to represent tasks for the list tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task object with the description and a "false" value
     * on isDone.
     *
     * @param description Text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a Task object with the description
     * and isDone value.
     *
     * @param description Text description of the task.
     * @param isDone Initial isDone value of task.
     */
    public Task(String description, boolean isDone) {
        assert !description.equals("");

        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task represented by taskRepresentation
     *
     * @param taskRepresentation The raw string representation of the task.
     * @return The Task object that is represented by the string.
     * @throws BenInvalidFileFormatException If the format of the string is invalid.
     */
    public static Task toTask(String taskRepresentation) throws BenInvalidFileFormatException {
        String[] sections = taskRepresentation.split("\\|");

        if (sections.length < 2) {
            throw new BenInvalidFileFormatException(taskRepresentation);
        }

        boolean markedDone = sections[1].equals("X");

        if (sections[0].equals("T")) {
            return new Todo(sections[2], markedDone);
        } else if (sections[0].equals("D")) {
            return new Deadline(sections[2], markedDone, LocalDateTime.parse(sections[3]));
        } else if (sections[0].equals("E")) {
            return new Event(
                    sections[2],
                    markedDone,
                    LocalDateTime.parse(sections[3]),
                    LocalDateTime.parse(sections[4])
            );
        }

        throw new BenInvalidFileFormatException(taskRepresentation);
    }

    /**
     * Returns "X" if the task is done or whitespace if not.
     *
     * @return Single character string depending on whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     *
     * @throws BenMarkAlreadyDoneException If this.isDone is already true.
     */
    public void markAsDone() throws BenMarkAlreadyDoneException {
        if (this.isDone) {
            throw new BenMarkAlreadyDoneException();
        } else {
            this.isDone = true;
        }
    }

    /**
     * Marks the task as undone.
     *
     * @throws BenMarkAlreadyNotDoneException If this.isDone is already false.
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
     * @return The string representation.
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
