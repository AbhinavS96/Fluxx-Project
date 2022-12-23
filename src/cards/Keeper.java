package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class Keeper extends Card{
	private String cardName;

	public Keeper(String cardName) {
		super("KEEPER");
		this.cardName = cardName;
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
