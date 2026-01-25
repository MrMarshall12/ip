package erika.exceptions;

public class UnknownCommandException extends ErikaException {
    public UnknownCommandException() {
        super("""
                Hmm, sorry. I don't understand your command.
                
                Type 'help' if you forget my commands
                """);
    }
}
