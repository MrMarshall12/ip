package erika.exceptions;

/** A subclass of ErikaException representing ErikaDateTimeParseException */
public class ErikaDateTimeParseException extends ErikaException{
    public ErikaDateTimeParseException() {
        super("""
                Invalid date-time input!
                
                Please ensure you follow either of the syntax below:
                    a. deadline <description> /by dd-MM-yyyy HH:mm
                    b. event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm
                """);
    }
}
