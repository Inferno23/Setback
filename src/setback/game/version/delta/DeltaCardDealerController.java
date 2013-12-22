/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch. 
 */
package setback.game.version.delta;

import static setback.utilities.UtilityFunctions.generateOrderedCardList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import setback.game.CardDealerController;
import setback.game.common.Card;
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
		this.random = new Random(seed);
	}

	/* (non-Javadoc)
	 * @see setback.game.CardDealerController#dealHands(setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand, setback.game.common.Hand)
	 */
	@Override
	public void dealHands(Hand playerOneHand, Hand playerTwoHand,
			Hand playerThreeHand, Hand playerFourHand) {
		
		List<Card> cardList = generateOrderedCardList();
		List<Card> playerOneCards = new ArrayList<Card>();
		List<Card> playerTwoCards = new ArrayList<Card>();
		List<Card> playerThreeCards = new ArrayList<Card>();
		List<Card> playerFourCards = new ArrayList<Card>();
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
}
