/**
 * This file was developed for fun by Michael Burns for a private
 * implementation-of-the card game Setback, also known as Pitch.
 */
package setback.game.common;

import static org.junit.Assert.*;
import static setback.utilities.TestCard.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import setback.common.PlayerNumber;

/**
 * Tests for the materials in the game.common package.
 * For example, the CardType enum is covered here.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public class SetbackGameCommonTest {

	////////////////
	// Card tests //
	////////////////

	@Test
	public void aceOfSpadesTest() {
		assertEquals(CardSuit.SPADES, aceOfSpades.getSuit());
		assertEquals(CardType.ACE, aceOfSpades.getType());
		assertEquals(14, aceOfSpades.getType().getStandardValue());
		assertEquals(4, aceOfSpades.getType().getGameValue());
		assertEquals("Ace-of-Spades", aceOfSpades.toString());
	}

	@Test
	public void twoOfSpadesTest() {
		assertEquals(CardSuit.SPADES, twoOfSpades.getSuit());
		assertEquals(CardType.TWO, twoOfSpades.getType());
		assertEquals(0, twoOfSpades.getType().getGameValue());
		assertEquals("Two-of-Spades", twoOfSpades.toString());
	}


	@Test
	public void threeOfSpadesTest() {
		assertEquals(CardSuit.SPADES, threeOfSpades.getSuit());
		assertEquals(CardType.THREE, threeOfSpades.getType());
		assertEquals(0, threeOfSpades.getType().getGameValue());
		assertEquals("Three-of-Spades", threeOfSpades.toString());
	}

	@Test
	public void fourOfSpadesTest() {
		assertEquals(CardSuit.SPADES, fourOfSpades.getSuit());
		assertEquals(CardType.FOUR, fourOfSpades.getType());
		assertEquals(0, fourOfSpades.getType().getGameValue());
		assertEquals("Four-of-Spades", fourOfSpades.toString());
	}

	@Test
	public void fiveOfSpadesTest() {
		assertEquals(CardSuit.SPADES, fiveOfSpades.getSuit());
		assertEquals(CardType.FIVE, fiveOfSpades.getType());
		assertEquals(0, fiveOfSpades.getType().getGameValue());
		assertEquals("Five-of-Spades", fiveOfSpades.toString());
	}

	@Test
	public void sixOfSpadesTest() {
		assertEquals(CardSuit.SPADES, sixOfSpades.getSuit());
		assertEquals(CardType.SIX, sixOfSpades.getType());
		assertEquals(0, sixOfSpades.getType().getGameValue());
		assertEquals("Six-of-Spades", sixOfSpades.toString());
	}

	@Test
	public void sevenOfSpadesTest() {
		assertEquals(CardSuit.SPADES, sevenOfSpades.getSuit());
		assertEquals(CardType.SEVEN, sevenOfSpades.getType());
		assertEquals(0, sevenOfSpades.getType().getGameValue());
		assertEquals("Seven-of-Spades", sevenOfSpades.toString());
	}

	@Test
	public void eightOfSpadesTest() {
		assertEquals(CardSuit.SPADES, eightOfSpades.getSuit());
		assertEquals(CardType.EIGHT, eightOfSpades.getType());
		assertEquals(0, eightOfSpades.getType().getGameValue());
		assertEquals("Eight-of-Spades", eightOfSpades.toString());
	}

	@Test
	public void nineOfSpadesTest() {
		assertEquals(CardSuit.SPADES, nineOfSpades.getSuit());
		assertEquals(CardType.NINE, nineOfSpades.getType());
		assertEquals(0, nineOfSpades.getType().getGameValue());
		assertEquals("Nine-of-Spades", nineOfSpades.toString());
	}

	@Test
	public void tenOfSpadesTest() {
		assertEquals(CardSuit.SPADES, tenOfSpades.getSuit());
		assertEquals(CardType.TEN, tenOfSpades.getType());
		assertEquals(10, tenOfSpades.getType().getGameValue());
		assertEquals("Ten-of-Spades", tenOfSpades.toString());
	}

	@Test
	public void jackOfSpadesTest() {
		assertEquals(CardSuit.SPADES, jackOfSpades.getSuit());
		assertEquals(CardType.JACK, jackOfSpades.getType());
		assertEquals(1, jackOfSpades.getType().getGameValue());
		assertEquals("Jack-of-Spades", jackOfSpades.toString());
	}

	@Test
	public void queenOfSpadesTest() {
		assertEquals(CardSuit.SPADES, queenOfSpades.getSuit());
		assertEquals(CardType.QUEEN, queenOfSpades.getType());
		assertEquals(2, queenOfSpades.getType().getGameValue());
		assertEquals("Queen-of-Spades", queenOfSpades.toString());
	}

	@Test
	public void kingOfSpadesTest() {
		assertEquals(CardSuit.SPADES, kingOfSpades.getSuit());
		assertEquals(CardType.KING, kingOfSpades.getType());
		assertEquals(3, kingOfSpades.getType().getGameValue());
		assertEquals("King-of-Spades", kingOfSpades.toString());
	}

	////////////////////
	// Coverage tests //
	////////////////////

	@Test
	public void cardTypeCoverageTest() {
		CardType[] types = CardType.values();
		assertEquals(13, types.length);
		assertEquals(CardType.ACE, CardType.valueOf("ACE"));
		assertEquals("Ace", CardType.ACE.getPrintableName());
	}

	@Test
	public void cardSuitCoverageTest() {
		CardSuit[] suits = CardSuit.values();
		assertEquals(4, suits.length);
		assertEquals(CardSuit.SPADES, CardSuit.valueOf("SPADES"));
		assertEquals("Spades", CardSuit.SPADES.getPrintableName());
	}

	@Test
	public void cardPlayerDescriptorTest() {
		CardPlayerDescriptor aceOfSpadesPlayerOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);

		assertEquals(aceOfSpades, aceOfSpadesPlayerOne.getCard());
		assertEquals(PlayerNumber.PLAYER_ONE, aceOfSpadesPlayerOne.getPlayer());

		CardPlayerDescriptor aceOfSpadesPlayerTwo = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor aceOfHeartsPlayerOne = new CardPlayerDescriptor(aceOfHearts, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor aceOfHeartsPlayerTwo = new CardPlayerDescriptor(aceOfHearts, PlayerNumber.PLAYER_TWO);

		assertTrue(aceOfSpadesPlayerOne.equals(new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE)));
		assertTrue(aceOfSpadesPlayerOne.equals(aceOfSpadesPlayerOne));
		assertFalse(aceOfSpadesPlayerOne.equals(aceOfSpadesPlayerTwo));
		assertFalse(aceOfSpadesPlayerOne.equals(aceOfHeartsPlayerOne));
		assertFalse(aceOfSpadesPlayerOne.equals(aceOfHeartsPlayerTwo));
		assertFalse(aceOfSpadesPlayerOne.equals(aceOfSpades));
		assertFalse(aceOfSpadesPlayerOne.hashCode() == 0);
	}

	@Test
	public void cardCoverageTest() {
		aceOfSpades.equals(new CardPlayerDescriptor(aceOfSpades, null));
	}

	@Test
	public void trickCoverageTest() {
		Trick trick = new Trick(new CardPlayerDescriptor(aceOfSpades, null),
				new CardPlayerDescriptor(twoOfSpades, null),
				new CardPlayerDescriptor(jackOfSpades, null),
				new CardPlayerDescriptor(tenOfSpades, null));

		assertEquals(aceOfSpades, trick.getFirstCard().getCard());
		assertEquals(twoOfSpades, trick.getSecondCard().getCard());
		assertEquals(jackOfSpades, trick.getThirdCard().getCard());
		assertEquals(tenOfSpades, trick.getFourthCard().getCard());
	}
	
	@Test
	public void handGetOwnerTest() {
		Hand playerOneHand = new Hand(PlayerNumber.PLAYER_ONE);
		assertEquals(PlayerNumber.PLAYER_ONE, playerOneHand.getOwner());
	}
	
	@Test
	public void roundGetTricksTest() {
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(new TrickResult(PlayerNumber.PLAYER_ONE, 15, true, twoOfSpades, aceOfSpades));
		Round round = new Round(tricks);
		assertEquals(tricks, round.getTricks());
	}
	
	@Test
	public void betCoverageTest() {
		Bet[] bets = Bet.values();
		assertEquals(6, bets.length);
		assertEquals(Bet.TWO, Bet.valueOf("TWO"));
		assertEquals("Two", Bet.TWO.getPrintableString());
		assertEquals("Two", Bet.TWO.toString());
	}
	
	@Test
	public void betResultCoverageTest() {
		BetResult result = new BetResult(PlayerNumber.PLAYER_ONE, Bet.TWO);
		assertEquals(PlayerNumber.PLAYER_ONE, result.getBettor());
		assertEquals(Bet.TWO, result.getBet());
	}
	
	///////////////////////////////
	// Some trick coverage tests //
	///////////////////////////////
	
	@Test
	public void firstCardNotTrumpSecondCardTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(twoOfClubs, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(threeOfClubs,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(fourOfClubs, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_TWO, result.getWinner());
		assertEquals(aceOfSpades, result.getHighCandidate());
		assertEquals(aceOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void firstCardNotTrumpThirdCardTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(twoOfClubs, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(threeOfClubs, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(aceOfSpades,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(fourOfClubs, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_THREE, result.getWinner());
		assertEquals(aceOfSpades, result.getHighCandidate());
		assertEquals(aceOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void firstCardNotTrumpFourthCardTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(twoOfClubs, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(threeOfClubs, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(fourOfClubs,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getWinner());
		assertEquals(aceOfSpades, result.getHighCandidate());
		assertEquals(aceOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void firstCardTrumpFourthCardHigherTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(threeOfClubs, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(fourOfClubs,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getWinner());
		assertEquals(aceOfSpades, result.getHighCandidate());
		assertEquals(twoOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void descendingTrumpCards() {
		Trick trick = new Trick(new CardPlayerDescriptor(fiveOfSpades, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(fourOfSpades, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(threeOfSpades,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_ONE, result.getWinner());
		assertEquals(fiveOfSpades, result.getHighCandidate());
		assertEquals(twoOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(0, result.getGamePoints());
	}
	
	@Test
	public void ascendingTrumpCards() {
		Trick trick = new Trick(new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(threeOfSpades, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(fourOfSpades,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(fiveOfSpades, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getWinner());
		assertEquals(fiveOfSpades, result.getHighCandidate());
		assertEquals(twoOfSpades, result.getLowCandidate());
		assertFalse(result.isJackOfTrump());
		assertEquals(0, result.getGamePoints());
	}
	
	@Test
	public void allJacksWithFirstAsTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(jackOfHearts, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(jackOfDiamonds,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(jackOfClubs, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_ONE, result.getWinner());
		assertEquals(jackOfSpades, result.getHighCandidate());
		assertEquals(jackOfSpades, result.getLowCandidate());
		assertTrue(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void allJacksWithSecondAsTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(jackOfHearts, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(jackOfDiamonds,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(jackOfClubs, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_TWO, result.getWinner());
		assertEquals(jackOfSpades, result.getHighCandidate());
		assertEquals(jackOfSpades, result.getLowCandidate());
		assertTrue(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void allJacksWithThirdAsTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(jackOfHearts, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(jackOfDiamonds, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(jackOfSpades,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(jackOfClubs, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_THREE, result.getWinner());
		assertEquals(jackOfSpades, result.getHighCandidate());
		assertEquals(jackOfSpades, result.getLowCandidate());
		assertTrue(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}
	
	@Test
	public void allJacksWithLastAsTrump() {
		Trick trick = new Trick(new CardPlayerDescriptor(jackOfClubs, PlayerNumber.PLAYER_ONE),
				new CardPlayerDescriptor(jackOfHearts, PlayerNumber.PLAYER_TWO),
				new CardPlayerDescriptor(jackOfDiamonds,PlayerNumber.PLAYER_THREE), 
				new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_FOUR));
		TrickResult result = trick.determineTrickResults(CardSuit.SPADES);
		assertEquals(PlayerNumber.PLAYER_FOUR, result.getWinner());
		assertEquals(jackOfSpades, result.getHighCandidate());
		assertEquals(jackOfSpades, result.getLowCandidate());
		assertTrue(result.isJackOfTrump());
		assertEquals(4, result.getGamePoints());
	}

	@Test
	public void roundResultStatusCoverageTest() {
		RoundResultStatus[] status = RoundResultStatus.values();
		assertEquals(3, status.length);
		assertEquals(RoundResultStatus.OK, RoundResultStatus.valueOf("OK"));
	}
	
	@Test
	public void cardEqualsNullTest() {
		assertFalse(jackOfSpades.equals(null));
	}
	
	@Test
	public void nullCardHashTest() {
		Card fakeCard = new Card(null, null);
		assertEquals(31*31, fakeCard.hashCode());
	}
	
	@Test
	public void handPrintingTest() {
		Hand hand = new Hand(PlayerNumber.PLAYER_ONE);
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(aceOfClubs);
		cardList.add(twoOfHearts);
		hand.setCards(cardList);
		
		String equalityString = "PLAYER_ONE'S HAND:\nAce-of-Clubs\nTwo-of-Hearts\n";
		
		assertEquals(equalityString, hand.toString());
	}
	
	@Test
	public void handSortingTest() {
		Hand hand = new Hand(PlayerNumber.PLAYER_ONE);
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(threeOfSpades);
		cardList.add(sixOfClubs);
		cardList.add(aceOfHearts);
		cardList.add(fourOfHearts);
		cardList.add(tenOfDiamonds);
		cardList.add(sevenOfDiamonds);
		cardList.add(aceOfSpades);
		cardList.add(sixOfSpades);
		cardList.add(queenOfDiamonds);
		cardList.add(jackOfDiamonds);
		cardList.add(aceOfDiamonds);
		cardList.add(sevenOfClubs);
		hand.setCards(cardList);
		hand.sortCards();
		
		String equalityString = "PLAYER_ONE'S HAND:\nThree-of-Spades\nSix-of-Spades\nAce-of-Spades\n"
				+ "Four-of-Hearts\nAce-of-Hearts\nSix-of-Clubs\nSeven-of-Clubs\nSeven-of-Diamonds\n"
				+ "Ten-of-Diamonds\nJack-of-Diamonds\nQueen-of-Diamonds\nAce-of-Diamonds\n";
		
		assertEquals(equalityString, hand.toString());
	}
}
