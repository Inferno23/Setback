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

	int numCards;
	
	/**
	 * Create the GUI for playing cards.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public PlayCardsView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Playing Cards screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Set up the current player label
		currentPlayerLabel = new JLabel();
		currentPlayerLabel.setBounds(GUI_WIDTH_CENTER, GUI_PLAY_CARDS_STRING_Y, 
				GUI_PLAY_CARDS_STRING_LENGTH, GUI_TEXT_HEIGHT);
		frame.getContentPane().add(currentPlayerLabel);
		// Get my hand and display it
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.PLAY);
		// Find the number of cards that are in the hand
		numCards = handContents.split("\t").length - 1;
		// Show my neighbors with the same number of cards
		displayNeighborHands(numCards);
		unpauseToggle = false;
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
						card.setBounds(GUI_WIDTH_CENTER, GUI_CARD_BOTTOM_Y - GUI_CARD_PLAYED_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
						// I played that card!
						currentPlayerLabel.setText("Current Player: Left");
						waitForAnyCard();
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
					ImageIcon cardIcon = factory.createCard(array[2]);
					if (cardPlayer.equals(controller.getLeft())) {
						leftCard = new JLabel(cardIcon);
						leftCard.setBounds(GUI_CARD_LEFT_X + GUI_CARD_PLAYED_SHIFT, GUI_HEIGHT_CENTER, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
						frame.getContentPane().add(leftCard);
						currentPlayerLabel.setText("Current Player: Center");
						// Remove the old left cards
						for (int index = 0; index < numCards; index++) {
							frame.getContentPane().remove(leftCards[index]);
						}
						// Redraw the correct number of cards
						displayLeftHand(numCards - 1);
						// Repaint the frame
						frame.repaint();
						checkForEndOfTrick(response);
						waitForAnyCard();
					}
					else if (cardPlayer.equals(controller.getCenter())) {
						centerCard = new JLabel(cardIcon);
						centerCard.setBounds(GUI_WIDTH_CENTER, GUI_CARD_TOP_Y + GUI_CARD_PLAYED_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
						frame.getContentPane().add(centerCard);
						currentPlayerLabel.setText("Current Player: Right");
						// Remove the old center cards
						for (int index = 0; index < numCards; index++) {
							frame.getContentPane().remove(centerCards[index]);
						}
						// Redraw the correct number of cards
						displayCenterHand(numCards - 1);
						// Repaint the frame
						frame.repaint();
						checkForEndOfTrick(response);
						waitForAnyCard();
					}
					else {
						rightCard = new JLabel(cardIcon);
						rightCard.setBounds(GUI_CARD_RIGHT_X - GUI_CARD_PLAYED_SHIFT, GUI_HEIGHT_CENTER, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
						frame.getContentPane().add(rightCard);
						currentPlayerLabel.setText("Current Player: Me");
						// Remove the old right cards
						for (int index = 0; index < numCards; index++) {
							frame.getContentPane().remove(rightCards[index]);
						}
						// Redraw the correct number of cards
						displayRightHand(numCards - 1);
						// Repaint the frame
						frame.repaint();
						checkForEndOfTrick(response);
					}

				}
			}
		};
		cardTimer = new Timer(DELAY, cardPlayedAction);
		cardTimer.start();
	}	

	/**
	 * This helper function checks the response from the server
	 * to determine if the trick has ended.  If the trick has ended,
	 * it will leave the GUI in stasis for DELAY seconds, then move
	 * on to the next trick.
	 * @param serverResponse The response from the server the last
	 * time that a card was played.
	 */
	private void checkForEndOfTrick(String serverResponse) {
		// If the trick is over, wait for DELAY then make a new GUI
		if (serverResponse.contains("WON TRICK")) {
			ActionListener pauseAction = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (unpauseToggle) {
						pauseTimer.stop();
						new PlayCardsView(controller, frame);
					}
					else {
						unpauseToggle = true;
					}
				}
			};
			pauseTimer = new Timer(DELAY, pauseAction);
			pauseTimer.start();
		}
	}
}
