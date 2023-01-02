package cards;

import game.Game;

/**
 * 
 * @author abhinav
 * Class for implementing actions
 * It accepts an ActionRunner object which implements the run function that defines the action
 *
 */

public class Action extends Card {

	/**
	 * actionRunner contains the run method. This is unique to each action and implements its behavior
	 * cardName is the name of the action
	 * cardDescription describes the action as shown on the card
	 */
	private final ActionRunner actionRunner;
	private final String cardName;
	private final String cardDescription;
	
	public Action(String cardName, String cardDescription, ActionRunner actionRunner) {
		super("Action");
		this.actionRunner = actionRunner;
		this.cardName = cardName;
		this.cardDescription = cardDescription;
	}

	@Override
	public void cardAction(Game game) {
		actionRunner.run(game);
		game.addToDiscardPile(this);
	}
	
	@Override
	public String toString() {
		return this.cardType + "\n" + this.cardName + "\n" + this.cardDescription;
	}

}
