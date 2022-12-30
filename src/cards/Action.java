package cards;

import game.Game;

public class Action extends Card {

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
