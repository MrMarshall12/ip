package erika.commands;

import erika.exceptions.ErikaException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/** An abstract class being the super-type of all commands */
public abstract class Command {
    protected String formattedMessage;

    public Command(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    /** Checks if this is a help command */
    public boolean isHelp() {
        return false;
    }

    /** Checks if this is a list command */
    public boolean isList() {
        return false;
    }

    /** Checks if this is a todo command */
    public boolean isToDo() {
        return false;
    }

    /** Checks if this is a deadline command */
    public boolean isDeadline() {
        return false;
    }

    /** Checks if this is an event command */
    public boolean isEvent() {
        return false;
    }

    /** Checks if this is a mark command */
    public boolean isMark() {
        return false;
    }

    /** Checks if this is a delete command */
    public boolean isDelete() {
        return false;
    }

    /** Checks if this is a bye command */
    public boolean isBye() {
        return false;
    }

    /** Checks if this is a find command */
    public boolean isFind() {
        return false;
    }

    /**
     * Executes the command based on its respective logic
     *
     * @param taskList list of tasks to be worked on
     * @param ui user interface class that handles interaction with user
     * @throws ErikaException if any of the execution logic triggers the subclass of ErikaException
     */
    public abstract void execute(TaskList taskList, Ui ui) throws ErikaException;

    @Override
    public String toString() {
        return "";
    }

}
