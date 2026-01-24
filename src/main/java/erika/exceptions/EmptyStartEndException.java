package erika.exceptions;

public class EmptyStartEndException extends ErikaException {
    public EmptyStartEndException() {
        super("""
                Start and end time for event command cannot be empty!
                
                Please ensure you follow the syntax below:
                    event <description> /from <start> /to <end>
                """);
    }
}
