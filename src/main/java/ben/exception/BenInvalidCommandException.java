package ben.exception;

public class BenInvalidCommandException extends BenException {
    /**
     * Initializes a BenInvalidCommandException object
     * with the default message
     */
    public BenInvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
