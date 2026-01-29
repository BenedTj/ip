package ben.exception;

public class BenMarkAlreadyNotDoneException extends BenException {
    /**
     * Initializes a BenMarkAlreadyNotDoneException object
     * with the default message.
     */
    public BenMarkAlreadyNotDoneException() {
        super("The task is already marked as not done.");
    }
}
