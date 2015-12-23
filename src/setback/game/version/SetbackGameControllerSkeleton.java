/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.BetController;
import setback.game.CardDealerController;
import setback.game.SetbackGameController;
import setback.game.common.*;

import java.util.ArrayList;
import java.util.List;

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
	protected boolean discardingResolved;
	protected boolean trickStarted;

	protected boolean playerOneDiscarded;
	protected boolean playerTwoDiscarded;
	protected boolean playerThreeDiscarded;
	protected boolean playerFourDiscarded;
	protected boolean discardingIgnored;

	protected boolean playerOneSelected;
	protected boolean playerTwoSelected;
	protected boolean playerThreeSelected;
	protected boolean playerFourSelected;

	protected boolean allBetsPlaced;
	
	protected boolean firstTrick;

	protected CardDealerController dealerController;

	protected PlayerNumber dealer;
	protected PlayerNumber nextBettor;
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

	protected List<CardPlayerDescriptor> trickCards;
	protected List<TrickResult> trickResults;

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
		nextBettor = PlayerNumber.PLAYER_TWO;
		currentPlayer = PlayerNumber.PLAYER_TWO;
		playerOneHand = new Hand(PlayerNumber.PLAYER_ONE);
		playerTwoHand = new Hand(PlayerNumber.PLAYER_TWO);
		playerThreeHand = new Hand(PlayerNumber.PLAYER_THREE);
		playerFourHand = new Hand(PlayerNumber.PLAYER_FOUR);
		teamOneScore = 0;
		teamTwoScore = 0;
		trickCards = new ArrayList<CardPlayerDescriptor>();
		trickResults = new ArrayList<TrickResult>();
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
		dealerController.dealHands(playerOneHand, playerTwoHand, playerThreeHand, playerFourHand);
		bettingResolved = false;
		allBetsPlaced = false;
		nextBettor = updatePlayer(dealer);
		currentPlayer = nextBettor; // This line is really just here to help the GUI
		winningBet = null;
		firstTrick = true;
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
		if (bettor != nextBettor) {
			throw new SetbackException("It is not your turn to bet!");
		}
		// Delegate the actual duty to the BetController
		betController.placeBet(bettor, bet);
		nextBettor = updatePlayer(nextBettor);
		if (bettor == dealer) {
			allBetsPlaced = true;
		}
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

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#discardCards(setback.common.PlayerNumber, setback.game.common.Card, setback.game.common.Card, setback.game.common.Card)
	 */
	public void discardCards(PlayerNumber player, Card cardOne, Card cardTwo, Card cardThree) throws SetbackException {
		// Check the state variables
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
		if (discardingResolved) {
			throw new SetbackException("Discarding has already been resolved!");
		}
		// Check if this player has already discarded
		boolean alreadyDiscarded = true;
		switch (player) {
		case PLAYER_ONE:
			alreadyDiscarded = playerOneDiscarded;
			break;
		case PLAYER_TWO:
			alreadyDiscarded = playerTwoDiscarded;
			break;
		case PLAYER_THREE:
			alreadyDiscarded = playerThreeDiscarded;
			break;
		case PLAYER_FOUR:
		default:
			alreadyDiscarded = playerFourDiscarded;
			break;
		}
		if (alreadyDiscarded) {
			throw new SetbackException("You have already discarded!");
		}
		// Check the cards for null values
		if (cardOne == null || cardTwo == null || cardThree == null) {
			throw new SetbackException("You must discard three valid cards!");
		}
		// Check that the cards are in the player's hand
		final Hand hand = getPlayerHand(player);
		if (!hand.getCards().contains(cardOne)) {
			throw new SetbackException("You do not have the " + cardOne.toString() + " so you cannot discard it!");
		}
		if (!hand.getCards().contains(cardTwo)) {
			throw new SetbackException("You do not have the " + cardTwo.toString() + " so you cannot discard it!");
		}
		if (!hand.getCards().contains(cardThree)) {
			throw new SetbackException("You do not have the " + cardThree.toString() + " so you cannot discard it!");
		}
		// Actually discard the cards
		final List<Card> cardList = hand.getCards();
		cardList.remove(cardOne);
		cardList.remove(cardTwo);
		cardList.remove(cardThree);
		hand.setCards(cardList);
		// Update the individual flags
		switch (player) {
		case PLAYER_ONE:
			playerOneDiscarded = true;
			break;
		case PLAYER_TWO:
			playerTwoDiscarded = true;
			break;
		case PLAYER_THREE:
			playerThreeDiscarded = true;
			break;
		case PLAYER_FOUR:
		default:
			playerFourDiscarded = true;
			break;
		}
		// Update the master flag
		discardingResolved = playerOneDiscarded && playerTwoDiscarded &&
				playerThreeDiscarded && playerFourDiscarded;
	}

	/* 
	 * (non-Javadoc)
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
		if (!discardingIgnored && !discardingResolved) {
			throw new SetbackException("You must resolve discarding!");
		}
		if (trickStarted) {
			throw new SetbackException("The trick has already been started!");
		}
		trickStarted = true;
		leadSuit = null;
	}

	/* 
	 * (non-Javadoc)
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
		if (!bettingResolved) {
			throw new SetbackException("You must resolve betting!");
		}
		if (!trumpSelected) {
			throw new SetbackException("You must select trump!");
		}
		if (!trickStarted) {
			throw new SetbackException("You must start the trick!");
		}
		if (!player.equals(currentPlayer)) {
			throw new SetbackException("It is not your turn! It is " +
					currentPlayer.toString() + "'s turn!");
		}

		final Hand currentHand = getPlayerHand(currentPlayer);
		validateCard(card, currentHand);
		// Remove the played card from the hand.
		currentHand.getCards().remove(card);
		// Update the currentPlayer for the next card. 
		currentPlayer = updatePlayer(currentPlayer);

		final CardPlayerDescriptor result = new CardPlayerDescriptor(card, player); 
		trickCards.add(result);
		return result;
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

		// The first trick must begin with trump
		if (firstTrick) {
			leadSuit = trump;
			/* This leaves the edge case of a player selecting a trump that
			they have no cards in.  This would result in trump being the
			lead suit, even though the first card is not trump */
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

	/* 
	 * (non-Javadoc)
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
		firstTrick = false;

		trickCards = new ArrayList<CardPlayerDescriptor>();
		trickResults.add(result);

		return result;
	}

	/* 
	 * (non-Javadoc)
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
		discardingResolved = false;
		playerOneDiscarded = false;
		playerTwoDiscarded = false;
		playerThreeDiscarded = false;
		playerFourDiscarded = false;
		dealer = updatePlayer(dealer);
		teamOneScore += result.getTeamOneRoundScore();
		teamTwoScore += result.getTeamTwoRoundScore();

		if (winningBet.getBettor().equals(PlayerNumber.PLAYER_ONE)
				|| winningBet.getBettor().equals(PlayerNumber.PLAYER_THREE)) {
			// Team One won the bet, check if they won the game.
			if (teamOneScore >= 21) {
				if (teamOneScore >= (teamTwoScore + 2)) {
					// Differential is large enough, the game is over.
					result = new RoundResult(result.getTeamOneRoundScore(), 
							result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_ONE_WINS);
				}
			}
		}
		else {
			// Team Two won the bet, check if they won the game.
			if (teamTwoScore >= 21) {
				if (teamTwoScore >= (teamOneScore + 2)) {
					// Differential is large enough, the game is over.
					result = new RoundResult(result.getTeamOneRoundScore(), 
							result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_TWO_WINS);
				}
			}
		}

		if (teamOneScore <= -11) {
			// Always check if the teams hit -11
			result = new RoundResult(result.getTeamOneRoundScore(), 
					result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_TWO_WINS);
		}
		else if (teamTwoScore <= -11) {
			// Always check if the teams hit -11
			result = new RoundResult(result.getTeamOneRoundScore(), 
					result.getTeamTwoRoundScore(), RoundResultStatus.TEAM_ONE_WINS);
		}

		trickResults = new ArrayList<TrickResult>();

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#selectPlayerNumber(setback.common.PlayerNumber)
	 */
	public boolean requestPlayerNumber(PlayerNumber requestedNumber) {
		boolean granted;

		switch(requestedNumber) {
		case PLAYER_ONE:
			granted = !playerOneSelected;
			playerOneSelected = true;
			break;
		case PLAYER_TWO:
			granted = !playerTwoSelected;
			playerTwoSelected = true;
			break;
		case PLAYER_THREE:
			granted = !playerThreeSelected;
			playerThreeSelected = true;
			break;
		case PLAYER_FOUR:
		default:
			granted = !playerFourSelected;
			playerFourSelected = true;
			break;
		}

		return granted;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#checkPlayersReady()
	 */
	public boolean checkPlayersReady() {
		return playerOneSelected && playerTwoSelected
				&& playerThreeSelected && playerFourSelected;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#checkAllBetsPlaced()
	 */
	public boolean checkAllBetsPlaced() {
		return allBetsPlaced;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#checkAllDiscarded()
	 */
	public boolean checkAllDiscarded() {
		return discardingResolved;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#checkFourCardsPlayed()
	 */
	public boolean checkFourCardsPlayed() {
		return (trickCards.size() == 4); 
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getTrickCards()
	 */
	public List<CardPlayerDescriptor> getTrickCards() {
		return trickCards;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getTrickResults()
	 */
	public List<TrickResult> getTrickResults() {
		return trickResults;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getDealer()
	 */
	public PlayerNumber getDealer() {
		return dealer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getCurrentPlayer()
	 */
	public PlayerNumber getCurrentPlayer() {
		return currentPlayer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getWinningBet()
	 */
	public BetResult getWinningBet() {
		return winningBet;
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getTrump()
	 */
	public CardSuit getTrump() {
		return trump;
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#getPlayerHand(setback.common.PlayerNumber)
	 */
	public Hand getPlayerHand(PlayerNumber player) {
		Hand resultHand;
		switch(player) {
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
}
