/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import setback.application.client.SetbackClientController;

/**
 * This class is the GUI for waiting for everyone to connect.
 * @author Michael Burns
 * @version May 27, 2014
 */
public class PleaseWaitView extends SetbackClientView {

	protected Timer connectionTimer;
	protected JLabel pleaseWait;
	
	/**
	 * Create the GUI for waiting for connections.
	 * Just call the super constructor.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PleaseWaitView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
		this.frame.revalidate();
		this.frame.repaint();
		view = this;
	}
	
	/**
	 * This function initializes the Please Wait screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	protected void initialize() {
		// Background and visibility
				super.initialize();
				// Please wait message
				final String pleaseWaitString = "Please wait for other players";
				final int pleaseWaitSize = pleaseWaitString.length() * UNICODE_SIZE_CONSTANT;
				pleaseWait = new JLabel(pleaseWaitString);
				pleaseWait.setBounds(GUI_WIDTH_CENTER - (pleaseWaitSize / 2), GUI_HEIGHT_CENTER, pleaseWaitSize, GUI_TEXT_HEIGHT);
				frame.getContentPane().add(pleaseWait);
				// Update timer
				final ActionListener updateAction = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						final String handContents = controller.userInput("SHOW_HAND");
						if (!handContents.equals("You do not have a hand yet!")) {
							connectionTimer.stop();
							// Wipe anything from before
							frame.getContentPane().removeAll();
							view = new PlaceBetsView(controller, frame);
						}
					}
				};
				connectionTimer = new Timer(DELAY, updateAction);
				timerList.add(connectionTimer);
				connectionTimer.start();
	}
}
