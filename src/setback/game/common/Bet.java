/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This enumeration defines the legal bets a player can make.
 * @author Michael Burns
 * @version Oct 29, 2013
 */
public enum Bet {
	
	TWO("Two", 2),
	THREE("Three", 3),
	FOUR("Four", 4),
	FIVE("Five", 5),
	PASS("Pass", 0),
	TAKE("Take", 0);
	
	private final String printableString;
	private final int value;
	
	/**
	 * Constructor for the enumerable item sets up the state.
	 * @param printableString The printable name of the bet.
	 * @param value The numerical value of the bet.
	 */
	private Bet(String printableString, int value) {
		this.printableString = printableString;
		this.value = value;
	}
	
	/**
	 * @return the printableString.
	 */
	public String getPrintableString() {
		return printableString;
	}
	
	/**
	 * @return the value.
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return printableString;
	}

}
