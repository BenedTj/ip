package ben.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ben.core.command.ByeCommand;
import ben.core.command.Command;
import ben.core.command.DeadlineCommand;
import ben.core.command.DeleteCommand;
import ben.core.command.EventCommand;
import ben.core.command.FindCommand;
import ben.core.command.ListCommand;
import ben.core.command.MarkCommand;
import ben.core.command.TodoCommand;
import ben.core.command.UnmarkCommand;
import ben.exception.BenDatetimeParseException;
import ben.exception.BenEmptyParameterValueException;
import ben.exception.BenInvalidCommandException;
import ben.exception.BenInvalidParameterException;
import ben.exception.BenMissingParameterException;

/**
 * The class that handles the user input parsing functionality.
 */
public class Parser {
    private static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses fullCommand and returns the command to be
     * executed in the form of a Command object.
     *
     * @param fullCommand The full input from the user.
     * @return The command to be executed.
     * @throws BenInvalidParameterException If the parameter is not allowed.
     * @throws BenEmptyParameterValueException If no value was specified
     *                                         for a parameter.
     * @throws BenMissingParameterException If no required parameter was specified.
     * @throws BenInvalidCommandException If the command does not exist.
     * @throws BenDatetimeParseException If the datetime is not in the valid format.
     */
    public static Command parse(String fullCommand)
            throws BenInvalidParameterException,
            BenEmptyParameterValueException,
            BenMissingParameterException,
            BenInvalidCommandException,
            BenDatetimeParseException {
        // Split input into main command and parameters
        String[] commandParameters = fullCommand.split(" ");
        int commandParametersLength = commandParameters.length;

        // Get main command
        String command = commandParameters[0];
        try {
            if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("mark")) {
                try {
                    // Obtain indexNumber
                    int indexNumber = Integer.parseInt(commandParameters[1]);

                    return new MarkCommand(indexNumber);
                } catch (NumberFormatException e) {
                    throw new BenInvalidParameterException(commandParameters[1]);
                }
            } else if (command.equals("unmark")) {
                try {
                    // Obtain indexNumber
                    int indexNumber = Integer.parseInt(commandParameters[1]);

                    return new UnmarkCommand(indexNumber);
                } catch (NumberFormatException e) {
                    throw new BenInvalidParameterException(commandParameters[1]);
                }
            } else if (command.equals("todo")) {
                // Throw an exception if there is no description
                if (commandParametersLength <= 1) {
                    throw new BenEmptyParameterValueException("description", "todo");
                }

                // Obtain the description of the Todo task
                StringBuilder todoDescriptionBuilder = new StringBuilder(commandParameters[1]);
                for (int i = 2; i < commandParametersLength; i++) {
                    todoDescriptionBuilder.append(" ");
                    todoDescriptionBuilder.append(commandParameters[i]);
                }
                String todoDescription = todoDescriptionBuilder.toString();

                return new TodoCommand(todoDescription);
            } else if (command.equals("deadline")) {
                // Throw an exception if there is no description
                if (commandParametersLength <= 1) {
                    throw new BenEmptyParameterValueException("description", "deadline");
                }

                Integer byIndex = null;

                // Obtain the description of the Deadline task
                StringBuilder deadlineDescriptionBuilder = new StringBuilder(commandParameters[1]);
                for (int i = 2; i < commandParametersLength; i++) {
                    // Stop once "/by" is encountered
                    if (commandParameters[i].equals("/by")) {
                        byIndex = i;
                        break;
                    }

                    deadlineDescriptionBuilder.append(" ");
                    deadlineDescriptionBuilder.append(commandParameters[i]);
                }
                String deadlineDescription = deadlineDescriptionBuilder.toString();

                // Throw an exception if there is no "/by"
                if (byIndex == null) {
                    throw new BenMissingParameterException("/by");
                }

                // Throw an exception if there is no value for "/by"
                if (byIndex + 1 >= commandParametersLength) {
                    throw new BenEmptyParameterValueException("/by", "deadline");
                }

                // Obtain the deadline of the Deadline task
                StringBuilder deadlineByBuilder = new StringBuilder(commandParameters[byIndex + 1]);
                for (int i = byIndex + 2; i < commandParametersLength; i++) {
                    deadlineByBuilder.append(" ");
                    deadlineByBuilder.append(commandParameters[i]);
                }
                String deadlineBy = deadlineByBuilder.toString();

                // Convert deadlineBy to LocalDatetime
                LocalDateTime datetimeBy = LocalDateTime.parse(deadlineBy, DATETIME_FORMATTER);

                return new DeadlineCommand(deadlineDescription, datetimeBy);
            } else if (command.equals("event")) {
                // Throw an exception if there is no description
                if (commandParametersLength <= 1) {
                    throw new BenEmptyParameterValueException("description", "event");
                }

                Integer fromIndex = null;
                Integer toIndex = null;

                // Obtain the description of the Event task
                StringBuilder eventDescriptionBuilder = new StringBuilder(commandParameters[1]);
                for (int i = 2; i < commandParametersLength; i++) {
                    // Stop once "/from" is encountered
                    if (commandParameters[i].equals("/from")) {
                        fromIndex = i;
                        break;
                    }

                    eventDescriptionBuilder.append(" ");
                    eventDescriptionBuilder.append(commandParameters[i]);
                }
                String eventDescription = eventDescriptionBuilder.toString();

                // Throw an exception if there is no "/from"
                if (fromIndex == null) {
                    throw new BenMissingParameterException("/from");
                }

                // Throw an exception if there is no value for "/from"
                if (fromIndex + 1 >= commandParametersLength
                        || commandParameters[fromIndex + 1].equals("/to")) {
                    throw new BenEmptyParameterValueException("/from", "event");
                }

                // Obtain the to date/time of the Event task
                StringBuilder eventFromBuilder = new StringBuilder(commandParameters[fromIndex + 1]);
                for (int i = fromIndex + 2; i < commandParametersLength; i++) {
                    // Stop once "/end" is encountered
                    if (commandParameters[i].equals("/to")) {
                        toIndex = i;
                        break;
                    }

                    eventFromBuilder.append(" ");
                    eventFromBuilder.append(commandParameters[i]);
                }
                String eventFrom = eventFromBuilder.toString();

                // Convert eventFrom to LocalDateTime
                LocalDateTime dateTimeFrom = LocalDateTime.parse(eventFrom, DATETIME_FORMATTER);

                // Throw an exception if there is no "/to"
                if (toIndex == null) {
                    throw new BenMissingParameterException("/to");
                }

                // Throw an exception if there is no value for "/to"
                if (toIndex + 1 >= commandParametersLength) {
                    throw new BenEmptyParameterValueException("/to", "event");
                }

                // Obtain the end date/time of the Event task
                StringBuilder eventToBuilder = new StringBuilder(commandParameters[toIndex + 1]);
                for (int i = toIndex + 2; i < commandParametersLength; i++) {
                    eventToBuilder.append(" ");
                    eventToBuilder.append(commandParameters[i]);
                }
                String eventTo = eventToBuilder.toString();

                // Convert eventTo to LocalDateTime
                LocalDateTime dateTimeTo = LocalDateTime.parse(eventTo, DATETIME_FORMATTER);

                return new EventCommand(eventDescription, dateTimeFrom, dateTimeTo);
            } else if (command.equals("delete")) {
                try {
                    // Obtain indexNumber
                    int indexNumber = Integer.parseInt(commandParameters[1]);

                    return new DeleteCommand(indexNumber);
                } catch (NumberFormatException e) {
                    throw new BenInvalidParameterException(commandParameters[1]);
                }
            } else if (command.equals("find")) {
                // Throw an exception if there is no query parameter
                if (commandParametersLength <= 1) {
                    throw new BenEmptyParameterValueException("find query", "event");
                }

                // Obtain the find query parameter
                StringBuilder findQueryBuilder = new StringBuilder(commandParameters[1]);
                for (int i = 2; i < commandParametersLength; i++) {
                    findQueryBuilder.append(" ");
                    findQueryBuilder.append(commandParameters[i]);
                }
                String findQuery = findQueryBuilder.toString();

                return new FindCommand(findQuery);
            } else {
                throw new BenInvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new BenDatetimeParseException();
        }
    }
}
