package game;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public Game(int numberOfPlayers) {
		/**
		 * Constructor that sets up the game. 
		 * The limits are set as per the basic rules. -1 represents that the rule is not in play;
		 * The discard pile is empty. Current player is not set.
		 */		
		
		this.playLimit = 1;
		this.handLimit = -1;
		this.drawLimit = 1;
		this.keeperLimit = -1;
		this.currentGoal = null;
		
		this.deck = new ArrayList<Card>();
		initializeCards();
		Collections.shuffle(deck);
		this.discardPile = new ArrayList<Card>();
		
		this.players = new ArrayList<Player>(numberOfPlayers);
		initializePlayers();
		this.currentPlayer = players.get(0);
	}
	
	private void initializeCards(){
		//add cards to the deck.
	}
	
	private void initializePlayers() {
		for(int i = 0; i < this.players.size(); ++i) {
			this.players.set(i, new Player(i));
		}
		//should deal 3 cards to each player here.
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
		while(true) {
			//draw till draw limit, check if draw is possible each time
			//move to another function
			for(int i = 0; i < this.drawLimit; ++i) {
				if(this.deck.size() == 0) {
					resetDiscardPile();
				}
				//draw the last card and remove it from the deck
				this.currentPlayer.draw(this.deck.remove(this.deck.size()-1));
			}
			for(int i = 0; i < this.playLimit; ++i) {
				//play -requires input. also check if user is out of cards
				int cardNumber;
				Card playedCard = this.currentPlayer.play(cardNumber);
				//Let the card do it's action
				playedCard.cardAction(this);
				//case Rule -limits updated.
				//case playrule -do nothing. the loop will handle it.
				//case drawrule -card action should trigger the remaining draws
				//case handlimitrule -everyone except current player discards in card action
				//for current user, handlimit is checked at the end of the turn
				//case keeperlimit rule -same as handlimit
				//case Keeper -card action add to players list
				//case Goal -replace the current goal
				//check if winner happens in card action
				//till play limit
			}
			//check handlimit 
			//check keeperlimit
		}
		
	}
	
	public boolean checkWinner() {
		for(Player p:this.players) {
			//check if goal id matches keeper id of the player
		}
		return false;
	}
	
	private void resetDiscardPile(){
		Collections.shuffle(this.discardPile);
		this.deck = this.discardPile;
		this.discardPile = null;
	}
	
	public void viewRules() {
		/**
		 * This function outputs the current rules of the game for the players' reference
		 */
		System.out.println("Current rules:");
		System.out.println("play limit: " + this.playLimit);
		System.out.println("draw limit: " + this.drawLimit);
		System.out.println("hand limit: " + this.handLimit);
		System.out.println("keeper limit: " + this.keeperLimit);
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
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
}
