/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

/**
 * This class is the GUI for Player Selection.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PlayerSelectView extends SetbackClientView {

	private static final long serialVersionUID = 2L;
	
	private JButton playerOneButton;
	private JButton playerTwoButton;
	private JButton playerThreeButton;
	private JButton playerFourButton;

	/**
	 * Create the GUI for player selection.  Just call the
	 * super constructor.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 */
	public PlayerSelectView(SetbackClientController controller) {
		super(controller);
	}

	/**
	 * This function initializes the Player Selection screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Player One Button
		playerOneButton = new JButton("Player One");
		playerOneButton.setBounds(240, 190, 200, 100);
		playerOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_ONE");
				update(response);
			}
		});
		this.getContentPane().add(playerOneButton);
		// Player Two Button
		playerTwoButton = new JButton("Player Two");
		playerTwoButton.setBounds(460, 190, 200, 100);
		playerTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_TWO");
				update(response);
			}
		});
		this.getContentPane().add(playerTwoButton);
		// Player Three Button
		playerThreeButton = new JButton("Player Three");
		playerThreeButton.setBounds(240, 310, 200, 100);
		playerThreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_THREE");
				update(response);
			}
		});
		this.getContentPane().add(playerThreeButton);
		// Player Four Button
		playerFourButton = new JButton("Player Four");
		playerFourButton.setBounds(460, 310, 200, 100);
		playerFourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_FOUR");
				update(response);
			}
		});
		this.getContentPane().add(playerFourButton);
	}
}
