/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import setback.application.command.Command;
import setback.application.command.CommandMessageJson;
import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.common.*;
import setback.game.version.SetbackMultiplayerGame;

import java.util.List;

/**
 * This class is the controller that a player interacts with.
 * Each player has a PlayerController, and each PlayerController
 * shares the same SetbackMultiplayerGame.
 * @author Michael
 * @version Dec 27, 2013
 */
public class PlayerController {

	protected final SetbackMultiplayerGame game;
	protected PlayerNumber myNumber;
	protected Hand myHand;

	/**
	 * Constructor for a PlayerController.  It takes in
	 * the shared SetbackMultiplayerGame that is being
	 * played.
	 * @param game The shared SetbackMultiplayerGame that
	 * all of the PlayerControllers will act upon.
	 */
	public PlayerController(SetbackMultiplayerGame game) {
		this.game = game;
		myNumber = null;
	}

	/**
	 * This function takes in a CommandMessageJson,
	 * checks that the message is valid, and
	 * executes the commandMessageJson on the game stored
	 * in the PlayerController.
	 * @param commandMessageJson The commandMessageJson to validate
	 * and execute.
	 * @return A string indicating what occurred.
	 * This string might be removed.
	 */
	public String processInput(CommandMessageJson commandMessageJson) {
		String returnString;

		if (commandMessageJson == null) {
			returnString = "No command";
		}
		else {
			try {
				// Request players
				if (commandMessageJson.getCommand().equals(Command.REQUEST_PLAYER_ONE)) {
					if (myNumber == null) {
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
					else {
						returnString = "Player one rejected";
					}
				}
				else if (commandMessageJson.getCommand().equals(Command.REQUEST_PLAYER_TWO)) {
					if (myNumber == null) {
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
					else {
						returnString = "Player two rejected";
					}
				}
				else if (commandMessageJson.getCommand().equals(Command.REQUEST_PLAYER_THREE)) {
					if (myNumber == null) {
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
					else {
						returnString = "Player three rejected";
					}
				}
				else if (commandMessageJson.getCommand().equals(Command.REQUEST_PLAYER_FOUR)) {
					if (myNumber == null) {
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
					else {
						returnString = "Player four rejected";
					}
				}
				// Place bets
				else if (commandMessageJson.getCommand().equals(Command.PLACE_BET)) {
					final String betString = commandMessageJson.getParameters().getString(0);
					game.placeBet(myNumber, Bet.valueOf(betString));
					returnString = myNumber.toString() + " BET " + betString;
					if (game.checkAllBetsPlaced()) {
						game.resolveBets();
					}
				}
				// Select trump
				else if (commandMessageJson.getCommand().equals(Command.SELECT_TRUMP)) {
					final String suitString = commandMessageJson.getParameters().getString(0);
					game.selectTrump(myNumber, CardSuit.valueOf(suitString));
					returnString = myNumber.toString() + " SELECTED " + suitString;
				}
				// Discard cards
				else if (commandMessageJson.getCommand().equals(Command.DISCARD_CARDS)) {
					final Card cardOne = Card.fromString(commandMessageJson.getParameters().getString(0));
					final Card cardTwo = Card.fromString(commandMessageJson.getParameters().getString(1));
					final Card cardThree = Card.fromString(commandMessageJson.getParameters().getString(2));
					game.discardCards(myNumber, cardOne, cardTwo, cardThree);
					// Discard the cards from my hand
					final List<Card> myCards = myHand.getCards();
					myCards.remove(cardOne);
					myCards.remove(cardTwo);
					myCards.remove(cardThree);
					myHand.setCards(myCards);
					returnString = myNumber.toString() + " DISCARDED "
							+ cardOne.toString() + " " + cardTwo.toString()
							+ " " + cardThree.toString();
					if (game.checkAllDiscarded()) {
						game.startTrick();
					}
				}
				// Play cards
				else if (commandMessageJson.getCommand().equals(Command.PLAY_CARD)) {
					final Card card = Card.fromString(commandMessageJson.getParameters().getString(0));
					game.playCard(card, myNumber);
					// Discard the card from my hand
					final List<Card> myCards = myHand.getCards(); 
					myCards.remove(card);
					returnString = myNumber.toString() + " PLAYED " + card.toString();
					// Check if all four cards have been played
					if (game.checkFourCardsPlayed()) {
						final List<CardPlayerDescriptor> trickCards = game.getTrickCards();
						game.playTrick(trickCards.get(0), trickCards.get(1),
								trickCards.get(2), trickCards.get(3));
						// Check if there are more cards to play
						if (myHand.getCards().size() > 0) {
							game.startTrick();
						}
						else {
							final RoundResult result = game.playRound(game.getTrickResults());
							if (result.getStatus().equals(RoundResultStatus.OK)) {
								game.startRound();
							}
						}
					}
				}
				// Show hands
				else if (commandMessageJson.getCommand().equals(Command.SHOW_HAND)) {
					if (myHand == null) {
						returnString = "You do not have a hand yet!";
					}
					else {
						returnString = myHand.toString();
					}
				}
				// Get current player
				else if (commandMessageJson.getCommand().equals(Command.GET_CURRENT_PLAYER)) {
					if (myHand == null) {
						returnString = "There is no current player yet!";
					}
					else {
						returnString = game.getCurrentPlayer().toString();
					}
				}
				// Get winning bet
				else if (commandMessageJson.getCommand().equals(Command.GET_WINNING_BET)) {
					returnString = game.getWinningBet().toString();
				}
				// Get trump
				else if (commandMessageJson.getCommand().equals(Command.GET_TRUMP)) {
					returnString = game.getTrump().toString();
				}
				// Get Team one score
				else if (commandMessageJson.getCommand().equals(Command.GET_TEAM_ONE_SCORE)) {
					returnString = Integer.toString(game.getTeamOneScore());
				}
				else if (commandMessageJson.getCommand().equals(Command.GET_TEAM_TWO_SCORE)) {
					returnString = Integer.toString(game.getTeamTwoScore());
				}
				// Exit
				else if (commandMessageJson.getCommand().equals(Command.EXIT)) {
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
	 * This function is called to get the hand and sort
	 * it at the start of a round.  This is called by
	 * the SetbackServerThread when a round begins.
	 */
	public void startRound() {
		// Get the hand and immediately sort the cards.
		myHand = game.getPlayerHand(myNumber);
		myHand.sortCards();
	}

}
