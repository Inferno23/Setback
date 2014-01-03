/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This enumeration defines the four suits in the deck.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public enum CardSuit {

	SPADES("Spades", 0),
	CLUBS("Clubs", 26),
	HEARTS("Hearts", 13),
	DIAMONDS("Diamonds", 39);
	
	private final String printableName;
	private final int sortValue;
	
	/**
	 * Constructor for the enumerable item sets up the state.
	 * @param printableName The name of the suit.
	 */
	private CardSuit(String printableName, int sortValue) {
		this.printableName = printableName;
		this.sortValue = sortValue;
	}
	
	/**
	 * @return the printableName.
	 */
	public String getPrintableName() {
		return printableName;
	}
	
	/**
	 * @return the sortValue.
	 */
	public int getSortValue() {
		return sortValue;
	}
	
	@Override
	public String toString() {
		return printableName;
	}
}
