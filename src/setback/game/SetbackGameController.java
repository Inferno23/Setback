/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import java.util.List;

import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.common.Bet;
import setback.game.common.Card;
import setback.game.common.CardPlayerDescriptor;
import setback.game.common.CardSuit;
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
}
