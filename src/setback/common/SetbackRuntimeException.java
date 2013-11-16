/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.common;

/**
 * This runtime exception is used when a non-declared exception is thrown.
 * It can be used to help the game die with dignity, rather than crashing.
 * @author Michael Burns
 * @version Oct 16, 2013
 */
public class SetbackRuntimeException extends RuntimeException {

	/**
	 * A serial ID included to get rid of the warning.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create an exception with a descriptive message.
	 * @param message The message that describes the exception.
	 */
	public SetbackRuntimeException(String message) {
		super(message);
	}

}
