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

import setback.application.client.ListenerEnum;
import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientView;

/**
 * This view will handle discarding cards.  It will
 * display the trump suit to all of the players.
 * Clicking a card will move it upwards, making
 * it a candidate for discarding.  Once the user
 * selects three cards, they can hit the discard button.
 * @author Michael Burns
 * @version Jan 8, 2014
 */
public class DiscardCardsView extends SetbackClientView {

	protected Timer discardTimer;
	protected Timer allDiscardTimer;

	private JLabel trumpLabel;

	private JButton discardButton;

	/**
	 * Create the GUI for discarding cards.  Just call the
	 * super constructor.
	 * @param controller  The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public DiscardCardsView(SetbackClientController controller, JFrame frame) {
		super(controller, frame);
	}

	/**
	 * This function initializes the Discard Cards screen.
	 * The background and visibility is handled by calling
	 * the super version of initialize.
	 */
	public void initialize() {
		// Background and visibility
		super.initialize();
		// Trump label
		trumpLabel = new JLabel("TRUMP IS " + controller.userInput("GET_TRUMP"));
		trumpLabel.setBounds(GUI_WIDTH_CENTER, GUI_HEIGHT_CENTER - (3 * GUI_SPACING_CONSTANT),
				GUI_DISCARD_CARDS_STRING_LENGTH, GUI_TEXT_HEIGHT);
		trumpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(trumpLabel);
		// Hands
		String handContents = controller.userInput("SHOW_HAND");
		displayHand(handContents, ListenerEnum.SHIFT_UP);
		displayNeighborHands(12);
		// Discard button
		discardButton = new JButton("Discard");
		discardButton.setBounds(GUI_WIDTH_CENTER - (GUI_DISCARD_CARDS_BUTTON_WIDTH / 2), GUI_DISCARD_CARDS_BUTTON_Y,
				GUI_DISCARD_CARDS_BUTTON_WIDTH, GUI_DISCARD_CARDS_BUTTON_HEIGHT);
		discardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (discardList.size() == 3) {
					discardTimer.stop();
					String response = controller.userInput("DISCARD_CARDS "
							+ discardList.get(0) + " " + discardList.get(1) + " " + discardList.get(2));
					// Double check that we discarded properly
					if (response.contains(controller.getMyNumber().toString() + " DISCARDED")) {
						discardButton.setEnabled(false);
						// If we are the last person to discard, then everything else is taken care of later
						if (response.contains("TRICK STARTED")) {
							update(response);
						}
						else {
							// We were not the last, so do it all ourselves
							update(response);
							for (JLabel card : cardList) {
								frame.getContentPane().remove(card);
							}
							cardList.clear();
							String handContents = controller.userInput("SHOW_HAND");
							displayHand(handContents, ListenerEnum.NONE);
							// Check that everyone has discarded
							ActionListener allDiscardAction = new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									String response = controller.userInput("NO_COMMAND");
									if (response.contains("TRICK STARTED")) {
										allDiscardTimer.stop();
										update(response);
									}
								}
							};
							allDiscardTimer = new Timer(DELAY, allDiscardAction);
							timerList.add(allDiscardTimer);
							allDiscardTimer.start();
						}
					}
				}
			}
		});
		discardButton.setEnabled(false);
		frame.getContentPane().add(discardButton);
		// Check the cardList
		ActionListener myDiscardAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (discardList.size() == 3) {
					discardButton.setEnabled(true);
				}
				else {
					discardButton.setEnabled(false);
				}
			}
		};
		discardTimer = new Timer(DELAY, myDiscardAction);
		timerList.add(discardTimer);
		discardTimer.start();
	}
}
