/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import setback.game.version.alpha.AlphaSetbackGameImpl;
import setback.game.version.beta.BetaSetbackGameImpl;
import setback.game.version.delta.DeltaSetbackGameImpl;
import setback.game.version.gamma.GammaSetbackGameImpl;

/**
 * Factory to produce various versions of the Setback Game.
 * This is implemented as a singleton.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class SetbackGameFactory {

	private static final SetbackGameFactory instance = new SetbackGameFactory();
	
	/**
	 * Default private constructor to ensure this is a singleton.
	 * This is deliberately left empty.
	 */
	private SetbackGameFactory() {
		// Intentionally Left empty.
	}

	/**
	 * @return the instance.
	 */
	public static SetbackGameFactory getInstance() {
		return instance;
	}
	
	/**
	 * Create a new Alpha Setback game.
	 * @return The created Alpha Setback game.
	 */
	public SetbackGameController makeAlphaSetbackGame() {
		return new AlphaSetbackGameImpl();
	}
	
	/**
	 * Create a new Beta Setback game.
	 * @return The created Beta Setback game.
	 */
	public SetbackGameController makeBetaSetbackGame() {
		return new BetaSetbackGameImpl();
	}
	
	/**
	 * Create a new Gamma Setback game.
	 * @return The created Gamma Setback game.
	 */
	public SetbackGameController makeGammaSetbackGame() {
		return new GammaSetbackGameImpl();
	}
	
	/**
	 * Create a new Delta Setback game with a random seed.
	 * @return The created Delta Setback game.
	 */
	public SetbackGameController makeDeltaSetbackGame() {
		return new DeltaSetbackGameImpl(System.currentTimeMillis());
	}
	
	/**
	 * Create a new Delta Setback game.
	 * @param seed The seed for the random number
	 * generator in the dealer.
	 * @return The created Delta Setback game.
	 */
	public SetbackGameController makeDeltaSetbackGame(long seed) {
		return new DeltaSetbackGameImpl(seed);
	}
}
