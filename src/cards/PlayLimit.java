package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class PlayLimit extends Rule {
	int playLimit;

	public PlayLimit(String name, int playLimit) {
		// Call the super constructor with the name argument
		super(name);

		this.playLimit = playLimit;
	}

	@Override
	public void cardAction(Game game) {
		// set the play limit for the game to the value of the playLimit instance
		// variable
		game.setPlayLimit(this.playLimit);
	}
}
