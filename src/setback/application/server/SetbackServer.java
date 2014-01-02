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
 * @version Dec 26, 2013
 */
public class SetbackServer {
	
	private static int DEFAULT_PORT = 2323;
	private static int MAX_CONNECTIONS = 4;
	
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
	 * @throws IOException If the port is occupied.
	 */
	public static void main(String[] args) throws IOException {

		int portNumber;
		int currentConnections = 0;
		
		final SetbackGameFactory factory = SetbackGameFactory.getInstance();
		final SetbackGameController game = factory.makeDeltaSetbackGame(0);
		
		if (args.length == 1) {
			portNumber = Integer.parseInt(args[0]);
		}
		else {
			portNumber = DEFAULT_PORT;
		}

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
			while (currentConnections < MAX_CONNECTIONS) {
				new SetbackServerThread(serverSocket.accept(), game).start();
				currentConnections++;
				System.out.println("currentConnections = " + currentConnections);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
}