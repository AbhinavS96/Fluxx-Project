package cards;

import game.Game;

//Reference: https://www.geeksforgeeks.org/functional-interfaces-java/
@FunctionalInterface
public interface ActionRunner {
	void run(Game game);
}
