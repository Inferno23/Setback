/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import static org.junit.Assert.assertEquals;
import static setback.utilities.TestCard.aceOfDiamonds;
import static setback.utilities.TestCard.aceOfSpades;
import static setback.utilities.TestCard.eightOfClubs;
import static setback.utilities.TestCard.fiveOfClubs;
import static setback.utilities.TestCard.fiveOfHearts;
import static setback.utilities.TestCard.fourOfClubs;
import static setback.utilities.TestCard.jackOfSpades;
import static setback.utilities.TestCard.sixOfClubs;
import static setback.utilities.TestCard.tenOfSpades;
import static setback.utilities.TestCard.threeOfClubs;
import static setback.utilities.TestCard.threeOfDiamonds;
import static setback.utilities.TestCard.threeOfHearts;
import static setback.utilities.TestCard.threeOfSpades;
import static setback.utilities.TestCard.twoOfClubs;
import static setback.utilities.TestCard.twoOfDiamonds;
import static setback.utilities.TestCard.twoOfSpades;
import static setback.utilities.TestUtilityFunctions.playerOneWinsBet;
import static setback.utilities.TestUtilityFunctions.teamOneLosesRoundOnBet;
import static setback.utilities.TestUtilityFunctions.teamOneWinsRoundOffBet;
import static setback.utilities.TestUtilityFunctions.teamOneWinsRoundOnBet;
import static setback.utilities.TestUtilityFunctions.teamTwoWinsRoundOnBet;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;
import setback.game.common.Bet;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.RoundResult;
import setback.game.common.RoundResultStatus;
import setback.game.common.TrickResult;

/**
 * This class will test the Gamma version of Setback
 * @author Michael Burns
 * @version Oct 30, 2013
 */
public class GammaSetbackTest {

	private SetbackGameController game;
	private SetbackGameFactory factory;
	private SetbackGameController mockGame;

	@Before
	public void setup() {
		factory = SetbackGameFactory.getInstance();
		game = factory.makeGammaSetbackGame();
		mockGame = null;
	}
	
