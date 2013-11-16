/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import setback.common.PlayerNumber;

/**
 * This class represents a single trick in a round of Setback.
 * It contains information on what four cards were played,
 * and by whom they were played.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class Trick {

	private final CardPlayerDescriptor firstCard;
	private final CardPlayerDescriptor secondCard;
	private final CardPlayerDescriptor thirdCard;
	private final CardPlayerDescriptor fourthCard;

	/**
	 * Constructor that sets the properties of a Trick.
	 * @param firstCard The first card and who played it.
	 * @param secondCard The second card and who played it,.
	 * @param thirdCard The third card and who played it.
	 * @param fourthCard The fourth card and who played it.
	 */
	public Trick(CardPlayerDescriptor firstCard,
			CardPlayerDescriptor secondCard,
			CardPlayerDescriptor thirdCard,
			CardPlayerDescriptor fourthCard) {

		this.firstCard = firstCard;
		this.secondCard = secondCard;
		this.thirdCard = thirdCard;
		this.fourthCard = fourthCard;
	}

	/**
	 * @return the firstCard.
	 */
	public CardPlayerDescriptor getFirstCard() {
		return firstCard;
	}

	/**
	 * @return the secondCard.
	 */
	public CardPlayerDescriptor getSecondCard() {
		return secondCard;
	}

	/**
	 * @return the thirdCard.
	 */
	public CardPlayerDescriptor getThirdCard() {
		return thirdCard;
	}

	/**
	 * @return the fourthCard.
	 */
	public CardPlayerDescriptor getFourthCard() {
		return fourthCard;
	}

	/**
	 * Determines the winner of the trick based on the cards
	 * played and the given trump suit.
	 * @param trump The suit that is trump for the round.
	 * @return The winner of the trick, along with how many game
	 * points were involved, the presence of the jack of trump,
	 * and any high/low candidates.
	 */
	public TrickResult determineTrickResults(CardSuit trump) {

		final PlayerNumber winner = determineTrickWinner(trump);
		final int gamePoints = calculateGamePoints();
		final boolean jackOfTrump = isJackOfTrump(trump);
		final Card lowCandidate = determineLowCandidate(trump);
		final Card highCandidate = determineHighCandidate(trump);

		return new TrickResult(winner, gamePoints, jackOfTrump, lowCandidate, highCandidate);
	}

	/**
	 * Helper function that determines the winner of the trick.
	 * @param trump The suit that is trump for the round.
	 * @return The winner of the trick.
	 */
	private PlayerNumber determineTrickWinner(CardSuit trump) {

		// First card is the initial candidate
		CardPlayerDescriptor candidate = firstCard;

		// Second card vs. candidate
		if (secondCard.getCard().getSuit().equals(candidate.getCard().getSuit())) {
			// The suits are matching, so compare the values
			if (secondCard.getCard().getType().getStandardValue() >
					candidate.getCard().getType().getStandardValue()) {
				// The new card is a higher value, so it is the new candidate.
				candidate = secondCard;
			}
		}
		else if (secondCard.getCard().getSuit().equals(trump)) {
			// The new card is trump and the other is not, so it is the new candidate
			candidate = secondCard;
		}
		
		// Third card vs. candidate
		if (thirdCard.getCard().getSuit().equals(candidate.getCard().getSuit())) {
			// The suits are matching, so compare the values
			if (thirdCard.getCard().getType().getStandardValue() >
					candidate.getCard().getType().getStandardValue()) {
				// The new card is a higher value, so it is the new candidate.
				candidate = thirdCard;
			}
		}
		else if (thirdCard.getCard().getSuit().equals(trump)) {
			// The new card is trump and the other is not, so it is the new candidate
			candidate = thirdCard;
		}
		
		// Fourth card vs. candidate
		if (fourthCard.getCard().getSuit().equals(candidate.getCard().getSuit())) {
			// The suits are matching, so compare the values
			if (fourthCard.getCard().getType().getStandardValue() >
					candidate.getCard().getType().getStandardValue()) {
				// The new card is a higher value, so it is the new candidate.
				candidate = fourthCard;
			}
		}
		else if (fourthCard.getCard().getSuit().equals(trump)) {
			// The new card is trump and the other is not, so it is the new candidate
			candidate = fourthCard;
		}


		return candidate.getPlayer();
	}

	/**
	 * Helper function that calculates the number of game points
	 * in the current trick.
	 * @return The number of game points in the trick.
	 */
	private int calculateGamePoints() {
		int gamePoints = 0;
		gamePoints += firstCard.getCard().getType().getGameValue();
		gamePoints += secondCard.getCard().getType().getGameValue();
		gamePoints += thirdCard.getCard().getType().getGameValue();
		gamePoints += fourthCard.getCard().getType().getGameValue();
		return gamePoints;
	}

	/**
	 * Helper function that determines if the Jack of Trump was played.
	 * @return True if the Jack of Trump was played, else false.
	 */
	private boolean isJackOfTrump(CardSuit trump) {
		boolean result = (CardType.JACK.equals(firstCard.getCard().getType()) && trump.equals(firstCard.getCard().getSuit()));
		result = result || (CardType.JACK.equals(secondCard.getCard().getType()) && trump.equals(secondCard.getCard().getSuit()));
		result = result || (CardType.JACK.equals(thirdCard.getCard().getType()) && trump.equals(thirdCard.getCard().getSuit()));
		result = result || (CardType.JACK.equals(fourthCard.getCard().getType()) && trump.equals(fourthCard.getCard().getSuit()));
		return result;
	}

	/**
	 * Helper function that determines what the lowest trump
	 * card was played.
	 * @return The lowest trump card played or null.
	 */
	private Card determineLowCandidate(CardSuit trump) {
		Card result = null;
		if (trump.equals(firstCard.getCard().getSuit())) {
			result = firstCard.getCard();
		}
		if (trump.equals(secondCard.getCard().getSuit())) {
			if (result == null) {
				result = secondCard.getCard();
			}
			else if (secondCard.getCard().getType().getStandardValue()
					< result.getType().getStandardValue()) {
				result = secondCard.getCard();
			}
		}
		if (trump.equals(thirdCard.getCard().getSuit())) {
			if (result == null) {
				result = thirdCard.getCard();
			}
			else if (thirdCard.getCard().getType().getStandardValue()
					< result.getType().getStandardValue()) {
				result = thirdCard.getCard();
			}
		}
		if (trump.equals(fourthCard.getCard().getSuit())) {
			if (result == null) {
				result = fourthCard.getCard();
			}
			else if (fourthCard.getCard().getType().getStandardValue()
					< result.getType().getStandardValue()) {
				result = fourthCard.getCard();
			}
		}
		return result;
	}

	/**
	 * Helper function that determines what the highest trump
	 * card was played.
	 * @return The highest trump card played or null.
	 */
	private Card determineHighCandidate(CardSuit trump) {
		Card result = null;
		if (trump.equals(firstCard.getCard().getSuit())) {
			result = firstCard.getCard();
		}
		if (trump.equals(secondCard.getCard().getSuit())) {
			if (result == null) {
				result = secondCard.getCard();
			}
			else if (secondCard.getCard().getType().getStandardValue()
					> result.getType().getStandardValue()) {
				result = secondCard.getCard();
			}
		}
		if (trump.equals(thirdCard.getCard().getSuit())) {
			if (result == null) {
				result = thirdCard.getCard();
			}
			else if (thirdCard.getCard().getType().getStandardValue()
					> result.getType().getStandardValue()) {
				result = thirdCard.getCard();
			}
		}
		if (trump.equals(fourthCard.getCard().getSuit())) {
			if (result == null) {
				result = fourthCard.getCard();
			}
			else if (fourthCard.getCard().getType().getStandardValue()
					> result.getType().getStandardValue()) {
				result = fourthCard.getCard();
			}
		}
		return result;
	}
}
