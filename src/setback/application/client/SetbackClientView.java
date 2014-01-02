package setback.application.client;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * This abstract class handles the basic GUI of Setback.
 * All specific screens are handled in subclasses of this.
 * @author Michael
 * @version Jan 2, 2014
 */
public abstract class SetbackClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected SetbackClientController controller;

	/**
	 * Create the GUI that the user will interact with.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 */
	public SetbackClientView(SetbackClientController controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * This function initializes the GUI's background and visibility.
	 * It is called by all subclasses when they run their version
	 * of initialize.
	 */
	protected void initialize() {
		// Initialize the application frame
		this.getContentPane().setBackground(new Color(60, 179, 113));
		this.setResizable(false);
		this.setTitle("Setback");
		this.setBounds(100, 100, 900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		// Make the screen visible
		this.setVisible(true);
	}

	/**
	 * This is the function that is called when there
	 * has been input from the server.
	 * The GUI will be updated accordingly.
	 * @param input The String sent from the Server.
	 */
	protected void update(String input) {
		if (input.equals("Player one selected")) {
			
		}
		else if (input.equals("Player two selected")) {
			
		}
		else if (input.equals("Player three selected")) {
			
		}
		else if (input.equals("Player four selected")) {
			
		}
		else if (input.equals("EXIT")) {
			System.exit(0);
		}
	}
}
