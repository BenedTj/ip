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
     * with the default message that mentions the index number.
     *
     * @param indexNumber The index number of the element.
     */
    public BenIndexOutOfRangeException(int indexNumber) {
        super("The index " + indexNumber + " is out of range and not found.");
    }
}
