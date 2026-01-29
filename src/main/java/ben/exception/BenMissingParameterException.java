package ben.exception;

public class BenMissingParameterException extends BenException {
    /**
     * Initializes a BenMissingParameterException object
     * with the default message.
     */
    public BenMissingParameterException() {
        super("There is a missing parameter.");
    }

    /**
     * Initializes a BenMissingParameterException object
     * with the default message that mentions the parameter name.
     *
     * @param parameterName The parameter name.
     */
    public BenMissingParameterException(String parameterName) {
        super("The parameter " + parameterName + " is missing.");
    }
}
