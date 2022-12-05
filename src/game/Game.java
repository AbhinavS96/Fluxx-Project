package game;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.Goal;
import player.Player;

/**
 * @author Abhinav
 *
 */

public class Game {
	
	int playLimit, handLimit, drawLimit, keeperLimit;
	Goal currentGoal;
	List<Card> deck;
	List<Card> discardPile;
	List<Player> players;
	Player currentPlayer;
	
	public Game(List<Card> deck, List<Player> players) {
		/**
		 * Constructor that sets up the game. 
		 * The limits are set as per the basic rules. -1 represents that the rule is not in play;
		 * The shuffled deck and players are passed as arguments
		 * The discard pile is empty. Current player is not set.
		 */		
		
		this.playLimit = 1;
		this.handLimit = -1;
		this.drawLimit = 1;
		this.keeperLimit = -1;		
		this.deck = deck;
		this.players = players;
		this.currentGoal = null;
		this.discardPile = new ArrayList<>();
	}
	
	public void setPlayLimit(int playLimit) {
		this.playLimit = playLimit;
	}

	public void setHandLimit(int handLimit) {
		this.handLimit = handLimit;
	}

	public void setDrawLimit(int drawLimit) {
		this.drawLimit = drawLimit;
	}

	public void setKeeperLimit(int keeperLimit) {
		this.keeperLimit = keeperLimit;
	}
	
	public void setGoal(Goal goal) {
		this.currentGoal = goal;
	}
	
	public void play() {
		/**
		 * This the where the game loop runs
		 */
	}
	
	public void checkWinner() {
		
	}
	
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	public void viewRules() {
		/**
		 * This function outputs the current rules of the game for the players' reference
		 */
	}
	
	public void viewGoals() {
		/**
		 * This function outputs the current goal of the game
		 */
		
		if(this.currentGoal == null) {
			System.out.println("No goal set");
			return;
		}
		
		System.out.println(this.currentGoal.getCardName());
	}
}
