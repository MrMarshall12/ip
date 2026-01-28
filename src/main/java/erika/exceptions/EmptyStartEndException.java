package erika.exceptions;

/**
 * A subclass of ErikaException representing EmptyStartEndException.
 */
public class EmptyStartEndException extends ErikaException {
    /**
     * Instantiates an instance of EmptyStartEndException.
     */
    public EmptyStartEndException() {
        super("""
                Start and end time for event command cannot be empty!
                
                Please ensure you follow the syntax below:
                    event <description> /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm
                """);
    }
}
