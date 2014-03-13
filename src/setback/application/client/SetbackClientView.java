package setback.application.client;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import setback.application.views.DiscardCardsView;
import setback.application.views.PlaceBetsView;
import setback.application.views.PlayCardsView;
import setback.application.views.SelectTrumpView;
import setback.game.common.Card;

/**
 * This abstract class handles the basic GUI of Setback.
 * All specific screens are handled in subclasses of this.
 * @author Michael
 * @version Jan 2, 2014
 */
public abstract class SetbackClientView {

	// GUI Constants
	protected int GUI_X = 100;
	protected int GUI_Y = 100;
	protected int GUI_WIDTH = 1200;
	protected int GUI_HEIGHT = 800;
	protected int GUI_WIDTH_CENTER = GUI_WIDTH / 2;
	protected int GUI_HEIGHT_CENTER = GUI_HEIGHT / 2;
	protected int GUI_TEXT_HEIGHT = 20;
	protected int GUI_SPACING_CONSTANT = 25;
	protected int GUI_SPACING_CONSTANT_HALF = GUI_SPACING_CONSTANT / 2 ;
	protected int UNICODE_SIZE_CONSTANT = 8;
	// Cards
	protected int GUI_CARD_WIDTH = 100;
	protected int GUI_CARD_HEIGHT = 125;
	protected int GUI_CARD_SPACING = 20;
	protected int GUI_CARD_BOTTOM_Y = GUI_HEIGHT * 7 / 8 - GUI_CARD_HEIGHT;
	protected int GUI_CARD_LEFT_X = GUI_WIDTH / 8 - GUI_CARD_HEIGHT;
	protected int GUI_CARD_TOP_Y = GUI_HEIGHT / 8;
	protected int GUI_CARD_RIGHT_X = GUI_WIDTH * 7 / 8;
	protected int GUI_CARD_DISCARD_SHIFT = 25;
	protected int GUI_CARD_PLAYED_SHIFT = GUI_CARD_HEIGHT + GUI_CARD_DISCARD_SHIFT;
	
	// PlayerSelectView
	protected int GUI_PLAYER_SELECT_BUTTON_WIDTH = 200;
	protected int GUI_PLAYER_SELECT_BUTTON_HEIGHT = 100;
	protected int GUI_PLAYER_SELECT_LEFT_COLUMN_X = GUI_WIDTH_CENTER - (GUI_PLAYER_SELECT_BUTTON_WIDTH + GUI_SPACING_CONSTANT_HALF);
	protected int GUI_PLAYER_SELECT_RIGHT_COLUMN_X = GUI_WIDTH_CENTER + GUI_SPACING_CONSTANT_HALF;
	protected int GUI_PLAYER_SELECT_TOP_ROW_Y = GUI_HEIGHT_CENTER - (GUI_PLAYER_SELECT_BUTTON_HEIGHT + GUI_SPACING_CONSTANT_HALF);
	protected int GUI_PLAYER_SELECT_BOTTOM_ROW_Y = GUI_HEIGHT_CENTER + GUI_SPACING_CONSTANT_HALF;
	// PlaceBetsView
	protected int GUI_PLACE_BET_BUTTON_WIDTH = 75;
	protected int GUI_PLACE_BET_BUTTON_HEIGHT = 75;
	protected int GUI_PLACE_BET_BUTTON_Y = GUI_HEIGHT_CENTER - (GUI_PLACE_BET_BUTTON_HEIGHT / 2);
	protected int GUI_PLACE_BET_STRING_LENGTH = 160;
	// SelectTrumpView
	protected int GUI_SELECT_TRUMP_BUTTON_WIDTH = 125;
	protected int GUI_SELECT_TRUMP_BUTTON_HEIGHT = 75;
	protected int GUI_SELECT_TRUMP_BUTTON_Y = GUI_HEIGHT_CENTER - (GUI_SELECT_TRUMP_BUTTON_HEIGHT / 2);
	protected int GUI_SELECT_TRUMP_STRING_LENGTH = 250;
	// DiscardCardsView
	protected int GUI_DISCARD_CARDS_BUTTON_WIDTH = 125;
	protected int GUI_DISCARD_CARDS_BUTTON_HEIGHT = 75;
	protected int GUI_DISCARD_CARDS_BUTTON_Y = GUI_HEIGHT_CENTER - (GUI_DISCARD_CARDS_BUTTON_HEIGHT / 2);
	protected int GUI_DISCARD_CARDS_STRING_LENGTH = 250;
	// PlayCardsView
	protected int GUI_PLAY_CARDS_STRING_Y = GUI_CARD_TOP_Y - GUI_SPACING_CONSTANT;
	protected int GUI_PLAY_CARDS_STRING_LENGTH = 200;
	
