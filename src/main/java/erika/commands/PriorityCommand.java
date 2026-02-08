package erika.commands;

import erika.entities.Task;
import erika.exceptions.ErikaIoException;
import erika.exceptions.InvalidPriorityCommandException;
import erika.exceptions.OutOfBoundsException;
import erika.exceptions.UnknownPriorityException;
import erika.utilities.TaskList;
import erika.utilities.Ui;
import erika.utilities.enums.Priority;

/**
 * A class representing a priority command.
 */
public class PriorityCommand extends Command {
    /**
     * Instantiates an instance of PriorityCommand.
     */
    public PriorityCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isPriority() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws InvalidPriorityCommandException,
            UnknownPriorityException, OutOfBoundsException, ErikaIoException {
        String[] splitMessage = super.formattedMessage.split(" ");
        if (splitMessage.length != 3) {
            throw new InvalidPriorityCommandException();
        }

        int index = -1;
        try {
            index = Integer.parseInt(splitMessage[1]);
        } catch (NumberFormatException e) {
            throw new InvalidPriorityCommandException();
        }

        if (!taskList.isWithinBounds(index - 1)) {
            throw new OutOfBoundsException();
        }

        Priority priority;
        switch (splitMessage[2].strip().toLowerCase()) {
        case "h":
            priority = Priority.HIGH;
            break;
        case "m":
            priority = Priority.MEDIUM;
            break;
        case "l":
            priority = Priority.LOW;
            break;
        case "n":
            priority = Priority.NONE;
            break;
        default:
            throw new UnknownPriorityException();
        }

        Task task = taskList.setPriority(index - 1, priority);
        return ui.showUpdatedPriorityTask(task);
    }

    @Override
    public String toString() {
        return "priority";
    }
}
