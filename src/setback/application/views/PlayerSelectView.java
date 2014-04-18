/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

/**
 * This class is the GUI for Player Selection.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PlayerSelectView extends SetbackClientView {
	
	private JButton playerOneButton;
	private JButton playerTwoButton;
	private JButton playerThreeButton;
	private JButton playerFourButton;

	private JLabel teamOneLabel;
	private JLabel teamTwoLabel;

	/**
	 * Create the GUI for player selection.  Just call the
	 * super constructor.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlayerSelectView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
		this.frame.revalidate();
		this.frame.repaint();
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
		playerOneButton.setBounds(GUI_PLAYER_SELECT_LEFT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_ONE");
				update(response);
			}
		});
		frame.getContentPane().add(playerOneButton);
		// Player Two Button
		playerTwoButton = new JButton("Player Two");
		playerTwoButton.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_TWO");
				update(response);
			}
		});
		frame.getContentPane().add(playerTwoButton);
		// Player Three Button
		playerThreeButton = new JButton("Player Three");
		playerThreeButton.setBounds(GUI_PLAYER_SELECT_LEFT_COLUMN_X, GUI_PLAYER_SELECT_BOTTOM_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerThreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_THREE");
				update(response);
			}
		});
		frame.getContentPane().add(playerThreeButton);
		// Player Four Button
		playerFourButton = new JButton("Player Four");
		playerFourButton.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_BOTTOM_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerFourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("REQUEST_PLAYER_FOUR");
				update(response);
			}
		});
		frame.getContentPane().add(playerFourButton);
		// Team One Label
		String teamOneString = "Team One";
		int teamOneSize = teamOneString.length() * UNICODE_SIZE_CONSTANT;
		teamOneLabel = new JLabel(teamOneString);
		teamOneLabel.setBounds(GUI_PLAYER_SELECT_LEFT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y - GUI_SPACING_CONSTANT, teamOneSize, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamOneLabel);
		// Team Two Label
		String teamTwoString = "Team Two";
		int teamTwoSize = teamTwoString.length() * UNICODE_SIZE_CONSTANT;
		teamTwoLabel = new JLabel(teamTwoString);
		teamTwoLabel.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y - GUI_SPACING_CONSTANT, teamTwoSize, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamTwoLabel);
	}
}