package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class PlayLimit extends Rule {
	private int playLimit;

	public PlayLimit(int playLimit) {
		// Call the super constructor with the name argument
		super("Play " + playLimit + " cards per turn. If you have fewer than that, play all your cards");
		this.playLimit = playLimit;
	}

	@Override
	public void cardAction(Game game) {
		// set the play limit for the game to the value of the playLimit instance
		// variable
		game.setPlayLimit(this.playLimit);
	}
	
	public String toString() {
		return "NEW " + this.cardType + "\n" 
				+ "PLay " + this.playLimit + "\n"
				+ "Replaces Play Rule" + "\n"
				+ this.cardDescription;
	}
}
