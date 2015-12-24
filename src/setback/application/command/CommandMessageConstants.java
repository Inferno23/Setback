/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.command;

/**
 * This contains all of the messages that can be sent.
 * @author Mike Burns
 */
public abstract class CommandMessageConstants {
  public static final String ROUND_BEGIN = "ROUND BEGIN";
  public static final String BETTING_RESOLVED = "BETTING RESOLVED";
  public static final String TRICK_STARTED = "TRICK STARTED";
  public static final String ROUND_ENDED = "ROUND ENDED";
}
