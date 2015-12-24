/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientControllerImplMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the DiscardCardsView.  It will prove that
 * cards can be selected and deselected, the discard button functions
 * properly, and the next view appears at the right time.
 * @author Michael Burns
 * @version May 30, 2014
 */
public class DiscardCardsViewIT {
	
	private DiscardCardsView view;
	private SetbackClientControllerImplMock controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void trumpLabelTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		assertTrue(view.trumpLabel.isVisible());
		assertEquals("TRUMP IS SPADES", view.trumpLabel.getText());
	}

	@Test
	public void selectOneCardTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		assertEquals(1, view.discardList.size());
		assertFalse(view.discardButton.isEnabled());
	}
	
	@Test
	public void selectTwoCardsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		assertEquals(2, view.discardList.size());
		assertFalse(view.discardButton.isEnabled());
	}
	
	@Test
	public void selectThreeCardsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		assertEquals(3, view.discardList.size());
		assertTrue(view.discardButton.isEnabled());
	}
	
	@Test
	public void selectAndDeselectOneCardTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardOneUnclickListeners = view.cardOne.getMouseListeners();
		cardOneUnclickListeners[0].mousePressed(null);
		assertEquals(0, view.discardList.size());
		assertFalse(view.discardButton.isEnabled());
	}
	
	@Test
	public void selectThreeAndDeselectOneCardTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		MouseListener[] cardOneUnclickListeners = view.cardOne.getMouseListeners();
		cardOneUnclickListeners[0].mousePressed(null);
		assertEquals(2, view.discardList.size());
		assertFalse(view.discardButton.isEnabled());
	}
	
	@Test
	public void discardWithoutTrickStartedTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		view.discardButton.doClick();
		assertEquals(DiscardCardsView.class, view.getView().getClass());
		assertFalse(view.discardButton.isEnabled());
		// Cards
		assertTrue(view.cardOne.isVisible());
		assertTrue(view.cardTwo.isVisible());
		assertTrue(view.cardThree.isVisible());
		assertTrue(view.cardFour.isVisible());
		assertTrue(view.cardFive.isVisible());
		assertTrue(view.cardSix.isVisible());
		assertTrue(view.cardSeven.isVisible());
		assertTrue(view.cardEight.isVisible());
		assertTrue(view.cardNine.isVisible());
		assertNull(view.cardTen);
		assertNull(view.cardEleven);
		assertNull(view.cardTwelve);
	}
	
	@Test
	public void discardAndThenTrickStartedTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		controller.noCommandString = "TRICK STARTED";
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		view.discardButton.doClick();
		ActionListener[] timerListeners = view.allDiscardTimer.getActionListeners();
		timerListeners[0].actionPerformed(null);
		assertEquals(PlayCardsView.class, view.getView().getClass());
		// Cards
		assertTrue(view.cardOne.isVisible());
		assertTrue(view.cardTwo.isVisible());
		assertTrue(view.cardThree.isVisible());
		assertTrue(view.cardFour.isVisible());
		assertTrue(view.cardFive.isVisible());
		assertTrue(view.cardSix.isVisible());
		assertTrue(view.cardSeven.isVisible());
		assertTrue(view.cardEight.isVisible());
		assertTrue(view.cardNine.isVisible());
		assertNull(view.cardTen);
		assertNull(view.cardEleven);
		assertNull(view.cardTwelve);
	}
	
	@Test
	public void discardWithTrickStartedTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.discarded = true;
		controller.trickStarted = true;
		view = new DiscardCardsView(controller, frame);
		MouseListener[] cardOneListeners = view.cardOne.getMouseListeners();
		cardOneListeners[0].mousePressed(null);
		MouseListener[] cardTwoListeners = view.cardTwo.getMouseListeners();
		cardTwoListeners[0].mousePressed(null);
		MouseListener[] cardThreeListeners = view.cardThree.getMouseListeners();
		cardThreeListeners[0].mousePressed(null);
		view.discardButton.doClick();
		assertEquals(PlayCardsView.class, view.getView().getClass());
		// Cards
		assertTrue(view.cardOne.isVisible());
		assertTrue(view.cardTwo.isVisible());
		assertTrue(view.cardThree.isVisible());
		assertTrue(view.cardFour.isVisible());
		assertTrue(view.cardFive.isVisible());
		assertTrue(view.cardSix.isVisible());
		assertTrue(view.cardSeven.isVisible());
		assertTrue(view.cardEight.isVisible());
		assertTrue(view.cardNine.isVisible());
		assertNull(view.cardTen);
		assertNull(view.cardEleven);
		assertNull(view.cardTwelve);
	}
}
