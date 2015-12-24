/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import setback.common.PlayerNumber;

/**
 * This abstract class handles all of the common code that all
 * SetbackClientControllers will want to have.
 * @author Mike Burns
 */
public abstract class SetbackClientControllerSkeleton implements SetbackClientController {

  // Common variables
  protected PlayerNumber myNumber;
  protected PlayerNumber left;
  protected PlayerNumber center;
  protected PlayerNumber right;

  /**
   * @return the myNumber.
   */
  public PlayerNumber getMyNumber() {
    return myNumber;
  }

  /**
   * @return the left.
   */
  public PlayerNumber getLeft() {
    return left;
  }

  /**
   * @return the center.
   */
  public PlayerNumber getCenter() {
    return center;
  }

  /**
   * @return the right.
   */
  public PlayerNumber getRight() {
    return right;
  }
}
