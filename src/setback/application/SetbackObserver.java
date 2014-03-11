/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application;


/**
 * The bare bones Observer interface for Setback.
 * The only required method is update, which is
 * called any time the Observable notifies its
 * observers.
 * @author Michael
 * @version Dec 29, 2013
 */
public interface SetbackObserver {
	
	/**
	 * This method is called whenever the observed object
	 * is changed.
	 * @param message The message from the Observable.
	 */
	void update(String message);
}
