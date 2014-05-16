/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.server;

import java.io.IOException;
import java.net.ServerSocket;

import setback.game.SetbackGameController;
import setback.game.SetbackGameFactory;

/**
 * This class functions as the server that the players' clients
 * will connect to when they want to play a game of Setback.
 * When a client connects to this server, a new thread is created.
 * @author Michael
 * @version Jan 5, 2014
 */
public class SetbackServer {
	
	private static final int DEFAULT_PORT = 2323;
	private static final int MAX_CONNECTIONS = 4;
	
	/**
	 * This is the executable function that creates the server.
	 * By default, the server runs on port 2323, but this can
	 * be changed if a different port is passed in as the only
	 * argument.
	 * This function also instantiates the SetbackGameController
	 * which is passed to each new thread.
	 * @param args Default variable for java.  If a number is
	 * passed in as the only argument, it will be used as the
	 * port number for the socket connection.
	 * @throws IOException If the server cannot accept connections.
	 */
	public static void main(String[] args) throws IOException {
		final SetbackGameController game;
		final int portNumber;
		final ServerSocket serverSocket;
		int currentConnections;
		
		final String debug = System.getenv("DEBUG");
		game = makeGame(debug);
		portNumber = getPortNumber(args);
		serverSocket = getServerSocket(portNumber);
		
		if (serverSocket == null) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
		
		for (currentConnections = 0; currentConnections < MAX_CONNECTIONS; currentConnections++) {
			new SetbackServerThread(serverSocket.accept(), game).start();
			currentConnections++;
			System.out.println("currentConnections = " + currentConnections);
		}
	}
	
	/**
	 * This function handles creating the game to be played.
	 * If the DEBUG system variable is set to be true, it will
	 * provide the SetbackGameFactory with a non-random seed
	 * for the game.  Otherwise, it will use a random seed for the game.
	 * @param debug The debug string.  True if in debug, null or false if retail.
	 * @return The SetbackGameController to be played.
	 */
	public static SetbackGameController makeGame(String debug) {
		final SetbackGameController game;
		final SetbackGameFactory factory = SetbackGameFactory.getInstance();
		
		if (debug != null && debug.equals("true")) {
			System.out.println("DEBUG");
			game = factory.makeDeltaSetbackGame(0);
		}
		else {
			System.out.println("RETAIL");
			game = factory.makeDeltaSetbackGame();
		}
	
		return game;
	}
	
	/**
	 * This function handles parsing the command line arguments
	 * for a specified port to play on.
	 * @param args The arguments given to the SetbackServer that
	 * should be null or contain the port number.
	 * @return The port number to be used.
	 */
	public static int getPortNumber(String[] args) {
		final int portNumber;
		if (args != null && args.length == 1) {
			portNumber = Integer.parseInt(args[0]);
		}
		else {
			portNumber = DEFAULT_PORT;
		}
		return portNumber;
	}

	/**
	 * This function handles creating the ServerSocket for clients
	 * to connect to.
	 * @param portNumber The port to open the socket on.
	 */
	public static ServerSocket getServerSocket(final int portNumber) {
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			serverSocket = null;
		}
		
		return serverSocket;
	}
}