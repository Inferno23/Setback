/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game;

import setback.application.SetbackObserver;
import setback.common.PlayerNumber;
import setback.game.common.Hand;

/**
 * This interface handles the methods for handling observers
 * for a SetbackGameController.
 * @author Mike Burns
 */
public interface SetbackGameObservable {

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
}
