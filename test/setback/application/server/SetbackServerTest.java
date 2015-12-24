/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.ServerSocket;

import org.junit.Ignore;
import org.junit.Test;

import setback.game.SetbackGameController;
import setback.game.version.delta.DeltaSetbackGameImpl;

/**
 * This class is to test the SetbackServer.  It will prove that
 * the SetbackServer can properly create games and specify ports.
 * @author Michael Burns
 * @version May 15, 2014
 */
public class SetbackServerTest {

	private final int port = 2323;
	
	@Test
	public void createRetailGameNullDebugTest() {
		final String debug = null;
		final SetbackGameController game = SetbackServer.makeGame(debug);
		assertEquals(DeltaSetbackGameImpl.class, game.getClass());
	}
	
	@Test
	public void createRetailGameFalseDebugTest() {
		final String debug = "false";
		final SetbackGameController game = SetbackServer.makeGame(debug);
		assertEquals(DeltaSetbackGameImpl.class, game.getClass());
	}
	
	@Test
	public void createDebugGameTest() {
		final String debug = "true";
		final SetbackGameController game = SetbackServer.makeGame(debug);
		assertEquals(DeltaSetbackGameImpl.class, game.getClass());
	}
	
	@Test
	public void getPortNumberNullTest() {
		final String[] args = null;
		final int result = SetbackServer.getPortNumber(args);
		assertEquals(port, result);
	}
	
	@Test
	public void getPortNumberInvalidArrayTest() {
		final String[] args = {"1", "2"};
		final int result = SetbackServer.getPortNumber(args);
		assertEquals(port, result);
	}
	
	@Test
	public void getPortNumberValidArrayTest() {
		final String[] args = {"1"};
		final int result = SetbackServer.getPortNumber(args);
		assertEquals(1, result);
	}

	@Ignore
	@Test
	public void getServerSocketSuccessTest() {
		final int portNumber = 1;
		final ServerSocket result = SetbackServer.getServerSocket(portNumber);
		assertNotNull(result);
		assertEquals(ServerSocket.class, result.getClass());
	}
	
	@Test
	public void getServerSocketFailureTest() {
		final int portNumber = 2;
		SetbackServer.getServerSocket(portNumber);
		final ServerSocket result = SetbackServer.getServerSocket(portNumber);
		assertNull(result);
	}
}
