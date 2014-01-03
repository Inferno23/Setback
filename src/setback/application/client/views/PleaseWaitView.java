/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

/**
 * This class is the GUI for Waiting to start.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PleaseWaitView extends SetbackClientView {

	private JLabel pleaseWait;
	private JButton beginGame;
	
	/**
	 * Create the GUI for placing bets.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PleaseWaitView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Please Wait screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Please wait message
		pleaseWait = new JLabel("Please wait for the other players to connect.");
		pleaseWait.setBounds(350, 250, 300, 20);
		frame.getContentPane().add(pleaseWait);
		// Begin Game Button
		beginGame = new JButton("Begin game");
		beginGame.setBounds(400, 275, 200, 100);
		beginGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String handContents = controller.userInput("SHOW_HAND");
				while (handContents.equals("You do not have a hand yet!")) {
					try {
						Thread.sleep(2000);
						handContents = controller.userInput("SHOW_HAND");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				update("BEGIN GAME");
			}
		});
		frame.getContentPane().add(beginGame);
	}
}
