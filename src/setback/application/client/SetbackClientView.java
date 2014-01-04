package setback.application.client;

import java.awt.Color;

import javax.swing.JFrame;

import setback.application.client.views.PlaceBetsView;

/**
 * This abstract class handles the basic GUI of Setback.
 * All specific screens are handled in subclasses of this.
 * @author Michael
 * @version Jan 2, 2014
 */
public abstract class SetbackClientView {
	
	protected SetbackClientController controller;
	protected JFrame frame;

	/**
	 * Create the GUI that the user will interact with.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public SetbackClientView(SetbackClientController controller, JFrame frame) {
		this.controller = controller;
		this.frame = frame;
		initialize();
	}

	/**
	 * This function initializes the GUI's background and visibility.
	 * It is called by all subclasses when they run their version
	 * of initialize.
	 */
	protected void initialize() {
		// Wipe anything from before
		frame.getContentPane().removeAll();
		// Initialize the application frame
		frame.getContentPane().setBackground(new Color(60, 179, 113));
		frame.setResizable(false);
		frame.setTitle("Setback");
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// Make the screen visible
		frame.setVisible(true);
	}

	/**
	 * This is the function that is called when there
	 * has been input from the server.
	 * The GUI will be updated accordingly.
	 * @param input The String sent from the Server.
	 */
	protected void update(String input) {
		if (input.endsWith(" selected")) {
			new PlaceBetsView(controller, frame);
		}
		else if (input.equals("BEGIN GAME")) {
			System.out.println("We made it!");
			//new PlaceBetsView(controller, frame);
		}
		else if (input.equals("EXIT")) {
			System.exit(0);
		}
	}

	/**
	 * By default this method does nothing, but many
	 * subclasses will sit here and poll the server
	 * for changes.
	 */
	protected void waitForUpdate() {	}
}
