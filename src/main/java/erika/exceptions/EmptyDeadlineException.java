package erika.exceptions;

/**
 * A subclass of ErikaException representing EmptyDeadlineException.
 */
public class EmptyDeadlineException extends ErikaException {
    /**
     * Instantiates an instance of EmptyDeadlineException.
     */
    public EmptyDeadlineException() {
        super("""
                Deadline for deadline command cannot be empty!
                
                Please ensure you follow the syntax below:
                    deadline <description> /by dd-MM-yyyy HH:mm
                """);
    }
}
