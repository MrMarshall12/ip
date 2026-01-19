public class InvalidDeleteCommandException extends ErikaException {
    public InvalidDeleteCommandException(String message) {
        super("""
                Invalid delete command!
                
                Please ensure you follow the syntax below:
                    delete <task_number>
                """);
    }
}
