/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.application.client;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Factory to produce images of the 52 cards in the deck.
 * This is implemented as a singleton.
 * @author Michael Burns
 * @version Jan 4, 2014
 */
public class CardImageFactory {

	private static final CardImageFactory instance = new CardImageFactory();
	
	/**
	 * Default private constructor to ensure this is a singleton.
	 * This is deliberately left empty.
	 */
	private CardImageFactory() {
		// Intentionally left empty.
	}
	
	/**
	 * @return the instance.
	 */
	public static CardImageFactory getInstance() {
		return instance;
	}
	
	/**
	 * This function takes in the name of a card,
	 * and produces an appropriately sized version of it.
	 * @param cardName The name of the card whose
	 * image we want.
	 * @return The image of the card.
	 */
	public ImageIcon createCard(String cardName) {
		final ImageIcon originalIcon = new ImageIcon("lib/cards/" + cardName + ".png");
		final BufferedImage resizedImage = new BufferedImage(100, 125, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphic = resizedImage.createGraphics();
		graphic.drawImage(originalIcon.getImage(), 0, 0, 100, 125, null);
		return new ImageIcon(resizedImage);
	}
	
	/**
	 * This function takes in the name of a card,
	 * and produces an appropriately sized version of it
	 * that is horizontal, for the left and right players.
	 * @param cardName The name of the card whose
	 * image we want.
	 * @return The image of the card.
	 */
	public ImageIcon createHorizontalCard(String cardName) {
		final ImageIcon originalIcon = new ImageIcon("lib/cards/" + cardName + ".png");
		final BufferedImage resizedImage = new BufferedImage(125, 100, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphic = resizedImage.createGraphics();
		graphic.drawImage(originalIcon.getImage(), 0, 0, 125, 100, null);
		return new ImageIcon(resizedImage);
	}
}
