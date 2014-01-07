/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import setback.common.PlayerNumber;

/**
 * This view will handle selecting trump.  It will
 * display the winning bettor/bet to all of the
 * players, but only the winner will be able to
 * click any of the buttons.
 * @author Michael Burns
 * @version Jan 7, 2014
 */
public class SelectTrumpView extends SetbackClientView {

	private Timer trumpSelectionTimer;

	private JLabel winningBet;

	private JButton spadesButton;
	private JButton heartsButton;
	private JButton clubsButton;
	private JButton diamondsButton;

	/**
	 * Create the GUI for selecting trump.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public SelectTrumpView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Select Trump screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Winning bet label
		winningBet = new JLabel(controller.userInput("GET_WINNING_BET"));
		winningBet.setBounds(340, 275, 250, 40);
		winningBet.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(winningBet);
		// Hands
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents);
		displayNeighborHands();
		// Set up the buttons
		final String trumpString = controller.getMyNumber().toString() + " SELECTED ";
		// Spades button
		spadesButton = new JButton("Spades");
		spadesButton.setBounds(170, 250, 125, 75);
		spadesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("SELECT_TRUMP SPADES");
				if (response.startsWith(trumpString + "SPADES")) {
					toggleButtons(buttonList, false);
					// Check for TRUMP SELECTED
					update(response);
				}
			}
		});
		frame.getContentPane().add(spadesButton);
		// Hearts button
		heartsButton = new JButton("Hearts");
		heartsButton.setBounds(300, 250, 125, 75);
		heartsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("SELECT_TRUMP HEARTS");
				if (response.startsWith(trumpString + "HEARTS")) {
					toggleButtons(buttonList, false);
					// Check for TRUMP SELECTED
					update(response);
				}
			}
		});
		frame.getContentPane().add(heartsButton);
		// Clubs button
		clubsButton = new JButton("Clubs");
		clubsButton.setBounds(430, 250, 125, 75);
		clubsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("SELECT_TRUMP CLUBS");
				if (response.startsWith(trumpString + "CLUBS")) {
					toggleButtons(buttonList, false);
					// Check for TRUMP SELECTED
					update(response);
				}
			}
		});
		frame.getContentPane().add(clubsButton);
		// Diamonds button
		diamondsButton = new JButton("Diamonds");
		diamondsButton.setBounds(560, 250, 125, 75);
		diamondsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = controller.userInput("SELECT_TRUMP DIAMONDS");
				if (response.startsWith(trumpString + "DIAMONDS")) {
					toggleButtons(buttonList, false);
					// Check for TRUMP SELECTED
					update(response);
				}
			}
		});
		frame.getContentPane().add(diamondsButton);
		// Add the buttons to the list
		buttonList.add(spadesButton);
		buttonList.add(heartsButton);
		buttonList.add(clubsButton);
		buttonList.add(diamondsButton);
		// Get the current player
		PlayerNumber currentPlayer = PlayerNumber.valueOf(controller.userInput("GET_CURRENT_PLAYER"));
		// This is the current player, enable the buttons
		if (currentPlayer.equals(controller.getMyNumber())) {
			toggleButtons(buttonList, true);
		}
		// This is not the current player, enable the timer
		else {
			toggleButtons(buttonList, false);
			ActionListener updateAction = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String response = controller.userInput("NO_COMMAND");
					if (!response.equals("NO_COMMAND")) {
						trumpSelectionTimer.stop();
						// Just move along to the discarding screen, trump
						// will be displayed there as well.
						update(response);
					}
				}
			};
			trumpSelectionTimer = new Timer(DELAY, updateAction);
			trumpSelectionTimer.start();
		}
	}
}
