/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import setback.common.SetbackException;

/**
 * This is the thread that the server spawns.  It is associated
 * with a connection to an individual client.
 * @author Michael
 * @version Dec 26, 2013
 */
public class SetbackServerThread extends Thread {

	private final Socket socket;
	private final PlayerController controller;
	private final CommandParser parser;

	/**
	 * Constructor that is called by the server.  It provides
	 * the socket that connects to the client, and the shared
	 * PlayerController which interacts with the SetbackGameController.
	 * @param socket The connection to the client.
	 * @param controller The GameController that connects to
	 * the actual game being shared by the four players.
	 */
	public SetbackServerThread(Socket socket, PlayerController controller) {
		super("SetbackServerThread");
		this.socket = socket;
		this.controller = controller;
		parser = new CommandParser();
	}

	/**
	 * This function is called when the thread is created.
	 * It initializes the read/write buffers with the server,
	 * and facilitates communication for the duration of the game.
	 */
	public void run() {
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				) {
			String inputLine, outputLine;
			CommandMessage inputCommand;

			outputLine = null;
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				try {
					inputCommand = parser.parseString(inputLine);
					outputLine = controller.processInput(inputCommand);
				} catch (SetbackException se) {
					outputLine = se.getMessage();
				}
				if (outputLine != null) {
					out.println(outputLine);
					if (outputLine.equals("EXIT"))
						break;
				}
			}
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
