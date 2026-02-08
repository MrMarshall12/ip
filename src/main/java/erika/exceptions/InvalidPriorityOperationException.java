package erika.exceptions;

public class InvalidPriorityOperationException extends ErikaException{
    public InvalidPriorityOperationException() {
        super("""
                Invalid priority operation!
                \s
                /p can only be used with todo, deadline, or event commands.
                \s""");
    }
}
