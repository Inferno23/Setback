/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import setback.common.PlayerNumber;

/**
 * This class represents a player's hand for a round.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class Hand {

	private final PlayerNumber owner;
	private List<Card> cards;

	/**
	 * Constructor that sets the properties of a hand.
	 * @param owner The player whose hand it is.
	 */
	public Hand(PlayerNumber owner) {
		this.owner = owner;
		cards = new ArrayList<Card>();
	}

	/**
	 * @return the owner.
	 */
	public PlayerNumber getOwner() {
		return owner;
	}

	/**
	 * @return the cards.
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Sets the cards for the hand.
	 * @param cards The cards the player begins with.
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Calculates the number of cards in the hand with the given suit.
	 * @param suit The suit to calculate on.
	 * @return The number of cards in the hand with the given suit.
	 */
	public int getNumberOfSuit(CardSuit suit) {
		int count = 0;

		for (Card card : cards) {
			if (card.getSuit().equals(suit)) {
				count++;
			}
		}

		return count;
	}

	/**
	 * This function sorts the cards in ascending order
	 * and by suit, in the order of Spades, Hearts, Clubs, Diamonds.
	 */
	public void sortCards() {
		Collections.sort(cards, new Comparator<Card>() {
			public int compare(Card one, Card two) {
				return one.getSortValue() - two.getSortValue();
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String handString = owner + "'S HAND:\n";
		for (Card card : cards) {
			handString += card.toString() + "\n";
		}
		return handString;
	}
}
