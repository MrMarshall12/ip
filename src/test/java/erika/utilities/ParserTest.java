package erika.utilities;

import erika.commands.ByeCommand;
import erika.commands.DeadlineCommand;
import erika.commands.DeleteCommand;
import erika.commands.EventCommand;
import erika.commands.HelpCommand;
import erika.commands.ListCommand;
import erika.commands.MarkCommand;
import erika.commands.ToDoCommand;
import erika.exceptions.ErikaException;
import erika.exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A class to test the Parser class.
 */
public class ParserTest {

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_listCommand_success() throws Exception {
        assertEquals((new ListCommand("list")).toString(),
                Parser.parseCommand("list").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_markCommand_success() throws Exception {
        assertEquals((new MarkCommand("mark 2")).toString(),
                Parser.parseCommand("mark 2").toString());

        assertEquals((new MarkCommand("unmark 2")).toString(),
                Parser.parseCommand("unmark 2").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_todoCommand_success() throws Exception {
        assertEquals((new ToDoCommand("todo a")).toString(),
                Parser.parseCommand("todo a").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_deadlineCommand_success() throws Exception {
        assertEquals((new DeadlineCommand("deadline a /by 02-02-2026 18:00")).toString(),
                Parser.parseCommand("deadline a /by 02-02-2026 18:00").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_eventCommand_success() throws Exception {
        assertEquals((new EventCommand("event a /from 02-02-2026 18:00 /to 02-02-2026 20:00")).toString(),
                Parser.parseCommand("event a /from 02-02-2026 18:00 /to 02-02-2026 20:00").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        assertEquals((new DeleteCommand("delete 1")).toString(),
                Parser.parseCommand("delete 1").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_helpCommand_success() throws Exception {
        assertEquals((new HelpCommand("help")).toString(),
                Parser.parseCommand("help").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_byeCommand_success() throws Exception {
        assertEquals((new ByeCommand("bye")).toString(),
                Parser.parseCommand("bye").toString());
    }

    /**
     * Tests parseCommand method.
     */
    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        try {
            assertEquals((new UnknownCommandException()).toString(),
                    Parser.parseCommand("blah,blah,blah").toString());
            fail();
        } catch (ErikaException e) {
            String message = """
                Hmm, sorry. I don't understand your command.
                
                Type 'help' if you forget my commands
                """;
            assertEquals(message, e.getMessage());
        }
    }
}
