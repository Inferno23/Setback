/**
 * This file was developed for fun by Michael Burns for a private
 * implementation of the card game Setback, also known as Pitch.
 */
package setback.game.version;

import setback.application.SetbackObserver;
import setback.common.PlayerNumber;
import setback.common.SetbackException;
import setback.game.common.*;

import java.util.List;

import static setback.application.command.CommandMessageConstants.*;

/**
 * This is the abstract class that adds observability features to
 * Setback.  This allows the Skeleton to only focus on gameplay functionality,
 * while multiplayer support is isolated into this class.
 * @author Michael Burns
 * @version Nov 28, 2014
 */
public abstract class SetbackGameObservableImpl
		extends SetbackGameControllerSkeleton
		implements SetbackMultiplayerGame {

	/**
	 * This is the list of observers to notify.
	 */
	protected List<SetbackObserver> observers;

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#addObserver(setback.networking.SetbackObserver)
	 */
	public void addObserver(SetbackObserver observer) {
		observers.add(observer);
	}

	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#notifyObservers()
	 */
	public void notifyObservers(String message) {
		for (SetbackObserver observer : observers) {
			observer.update(message);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#selectPlayerNumber(setback.common.PlayerNumber)
	 */
	public boolean requestPlayerNumber(PlayerNumber requestedNumber) {
		final boolean result = super.requestPlayerNumber(requestedNumber);
		String message;
		if (result) {
			switch(requestedNumber) {
			case PLAYER_ONE:
				message = "PlayerOneSelected";
				break;
			case PLAYER_TWO:
				message = "PlayerTwoSelected";
				break;
			case PLAYER_THREE:
				message = "PlayerThreeSelected";
				break;
			case PLAYER_FOUR:
			default:
				message = "PlayerFourSelected";
				break;
			}
			notifyObservers(message);
		}
		return result;
	}
	
	/**
	 * All functions below here merely call the superclass version,
	 * and then notify the observers what has happened.
	 */
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.version.SetbackGameControllerSkeleton#startRound()
	 */
	@Override
	public void startRound() throws SetbackException {
		super.startRound();
		notifyObservers(ROUND_BEGIN);
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.version.SetbackGameControllerSkeleton#placeBet(setback.common.PlayerNumber, setback.game.common.Bet)
	 */
	@Override
	public void placeBet(PlayerNumber bettor, Bet bet)
			throws SetbackException {
		super.placeBet(bettor, bet);
		notifyObservers(bettor.toString() + " BET " + bet.toString().toUpperCase());
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#resolveBets()
	 */
	@Override
	public void resolveBets() throws SetbackException {
		super.resolveBets();
		notifyObservers(BETTING_RESOLVED);
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#selectTrump(setback.common.PlayerNumber, setback.game.common.CardSuit)
	 */
	@Override
	public void selectTrump(PlayerNumber leader, CardSuit trump) throws SetbackException {
		super.selectTrump(leader, trump);
		notifyObservers(leader.toString() + " SELECTED " + trump.toString());
	}
	
	/*
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#discardCards(setback.common.PlayerNumber, setback.game.common.Card, setback.game.common.Card, setback.game.common.Card)
	 */
	public void discardCards(PlayerNumber player, Card cardOne, Card cardTwo, Card cardThree) throws SetbackException {
		super.discardCards(player, cardOne, cardTwo, cardThree);
		notifyObservers(player.toString() + " DISCARDED");
	}
	
	/* 
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#startTrick()
	 */
	@Override
	public void startTrick() throws SetbackException {
		super.startTrick();
		notifyObservers(TRICK_STARTED);
	}
	
	/* 
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#playCard(setback.game.common.Card, setback.common.PlayerNumber)
	 */
	@Override
	public CardPlayerDescriptor playCard(Card card, PlayerNumber player)
			throws SetbackException {
		final CardPlayerDescriptor result = super.playCard(card, player);
		notifyObservers(player.toString() + " PLAYED " + card.toString());
		return result;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#playTrick(setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor,
	 * setback.game.common.CardPlayerDescriptor)
	 */
	@Override
	public TrickResult playTrick(CardPlayerDescriptor firstCard,
			CardPlayerDescriptor secondCard, CardPlayerDescriptor thirdCard,
			CardPlayerDescriptor fourthCard) throws SetbackException {
		final TrickResult result = super.playTrick(firstCard, secondCard, thirdCard, fourthCard);
		notifyObservers(currentPlayer.toString() + " WON TRICK");
		return result;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see setback.game.SetbackGameController#playRound(java.util.List)
	 */
	@Override
	public RoundResult playRound(List<TrickResult> tricks)
			throws SetbackException {
		final RoundResult result = super.playRound(tricks);
		notifyObservers(ROUND_ENDED);
		return result;
	}

}
