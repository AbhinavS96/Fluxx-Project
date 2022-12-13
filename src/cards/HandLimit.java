package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class HandLimit extends Rule {
	int handLimit;

	public int getHandLimit() {
		return this.handLimit;
	}

	public void setHandLimit(int handLimit) {
		this.handLimit = handLimit;
	}

	@Override
	public void cardAction(Game game) {
		////set the hand limit for the game to the value of the handLimit instance variable
		game.setHandLimit(this.handLimit);
	}
}
