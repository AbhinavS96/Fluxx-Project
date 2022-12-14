package cards;

import java.util.List;

import game.Game;

/**
 * @author Massina
 *
 */

public class Goal extends Card{
	List<Integer> goalRequirements;
	String goalDescription;

	public Goal(String name, List<Integer> goalRequirements, String goalDescription) {
		super(name);
		this.goalRequirements = goalRequirements;
		this.goalDescription = goalDescription;
	}

	public List<Integer> getGoalRequirements() {
		return this.goalRequirements;
	}

	public void setGoalRequirements(List<Integer> goalRequirements) {
		this.goalRequirements = goalRequirements;
	}

	public String getGoalDescription() {
		return this.goalDescription;
	}

	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
	}

	@Override
	public void cardAction(Game game) {
		// TODO Auto-generated method stub
		
	}
}
