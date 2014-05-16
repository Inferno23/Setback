/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;

/**
 * This class is to test the SetbackClientController.
 * It will prove that the controller works
 * @author Michael Burns
 * @version May 15, 2014
 */
public class SetbackClientControllerTest {

	private SetbackClientController controller;
	
	@Before
	public void setup() {
		controller = new SetbackClientController(null);
	}
	
//	@Test
//	public void setPlayerOneTest() {
//		controller.setPlayerNumbers("Player_one selected");
//		assertEquals(PlayerNumber.PLAYER_ONE, controller.getMyNumber());
//		assertEquals(PlayerNumber.PLAYER_TWO, controller.getLeft());
//		assertEquals(PlayerNumber.PLAYER_THREE, controller.getCenter());
//		assertEquals(PlayerNumber.PLAYER_FOUR, controller.getRight());
//	}
}