	/**
	 * Executes the playing of a round where PlayerOne goes first.
	 * @return A RoundResult with the results of the round.
	 * @throws SetbackException If something goes wrong.
	 */
	private RoundResult playLegalRoundPlayerOneFirst() throws SetbackException {
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		
		game.startTrick();		
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(threeOfHearts, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fiveOfHearts, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour, cpdRoundThreePlayerOne);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundFourPlayerOne = game.playCard(fiveOfClubs, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundFourPlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundFourPlayerThree = game.playCard(twoOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundFourPlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		TrickResult resultFour = game.playTrick(cpdRoundFourPlayerOne, cpdRoundFourPlayerTwo, cpdRoundFourPlayerThree, cpdRoundFourPlayerFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(resultOne);
		tricks.add(resultTwo);
		tricks.add(resultThree);
		tricks.add(resultFour);
		
		return game.playRound(tricks);
	}
	
	/**
	 * Executes the playing of a round where PlayerTwo goes first.
	 * @return A RoundResult with the results of the round.
	 * @throws SetbackException If something goes wrong.
	 */
	private RoundResult playLegalRoundPlayerTwoFirst() throws SetbackException {
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour, cpdRoundOnePlayerOne);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		
		game.startTrick();		
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(threeOfHearts, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fiveOfHearts, PlayerNumber.PLAYER_FOUR);
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour, cpdRoundThreePlayerOne);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundFourPlayerOne = game.playCard(fiveOfClubs, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundFourPlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundFourPlayerThree = game.playCard(twoOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundFourPlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		TrickResult resultFour = game.playTrick(cpdRoundFourPlayerOne, cpdRoundFourPlayerTwo, cpdRoundFourPlayerThree, cpdRoundFourPlayerFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(resultOne);
		tricks.add(resultTwo);
		tricks.add(resultThree);
		tricks.add(resultFour);
		
		return game.playRound(tricks);
	}
	
	/**
	 * Executes the playing of a round where Team One wins every trick.
	 * @return A RoundResult with the results of the round.
	 * @throws SetbackException If something goes wrong.
	 */
	private RoundResult teamOneHitsFiveBet() throws SetbackException {
		CardPlayerDescriptor cpdRoundOnePlayerOne = game.playCard(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundOnePlayerTwo = game.playCard(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundOnePlayerThree = game.playCard(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundOnePlayerFour = game.playCard(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		TrickResult resultOne = game.playTrick(cpdRoundOnePlayerOne, cpdRoundOnePlayerTwo, cpdRoundOnePlayerThree, cpdRoundOnePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundTwoPlayerOne = game.playCard(threeOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundTwoPlayerTwo = game.playCard(aceOfDiamonds, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundTwoPlayerThree = game.playCard(sixOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundTwoPlayerFour = game.playCard(threeOfDiamonds, PlayerNumber.PLAYER_FOUR);
		TrickResult resultTwo = game.playTrick(cpdRoundTwoPlayerOne, cpdRoundTwoPlayerTwo, cpdRoundTwoPlayerThree, cpdRoundTwoPlayerFour);
		
		game.startTrick();		
		CardPlayerDescriptor cpdRoundThreePlayerOne = game.playCard(twoOfDiamonds, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundThreePlayerTwo = game.playCard(eightOfClubs, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundThreePlayerThree = game.playCard(threeOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundThreePlayerFour = game.playCard(fourOfClubs, PlayerNumber.PLAYER_FOUR);
		TrickResult resultThree = game.playTrick(cpdRoundThreePlayerOne, cpdRoundThreePlayerTwo, cpdRoundThreePlayerThree, cpdRoundThreePlayerFour);
		
		game.startTrick();
		CardPlayerDescriptor cpdRoundFourPlayerOne = game.playCard(fiveOfClubs, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdRoundFourPlayerTwo = game.playCard(threeOfHearts, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdRoundFourPlayerThree = game.playCard(twoOfClubs, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdRoundFourPlayerFour = game.playCard(fiveOfHearts, PlayerNumber.PLAYER_FOUR);
		TrickResult resultFour = game.playTrick(cpdRoundFourPlayerOne, cpdRoundFourPlayerTwo, cpdRoundFourPlayerThree, cpdRoundFourPlayerFour);
		
		List<TrickResult> tricks = new ArrayList<TrickResult>();
		tricks.add(resultOne);
		tricks.add(resultTwo);
		tricks.add(resultThree);
		tricks.add(resultFour);
		
		return game.playRound(tricks);
	}
	
	//////////////////////
	// Start of testing //
	//////////////////////
	
	@Test(expected=SetbackException.class)
	public void attemptToBetBeforeStartingGame() throws SetbackException {
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToBetBeforeStartingRound() throws SetbackException {
		game.startGame();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToStartTrickBeforeBetting() throws SetbackException {
		game.startGame();
		game.startRound();
		game.startTrick();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToStartTrickBeforeSelectingTrump() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.startTrick();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlayTrickBeforeBetting() throws SetbackException {
		game.startGame();
		game.startRound();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlayTrickBeforeSelectingTrump() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		
		CardPlayerDescriptor cpdOne = new CardPlayerDescriptor(aceOfSpades, PlayerNumber.PLAYER_ONE);
		CardPlayerDescriptor cpdTwo = new CardPlayerDescriptor(twoOfSpades, PlayerNumber.PLAYER_TWO);
		CardPlayerDescriptor cpdThree = new CardPlayerDescriptor(jackOfSpades, PlayerNumber.PLAYER_THREE);
		CardPlayerDescriptor cpdFour = new CardPlayerDescriptor(tenOfSpades, PlayerNumber.PLAYER_FOUR);
		
		game.playTrick(cpdOne, cpdTwo, cpdThree, cpdFour);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToPlaceBetAfterResolving() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
	}
		
	@Test(expected=SetbackException.class)
	public void attemptToSelectTrumpBeforeStartingGame() throws SetbackException {
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToSelectTrumpBeforeStartingRound() throws SetbackException {
		game.startGame();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToSelectTrumpBeforeResolving() throws SetbackException {
		game.startGame();
		game.startRound();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToSelectTrumpAfterSelecting() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToResolveBeforeStartingGame() throws SetbackException {
		game.resolveBets();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToResolveBeforeStartingRound() throws SetbackException {
		game.startGame();
		game.resolveBets();
	}
	
	@Test(expected=SetbackException.class)
	public void attemptToResolveAfterResolving() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.resolveBets();
	}
	
	@Test
	public void playerOneWinsBetsAndSelectsTrump() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
	}
	
	@Test(expected=SetbackException.class)
	public void playerOneWinsBetsAndPlayerTwoAttemptsToSelectTrump() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_TWO, CardSuit.SPADES);
	}
	
	@Test
	public void teamOneBetsTwoGetsFour() throws SetbackException {
		game.startGame();
		game.startRound();
		playerOneWinsBet(game);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		RoundResult roundResult = playLegalRoundPlayerOneFirst();
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}

	@Test
	public void teamTwoBetsTwoGetsNegative() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.TWO);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_TWO, CardSuit.SPADES);
		game.startTrick();
		
		RoundResult roundResult = playLegalRoundPlayerTwoFirst();
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(-2, roundResult.getTeamTwoRoundScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamOneBetsFiveAndFails() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.FIVE);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		RoundResult roundResult = playLegalRoundPlayerOneFirst();
		assertEquals(-5, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamOneBetsFiveAndSucceeds() throws SetbackException {
		game.startGame();
		game.startRound();
		game.placeBet(PlayerNumber.PLAYER_ONE, Bet.FIVE);
		game.placeBet(PlayerNumber.PLAYER_TWO, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_THREE, Bet.PASS);
		game.placeBet(PlayerNumber.PLAYER_FOUR, Bet.PASS);
		game.resolveBets();
		game.selectTrump(PlayerNumber.PLAYER_ONE, CardSuit.SPADES);
		game.startTrick();
		
		RoundResult roundResult = teamOneHitsFiveBet();
		assertEquals(5, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test(expected=SetbackException.class)
	public void getTeamOneScoreBeforeGame() throws SetbackException {
		game.getTeamOneScore();
	}
	
	@Test(expected=SetbackException.class)
	public void getTeamTwoScoreBeforeGame() throws SetbackException {
		game.getTeamTwoScore();
	}
	
	///////////////////////////
	// End of the game tests //
	///////////////////////////
	
	@Test
	public void teamOneHitsTwentyOneOnBetAndWins() throws SetbackException {
		mockGame = new MockGammaSetbackController(17, 0);
		RoundResult roundResult = teamOneWinsRoundOnBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(21, mockGame.getTeamOneScore());
		assertEquals(0, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_ONE_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamOneExceedsTwentyOneOnBetAndWins() throws SetbackException {
		mockGame = new MockGammaSetbackController(18, 0);
		RoundResult roundResult = teamOneWinsRoundOnBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(22, mockGame.getTeamOneScore());
		assertEquals(0, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_ONE_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamOneHitsTwentyOneOnBetButWithinTwo() throws SetbackException {
		mockGame = new MockGammaSetbackController(17, 20);
		RoundResult roundResult = teamOneWinsRoundOnBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(0, roundResult.getTeamTwoRoundScore());
		assertEquals(21, mockGame.getTeamOneScore());
		assertEquals(20, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamOneFallsBelowNegativeEleven() throws SetbackException {
		mockGame = new MockGammaSetbackController(-7, 0);
		RoundResult roundResult = teamOneLosesRoundOnBet(mockGame);
		assertEquals(-5, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(-12, mockGame.getTeamOneScore());
		assertEquals(3, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_TWO_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamOneFallsToNegativeEleven() throws SetbackException {
		mockGame = new MockGammaSetbackController(-6, 0);
		RoundResult roundResult = teamOneLosesRoundOnBet(mockGame);
		assertEquals(-5, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(-11, mockGame.getTeamOneScore());
		assertEquals(3, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_TWO_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamOneHitsTwentyOneOffBet() throws SetbackException {
		mockGame = new MockGammaSetbackController(17, 0);
		RoundResult roundResult = teamOneWinsRoundOffBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(-2, roundResult.getTeamTwoRoundScore());
		assertEquals(21, mockGame.getTeamOneScore());
		assertEquals(-2, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoHitsTwentyOneOffBet() throws SetbackException {
		mockGame = new MockGammaSetbackController(15, 18);
		RoundResult roundResult = teamOneLosesRoundOnBet(mockGame);
		assertEquals(-5, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(10, mockGame.getTeamOneScore());
		assertEquals(21, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoHitsTwentyOneOnBetAndWins() throws SetbackException {
		mockGame = new MockGammaSetbackController(0, 18);
		RoundResult roundResult = teamTwoWinsRoundOnBet(mockGame);
		assertEquals(0, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(0, mockGame.getTeamOneScore());
		assertEquals(21, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_TWO_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoExceedsTwentyOneOnBetAndWins() throws SetbackException {
		mockGame = new MockGammaSetbackController(0, 19);
		RoundResult roundResult = teamTwoWinsRoundOnBet(mockGame);
		assertEquals(0, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(0, mockGame.getTeamOneScore());
		assertEquals(22, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_TWO_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoHitsTwentyOneOnBetButWithinTwo() throws SetbackException {
		mockGame = new MockGammaSetbackController(20, 18);
		RoundResult roundResult = teamTwoWinsRoundOnBet(mockGame);
		assertEquals(0, roundResult.getTeamOneRoundScore());
		assertEquals(3, roundResult.getTeamTwoRoundScore());
		assertEquals(20, mockGame.getTeamOneScore());
		assertEquals(21, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.OK, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoFallsToNegativeEleven() throws SetbackException {
		mockGame = new MockGammaSetbackController(0, -9);
		RoundResult roundResult = teamOneWinsRoundOffBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(-2, roundResult.getTeamTwoRoundScore());
		assertEquals(4, mockGame.getTeamOneScore());
		assertEquals(-11, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_ONE_WINS, roundResult.getStatus());
	}
	
	@Test
	public void teamTwoFallsBelowNegativeElven() throws SetbackException {
		mockGame = new MockGammaSetbackController(0, -10);
		RoundResult roundResult = teamOneWinsRoundOffBet(mockGame);
		assertEquals(4, roundResult.getTeamOneRoundScore());
		assertEquals(-2, roundResult.getTeamTwoRoundScore());
		assertEquals(4, mockGame.getTeamOneScore());
		assertEquals(-12, mockGame.getTeamTwoScore());
		assertEquals(RoundResultStatus.TEAM_ONE_WINS, roundResult.getStatus());
	}
}