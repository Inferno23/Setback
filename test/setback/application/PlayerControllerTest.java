/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import setback.application.PlayerController;
import setback.application.command.Command;
import setback.application.command.CommandMessage;
import setback.common.PlayerNumber;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;

/**
 * This class will test the PlayerController class.
 * @author Michael
 * @version Dec 26, 2013
 */
public class PlayerControllerTest {

	private SetbackGameFactory factory;
	private SetbackGameController game;
	private PlayerController controllerOne;
	private PlayerController controllerTwo;
	private PlayerController controllerThree;
	private PlayerController controllerFour;

	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeDeltaSetbackGame(0);
		controllerOne = new PlayerController(game);
		controllerTwo = new PlayerController(game);
		controllerThree = new PlayerController(game);
		controllerFour = new PlayerController(game);
	}

	@Test
	public void nullInput() {
		final String result = controllerOne.processInput(null);
		final String expected = "No command";
		assertEquals(expected, result);
	}

	@Test
	public void noCommandInput() {
		final String result = controllerOne.processInput(new CommandMessage(Command.NO_COMMAND));
		final String expected = "No command";
		assertEquals(expected, result);
	}

	@Test
	public void exitInput() {
		final String result = controllerOne.processInput(new CommandMessage(Command.EXIT));
		final String expected = "EXIT";
		assertEquals(expected, result);
	}

	@Test
	public void requestPlayerOneSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String expected = "Player one selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_ONE, controllerOne.getMyNumber());
	}

	@Test
	public void requestPlayerOneRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String expected = "Player one rejected";
		assertEquals(expected, result);
	}

	@Test
	public void requestPlayerTwoSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String expected = "Player two selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_TWO, controllerOne.getMyNumber());
	}

	@Test
	public void requestPlayerTwoRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String expected = "Player two rejected";
		assertEquals(expected, result);
	}

	@Test
	public void requestPlayerThreeSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String expected = "Player three selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_THREE, controllerOne.getMyNumber());
	}

	@Test
	public void requestPlayerThreeRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String expected = "Player three rejected";
		assertEquals(expected, result);
	}

	@Test
	public void requestPlayerFourSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String expected = "Player four selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_FOUR, controllerOne.getMyNumber());
	}

	@Test
	public void requestPlayerFourRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String expected = "Player four rejected";
		assertEquals(expected, result);
	}

	@Test
	public void attemptBetWithoutFourPlayers() {
		final String arguments[] = {"PASS"};
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String result = controllerOne.processInput(new CommandMessage(Command.PLACE_BET, arguments));
		final String expected = "You must start the game!";
		assertEquals(expected, result);
	}

	@Test
	public void showHandProperly() {
		initializeFourControllers();
		final String result = controllerOne.processInput(new CommandMessage(Command.SHOW_HAND));
		final String expected = "PLAYER_ONE'S HAND:\nThree-of-Spades\nSix-of-Spades\nAce-of-Spades"
				+ "\nFour-of-Hearts\nAce-of-Hearts\nSix-of-Clubs\nSeven-of-Clubs\nSeven-of-Diamonds"
				+ "\nTen-of-Diamonds\nJack-of-Diamonds\nQueen-of-Diamonds\nAce-of-Diamonds\n";
		assertEquals(expected, result);
	}

	@Test
	public void showHandWithoutFourPlayers() {
		final String result = controllerOne.processInput(new CommandMessage(Command.SHOW_HAND));
		final String expected = "You do not have a hand yet!";
		assertEquals(expected, result);
	}

	@Test
	public void getCurrentPlayerWithoutFourPlayers() {
		final String result = controllerOne.processInput(new CommandMessage(Command.GET_CURRENT_PLAYER));
		final String expected = "There is no current player yet!";
		assertEquals(expected, result);
	}
	
	@Test
	public void getCurrentPlayerProperly() {
		initializeFourControllers();
		final String result = controllerOne.processInput(new CommandMessage(Command.GET_CURRENT_PLAYER));
		final String expected = "PLAYER_TWO";
		assertEquals(expected, result);
	}
	
	@Test
	public void playerTwoValidBet() {
		initializeFourControllers();
		final String arguments[] = {"PASS"};
		final String result = controllerTwo.processInput(new CommandMessage(Command.PLACE_BET, arguments));
		final String expected = "PLAYER_TWO BET PASS";
		assertEquals(expected, result);
	}

	@Test
	public void playerOneEarlyBet() {
		initializeFourControllers();
		final String arguments[] = {"PASS"};
		final String result = controllerOne.processInput(new CommandMessage(Command.PLACE_BET, arguments));
		final String expected = "It is not your turn to bet!";
		assertEquals(expected, result);
	}

	@Test
	public void playerOneSelectsSpades() {
		playerOneWinsBet();
		final String spades[] = {"SPADES"};
		final String result = controllerOne.processInput(new CommandMessage(Command.SELECT_TRUMP, spades));
		final String expected = "PLAYER_ONE SELECTED SPADES";
		assertEquals(expected, result);
	}

	@Test
	public void playerTwoTriesToSelectTrump() {
		playerOneWinsBet();
		final String spades[] = {"SPADES"};
		final String result = controllerTwo.processInput(new CommandMessage(Command.SELECT_TRUMP, spades));
		final String expected = "You did not win the bet, so you do not select trump!";
		assertEquals(expected, result);
	}

	@Test
	public void playerOneDiscardsProperly() {
		prepareForDiscarding();
		final String discardOne[] = {"Six-of-Clubs", "Seven-of-Clubs", "Four-of-Hearts"};
		final String result = controllerOne.processInput(new CommandMessage(Command.DISCARD_CARDS, discardOne));
		final String expected = "PLAYER_ONE DISCARDED Six-of-Clubs Seven-of-Clubs Four-of-Hearts";
		assertEquals(expected, result);
	}

	@Test
	public void playerOnePlaysValidCard() {
		beginFirstTrick();
		final String card[] = {"Ace-of-Spades"};
		final String result = controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, card));
		final String expected = "PLAYER_ONE PLAYED Ace-of-Spades";
		assertEquals(expected, result);
	}

	@Test
	public void playerOnePlaysInvalidCard() {
		beginFirstTrick();
		final String card[] = {"Queen-of-Spades"};
		final String result = controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, card));
		final String expected = "You don't have that card!";
		assertEquals(expected, result);
	}

	@Test
	public void playerTwoPlaysCardEarly() {
		beginFirstTrick();
		final String card[] = {"Queen-of-Spades"};
		final String result = controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, card));
		final String expected = "It is not your turn! It is PLAYER_ONE's turn!";
		assertEquals(expected, result);
	}

	@Test
	public void fullTrickPlayed() {
		beginFirstTrick();
		final String cardOne[] = {"Ace-of-Spades"};
		final String cardTwo[] = {"Eight-of-Spades"};
		final String cardThree[] = {"Jack-of-Spades"};
		final String cardFour[] = {"Ten-of-Spades"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOne));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwo));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThree));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFour));
	}

	@Test
	public void fullRoundPlayed() {
		playFullFirstRound();
		assertTrue(game.getPlayerHand(PlayerNumber.PLAYER_ONE).getCards().contains(new Card(CardType.ACE, CardSuit.CLUBS)));
	}



	/**
	 * Helper function that initializes all four
	 * PlayerControllers for the four players.
	 */
	private void initializeFourControllers() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		controllerTwo.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		controllerThree.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		controllerFour.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		controllerOne.handleUpdate("ROUND BEGIN");
		controllerTwo.handleUpdate("ROUND BEGIN");
		controllerThree.handleUpdate("ROUND BEGIN");
		controllerFour.handleUpdate("ROUND BEGIN");
	}

	/**
	 * Helper function that calls the initializeFourControllers
	 * method, and then places bets for the four players, resulting
	 * in PLAYER_ONE winning with a bet of two.
	 */
	private void playerOneWinsBet() {
		initializeFourControllers();
		final String pass[] = {"PASS"};
		final String two[] = {"TWO"};
		controllerTwo.processInput(new CommandMessage(Command.PLACE_BET, pass));
		controllerThree.processInput(new CommandMessage(Command.PLACE_BET, pass));
		controllerFour.processInput(new CommandMessage(Command.PLACE_BET, pass));
		controllerOne.processInput(new CommandMessage(Command.PLACE_BET, two));
	}

	/**
	 * Helper function that calls playerOneWinsBets and
	 * then selects SPADES as trump, leaving the controllers
	 * ready to discard cards.
	 */
	private void prepareForDiscarding() {
		playerOneWinsBet();
		final String spades[] = {"SPADES"};
		controllerOne.processInput(new CommandMessage(Command.SELECT_TRUMP, spades));
	}

	/**
	 * Helper function that calls prepareForDiscarding and
	 * discards cards from each controller, beginning the trick.
	 */
	private void beginFirstTrick() {
		prepareForDiscarding();
		final String discardOne[] = {"Six-of-Clubs", "Seven-of-Clubs", "Four-of-Hearts"};
		final String discardTwo[] = {"Two-of-Hearts", "Three-of-Hearts", "Six-of-Hearts"};
		final String discardThree[] = {"Eight-of-Hearts", "Ten-of-Hearts", "Jack-of-Hearts"};
		final String discardFour[] = {"Five-of-Hearts", "Queen-of-Hearts", "King-of-Hearts"};
		controllerOne.processInput(new CommandMessage(Command.DISCARD_CARDS, discardOne));
		controllerTwo.processInput(new CommandMessage(Command.DISCARD_CARDS, discardTwo));
		controllerThree.processInput(new CommandMessage(Command.DISCARD_CARDS, discardThree));
		controllerFour.processInput(new CommandMessage(Command.DISCARD_CARDS, discardFour));
	}

	/**
	 * Helper function that plays a full first round of Setback.
	 */
	private void playFullFirstRound() {
		beginFirstTrick();
		// Trick one
		final String cardOneOne[] = {"Ace-of-Spades"};
		final String cardTwoOne[] = {"Eight-of-Spades"};
		final String cardThreeOne[] = {"Jack-of-Spades"};
		final String cardFourOne[] = {"Ten-of-Spades"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneOne));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoOne));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeOne));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourOne));
		// Trick two
		final String cardOneTwo[] = {"Three-of-Spades"};
		final String cardTwoTwo[] = {"Queen-of-Spades"};
		final String cardThreeTwo[] = {"King-of-Spades"};
		final String cardFourTwo[] = {"Two-of-Spades"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneTwo));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoTwo));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeTwo));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourTwo));
		// Trick three
		final String cardThreeThree[] = {"Ace-of-Clubs"};
		final String cardFourThree[] = {"Eight-of-Clubs"};
		final String cardOneThree[] = {"Ten-of-Diamonds"};
		final String cardTwoThree[] = {"Five-of-Clubs"};
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeThree));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourThree));
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneThree));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoThree));
		// Trick four
		final String cardThreeFour[] = {"Two-of-Clubs"};
		final String cardFourFour[] = {"Jack-of-Clubs"};
		final String cardOneFour[] = {"Six-of-Spades"};
		final String cardTwoFour[] = {"King-of-Clubs"};
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeFour));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourFour));
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneFour));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoFour));
		// Trick five
		final String cardOneFive[] = {"Ace-of-Hearts"};
		final String cardTwoFive[] = {"Seven-of-Hearts"};
		final String cardThreeFive[] = {"Ten-of-Clubs"};
		final String cardFourFive[] = {"Two-of-Diamonds"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneFive));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoFive));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeFive));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourFive));
		// Trick six
		final String cardOneSix[] = {"Ace-of-Diamonds"};
		final String cardTwoSix[] = {"Four-of-Diamonds"};
		final String cardThreeSix[] = {"Six-of-Diamonds"};
		final String cardFourSix[] = {"Three-of-Diamonds"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneSix));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoSix));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeSix));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourSix));
		// Trick seven
		final String cardOneSeven[] = {"Queen-of-Diamonds"};
		final String cardTwoSeven[] = {"Five-of-Diamonds"};
		final String cardThreeSeven[] = {"Three-of-Clubs"};
		final String cardFourSeven[] = {"Eight-of-Diamonds"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneSeven));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoSeven));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeSeven));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourSeven));
		// Trick Eight
		final String cardOneEight[] = {"Jack-of-Diamonds"};
		final String cardTwoEight[] = {"Nine-of-Hearts"};
		final String cardThreeEight[] = {"Four-of-Clubs"};
		final String cardFourEight[] = {"Nine-of-Diamonds"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneEight));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoEight));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeEight));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourEight));
		// Trick Nine
		final String cardOneNine[] = {"Seven-of-Diamonds"};
		final String cardTwoNine[] = {"Nine-of-Spades"};
		final String cardThreeNine[] = {"Seven-of-Spades"};
		final String cardFourNine[] = {"Nine-of-Clubs"};
		controllerOne.processInput(new CommandMessage(Command.PLAY_CARD, cardOneNine));
		controllerTwo.processInput(new CommandMessage(Command.PLAY_CARD, cardTwoNine));
		controllerThree.processInput(new CommandMessage(Command.PLAY_CARD, cardThreeNine));
		controllerFour.processInput(new CommandMessage(Command.PLAY_CARD, cardFourNine));
	}
}
