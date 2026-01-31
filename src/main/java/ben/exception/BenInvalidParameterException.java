package ben.exception;

/**
 * The class that represents a BenException
 * for an invalid value as a command parameter.
 */
public class BenInvalidParameterException extends BenException {
    /**
     * Initializes a BenInvalidParameterException object
     * with the default message.
     */
    public BenInvalidParameterException(String value) {
        super(value + " is an invalid value for the parameter.");
    }
}
