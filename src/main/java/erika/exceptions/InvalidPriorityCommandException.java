package erika.exceptions;

/**
 * A subclass of ErikaException representing InvalidPriorityCommandException.
 */
public class InvalidPriorityCommandException extends ErikaException {
    /**
     * Instantiates an instance of InvalidPriorityCommandException.
     */
    public InvalidPriorityCommandException() {
        super("""
                Invalid delete command!
                \s
                Please ensure you follow the syntax below:
                    prio <task_number> <priority>
                \s""");
    }
}
