package cards;

import game.Game;

/**
 * @author Massina
 *
 */

public class KeeperLimit extends Rule {
	private final int keeperLimit;

	public KeeperLimit(int keeperLimit) {
		// Calls the super constructor with the name argument
		super("If it isn't your turn, you can only have " + keeperLimit + " keepers in play. Discard extras immediately. You may acquire new Keepers during your turn as long as you discard down to " + keeperLimit + " when your turn ends");
		this.keeperLimit = keeperLimit;
	}

	@Override
	public void cardAction(Game game) {
		// set the keeper limit for the game to the value of the keeperLimit instance
		// variable
		game.setKeeperLimit(this.keeperLimit);
		//remove extra keepers for everyone except current player
	}
	
	public String toString() {
		return "NEW " + this.cardType + "\n" 
				+ "Keeper Limit " + this.keeperLimit + "\n"
				+ "Replaces Keeper Limit" + "\n"
				+ this.cardDescription;
	}
}
