/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface is an abstraction of a socket that will
 * be used to hide the use of the Socket class from the rest
 * of Setback.  It will also enable the use of mock sockets
 * for testing purposes.
 * @author Michael Burns
 * @version May 25, 2014
 */
public interface IOPair {

	/**
	 * This returns the OutputStream used as output from the socket.
	 * @return The OutputStream used as output from the socket.
	 * @throws IOException If the OutputStream cannot be created.
	 */
	public OutputStream out() throws IOException;
	
	/**
	 * This returns the InputStream used as input from the socket.
	 * @return The InputStream used as input from the socket.
	 * @throws IOException If the InputStream cannot be created.
	 */
	public InputStream in() throws IOException;

	/**
	 * Closes any overhead associated with the input and output streams.
	 * @throws IOException If the streams cannot be closed.
	 */
	public void close() throws IOException;
}
