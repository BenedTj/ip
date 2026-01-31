package ben.exception;

/**
 * The class that represents a BenException for marking not done
 * a task that is already marked not done.
 */
public class BenMarkAlreadyNotDoneException extends BenException {
    /**
     * Initializes a BenMarkAlreadyNotDoneException object
     * with the default message.
     */
    public BenMarkAlreadyNotDoneException() {
        super("The task is already marked as not done.");
    }
}
