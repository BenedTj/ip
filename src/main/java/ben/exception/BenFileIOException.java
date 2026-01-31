package ben.exception;

public class BenFileIOException extends BenException {
    /**
     * Initializes a BenFileIOException object
     * with the default message.
     */
    public BenFileIOException() {
        super("Tasks saving failed due to input/output error.");
    }
}
