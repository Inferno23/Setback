/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.utilities;

import static setback.utilities.TestCard.aceOfSpades;
import static setback.utilities.TestCard.jackOfSpades;
import static setback.utilities.TestCard.tenOfSpades;
import static setback.utilities.TestCard.twoOfSpades;

import java.util.ArrayList;
import java.util.List;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.common.Bet;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.RoundResult;
import setback.game.common.TrickResult;

/**
 * This class holds testing utility functions that are used
 * in multiple testing files.
 * @author Michael Burns
 * @version Nov 7, 2013
 */
public class UtilityFunctions {

	/**
	 * Places legal bets where Player One bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerOneWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
	}
	
	/**
	 * Places legal bets where Player Two bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerTwoWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
	}
	
	/**
	 * Places legal bets where Player Three bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerThreeWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
	}
	
	/**
	 * Places legal bets where Player Four bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerFourWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.TWO);
	}
	
	
	public static RoundResult teamOneWinsRound(SetbackGameController game) throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		TrickResult trickResult = game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(trickResult);
		
		return game.playRound(tricks);
	}
}
