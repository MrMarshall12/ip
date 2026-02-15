package erika.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import erika.entities.Deadlines;
import erika.entities.Task;
import erika.exceptions.EmptyDeadlineException;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.ErikaDateTimeParseException;
import erika.exceptions.ErikaIoException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing a deadline command.
 */
public class DeadlineCommand extends Command {
    /**
     * Instantiates an instance of DeadlineCommand.
     */
    public DeadlineCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isDeadline() {
        return true;
    }

    /**
     * Creates a deadline task.
     * This method checks for invalid date formats and end date that precedes start date.
     */
    private Task createDeadline(String taskName, String deadlineTime)
            throws ErikaDateTimeParseException {
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(deadlineTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ErikaDateTimeParseException();
        }
        return (new Deadlines(taskName, deadline)).setPriority(super.priority);
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws EmptyDescriptionException,
            EmptyDeadlineException, ErikaIoException, ErikaDateTimeParseException {
        String deadlineContent = super.formattedMessage.toLowerCase().replace("deadline", "").strip();
        String[] splitMessage = deadlineContent.split("/by");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundBy = super.formattedMessage.split("/by");
        String deadlineTime = splitAroundBy.length == 2
                ? splitAroundBy[1].strip()
                : "";
        if (deadlineTime.isEmpty()) {
            throw new EmptyDeadlineException();
        }

        Task task = createDeadline(taskName, deadlineTime);
        taskList.add(task);
        return ui.showAddedTask(task);
    }

    @Override
    public String toString() {
        return "deadline";
    }
}
