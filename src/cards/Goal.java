package cards;

import java.util.List;

/**
 * @author Massina
 *
 */

public class Goal {
	List<Integer> goalRequirements;
	String goalDescription;

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

}
