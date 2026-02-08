package erika.commands;

import java.util.function.Predicate;

import erika.entities.Task;
import erika.exceptions.ErikaIoException;
import erika.exceptions.InvalidMarkCommandException;
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
    public String execute(TaskList taskList, Ui ui) throws InvalidMarkCommandException,
            ErikaIoException {
        String target = super.formattedMessage.replace("find", "").strip();
        Predicate<Task> predicate = t -> t.getTaskName().toLowerCase().startsWith(target);

        return ui.showSelectedTasks(taskList, predicate);
    }

    @Override
    public String toString() {
        return "find";
    }
}
