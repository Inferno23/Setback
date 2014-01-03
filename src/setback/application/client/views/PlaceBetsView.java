/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client.views;

import javax.swing.JFrame;
import javax.swing.JLabel;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

/**
 * This class is the GUI for Placing Bets
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PlaceBetsView extends SetbackClientView {

	private JLabel pleaseWait;
	
	/**
	 * Create the GUI for placing bets.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlaceBetsView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Place Bets screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Please wait message
		pleaseWait = new JLabel("THE GAME HAS BEGUN!!!");
		pleaseWait.setBounds(350, 300, 300, 20);
		frame.getContentPane().add(pleaseWait);

	}
	
	/**
	 * This helper function busy waits while displaying the
	 * please wait message until all four players have connected,
	 * then it terminates, allowing the bettingInitialization
	 * function to execute.
	 * @return The contents of the player's hand
	 */
	private String busyWait() {
		String handContents = controller.userInput("SHOW_HAND");
		while (handContents.equals("You do not have a hand yet!")) {
			pleaseWait.setVisible(true);
			try {
				Thread.sleep(2000);
				handContents = controller.userInput("SHOW_HAND");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pleaseWait.setVisible(false);
		return handContents;
	}
	
	/**
	 * Helper function that initializes the displaying of the hand
	 * and the betting buttons.
	 * @param handContents The contents of the player's hand.
	 */
	private void bettingInitialization(String handContents) {
		String cards[] = handContents.split("\n");
		System.out.println(handContents);
	}
}
