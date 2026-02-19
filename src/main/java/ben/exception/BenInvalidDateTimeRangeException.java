package ben.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The class that represents a BenException for an invalid DateTime range.
 */
public class BenInvalidDateTimeRangeException extends BenException {
    /**
     * Initializes a BenInvalidDateTimeRangeException object
     * with the default message.
     */
    public BenInvalidDateTimeRangeException() {
        super("The DateTime range is invalid.");
    }

    /**
     * Initializes a BenInvalidDateTimeRangeException object
     * with the starting and ending DateTime value for the range
     * displayed within the message.
     *
     * @param beginDateTime The starting DateTime value for the range.
     * @param endDateTime The ending DateTime value for the range.
     */
    public BenInvalidDateTimeRangeException(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        super("The DateTime "
                + beginDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm", Locale.ENGLISH))
                + " must be before "
                + endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm", Locale.ENGLISH))
                + ".");
    }
}
