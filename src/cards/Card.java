package cards;

import game.Game;

public abstract class Card {
	String cardName;
	
	public abstract void cardAction(Game game);
	
	public String getCardName() {
		
	}

	public void setCardName(String cardName) {
		
	}
}
