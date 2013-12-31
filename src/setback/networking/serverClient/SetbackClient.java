/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking.serverClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class functions as the client that a player will run
 * when they want to play a game of Setback.
 * @author Michael
 * @version Dec 26, 2013
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

		// Make the connection
		try (
				Socket socket = new Socket(hostname, portNumber);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("EXIT"))
					break;

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
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
