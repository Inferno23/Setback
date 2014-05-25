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
	 * @param left The PlayerNumber for the player on the left.
	 * @param center The PlayerNumber for the player in the center.
	 * @param right The PlayerNumber for the player on the right.
	 */
	public SetbackClientControllerMock(PlayerNumber myNumber, PlayerNumber left,
			PlayerNumber center, PlayerNumber right) {
		// We do not want an actual socket for the mock
		super(new MockIOPair(null));
		// Set the given player numbers, which may be null.
		this.myNumber = myNumber;
		this.left = left;
		this.center = center;
		this.right = right;
	}

	@Override
	public String userInput(String input) {
		String returnString = null;

		//TODO: Add the appropriate

		return returnString;
	}

}
