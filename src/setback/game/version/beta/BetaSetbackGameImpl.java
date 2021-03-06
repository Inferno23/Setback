/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.beta;

import java.util.ArrayList;

import setback.application.SetbackObserver;
import setback.game.SetbackGameController;
import setback.game.version.SetbackGameObservableImpl;
import setback.game.version.alpha.DummyBetController;

/**
 * The implementation of SetbackGameController for the Beta version.
 * This version consists of a single round with four tricks.
 * Player One always begins the round, spades are always trump.
 * @author Michael Burns
 * @version Oct 21, 2013
 */
public class BetaSetbackGameImpl extends SetbackGameObservableImpl implements
		SetbackGameController {
	
	/**
	 * Constructor for a BetaSetbackGameImpl.  Initializes the state variables.
	 */
	public BetaSetbackGameImpl() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		discardingResolved = false;
		trickStarted = false;
		dealerController = new BetaCardDealerController();
		betController = new DummyBetController();
		discardingIgnored = true;
		observers = new ArrayList<SetbackObserver>();
	}
}
