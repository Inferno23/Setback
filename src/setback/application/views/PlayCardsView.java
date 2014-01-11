/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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

	private Timer cardTimer;
	private Timer pauseTimer;
	
	private boolean unpauseToggle;
	
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
		// Hands
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.PLAY);
		displayNeighborHands(9);
		findCurrentPlayer();	
		unpauseToggle = false;
	}
	
	/**
	 * This helper function handles waiting for other
	 * people to play cards
	 * @param playerToWaitOn
	 */
	private void waitForCard(final PlayerNumber playerToWaitOn) {
		toggleButtons(buttonList, false);
		ActionListener cardPlayedAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String response = controller.userInput("NO_COMMAND");
				if (response.contains("TRICK STARTED")) {
					// It is a new trick, determine whose turn it is
					cardTimer.stop();
					pause();
				}
				else if (!response.equals("No command")) {
					// Still in the middle of a trick, wait for cards
					cardTimer.stop();
					if (playerToWaitOn == controller.getLeft()) {
						// Left label
						String array[] = response.split(" ");
						leftCard = new JLabel(factory.createCard(array[2]));
						leftCard.setBounds(170, 185, CARD_WIDTH, CARD_HEIGHT);
						frame.getContentPane().add(leftCard);
						frame.repaint();
						waitForCard(controller.getCenter());
					} else if (playerToWaitOn == controller.getCenter()) {
						// Center label
						String array[] = response.split(" ");
						centerCard = new JLabel(factory.createCard(array[2]));
						centerCard.setHorizontalAlignment(SwingConstants.CENTER);
						centerCard.setBounds(340, 160, CARD_WIDTH, CARD_HEIGHT);
						frame.getContentPane().add(centerCard);
						frame.repaint();
						waitForCard(controller.getRight());
					}
					else {
						// Right label
						String array[] = response.split(" ");
						rightCard = new JLabel(factory.createCard(array[2]));
						rightCard.setHorizontalAlignment(SwingConstants.RIGHT);
						rightCard.setBounds(510, 185, CARD_WIDTH, CARD_HEIGHT);
						frame.getContentPane().add(rightCard);
						frame.repaint();
					}
					// Check for the end of the trick
					update(response);
				}
			}
		};
		cardTimer = new Timer(DELAY, cardPlayedAction);
		cardTimer.start();
	}

	/**
	 * Helper function that determines if this player is the
	 * current player, and if not, waits for the correct
	 * player to play a card.
	 */
	private void findCurrentPlayer() {
		// Check if this is the active player
		PlayerNumber currentPlayer = PlayerNumber.valueOf(controller.userInput("GET_CURRENT_PLAYER").toUpperCase());
		if (currentPlayer.equals(controller.getLeft())) {
			waitForCard(controller.getLeft());
		}
		else if (currentPlayer.equals(controller.getCenter())) {
			waitForCard(controller.getCenter());
		}
		else if (currentPlayer.equals(controller.getRight())) {
			waitForCard(controller.getRight());
		}
		else {
			// I am the current player
		}
	}
	
	/**
	 * Helper function that waits for two seconds, then
	 * clears the played cards.
	 */
	private void pause() {
		ActionListener pauseAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (unpauseToggle) {
					pauseTimer.stop();
					unpauseToggle = false;
					frame.getContentPane().remove(myCard);
					frame.getContentPane().remove(leftCard);
					frame.getContentPane().remove(centerCard);
					frame.getContentPane().remove(rightCard);
					frame.repaint();
					frame.getContentPane().remove(myCard);
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
