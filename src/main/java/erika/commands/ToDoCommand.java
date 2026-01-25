package erika.commands;

/** A class representing a todo command */
public class ToDoCommand extends Command {
    @Override
    public boolean isToDo() {
        return true;
    }
}
