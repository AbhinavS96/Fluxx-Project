package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public abstract class Card {
	String cardName;

	public Card(String name) {
		this.cardName = name;
	}

	public abstract void cardAction(Game game);

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
