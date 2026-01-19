public class EmptyStartEndException extends Exception {
    public EmptyStartEndException() {
        super("""
                Start and end time for event command cannot be empty
                
                Please ensure you follow the syntax below:
                    event <description> /from <start> /to <end>
                """);
    }
}
