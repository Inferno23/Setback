/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import setback.application.client.views.PlayerSelectView;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * @author Michael
 * @version Jan 1, 2014
 */
public class SetbackClient {
	
	private static int DEFAULT_PORT = 2323;
	private static String DEFAULT_HOSTNAME = "66.189.40.187";

	/**
	 * This is the executable function that connects the
	 * client to the server.  By default, the client tries
	 * to connect to port 2323 on the local computer, but
	 * this can be overwritten on the command line.
	 * @param args Default variable for java.  If a number is
	 * passed in as the first argument, and an IP address is passed
	 * in as the second argument, they will be used as the port and
	 * host name for the socket connection.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		int portNumber;
		String hostname;

		// Setting up connection variables
		if (args.length == 2) {
			portNumber = Integer.parseInt(args[0]);
			hostname = args[1];
		}
		else {
			portNumber = DEFAULT_PORT;
			hostname = DEFAULT_HOSTNAME;
		}

		try {
			// Make the connection
			Socket socket = new Socket(hostname, portNumber);
			// Initialize the controller and the view
			SetbackClientController controller = new SetbackClientController(socket);
			new PlayerSelectView(controller, new JFrame());
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostname);
			System.exit(1);
		}
	}
}