	// High level variables
	protected SetbackClientController controller;
	protected CardImageFactory factory;
	// Random variables
	protected int DELAY = 2000;
//	protected int DELAY = 5000;
	// GUI variables
	protected JFrame frame;
	protected List<JButton> buttonList;
	protected List<JLabel> cardList;
	protected List<Card> discardList;
	// Cards in my hand
	protected JLabel cardOne;
	protected JLabel cardTwo;
	protected JLabel cardThree;
	protected JLabel cardFour;
	protected JLabel cardFive;
	protected JLabel cardSix;
	protected JLabel cardSeven;
	protected JLabel cardEight;
	protected JLabel cardNine;
	protected JLabel cardTen;
	protected JLabel cardEleven;
	protected JLabel cardTwelve;
	// Neighbor hands
	protected JLabel[] leftCards;
	protected JLabel[] centerCards;
	protected JLabel[] rightCards;
	// Played cards
	protected JLabel myCard;
	protected JLabel leftCard;
	protected JLabel centerCard;
	protected JLabel rightCard;
	// Playing card variables
	protected Timer cardTimer;
	protected Timer pauseTimer;
	protected boolean unpauseToggle;
	protected JLabel currentPlayerLabel;

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
		leftCards = new JLabel[13];
		centerCards = new JLabel[13];
		rightCards = new JLabel[13];
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
		cardList = new ArrayList<JLabel>();
		discardList = new ArrayList<Card>();
		// Initialize the application frame
		frame.getContentPane().setBackground(new Color(60, 179, 113));
		frame.setResizable(false);
		frame.setTitle("Setback");
		frame.setBounds(GUI_X, GUI_Y, GUI_WIDTH, GUI_HEIGHT);
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
			new DiscardCardsView(controller, frame);
		}
		else if (input.contains("TRICK STARTED")) {
			new PlayCardsView(controller, frame);
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
	 * @param listener The type of listener to add to the cards.
	 */
	protected void displayHand(String handContents, ListenerEnum listener) {
		// Parse the cards
		final String cards[] = handContents.split("\t");
		// Card labels and bounds
		if (cards.length > 12) {
			cardTwelve = new JLabel(factory.createCard(cards[12]));
			cardTwelve.setBounds(GUI_WIDTH_CENTER + (5 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardTwelve, cards[12]);
			frame.getContentPane().add(cardTwelve);
			cardList.add(cardTwelve);
		}
		if (cards.length > 11) {
			cardEleven = new JLabel(factory.createCard(cards[11]));
			cardEleven.setBounds(GUI_WIDTH_CENTER + (4 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardEleven, cards[11]);
			frame.getContentPane().add(cardEleven);
			cardList.add(cardEleven);
		}
		if (cards.length > 10) {
			cardTen = new JLabel(factory.createCard(cards[10]));
			cardTen.setBounds(GUI_WIDTH_CENTER + (3 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardTen, cards[10]);
			frame.getContentPane().add(cardTen);
			cardList.add(cardTen);
		}
		if (cards.length > 9) {
			cardNine = new JLabel(factory.createCard(cards[9]));
			cardNine.setBounds(GUI_WIDTH_CENTER + (2 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardNine, cards[9]);
			frame.getContentPane().add(cardNine);
			cardList.add(cardNine);
		}
		if (cards.length > 8) {
			cardEight = new JLabel(factory.createCard(cards[8]));
			cardEight.setBounds(GUI_WIDTH_CENTER + (1 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardEight, cards[8]);
			frame.getContentPane().add(cardEight);
			cardList.add(cardEight);
		}
		if (cards.length > 7) {
			cardSeven = new JLabel(factory.createCard(cards[7]));
			cardSeven.setBounds(GUI_WIDTH_CENTER + (0 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardSeven, cards[7]);
			frame.getContentPane().add(cardSeven);
			cardList.add(cardSeven);
		}
		if (cards.length > 6) {
			cardSix = new JLabel(factory.createCard(cards[6]));
			cardSix.setBounds(GUI_WIDTH_CENTER - (1 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardSix, cards[6]);
			frame.getContentPane().add(cardSix);
			cardList.add(cardSix);
		}
		if (cards.length > 5) {
			cardFive = new JLabel(factory.createCard(cards[5]));
			cardFive.setBounds(GUI_WIDTH_CENTER - (2 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardFive, cards[5]);
			frame.getContentPane().add(cardFive);
			cardList.add(cardFive);
		}
		if (cards.length > 4) {
			cardFour = new JLabel(factory.createCard(cards[4]));
			cardFour.setBounds(GUI_WIDTH_CENTER - (3 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardFour, cards[4]);
			frame.getContentPane().add(cardFour);
			cardList.add(cardFour);
		}
		if (cards.length > 3) {
			cardThree = new JLabel(factory.createCard(cards[3]));
			cardThree.setBounds(GUI_WIDTH_CENTER - (4 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardThree, cards[3]);
			frame.getContentPane().add(cardThree);
			cardList.add(cardThree);
		}
		if (cards.length > 2) {
			cardTwo = new JLabel(factory.createCard(cards[2]));
			cardTwo.setBounds(GUI_WIDTH_CENTER - (5 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardTwo, cards[2]);
			frame.getContentPane().add(cardTwo);
			cardList.add(cardTwo);
		}
		if (cards.length > 1) {
			cardOne = new JLabel(factory.createCard(cards[1]));
			cardOne.setBounds(GUI_WIDTH_CENTER - (6 * GUI_CARD_SPACING),
					GUI_CARD_BOTTOM_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			addListener(listener, cardOne, cards[1]);
			frame.getContentPane().add(cardOne);
			cardList.add(cardOne);
		}
		frame.repaint();
	}

	/**
	 * Helper function that displays the neighbors hands.
	 * All of the cards appear the same, as you only see the backs.
	 * @param numCards The number of cards in the hands to display.
	 */
	protected void displayNeighborHands(int numCards) {
		// Left neighbor
		displayLeftHand(numCards);
		// Center neighbor
		displayCenterHand(numCards);
		// Right neighbor
		displayRightHand(numCards);
	}
	
	/**
	 * Helper function that displays the left neighbor's hand.
	 * @param numCards The number of cards in the left hand.
	 */
	protected void displayLeftHand(int numCards) {
		int index;
		int offsetY = GUI_HEIGHT_CENTER - (numCards * GUI_CARD_SPACING);
		
		for (index = numCards; index > 0; index--) {
			leftCards[index] = new JLabel(factory.createHorizontalCard("Back-Left"));
			leftCards[index].setBounds(GUI_CARD_LEFT_X, offsetY + (GUI_CARD_SPACING * index), GUI_CARD_HEIGHT, GUI_CARD_WIDTH);
			frame.getContentPane().add(leftCards[index]);
		}
		frame.repaint();
	}
	
	/**
	 * Helper function that displays the center neighbor's hand.
	 * @param numCards The number of cards in the center hand.
	 */
	protected void displayCenterHand(int numCards) {
		int index;
		int offsetX = GUI_WIDTH_CENTER - (numCards * GUI_CARD_SPACING);
		
		for (index = 1; index <= numCards; index++) {
			centerCards[index] = new JLabel(factory.createCard("Back-Center"));
			centerCards[index].setBounds(offsetX + (GUI_CARD_SPACING * index), GUI_CARD_TOP_Y, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
			frame.getContentPane().add(centerCards[index]);
		}
		frame.repaint();
	}
	
	/**
	 * Helper function that displays the right neighbor's hand.
	 * @param numCards The number of cards in the right hand.
	 */
	protected void displayRightHand(int numCards) {
		int index;
		int offsetY = GUI_HEIGHT_CENTER - (numCards * GUI_CARD_SPACING);
		
		for (index = 1; index <= numCards; index++) {
			rightCards[index] = new JLabel(factory.createHorizontalCard("Back-Right"));
			rightCards[index].setBounds(GUI_CARD_RIGHT_X, offsetY + (GUI_CARD_SPACING * index), GUI_CARD_HEIGHT, GUI_CARD_WIDTH);
			frame.getContentPane().add(rightCards[index]);
		}
		frame.repaint();
	}
	
	/**
	 * This helper function adds a listener of the specified type
	 * to the specified card.
	 * @param type The type of listener to attach.
	 * @param card The card to attach the listener to.
	 * @param cardName The string name of the card.
	 */
	protected void addListener(ListenerEnum type, final JLabel card, final String cardName) {
		switch(type) {
		case SHIFT_UP:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (discardList.size() < 3) {
						discardList.add(Card.fromString(cardName));
						card.setBounds(card.getX(), card.getY() - GUI_CARD_DISCARD_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
						addListener(ListenerEnum.SHIFT_DOWN, card, cardName);
					}
				}
			});
			break;
		case SHIFT_DOWN:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					discardList.remove(Card.fromString(cardName));
					card.setBounds(card.getX(), card.getY() + GUI_CARD_DISCARD_SHIFT, GUI_CARD_WIDTH, GUI_CARD_HEIGHT);
					addListener(ListenerEnum.SHIFT_UP, card, cardName);
				}
			});
			break;
		default:
			break;
		}
	}
	
}
