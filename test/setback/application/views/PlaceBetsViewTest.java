/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientControllerMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the PlaceBetsView.  It will prove that the
 * buttons are enabled at the proper time, the messages show up correctly,
 * and the next view appears at the right time.
 * @author Michael Burns
 * @version May 27, 2014
 */
public class PlaceBetsViewTest {

	private PlaceBetsView view;
	private SetbackClientControllerMock controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void playerHandVisibleTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PlaceBetsView(controller, frame);
		assertTrue(view.cardOne.isVisible());
		assertTrue(view.cardTwo.isVisible());
		assertTrue(view.cardThree.isVisible());
		assertTrue(view.cardFour.isVisible());
		assertTrue(view.cardFive.isVisible());
		assertTrue(view.cardSix.isVisible());
		assertTrue(view.cardSeven.isVisible());
		assertTrue(view.cardEight.isVisible());
		assertTrue(view.cardNine.isVisible());
		assertTrue(view.cardTen.isVisible());
		assertTrue(view.cardEleven.isVisible());
		assertTrue(view.cardTwelve.isVisible());
	}
	
	@Test
	public void leftHandVisibleTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PlaceBetsView(controller, frame);
		assertTrue(view.leftHand[1].isVisible());
		assertTrue(view.leftHand[2].isVisible());
		assertTrue(view.leftHand[3].isVisible());
		assertTrue(view.leftHand[4].isVisible());
		assertTrue(view.leftHand[5].isVisible());
		assertTrue(view.leftHand[6].isVisible());
		assertTrue(view.leftHand[7].isVisible());
		assertTrue(view.leftHand[8].isVisible());
		assertTrue(view.leftHand[9].isVisible());
		assertTrue(view.leftHand[10].isVisible());
		assertTrue(view.leftHand[11].isVisible());
		assertTrue(view.leftHand[12].isVisible());
	}
	
	@Test
	public void centerHandVisibleTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PlaceBetsView(controller, frame);
		assertTrue(view.centerHand[1].isVisible());
		assertTrue(view.centerHand[2].isVisible());
		assertTrue(view.centerHand[3].isVisible());
		assertTrue(view.centerHand[4].isVisible());
		assertTrue(view.centerHand[5].isVisible());
		assertTrue(view.centerHand[6].isVisible());
		assertTrue(view.centerHand[7].isVisible());
		assertTrue(view.centerHand[8].isVisible());
		assertTrue(view.centerHand[9].isVisible());
		assertTrue(view.centerHand[10].isVisible());
		assertTrue(view.centerHand[11].isVisible());
		assertTrue(view.centerHand[12].isVisible());
	}
	
	@Test
	public void rightHandVisibleTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PlaceBetsView(controller, frame);
		assertTrue(view.rightHand[1].isVisible());
		assertTrue(view.rightHand[2].isVisible());
		assertTrue(view.rightHand[3].isVisible());
		assertTrue(view.rightHand[4].isVisible());
		assertTrue(view.rightHand[5].isVisible());
		assertTrue(view.rightHand[6].isVisible());
		assertTrue(view.rightHand[7].isVisible());
		assertTrue(view.rightHand[8].isVisible());
		assertTrue(view.rightHand[9].isVisible());
		assertTrue(view.rightHand[10].isVisible());
		assertTrue(view.rightHand[11].isVisible());
		assertTrue(view.rightHand[12].isVisible());
	}
	
	@Test
	public void leftPlayerBetsTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		controller.noCommandString = "PLAYER_TWO BET PASS";
		view = new PlaceBetsView(controller, frame);
		ActionListener listeners[] = view.neighborBetTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.leftBet.isVisible());
		assertEquals("PLAYER_TWO BET PASS", view.leftBet.getText());
	}
	
	@Test
	public void centerPlayerBetsTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_FOUR);
		controller.noCommandString = "PLAYER_TWO BET PASS";
		view = new PlaceBetsView(controller, frame);
		ActionListener listeners[] = view.neighborBetTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.centerBet.isVisible());
		assertEquals("PLAYER_TWO BET PASS", view.centerBet.getText());
	}
	
	@Test
	public void rightPlayerBetsTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_THREE);
		controller.noCommandString = "PLAYER_TWO BET PASS";
		view = new PlaceBetsView(controller, frame);
		ActionListener listeners[] = view.neighborBetTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertTrue(view.rightBet.isVisible());
		assertEquals("PLAYER_TWO BET PASS", view.rightBet.getText());
	}
	
	@Test
	public void buttonsNotEnabledTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PlaceBetsView(controller, frame);
		assertFalse(view.passButton.isEnabled());
		assertFalse(view.twoButton.isEnabled());
		assertFalse(view.threeButton.isEnabled());
		assertFalse(view.fourButton.isEnabled());
		assertFalse(view.fiveButton.isEnabled());
		assertFalse(view.takeButton.isEnabled());
	}
	
	@Test
	public void buttonsEnabledTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		assertTrue(view.passButton.isEnabled());
		assertTrue(view.twoButton.isEnabled());
		assertTrue(view.threeButton.isEnabled());
		assertTrue(view.fourButton.isEnabled());
		assertTrue(view.fiveButton.isEnabled());
		assertTrue(view.takeButton.isEnabled());
	}
	
	@Test
	public void playerTwoBetsPassTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.passButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU PASSED", view.myBet.getText());
	}
	
	@Test
	public void playerTwoBetsTwoTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.twoButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU BET TWO", view.myBet.getText());
	}
	
	@Test
	public void playerTwoBetsThreeTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.threeButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU BET THREE", view.myBet.getText());
	}
	
	@Test
	public void playerTwoBetsFourTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.fourButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU BET FOUR", view.myBet.getText());
	}
	
	@Test
	public void playerTwoBetsFiveTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.fiveButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU BET FIVE", view.myBet.getText());
	}
	
	@Test
	public void playerTwoTakesBetTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new PlaceBetsView(controller, frame);
		view.takeButton.doClick();
		assertTrue(view.myBet.isVisible());
		assertEquals("YOU TOOK THE BET", view.myBet.getText());
	}
	
	@Test
	public void playerTwoPassesBetBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.passButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
	
	@Test
	public void playerTwoBetsTwoBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.twoButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
	
	@Test
	public void playerTwoBetsThreeBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.threeButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
	
	@Test
	public void playerTwoBetsFourBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.fourButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
	
	@Test
	public void playerTwoBetsFiveBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.fiveButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
	
	@Test
	public void playerTwoBetsTakeBettingResolvedTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		controller.bettingResolved = true;
		view = new PlaceBetsView(controller, frame);
		view.takeButton.doClick();
		assertEquals(SelectTrumpView.class, view.getView().getClass());
	}
}
