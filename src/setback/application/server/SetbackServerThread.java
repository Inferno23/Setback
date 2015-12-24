/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import setback.application.SetbackObserver;
import setback.application.command.CommandMessageJson;
import setback.application.command.CommandParser;
import setback.application.socket.IOPair;
import setback.common.SetbackException;
import setback.game.SetbackGameController;
import setback.game.version.SetbackMultiplayerGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * This is the thread that the server spawns.  It is associated
 * with a connection to an individual client.
 * @author Michael
 * @version Jan 2, 2014
 */
public class SetbackServerThread extends Thread implements SetbackObserver {

	private final IOPair pair;
	protected PlayerController controller;
	private final CommandParser parser;

	protected PrintWriter out;
	protected BufferedReader in;

	/**
	 * Constructor that is called by the server.  It provides
	 * the socket that connects to the client, and the shared
	 * SetbackServerController which interacts with the SetbackGameController.
	 * @param pair The input/output pair connected to the client.
	 * @param game The SetbackGameController that is
	 * being shared by the four players.
	 */
	public SetbackServerThread(IOPair pair, SetbackMultiplayerGame game) {
		super("SetbackServerThread");
		this.pair = pair;
		game.addObserver(this);
		controller = new PlayerController(game);
		parser = new CommandParser();
	}

	/**
	 * This function is called when the thread is created.
	 * It initializes the read/write buffers with the server,
	 * and facilitates communication for the duration of the game.
	 */
	public void run() {
		try {
			out = new PrintWriter(pair.out(), true);
			in = new BufferedReader(
					new InputStreamReader(pair.in()));

			String inputLine, outputLine;

			outputLine = null;
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				try {
          // TODO: Shouldn't need to use the Parser to make a CommandMessageJson
					CommandMessageJson commandMessageJson =
              parser.parseString(inputLine);
					outputLine = controller.processInput(commandMessageJson);
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

			pair.close();
			
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
			// Game inputs
			if (message.equals("ROUND BEGIN")) {
				controller.startRound();
			}
			else if (message.equals("BETTING RESOLVED")) {
				// The PlayerController does not care, but we need to tell the client
				out.print(message + " ");
			}
			else if (message.contains("TRICK STARTED")) {
				// The PlayerController does not care, but we need to tell the client
				out.print(message + " ");
			}
			else if (message.contains("ROUND ENDED")) {
				// The PlayerController does not care, but we need to tell the client
				out.print(message + " ");
			}
			// Player inputs
			else if (message.startsWith("PLAYER_")) {
				// Placing a bet
				if (message.contains(" BET ")) {
					// The PlayerController does not care, but we need to tell the client
					out.print(message + " ");
				}
				// Selecting trump
				else if (message.contains(" SELECTED ")) {
					// The PlayerController does not care, but we need to tell the client
					out.print(message + " ");
				}
				// Discarding cards
				else if (message.contains(" DISCARDED")) {
					// The PlayerController does not care, but we need to tell the client
					out.print(message + " ");
				}
				// Playing cards
				else if (message.contains(" PLAYED ")) {
					// The PlayerController does not care, but we need to tell the client
					out.print(message + " ");
				}
				else if (message.contains(" WON TRICK")) {
					out.print(message + " ");
				}
			}
		}
	}
}
