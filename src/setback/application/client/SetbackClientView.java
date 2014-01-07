package setback.application.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import setback.application.client.views.PlaceBetsView;

/**
 * This abstract class handles the basic GUI of Setback.
 * All specific screens are handled in subclasses of this.
 * @author Michael
 * @version Jan 2, 2014
 */
public abstract class SetbackClientView {

	// High level variables
	protected SetbackClientController controller;
	protected CardImageFactory factory;
	// Random variables
	protected int DELAY;
	protected int CARD_OFFSET;
	// GUI variables
	protected JFrame frame;
	protected List<JButton> buttonList;
	private JLabel cardImage;

	/**
	 * Create the GUI that the user will interact with.
	 * @param controller The SetbackClientController that
	 * will handle all of the communication with the server.
	 * @param frame The JFrame that the application runs in.
	 */
	public SetbackClientView(SetbackClientController controller, JFrame frame) {
		this.controller = controller;
		this.frame = frame;
		factory = CardImageFactory.getInstance();
		DELAY = 2000;
		CARD_OFFSET = 20;
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
		buttonList = new ArrayList<JButton>();
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
			controller.setPlayerNumbers(input);
			new PlaceBetsView(controller, frame);
		}
		else if (input.contains("BETTING RESOLVED")) {
			new SelectTrumpView(controller, frame);
		}
		else if (input.contains(" SELECTED ")) {
			System.out.println("We have selected trump");
		}
		else if (input.equals("BEGIN GAME")) {
			System.out.println("We made it!");
		}
		else if (input.equals("EXIT")) {
			System.exit(0);
		}
	}

	/**
	 * This helper function enables or disables
	 * all of the listed buttons based on the toggle field.
	 * @param buttonList The list containing the buttons
	 * to be toggled.
	 * @param toggle The boolean flag that determines
	 * if the buttons should be enabled or disabled.
	 */
	protected void toggleButtons(List<JButton> buttonList, boolean toggle) {
		for(JButton button : buttonList) {
			button.setEnabled(toggle);
		}
	}
	
	/**
	 * Helper function that initializes the displaying of a hand.
	 * @param handContents The contents of the player's hand.
	 */
	protected void displayHand(String handContents) {
		// Parse the cards
		String cards[] = handContents.split("\t");
		for (int index = 12; index > 0; index--) {
			cardImage = new JLabel(factory.createCard(cards[index]));
			cardImage.setBounds(230 + (CARD_OFFSET * index), 400, 100, 125);
			frame.getContentPane().add(cardImage);
		}
		frame.repaint();
	}

	/**
	 * Helper function that displays the neighbors hands.
	 * All of the cards appear the same, as you only see the backs.
	 */
	protected void displayNeighborHands() {
		int index;
		// Left neighbor
		for (index = 12; index > 0; index--) {
			cardImage = new JLabel(factory.createHorizontalCard("Back-Left"));
			cardImage.setBounds(35, 60 + (CARD_OFFSET * index), 125, 100);
			frame.getContentPane().add(cardImage);
		}
		// Center neighbor
		for (index = 1; index < 13; index++) {
			cardImage = new JLabel(factory.createCard("Back-Center"));
			cardImage.setBounds(235 + (CARD_OFFSET * index), 30, 100, 125);
			frame.getContentPane().add(cardImage);
		}
		// Right neighbor
		for (index = 1; index < 13; index++) {
			cardImage = new JLabel(factory.createHorizontalCard("Back-Right"));
			cardImage.setBounds(710, 40 + (CARD_OFFSET * index), 125, 100);
			frame.getContentPane().add(cardImage);
		}
		frame.repaint();
	}
}
