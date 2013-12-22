/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import static org.junit.Assert.assertEquals;
import static setback.utilities.TestCard.aceOfSpades;
import static setback.utilities.TestCard.eightOfClubs;
import static setback.utilities.TestCard.fourOfClubs;
import static setback.utilities.TestCard.jackOfSpades;
import static setback.utilities.TestCard.sixOfClubs;
import static setback.utilities.TestCard.tenOfSpades;
import static setback.utilities.TestCard.threeOfSpades;
import static setback.utilities.TestCard.twoOfSpades;
import static setback.utilities.TestUtilityFunctions.playerOneWinsBet;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.RoundResult;
import setback.game.common.RoundResultStatus;
import setback.game.common.TrickResult;

/**
 * This class will test the determineRoundResults
 * function in the Round Class.  This is for coverage
 * and to ensure that all of the code is valid.
 * @author Michael Burns
 * @version Nov 7, 2013
 */
public class RoundResultTest {

	private SetbackGameController game;
	private SetbackGameFactory factory;

	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeGammaSetbackGame();
	}
	
	/**
	 * Helper function that gets a game ready to play cards.
	 * @throws SetbackException 
	 */
	private void prepareGame() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
	}
	
	@Test
	public void playerThreeWinsATrickWithTrumpThenPlayerOnePlaysAce() throws SetbackException {
		prepareGame();
		
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour, cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(resultOne);
		tricks.add(resultTwo);
		RoundResult roundResult = game.playRound(tricks);
		
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
}
