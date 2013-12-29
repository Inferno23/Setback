/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;

/**
 * This class will test the PlayerController class.
 * @author Michael
 * @version Dec 26, 2013
 */
public class PlayerControllerTest {
	
	private SetbackGameFactory factory;
	private SetbackGameController game;
	private PlayerController controllerOne;
	private PlayerController controllerTwo;
	private PlayerController controllerThree;
	private PlayerController controllerFour;
	
	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeDeltaSetbackGame(0);
		controllerOne = new PlayerController(game);
		controllerTwo = new PlayerController(game);
		controllerThree = new PlayerController(game);
		controllerFour = new PlayerController(game);
	}

	@Test
	public void nullInput() {
		final String result = controllerOne.processInput(null);
		final String expected = "No command";
		assertEquals(expected, result);
	}
	
	@Test
	public void noCommandInput() {
		final String result = controllerOne.processInput(new CommandMessage(Command.NO_COMMAND));
		final String expected = "No command";
		assertEquals(expected, result);
	}
	
	@Test
	public void exitInput() {
		final String result = controllerOne.processInput(new CommandMessage(Command.EXIT));
		final String expected = "EXIT";
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerOneSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String expected = "Player one selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_ONE, controllerOne.getMyNumber());
	}
	
	@Test
	public void requestPlayerOneRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String expected = "Player one rejected";
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerTwoSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String expected = "Player two selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_TWO, controllerOne.getMyNumber());
	}
	
	@Test
	public void requestPlayerTwoRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
		final String expected = "Player two rejected";
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerThreeSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String expected = "Player three selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_THREE, controllerOne.getMyNumber());
	}
	
	@Test
	public void requestPlayerThreeRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
		final String expected = "Player three rejected";
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerFourSelected() {
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String expected = "Player four selected";
		assertEquals(expected, result);
		assertEquals(PlayerNumber.PLAYER_FOUR, controllerOne.getMyNumber());
	}
	
	@Test
	public void requestPlayerFourRejected() {
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String result = controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
		final String expected = "Player four rejected";
		assertEquals(expected, result);
	}
	
	@Test
	public void startGameWithoutFourPlayers() {
		final String arguments[] = {"PASS"};
		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
		final String result = controllerOne.processInput(new CommandMessage(Command.PLACE_BET, arguments));
		final String expected = "You must start the game!";
		assertEquals(expected, result);
	}
	
//	@Test
//	public void startGameWithFourPlayers() {
//		controllerOne.processInput(new CommandMessage(Command.REQUEST_PLAYER_ONE));
//		controllerTwo.processInput(new CommandMessage(Command.REQUEST_PLAYER_TWO));
//		controllerThree.processInput(new CommandMessage(Command.REQUEST_PLAYER_THREE));
//		controllerFour.processInput(new CommandMessage(Command.REQUEST_PLAYER_FOUR));
//	}
}
