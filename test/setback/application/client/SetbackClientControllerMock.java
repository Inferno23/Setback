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

	public String noCommandString;
	public boolean bettingResolved;

	/**
	 * A constructor for the subclass which does not take in a socket,
	 * because we do not want a real socket connection.
	 */
	public SetbackClientControllerMock() {
		// We do not want an actual socket for the mock
		super(new MockIOPair(null));
		noCommandString = "No command";
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
		setPlayerNumbers(myNumber);
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

		// PleaseWaitView and PlaceBetsView
		else if (input.equals("SHOW_HAND")) {
			if (myNumber == null) {
				returnString = "You do not have a hand yet!";
			}
			else if (myNumber == PlayerNumber.PLAYER_ONE) {
				returnString = "PLAYER ONE'S HAND:\tThree-of-Spades\t"
						+ "Six-of-Spades\tAce-of-Spades\tFour-of-Hearts\t"
						+ "Ace-of-Hearts\tSix-of-Clubs\tSeven-of-Clubs\t"
						+ "Seven-of-Diamonds\tTen-of-Diamonds\tJack-of-Diamonds\t"
						+ "Queen-of-Diamonds\tAce-of-Diamonds";
			}
			else if (myNumber == PlayerNumber.PLAYER_TWO) {
				returnString = "PLAYER TWO'S HAND:\tEight-of-Spades\t"
						+ "Nine-of-Spades\tQueen-of-Spades\tFive-of-Clubs\t"
						+ "King-of-Clubs\tTwo-of-Hearts\tThree-of-Hearts\t"
						+ "Six-of-Hearts\tSeven-of-Hearts\tNine-of-Hearts\t"
						+ "Four-of-Diamonds\tFive-of-Diamonds";
			}
			else if (myNumber == PlayerNumber.PLAYER_THREE) {
				returnString = "PLAYER THREE'S HAND:\tSeven-of-Spades\t"
						+ "Jack-of-Spades\tKing-of-Spades\tTwo-of-Clubs\t"
						+ "Three-of-Clubs\tFour-of-Clubs\tTen-of-Clubs\t"
						+ "Ace-of-Clubs\tEight-of-Hearts\tTen-of-Hearts\t"
						+ "Jack-of-Hearts\tSix-of-Diamonds";
			}
			else if (myNumber == PlayerNumber.PLAYER_FOUR) {
				returnString = "PLAYER FOUR'S HAND:\tTwo-of-Spades\t"
						+ "Ten-of-Spades\tEight-of-Clubs\tNine-of-Clubs\t"
						+ "Jack-of-Clubs\tFive-of-Hearts\tQueen-of-Hearts\t"
						+ "King-of-Hearts\tTwo-of-Diamonds\tThree-of-Diamonds\t"
						+ "Eight-of-Diamonds\tNine-of-Diamonds";
			}
		}
		else if (input.startsWith("PLACE_BET")) {
			if (input.equals("PLACE_BET PASS")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET PASS";
			}
			else if (input.equals("PLACE_BET TWO")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET TWO";
			}
			else if (input.equals("PLACE_BET THREE")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET THREE";
			}
			else if (input.equals("PLACE_BET FOUR")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET FOUR";
			}
			else if (input.equals("PLACE_BET FIVE")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET FIVE";
			}
			else if (input.equals("PLACE_BET TAKE")) {
				returnString = myNumber.toString().toUpperCase()
						+ " BET TAKE";
			}

			// Concatenate BETTING RESOLVED
			if (bettingResolved) {
				returnString += " BETTING RESOLVED";
			}
		}

		// SetbackClientView
		else if (input.equals("GET_CURRENT_PLAYER")) {
			returnString = "PLAYER_TWO";
		}

		else if (input.equals("NO_COMMAND")) {
			returnString = noCommandString;
		}

		return returnString;
	}

}
