/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import setback.common.PlayerNumber;
import setback.game.SetbackGameController;
import setback.game.common.Hand;

/**
 * This class subclasses the real PlayerController, but has getters
 * in order to do testing.
 * @author Michael Burns
 * @version May 15, 2014
 */
public class PlayerControllerMock extends PlayerController {
	
	/**
	 * The status field is used to determine the state of the mock PlayerController.
	 */
	private String status;
	
	/* (non-Javadoc)
	 * @see setback.application.server.PlayerController#PlayerController()
	 */
	public PlayerControllerMock(SetbackGameController game) {
		super(game);
		status = null;
	}

	/**
	 * @return the game.
	 */
	public SetbackGameController getGame() {
		return game;
	}

	/**
	 * @return the myNumber.
	 */
	public PlayerNumber getMyNumber() {
		return myNumber;
	}

	/**
	 * @return the myHand.
	 */
	public Hand getMyHand() {
		return myHand;
	}

	/**
	 * @return the status.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Set the status string to be "ROUND STARTED"
	 */
	@Override
	public void startRound() {
		status = "ROUND STARTED";
	}
}
