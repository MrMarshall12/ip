package erika.commands;

/** An abstract class being the super-type of all commands */
public abstract class Command {

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

    /** Checks if this is an Unmark command */
    public boolean isUnmark() {
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

}
