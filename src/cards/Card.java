package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public abstract class Card {
	protected final String cardType;

	/**
	 * 
	 * @param cardType can be Rule, Keeper, Goal, etc depending on the game type.
	 * This is only used for printing text
	 */
	public Card(String cardType) {
		this.cardType = cardType;
	}

	public abstract void cardAction(Game game);
}
