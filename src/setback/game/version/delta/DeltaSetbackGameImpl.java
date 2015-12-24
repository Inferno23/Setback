/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.delta;

import java.util.ArrayList;

import setback.application.SetbackObserver;
import setback.game.SetbackGameController;
import setback.game.version.SetbackGameObservableImpl;
import setback.game.version.gamma.GammaBetController;

/**
 * The implementation of SetbackGameController for the Delta version.
 * This version consists of a full game with random hands and discarding.
 * A real game of Setback can be played starting with this version. 
 * @author Michael
 * @version Dec 21, 2013
 */
public class DeltaSetbackGameImpl extends SetbackGameObservableImpl {

	/**
	 * Constructor for a DeltaSetbackGameImpl that takes in a seed
	 * to be used with the random number generator in the
	 * card dealer.
	 * @param seed The seed for the random number generator
	 * in the card dealer.
	 */
	public DeltaSetbackGameImpl(long seed) {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		discardingResolved = false;
		trickStarted = false;
		dealerController = new DeltaCardDealerController(seed);
		betController = new GammaBetController();
		discardingIgnored = false;
		playerOneSelected = false;
		playerTwoSelected = false;
		playerThreeSelected = false;
		playerFourSelected = false;
		observers = new ArrayList<SetbackObserver>();
	}
}
