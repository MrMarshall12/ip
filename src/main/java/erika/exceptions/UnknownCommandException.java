package erika.exceptions;

/** A subclass of ErikaException representing UnknownCommandException */
public class UnknownCommandException extends ErikaException {
    public UnknownCommandException() {
        super("""
                Hmm, sorry. I don't understand your command.
                
                Type 'help' if you forget my commands
                """);
    }
}
