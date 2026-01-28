package erika.commands;

import erika.entities.Task;
import erika.entities.ToDos;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.ErikaIOException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a todo command.
 */
public class ToDoCommand extends Command {
    /**
     * Instantiates an instance of ToDoCommand.
     */
    public ToDoCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isToDo() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyDescriptionException,
            ErikaIOException {
        String taskName = super.formattedMessage.toLowerCase().replace("todo", "").strip();
        if  (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new ToDos(taskName);
        taskList.add(task);
        ui.showAddedTask(task);
    }

    @Override
    public String toString() {
        return "todo";
    }
}
