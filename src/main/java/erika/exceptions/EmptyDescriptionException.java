package erika.exceptions;

/** A subclass of ErikaException representing EmptyDescriptionException */
public class EmptyDescriptionException extends ErikaException {
    public EmptyDescriptionException() {
        super("""
                Description for todo, deadline, and event commands cannot be empty!
                
                Please ensure you follow either of the syntax below:
                    a. todo <description>
                    b. deadline <description> /by dd-MM-yyyy HH:mm
                    c. event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm
                """);

    }
}
