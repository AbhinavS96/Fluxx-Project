package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class DrawLimit extends Rule {
	int drawLimit;

	public DrawLimit(String name, int drawLimit) {
		// Calls the super constructor with the name argument
		super(name);

		this.drawLimit = drawLimit;
	}

	@Override
	public void cardAction(Game game) {
		//check if the draw limit is greater. If so, trigger sufficient draws for the 
		//current user.
		// set the draw limit for the game to the value of the drawLimit instance
		// variable
		game.setDrawLimit(this.drawLimit);

	}
}
