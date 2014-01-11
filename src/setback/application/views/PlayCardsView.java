/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import javax.swing.JFrame;

import setback.application.client.ListenerEnum;
import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

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
	}
}
