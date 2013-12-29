/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback(false), also known as Pitch.
 */
package setback.networking.command;

/**
 * This enumeration defines the commands that are passed
 * back and forth between the server and client.
 * @author Michael
 * @version Dec 27, 2013
 */
public enum Command {

	NO_COMMAND(0),
	EXIT(0),
	REQUEST_PLAYER_ONE(0),
	REQUEST_PLAYER_TWO(0),
	REQUEST_PLAYER_THREE(0),
	REQUEST_PLAYER_FOUR(0),
	PLACE_BET(1),
	SELECT_TRUMP(1),
	DISCARD_CARDS(3),
	PLAY_CARD(1);
	
	private final int numberOfArguments;

	/**
	 * Constructor for the enumerable item sets up the state.
	 * @param numberOfArguments The number of additional
	 * arguments the command requires
	 */
	private Command(int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
	}

	/**
	 * @return the numberOfArguments
	 */
	public int getNumberOfArguments() {
		return numberOfArguments;
	}

	
}
