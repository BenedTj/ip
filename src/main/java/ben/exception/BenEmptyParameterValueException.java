package ben.exception;

// The class that represents BenExceptions for when command parameter value is empty.
public class BenEmptyParameterValueException extends BenException {
    /**
     * Initializes a BenEmptyDescriptionException object
     * with the default message.
     */
    public BenEmptyParameterValueException() {
        super("The value cannot be empty.");
    }

    /**
     * Initializes a BenMissingParameterException object
     * with the default message that mentions the task type.
     *
     * @param parameterName The name of the parameter.
     * @param taskType The type of task.
     */
    public BenEmptyParameterValueException(String parameterName, String taskType) {
        super("The " + parameterName + " of a " + taskType + " cannot be empty.");
    }
}
