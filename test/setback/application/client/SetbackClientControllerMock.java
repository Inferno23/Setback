/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import setback.application.socket.MockIOPair;
import setback.common.PlayerNumber;

/**
 * This class subclasses the real SetbackClientController, but has getters
 * in order to do testing.  Additionally, it fakes out the userInput method
 * so it can provide responses without needed a socket connection.
 * @author Michael Burns
 * @version May 25, 2014
 */
public class SetbackClientControllerMock extends SetbackClientController {

	/**
	 * A constructor for the subclass which does not take in a socket,
	 * because we do not want a real socket connection.
	 */
	public SetbackClientControllerMock() {
		// We do not want an actual socket for the mock
		super(new MockIOPair(null));
	}

	/**
	 * A constructor for the subclass which does not take in a socket,
	 * because we do not want a real socket connection.  It does take
	 * in PlayerNumbers for each player to test the player selection buttons.
	 * @param myNumber The PlayerNumber for the player.
	 */
	public SetbackClientControllerMock(PlayerNumber myNumber) {
		// We do not want an actual socket for the mock
		super(new MockIOPair(null));
		// Set the given player numbers, which may be null.
		this.myNumber = myNumber;
	}

	@Override
	public String userInput(String input) {
		String returnString = null;

		// PlayerSelectView
		if (input.equals("REQUEST_PLAYER_ONE")) {
			if (myNumber == null) {
				returnString = "Player one selected";
			}
			else {
				returnString = "Player one rejected";
			}
		}
		else if (input.equals("REQUEST_PLAYER_TWO")) {
			if (myNumber == null) {
				returnString = "Player two selected";
			}
			else {
				returnString = "Player two rejected";
			}
		}
		else if (input.equals("REQUEST_PLAYER_THREE")) {
			if (myNumber == null) {
				returnString = "Player three selected";
			}
			else {
				returnString = "Player three rejected";
			}
		}
		else if (input.equals("REQUEST_PLAYER_FOUR")) {
			if (myNumber == null) {
				returnString = "Player four selected";
			}
			else {
				returnString = "Player four rejected";
			}
		}
		
		// Next view

		return returnString;
	}

}
