package ben.exception;

public class BenInvalidFileFormatException extends BenException {
    /**
     * Initializes a BenInvalidFileFormatException object
     * with the default message.
     */
    public BenInvalidFileFormatException() {
        super("Invalid line format");
    }
    /**
     * Initializes a BenInvalidFileFormatException object
     * with the default message that mentions the line
     * that has the incorrect format.
     *
     * @param line The content of the line with the incorrect format.
     */
    public BenInvalidFileFormatException(String line) {
        super("The following line has an invalid format: " + line);
    }
}
