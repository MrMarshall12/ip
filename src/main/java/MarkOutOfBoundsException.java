public class MarkOutOfBoundsException extends ErikaException{
    public MarkOutOfBoundsException() {
        super("""
                Task number out of bounds!
                
                Please ensure the specified task number is
                within boundary
                """);
    }
}
