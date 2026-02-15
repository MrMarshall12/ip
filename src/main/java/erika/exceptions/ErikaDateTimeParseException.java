package erika.exceptions;

/**
 * A subclass of ErikaException representing ErikaDateTimeParseException.
 */
public class ErikaDateTimeParseException extends ErikaException {
    /**
     * Instantiates an instance of ErikaDateTimeParseException.
     */
    public ErikaDateTimeParseException() {
        super("""
                Invalid date-time input!
                \s
                Please ensure you follow either of the syntax below:
                    a. deadline <description> /by dd-MM-yyyy HH:mm
                    b. event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm
                \s
                Take note that:
                    1. for event, the /from cannot precede the /to
                    2. the specified date must exist
                \s""");
    }
}
