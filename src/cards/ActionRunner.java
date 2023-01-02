package cards;

import game.Game;

/**
 * 
 * @author abhinav
 * This is a functional interface used to implement Action cards.
 * While a new Action() is created, a unique labda expression can be passed as a parameter to implement the action 
 *
 */

//Reference: https://www.geeksforgeeks.org/functional-interfaces-java/
@FunctionalInterface
public interface ActionRunner {
	void run(Game game);
}
