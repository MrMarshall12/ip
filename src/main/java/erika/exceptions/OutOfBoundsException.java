package erika.exceptions;

public class OutOfBoundsException extends ErikaException{
    public OutOfBoundsException() {
        super("""
                Task number out of bounds!
                
                Please ensure the specified task number is
                within boundary
                """);
    }
}
