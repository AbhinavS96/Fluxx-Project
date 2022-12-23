package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public abstract class Card {
	protected String cardName;
	protected String cardType;
	protected String cardDescription;

	public Card(String name, String cardType, String cardDescription) {
		this.cardName = name;
		this.cardType = cardType;
		this.cardDescription = cardDescription;
	}

	public abstract void cardAction(Game game);

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
