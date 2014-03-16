/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import setback.application.client.SetbackClientController;
import setback.common.PlayerNumber;

/**
 * This class is a subclass of the PlayCardsView class.  It handles
 * playing the final trick of a round, and properly gets us to the
 * RoundScoreView at the end of the trick.
 * @author Michael Burns
 * @version Mar 16, 2014
 */
public class PlayCardsFinalTrickView extends PlayCardsView {

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
	public PlayCardsFinalTrickView(SetbackClientController controller, JFrame frame,
			String myCardName, String leftCardName, String centerCardName,
			String rightCardName) {
		super(controller, frame, myCardName, leftCardName, centerCardName,
				rightCardName);
	}

	/**
	 * Overrides the function from the PlayCardsView.
	 * Instead of making a new PlayCardsView when a card is played,
	 * it creates a new PlayCardsFinalTrickView, because it is the
	 * final trick of the round.
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
						leftCardName = cardName;
						if (!checkForEndOfTrick()) {
							new PlayCardsFinalTrickView(controller, frame, myCardName, cardName, centerCardName, rightCardName);
						}
					}
					else if (cardPlayer.equals(controller.getCenter())) {
						centerCardName = cardName;
						if (!checkForEndOfTrick()) {
							new PlayCardsFinalTrickView(controller, frame, myCardName, leftCardName, cardName, rightCardName);
						}
					}
					else {
						rightCardName = cardName;
						if (!checkForEndOfTrick()) {
							new PlayCardsFinalTrickView(controller, frame, myCardName, leftCardName, centerCardName, cardName);
						}
					}
				}
			}
		};
		cardTimer = new Timer(DELAY, cardPlayedAction);
		timerList.add(cardTimer);
		cardTimer.start();
	}	

	/**
	 * Overrides the function from the PlayCardsView.
	 * Instead of making a new PlayCardsView at the end of the trick,
	 * it goes to the RoundScoreView, because the whole round should be over.
	 */
	protected boolean checkForEndOfTrick() {
		// If the trick is over, wait for DELAY then make a new GUI
		if (myCardName != null && leftCardName != null && centerCardName != null && rightCardName != null) {
			ActionListener pauseAction = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (unpauseToggle) {
						pauseTimer.stop();
						// Just in case we have a race condition
						stopTimers();
						new RoundScoreView(controller, frame);
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
