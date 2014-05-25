/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * An implementation of the IOPair interface that uses sockets
 * @author Michael Burns
 * @version May 25, 2014
 */
public class SocketIOPair implements IOPair {

	private Socket socket;

	/**
	 * Constructor that takes in a Socket to use
	 * when creating the input and output stream.
	 * @param socket The connected Socket.
	 */
	public SocketIOPair(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Returns the OutputStream from the Socket.
	 * @throws IOException 
	 */
	public OutputStream out() throws IOException {
		return socket.getOutputStream();
	}

	/**
	 * Returns the InputStream from the Socket.
	 * @throws IOException 
	 */
	public InputStream in() throws IOException {
		return socket.getInputStream();
	}

	/**
	 * Closes the socket.
	 * @throws IOException 
	 */
	public void close() throws IOException {
		socket.close();
	}

}
