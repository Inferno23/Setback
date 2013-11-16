/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

import setback.common.PlayerNumber;

/**
 * This class is used to identify cards and their associated player.
 * These are used for remembering who played what in a particular
 * trick.
 * @author Michael Burns
 * @version Oct 17, 2013
 */
public class CardPlayerDescriptor {

	private final Card card;
	private final PlayerNumber player;
	
	/**
	 * Construct a CardPlayerDescriptor with the given card
	 * and player who played the card.
	 * @param card The card that was played.
	 * @param player The player who played the card.
	 */
	public CardPlayerDescriptor(Card card, PlayerNumber player) {
		this.card = card;
		this.player = player;
	}

	/**
	 * @return the card.
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @return the player.
	 */
	public PlayerNumber getPlayer() {
		return player;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean equality;
		if (other == this) {
			equality = true;
		}
		else if (other instanceof CardPlayerDescriptor) {
			final CardPlayerDescriptor that = (CardPlayerDescriptor) other;
			equality = (card.equals(that.card) && player.equals(that.player));
		}
		else {
			equality = false;
		}
		return equality;
	}
	
	@Override
	public int hashCode() {
		return card.hashCode() * player.hashCode();
	}
}
