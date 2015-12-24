/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFrame;

import setback.application.socket.IOPair;
import setback.application.views.PlayerSelectView;
import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.common.SetbackRuntimeException;

/**
 * This is the brain of the SetbackClient.
 * When a button is clicked in the GUI, this
 * class handles the response.
 * @author Michael Burns
 * @version Jan 2, 2014
 */
public class SetbackClientControllerImpl extends SetbackClientControllerSkeleton
		implements SetbackClientController {

	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Constructor which takes in no arguments.
	 * This is useful for the Vertx client, which will
	 * replace this.
	 */
	SetbackClientControllerImpl() {
		// Create the GUI
		new PlayerSelectView(this, new JFrame());
	}

	/**
	 * Constructor for a SetbackClientControllerImpl that takes
	 * in the socket connection from the SetbackClient, and
	 * uses it to establish read/write connections with the
	 * SetbackServerThread.
	 * @param pair The IOPair that has the input and output streams.
	 */
	SetbackClientControllerImpl(IOPair pair) {
		// Make the connections for reading and writing
		try {
			out = new PrintWriter(pair.out(), true);
			in = new BufferedReader(
					new InputStreamReader(pair.in()));
			// Read and ignore the initial "null" string.
			in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		new SetbackClientControllerImpl();
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
						returnString += "\t" + fromServer;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnString;
	}

  @Override
  public String requestPlayer(PlayerNumber playerNumber) {
    switch (playerNumber) {
      case PLAYER_ONE:
        return userInput("REQUEST_PLAYER_ONE");
      case PLAYER_TWO:
        return userInput("REQUEST_PLAYER_TWO");
      case PLAYER_THREE:
        return userInput("REQUEST_PLAYER_THREE");
      case PLAYER_FOUR:
        return userInput("REQUEST_PLAYER_FOUR");
      default:
        return "rejected";
    }
  }

  /**
	 * This function sets the appropriate
	 * PlayerNumbers for the controller, and
	 * the left, center, and right.
	 * @param input The string containing this client's
	 * player number.
	 */
	public void setPlayerNumbersFromString(String input) {
		final String[] array = input.split(" ");
		final String playerString = array[0] + "_" + array[1];
		myNumber = PlayerNumber.valueOf(playerString.toUpperCase());
		setPlayerNumbers(myNumber);
	}

	/**
	 * This function takes in a PlayerNumber and sets
	 * the left, center, and right numbers.
	 * @param number The PlayerNumber of the controller.
	 */
	public void setPlayerNumbers(PlayerNumber number) {
		switch (number) {
			case PLAYER_ONE:
				left = PlayerNumber.PLAYER_TWO;
				center = PlayerNumber.PLAYER_THREE;
				right = PlayerNumber.PLAYER_FOUR;
				break;
			case PLAYER_TWO:
				left = PlayerNumber.PLAYER_THREE;
				center = PlayerNumber.PLAYER_FOUR;
				right = PlayerNumber.PLAYER_ONE;
				break;
			case PLAYER_THREE:
				left = PlayerNumber.PLAYER_FOUR;
				center = PlayerNumber.PLAYER_ONE;
				right = PlayerNumber.PLAYER_TWO;
				break;
			case PLAYER_FOUR:
			default:
				left = PlayerNumber.PLAYER_ONE;
				center = PlayerNumber.PLAYER_TWO;
				right = PlayerNumber.PLAYER_THREE;
				break;
		}
	}
}
