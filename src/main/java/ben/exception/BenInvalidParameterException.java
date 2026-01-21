package ben.exception;

public class BenInvalidParameterException extends BenException {
    /**
     * Initializes a BenInvalidParameterException object
     * with the default message
     */
    public BenInvalidParameterException(String value) {
        super(value + " is an invalid value for the parameter.");
    }
}
