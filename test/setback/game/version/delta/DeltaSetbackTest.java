/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.delta;

import static setback.utilities.TestCard.aceOfClubs;
import static setback.utilities.TestCard.aceOfDiamonds;
import static setback.utilities.TestCard.aceOfSpades;
import static setback.utilities.TestCard.eightOfClubs;
import static setback.utilities.TestCard.eightOfSpades;
import static setback.utilities.TestCard.fiveOfDiamonds;
import static setback.utilities.TestCard.jackOfSpades;
import static setback.utilities.TestCard.kingOfSpades;
import static setback.utilities.TestCard.nineOfSpades;
import static setback.utilities.TestCard.queenOfSpades;
import static setback.utilities.TestCard.sevenOfSpades;
import static setback.utilities.TestCard.sixOfDiamonds;
import static setback.utilities.TestCard.sixOfSpades;
import static setback.utilities.TestCard.tenOfSpades;
import static setback.utilities.TestCard.threeOfDiamonds;
import static setback.utilities.TestCard.threeOfSpades;
import static setback.utilities.TestCard.twoOfSpades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;
import setback.game.common.Bet;
import setback.game.common.Card;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.TrickResult;

/**
 * This class will test random card dealing.
 * @author Michael
 * @version Dec 21, 2013
 */
public class DeltaSetbackTest {
	
	private SetbackGameController game;
	private SetbackGameFactory factory;
	private Card badCard = aceOfClubs;

	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeDeltaSetbackGame(0);
	}
		
	@Test(expected=SetbackException.class)
	public void discardBeforeStartingGame() throws SetbackException {
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardBeforeStartingRound() throws SetbackException {
		game.startGame();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardBeforeResolvingBets() throws SetbackException {
		game.startGame();
		game.startRound();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}

	@Test(expected=SetbackException.class)
	public void discardBeforeSelectingTrump() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardNullFirstCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, null, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardNullSecondCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, null, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardNullThirdCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, null);
	}
	
	@Test(expected=SetbackException.class)
	public void discardInvalidFirstCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, badCard, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardInvalidSecondCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, badCard, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardInvalidThirdCard() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, badCard);
	}
	
	@Test(expected=SetbackException.class)
	public void discardPlayerOneTwice() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardPlayerTwoTwice() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_TWO, eightOfSpades, nineOfSpades, queenOfSpades);
		game.discardCards(PlayerNumber.PLAYER_TWO, eightOfSpades, nineOfSpades, queenOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardPlayerThreeTwice() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_THREE, sevenOfSpades, jackOfSpades, kingOfSpades);
		game.discardCards(PlayerNumber.PLAYER_THREE, sevenOfSpades, jackOfSpades, kingOfSpades);
	}
	
	@Test(expected=SetbackException.class)
	public void discardPlayerFourTwice() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_FOUR, twoOfSpades, tenOfSpades, eightOfClubs);
		game.discardCards(PlayerNumber.PLAYER_FOUR, twoOfSpades, tenOfSpades, eightOfClubs);
	}
	
	@Test(expected=SetbackException.class)
	public void discardPlayerOneAfterResolving() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
		game.discardCards(PlayerNumber.PLAYER_TWO, eightOfSpades, nineOfSpades, queenOfSpades);
		game.discardCards(PlayerNumber.PLAYER_THREE, sevenOfSpades, jackOfSpades, kingOfSpades);
		game.discardCards(PlayerNumber.PLAYER_FOUR, twoOfSpades, tenOfSpades, eightOfClubs);
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
	}

	@Test(expected=SetbackException.class)
	public void startTrickWithoutDiscarding() throws SetbackException {
		initializeUpToDiscard();
		game.startTrick();
	}
	
	@Test
	public void properlyStartASecondRound() throws SetbackException {
		initializeUpToDiscard();
		game.discardCards(PlayerNumber.PLAYER_ONE, threeOfSpades, sixOfSpades, aceOfSpades);
		game.discardCards(PlayerNumber.PLAYER_TWO, eightOfSpades, nineOfSpades, queenOfSpades);
		game.discardCards(PlayerNumber.PLAYER_THREE, sevenOfSpades, jackOfSpades, kingOfSpades);
		game.discardCards(PlayerNumber.PLAYER_FOUR, twoOfSpades, tenOfSpades, eightOfClubs);
		game.startTrick();
		CardPlayerDescriptor cpdOne = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = game.playCard(fiveOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = game.playCard(sixOfDiamonds, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		
		List<TrickResult> trickResults = new ArrayList<TrickResult>();
		trickResults.add(game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour));
		game.playRound(trickResults);
		game.startRound();
	}
	
	/**
	 * Helper function that calls all of the correct functions up
	 * to the point of discarding cards.
	 * @throws SetbackException If a new variable is added that is
	 * not yet covered by this helper.
	 */
	private void initializeUpToDiscard() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.DIAMONDS);
	}
}
