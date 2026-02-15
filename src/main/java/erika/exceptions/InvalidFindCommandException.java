package erika.exceptions;

/**
 * A subclass of ErikaException representing InvalidFindCommandException.
 */
public class InvalidFindCommandException extends ErikaException {
    public InvalidFindCommandException() {
        super("""
                Invalid find command!
                \s
                Please ensure you follow the syntax below:
                    find <task_name>
                \s""");
    }
}
