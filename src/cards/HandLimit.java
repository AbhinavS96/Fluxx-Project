package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class HandLimit extends Rule {
	int handLimit;

	public HandLimit(String name, int handLimit) {
		// Calls the super constructor with the name argument
		super(name);

		this.handLimit = handLimit;
	}

	@Override
	public void cardAction(Game game) {
		//// set the hand limit for the game to the value of the handLimit instance
		//// variable
		game.setHandLimit(this.handLimit);
		//everyone except current player discards
	}
}
