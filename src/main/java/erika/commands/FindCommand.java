package erika.commands;

import java.util.function.Predicate;

import erika.entities.Task;
import erika.exceptions.InvalidFindCommandException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a find command.
 */
public class FindCommand extends Command {
    /**
     * Instantiates an instance of FindCommand.
     */
    public FindCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isFind() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws InvalidFindCommandException {
        String[] splitMessage = super.formattedMessage.split(" ");
        if (splitMessage.length < 2) {
            throw new InvalidFindCommandException();
        }
        String target = super.formattedMessage.replace("find", "").strip().toLowerCase();
        Predicate<Task> predicate = t -> t.getTaskName().toLowerCase().contains(target);

        return ui.showSelectedTasks(taskList, predicate);
    }

    @Override
    public String toString() {
        return "find";
    }
}
