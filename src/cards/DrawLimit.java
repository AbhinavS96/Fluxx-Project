package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class DrawLimit extends Rule {
	int drawLimit;

	public int getDrawLimit() {
		return this.drawLimit;
	}

	public void setDrawLimit(int drawLimit) {
		this.drawLimit = drawLimit;
	}

	@Override
	public void cardAction(Game game) {
		//set the draw limit for the game to the value of the drawLimit instance variable
		game.setDrawLimit(this.drawLimit);
		
	}
}
