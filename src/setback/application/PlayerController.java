/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application;

import java.util.List;

import setback.application.command.Command;
import setback.application.command.CommandMessage;
import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.common.Bet;
import setback.game.common.Card;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.Hand;
import setback.game.common.RoundResult;
import setback.game.common.RoundResultStatus;

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
		game.addObserver(this);
		myNumber = null;
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
				// Request players
				if (command.getCommand().equals(Command.REQUEST_PLAYER_ONE)) {
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
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_TWO)) {
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
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_THREE)) {
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
				else if (command.getCommand().equals(Command.REQUEST_PLAYER_FOUR)) {
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
				else if (command.getCommand().equals(Command.PLACE_BET)) {
					String betString = command.getArguments()[0];
					game.placeBet(myNumber, Bet.valueOf(betString));
					returnString = myNumber.toString() + " BET " + betString;
					if (game.checkAllBetsPlaced()) {
						game.resolveBets();
					}
				}
				// Select trump
				else if (command.getCommand().equals(Command.SELECT_TRUMP)) {
					String suitString = command.getArguments()[0];
					game.selectTrump(myNumber, CardSuit.valueOf(suitString));
					returnString = myNumber.toString() + " SELECTED " + suitString;
				}
				// Discard cards
				else if (command.getCommand().equals(Command.DISCARD_CARDS)) {
					Card cardOne = Card.fromString(command.getArguments()[0]);
					Card cardTwo = Card.fromString(command.getArguments()[1]);
					Card cardThree = Card.fromString(command.getArguments()[2]);
					game.discardCards(myNumber, cardOne, cardTwo, cardThree);
					// Discard the cards from my hand
					List<Card> myCards = myHand.getCards(); 
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
				else if (command.getCommand().equals(Command.PLAY_CARD)) {
					Card card = Card.fromString(command.getArguments()[0]);
					game.playCard(card, myNumber);
					// Discard the card from my hand
					List<Card> myCards = myHand.getCards(); 
					myCards.remove(card);
					returnString = myNumber.toString() + " PLAYED " + card.toString();
					// Check if all four cards have been played
					if (game.checkFourCardsPlayed()) {
						List<CardPlayerDescriptor> trickCards = game.getTrickCards();
						game.playTrick(trickCards.get(0), trickCards.get(1),
								trickCards.get(2), trickCards.get(3));
						// Check if there are more cards to play
						if (myHand.getCards().size() > 0) {
							game.startTrick();
						}
						else {
							RoundResult result = game.playRound(game.getTrickResults());
							if (result.getStatus().equals(RoundResultStatus.OK)) {
								game.startRound();
							}
						}
					}
				}
				// Show hands
				else if (command.getCommand().equals(Command.SHOW_HAND)) {
					if (myHand == null) {
						returnString = "You do not have a hand yet!";
					}
					else {
						returnString = myHand.toString();
					}
				}
				// Exit
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
