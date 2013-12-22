/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch. 
 */
package setback.utilities;

import java.util.ArrayList;
import java.util.List;

import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;

/**
 * This class holds utility functions that are used
 * in multiple files.
 * @author Michael
 * @version Dec 21, 2013
 */
public class UtilityFunctions {
	
	/**
	 * Helper function that returns the ordered list of cards.
	 * This list will be used to randomly deal the hands, but
	 * the basis list will always bee the same.
	 * @return A list with all 52 cards in it.
	 */
	public static List<Card> generateOrderedCardList() {
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(new Card(CardType.ACE, CardSuit.SPADES));
		cardList.add(new Card(CardType.TWO, CardSuit.SPADES));
		cardList.add(new Card(CardType.THREE, CardSuit.SPADES));
		cardList.add(new Card(CardType.FOUR, CardSuit.SPADES));
		cardList.add(new Card(CardType.FIVE, CardSuit.SPADES));
		cardList.add(new Card(CardType.SIX, CardSuit.SPADES));
		cardList.add(new Card(CardType.SEVEN, CardSuit.SPADES));
		cardList.add(new Card(CardType.EIGHT, CardSuit.SPADES));
		cardList.add(new Card(CardType.NINE, CardSuit.SPADES));
		cardList.add(new Card(CardType.TEN, CardSuit.SPADES));
		cardList.add(new Card(CardType.JACK, CardSuit.SPADES));
		cardList.add(new Card(CardType.QUEEN, CardSuit.SPADES));
		cardList.add(new Card(CardType.KING, CardSuit.SPADES));
		cardList.add(new Card(CardType.ACE, CardSuit.CLUBS));
		cardList.add(new Card(CardType.TWO, CardSuit.CLUBS));
		cardList.add(new Card(CardType.THREE, CardSuit.CLUBS));
		cardList.add(new Card(CardType.FOUR, CardSuit.CLUBS));
		cardList.add(new Card(CardType.FIVE, CardSuit.CLUBS));
		cardList.add(new Card(CardType.SIX, CardSuit.CLUBS));
		cardList.add(new Card(CardType.SEVEN, CardSuit.CLUBS));
		cardList.add(new Card(CardType.EIGHT, CardSuit.CLUBS));
		cardList.add(new Card(CardType.NINE, CardSuit.CLUBS));
		cardList.add(new Card(CardType.TEN, CardSuit.CLUBS));
		cardList.add(new Card(CardType.JACK, CardSuit.CLUBS));
		cardList.add(new Card(CardType.QUEEN, CardSuit.CLUBS));
		cardList.add(new Card(CardType.KING, CardSuit.CLUBS));
		cardList.add(new Card(CardType.ACE, CardSuit.HEARTS));
		cardList.add(new Card(CardType.TWO, CardSuit.HEARTS));
		cardList.add(new Card(CardType.THREE, CardSuit.HEARTS));
		cardList.add(new Card(CardType.FOUR, CardSuit.HEARTS));
		cardList.add(new Card(CardType.FIVE, CardSuit.HEARTS));
		cardList.add(new Card(CardType.SIX, CardSuit.HEARTS));
		cardList.add(new Card(CardType.SEVEN, CardSuit.HEARTS));
		cardList.add(new Card(CardType.EIGHT, CardSuit.HEARTS));
		cardList.add(new Card(CardType.NINE, CardSuit.HEARTS));
		cardList.add(new Card(CardType.TEN, CardSuit.HEARTS));
		cardList.add(new Card(CardType.JACK, CardSuit.HEARTS));
		cardList.add(new Card(CardType.QUEEN, CardSuit.HEARTS));
		cardList.add(new Card(CardType.KING, CardSuit.HEARTS));
		cardList.add(new Card(CardType.ACE, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.TWO, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.THREE, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.FOUR, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.FIVE, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.SIX, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.SEVEN, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.EIGHT, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.NINE, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.TEN, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.JACK, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.QUEEN, CardSuit.DIAMONDS));
		cardList.add(new Card(CardType.KING, CardSuit.DIAMONDS));
		
		return cardList;
	}
}
