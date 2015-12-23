/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientControllerMock;
import setback.common.PlayerNumber;

/**
 * This class is to test the PleaseWaitView.  It will prove that the
 * message shows up properly, and that the next view appears at the right time.
 * @author Michael Burns
 * @version May 25, 2014
 */
public class PleaseWaitViewIT {
	
	private PleaseWaitView view;
	private SetbackClientController controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void pleaseWaitStringVisibleTest() {
		controller = new SetbackClientControllerMock();
		view = new PleaseWaitView(controller, frame);
		String text = view.pleaseWait.getText();
		assertEquals("Please wait for other players", text);
		assertTrue(view.pleaseWait.isVisible());
	}
	
	@Test
	public void pleaseWaitNoTransitionTest() {
		controller = new SetbackClientControllerMock();
		view = new PleaseWaitView(controller, frame);
		ActionListener listeners[] = view.connectionTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertEquals(PleaseWaitView.class, view.getView().getClass());
	}
	
	@Test
	public void pleaseWaitToPlaceBetsTransistionTest() {
		controller = new SetbackClientControllerMock(PlayerNumber.PLAYER_ONE);
		view = new PleaseWaitView(controller, frame);
		ActionListener listeners[] = view.connectionTimer.getActionListeners();
		listeners[0].actionPerformed(null);
		assertEquals(PlaceBetsView.class, view.getView().getClass());
	}
}
