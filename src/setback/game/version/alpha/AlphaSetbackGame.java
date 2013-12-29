/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import java.util.ArrayList;

import setback.game.SetbackGameController;
import setback.game.version.SetbackGameControllerSkeleton;
import setback.networking.SetbackObserver;

/**
 * The implementation of SetbackGameController for the Alpha version.
 * This version consists of a single round with a single trick.
 * Player One always goes first, spades are always trump.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class AlphaSetbackGame  extends SetbackGameControllerSkeleton implements SetbackGameController {

	/**
	 * Constructor for an AlphaSetbackGame.  Initializes the state variables.
	 */
	public AlphaSetbackGame() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		discardingResolved = false;
		trickStarted = false;
		dealerController = new AlphaCardDealerController();
		betController = new DummyBetController();
		discardingIgnored = true;
		observers = new ArrayList<SetbackObserver>();
	}
}
