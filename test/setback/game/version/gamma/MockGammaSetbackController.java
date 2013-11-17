/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version.gamma;

import setback.common.PlayerNumber;
import setback.game.SetbackGameController;
import setback.game.common.Hand;

/**
 * This is a mock of the GammaSetbackGame.
 * It is being used for end of game testing.
 * @author Michael Burns
 * @version Nov 7, 2013
 */
public class MockGammaSetbackController extends GammaSetbackGame implements
		SetbackGameController {

	/**
	 * Constructor that takes in two scores to begin with
	 * as the TeamOneScore and TeamTwoScore.
	 * @param teamOneScore The initial score for team one.
	 * @param teamTwoScore The initial score for team two.
	 */
	public MockGammaSetbackController(int teamOneScore, int teamTwoScore) {
		this.teamOneScore = teamOneScore;
		this.teamTwoScore = teamTwoScore;
	}

	/**
	 * Overrides the initialization function to skip the
	 * team score zeroing.
	 */
	@Override
	protected void initializeGame() {
		gameStarted = true;
		dealer = PlayerNumber.PLAYER_ONE;
		currentPlayer = PlayerNumber.PLAYER_ONE;
		playerOneHand = new Hand(PlayerNumber.PLAYER_ONE);
		playerTwoHand = new Hand(PlayerNumber.PLAYER_TWO);
		playerThreeHand = new Hand(PlayerNumber.PLAYER_THREE);
		playerFourHand = new Hand(PlayerNumber.PLAYER_FOUR); 
	}
}
