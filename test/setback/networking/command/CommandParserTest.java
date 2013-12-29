/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import setback.common.SetbackException;
import setback.networking.command.Command;
import setback.networking.command.CommandMessage;
import setback.networking.command.CommandParser;

/**
 * This class will test the CommandParser class.
 * @author Michael
 * @version Dec 26, 2013
 */
public class CommandParserTest {

	private CommandParser parser;
	
	@Before
	public void setUp() throws Exception {
		parser = new CommandParser();
	}

	@Test
	public void nullInput() throws SetbackException {
		final CommandMessage result = parser.parseString(null);
		final CommandMessage expected = new CommandMessage(Command.NO_COMMAND);
		assertEquals(expected, result);
	}
	
	@Test(expected=SetbackException.class)
	public void unknownInput() throws SetbackException {
		parser.parseString("UNKNOWN_STRING");
	}
	
	@Test
	public void requestPlayerOne() throws SetbackException {
		final CommandMessage result = parser.parseString("REQUEST_PLAYER_ONE");
		final CommandMessage expected = new CommandMessage(Command.REQUEST_PLAYER_ONE);
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerTwo() throws SetbackException {
		final CommandMessage result = parser.parseString("REQUEST_PLAYER_TWO");
		final CommandMessage expected = new CommandMessage(Command.REQUEST_PLAYER_TWO);
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerThree() throws SetbackException {
		final CommandMessage result = parser.parseString("REQUEST_PLAYER_THREE");
		final CommandMessage expected = new CommandMessage(Command.REQUEST_PLAYER_THREE);
		assertEquals(expected, result);
	}
	
	@Test
	public void requestPlayerFour() throws SetbackException {
		final CommandMessage result = parser.parseString("REQUEST_PLAYER_FOUR");
		final CommandMessage expected = new CommandMessage(Command.REQUEST_PLAYER_FOUR);
		assertEquals(expected, result);
	}
	
	@Test(expected=SetbackException.class)
	public void extraArgumentWithRequestPlayerOne() throws SetbackException {
		parser.parseString("REQUEST_PLAYER_ONE EXTRA_WORDS");
	}
	
	@Test(expected=SetbackException.class)
	public void missingArgumentWithPlaceBet() throws SetbackException {
		parser.parseString("PLACE_BET");
	}
	
	@Test
	public void placeBetPass() throws SetbackException {
		final CommandMessage result = parser.parseString("PLACE_BET PASS");
		final String arguments[] = {"PASS"};
		final CommandMessage expected = new CommandMessage(Command.PLACE_BET, arguments);
		assertEquals(expected, result);
	}
	
	@Test(expected=SetbackException.class)
	public void extraArgumentWithPlaceBet() throws SetbackException {
		parser.parseString("PLACE_BET PASS EXTRA_WORDS");
	}
	
	@Test(expected=SetbackException.class)
	public void missingArgumentWithDiscardCards() throws SetbackException {
		parser.parseString("DISCARD_CARDS");
	}
	
	@Test
	public void discardCardsProperly() throws SetbackException {
		final CommandMessage result = 
				parser.parseString("DISCARD_CARDS ACE-OF-SPADES TWO-OF-SPADES THREE-OF-SPADES");
		final String arguments[] = {"ACE-OF-SPADES", "TWO-OF-SPADES", "THREE-OF-SPADES"};
		final CommandMessage expected = new CommandMessage(Command.DISCARD_CARDS, arguments);
		assertEquals(expected, result);
	}
	
	@Test(expected=SetbackException.class)
	public void extraArgumentWithDiscardCards() throws SetbackException {
		parser.parseString("DISCARD_CARDS ACE-OF-SPADES TWO-OF-SPADES THREE-OF-SPADES EXTRA_WORDS");
	}

}
