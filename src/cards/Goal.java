package cards;

import java.util.List;

import game.Game;

/**
 * @author Massina
 *
 */

public class Goal extends Card implements Comparable<List<Card>>{
	private final String cardName;
	private final List<String> goalRequirements;

	public Goal(String cardName, List<String> goalRequirements) {
		super("GOAL");
		this.cardName = cardName;
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
		String response = this.cardType + "\n" + this.cardName + "\n" + this.goalRequirements.get(0) + " ";
		for(int i = 1; i < this.goalRequirements.size(); ++i) {
			response += "and " + this.goalRequirements.get(i);
		}
		return response;
	}
	
	/**
	 * 
	 * @param keepersInHand are the keepers to compare
	 * The compareTo method checks if the incoming keepers satisfy the goal requirements
	 * 
	 */
	public int compareTo(List <Card> keepersInHand) {
		for(String requirement: this.goalRequirements) {
			if(!keepersInHand.contains(new Keeper(requirement))) {
				return 1;
			}
		}
		return 0;
	}
}
