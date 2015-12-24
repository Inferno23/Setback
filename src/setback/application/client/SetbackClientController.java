/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import setback.common.PlayerNumber;

/**
 * This interface contains the common methods that any SetbackClient
 * would need to implement, regardless of how it does it.
 * @author Mike Burns
 */
public interface SetbackClientController {

  void setPlayerNumbersFromString(String input);

  String userInput(String input);

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
}
