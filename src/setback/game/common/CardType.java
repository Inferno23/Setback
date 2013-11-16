/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This enumeration defines the various card types that are
 * used in the deck of cards for Setback.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public enum CardType {
	
	ACE("Ace", 14, 4),
	TWO("Two", 2, 0),
	THREE("Three", 3, 0),
	FOUR("Four", 4, 0),
	FIVE("Five", 5, 0),
	SIX("Six", 6, 0),
	SEVEN("Seven", 7, 0),
	EIGHT("Eight", 8, 0),
	NINE("Nine", 9, 0),
	TEN("Ten", 10, 10),
	JACK("Jack", 11, 1),
	QUEEN("Queen", 12, 2),
	KING("King", 13, 3);

	private final String printableName;
	private final int standardValue;
	private final int gameValue;
	
	/**
	 * Constructor for the enumerable item sets up the state.
	 * @param printableName The name of the card.
	 * @param standardvalue The value of the card numerically.
	 * @param gameValue The value of the card in game points.
	 */
	private CardType(String printableName, int standardValue, int gameValue) {
		this.printableName = printableName;
		this.standardValue = standardValue;
		this.gameValue = gameValue;
	}

	/**
	 * @return the printableName.
	 */
	public String getPrintableName() {
		return printableName;
	}

	/**
	 * @return the standardValue.
	 */
	public int getStandardValue() {
		return standardValue;
	}
	
	/**
	 * @return the gameValue.
	 */
	public int getGameValue() {
		return gameValue;
	}
	
	@Override
	public String toString() {
		return printableName;
	}
}
