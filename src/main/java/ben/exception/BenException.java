package ben.exception;

/**
 The class that represents the base class for BenException.
 */
public class BenException extends Exception {
    /**
     * Initializes a BenException object with the message.
     *
     * @param message Exception message as a string.
     */
    public BenException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
