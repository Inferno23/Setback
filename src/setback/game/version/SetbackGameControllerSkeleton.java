/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version;

import java.util.List;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.BetController;
import setback.game.SetbackGameController;
import setback.game.common.Bet;
import setback.game.common.BetResult;
import setback.game.common.Card;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.Hand;
import setback.game.common.Round;
import setback.game.common.RoundResult;
import setback.game.common.RoundResultStatus;
import setback.game.common.Trick;
import setback.game.common.TrickResult;

/**
 * This is the abstract class that implements the common features
 * of the different versions of the SetbackGameController
 * @author Michael Burns
 * @version Oct 21, 2013
 */
public abstract class SetbackGameControllerSkeleton implements SetbackGameController {

	protected boolean gameStarted;
	protected boolean gameOver;
	protected boolean roundStarted;
	protected boolean bettingResolved;
	protected boolean trumpSelected;
	protected boolean trickStarted;

	protected PlayerNumber dealer;
	protected PlayerNumber currentPlayer;
	protected CardSuit trump;
	protected CardSuit leadSuit;

	protected Hand playerOneHand;
	protected Hand playerTwoHand;
	protected Hand playerThreeHand;
	protected Hand playerFourHand;

	protected BetController betController;
	protected BetResult winningBet;
	
	protected int teamOneScore;
	protected int teamTwoScore;

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#startGame()
	 */
	@Override
	public void startGame() throws SetbackException {
		if (gameStarted) {
			throw new SetbackException("The game has already been started!");
		}
		initializeGame();
	}

