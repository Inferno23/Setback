/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientControllerMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the RoundScoreView.  It will prove that
 * the score is properly displayed for each team, and then
 * the next round begins properly.
 * @author Michael Burns
 * @version May 31, 2014
 */
public class RoundScoreViewTest {

	private RoundScoreView view;
	private SetbackClientControllerMock controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void playerOneTeamStringTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new RoundScoreView(controller, frame);
		assertTrue(view.teamLabel.isVisible());
		assertEquals(view.teamLabel.getText(), "FYI: You're on TEAM ONE");
	}
	
	@Test
	public void playerTwoTeamStringTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_TWO);
		view = new RoundScoreView(controller, frame);
		assertTrue(view.teamLabel.isVisible());
		assertEquals(view.teamLabel.getText(), "FYI: You're on TEAM TWO");
	}
	
	@Test
	public void playerThreeTeamStringTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_THREE);
		view = new RoundScoreView(controller, frame);
		assertTrue(view.teamLabel.isVisible());
		assertEquals(view.teamLabel.getText(), "FYI: You're on TEAM ONE");
	}
	
	@Test
	public void playerFourTeamStringTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_FOUR);
		view = new RoundScoreView(controller, frame);
		assertTrue(view.teamLabel.isVisible());
		assertEquals(view.teamLabel.getText(), "FYI: You're on TEAM TWO");
	}
	
	@Test
	public void teamScoresTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new RoundScoreView(controller, frame);
		assertTrue(view.teamOneScore.isVisible());
		assertEquals("TEAM ONE: 1", view.teamOneScore.getText());
		assertTrue(view.teamTwoScore.isVisible());
		assertEquals("TEAM TWO: 2", view.teamTwoScore.getText());
	}
	
	@Test
	public void continueButtonTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new RoundScoreView(controller, frame);
		view.continueButton.doClick();
		assertEquals(PlaceBetsView.class, view.getView().getClass());
	}
}
