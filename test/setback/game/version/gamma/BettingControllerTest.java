/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.BetController;
import setback.game.common.Bet;
import setback.game.common.BetResult;
import setback.game.version.alpha.DummyBetController;

/**
 * This test class will implement all of the non-integrated
 * betting tests.
 * @author Michael Burns
 * @version Nov 5, 2013
 */
public class BettingControllerTest {

	private BetController betController;

	@Before
	public void setup() {
		betController = new GammaBetController();
	}

	@Test
	public void dummyBettingCoverageTest() throws SetbackException {
		BetController dummyController = new DummyBetController();
		dummyController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		dummyController.validateBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}

	@Test
	public void playerOneBetsTwoRestPass() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_ONE, result.getBettor());
		assertEquals(Bet.TWO, result.getBet());
	}

	@Test
	public void playersOneAndTwoBet() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.THREE);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_TWO, result.getBettor());
		assertEquals(Bet.THREE, result.getBet());
	}

	@Test
	public void playerThreeBets() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_THREE, result.getBettor());
		assertEquals(Bet.FOUR, result.getBet());
	}

	@Test
	public void playerFourBets() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.FIVE);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getBettor());
		assertEquals(Bet.FIVE, result.getBet());
	}

	@Test(expected=SetbackException.class)
	public void playerFourTriesToTakeWithoutEarlierBet() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TAKE);
	}

	@Test(expected=SetbackException.class)
	public void tooFewBets() throws SetbackException {
		betController.determineWinner();
	}

	@Test(expected=SetbackException.class)
	public void tooManyBets() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.THREE);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.FOUR);
	}

	@Test(expected=SetbackException.class)
	public void firstBetTriesToTake() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TAKE);
	}

	@Test(expected=SetbackException.class)
	public void secondBetTriesToTake() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.TAKE);
	}

	@Test(expected=SetbackException.class)
	public void thirdBetTriesToTake() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.TAKE);
	}

	@Test(expected=SetbackException.class)
	public void allPlayersPass() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
	}

	@Test(expected=SetbackException.class)
	public void secondPlayerUnderbetsFirst() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
	}

	@Test(expected=SetbackException.class)
	public void thirdPlayerUnderbetsFirst() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.TWO);
	}

	@Test(expected=SetbackException.class)
	public void thirdPlayerUnderbetsSecond() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.TWO);
	}

	@Test(expected=SetbackException.class)
	public void fourthPlayerUnderbetsFirst() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TWO);
	}

	@Test(expected=SetbackException.class)
	public void fourthPlayerUnderbetsSecond() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TWO);
	}

	@Test(expected=SetbackException.class)
	public void fourthPlayerUnderbetsThird() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.FOUR);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TWO);
	}

	@Test
	public void fourthPlayerTakesFirstBet() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TAKE);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getBettor());
		assertEquals(Bet.TWO, result.getBet());
	}

	@Test
	public void fourthPlayerTakesSecondBet() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TAKE);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getBettor());
		assertEquals(Bet.TWO, result.getBet());
	}

	@Test
	public void fourthPlayerTakesThirdBet() throws SetbackException {
		betController.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		betController.placeBet(PlayerNumber.PLAYER_THREE, Bet.TWO);
		betController.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TAKE);
		BetResult result = betController.determineWinner();
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getBettor());
		assertEquals(Bet.TWO, result.getBet());
	}
}
