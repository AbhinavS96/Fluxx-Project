package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
	boolean isThereAWinner;
	
	public Game(int numberOfPlayers) {
		/**
		 * Constructor that sets up the game. 
		 * The limits are set as per the basic rules. -1 represents that the rule is not in play;
		 * The discard pile is empty. Current player is not set.
		 */		
		
		this.isThereAWinner = false;
		
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
		if(handLimit < this.handLimit) {
			discardHand(handLimit);
		}
		this.handLimit = handLimit;
	}

	public void setDrawLimit(int drawLimit) {
		//check if the draw limit is greater. If so, trigger sufficient draws for the current user.
		if(this.drawLimit < drawLimit) {
			drawCards(drawLimit - this.drawLimit);
		}
		this.drawLimit = drawLimit;
	}
	
	public int getDrawLimit() {
		return this.drawLimit;
	}

	public void setKeeperLimit(int keeperLimit) {
		this.keeperLimit = keeperLimit;
	}
	
	public void setGoal(Goal goal) {
		this.currentGoal = goal;
	}
	
	/**
	 * This the where the game loop runs
	 */
	public void play() {	
		while(!isThereAWinner) {
			//draw till draw limit, check if draw is possible each time
			drawCards(this.drawLimit);
			for(int i = 0; i < this.playLimit; ++i) {
				//play -requires input. also check if user is out of cards
				Scanner sc = new Scanner(System.in);
				System.out.println("choose a card to play");
				int cardNumber = sc.nextInt();
				Card playedCard = this.currentPlayer.play(cardNumber);
				//Let the card do it's action
				playedCard.cardAction(this);
				this.discardPile.add(playedCard);
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
				if(isThereAWinner) {
					break;
				}
			}
			//check handlimit 
			while(this.currentPlayer.getHandSize() > this.handLimit) {
				this.discardPile.add(this.currentPlayer.discardCard());
			}
			//check keeperlimit
			while(this.currentPlayer.getKeeperSize() > this.keeperLimit) {
				this.discardPile.add(this.currentPlayer.discardKeeper());
			}
		}
		
	}
	
	/**
	 * 
	 * @param drawLimit the remaining draws for this turn
	 * This function is called in two cases
	 * 1. During a regular draw action
	 * 2. During a rule change where the parameter will have the value of the remaining draws
	 */
	private void drawCards(int drawLimit) {
		for(int i = 0; i < drawLimit; ++i) {
			if(this.deck.size() == 0) {
				resetDiscardPile();
			}
			//draw the last card and remove it from the deck
			this.currentPlayer.draw(this.deck.remove(this.deck.size()-1));
		}
	}
	
	/**
	 * 
	 * @param handLimit is the updated hand limit when a rule is changed
	 * All players except the current have to discard cards
	 */
	private void discardHand(int handLimit) {
		for(Player p:this.players) {
			if(p == this.currentPlayer) {
				continue;
			}
			for(int i = 0; i < p.getHandSize() - handLimit; ++i) {
				this.discardPile.add(p.discardCard());
			}
		}
		
	}
	
	public void checkWinner() {
		for(Player p:this.players) {
			//check if goal id matches keeper id of the player
			if(false) {
				this.isThereAWinner = true;
			}
		}
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
