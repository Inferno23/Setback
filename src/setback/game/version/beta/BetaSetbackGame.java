/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.beta;

import java.util.ArrayList;
import java.util.List;

import setback.game.SetbackGameController;
import setback.game.common.Card;
import setback.game.common.CardSuit;
import setback.game.common.CardType;
import setback.game.version.SetbackGameControllerSkeleton;
import setback.game.version.alpha.DummyBetController;

/**
 * The implementation of SetbackGameController for the Beta version.
 * This version consists of a single round with four tricks.
 * Player One always begins the round, spades are always trump.
 * @author Michael Burns
 * @version Oct 21, 2013
 */
public class BetaSetbackGame extends SetbackGameControllerSkeleton implements
		SetbackGameController {
	
	/**
	 * Constructor for a BetaSetbackGame.  Initializes the state variables.
	 */
	public BetaSetbackGame() {
		gameStarted = false;
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		trickStarted = false;
		betController = new DummyBetController();
	}

	/* (non-Javadoc)
	 * @see setback.game.version.SetbackGameControllerSkeleton#dealHands()
	 */
	@Override
	protected void dealHands() {
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
