/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is the brain of the SetbackClient.
 * When a button is clicked in the GUI, this
 * class handles the response.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class SetbackClientController {

	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Constructor for a SetbackClientController that takes
	 * in the socket connection from the SetbackClient, and
	 * uses it to establish read/write connections with the
	 * SetbackServerThread.
	 * @param socket The socket connection to the server.
	 */
	SetbackClientController(Socket socket) {
		// Make the connections for reading and writing
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			// Read and ignore the initial "null" string.
			in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is called when a button is pressed
	 * in the GUI.  The button corresponds to a command
	 * from the user.  This command is sent to the server,
	 * and the response is returned to the user.
	 * @param input The string from the button click.
	 * @return The server's response.
	 */
	public String userInput(String input) {
		String fromServer;
		String returnString = null;
		
		out.println(input);
		
		try {
			if ((fromServer = in.readLine()) != null) {
				returnString = fromServer;
				
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("EXIT")) {
					System.exit(0);
				}
				// Handle multiple lines from server
				else if (fromServer.endsWith("HAND:")) {
					while ((fromServer = in.readLine()) != null && fromServer.length() != 0) {
						System.out.println("Server: " + fromServer);
						returnString += fromServer;
					}
				}
			}
			else {
				returnString = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnString;
	}


}
