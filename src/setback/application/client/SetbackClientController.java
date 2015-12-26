/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import setback.common.PlayerNumber;
import setback.common.PlayerTeam;
import setback.game.common.Bet;
import setback.game.common.Card;
import setback.game.common.CardSuit;

import java.util.List;

/**
 * This interface contains the common methods that any SetbackClient
 * would need to implement, regardless of how it does it.
 * @author Mike Burns
 */
public interface SetbackClientController {

  // TODO: Rewrite this
  void setPlayerNumbersFromString(String input);

  // Getters for the players

  /**
   * @return The player's number.
   */
  PlayerNumber getMyNumber();

  /**
   * @return The number of the player to the left.
   */
  PlayerNumber getLeft();

  /**
   * @return The number of the player to the center.
   */
  PlayerNumber getCenter();

  /**
   * @return The number of the player to the right.
   */
  PlayerNumber getRight();

  // Actual methods to send

  /**
   * Busy wait helper method.  Should be deleted.
   * @return A string.
   */
  // TODO: Delete and replace
  String noCommand();

  /**
   * Request a given PlayerNumber.
   * @param playerNumber The PlayerNumber to request.
   * @return String indicating success or failure.
   */
  String requestPlayer(PlayerNumber playerNumber);

  /**
   * Show the player's hand.
   * @return A string containing the hand.
   */
  String showHand();

  /**
   * Get the player who must take the next action.
   * @return The player whose turn it is.
   */
  String getCurrentPlayer();

  /**
   * Place a bid when for the hand.
   * @param bid The bid to place.
   * @return A string indicating success or failure.
   */
  String placeBid(Bet bid);

  /**
   * Get the winning bid from this round of bidding.
   * @return A string indicating the winning bid.
   */
  String getWinningBid();

  /**
   * Select trump for the hand.
   * @param suit The suit to select as trump
   * @return A string indicating success or failure.
   */
  String selectTrump(CardSuit suit);

  /**
   * Get the trump suit for the hand.
   * @return A string indicating the trump suit for the hand.
   */
  String getTrump();

  /**
   * Discard the cards in the given list of cards.
   * @param discardList The list of cards to discard.
   * @return A string indicating success or failure.
   */
  String discardCards(List<Card> discardList);

  /**
   * Play a card in a trick.
   * @param card The card to play.
   * @return A string indicating success or failure.
   */
  String playCard(Card card);

  /**
   * Get the score for a team thus far.
   * @param team The team to get the score for.
   * @return A string indicating the team's score.
   */
  // TODO: Make this return both team's score in a JsonObject.
  String getTeamScore(PlayerTeam team);
}
