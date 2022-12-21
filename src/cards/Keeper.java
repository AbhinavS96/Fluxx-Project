package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class Keeper extends Card{
	int keeperID;

	public Keeper(int keeperID) {
		this.keeperID = keeperID;
	}

	public int getKeeperID() {
		return this.keeperID;
	}

	public void setKeeperID(int keeperID) {
		this.keeperID = keeperID;
	}

	@Override
	public void cardAction(Game game) {
		//add keeper to the currentPlayer 
		//check if someone won
		
	}

}
