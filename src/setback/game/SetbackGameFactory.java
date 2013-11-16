/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import setback.game.version.alpha.AlphaSetbackGame;
import setback.game.version.beta.BetaSetbackGame;
import setback.game.version.gamma.GammaSetbackGame;

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
		return new AlphaSetbackGame();
	}
	
	/**
	 * Create a new Beta Setback game.
	 * @return The created Beta Setback game.
	 */
	public SetbackGameController makeBetaSetbackGame() {
		return new BetaSetbackGame();
	}
	
	/**
	 * Create a new Gamma Setback game.
	 * @return The created Gamma Setback game.
	 */
	public SetbackGameController makeGammaSetbackGame() {
		return new GammaSetbackGame();
	}
}
