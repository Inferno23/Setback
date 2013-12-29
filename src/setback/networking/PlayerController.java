/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking;

import java.util.Observable;
import java.util.Observer;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.common.Bet;
import setback.game.common.Card;
import setback.game.common.Hand;
import setback.networking.command.Command;
import setback.networking.command.CommandMessage;

/**
 * This class is the controller that a player interacts with.
 * Each player has a PlayerController, and each PlayerController
 * shares the same SetbackGameController.
 * @author Michael
 * @version Dec 27, 2013
 */
public class PlayerController implements SetbackObserver {

	private final SetbackGameController game;
	private PlayerNumber myNumber;
	private Hand myHand;

	/**
	 * Constructor for a PlayerController.  It takes in
	 * the shared SetbackGameController that is being
	 * played.
	 * @param game The shared SetbackGameController that
	 * all of the PlayerControllers will act upon.
	 */
	public PlayerController(SetbackGameController game) {
		this.game = game;
		initializeVariables();
	}

	/**
	 * This function takes in a CommandMessage,
	 * checks that the message is valid, and
	 * executes the command on the game stored
	 * in the PlayerController.
	 * @param command The command to validate
	 * and execute.
	 * @return A string indicating what occurred.
	 * This string might be removed.
	 */
	public String processInput(CommandMessage command) {
		String returnString;

		if (command == null) {
			returnString = "No command";
		}
		else {
			try {
				if (command.getCommand().equals(Command.REQUEST_PLAYER_ONE)) {
					if (game.requestPlayerNumber(PlayerNumber.PLAYER_ONE)) {
						myNumber = PlayerNumber.PLAYER_ONE;
						returnString = "Player one selected";
						if (game.checkPlayersReady()) {
							game.startGame();
							game.startRound();
						}
					}
					else {
						returnString = "Player one rejected";
					}
				}
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_TWO)) {
					if (game.requestPlayerNumber(PlayerNumber.PLAYER_TWO)) {
						myNumber = PlayerNumber.PLAYER_TWO;
						returnString = "Player two selected";
						if (game.checkPlayersReady()) {
							game.startGame();
							game.startRound();
						}
					}
					else {
						returnString = "Player two rejected";
					}
				}
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_THREE)) {
					if (game.requestPlayerNumber(PlayerNumber.PLAYER_THREE)) {
						myNumber = PlayerNumber.PLAYER_THREE;
						returnString = "Player three selected";
						if (game.checkPlayersReady()) {
							game.startGame();
							game.startRound();
						}
					}
					else {
						returnString = "Player three rejected";
					}
				}
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_FOUR)) {
					if (game.requestPlayerNumber(PlayerNumber.PLAYER_FOUR)) {
						myNumber = PlayerNumber.PLAYER_FOUR;
						returnString = "Player four selected";
						if (game.checkPlayersReady()) {
							game.startGame();
							game.startRound();
						}
					}
					else {
						returnString = "Player four rejected";
					}
				}
				else if (command.getCommand().equals(Command.PLACE_BET)) {
					String betString = command.getArguments()[0];
					game.placeBet(myNumber, Bet.valueOf(betString));
					returnString = myNumber.toString() + " BET " + betString;
					if (game.checkAllBetsPlaced()) {
						game.resolveBets();
					}
				}
				else if (command.getCommand().equals(Command.PLAY_CARD)) {
					String cardString = command.getArguments()[0];
					game.playCard(Card.fromString(cardString), myNumber);
					returnString = myNumber.toString() + " PLAYED " + cardString;
					//TODO: Check if all four cards have been played in the trick
				}

				else if (command.getCommand().equals(Command.EXIT)) {
					returnString = "EXIT";
				}
				else {
					returnString = "No command";
				}
			} catch (SetbackException e) {
				returnString = e.getMessage();
			}
		}

		return returnString;
	}

	/**
	 * @return the myNumber
	 */
	public PlayerNumber getMyNumber() {
		return myNumber;
	}

	/**
	 * This helper function initializes all of the variables
	 * needed in the GameController.  These variables need
	 * to be set to ensure that the correct behavior is
	 * provided by the processInput function.
	 */
	private void initializeVariables() {

	}

	/*
	 * (non-Javadoc)
	 * @see setback.networking.SetbackObserver#update(java.lang.String)
	 */
	public void update(String message) {
		if (message == null) {
			
		}
		else {
			if (message.equals("ROUND BEGIN")) {
				myHand = game.getPlayerHand(myNumber);
			}
		}
		
	}
}
