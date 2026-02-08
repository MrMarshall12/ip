package erika.exceptions;

/**
 * A subclass of ErikaException representing InvalidPriorityOperationException.
 */
public class InvalidPriorityOperationException extends ErikaException{
    /**
     * Instantiates an instance of InvalidPriorityOperationException.
     */
    public InvalidPriorityOperationException() {
        super("""
                Invalid priority operation!
                \s
                /p can only be used with todo, deadline, or event commands.
                \s""");
    }
}
