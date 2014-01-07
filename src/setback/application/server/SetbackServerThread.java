/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import setback.application.PlayerController;
import setback.application.SetbackObserver;
import setback.application.command.CommandMessage;
import setback.application.command.CommandParser;
import setback.common.SetbackException;
import setback.game.SetbackGameController;

/**
 * This is the thread that the server spawns.  It is associated
 * with a connection to an individual client.
 * @author Michael
 * @version Jan 2, 2014
 */
public class SetbackServerThread extends Thread implements SetbackObserver {

	private final Socket socket;
	private final PlayerController controller;
	private final CommandParser parser;

	private PrintWriter out;

	/**
	 * Constructor that is called by the server.  It provides
	 * the socket that connects to the client, and the shared
	 * PlayerController which interacts with the SetbackGameController.
	 * @param socket The connection to the client.
	 * @param game The SetbackGameController that is
	 * being shared by the four players.
	 */
	public SetbackServerThread(Socket socket, SetbackGameController game) {
		super("SetbackServerThread");
		this.socket = socket;
		game.addObserver(this);
		this.controller = new PlayerController(game);
		parser = new CommandParser();
	}

	/**
	 * This function is called when the thread is created.
	 * It initializes the read/write buffers with the server,
	 * and facilitates communication for the duration of the game.
	 */
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

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
					if (outputLine.equals("EXIT")) {
						break;
					}
				}
			}
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see setback.networking.SetbackObserver#update(java.lang.String)
	 */
	public void update(String message) {
		if (message != null) {
			if (message.equals("ROUND BEGIN")) {
				controller.handleUpdate("ROUND BEGIN");
			}
			else if (message.startsWith("PLAYER_") && message.contains(" BET ")) {
				// The PlayerController does not care, but we need to tell the client
				out.print(message + " ");
			}
			else if (message.equals("BETTING RESOLVED")) {
				controller.handleUpdate("BETTING_RESOLVED");
			}
			else if (message.equals("ROUND ENDED")) {
				
			}
			
		}
	}
}
