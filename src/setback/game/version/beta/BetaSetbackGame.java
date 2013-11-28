/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.beta;

import setback.game.SetbackGameController;
import setback.game.version.SetbackGameControllerSkeleton;
import setback.game.version.alpha.DummyBetController;

/**
 * The implementation of SetbackGameController for the Beta version.
 * This version consists of a single round with four tricks.
 * Player One always begins the round, spades are always trump.
 * @author Michael Burns
 * @version Oct 21, 2013
 */
public class BetaSetbackGame extends SetbackGameControllerSkeleton implements
		SetbackGameController {
	
	/**
	 * Constructor for a BetaSetbackGame.  Initializes the state variables.
	 */
	public BetaSetbackGame() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		discardingResolved = false;
		trickStarted = false;
		dealerController = new BetaCardDealerController();
		betController = new DummyBetController();
		discardingIgnored = true;
	}
}
