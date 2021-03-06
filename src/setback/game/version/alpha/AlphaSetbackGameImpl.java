/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import java.util.ArrayList;

import setback.application.SetbackObserver;
import setback.game.SetbackGameController;
import setback.game.version.SetbackGameObservableImpl;

/**
 * The implementation of SetbackGameController for the Alpha version.
 * This version consists of a single round with a single trick.
 * Player One always goes first, spades are always trump.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class AlphaSetbackGameImpl extends SetbackGameObservableImpl implements SetbackGameController {

	/**
	 * Constructor for an AlphaSetbackGameImpl.  Initializes the state variables.
	 */
	public AlphaSetbackGameImpl() {
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
