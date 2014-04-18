/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch. 
 */
package setback.game.version.delta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import setback.game.CardDealerController;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;
import setback.game.common.Hand;

/**
 * The implementation of a CardDealerController for the Delta version
 * of Setback.  This dealer will deal hands of twelve cards that are
 * randomly selected.
 * @author Michael
 * @version Dec 21, 2013
 */
public class DeltaCardDealerController implements CardDealerController {

	Random random;

	/**
	 * Constructor that takes in a seed for the random
	 * number generator.  This will be used with testing.
	 * In practice, the seed should be truly random.
	 * @param seed The random seed for the random
	 * number generator.
	 */
	public DeltaCardDealerController(long seed) {
		random = new Random(seed);
	}

	/* (non-Javadoc)
	 * @see setback.game.CardDealerController#dealHands(setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand)
	 */
	@Override
	public void dealHands(Hand playerOneHand, Hand playerTwoHand,
			Hand playerThreeHand, Hand playerFourHand) {
		
		final List<Card> cardList = generateOrderedCardList();
		final List<Card> playerOneCards = new ArrayList<Card>();
		final List<Card> playerTwoCards = new ArrayList<Card>();
		final List<Card> playerThreeCards = new ArrayList<Card>();
		final List<Card> playerFourCards = new ArrayList<Card>();
		int index;
		int randomNumber;
		
		for (index = 0; index < 12; index++) {
			randomNumber = random.nextInt(cardList.size() - 1);
			  playerOneCards.add(cardList.remove(randomNumber));
		}
		playerOneHand.setCards(playerOneCards);
		
		for (index = 0; index < 12; index++) {
			randomNumber = random.nextInt(cardList.size() - 1);
			  playerTwoCards.add(cardList.remove(randomNumber));
		}
		playerTwoHand.setCards(playerTwoCards);
		
		for (index = 0; index < 12; index++) {
			randomNumber = random.nextInt(cardList.size() - 1);
			  playerThreeCards.add(cardList.remove(randomNumber));
		}
		playerThreeHand.setCards(playerThreeCards);
		
		for (index = 0; index < 12; index++) {
			randomNumber = random.nextInt(cardList.size() - 1);
			  playerFourCards.add(cardList.remove(randomNumber));
		}
		playerFourHand.setCards(playerFourCards);
	}
	
	/**
	 * Helper function that returns the ordered list of cards.
	 * This list will be used to randomly deal the hands, but
	 * the basis list will always bee the same.
	 * @return A list with all 52 cards in it.
	 */
	private List<Card> generateOrderedCardList() {
		final List<Card> cardList = new ArrayList<Card>();
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
