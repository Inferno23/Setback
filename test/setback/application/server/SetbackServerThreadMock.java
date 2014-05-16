/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import java.io.PrintWriter;
import java.net.Socket;

import setback.game.SetbackGameController;

/**
 * This class subclasses the real SetbackServerThread, but has getters
 * for the protected fields in order to run validation tests.
 * @author Michael Burns
 * @version May 15, 2014
 */
public class SetbackServerThreadMock extends SetbackServerThread {
	
	/* (non-Javadoc)
	 * @see setback.application.server.SetbackServerThread#SetbackServerThread()
	 */
	public SetbackServerThreadMock(Socket socket, SetbackGameController game) {
		super(socket, game);
		controller = new PlayerControllerMock(game);
	}

	/**
	 * @return the controller.
	 */
	public PlayerController getController() {
		return controller;
	}

	/**
	 * @return the game.
	 */
	public SetbackGameController getGame() {
		PlayerControllerMock mockController = (PlayerControllerMock) controller;
		return mockController.getGame();
	}
	
	/**
	 * Setter for the out PrintWriter
	 * @param out
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}
