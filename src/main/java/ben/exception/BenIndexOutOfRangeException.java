package ben.exception;

public class BenIndexOutOfRangeException extends BenException {
    /**
     * Initializes a BenIndexOutOfRangeException object
     * with the default message.
     */
    public BenIndexOutOfRangeException() {
        super("The index is out of range and not found.");
    }

    /**
     * Initializes a BenIndexOutOfRangeException object
     * with the default message that mentions the index.
     *
     * @param index The index of the element.
     */
    public BenIndexOutOfRangeException(int index) {
        super("The index " + (index + 1) + " is out of range and not found.");
    }
}
