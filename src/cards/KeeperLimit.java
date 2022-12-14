package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class KeeperLimit extends Rule {
	int keeperLimit;

	public KeeperLimit(String name, int keeperLimit) {
		// Calls the super constructor with the name argument
		super(name);

		this.keeperLimit = keeperLimit;
	}

	@Override
	public void cardAction(Game game) {
		// set the keeper limit for the game to the value of the keeperLimit instance
		// variable
		game.setKeeperLimit(this.keeperLimit);
	}
}
