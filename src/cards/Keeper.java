package cards;

import java.util.Objects;

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
	
	public String getCardName() {
		return this.cardName;
	}
	
	//Using this to make contains method work as expected
	//Reference https://stackoverflow.com/questions/2642589/how-does-a-arraylists-contains-method-evaluate-objects
	@Override
	public int hashCode() {
		return Objects.hash(cardName);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Keeper)
        {
            return Objects.equals(cardName, ((Keeper)(object)).cardName);
        }
		return false;
	}

}
