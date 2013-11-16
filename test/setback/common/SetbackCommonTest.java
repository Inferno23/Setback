/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the common materials, such as Exceptions.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public class SetbackCommonTest {

	@Test(expected=SetbackException.class)
	public void setbackExceptionTest() throws SetbackException {
		throw new SetbackException("This is a test of the normal Exception");
	}
	
	@Test(expected=SetbackRuntimeException.class)
	public void setbackRuntimeExceptionTest() throws SetbackRuntimeException {
		throw new SetbackRuntimeException("This is a test of the Runtime Exception.");
	}

	@Test
	public void playerNumberCoverageTest() {
		assertEquals("PLAYER_ONE", PlayerNumber.PLAYER_ONE.toString());
		assertEquals("PLAYER_TWO", PlayerNumber.PLAYER_TWO.toString());
		assertEquals("PLAYER_THREE", PlayerNumber.PLAYER_THREE.toString());
		assertEquals("PLAYER_FOUR", PlayerNumber.PLAYER_FOUR.toString());
		PlayerNumber[] players = PlayerNumber.values();
		assertEquals(4, players.length);
		assertEquals(PlayerNumber.PLAYER_ONE, PlayerNumber.valueOf("PLAYER_ONE"));
		assertEquals(PlayerNumber.PLAYER_TWO, PlayerNumber.valueOf("PLAYER_TWO"));
		assertEquals(PlayerNumber.PLAYER_THREE, PlayerNumber.valueOf("PLAYER_THREE"));
		assertEquals(PlayerNumber.PLAYER_FOUR, PlayerNumber.valueOf("PLAYER_FOUR"));
	}
	
	@Test
	public void playerTeamCoverageTest() {
		assertEquals("TEAM_ONE", PlayerTeam.TEAM_ONE.toString());
		assertEquals("TEAM_TWO", PlayerTeam.TEAM_TWO.toString());
		PlayerTeam[] teams = PlayerTeam.values();
		assertEquals(2, teams.length);
		assertEquals(PlayerTeam.TEAM_ONE, PlayerTeam.valueOf("TEAM_ONE"));
		assertEquals(PlayerTeam.TEAM_TWO, PlayerTeam.valueOf("TEAM_TWO"));
	}
}
