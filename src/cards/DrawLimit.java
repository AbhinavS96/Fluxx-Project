package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class DrawLimit extends Rule {
	private int drawLimit;

	public DrawLimit(String name, int drawLimit) {
		// Calls the super constructor with the name argument
		super(name, "Draw " + drawLimit + " cards per turn. If you just played this card, draw extra cards as needed to reach 2 cards drawn.");
		this.drawLimit = drawLimit;
	}

	@Override
	public void cardAction(Game game) {
		//if the current user can draw more, he draws more. this is handled in game.setDrawLimit()
		// set the draw limit for the game to the value of the drawLimit instance
		// variable
		game.setDrawLimit(this.drawLimit);

	}
	
	public String toString() {
		return "NEW " + this.cardType + "\n" 
				+ "Draw " + this.drawLimit + "\n"
				+ "Replaces Draw Rule" + "\n"
				+ this.cardDescription;
	}
}
