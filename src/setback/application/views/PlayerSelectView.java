/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import setback.application.client.SetbackClientController;
import setback.common.PlayerNumber;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class is the GUI for Player Selection.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PlayerSelectView extends SetbackClientView {
	
	protected JButton playerOneButton;
	protected JButton playerTwoButton;
	protected JButton playerThreeButton;
	protected JButton playerFourButton;

	protected JLabel teamOneLabel;
	protected JLabel teamTwoLabel;
	
	protected JLabel errorLabel;

	/**
	 * Create the GUI for player selection.  Just call the
	 * super constructor.
	 * @param controller The SetbackClientControllerImpl that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlayerSelectView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
		this.frame.revalidate();
		this.frame.repaint();
		view = this;
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
		playerOneButton.addActionListener(createPlayerRequestListener(PlayerNumber.PLAYER_ONE));
		frame.getContentPane().add(playerOneButton);
		// Player Two Button
		playerTwoButton = new JButton("Player Two");
		playerTwoButton.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerTwoButton.addActionListener(createPlayerRequestListener(PlayerNumber.PLAYER_TWO));
		frame.getContentPane().add(playerTwoButton);
		// Player Three Button
		playerThreeButton = new JButton("Player Three");
		playerThreeButton.setBounds(GUI_PLAYER_SELECT_LEFT_COLUMN_X, GUI_PLAYER_SELECT_BOTTOM_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerThreeButton.addActionListener(createPlayerRequestListener(PlayerNumber.PLAYER_THREE));
		frame.getContentPane().add(playerThreeButton);
		// Player Four Button
		playerFourButton = new JButton("Player Four");
		playerFourButton.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_BOTTOM_ROW_Y, GUI_PLAYER_SELECT_BUTTON_WIDTH, GUI_PLAYER_SELECT_BUTTON_HEIGHT);
		playerFourButton.addActionListener(createPlayerRequestListener(PlayerNumber.PLAYER_FOUR));
		frame.getContentPane().add(playerFourButton);
		// Team One Label
		final String teamOneString = "Team One";
		final int teamOneSize = teamOneString.length() * UNICODE_SIZE_CONSTANT;
		teamOneLabel = new JLabel(teamOneString);
		teamOneLabel.setBounds(GUI_PLAYER_SELECT_LEFT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y - GUI_SPACING_CONSTANT, teamOneSize, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamOneLabel);
		// Team Two Label
		final String teamTwoString = "Team Two";
		final int teamTwoSize = teamTwoString.length() * UNICODE_SIZE_CONSTANT;
		teamTwoLabel = new JLabel(teamTwoString);
		teamTwoLabel.setBounds(GUI_PLAYER_SELECT_RIGHT_COLUMN_X, GUI_PLAYER_SELECT_TOP_ROW_Y - GUI_SPACING_CONSTANT, teamTwoSize, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamTwoLabel);
		// Error Message Label
		final String errorLabelString = "That player has already been selected.";
		final int errorLabelSize = errorLabelString.length() * UNICODE_SIZE_CONSTANT;
		errorLabel = new JLabel(errorLabelString);
		errorLabel.setBounds(GUI_WIDTH_CENTER - errorLabelSize / 2, GUI_PLAYER_SELECT_ERROR_Y, errorLabelSize, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(errorLabel);
		errorLabel.setVisible(false);
	}

  /**
   * Helper method to create the action listener for the
   * player request buttons.
   * @param playerNumber The player number to request.
   * @return The listener for the button.
   */
  private ActionListener createPlayerRequestListener(PlayerNumber playerNumber) {
    return listener -> {
      final String response = controller.requestPlayer(playerNumber);
      view = update(response);
      if (response.endsWith("rejected")) {
        errorLabel.setVisible(true);
      }
    };
  }
}