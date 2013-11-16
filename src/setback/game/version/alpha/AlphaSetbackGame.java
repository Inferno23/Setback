/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.alpha;

import java.util.ArrayList;
import java.util.List;

import setback.game.SetbackGameController;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;
import setback.game.version.SetbackGameControllerSkeleton;

/**
 * The implementation of SetbackGameController for the Alpha version.
 * This version consists of a single round with a single trick.
 * Player One always goes first, spades are always trump.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class AlphaSetbackGame  extends SetbackGameControllerSkeleton implements SetbackGameController {

	/**
	 * Constructor for an AlphaSetbackGame.  Initializes the state variables.
	 */
	public AlphaSetbackGame() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		trickStarted = false;
		betController = new DummyBetController();
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.version.SetbackGameControllerSkeleton#dealHands()
	 */
	protected void dealHands() {
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
