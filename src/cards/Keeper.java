package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class Keeper extends Card{

	public Keeper(String cardName) {
		super(cardName, "KEEPER", "");
	}

	@Override
	public void cardAction(Game game) {
		//add keeper to the currentPlayer 
		//check if someone won
		game.getCurrentPlayer().addKeeper(this);
		game.checkWinner();
	}
	
	public String toString() {
		return this.cardType + "\n" + this.cardName;
	}

}
