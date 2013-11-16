/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.common;

/**
 * This enumeration defines the possible outcomes of a Round.
 * It will either be ok, or a team will have one.
 * @author Michael Burns
 * @version Nov 7, 2013
 */
public enum RoundResultStatus {
	OK,
	TEAM_ONE_WINS,
	TEAM_TWO_WINS;
}
