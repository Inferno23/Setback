/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import setback.common.PlayerNumber;
import setback.game.BetController;
import setback.game.common.Bet;
import setback.game.common.BetResult;

/**
 * This is a dummy BetController to keep the code from Alpha
 * and Beta functional with the Skeleton. 
 * @author Michael Burns
 * @version Oct 30, 2013
 */
public class DummyBetController implements BetController {

	/* (non-Javadoc)
	 * @see setback.game.BetController#placeBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void placeBet(PlayerNumber bettor, Bet bet) {};

	/* (non-Javadoc)
	 * @see setback.game.BetController#validateBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void validateBet(PlayerNumber bettor, Bet bet) {};

	/* (non-Javadoc)
	 * @see setback.game.BetController#determineWinner()
	 */
	@Override
	public BetResult determineWinner() {
		return new BetResult(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}

}
