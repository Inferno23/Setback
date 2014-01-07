/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import setback.common.PlayerNumber;

/**
 * This class contains the result of a player placing a bet.
 * It has the player who was the bettor, and their proposed bet.
 * @author Michael Burns
 * @version Oct 30, 2013
 */
public class BetResult {
	
	private final PlayerNumber bettor;
	private final Bet bet;
	
	/**
	 * Constructor that sets the properties of a BetResult.
	 * @param bettor The player who bet.
	 * @param bet The bet they proposed.
	 */
	public BetResult(PlayerNumber bettor, Bet bet) {
		this.bettor = bettor;
		this.bet = bet;
	}

	/**
	 * @return the bettor.
	 */
	public PlayerNumber getBettor() {
		return bettor;
	}

	/**
	 * @return the bet.
	 */
	public Bet getBet() {
		return bet;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return bettor.toString() + " WON WITH A BET OF  " + bet.toString().toUpperCase();
	}
}
