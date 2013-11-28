/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import java.util.ArrayList;
import java.util.List;

import setback.game.CardDealerController;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;
import setback.game.common.Hand;

/**
 * The implementation of a CardDealerController for the Alpha version
 * of Setback.  This dealer will deal hands of a single card that is
 * always the same.  The hands are as follows:
 * <ul>
 * <li>Player One:</li><ul><li>Ace of Spades</li></ul>
 * <li>Player Two:</li><ul><li>Two of Spades</li></ul>
 * <li>Player Three:</li><ul><li>Jack of Spades</li></ul>
 * <li>Player Four:</li><ul><li>Ten of Spades</li></ul>
 * </ul>
 * @author Michael Burns
 * @version Nov 28, 2013
 */
public class AlphaCardDealerController implements CardDealerController {

	/*
	 * (non-Javadoc)
	 * @see setback.game.CardDealerController#dealHands(setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand)
	 */
	@Override
	public void dealHands(Hand playerOneHand, Hand playerTwoHand, Hand playerThreeHand, Hand playerFourHand) {
		final List<Card> playerOneCards = new ArrayList<Card>();
		playerOneCards.add(new Card(CardType.ACE, CardSuit.SPADES));
		playerOneHand.setCards(playerOneCards);
		
		final List<Card> playerTwoCards = new ArrayList<Card>();
		playerTwoCards.add(new Card(CardType.TWO, CardSuit.SPADES));
		playerTwoHand.setCards(playerTwoCards);
		
		final List<Card> playerThreeCards = new ArrayList<Card>();
		playerThreeCards.add(new Card(CardType.JACK, CardSuit.SPADES));
		playerThreeHand.setCards(playerThreeCards);
		
		final List<Card> playerFourCards = new ArrayList<Card>();
		playerFourCards.add(new Card(CardType.TEN, CardSuit.SPADES));
		playerFourHand.setCards(playerFourCards);
	}

}
