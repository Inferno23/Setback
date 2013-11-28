/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.beta;

import java.util.ArrayList;
import java.util.List;

import setback.game.CardDealerController;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;
import setback.game.common.Hand;

/**
 * The implementation of a CardDealerController for the Beta version
 * of Setback.  This dealer will deal hands of four cards that are
 * always the same.  The hands are as follows:
 * <ul>
 * <li>Player One:</li><ul><li>Ace of Spades</li><li>Three of Spades</li><li>Two of Diamonds</li><li>Five of Clubs</li></ul>
 * <li>Player Two:</li><ul><li>Two of Spades</li><li>Ace of Diamonds</li><li>Eight of Clubs</li><li>Three of Hearts</li></ul>
 * <li>Player Three:</li><ul><li>Jack of Spades</li><li>Six of Clubs</li><li>Three of Clubs</li><li>Two of Clubs</li></ul>
 * <li>Player Four:</li><ul><li>Ten of Spades</li><li>Three of Diamonds</li><li>Four of Clubs</li><li>Five of Hearts</li></ul>
 * </ul>
 * @author Michael Burns
 * @version Nov 28, 2013
 */
public class BetaCardDealerController implements CardDealerController {

	/* (non-Javadoc)
	 * @see setback.game.CardDealerController#dealHands(setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand)
	 */
	@Override
	public void dealHands(Hand playerOneHand, Hand playerTwoHand, Hand playerThreeHand, Hand playerFourHand) {
		final List<Card> playerOneCards = new ArrayList<Card>();
		playerOneCards.add(new Card(CardType.ACE, CardSuit.SPADES));
		playerOneCards.add(new Card(CardType.THREE, CardSuit.SPADES));
		playerOneCards.add(new Card(CardType.TWO, CardSuit.DIAMONDS));
		playerOneCards.add(new Card(CardType.FIVE, CardSuit.CLUBS));
		playerOneHand.setCards(playerOneCards);

		final List<Card> playerTwoCards = new ArrayList<Card>();
		playerTwoCards.add(new Card(CardType.TWO, CardSuit.SPADES));
		playerTwoCards.add(new Card(CardType.ACE, CardSuit.DIAMONDS));
		playerTwoCards.add(new Card(CardType.EIGHT, CardSuit.CLUBS));
		playerTwoCards.add(new Card(CardType.THREE, CardSuit.HEARTS));
		playerTwoHand.setCards(playerTwoCards);

		final List<Card> playerThreeCards = new ArrayList<Card>();
		playerThreeCards.add(new Card(CardType.JACK, CardSuit.SPADES));
		playerThreeCards.add(new Card(CardType.SIX, CardSuit.CLUBS));
		playerThreeCards.add(new Card(CardType.THREE, CardSuit.CLUBS));
		playerThreeCards.add(new Card(CardType.TWO, CardSuit.CLUBS));
		playerThreeHand.setCards(playerThreeCards);

		final List<Card> playerFourCards = new ArrayList<Card>();
		playerFourCards.add(new Card(CardType.TEN, CardSuit.SPADES));
		playerFourCards.add(new Card(CardType.THREE, CardSuit.DIAMONDS));
		playerFourCards.add(new Card(CardType.FOUR, CardSuit.CLUBS));
		playerFourCards.add(new Card(CardType.FIVE, CardSuit.HEARTS));
		playerFourHand.setCards(playerFourCards);
	}

}
