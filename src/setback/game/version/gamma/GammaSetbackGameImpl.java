/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import java.util.ArrayList;

import setback.application.SetbackObserver;
import setback.game.SetbackGameController;
import setback.game.version.SetbackGameObservableImpl;
import setback.game.version.beta.BetaCardDealerController;

/**
 * The implementation of SetbackGameController for the Gamma version.
 * This version consists of a single round with four tricks.
 * Actual betting is implemented in this version.
 * @author Michael Burns
 * @version Oct 30, 2013
 */
public class GammaSetbackGameImpl extends SetbackGameObservableImpl implements
		SetbackGameController {

	/**
	 * Constructor for a GammaSetbackGameImpl.  Initializes the state variables.
	 */
	public GammaSetbackGameImpl() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		discardingResolved = false;
		trickStarted = false;
		dealerController = new BetaCardDealerController();
		betController = new GammaBetController();
		discardingIgnored = true;
		observers = new ArrayList<SetbackObserver>();
	}
}
