/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.utilities;

import static setback.utilities.TestCard.*;
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
public class TestUtilityFunctions {

	/**
	 * Places legal bets where Player One bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerOneWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}
	
	/**
	 * Places legal bets where Player Two bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerTwoWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
	}
	
	/**
	 * Places legal bets where Player Three bets Two, and
	 * everyone else passes.
	 * @throws SetbackException
	 */
	public static void playerThreeWinsBet(SetbackGameController game) throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
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
	
	/**
	 * Plays a round with a single trick where Team One wins the bet of 2,
	 * and wins the round, resulting in scores of +4 and +0.
	 * NOTE: Uses cards from Beta/Gamma, but this still works with later versions
	 * @param game The game controller to run this function on.
	 * @return The result of the round that was played.
	 * @throws SetbackException if something highly unexpected happens.
	 */
	public static RoundResult teamOneWinsRoundOnBet(SetbackGameController game) throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
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
	
	/**
	 * Plays a round with a single trick where Team One wins the bet of 5,
	 * but loses the round, resulting in scores of -5, and +3.
	 * NOTE: Uses cards from Beta/Gamma, but this still works with later versions
	 * @param game The game controller to run this function on.
	 * @return The result of the round that was played.
	 * @throws SetbackException if something highly unexpected happens.
	 */
	public static RoundResult teamOneLosesRoundOnBet(SetbackGameController game) throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.FIVE);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.DIAMONDS);
		game.startTrick();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		
		TrickResult trickResult = game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(trickResult);
		
		return game.playRound(tricks);
	}
	
	/**
	 * Plays a round with a single trick where Team Two wins the bet of 2,
	 * but loses the round, resulting in scores of +4, and -2.
	 * NOTE: Uses cards from Beta/Gamma, but this still works with later versions
	 * @param game The game controller to run this function on.
	 * @return The result of the round that was played.
	 * @throws SetbackException if something highly unexpected happens.
	 */
	public static RoundResult teamOneWinsRoundOffBet(SetbackGameController game) throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_TWO, CardSuit.SPADES);
		game.startTrick();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		TrickResult trickResult = game.playTrick(cpdTwo, cpdThree, cpdFour, cpdOne);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(trickResult);
		
		return game.playRound(tricks);
	}
	
	/**
	 * Plays a round with a single trick where Team Two wins the bet of 2,
	 * and wins the round, resulting in scores of +0, and +3.
	 * NOTE: Uses cards from Beta/Gamma, but this still works with later versions
	 * @param game The game controller to run this function on.
	 * @return The result of the round that was played.
	 * @throws SetbackException if something highly unexpected happens.
	 */
	public static RoundResult teamTwoWinsRoundOnBet(SetbackGameController game) throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_TWO, CardSuit.DIAMONDS);
		game.startTrick();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		
		TrickResult trickResult = game.playTrick(cpdTwo, cpdThree, cpdFour, cpdOne);
	
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(trickResult);
		
		return game.playRound(tricks);
	}
}
