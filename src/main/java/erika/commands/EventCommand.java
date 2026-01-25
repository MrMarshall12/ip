package erika.commands;

/** A class representing an event command */
public class EventCommand extends Command {
    @Override
    public boolean isEvent() {
        return true;
    }
}
