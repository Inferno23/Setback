/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This class is simply a data structure that describes a particular card.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public class Card {

	private final CardType type;
	private final CardSuit suit;

	/**
	 * Constructor for a Card which requires the type
	 * and suit of the card
	 * @param type The type of card it is.
	 * @param suit The suit the card belongs to.
	 */
	public Card(CardType type, CardSuit suit) {
		this.type = type;
		this.suit = suit;
	}

	/**
	 * @return the type.
	 */
	public CardType getType() {
		return type;
	}

	/**
	 * @return the suit.
	 */
	public CardSuit getSuit() {
		return suit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equality = false;
		if (this == obj) {
			equality = true;
		}
		else if (obj == null) {
			equality = false;
		}
		else if (getClass() != obj.getClass()) {
			equality = false;
		}
		else {
			final Card other = (Card) obj;
			if (suit != other.suit) {
				equality = false;
			}
			else if (type != other.type) {
				equality = false;
			}
			else {
				equality = true;
			}
		}
		return equality;
	}

	@Override
	public String toString() {
		return type.toString() + " of " + suit.toString();
	}
}
