/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import java.util.List;

import setback.common.PlayerNumber;

/**
 * This class represents a single round in a game of setback.
 * It contains the list of tricks that were played. 
 * @author Michael Burns
 * @version Oct 20, 2013
 */
public class Round {

	private final List<TrickResult> tricks;

	/**
	 * Constructor that sets the properties of a Round.
	 * @param tricks The tricks that were played in this round.
	 */
	public Round(List<TrickResult> tricks) {
		this.tricks = tricks;
	}

	/**
	 * @return the tricks.
	 */
	public List<TrickResult> getTricks() {
		return tricks;
	}

	/**
	 * Determines the results of the round.  Calculates
	 * how many overall points each of the two teams earned.
	 * @param bet The BetResult for the current round.  This
	 * is needed to determine if the teams hit their targets.
	 * @return The RoundResult containing the number of
	 * overall points each team won.
	 */
	public RoundResult determineRoundResults(BetResult bet) {
		int teamOneRoundScore = 0;
		int teamTwoRoundScore = 0;
		Card teamOneHighCandidate = null;
		Card teamTwoHighCandidate = null;
		Card teamOneLowCandidate = null;
		Card teamTwoLowCandidate = null;
		int teamOneGameScore = 0;
		int teamTwoGameScore = 0;
		boolean teamOneWinEveryTrick = true;
		boolean teamTwoWinEveryTrick = false;

		// Loop over tricks to get candidates.
		for (TrickResult trick : tricks) {
			// High
			if (trick.getWinner() == PlayerNumber.PLAYER_ONE || trick.getWinner() == PlayerNumber.PLAYER_THREE) {
				// Shooting the moon
				teamTwoWinEveryTrick = false;
				// High
				if (teamOneHighCandidate == null) {
					teamOneHighCandidate = trick.getHighCandidate();
				}
				else if (trick.getHighCandidate() != null) {
					if (teamOneHighCandidate.getType().getStandardValue() <
							trick.getHighCandidate().getType().getStandardValue()) {
						teamOneHighCandidate = trick.getHighCandidate();
					}
				}
			}
			else {
				// Shooting the moon
				teamOneWinEveryTrick = false;
				// High
				if (teamTwoHighCandidate == null) {
					teamTwoHighCandidate = trick.getHighCandidate();
				}
				else if (trick.getHighCandidate() != null) {
					if (teamTwoHighCandidate.getType().getStandardValue() <
							trick.getHighCandidate().getType().getStandardValue()) {
						teamTwoHighCandidate = trick.getHighCandidate();
					}
				}
			}
			// Low
			if (trick.getWinner() == PlayerNumber.PLAYER_ONE || trick.getWinner() == PlayerNumber.PLAYER_THREE) {
				if (teamOneLowCandidate == null) {
					teamOneLowCandidate = trick.getLowCandidate();
				}
				else if (trick.getLowCandidate() != null) {
					if (teamOneLowCandidate.getType().getStandardValue() >
					trick.getLowCandidate().getType().getStandardValue()) {
						teamOneLowCandidate = trick.getLowCandidate();
					}
				}
			}
			else {
				if (teamTwoLowCandidate == null) {
					teamTwoLowCandidate = trick.getLowCandidate();
				}
				else if (trick.getLowCandidate() != null) {
					if (teamTwoLowCandidate.getType().getStandardValue() >
					trick.getLowCandidate().getType().getStandardValue()) {
						teamTwoLowCandidate = trick.getLowCandidate();
					}
				}
			}
			// Jack
			if (trick.isJackOfTrump()) {
				if (trick.getWinner() == PlayerNumber.PLAYER_ONE || trick.getWinner() == PlayerNumber.PLAYER_THREE) {
					teamOneRoundScore++;
				}
				else {
					teamTwoRoundScore++;
				}
			}
			// Game
			if (trick.getWinner() == PlayerNumber.PLAYER_ONE || trick.getWinner() == PlayerNumber.PLAYER_THREE) {
				teamOneGameScore += trick.getGamePoints();
			}
			else {
				teamTwoGameScore += trick.getGamePoints();
			}
		}

		// Determine the end of round winners
		
		// High
		if (teamOneHighCandidate != null) {
			if (teamTwoHighCandidate != null) {
				// Both not null
				if (teamOneHighCandidate.getType().getStandardValue() >
				teamTwoHighCandidate.getType().getStandardValue()) {
					// Team one wins
					teamOneRoundScore++;
				}
				else {
					// Team two wins
					teamTwoRoundScore++;
				}
			}
			else {
				// Team Two null
				teamOneRoundScore++;
			}
		}
		else {
			// Team One null
			teamTwoRoundScore++;
		}
		// Low
		if (teamOneLowCandidate != null) {
			if (teamTwoLowCandidate != null) {
				// Both not null
				if (teamOneLowCandidate.getType().getStandardValue() <
				teamTwoLowCandidate.getType().getStandardValue()) {
					// Team one wins
					teamOneRoundScore++;
				}
				else {
					// Team two wins
					teamTwoRoundScore++;
				}
			}
			else {
				// Team Two null
				teamOneRoundScore++;
			}
		}
		else {
			// Team One null
			teamTwoRoundScore++;
		}
		// Game
		if (teamOneGameScore > teamTwoGameScore) {
			teamOneRoundScore++;
		}
		else if (teamOneGameScore < teamTwoGameScore) {
			teamTwoRoundScore++;
		}

		// Shoot the moon
		if (bet.getBet() == Bet.FIVE) {
			if (bet.getBettor() == PlayerNumber.PLAYER_ONE || bet.getBettor() == PlayerNumber.PLAYER_THREE) {
				if (teamOneWinEveryTrick) {
					teamOneRoundScore = 5;
				}
			}
			else {
				if (teamTwoWinEveryTrick) {
					teamTwoRoundScore = 5;
				}
			}
		}
		
		// Check if totals match bets.
		if (bet.getBettor() == PlayerNumber.PLAYER_ONE || bet.getBettor() == PlayerNumber.PLAYER_THREE) {
			if (teamOneRoundScore < bet.getBet().getValue()) {
				// Losers get negative scores
				teamOneRoundScore = (bet.getBet().getValue() * -1);
			}
		}
		else {
			if (teamTwoRoundScore < bet.getBet().getValue()) {
				// Losers get negative scores
				teamTwoRoundScore = (bet.getBet().getValue() * -1);
			}
		}
		
		return new RoundResult(teamOneRoundScore, teamTwoRoundScore, RoundResultStatus.OK);
	}
}
