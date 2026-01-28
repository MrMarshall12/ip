package erika.commands;

import erika.exceptions.ErikaException;
import erika.utilities.TaskList;
import erika.utilities.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class to test the DeleteCommand class.
 */
public class DeleteCommandTest {

    /**
     * Tests execute method.
     */
    @Test
    public void execute_nonExistentTask_exceptionThrown() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand("delete 1");
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            deleteCommand.execute(taskList, ui);
            fail();
        } catch (ErikaException e) {
            String message = """
                Task number out of bounds!
                
                Please ensure the specified task number is
                within boundary
                """;
            assertEquals(message, e.getMessage());
        }

    }
}
