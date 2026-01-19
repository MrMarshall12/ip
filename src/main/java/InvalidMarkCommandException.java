public class InvalidMarkCommandException extends ErikaException {
    public InvalidMarkCommandException() {
        super("""
                Invalid mark command!
                
                Please ensure you follow either of the syntax below:
                    a. mark <task_number>
                    b. unmark <task_number>
                """);
    }
}
