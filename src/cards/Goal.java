package cards;

import java.util.List;

import game.Game;

/**
 * @author Massina
 *
 */

public class Goal extends Card{
	private List<String> goalRequirements;

	public Goal(String name, String cardDescription, List<String> goalRequirements) {
		super(name, "GOAL", cardDescription);
		this.goalRequirements = goalRequirements;
	}

	public List<String> getGoalRequirements() {
		return this.goalRequirements;
	}

	@Override
	public void cardAction(Game game) {
		game.setGoal(this);
		game.checkWinner();
	}
	
	public String toString() {
		String response = this.cardType + "\n" + this.cardName + "\n" + this.goalRequirements.get(0);
		for(int i = 1; i < this.goalRequirements.size(); ++i) {
			response += this.goalRequirements.get(i);
		}
		return response;
	}
}
