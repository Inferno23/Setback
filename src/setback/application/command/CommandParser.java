/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.command;

import setback.common.SetbackException;

/**
 * This class parses commands sent from the client to
 * the server threads.  It takes in Strings that are
 * based from CommandMessages, and converts them back
 * into CommandMessages.
 * @author Michael
 * @version Dec 26, 2013
 */
public class CommandParser {

	/**
	 * Function that takes in a String and converts
	 * it back into a CommandMessage.
	 * @param input The String to convert.
	 * @throws SetbackException if an unknown string is
	 * entered, or a Command has the wrong number of arguments. 
	 * @return A CommandMessage parsed from the input string.
	 */
	public CommandMessage parseString(String input) throws SetbackException {
		CommandMessage message;
		Command command;

		// Check for null
		if (input == null) {
			message = new CommandMessage(Command.NO_COMMAND);
		}
		// Parse the string
		else {
			String array[] = input.split(" ");
			try {
				command = Command.valueOf(array[0]);
				int argumentNumber = command.getNumberOfArguments();
				// Check if we should have zero arguments
				if (argumentNumber == 0) {
					if (array.length == 1) {
						message = new CommandMessage(command);
					}
					else {
						throw new SetbackException("Too many arguments!");
					}
				}
				else if (argumentNumber == 1) {
					if (array.length == 2) {
						String arguments[] = {array[1]};
						message = new CommandMessage(command, arguments);
					}
					else if (array.length < 2) {
						throw new SetbackException("Too few arguments!");
					}
					else {
						throw new SetbackException("Too many arguments!");
					}
				}
				// We should have three arguments
				else {
					if (array.length == 4) {
						String arguments[] = {array[1], array[2], array[3]};
						message = new CommandMessage(command, arguments);
					}
					else if (array.length < 4) {
						throw new SetbackException("Too few arguments!");
					}
					else {
						throw new SetbackException("Too many arguments!");
					}
				}
			}
			catch (IllegalArgumentException e) {
				throw new SetbackException("Unknown command!");
			}
		}

		return message;
	}
}
