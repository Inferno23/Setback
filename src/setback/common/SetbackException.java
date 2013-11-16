/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 * @author Michael Burns
 */
package setback.common;

/**
 * This is the exception that is used for any declared exception in the Setback game.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public class SetbackException extends Exception {

	/**
	 * A serial ID included to get rid of the warning.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with a descriptive message.
	 * @param message The message that describes the exception.
	 */
	public SetbackException(String message) {
		super(message);
	}
	
}
