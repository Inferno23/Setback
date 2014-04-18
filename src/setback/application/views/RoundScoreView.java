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
import setback.common.PlayerNumber;

/**
 * This class will display the scores for each team until the
 * player clicks a continue button, which will take them to the
 * next hand.
 * @author Michael Burns
 * @version Mar 16, 2014
 */
public class RoundScoreView extends SetbackClientView {

	private JLabel teamLabel;
	private JLabel teamOneScore;
	private JLabel teamTwoScore;
	private JButton continueButton;

	/**
	 * Create the GUI for displaying the score after a round of Setback.
	 * It will contain the team scores and a continue button, which
	 * will take the player to the next hand.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public RoundScoreView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
		this.frame.revalidate();
		this.frame.repaint();
	}

	/**
	 * This function initializes the Round Score screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	protected void initialize() {
		// Background and visibility
		super.initialize();
		final String teamOne = "TEAM ONE: " + controller.userInput("GET_TEAM_ONE_SCORE");
		final String teamTwo = "TEAM TWO: " + controller.userInput("GET_TEAM_TWO_SCORE");
		final int teamLength = teamOne.length() * UNICODE_SIZE_CONSTANT;
		final PlayerNumber myNumber = controller.getMyNumber();
		String teamString = "FYI: You're on ";

		// Team One
		if (myNumber.equals(PlayerNumber.PLAYER_ONE) || myNumber.equals(PlayerNumber.PLAYER_THREE)) {
			teamString = teamString + "TEAM ONE";
		}
		// Team Two
		else {
			teamString = teamString + "TEAM TWO";
		}
		final int teamStringLength = teamString.length() * UNICODE_SIZE_CONSTANT;

		// Which team you're on
		teamLabel = new JLabel();
		teamLabel.setText(teamString);
		teamLabel.setBounds(GUI_WIDTH_CENTER, GUI_HEIGHT_CENTER - GUI_SPACING_CONSTANT, 
				teamStringLength, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamLabel);
		// Team one score
		teamOneScore = new JLabel();
		teamOneScore.setText(teamOne);
		teamOneScore.setBounds(GUI_WIDTH_CENTER - teamLength - GUI_SPACING_CONSTANT_HALF,
				GUI_HEIGHT_CENTER, teamLength, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamOneScore);
		// Team two score
		teamTwoScore = new JLabel();
		teamTwoScore.setText(teamTwo);
		teamTwoScore.setBounds(GUI_WIDTH_CENTER + GUI_SPACING_CONSTANT_HALF,
				GUI_HEIGHT_CENTER, teamLength, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(teamTwoScore);
		// Continue button
		continueButton = new JButton("Continue");
		continueButton.setBounds(GUI_WIDTH_CENTER - (GUI_ROUND_SCORE_BUTTON_WIDTH / 2),
				GUI_HEIGHT_CENTER + GUI_SPACING_CONSTANT, GUI_ROUND_SCORE_BUTTON_WIDTH,
				GUI_ROUND_SCORE_BUTTON_HEIGHT);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Continue to the next hand
				new PlaceBetsView(controller, frame);
			}
		});
		frame.getContentPane().add(continueButton);
	}
}
