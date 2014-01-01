/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking.command;

import java.util.Arrays;

/**
 * This class is the message that is sent between the server
 * and the client. The server will parse the string and use
 * it to run the game.
 * @author Michael
 * @version Dec 24, 2013
 */
public class CommandMessage {
	private Command command;
	private String[] arguments;
	
	/**
	 * Constructor for commands that have additional
	 * arguments.  These commands are used in the
	 * actual game, for things such as playing cards.
	 * @param command The command to be sent.
	 * @param arguments The additional arguments for
	 * the command.
	 */
	public CommandMessage(Command command, String[] arguments) {
		this.command = command;
		this.arguments = arguments;
	}

	/**
	 * Constructor for commands that do not have any
	 * extra arguments.  Typically these commands are
	 * for setting up the game at initialization.
	 * @param command The command to be sent.
	 */
	public CommandMessage(Command command) {
		this.command = command;
	}

	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * @return the arguments
	 */
	public String[] getArguments() {
		return arguments;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String returnString = command.toString();
		for (String argumentString : arguments) {
			returnString += " " + argumentString;
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CommandMessage other = (CommandMessage) obj;
		if (!Arrays.equals(arguments, other.arguments)) {
			return false;
		}
		if (command != other.command) {
			return false;
		}
		return true;
	}
}
