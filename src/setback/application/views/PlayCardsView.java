/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import javax.swing.JFrame;
import javax.swing.JLabel;

import setback.application.client.ListenerEnum;
import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;
import setback.common.PlayerNumber;

/**
 * This view will handle playing cards.  It will
 * display cards that people play by putting them
 * in the center, but in front of the player.
 * At the end of the trick, cards will pause for
 * two seconds prior to disappearing.  There will
 * be a message indicating whose turn it is at a
 * given time displayed in the center of the view.
 * @author Michael Burns
 * @version Jan 8, 2014
 */
public class PlayCardsView extends SetbackClientView {

	/**
	 * Create the GUI for playing cards.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlayCardsView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Playing Cards screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Hands
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.PLAY);
		displayNeighborHands(9);
		currentPlayerLabel = new JLabel();
		currentPlayerLabel.setBounds(100, 50, 200, 20);
		frame.getContentPane().add(currentPlayerLabel);
		findCurrentPlayer();
		unpauseToggle = false;
	}

	/**
	 * Helper function that determines if this player is the
	 * current player, and if not, waits for the correct
	 * player to play a card.
	 */
	private void findCurrentPlayer() {
		// Check if this is the active player
		PlayerNumber currentPlayer = PlayerNumber.valueOf(controller.userInput("GET_CURRENT_PLAYER").toUpperCase());
		if (currentPlayer.equals(controller.getLeft())) {
			currentPlayerLabel.setText("Current Player: Left");
		}
		else if (currentPlayer.equals(controller.getCenter())) {
			currentPlayerLabel.setText("Current Player: Center");
		}
		else if (currentPlayer.equals(controller.getRight())) {
			currentPlayerLabel.setText("Current Player: Right");
		}
		else {
			// I am the current player
			currentPlayerLabel.setText("Current Player: Me");
		}
		waitForAnyCard();
	}
}
