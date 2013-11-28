/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import setback.game.common.Hand;

/**
 * The CardDealerController is the interface for any version of a
 * card dealer.  The purpose of this interface is to encapsulate
 * the dealing of cards, and remove it from the SetbackGameController.
 * @author Michael Burns
 * @version Nov 28, 2013
 */
public interface CardDealerController {

	/**
	 *
	 * This function deals cards to each of the four players.
	 * The method of determining these cards varies from version to version.
	 * Alpha: Each hand gets a single predetermined card.
	 * Beta: Each hand gets four predetermined cards.
	 * Delta: Each hand gets 12 randomly selected cards.
	 * @param playerOneHand Player One's Hand.
	 * @param playerTwoHand Player Two's Hand.
	 * @param playerThreeHand Player Three's Hand.
	 * @param playerFourHand Player Four's Hand.
	 */
	void dealHands(Hand playerOneHand, Hand playerTwoHand, Hand playerThreeHand, Hand playerFourHand);
}
