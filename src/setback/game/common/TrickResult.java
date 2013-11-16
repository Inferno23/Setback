/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import setback.common.PlayerNumber;

/**
 * This class contains the results of a single trick.  Information
 * includes the winner of the trick, how many game points were
 * earned, the presence of the jack of trump, and any
 * high/low candidates.
 *  
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class TrickResult {

	private final PlayerNumber winner;
	private final int gamePoints;
	private final boolean jackOfTrump;
	private final Card lowCandidate;
	private final Card highCandidate;
	
	/**
	 * Constructor that sets the properties of a TrickResult
	 * @param winner The PlayerNumber who won the trick.
	 * @param gamePoints The number of gamePoints in the trick.
	 * @param jackOfTrump Whether or not the jack of trump was played.
	 * @param lowCandidate The lowest trump card played or null.
	 * @param highCandidate The highest trump card played or null.
	 */
	public TrickResult(PlayerNumber winner, int gamePoints,
			boolean jackOfTrump, Card lowCandidate, Card highCandidate) {
		
		this.winner = winner;
		this.gamePoints = gamePoints;
		this.jackOfTrump = jackOfTrump;
		this.lowCandidate = lowCandidate;
		this.highCandidate = highCandidate;
	}

	/**
	 * @return the winner.
	 */
	public PlayerNumber getWinner() {
		return winner;
	}

	/**
	 * @return the gamePoints.
	 */
	public int getGamePoints() {
		return gamePoints;
	}

	/**
	 * @return the jackOfTrump.
	 */
	public boolean isJackOfTrump() {
		return jackOfTrump;
	}

	/**
	 * @return the lowCandidate.
	 */
	public Card getLowCandidate() {
		return lowCandidate;
	}

	/**
	 * @return the highCandidate.
	 */
	public Card getHighCandidate() {
		return highCandidate;
	}
}
