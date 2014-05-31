/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientControllerMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the PlayCardsView.  It will prove that cards
 * appear properly, can only be played at the right time, and tricks
 * work properly.
 * @author Michael Burns
 * @version May 31, 2014
 */
public class PlayCardsViewTest {

	private PlayCardsView view;
	private SetbackClientControllerMock controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void leftCurrentPlayerLabelTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		assertTrue(view.currentPlayerLabel.isVisible());
		assertEquals("Current Player: Left", view.currentPlayerLabel.getText());
	}
	
	@Test
	public void centerCurrentPlayerLabelTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_FOUR);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		assertTrue(view.currentPlayerLabel.isVisible());
		assertEquals("Current Player: Center", view.currentPlayerLabel.getText());
	}
	
	@Test
	public void rightCurrentPlayerLabelTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_THREE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		assertTrue(view.currentPlayerLabel.isVisible());
		assertEquals("Current Player: Right", view.currentPlayerLabel.getText());
	}
	
	@Test
	public void selfCurrentPlayerLabelTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		assertTrue(view.currentPlayerLabel.isVisible());
		assertEquals("Current Player: Me", view.currentPlayerLabel.getText());
	}
	
	@Test
	public void selfAndAllOthersPlayedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades");
		assertTrue(view.myCard.isVisible());
		assertTrue(view.leftCard.isVisible());
		assertTrue(view.centerCard.isVisible());
		assertTrue(view.rightCard.isVisible());
	}
	
	@Test
	public void notSelfButOthersPlayedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades");
		assertNull(view.myCard);
		assertTrue(view.leftCard.isVisible());
		assertTrue(view.centerCard.isVisible());
		assertTrue(view.rightCard.isVisible());
	}
	
	@Test
	public void selfButNoOthersPlayerTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, "Ace-of-Spades", null, null, null);
		assertTrue(view.myCard.isVisible());
		assertNull(view.leftCard);
		assertNull(view.centerCard);
		assertNull(view.rightCard);
	}
	
	@Test
	public void playerOnePlaysCardTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		controller.currentPlayer = PlayerNumber.PLAYER_ONE;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		// Check that we're set up
		assertEquals("Current Player: Me", view.currentPlayerLabel.getText());
		assertNull(view.myCard);
		assertNull(view.leftCard);
		assertNull(view.centerCard);
		assertNull(view.rightCard);
		// Click the Ace of Spades
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		assertTrue(view.getView().myCard.isVisible());
	}
	
	@Test
	public void wrongPlayerTriesToPlayTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, null, null, null, null);
		// Check that we're set up
		assertEquals("Current Player: Left", view.currentPlayerLabel.getText());
		assertNull(view.myCard);
		assertNull(view.leftCard);
		assertNull(view.centerCard);
		assertNull(view.rightCard);
		// Click the Ace of Spades
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		assertNull(view.myCard);
	}
	
	@Test
	public void leftPlayerPlaysCardTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		controller.noCommandString = "PLAYER_TWO PLAYED Ace-of-Spades";
		view = new PlayCardsView(controller, frame, null, null, null, null);
		ActionListener[] listeners = view.cardTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.getView().leftCard.isVisible());
	}
	
	@Test
	public void centerPlayerPlaysCardTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_FOUR);
		controller.discarded = true;
		controller.noCommandString = "PLAYER_TWO PLAYED Ace-of-Spades";
		view = new PlayCardsView(controller, frame, null, null, null, null);
		ActionListener[] listeners = view.cardTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.getView().centerCard.isVisible());
	}
	
	@Test
	public void rightPlayerPlaysCardTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_THREE);
		controller.discarded = true;
		controller.noCommandString = "PLAYER_TWO PLAYED Ace-of-Spades";
		view = new PlayCardsView(controller, frame, null, null, null, null);
		ActionListener[] listeners = view.cardTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.getView().rightCard.isVisible());
	}
	
	@Test
	public void endOfNormalTrickTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new PlayCardsView(controller, frame, "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades");
		ActionListener[] listeners = view.pauseTimer.getActionListeners();
		// This toggles the unpauseToggle
		listeners[0].actionPerformed(null);
		// This creates the new GUI
		listeners[0].actionPerformed(null);
		assertNull(view.getView().myCard);
		assertNull(view.getView().leftCard);
		assertNull(view.getView().centerCard);
		assertNull(view.getView().rightCard);
	}
	
	@Test
	public void endOfFinalTrickTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.finalTrick = true;
		view = new PlayCardsView(controller, frame, "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades", "Ace-of-Spades");
		ActionListener[] listeners = view.pauseTimer.getActionListeners();
		// This toggles the unpauseToggle
		listeners[0].actionPerformed(null);
		// This creates the new GUI
		listeners[0].actionPerformed(null);
		assertEquals(RoundScoreView.class, view.getView().getClass());
	}
}
