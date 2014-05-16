/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;

/**
 * This class is to test the SetbackServerThread.  It will prove that
 * the SetbackServerThread correctly handles input from the PlayerController.
 * @author Michael Burns
 * @version May 15, 2014
 */
public class SetbackServerThreadTest {

	private SetbackServerThreadMock thread;
	private File mockFile;
	private PrintWriter out;
	
	@Before
	public void setUp() throws UnknownHostException, IOException {
		// TODO: Make this a mock game?
		SetbackGameController game = SetbackGameFactory.getInstance().makeDeltaSetbackGame();
		thread = new SetbackServerThreadMock(null, game);
		mockFile = new File("test/setback/application/server/mock.txt");
		out = new PrintWriter(mockFile);
		thread.setOut(out);
	}
	
	@After
	public void cleanUp() {
		out.close();
		mockFile.delete();
	}
	
	@Test
	public void updateNullMessageTest() {
		final String message = null;
		thread.update(message);
		PlayerControllerMock mockController = (PlayerControllerMock) thread.getController();
		assertNull(mockController.getStatus());
	}
	
	@Test
	public void updateRoundBeginTest() {
		final String message = "ROUND BEGIN";
		thread.update(message);
		PlayerControllerMock mockController = (PlayerControllerMock) thread.getController();
		final String expected = "ROUND STARTED";
		assertEquals(expected, mockController.getStatus());
	}
	
	@Test
	public void updateBettingResolvedTest() throws IOException {
		final String message = "BETTING RESOLVED";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "BETTING RESOLVED ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updateTrickStartedTest() throws IOException {
		final String message = "TRICK STARTED";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "TRICK STARTED ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updateRoundEndedTest() throws IOException {
		final String message = "ROUND ENDED";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "ROUND ENDED ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerBetTest() throws IOException {
		final String message = "PLAYER_ONE BET TWO";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "PLAYER_ONE BET TWO ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerSelectTrumpTest() throws IOException {
		final String message = "PLAYER_ONE SELECTED DIAMONDS";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "PLAYER_ONE SELECTED DIAMONDS ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerDiscardTest() throws IOException {
		final String message = "PLAYER_ONE DISCARDED TWO_OF_DIAMONDS THREE_OF_DIAMONDS FOUR_OF_DIAMONDS";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "PLAYER_ONE DISCARDED TWO_OF_DIAMONDS THREE_OF_DIAMONDS FOUR_OF_DIAMONDS ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerPlayCardTest() throws IOException {
		final String message = "PLAYER_ONE PLAYED TWO_OF_DIAMONDS";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "PLAYER_ONE PLAYED TWO_OF_DIAMONDS ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerWonTrickTest() throws IOException {
		final String message = "PLAYER_ONE WON TRICK";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "PLAYER_ONE WON TRICK ";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updatePlayerInvalidTest() throws IOException {
		final String message = "PLAYER_ONE";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "";
		assertEquals (expected, contents);
	}
	
	@Test
	public void updateInvalidTest() throws IOException {
		final String message = "TEST";
		thread.update(message);
		out.flush();
		String contents = FileUtils.readFileToString(mockFile, null);
		String expected = "";
		assertEquals (expected, contents);
	}
}
