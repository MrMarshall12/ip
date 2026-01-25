package erika.commands;

/** A class representing a deadline command */
public class DeadlineCommand extends Command {
    @Override
    public boolean isDeadline() {
        return true;
    }
}
