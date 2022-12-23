package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public abstract class Card {
	protected String cardType;

	public Card(String cardType) {
		this.cardType = cardType;
	}

	public abstract void cardAction(Game game);
}
