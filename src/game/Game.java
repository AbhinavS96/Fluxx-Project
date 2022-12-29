package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import InputManager.InputManager;
import cards.Card;
import cards.DrawLimit;
import cards.Goal;
import cards.HandLimit;
import cards.Keeper;
import cards.KeeperLimit;
import cards.PlayLimit;
import player.Player;

/**
 * 
 * @author abhinav
 * This is the implementation of simplified fluxx
 */

public class Game {
	
	private Integer playLimit, handLimit, drawLimit, keeperLimit;
	private Goal currentGoal;
	private List<Card> deck;
	private List<Card> discardPile;
	private List<Player> players;
	private int currentPlayer;
	boolean isThereAWinner;
	
	/**
	 * 
	 * @param numberOfPlayers is the number of players in the game
	 * Constructor that sets up the game. 
	 * The limits are set as per the basic rules. null represents that the rule is not in play;
	 * The discard pile is initially empty. Current player is set to 0.
	 */	
	public Game(int numberOfPlayers) {	
		
		this.isThereAWinner = false;
		
		this.playLimit = 1;
		this.handLimit = null;
		this.drawLimit = 1;
		this.keeperLimit = null;
		this.currentGoal = null;
		
		this.deck = new ArrayList<Card>();
		initializeCards();
		this.discardPile = new ArrayList<Card>();
		
		initializePlayers(numberOfPlayers);
	}
	
	/**
	 * 
	 * Function that creates all the cards and adds them to the deck
	 */
	private void initializeCards(){
		//add cards to the deck.
		//goals
		//reference for Arrays.asList: https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/
		this.deck.add(new Goal("The Appliances", new ArrayList<String>(Arrays.asList("Television", "The Toaster"))));
//		this.deck.add(new Goal("The Bakery", new ArrayList<String>(Arrays.asList("Bread", "Cookies"))));
//		this.deck.add(new Goal("The eye of the Beholder", new ArrayList<String>(Arrays.asList("Love", "The Eye"))));
//		this.deck.add(new Goal("Bed Time", new ArrayList<String>(Arrays.asList("Sleep", "Time"))));
//		this.deck.add(new Goal("Can't Buy Me Love", new ArrayList<String>(Arrays.asList("Money", "Love"))));
//		this.deck.add(new Goal("Chocolate Cookies", new ArrayList<String>(Arrays.asList("Chocolate", "Cookies"))));
//		this.deck.add(new Goal("Chocolate Milk", new ArrayList<String>(Arrays.asList("Chocolate", "Milk"))));
//		this.deck.add(new Goal("Chocolate Extravaganza", new ArrayList<String>(Arrays.asList("Chocolate", "The Party"))));
		
		//keepers
		this.deck.add(new Keeper("Television"));
		this.deck.add(new Keeper("The Toaster"));
//		this.deck.add(new Keeper("Bread"));
//		this.deck.add(new Keeper("Cookies"));
//		this.deck.add(new Keeper("Love"));
//		this.deck.add(new Keeper("The Eye"));
//		this.deck.add(new Keeper("Sleep"));
//		this.deck.add(new Keeper("Time"));
//		this.deck.add(new Keeper("Money"));
//		this.deck.add(new Keeper("Love"));
//		this.deck.add(new Keeper("Chocolate"));
//		this.deck.add(new Keeper("Milk"));
//		this.deck.add(new Keeper("The Party"));
		
		//rules
		//draw limit
		this.deck.add(new DrawLimit(2));
		this.deck.add(new DrawLimit(3));
		//play limit
		this.deck.add(new PlayLimit(2));
		this.deck.add(new PlayLimit(3));
		//hand limit
		this.deck.add(new HandLimit(1));
		this.deck.add(new HandLimit(2));
		//keeper limit
		this.deck.add(new KeeperLimit(2));
		this.deck.add(new KeeperLimit(3));
		
		//Shuffle the deck
		Collections.shuffle(this.deck);
	}
	
	/**
	 * 
	 * @param numberOfPlayers number of players in the game
	 * New players are created. Each of them are dealt 3 cards from the deck
	 * The current player is initialized to 0
	 */
	private void initializePlayers(int numberOfPlayers) {
		this.players = new ArrayList<Player>(numberOfPlayers);
		for(int i = 0; i < numberOfPlayers; ++i) {
			this.players.add(new Player(i));
		}
		for(int i = 0; i < 3; ++i) {
			for(Player p:this.players) {
				p.addCard(this.deck.remove(this.deck.size()-1));
			}
		}		
		this.currentPlayer = 0;
	}
	
