package erika.exceptions;

/** A subclass of ErikaException representing InvalidDeleteCommandException */
public class InvalidDeleteCommandException extends ErikaException {
    public InvalidDeleteCommandException() {
        super("""
                Invalid delete command!
                
                Please ensure you follow the syntax below:
                    delete <task_number>
                """);
    }
}
