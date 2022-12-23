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
		super(name, "You can only have " + handLimit + " card in your hand. Discard extras immediately. During your turn this rule does not apply to you; discard at the end of your turn");

		this.handLimit = handLimit;
	}

	@Override
	public void cardAction(Game game) {
		//// set the hand limit for the game to the value of the handLimit instance
		//// variable
		game.setHandLimit(this.handLimit);
		//everyone except current player discards
		
	}
	
	public String toString() {
		return "NEW " + this.cardType + "\n" 
				+ "Hand Limit " + this.handLimit + "\n"
				+ "Replaces Hand Limit" + "\n"
				+ this.cardDescription;
	}
}
