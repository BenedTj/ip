package ben.exception;

/**
 * The class that represents BenException
 * that is caused by a file input/output exception.
 */
public class BenFileIOException extends BenException {
    /**
     * Initializes a BenFileIOException object
     * with the default message.
     */
    public BenFileIOException() {
        super("Tasks saving failed due to input/output error.");
    }
}
