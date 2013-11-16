/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.BetController;
import setback.game.common.Bet;
import setback.game.common.BetResult;

/**
 * This is the implementation of betting for the BetController.
 * @author Michael Burns
 * @version Oct 31, 2013
 */
public class GammaBetController implements BetController {

	BetResult firstBetResult;
	BetResult secondBetResult;
	BetResult thirdBetResult;
	BetResult fourthBetResult;

	/**
	 * Constructor for a bet controller.  It initializes the fields.
	 */
	public GammaBetController() {
		clearBets();
	}

	/* (non-Javadoc)
	 * @see setback.game.BetController#placeBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void placeBet(PlayerNumber bettor, Bet bet) throws SetbackException {
		if (firstBetResult == null) {
			validateBet(bettor, bet);
			firstBetResult = new BetResult(bettor, bet);
		}
		else if (secondBetResult == null) {
			validateBet(bettor, bet);
			secondBetResult = new BetResult(bettor, bet);
		}
		else if (thirdBetResult == null) {
			validateBet(bettor, bet);
			thirdBetResult = new BetResult(bettor, bet);
		}
		else if (fourthBetResult == null) {
			validateBet(bettor, bet);
			fourthBetResult = new BetResult(bettor, bet);
		}
		else {
			throw new SetbackException("All four bets have already been placed!");
		}
	}

	/* (non-Javadoc)
	 * @see setback.game.BetController#validateBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void validateBet(PlayerNumber bettor, Bet bet)
			throws SetbackException {
		// First Bet
		if (firstBetResult == null) {
			if (bet == Bet.TAKE) {
				throw new SetbackException("Only the dealer can take the bet!");
			}
		}
		// Second Bet
		else if (secondBetResult == null) {
			if (bet == Bet.TAKE) {
				throw new SetbackException("Only the dealer can take the bet!");
			}
			else if (bet != Bet.PASS) {
				// They didn't pass, so check that it was a valid bet
				if (bet.getValue() <= firstBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
			}
		}
		// Third Bet
		else if (thirdBetResult == null) {
			if (bet == Bet.TAKE) {
				throw new SetbackException("Only the dealer can take the bet!");
			}
			else if (bet != Bet.PASS) {
				// They didn't pass, so check that it was a valid bet
				if (bet.getValue() <= secondBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
				else if (bet.getValue() <= firstBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
			}
		}
		// Fourth Bet
		else {
			if (bet == Bet.PASS) {
				if (thirdBetResult.getBet() == Bet.PASS && 
						secondBetResult.getBet() == Bet.PASS &&
						firstBetResult.getBet() == Bet.PASS) {
					throw new SetbackException("The dealer cannot pass if everyone else has passed!");
				}
			}
			else if (bet != Bet.TAKE) {
				// They didn't take the bet, so check that it was a valid bet
				if (bet.getValue() <= thirdBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
				else if (bet.getValue() <= secondBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
				else if (bet.getValue() <= firstBetResult.getBet().getValue()) {
					throw new SetbackException("You must exceed all previous bets or pass!");
				}
			}
			else {
				if (thirdBetResult.getBet() == Bet.PASS && 
						secondBetResult.getBet() == Bet.PASS &&
						firstBetResult.getBet() == Bet.PASS) {
					throw new SetbackException("The dealer cannot take the bet if everyone else has passed!");
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see setback.game.BetController#determineWinner()
	 */
	@Override
	public BetResult determineWinner() throws SetbackException {
		BetResult result;

		if (fourthBetResult == null) {
			throw new SetbackException("All four bets must be placed before a winner can be determined!");
		}

		if (fourthBetResult.getBet() != Bet.PASS) {
			// The dealer is taking the bet, time to figure out what it is.
			if (fourthBetResult.getBet() == Bet.TAKE) {
				if (thirdBetResult.getBet() != Bet.PASS) {
					result = new BetResult(fourthBetResult.getBettor(), thirdBetResult.getBet());
				}
				else if (secondBetResult.getBet() != Bet.PASS) {
					result = new BetResult(fourthBetResult.getBettor(), secondBetResult.getBet());
				}
				else {
					result = new BetResult(fourthBetResult.getBettor(), firstBetResult.getBet());
				}
			}
			// The dealer is betting, but not taking, what a weirdo.
			else {
				result = fourthBetResult;
			}
		}
		else if (thirdBetResult.getBet() != Bet.PASS) {
			result = thirdBetResult;
		}
		else if (secondBetResult.getBet() != Bet.PASS) {
			result = secondBetResult;
		}
		else {
			result = firstBetResult;
		}

		// We have determined the result.  Clear the bets for the next time. 
		clearBets();
		// Return the result.
		return result;
	}

	/**
	 * A helper function that clears the bet fields after betting.
	 */
	private void clearBets() {
		firstBetResult = null;
		secondBetResult = null;
		thirdBetResult = null;
		fourthBetResult = null;
	}
}
