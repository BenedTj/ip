package ben.exception;

/**
 * The class that represents a BenException for marking done
 * a task that is already marked done.
 */
public class BenMarkAlreadyDoneException extends BenException {
    /**
     * Initializes a BenMarkAlreadyDoneException object
     * with the default message.
     */
    public BenMarkAlreadyDoneException() {
        super("The task is already marked as done.");
    }
}
