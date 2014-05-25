/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.views;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import setback.application.client.SetbackClientController;
import setback.application.client.SetbackClientControllerMock;

/**
 * This class is to test the PlayerSelectView.  It will prove that the
 * buttons exist and can be clicked, providing appropriate responses
 * based on whether or not that player has already been selected.
 * @author Michael Burns
 * @version May 25, 2014
 */
public class PlayerSelectViewTest {

	private PlayerSelectView view;
	private SetbackClientController controller;
	private JFrame frame;
	
	@Before
	public void setup() {
		frame = new JFrame();
	}
	
	@Test
	public void teamOneLabelTest() {
		controller = new SetbackClientControllerMock();
		view = new PlayerSelectView(controller, frame);
		String label = view.teamOneLabel.getText();
		assertEquals("Team One", label);
	}
	
	@Test
	public void teamTwoLabelTest() {
		controller = new SetbackClientControllerMock();
		view = new PlayerSelectView(controller, frame);
		String label = view.teamTwoLabel.getText();
		assertEquals("Team Two", label);
	}
	
	//TODO: Test the rest of PlayerSelectView
}
