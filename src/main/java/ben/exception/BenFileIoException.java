package ben.exception;

/**
 * The class that represents BenException
 * that is caused by a file input/output exception.
 */
public class BenFileIoException extends BenException {
    /**
     * Initializes a BenFileIoException object
     * with the default message.
     */
    public BenFileIoException() {
        super("Tasks saving failed due to input/output error.");
    }
}
