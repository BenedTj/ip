package ben.exception;

/**
 * The class that represents a BenException for an unknown command.
 */
public class BenInvalidCommandException extends BenException {
    /**
     * Initializes a BenInvalidCommandException object
     * with the default message.
     */
    public BenInvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
