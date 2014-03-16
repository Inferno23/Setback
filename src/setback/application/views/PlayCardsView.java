/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import setback.application.client.ListenerEnum;
import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;
import setback.common.PlayerNumber;

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

	protected Timer cardTimer;
	protected Timer pauseTimer;

	protected int numCards;
	protected String myCardName;
	protected String leftCardName;
	protected String centerCardName;
	protected String rightCardName;

	/**
	 * Create the GUI for playing cards, call the super constructor.
	 * Initialization must be done in the constructor, because it requires knowledge of the
	 * previously played cards, which won't get passed to initialize().
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 * @param myCardName The name of the card that I have played in this trick.
	 * @param leftCardName The name of the card that left has played in this trick.
	 * @param centerCardName The name of the card that center has played in this trick.
	 * @param rightCardName The name of the card that right has played in this trick.
	 */
	public PlayCardsView(SetbackClientController controller, JFrame frame, String myCardName, String leftCardName, String centerCardName, String rightCardName) {
		// Call the super constructor to get basic initialization
		super(controller, frame);
		// Set some fields
		this.myCardName = myCardName;
		this.leftCardName = leftCardName;
		this.centerCardName = centerCardName;
		this.rightCardName = rightCardName;
		unpauseToggle = false;
		// Set up the current player label
		currentPlayerLabel = new JLabel();
		currentPlayerLabel.setBounds(GUI_WIDTH_CENTER, GUI_PLAY_CARDS_STRING_Y, 
				GUI_PLAY_CARDS_STRING_LENGTH, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(currentPlayerLabel);
		// Get my hand and display it
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.PLAY);
		// Find the number of cards that are in the hand
		this.numCards = handContents.split("\t").length - 1;
		displayPlayedCards();
		displayCorrectedNeighborHands();

		// Check if the all four cards have been played
		if (!checkForEndOfTrick()) {

			// Find the current player
			PlayerNumber currentPlayer = PlayerNumber.valueOf(controller.userInput("GET_CURRENT_PLAYER").toUpperCase());
			// If I am the current player we won't call waitForAnyCard.
			if (currentPlayer.equals(controller.getMyNumber())) {
				currentPlayerLabel.setText("Current Player: Me");
			}
			// If I am not the current player we will call waitForAnyCard
			else {
				if (currentPlayer.equals(controller.getLeft())) {
					currentPlayerLabel.setText("Current Player: Left");
				}
				else if (currentPlayer.equals(controller.getCenter())) {
					currentPlayerLabel.setText("Current Player: Center");
				}
				else {
					currentPlayerLabel.setText("Current Player: Right");
				}
				// Now wait for a card to be played
				waitForAnyCard();
			}
		}
	}

	/**
	 * Helper function that displays the previously played cards.
	 */
	private void displayPlayedCards() {
		if (myCardName != null) {
			ImageIcon cardIcon = factory.createCard(myCardName);
			myCard = new JLabel(cardIcon);
			myCard.setBounds(GUI_WIDTH_CENTER, GUI_CARD_BOTTOM_Y - GUI_CARD_PLAYED_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			frame.getContentPane().add(myCard);
		}
		if (leftCardName != null) {
			ImageIcon cardIcon = factory.createCard(leftCardName);
			leftCard = new JLabel(cardIcon);
			leftCard.setBounds(GUI_CARD_LEFT_X + GUI_CARD_PLAYED_SHIFT, GUI_HEIGHT_CENTER, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			frame.getContentPane().add(leftCard);
		}
		if (centerCardName != null) {
			ImageIcon cardIcon = factory.createCard(centerCardName);
			centerCard = new JLabel(cardIcon);
			centerCard.setBounds(GUI_WIDTH_CENTER, GUI_CARD_TOP_Y + GUI_CARD_PLAYED_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			frame.getContentPane().add(centerCard);
		}
		if (rightCardName != null) {
			ImageIcon cardIcon = factory.createCard(rightCardName);
			rightCard = new JLabel(cardIcon);
			rightCard.setBounds(GUI_CARD_RIGHT_X - GUI_CARD_PLAYED_SHIFT, GUI_HEIGHT_CENTER, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			frame.getContentPane().add(rightCard);
		}
	}

	/**
	 * Helper function that displays the correct number of cards in the neighbors' hands.
	 */
	private void displayCorrectedNeighborHands() {
		// I haven't played yet, so numCards is for hands that haven't played
		if (myCardName == null) {
			// Left hasn't played yet
			if (leftCardName == null) {
				displayLeftHand(numCards);
			}
			// Left has played
			else {
				displayLeftHand(numCards - 1);
			}
			// Center hasn't played yet
			if (centerCardName == null) {
				displayCenterHand(numCards);
			}
			// Center has played
			else {
				displayCenterHand(numCards - 1);
			}
			// Right hasn't played yet
			if (rightCardName == null) {
				displayRightHand(numCards);
			}
			// Right has played
			else {
				displayRightHand(numCards - 1);
			}
		}
		// I have already played a card, so numCards is for hands that have played
		else {
			// Left hasn't played yet
			if (leftCardName == null) {
				displayLeftHand(numCards + 1);
			}
			// Left has played
			else {
				displayLeftHand(numCards);
			}
			// Center hasn't played yet
			if (centerCardName == null) {
				displayCenterHand(numCards + 1);
			}
			// Center has played
			else {
				displayCenterHand(numCards);
			}
			// Right hasn't played yet
			if (rightCardName == null) {
				displayRightHand(numCards + 1);
			}
			// Right has played
			else {
				displayRightHand(numCards);
			}
		}
	}

	/**
	 * Override of the addListener function from SetbackClientView.  This copy
	 * defaults to using the original function except in the case of PLAY cards,
	 * which is explicitly controlled here because this is the GUI that involves
	 * playing cards.
	 */
	protected void addListener(ListenerEnum type, final JLabel card, final String cardName) {
		switch(type) {
		case PLAY:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					// Try to play the card
					String response = controller.userInput("PLAY_CARD " + cardName);
					// We expect it to get played
					String desired = controller.getMyNumber() + " PLAYED " + cardName;
					// If we played the card properly
					if (response.startsWith(desired)) {
						// We have more cards to play
						if (numCards > 1) {
							new PlayCardsView(controller, frame, cardName, leftCardName, centerCardName, rightCardName);
						}
						// This is our last card, so go the subclass that handles the end of the round
						else {
							new PlayCardsFinalTrickView(controller, frame, cardName, leftCardName, centerCardName, rightCardName);
						}
					}
					else {
						// TODO: Show error below my cards
					}
				}
			});
		default:
			// Otherwise just use the super version.
			super.addListener(type, card, cardName);
		}
	}

	/**
	 * This helper function polls the server to see if
	 * another player has played a card.  If it detects
	 * that a card has been played, it displays it in
	 * front of the appropriate player, and updates the
	 * current player string.
	 */
	protected void waitForAnyCard() {
		ActionListener cardPlayedAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String response = controller.userInput("NO_COMMAND");
				if (!response.equals("No command")) {
					// Stop the timer
					cardTimer.stop();
					// Split the response
					String array[] = response.split(" ");
					PlayerNumber cardPlayer = PlayerNumber.valueOf(array[0]);
					String cardName = array[2];
					if (cardPlayer.equals(controller.getLeft())) {
						new PlayCardsView(controller, frame, myCardName, cardName, centerCardName, rightCardName);
					}
					else if (cardPlayer.equals(controller.getCenter())) {
						new PlayCardsView(controller, frame, myCardName, leftCardName, cardName, rightCardName);
					}
					else {
						new PlayCardsView(controller, frame, myCardName, leftCardName, centerCardName, cardName);
					}
				}
			}
		};
		cardTimer = new Timer(DELAY, cardPlayedAction);
		timerList.add(cardTimer);
		cardTimer.start();
	}	

	/**
	 * This helper function checks the response from the server
	 * to determine if the trick has ended.  If the trick has ended,
	 * it will leave the GUI in stasis for DELAY seconds, then move
	 * on to the next trick.
	 * @param serverResponse The response from the server the last
	 * time that a card was played.
	 * @return True if the trick is ending, else false.
	 */
	protected boolean checkForEndOfTrick() {
		// If the trick is over, wait for DELAY then make a new GUI
		if (myCardName != null && leftCardName != null && centerCardName != null && rightCardName != null) {
			ActionListener pauseAction = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (unpauseToggle) {
						pauseTimer.stop();
						new PlayCardsView(controller, frame, null, null, null, null);
					}
					else {
						unpauseToggle = true;
					}
				}
			};
			pauseTimer = new Timer(DELAY, pauseAction);
			timerList.add(pauseTimer);
			pauseTimer.start();
			return true;
		}
		else {
			return false;
		}
	}
}
