/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import setback.application.socket.IOPair;
import setback.application.socket.SocketIOPair;
import setback.common.SetbackException;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * @author Michael
 * @version Jan 1, 2014
 */
public class SetbackClient {

	private static final int DEFAULT_PORT = 2323;
	private static final String DEFAULT_HOSTNAME = "216.49.151.138";

	/**
	 * This is the executable function that connects the
	 * client to the server.  By default, the client tries
	 * to connect to port 2323 on the local computer, but
	 * this can be overwritten on the command line.
	 * @param args Default variable for java.  If a number is
	 * passed in as the first argument, and an IP address is passed
	 * in as the second argument, they will be used as the port and
	 * host name for the socket connection.
	 */
	public static void main(String[] args) {

		final Socket socket;

		try {
			// Set up the socket connection
			if (args.length == 2) {
				socket = makeSocket(Integer.parseInt(args[0]), args[1]);
			}
			else {
				socket = makeSocket(DEFAULT_PORT, DEFAULT_HOSTNAME);
			}
			
			final IOPair pair = new SocketIOPair(socket);
			// Start the controller
			new SetbackClientControllerImpl(pair);

		} catch (SetbackException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * This function handles creating a socket with the given hostname and
	 * port number.  Throws an exception if the socket cannot be created.
	 * @param portNumber The port to connect on.
	 * @param hostname The name of the host to connect to.
	 * @return The Socket that has been established.
	 * @throws SetbackException If the Socket cannot be opened.
	 */
	public static Socket makeSocket(int portNumber, String hostname) throws SetbackException {
		final Socket socket;

		try {
			socket = new Socket(hostname, portNumber);
		} catch (UnknownHostException e) {
			throw new SetbackException("Don't know about host " + hostname);
		} catch (IOException e) {
			throw new SetbackException("Couldn't get I/O for the connection to " + hostname);
		}

		return socket;
	}
}
