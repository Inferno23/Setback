/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.common.Bet;
import setback.game.common.BetResult;

/**
 * This interface will handle betting at the start of a round.
 * The public methods in the interface are placeBet and determineWinner.
 * validateBet is a 
 * @author Michael Burns
 * @version Oct 29, 2013
 */
public interface BetController {

	/**
	 * This function places a bet for a player if the bet
	 * is validated as legal.
	 * @param bettor The player who is betting.
	 * @param bet The bet that they are proposing.
	 * @throws SetbackException If the bet is illegal.
	 */
	void placeBet(PlayerNumber bettor, Bet bet) throws SetbackException;
	
	/**
	 * This function validates that a bet is legal.
	 * @param bettor The player who is betting.
	 * @param bet The bet that they are proposing.
	 * @throws SetbackException If the bet is illegal.
	 */
	void validateBet(PlayerNumber bettor, Bet bet) throws SetbackException;
	
	/**
	 * This function determines the winner based on the past four bets.
	 * It throws an exception if there have not been four bets.
	 * It clears the bet fields for the next round of betting.
	 * @return The winner and their bet.
	 * @throws SetbackExcpetion If anything goes wrong.
	 */
	BetResult determineWinner() throws SetbackException;
}
