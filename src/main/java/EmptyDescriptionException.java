public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        super("""
                Description for todo, deadline, and event commands cannot be empty
                
                Please ensure you follow either of the syntax below:
                    a. todo <description>
                    b. deadline <description> /by <deadline>
                    c. event <description> /from <start> /to <end>
                """);

    }
}