	public void setPlayLimit(int playLimit) {
		this.playLimit = playLimit;
		System.out.println("Play Limit updated to " + playLimit + "\n");
	}

	public void setHandLimit(int handLimit) {
		System.out.println("Hand Limit updated to " + handLimit + "\n");
		if(this.handLimit != null && handLimit < this.handLimit) {
			discardHand(handLimit);
		}
		this.handLimit = handLimit;
	}

	public void setDrawLimit(int drawLimit) {
		System.out.println("Draw Limit updated to " + drawLimit + "\n");
		//check if the draw limit is greater. If so, trigger sufficient draws for the current user.
		if(this.drawLimit < drawLimit) {
			drawCards(drawLimit - this.drawLimit);
		}
		this.drawLimit = drawLimit;
	}

	public void setKeeperLimit(int keeperLimit) {
		System.out.println("Keeper Limit updated to " + keeperLimit + "\n");
		if(this.keeperLimit != null && this.keeperLimit < keeperLimit) {
			discardKeepers(keeperLimit);
		}
		this.keeperLimit = keeperLimit;
	}
	
	public void setGoal(Goal goal) {
		this.currentGoal = goal;
		System.out.println("Goal updated to: ");
		System.out.println(goal);
		System.out.println();
	}
	
	/**
	 * 
	 * This the where the game loop runs
	 * Cards are drawn till the draw limit
	 * The user can choose which card to play
	 * The card will then perform it's own action
	 * All limits are verified at the end of the turn to make sure that everyone complies
	 */
	public void play() {	
		while(!isThereAWinner) {
			System.out.println("*****" + this.players.get(currentPlayer) + "'s turn starts*****\n");
			//draw till draw limit, check if draw is possible each time
			drawCards(this.drawLimit);
			
			//play till play limit
			for(int i = 0; i < this.playLimit; ++i) {
				System.out.println(this.players.get(currentPlayer) + "'s turn to play...");
				//check if user is out of cards. If so, turn ends
				if(this.players.get(currentPlayer).getHandSize() == 0) {
					System.out.println("No more cards to play. Turn has ended");
					break;
				}
				
				System.out.println("1. Play");
				System.out.println("2. View Keepers");
				System.out.println("3. View Goal");
				System.out.println("4. View Rules");
				System.out.println("5. Quit Game");
				
				int choice = InputManager.getIntergerInput("Please choose an option from the menu", 1, 5, "Please choose a valid option...");
				switch(choice) {
				case 1: break;
				case 2: 
					viewKeepersInPlay();
					i--;
					continue;
				case 3:
					viewGoals();
					i--;
					continue;
				case 4:
					viewRules();
					i--;
					continue;
				case 5:
					System.out.println("Quitting game...\n");
					return;
				}
				
				this.players.get(this.currentPlayer).viewhand();
				System.out.println((this.players.get(this.currentPlayer).getHandSize()+1) + ". Go back\n");
				int cardNumber = InputManager.getIntergerInput("Choose a card to play or go back to previous menu...", 1, this.players.get(currentPlayer).getHandSize(), "Please choose a valid card number...");			
				
				if(cardNumber == this.players.get(this.currentPlayer).getHandSize()+1) {
					i--;
					System.out.println();
					continue;
				}
				Card playedCard = this.players.get(currentPlayer).play(cardNumber-1);
				System.out.println("Played: ");
				System.out.println(playedCard);
				System.out.println();
				
				//Let the card do it's action
				playedCard.cardAction(this);
				//add the played card to the discard pile
				this.discardPile.add(playedCard);
				
				if(isThereAWinner) {
					return;
				}
			}
			
			//check hand limit ignore case null
			while(this.handLimit != null && this.players.get(currentPlayer).getHandSize() > this.handLimit) {
				System.out.println("You have more cards than the Hand Limit. Please discard " +  (this.players.get(currentPlayer).getHandSize() - this.handLimit) + " cards...\n");
				this.discardPile.add(this.players.get(currentPlayer).discardCard());
			}
			//check keeper limit ignore case null
			while(this.keeperLimit != null && this.players.get(currentPlayer).getKeeperSize() > this.keeperLimit) {
				System.out.println("You have more Keepers than the Keeper Limit. Please discard " +  (this.players.get(currentPlayer).getKeeperSize() - this.keeperLimit) + " keepers...\n");
				this.discardPile.add(this.players.get(currentPlayer).discardKeeper());
			}
			
			System.out.println("******" + this.players.get(currentPlayer) + "'s turn ends******\n");
			
			//at the end of the turn, the next player is decided
			if(this.currentPlayer == this.players.size()-1) {
				this.currentPlayer = 0;
			}
			else {
				this.currentPlayer++;
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
		System.out.println("Drawing " + drawLimit + " cards...");
		for(int i = 0; i < drawLimit; ++i) {
			//if the deck is empty, the discard pile is reshuffled and added back into the deck
			if(this.deck.size() == 0) {
				resetDiscardPile();
			}
			//draw the last card and remove it from the deck
			this.players.get(currentPlayer).draw(this.deck.remove(this.deck.size()-1));
		}
	}
	
	/**
	 * 
	 * @param handLimit is the updated hand limit when a rule is changed
	 * All players except the current have to discard cards
	 */
	private void discardHand(int handLimit) {
		System.out.println("\nHand Limit has changed. All players have to comply...\n");
		for(Player p:this.players) {
			if(p == this.players.get(currentPlayer)) {
				continue;
			}
			if(p.getHandSize() > handLimit) {
				System.out.println(p + "'s turn to discard cards from hand...");
			}
			for(int i = 0; i < p.getHandSize() - handLimit; ++i) {
				System.out.println("You have more cards than the Hand Limit. Please discard " +  (p.getHandSize() - this.handLimit) + " cards...\n");
				this.discardPile.add(p.discardCard());
			}
		}
		System.out.println("New Hand Limit applies to all players\n");
	}
	
	/**
	 * 
	 * @param keeperLimit is the updated keeper limit when the rule is changed
	 * All players except the current have to discard keepers immediately
	 */
	private void discardKeepers(int keeperLimit) {
		System.out.println("\nKeeper Limit has changed. All players have to comply...\n");
		for(Player p:this.players) {
			if(p == this.players.get(currentPlayer)) {
				continue;
			}
			if(p.getKeeperSize() > keeperLimit) {
				System.out.println(p + "'s turn to discard keepers...");
			}
			for(int i = 0; i < p.getKeeperSize() - handLimit; ++i) {
				System.out.println("You have more cards than the Keeper Limit. Please discard " +  (p.getKeeperSize() - this.keeperLimit) + " Keepers...\n");
				this.discardPile.add(p.discardKeeper());
			}
		}
		System.out.println("New Keeper Limit applies to all players\n");
	}
	
	private void viewKeepersInPlay() {
		System.out.println("Keepers in play:");
		for(Player p:this.players) {
			System.out.println(p + ":");
			p.viewKeepers();
		}
	}
	
	public void checkWinner() {
		for(Player p:this.players) {
			//check if goal id matches keeper id of the player
			if(this.currentGoal != null && this.currentGoal.compareTo(p.getKeepers()) == 0) {
				this.isThereAWinner = true;
				System.out.println(p + " has won the game!\n");
				return;
			}
		}
	}
	
	private void resetDiscardPile(){
		Collections.shuffle(this.discardPile);
		this.deck = this.discardPile;
		this.discardPile = null;
	}
	
	/**
	 * 
	 * This function outputs the current rules of the game for the players' reference
	 */
	public void viewRules() {
		System.out.println("Current rules:");
		System.out.println("Play Limit: " + this.playLimit);
		System.out.println("Draw Limit: " + this.drawLimit);
		System.out.println("Hand Limit: " + (this.handLimit == null ? "No limit set" : this.handLimit));
		System.out.println("Keeper Limit: " + (this.keeperLimit == null ? "No limit set" : this.keeperLimit));
		System.out.println();
	}
	
	/**
	 * 
	 * This function outputs the current goal of the game
	 */
	public void viewGoals() {
		if(this.currentGoal == null) {
			System.out.println("No Goal set\n");
			return;
		}
		
		System.out.println("Current Goal: ");
		System.out.println(this.currentGoal);
		System.out.println();
	}
	
	public Player getCurrentPlayer() {
		return this.players.get(currentPlayer);
	}
}