	/**
	 * This function sets up the variables that are stored in a game.
	 * e.g. gameStarted, dealer, the hands, and the scores.
	 */
	protected void initializeGame() {
		gameStarted = true;
		gameOver = false;
		dealer = PlayerNumber.PLAYER_ONE;
		currentPlayer = PlayerNumber.PLAYER_ONE;
		playerOneHand = new Hand(PlayerNumber.PLAYER_ONE);
		playerTwoHand = new Hand(PlayerNumber.PLAYER_TWO);
		playerThreeHand = new Hand(PlayerNumber.PLAYER_THREE);
		playerFourHand = new Hand(PlayerNumber.PLAYER_FOUR);
		teamOneScore = 0;
		teamTwoScore = 0;
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#startRound()
	 */
	@Override
	public void startRound() throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (roundStarted) {
			throw new SetbackException("The round has already been started!");
		}
		roundStarted = true;
		dealHands();
		bettingResolved = false;
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#startTrick()
	 */
	@Override
	public void startTrick() throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (!bettingResolved) {
			throw new SetbackException("You must resolve betting!");
		}
		if (!trumpSelected) {
			throw new SetbackException("You must select trump!");
		}
		if (trickStarted) {
			throw new SetbackException("The trick has already been started!");
		}
		trickStarted = true;
		leadSuit = null;
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#playCard(setback.game.common.Card, setback.common.PlayerNumber)
	 */
	@Override
	public CardPlayerDescriptor playCard(Card card, PlayerNumber player)
			throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (!player.equals(currentPlayer)) {
			throw new SetbackException("It is not your turn! It is " +
					currentPlayer.toString() + "'s turn!");
		}

		final Hand currentHand = getCurrentHand();
		validateCard(card, currentHand);
		// Remove the played card from the hand.
		currentHand.getCards().remove(card);
		// Update the currentPlayer for the next card. 
		currentPlayer = updatePlayer(currentPlayer);

		return new CardPlayerDescriptor(card, player);
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#playTrick(setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor)
	 */
	@Override
	public TrickResult playTrick(CardPlayerDescriptor firstCard,
			CardPlayerDescriptor secondCard, CardPlayerDescriptor thirdCard,
			CardPlayerDescriptor fourthCard) throws SetbackException {

		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (!bettingResolved) {
			throw new SetbackException("You must resolve betting!");
		}
		if (!trumpSelected) {
			throw new SetbackException("You must select trump!");
		}
		if (!trickStarted) {
			throw new SetbackException("You must start the trick!");
		}

		final Trick trick = new Trick(firstCard, secondCard, thirdCard, fourthCard);
		final TrickResult result = trick.determineTrickResults(trump);
		currentPlayer = result.getWinner();
		trickStarted = false;
		leadSuit = null;
		return result;
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#playRound(java.util.List)
	 */
	@Override
	public RoundResult playRound(List<TrickResult> tricks)
			throws SetbackException {

		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}

		final Round round = new Round(tricks);
		RoundResult result = round.determineRoundResults(winningBet);  
		roundStarted = false;
		bettingResolved = false;
		trumpSelected = false;
		dealer = updatePlayer(dealer);
		teamOneScore += result.getTeamOneRoundScore();
		teamTwoScore += result.getTeamTwoRoundScore();
		
		if (winningBet.getBettor().equals(PlayerNumber.PLAYER_ONE)
				|| winningBet.getBettor().equals(PlayerNumber.PLAYER_THREE)) {
			// Team One won the bet, check if they won the game.
			if (teamOneScore >= 21) {
				if (teamOneScore < (teamTwoScore + 2)) {
					// Differential is not two or more, keep playing.
				}
				else {
					result = new RoundResult(result.getTeamOneRoundScore(), 
							result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_ONE_WINS);
				}
			}
		}
		else {
			// Team Two won the bet, check if they won the game.
		}
		
		if (teamOneScore <= -11) {
			// Always check if the teams hit -11
			result = new RoundResult(result.getTeamOneRoundScore(), 
					result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_TWO_WINS);
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#placeBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void placeBet(PlayerNumber bettor, Bet bet)
			throws SetbackException {

		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (bettingResolved) {
			throw new SetbackException("Betting has already been resolved!");
		}
		// Delegate the actual duty to the BetController
		betController.placeBet(bettor, bet);
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#resolveBets()
	 */
	@Override
	public void resolveBets() throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (bettingResolved) {
			throw new SetbackException("Betting has already been resolved!");
		}
		
		bettingResolved = true;
		winningBet = betController.determineWinner();
		currentPlayer = winningBet.getBettor();
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#selectTrump(setback.common.PlayerNumber, setback.game.common.CardSuit)
	 */
	@Override
	public void selectTrump(PlayerNumber leader, CardSuit trump) throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		if (!roundStarted) {
			throw new SetbackException("You must start the round!");
		}
		if (!bettingResolved) {
			throw new SetbackException("You must resolve betting!");
		}
		if (trumpSelected) {
			throw new SetbackException("Trump has already been selected!");
		}
		if (leader != currentPlayer) {
			throw new SetbackException("You did not win the bet, so you do not select trump!");
		}
		trumpSelected = true;
		this.trump = trump;
	}

	/**
	 * This function deals cards to each of the four players.
	 * The cards are placed in the variables:
	 * playerOneHand, playerTwoHand, playerThreeHand, playerFourHand.
	 */
	protected abstract void dealHands();

	/**
	 * Updates the currentPlayer variable to point to the next person
	 * that should be playing a card.
	 * @param playerToUpdate is the PlayerNumber variable that should be updated.
	 * @return The updated player.
	 */
	private PlayerNumber updatePlayer(PlayerNumber playerToUpdate) {
		switch(playerToUpdate) {
		case PLAYER_ONE:
			playerToUpdate = PlayerNumber.PLAYER_TWO;
			break;
		case PLAYER_TWO:
			playerToUpdate = PlayerNumber.PLAYER_THREE;
			break;
		case PLAYER_THREE:
			playerToUpdate = PlayerNumber.PLAYER_FOUR;
			break;
		default:
			playerToUpdate = PlayerNumber.PLAYER_ONE;
			break;
		}
		return playerToUpdate;
	}

	/**
	 * This function gets the hand of the currentPlayer.
	 * It is just to make the code in playCard cleaner.
	 * @return The current hand.
	 */
	private Hand getCurrentHand() {
		Hand resultHand;
		switch(currentPlayer) {
		case PLAYER_ONE:
			resultHand = playerOneHand;
			break;
		case PLAYER_TWO:
			resultHand = playerTwoHand;
			break;
		case PLAYER_THREE:
			resultHand = playerThreeHand;
			break;
		default:
			resultHand = playerFourHand;
			break;
		}
		return resultHand;
	}

	/**
	 * This function checks that a card can be legally played.
	 * @param card The card to play.
	 * @param hand The hand from which the card comes.
	 * @throws SetbackException If there is something wrong with the card.
	 */
	private void validateCard(Card card, Hand hand) throws SetbackException {
		// Check that the card is in the hand.
		if (!hand.getCards().contains(card)) {
			throw new SetbackException("You don't have that card!");
		}

		// Check that the card is a legal choice.
		if (leadSuit == null) {
			leadSuit = card.getSuit();
		}
		else if (!card.getSuit().equals(leadSuit)) {
			if (!card.getSuit().equals(trump)) {
				if (hand.getNumberOfSuit(leadSuit) != 0) {
					throw new SetbackException("You have to follow suit or play trump!");
				}
				// If the hand cannot follow the suit, then there is no problem.
			}
			// If the card is trump, then there is no problem
		}
		// If the suit matches, then there is no problem.
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#getTeamOneScore()
	 */
	@Override
	public int getTeamOneScore() throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		return teamOneScore;
	}

	/* (non-Javadoc)
	 * @see setback.game.SetbackGameController#getTeamTwoScore()
	 */
	@Override
	public int getTeamTwoScore() throws SetbackException {
		if (!gameStarted) {
			throw new SetbackException("You must start the game!");
		}
		return teamTwoScore;
	}
}
