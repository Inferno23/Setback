package setback.application.client;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

	// High level variables
	protected SetbackClientController controller;
	protected CardImageFactory factory;
	// Random variables
	protected int DELAY;
	protected int CARD_OFFSET;
	protected int CARD_WIDTH;
	protected int CARD_HEIGHT;
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
		CARD_WIDTH = 100;
		CARD_HEIGHT = 125;
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
			cardTwelve.setBounds(470, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardTwelve, cards[12]);
			frame.getContentPane().add(cardTwelve);
			cardList.add(cardTwelve);
		}
		if (cards.length > 11) {
			cardEleven = new JLabel(factory.createCard(cards[11]));
			cardEleven.setBounds(450, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardEleven, cards[11]);
			frame.getContentPane().add(cardEleven);
			cardList.add(cardEleven);
		}
		if (cards.length > 10) {
			cardTen = new JLabel(factory.createCard(cards[10]));
			cardTen.setBounds(430, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardTen, cards[10]);
			frame.getContentPane().add(cardTen);
			cardList.add(cardTen);
		}
		if (cards.length > 9) {
			cardNine = new JLabel(factory.createCard(cards[9]));
			cardNine.setBounds(410, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardNine, cards[9]);
			frame.getContentPane().add(cardNine);
			cardList.add(cardNine);
		}
		if (cards.length > 8) {
			cardEight = new JLabel(factory.createCard(cards[8]));
			cardEight.setBounds(390, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardEight, cards[8]);
			frame.getContentPane().add(cardEight);
			cardList.add(cardEight);
		}
		if (cards.length > 7) {
			cardSeven = new JLabel(factory.createCard(cards[7]));
			cardSeven.setBounds(370, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardSeven, cards[7]);
			frame.getContentPane().add(cardSeven);
			cardList.add(cardSeven);
		}
		if (cards.length > 6) {
			cardSix = new JLabel(factory.createCard(cards[6]));
			cardSix.setBounds(350, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardSix, cards[6]);
			frame.getContentPane().add(cardSix);
			cardList.add(cardSix);
		}
		if (cards.length > 5) {
			cardFive = new JLabel(factory.createCard(cards[5]));
			cardFive.setBounds(330, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardFive, cards[5]);
			frame.getContentPane().add(cardFive);
			cardList.add(cardFive);
		}
		if (cards.length > 4) {
			cardFour = new JLabel(factory.createCard(cards[4]));
			cardFour.setBounds(310, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardFour, cards[4]);
			frame.getContentPane().add(cardFour);
			cardList.add(cardFour);
		}
		if (cards.length > 3) {
			cardThree = new JLabel(factory.createCard(cards[3]));
			cardThree.setBounds(290, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardThree, cards[3]);
			frame.getContentPane().add(cardThree);
			cardList.add(cardThree);
		}
		if (cards.length > 2) {
			cardTwo = new JLabel(factory.createCard(cards[2]));
			cardTwo.setBounds(270, 400, CARD_WIDTH, CARD_HEIGHT);
			addListener(listener, cardTwo, cards[2]);
			frame.getContentPane().add(cardTwo);
			cardList.add(cardTwo);
		}
		if (cards.length > 1) {
			cardOne = new JLabel(factory.createCard(cards[1]));
			cardOne.setBounds(250, 400, CARD_WIDTH, CARD_HEIGHT);
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
		int index;
		// Left neighbor
		for (index = numCards; index > 0; index--) {
			leftCards[index] = new JLabel(factory.createHorizontalCard("Back-Left"));
			leftCards[index].setBounds(35, 60 + (CARD_OFFSET * index), 125, 100);
			frame.getContentPane().add(leftCards[index]);
		}
		// Center neighbor
		for (index = 1; index <= numCards; index++) {
			centerCards[index] = new JLabel(factory.createCard("Back-Center"));
			centerCards[index].setBounds(235 + (CARD_OFFSET * index), 30, 100, 125);
			frame.getContentPane().add(centerCards[index]);
		}
		// Right neighbor
		for (index = 1; index <= numCards; index++) {
			rightCards[index] = new JLabel(factory.createHorizontalCard("Back-Right"));
			rightCards[index].setBounds(710, 40 + (CARD_OFFSET * index), 125, 100);
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
	private void addListener(ListenerEnum type, final JLabel card, final String cardName) {
		switch(type) {
		case SHIFT_UP:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (discardList.size() < 3) {
						discardList.add(Card.fromString(cardName));
						card.setBounds(card.getX(), 375, CARD_WIDTH, CARD_HEIGHT);
						addListener(ListenerEnum.SHIFT_DOWN, card, cardName);
					}
				}
			});
			break;
		case SHIFT_DOWN:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					discardList.remove(Card.fromString(cardName));
					card.setBounds(card.getX(), 400, CARD_WIDTH, CARD_HEIGHT);
					addListener(ListenerEnum.SHIFT_UP, card, cardName);
				}
			});
			break;
		case PLAY:
			card.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					//card.setBounds(x, y, width, height);
				}
			});
			break;
		default:
			break;
		}
	}
}
