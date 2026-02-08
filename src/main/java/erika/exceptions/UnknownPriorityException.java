package erika.exceptions;

/**
 * A subclass of ErikaException representing UnknownPriorityException.
 */
public class UnknownPriorityException extends ErikaException{
    /**
     * Instantiates an instance of UnknownPriorityException.
     */
    public UnknownPriorityException() {
        super("""
                Unknown priority!
                \s
                Known priorities:
                     h = high, m = medium, l = low, n = no priority
                \s""");
    }
}
