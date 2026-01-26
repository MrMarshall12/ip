package erika.utilities;

import erika.commands.ByeCommand;
import erika.commands.Command;
import erika.commands.DeadlineCommand;
import erika.commands.DeleteCommand;
import erika.commands.EventCommand;
import erika.commands.HelpCommand;
import erika.commands.ListCommand;
import erika.commands.MarkCommand;
import erika.commands.ToDoCommand;
import erika.exceptions.UnknownCommandException;

/** A class representing a parser that parses command from user */
public class Parser {
    /** Checks if the add command is a todo */
    private static boolean isToDo(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("todo");
    }

    /** Checks if the add command is a deadline */
    private static boolean isDeadline(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("deadline");
    }

    /** Checks if the add command is an event */
    private static boolean isEvent(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("event");
    }

    /** Checks if the user input is a list command */
    private static boolean isListCommand(String formattedMessage) {
        return formattedMessage.equalsIgnoreCase("list");
    }

    /** Checks if the user input is a marking command */
    private static boolean isMarkingCommand(String formattedMessage) {
        return formattedMessage.toLowerCase().startsWith("mark")
                || formattedMessage.toLowerCase().startsWith("unmark");
    }

    /** Checks if the user input is a delete task command */
    private static boolean isDeleteTaskCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("delete");
    }

    /** Checks if the user input is a help command */
    private static boolean isHelpCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.equalsIgnoreCase("help");
    }

    /** Checks if the user input is a bye command */
    private static boolean isByeCommand(String formattedMessage) {
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.equalsIgnoreCase("bye");
    }

    /** Transforms user's command into an instance of a subclass of Command */
    public static Command parseCommand(String command) throws UnknownCommandException {
        String formattedMessage = command.strip();
        if (isListCommand(formattedMessage)) {
            return new ListCommand(formattedMessage);
        } else if (isMarkingCommand(formattedMessage)) {
            return new MarkCommand(formattedMessage);
        } else if (isToDo(formattedMessage)) {
            return new ToDoCommand(formattedMessage);
        } else if (isDeadline(formattedMessage)) {
            return new DeadlineCommand(formattedMessage);
        } else if (isEvent(formattedMessage)) {
            return new EventCommand(formattedMessage);
        } else if (isDeleteTaskCommand(formattedMessage)) {
            return new DeleteCommand(formattedMessage);
        } else if (isHelpCommand(formattedMessage)) {
            return new HelpCommand(formattedMessage);
        } else if (isByeCommand(formattedMessage)) {
            return new ByeCommand(formattedMessage);
        } else {
            throw new UnknownCommandException();
        }
    }
}
