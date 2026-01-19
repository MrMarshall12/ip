public class EmptyDeadlineException extends ErikaException {
    public EmptyDeadlineException() {
        super("""
                Deadline for deadline command cannot be empty
                
                Please ensure you follow the syntax below:
                    deadline <description> /by <deadline>
                """);
    }
}
