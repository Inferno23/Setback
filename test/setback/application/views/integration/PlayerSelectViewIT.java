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

import setback.application.client.SetbackClientControllerImpl;
import setback.application.client.SetbackClientControllerImplMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the PlayerSelectView.  It will prove that the
 * buttons exist and can be clicked, providing appropriate responses
 * based on whether or not that player has already been selected.
 * @author Michael Burns
 * @version May 25, 2014
 */
public class PlayerSelectViewIT {

	private PlayerSelectView view;
	private SetbackClientControllerImpl controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void teamOneLabelTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		String text = view.teamOneLabel.getText();
		assertEquals("Team One", text);
		assertTrue(view.teamOneLabel.isVisible());
	}
	
	@Test
	public void teamTwoLabelTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		String text = view.teamTwoLabel.getText();
		assertEquals("Team Two", text);
		assertTrue(view.teamTwoLabel.isVisible());
	}
	
	@Test
	public void errorLabelInitialTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		String text = view.errorLabel.getText();
		assertEquals("That player has already been selected.", text);
		assertFalse(view.errorLabel.isVisible());
	}
	
	@Test
	public void selectPlayerOneSuccessTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		view.playerOneButton.doClick();
		assertEquals(PleaseWaitView.class, view.getView().getClass());
	}
	
	@Test
	public void selectPlayerOneFailureTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_ONE);
		view = new PlayerSelectView(controller, frame);
		view.playerOneButton.doClick();
		assertEquals(PlayerSelectView.class, view.getView().getClass());
		assertTrue(view.errorLabel.isVisible());
	}
	
	@Test
	public void selectPlayerTwoSuccessTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		view.playerTwoButton.doClick();
		assertEquals(PleaseWaitView.class, view.getView().getClass());
	}
	
	@Test
	public void selectPlayerTwoFailureTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_TWO);
		view = new PlayerSelectView(controller, frame);
		view.playerTwoButton.doClick();
		assertEquals(PlayerSelectView.class, view.getView().getClass());
		assertTrue(view.errorLabel.isVisible());
	}
	
	@Test
	public void selectPlayerThreeSuccessTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		view.playerThreeButton.doClick();
		assertEquals(PleaseWaitView.class, view.getView().getClass());
	}
	
	@Test
	public void selectPlayerThreeFailureTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_THREE);
		view = new PlayerSelectView(controller, frame);
		view.playerThreeButton.doClick();
		assertEquals(PlayerSelectView.class, view.getView().getClass());
		assertTrue(view.errorLabel.isVisible());
	}
	
	@Test
	public void selectPlayerFourSuccessTest() {
		controller = new SetbackClientControllerImplMock();
		view = new PlayerSelectView(controller, frame);
		view.playerFourButton.doClick();
		assertEquals(PleaseWaitView.class, view.getView().getClass());
	}
	
	@Test
	public void selectPlayerFourFailureTest() {
		controller = new SetbackClientControllerImplMock(PlayerNumber.PLAYER_FOUR);
		view = new PlayerSelectView(controller, frame);
		view.playerFourButton.doClick();
		assertEquals(PlayerSelectView.class, view.getView().getClass());
		assertTrue(view.errorLabel.isVisible());
	}
	//TODO: Test the rest of PlayerSelectView
}
