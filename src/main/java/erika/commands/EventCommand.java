package erika.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import erika.entities.Events;
import erika.entities.Task;
import erika.exceptions.EmptyDescriptionException;
import erika.exceptions.EmptyStartEndException;
import erika.exceptions.ErikaDateTimeParseException;
import erika.exceptions.ErikaIoException;
import erika.utilities.TaskList;
import erika.utilities.Ui;

/**
 * A class representing an event command.
 */
public class EventCommand extends Command {
    /**
     * Instantiates an instance of EventCommand.
     */
    public EventCommand(String formattedMessage) {
        super(formattedMessage);
    }

    @Override
    public boolean isEvent() {
        return true;
    }

    /**
     * Creates an event task.
     * This method checks for invalid date formats and end date that precedes start date.
     */
    private Task createEvent(String taskName, String startDate, String endDate)
            throws ErikaDateTimeParseException {
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(startDate, DATE_TIME_FORMATTER);
            end = LocalDateTime.parse(endDate, DATE_TIME_FORMATTER);
            if (start.isAfter(end)) {
                throw new ErikaDateTimeParseException();
            }
        } catch (DateTimeParseException e) {
            throw new ErikaDateTimeParseException();
        }

        return (new Events(taskName, start, end)).setPriority(super.priority);
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws EmptyDescriptionException,
            EmptyStartEndException, ErikaIoException, ErikaDateTimeParseException {
        String eventContent = super.formattedMessage.toLowerCase().replace("event", "").strip();
        String[] splitMessage = eventContent.split("/from ");
        String taskName = splitMessage.length > 0
                ? splitMessage[0].strip()
                : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        String[] splitAroundFrom = super.formattedMessage.split("/from ");
        String[] splitAroundTo = splitAroundFrom.length > 1
                ? splitAroundFrom[1].split("/to")
                : new String[0];
        if (splitAroundTo.length != 2) {
            throw new EmptyStartEndException();
        }

        String startDate = splitAroundTo[0].strip();
        String endDate = splitAroundTo[1].strip();

        Task task = createEvent(taskName, startDate, endDate);
        taskList.add(task);
        return ui.showAddedTask(task);
    }

    @Override
    public String toString() {
        return "event";
    }
}
