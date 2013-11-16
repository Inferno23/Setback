/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This class contains the complete results of a round of Setback.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class RoundResult {

	private final int teamOneRoundScore;
	private final int teamTwoRoundScore;
	private final RoundResultStatus status;
	
	/**
	 * Constructor that sets the properties of a RoundResult.
	 * @param teamOneRoundScore The number of overall points team one earned.
	 * @param teamTwoRoundScore The number of overall points team two earned.
	 * @param status A status indicating if this round has led to the end of the game.
	 */
	public RoundResult(int teamOneRoundScore, int teamTwoRoundScore, RoundResultStatus status) {
		this.teamOneRoundScore = teamOneRoundScore;
		this.teamTwoRoundScore = teamTwoRoundScore;
		this.status = status;
	}

	/**
	 * @return the teamOneRoundScore.
	 */
	public int getTeamOneRoundScore() {
		return teamOneRoundScore;
	}

	/**
	 * @return the teamTwoRoundScore.
	 */
	public int getTeamTwoRoundScore() {
		return teamTwoRoundScore;
	}
	
	/**
	 * @return the status
	 */
	public RoundResultStatus getStatus() {
		return status;
	}
}
