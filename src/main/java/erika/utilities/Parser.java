package erika.utilities;

import java.util.Objects;

import erika.commands.ByeCommand;
import erika.commands.Command;
import erika.commands.DeadlineCommand;
import erika.commands.DeleteCommand;
import erika.commands.EventCommand;
import erika.commands.FindCommand;
import erika.commands.HelpCommand;
import erika.commands.ListCommand;
import erika.commands.MarkCommand;
import erika.commands.PriorityCommand;
import erika.commands.ToDoCommand;
import erika.exceptions.UnknownCommandException;
import erika.exceptions.UnknownPriorityException;
import erika.utilities.enums.Priority;

/**
 * A class representing a parser that parses command from the user.
 */
public class Parser {
    /**
     * Checks if the add command is a todo.
     */
    private static boolean isToDo(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.toLowerCase().startsWith("todo");
    }

    /**
     * Checks if the add command is a deadline.
     */
    private static boolean isDeadline(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.toLowerCase().startsWith("deadline");
    }

    /**
     * Checks if the add command is an event.
     */
    private static boolean isEvent(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.toLowerCase().startsWith("event");
    }

    /**
     * Checks if the user input is a list command.
     */
    private static boolean isListCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.equalsIgnoreCase("list");
    }

    /**
     * Checks if the user input is a marking command.
     */
    private static boolean isMarkingCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.toLowerCase().startsWith("mark")
                || formattedMessage.toLowerCase().startsWith("unmark");
    }

    /**
     * Checks if the user input is a delete task command.
     */
    private static boolean isDeleteTaskCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("delete");
    }

    /**
     * Checks if the user input is a help command.
     */
    private static boolean isHelpCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.equalsIgnoreCase("help");
    }

    /**
     * Checks if the user input is a bye command.
     */
    private static boolean isByeCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.equalsIgnoreCase("bye");
    }

    /**
     * Checks if the user input is a find command.
     */
    private static boolean isFindCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        String lowerCase = formattedMessage.toLowerCase();
        return lowerCase.startsWith("find");
    }

    /**
     * Checks if the user input is a priority command.
     */
    private static boolean isPriorityCommand(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        return formattedMessage.toLowerCase().startsWith("prio");
    }

    /**
     * Removes the priority specifier from the formatted message.
     *
     * @return formatted message without the priority specifier.
     */
    private static String removePrioritySpecifier(String formattedMessage) {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        if (formattedMessage.toLowerCase().contains("/p")) {
            return formattedMessage.split("/p")[0].strip();
        } else {
            return formattedMessage;
        }
    }


    /**
     * Checks if the priority is specified.
     *
     * @return the priority of the task.
     */
    private static Priority checkPriority(String formattedMessage) throws
            UnknownPriorityException {
        assert Objects.nonNull(formattedMessage) : "formattedMessage cannot be null";
        String[] partitioned = formattedMessage.toLowerCase().split("/p");
        if (partitioned.length != 2 && !formattedMessage.contains("/p")) { // Guards against unspecified priority
            return Priority.NONE;
        } else if (partitioned.length != 2 && formattedMessage.contains("/p")) {
            throw new UnknownPriorityException();
        }

        String lowerCase = partitioned[1].strip();
        if (lowerCase.equals("h")) {
            return Priority.HIGH;
        } else if (lowerCase.equals("m")) {
            return Priority.MEDIUM;
        } else if (lowerCase.equals("l")) {
            return Priority.LOW;
        } else if (lowerCase.equals("n")) {
            return Priority.NONE;
        } else {
            throw new UnknownPriorityException();
        }
    }

    /**
     * Transforms a command from the user into an instance of Command's subclasses.
     *
     * @return Object of Command's subclasses.
     * @throws UnknownCommandException if the command is unknown.
     */
    public static Command parseCommand(String command) throws UnknownCommandException,
            UnknownPriorityException {
        String formattedMessage = command.strip();
        if (isListCommand(formattedMessage)) {
            return new ListCommand(formattedMessage);
        } else if (isMarkingCommand(formattedMessage)) {
            return new MarkCommand(formattedMessage);
        } else if (isToDo(formattedMessage)) {
            Priority priority = checkPriority(formattedMessage);
            formattedMessage = removePrioritySpecifier(formattedMessage);
            return new ToDoCommand(formattedMessage).setPriority(priority);
        } else if (isDeadline(formattedMessage)) {
            Priority priority = checkPriority(formattedMessage);
            formattedMessage = removePrioritySpecifier(formattedMessage);
            return new DeadlineCommand(formattedMessage).setPriority(priority);
        } else if (isEvent(formattedMessage)) {
            Priority priority = checkPriority(formattedMessage);
            formattedMessage = removePrioritySpecifier(formattedMessage);
            return new EventCommand(formattedMessage).setPriority(priority);
        } else if (isDeleteTaskCommand(formattedMessage)) {
            return new DeleteCommand(formattedMessage);
        } else if (isHelpCommand(formattedMessage)) {
            return new HelpCommand(formattedMessage);
        } else if (isByeCommand(formattedMessage)) {
            return new ByeCommand(formattedMessage);
        } else if (isFindCommand(formattedMessage)) {
            return new FindCommand(formattedMessage);
        } else if (isPriorityCommand(formattedMessage)) {
            return new PriorityCommand(formattedMessage);
        } else {
            throw new UnknownCommandException();
        }
    }
}
