/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientControllerImplMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the SelectTrumpView.  It will prove that
 * the buttons are enabled for the correct user, the message shows
 * up properly, and the next view appears at the right time.
 * @author Michael Burns
 * @version May 30, 2014
 */
public class SelectTrumpIT {

	private SelectTrumpView view;
	private SetbackClientControllerImplMock controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void winningBetLabelTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new SelectTrumpView(controller, frame);
		assertTrue(view.winningBet.isVisible());
		assertEquals("PLAYER_TWO WON WITH A BET OF TWO", view.winningBet.getText());
	}
	
	@Test
	public void buttonsForBetLosersTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new SelectTrumpView(controller, frame);
		assertTrue(view.spadesButton.isVisible());
		assertFalse(view.spadesButton.isEnabled());
		assertTrue(view.heartsButton.isVisible());
		assertFalse(view.heartsButton.isEnabled());
		assertTrue(view.clubsButton.isVisible());
		assertFalse(view.clubsButton.isEnabled());
		assertTrue(view.diamondsButton.isVisible());
		assertFalse(view.diamondsButton.isEnabled());
	}
	
	@Test
	public void buttonsForBetWinnerTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new SelectTrumpView(controller, frame);
		assertTrue(view.spadesButton.isVisible());
		assertTrue(view.spadesButton.isEnabled());
		assertTrue(view.heartsButton.isVisible());
		assertTrue(view.heartsButton.isEnabled());
		assertTrue(view.clubsButton.isVisible());
		assertTrue(view.clubsButton.isEnabled());
		assertTrue(view.diamondsButton.isVisible());
		assertTrue(view.diamondsButton.isEnabled());
	}
	
	@Test
	public void clickSpadesTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new SelectTrumpView(controller, frame);
		view.spadesButton.doClick();
		assertEquals(DiscardCardsView.class, view.getView().getClass());
	}
	
	@Test
	public void clickHeartsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new SelectTrumpView(controller, frame);
		view.heartsButton.doClick();
		assertEquals(DiscardCardsView.class, view.getView().getClass());
	}
	
	@Test
	public void clickClubsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new SelectTrumpView(controller, frame);
		view.clubsButton.doClick();
		assertEquals(DiscardCardsView.class, view.getView().getClass());
	}
	
	@Test
	public void clickDiamondsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new SelectTrumpView(controller, frame);
		view.diamondsButton.doClick();
		assertEquals(DiscardCardsView.class, view.getView().getClass());
	}
	
	@Test
	public void hearSpadesTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.noCommandString = "PLAYER_TWO SELECTED SPADES";
		view = new SelectTrumpView(controller, frame);
	}
	
	@Test
	public void hearHeartsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.noCommandString = "PLAYER_TWO SELECTED HEARTS";
		view = new SelectTrumpView(controller, frame);
	}
	
	@Test
	public void hearClubsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.noCommandString = "PLAYER_TWO SELECTED CLUBS";
		view = new SelectTrumpView(controller, frame);
	}
	
	@Test
	public void hearDiamondsTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		controller.noCommandString = "PLAYER_TWO SELECTED DIAMONDS";
		view = new SelectTrumpView(controller, frame);
	}
}
