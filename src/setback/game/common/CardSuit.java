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

	SPADES("Spades"),
	CLUBS("Clubs"),
	HEARTS("Hearts"),
	DIAMONDS("Diamonds");
	
	private final String printableName;
	
	/**
	 * Constructor for the enumerable item sets up the state.
	 * @param printableName The name of the suit.
	 */
	private CardSuit(String printableName) {
		this.printableName = printableName;
	}
	
	/**
	 * @return the printableName.
	 */
	public String getPrintableName() {
		return printableName;
	}
	
	@Override
	public String toString() {
		return printableName;
	}
}
