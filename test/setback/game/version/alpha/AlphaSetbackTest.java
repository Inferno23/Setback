/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static setback.utilities.TestCard.aceOfHearts;
import static setback.utilities.TestCard.aceOfSpades;
import static setback.utilities.TestCard.jackOfSpades;
import static setback.utilities.TestCard.tenOfSpades;
import static setback.utilities.TestCard.twoOfSpades;

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
 * This class will test the Alpha version of setback.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class AlphaSetbackTest {

	private SetbackGameController game;
	private SetbackGameFactory factory;
	
	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeAlphaSetbackGame();
	}
	
	private List<TrickResult> getAlphaTrickList() throws SetbackException {
		CardPlayerDescriptor cpdOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour));
		return tricks;
	}
	
	@Test(expected=SetbackException.class)
	public void playCardBeforeStartGame() throws SetbackException {
		game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
	}
	
	@Test(expected=SetbackException.class)
	public void playCardBeforeStartRound() throws SetbackException {
		game.startGame();
		game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
	}
	
	@Test
	public void playerOnePlaysAceOfSpades() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		CardPlayerDescriptor cpd = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		assertEquals(aceOfSpades, cpd.getCard());
		assertEquals(PlayerNumber.PLAYER_ONE, cpd.getPlayer());
	}
	
	@Test(expected=SetbackException.class)
	public void playerOnePlaysNotAceOfSpades() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		game.playCard(aceOfHearts, PlayerNumber.PLAYER_ONE);
	}
	
	@Test(expected=SetbackException.class)
	public void playerTwoPlaysFirst() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		game.playCard(aceOfSpades, PlayerNumber.PLAYER_TWO);
	}
	
	@Test
	public void playerOneThenPlayerTwoPlays() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		CardPlayerDescriptor cpdOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		assertEquals(aceOfSpades, cpdOne.getCard());
		assertEquals(PlayerNumber.PLAYER_ONE, cpdOne.getPlayer());
		CardPlayerDescriptor cpdTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		assertEquals(twoOfSpades, cpdTwo.getCard());
		assertEquals(PlayerNumber.PLAYER_TWO, cpdTwo.getPlayer());
	}
	
	@Test(expected=SetbackException.class)
	public void playerTwoPlaysWrongCard() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		game.playCard(aceOfHearts, PlayerNumber.PLAYER_TWO);
	}
	
	@Test
	public void playAFullTrick() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		TrickResult result = game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
		assertEquals(PlayerNumber.PLAYER_ONE, result.getWinner());
		assertEquals(15, result.getGamePoints());
		assertTrue(result.isJackOfTrump());
		assertEquals(twoOfSpades, result.getLowCandidate());
		assertEquals(aceOfSpades, result.getHighCandidate());
	}
	
	@Test
	public void playARound() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		List<TrickResult> tricks = getAlphaTrickList();
		
		RoundResult result = game.playRound(tricks);
		assertEquals(4, result.getTeamOneRoundScore());
		assertEquals(0, result.getTeamTwoRoundScore());
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToRestartAGame() throws SetbackException {
		game.startGame();
		game.startGame();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToRestartARound() throws SetbackException {
		game.startGame();
		game.startRound();
		game.startRound();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToStartARoundBeforeAGame() throws SetbackException {
		game.startRound();
	}
	
	@Test
	public void playTwoRounds() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		List<TrickResult> tricks = getAlphaTrickList();
		
		game.playRound(tricks);
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		game.playRound(tricks);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlaySecondRoundWithoutStartingIt() throws SetbackException {
		game.startGame();
		game.startRound();
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		List<TrickResult> tricks = getAlphaTrickList();
		
		game.playRound(tricks);
		game.playRound(tricks);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlayRoundBeforeStartingTheGame() throws SetbackException {
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(new TrickResult(PlayerNumber.PLAYER_ONE, 15, true, twoOfSpades, aceOfSpades));
		game.playRound(tricks);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlayTrickBeforeStartingTheGame() throws SetbackException {
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlayTrickBeforeStartingTheRound() throws SetbackException {
		game.startGame();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
	}
}