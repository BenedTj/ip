package ben.exception;

// The class that represents BenExceptions for Datetime parsing.
public class BenDatetimeParseException extends BenException {
    /**
     * Initializes a BenDatetimeParseExeption object
     * with the default message.
     */
    public BenDatetimeParseException() {
        super("Invalid DateTime input.");
    }
}
