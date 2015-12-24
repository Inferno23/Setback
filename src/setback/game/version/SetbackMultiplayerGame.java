/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version;

import setback.game.SetbackGameController;
import setback.game.SetbackGameObservable;

/**
 * This interface implements the interfaces that are needed
 * to run a multiplayer SetbackGame.  Any versions that support
 * multiplayer should implement just this interface.
 * @author Mike Burns
 */
public interface SetbackMultiplayerGame
    extends SetbackGameController, SetbackGameObservable {
}
