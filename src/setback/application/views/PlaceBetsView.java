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
import javax.swing.SwingConstants;
import javax.swing.Timer;

import setback.application.client.SetbackClientController;
import setback.common.PlayerNumber;

/**
 * This class is the GUI for Placing Bets
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class PlaceBetsView extends SetbackClientView {

	protected Timer neighborBetTimer;

	protected JLabel myBet;
	protected JLabel leftBet;
	protected JLabel centerBet;
	protected JLabel rightBet;

	protected JButton passButton;
	protected JButton twoButton;
	protected JButton threeButton;
	protected JButton fourButton;
	protected JButton fiveButton;
	protected JButton takeButton;

	/**
	 * Create the GUI for placing bets.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlaceBetsView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
		this.frame.revalidate();
		this.frame.repaint();
		view = this;
	}

	/**
	 * This function initializes the Place Bets screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	protected void initialize() {
		// Background and visibility
		super.initialize();
		final String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.NONE);
		displayNeighborHands(12);
		bettingInitialization();
		final PlayerNumber currentPlayer = PlayerNumber.valueOf(controller.userInput("GET_CURRENT_PLAYER").toUpperCase());
		if (currentPlayer.equals(controller.getLeft())) {
			waitOnPlayer(controller.getLeft());
		}
		else if (currentPlayer.equals(controller.getCenter())) {
			waitOnPlayer(controller.getCenter());
		}
		else if (currentPlayer.equals(controller.getRight())) {
			waitOnPlayer(controller.getRight());
		}
		else {
			// I am the current player
			toggleButtons(buttonList, true);
		}

	}

	/**
	 * This helper function creates the betting buttons and
	 * the betting resolved timer, but leaves the buttons
	 * as unclickable.
	 */
	private void bettingInitialization() {
		final String betString = controller.getMyNumber().toString() + " BET ";
		// Pass button
		passButton = new JButton("Pass");
		passButton.setBounds(GUI_WIDTH_CENTER - ((3 * GUI_PLACE_BET_BUTTON_WIDTH) + (2 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		passButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET PASS");
				if (response.startsWith(betString + "PASS")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU PASSED");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(passButton);
		// Two button
		twoButton = new JButton("Two");
		twoButton.setBounds(GUI_WIDTH_CENTER - ((2 * GUI_PLACE_BET_BUTTON_WIDTH) + (1 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		twoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET TWO");
				if (response.startsWith(betString + "TWO")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU BET TWO");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(twoButton);
		// Three button
		threeButton = new JButton("Three");
		threeButton.setBounds(GUI_WIDTH_CENTER - ((1 * GUI_PLACE_BET_BUTTON_WIDTH) + (0 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		threeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET THREE");
				if (response.startsWith(betString + "THREE")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU BET THREE");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(threeButton);
		// Four button
		fourButton = new JButton("Four");
		fourButton.setBounds(GUI_WIDTH_CENTER + ((0 * GUI_PLACE_BET_BUTTON_WIDTH) + (0 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		fourButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET FOUR");
				if (response.startsWith(betString + "FOUR")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU BET FOUR");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(fourButton);
		// Five button
		fiveButton = new JButton("Five");
		fiveButton.setBounds(GUI_WIDTH_CENTER + ((1 * GUI_PLACE_BET_BUTTON_WIDTH) + (1 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		fiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET FIVE");
				if (response.startsWith(betString + "FIVE")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU BET FIVE");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(fiveButton);
		// Take button
		takeButton = new JButton("Take");
		takeButton.setBounds(GUI_WIDTH_CENTER + ((2 * GUI_PLACE_BET_BUTTON_WIDTH) + (2 * GUI_SPACING_CONSTANT) + GUI_SPACING_CONSTANT_HALF),
				GUI_PLACE_BET_BUTTON_Y, GUI_PLACE_BET_BUTTON_HEIGHT, GUI_PLACE_BET_BUTTON_WIDTH);
		takeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String response = controller.userInput("PLACE_BET TAKE");
				if (response.startsWith(betString + "TAKE")) {
					toggleButtons(buttonList, false);
					myBet.setText("YOU TOOK THE BET");
					// Check for BETTING RESOLVED
					if (response.contains("BETTING RESOLVED")) {
						view = update(response);
					}
					else {
						waitOnPlayer(controller.getLeft());
					}
				}
			}
		});
		frame.getContentPane().add(takeButton);
		// Add the buttons to the list
		buttonList.add(passButton);
		buttonList.add(twoButton);
		buttonList.add(threeButton);
		buttonList.add(fourButton);
		buttonList.add(fiveButton);
		buttonList.add(takeButton);
		// Disable all of the buttons
		toggleButtons(buttonList, false);
		// My bet label
		myBet = new JLabel();
		myBet.setHorizontalAlignment(SwingConstants.CENTER);
		myBet.setBounds(GUI_WIDTH_CENTER,
				GUI_CARD_BOTTOM_Y - 2 * GUI_SPACING_CONSTANT, 
				GUI_PLACE_BET_STRING_LENGTH, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(myBet);
	}

	/**
	 * This helper function handles waiting for other
	 * people to place their bets.
	 * @param playerToWaitOn
	 */
	private void waitOnPlayer(final PlayerNumber playerToWaitOn) {
		toggleButtons(buttonList, false);
		final ActionListener singleBetAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				final String response = controller.userInput("NO_COMMAND");
				if (!response.equals("No command")) {
					neighborBetTimer.stop();
					if (playerToWaitOn == controller.getLeft()) {
						// Left label
						final String[] array = response.split(" ");
						leftBet = new JLabel(array[0] + " " + array[1] + " " + array[2]);
						leftBet.setBounds(GUI_CARD_LEFT_X,
								GUI_CARD_TOP_Y - GUI_SPACING_CONSTANT, 
								GUI_PLACE_BET_STRING_LENGTH, GUI_TEXT_HEIGHT);
						frame.getContentPane().add(leftBet);
						frame.repaint();
						waitOnPlayer(controller.getCenter());
					} else if (playerToWaitOn == controller.getCenter()) {
						// Center label
						final String[] array = response.split(" ");
						centerBet = new JLabel(array[0] + " " + array[1] + " " + array[2]);
						centerBet.setHorizontalAlignment(SwingConstants.CENTER);
						centerBet.setBounds(GUI_WIDTH_CENTER - GUI_CARD_WIDTH,
								GUI_CARD_TOP_Y - GUI_SPACING_CONSTANT, 
								GUI_PLACE_BET_STRING_LENGTH, GUI_TEXT_HEIGHT);
						frame.getContentPane().add(centerBet);
						frame.repaint();
						waitOnPlayer(controller.getRight());
					}
					else {
						// Right label
						final String[] array = response.split(" ");
						rightBet = new JLabel(array[0] + " " + array[1] + " " + array[2]);
						rightBet.setHorizontalAlignment(SwingConstants.RIGHT);
						rightBet.setBounds(GUI_CARD_RIGHT_X - 50,
								GUI_CARD_TOP_Y - GUI_SPACING_CONSTANT, 
								GUI_PLACE_BET_STRING_LENGTH, GUI_TEXT_HEIGHT);
						frame.getContentPane().add(rightBet);
						frame.repaint();
						// It's my turn to bet.
						toggleButtons(buttonList, true);
					}
					// Check for BETTING RESOLVED
					view = update(response);
				}
			}
		};
		neighborBetTimer = new Timer(DELAY, singleBetAction);
		timerList.add(neighborBetTimer);
		neighborBetTimer.start();
	}
}
