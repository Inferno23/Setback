/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import java.util.List;

import setback.application.SetbackObserver;
import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.common.Bet;
import setback.game.common.BetResult;
import setback.game.common.Card;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
import setback.game.common.Hand;
import setback.game.common.RoundResult;
import setback.game.common.TrickResult;

/**
 * The SetbackGameController is the interface for any version of
 * a Setback game controller.  The purpose of this interface is
 * to control the flow of the overall game, and to ensure that
 * game rules are followed.
 * 
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public interface SetbackGameController {

	/**
	 * Before a game can be played, this initialization method
	 * must be invoked.
	 * @throws SetbackException if the game is already running.
	 */
	void startGame() throws SetbackException;
	
	/**
	 * Before a round can be played, this method must be called.
	 * This will deal out hands to each player.
	 * @throws SetbackException if there is a round currently running.
	 */
	void startRound() throws SetbackException;
	
	/**
	 * This function places a bet for a player if the bet
	 * is validated as legal.
	 * @param bettor The player who is betting.
	 * @param bet The bet that they are proposing.
	 * @throws SetbackException If the bet is illegal.
	 */
	void placeBet(PlayerNumber bettor, Bet bet) throws SetbackException;
	
	/**
	 * This method is called after the bets are placed.  It calls
	 * determineWinner on the BetController, and determines who
	 * won the bet.
	 * @throws SetbackException if there are any problems.
	 */
	void resolveBets() throws SetbackException;
	
	/**
	 * This method is called after the betting winner has been determined.
	 * It allows the winner of the betting to determine trump.  If any
	 * other player attempts to pick trump, an exception is thrown.
	 * @param leader The player picking trump.
	 * @param trump The suit that is trump.
	 * @throws SetbackException if the wrong player picks a suit.
	 */
	void selectTrump(PlayerNumber leader, CardSuit trump) throws SetbackException;
	
	/**
	 * This method is called after trump has been selected for the round.
	 * It allows a player to discard three cards from their current hand.
	 * Each player must discard at the beginning of each round in all
	 * versions of the game beginning with Delta.
	 * @param player The player who is discarding cards.
	 * @param cardOne The first card to discard.
	 * @param cardTwo The second card to discard.
	 * @param cardThree The third card to discard.
	 * @throws SetbackException If a player attempts to discard a card
	 * that is not currently in their hand.
	 * @since DeltaSetbackGame
	 */
	void discardCards(PlayerNumber player, Card cardOne, Card cardTwo, Card cardThree) throws SetbackException;
	
	/**
	 * Before a trick can be played, this method must be called.
	 * This will initialize the variable for the lead suit of a trick.
	 * @throws SetbackException
	 */
	void startTrick() throws SetbackException;
	
	/**
	 * This method executes a single card being played in a trick.
	 * It is called for every card for every trick for every round.
	 * @param card The card to play.
	 * @param player The player who played the card.
	 * @return A CardPlayerDescriptor with the information given.
	 * @throws SetbackException If the card played is illegal for any reason.
	 */
	CardPlayerDescriptor playCard(Card card, PlayerNumber player) throws SetbackException;
	
	/**
	 * This method executes a single trick in a round.  It is called
	 * for every trick of every round of the game.
	 * @param firstCard The first card and who played it.
	 * @param secondCard The second card and who played it,.
	 * @param thirdCard The third card and who played it.
	 * @param fourthCard The fourth card and who played it.
	 * @return The winner of the trick, along with how many game
	 * points were involved, the presence of the jack of trump,
	 * and any high/low candidates.
	 * @throws SetbackException
	 */
	TrickResult playTrick(CardPlayerDescriptor firstCard,
			CardPlayerDescriptor secondCard,
			CardPlayerDescriptor thirdCard,
			CardPlayerDescriptor fourthCard) throws SetbackException;
	
	/**
	 * This method executes a full round in the game.  It is called
	 * for every round of the game.
	 * @param tricks The list of results of the tricks from the round.
	 * @throws SetbackException if there are any problems in the round.
	 * @return The points that each team earned in the round.
	 */
	RoundResult playRound(List<TrickResult> tricks) throws SetbackException;

	//////////////////////////////////////////
	// Multi-player only functions          //
	//////////////////////////////////////////
	/**
	 * Prior to playing a multiplayer game, players must request
	 * a PlayerNumber.
	 * @param requestedNumber The PlayerNumber that is being requested.
	 * @return True if the number is granted, false if it is
	 * already taken.
	 * @throws SetbackException 
	 */
	boolean requestPlayerNumber(PlayerNumber requestedNumber) throws SetbackException;
	
	/**
	 * Prior to starting a multiplayer game, all players must
	 * have selected a PlayerNumber.  This function returns
	 * true if all the players have been selected.
	 * @return True if four players are connected.
	 */
	boolean checkPlayersReady();
	
	/**
	 * This function checks if all four bets have been placed.
	 * If it returns true, the PlayerController will resolve
	 * the bets.
	 * @return True if all four bets have been placed for a round.
	 */
	boolean checkAllBetsPlaced();
	
	/**
	 * Prior to starting a trick in a multiplayer game,
	 * all players must have discarded.  This function
	 * returns true if all players have discarded.
	 * @return True if all players have discarded.
	 */
	boolean checkAllDiscarded();
	
	/**
	 * Checks if all four players have played cards.
	 * If it returns true, playTrick can be called.
	 * @return True if four cards have been played this trick.
	 */
	boolean checkFourCardsPlayed();
	
	/**
	 * Getter for the list of cards played in the trick.
	 * @return The trickCards list.
	 */
	List<CardPlayerDescriptor> getTrickCards();
	
	/**
	 * Getter for the list of TrickResults from the round.
	 * @return The trickResults list.
	 */
	List<TrickResult> getTrickResults();
	
	/**
	 * Getter for the dealer field.
	 * @return The dealer's PlayerNumber.
	 */
	PlayerNumber getDealer();
	
	/**
	 * Getter for the currentPlayer field.
	 * @return The current player's PlayerNumber.
	 */
	PlayerNumber getCurrentPlayer();
	
	/**
	 * Getter for the winningBet field.
	 * @return The winning bettor and bet.
	 */
	BetResult getWinningBet();
	
	/**
	 * Getter for the trump field.
	 * @return The trump suit.
	 */
	CardSuit getTrump();
	
	//////////////////////////////////////////
	// These functions are for observers    //
	//////////////////////////////////////////
	
	/**
	 * Adds an observer to the set of observers for this object.
	 * @param observer The observer to add.
	 */
	void addObserver(SetbackObserver observer);
	
	/**
	 * Notify all of the observers that a change has been made.
	 * @param message The message to tell the Observers.
	 */
	void notifyObservers(String message);
	
	/**
	 * This function gets the hand of the specified player.
	 * @param player The player whose hand should be returned.
	 * @return The specified player's hand.
	 */
	Hand getPlayerHand(PlayerNumber player);
	
	//////////////////////////////////////////
	// These functions can happen any time. //
	//////////////////////////////////////////
	
	/**
	 * This method returns the score for team one.
	 * @throws SetbackException if the game has not been started.
	 * @return The persistent score for team one.
	 */
	int getTeamOneScore() throws SetbackException;
	
	/**
	 * This method returns the score for team two.
	 * @throws SetbackException if the game has not been started.
	 * @return The persistent score for team two.
	 */
	int getTeamTwoScore() throws SetbackException;
}
