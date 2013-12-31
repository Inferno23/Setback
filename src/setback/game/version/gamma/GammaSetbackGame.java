/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import java.util.ArrayList;

import setback.game.SetbackGameController;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.TrickResult;
import setback.game.version.SetbackGameControllerSkeleton;
import setback.game.version.beta.BetaCardDealerController;
import setback.networking.SetbackObserver;

/**
 * The implementation of SetbackGameController for the Gamma version.
 * This version consists of a single round with four tricks.
 * Actual betting is implemented in this version.
 * @author Michael Burns
 * @version Oct 30, 2013
 */
public class GammaSetbackGame extends SetbackGameControllerSkeleton implements
		SetbackGameController {

	/**
	 * Constructor for a GammaSetbackGame.  Initializes the state variables.
	 */
	public GammaSetbackGame() {
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
