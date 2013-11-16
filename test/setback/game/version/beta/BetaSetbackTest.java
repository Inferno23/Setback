/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.beta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static setback.utilities.TestCard.*;

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
import setback.game.common.TrickResult;

/**
 * This class will test the Beta version of Setback.
 * @author Michael Burns
 * @version Oct 22, 2013
 */
public class BetaSetbackTest {

	private SetbackGameController game;
	private SetbackGameFactory factory;

	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeBetaSetbackGame();
	}

	@Test(expected=SetbackException.class)
	public void playerTwoTriesToLeadSecondTrick() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);

		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);

		game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
	}

	@Test
	public void twoFullTricks() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();

		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		assertEquals(PlayerNumber.PLAYER_ONE, resultOne.getWinner());
		assertEquals(15, resultOne.getGamePoints());
		assertTrue(resultOne.isJackOfTrump());
		assertEquals(twoOfSpades, resultOne.getLowCandidate());
		assertEquals(aceOfSpades, resultOne.getHighCandidate());

		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		assertEquals(PlayerNumber.PLAYER_ONE, resultTwo.getWinner());
		assertEquals(4, resultTwo.getGamePoints());
		assertFalse(resultTwo.isJackOfTrump());
		assertEquals(threeOfSpades, resultTwo.getLowCandidate());
		assertEquals(threeOfSpades, resultTwo.getHighCandidate());
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToStartTrickBeforeGame() throws SetbackException {
		game.startTrick();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToStartTrickBeforeRound() throws SetbackException {
		game.startGame();
		game.startTrick();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToRestartTrick() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		game.startTrick();
	}
	
	@Test(expected=SetbackException.class)
	public void playerTwoBreaksSuit() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
	}
	
	@Test
	public void playerPlaysTrumpWhenTheyDoHaveSuit() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		assertEquals(PlayerNumber.PLAYER_TWO, resultTwo.getWinner());
		assertEquals(4, resultTwo.getGamePoints());
		assertFalse(resultTwo.isJackOfTrump());
		assertNull(resultTwo.getLowCandidate());
		assertNull(resultTwo.getHighCandidate());
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour, cpdRoundThreePlayerOne);
		assertEquals(PlayerNumber.PLAYER_ONE, resultThree.getWinner());
		assertEquals(0, resultThree.getGamePoints());
		assertFalse(resultThree.isJackOfTrump());
		assertEquals(threeOfSpades, resultThree.getLowCandidate());
		assertEquals(threeOfSpades, resultThree.getHighCandidate());
	}
	
	@Test
	public void playerPlaysTrumpWhenTheyDoNotHaveSuit() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		assertEquals(PlayerNumber.PLAYER_TWO, resultTwo.getWinner());
		assertEquals(4, resultTwo.getGamePoints());
		assertFalse(resultTwo.isJackOfTrump());
		assertNull(resultTwo.getLowCandidate());
		assertNull(resultTwo.getHighCandidate());
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(threeOfHearts, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fiveOfHearts, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour, cpdRoundThreePlayerOne);
		assertEquals(PlayerNumber.PLAYER_ONE, resultThree.getWinner());
		assertEquals(0, resultThree.getGamePoints());
		assertFalse(resultThree.isJackOfTrump());
		assertEquals(threeOfSpades, resultThree.getLowCandidate());
		assertEquals(threeOfSpades, resultThree.getHighCandidate());
	}
	
	@Test
	public void playAFourTrickRound() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		
		game.startTrick();		
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(threeOfHearts, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fiveOfHearts, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour, cpdRoundThreePlayerOne);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundFourPlayerOne = game.playCard(fiveOfClubs, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundFourPlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundFourPlayerThree = game.playCard(twoOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundFourPlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		TrickResult resultFour = game.playTrick(cpdRoundFourPlayerOne, cpdRoundFourPlayerTwo, cpdRoundFourPlayerThree, cpdRoundFourPlayerFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(resultOne);
		tricks.add(resultTwo);
		tricks.add(resultThree);
		tricks.add(resultFour);
		
		RoundResult roundResult = game.playRound(tricks);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
	}
}
