package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import InputManager.InputManager;
import cards.Card;
import cards.DrawLimit;
import cards.Goal;
import cards.HandLimit;
import cards.KeeperLimit;
import cards.PlayLimit;
import player.Player;

/**
 * 
 * @author abhinav
 * This is the implementation of main game logic
 * Game type specific logic is implemented in child classes
 * 
 */

public abstract class Game {
	
	/**
	 * The four Limits and goal indicate the current state of the game. These are modified by cards as the game progresses
	 * The cards are initially added to the list deck and they are moved to the list discardPile as the game progresses
	 * List players hold all the active players and current player is the index of the player who's turn it is
	 * isThereAWinner is another state variable used to indicate the end of the game
	 */
	protected Integer playLimit;
	protected Integer handLimit;
	protected Integer drawLimit;
	protected Integer keeperLimit;
	private Goal currentGoal;
	protected List<Card> deck;
	private List<Card> discardPile;
	private List<Player> players;
	private int currentPlayer;
	private boolean isThereAWinner;
	
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
	 * It is implemented in the child class
	 */
	protected abstract void initializeCards();
	
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
	
	/**
	 * 
	 * @param playLimit is the updated Play Limit
	 * If the current rule is not a basic rule, then a new card is created and added to the discard pile
	 * This is done because the rule card object is lost after assigning it's value to the game's state
	 * This can later be refactored out when the game supports more types of rules
	 */
	public void setPlayLimit(int playLimit) {
		if(this.playLimit != 1) {
			addToDiscardPile(new PlayLimit(this.playLimit));
		}
		this.playLimit = playLimit;
		System.out.println("Play Limit updated to " + playLimit + "\n");
	}

	/**
	 * 
	 * @param handLimit is the updated Hand Limit
	 * If there is a need to discard cards, the discardHand function is called
	 * If the current rule is not a basic rule, then a new card is created and added to the discard pile
	 * This is done because the rule card object is lost after assigning it's value to the game's state
	 * This can later be refactored out when the game supports more types of rules
	 */
	public void setHandLimit(int handLimit) {
		if(this.handLimit != null) {
			addToDiscardPile(new HandLimit(this.handLimit));
		}
		System.out.println("Hand Limit updated to " + handLimit + "\n");
		if(this.handLimit == null || handLimit < this.handLimit) {
			discardHand(handLimit);
		}
		this.handLimit = handLimit;
	}
	
	/**
	 * 
	 * @param drawLimit is the updated Draw Limit
	 * If the current rule is not a basic rule, then a new card is created and added to the discard pile
	 * This is done because the rule card object is lost after assigning it's value to the game's state
	 * This can later be refactored out when the game supports more types of rules
	 */
	public void setDrawLimit(int drawLimit) {
		if(this.drawLimit != 1) {
			addToDiscardPile(new DrawLimit(this.drawLimit));
		}
		System.out.println("Draw Limit updated to " + drawLimit + "\n");
		//check if the draw limit is greater. If so, trigger sufficient draws for the current user.
		if(this.drawLimit < drawLimit) {
			drawCards(drawLimit - this.drawLimit);
		}
		this.drawLimit = drawLimit;
	}

	/**
	 * 
	 * @param keeperLimit is the updated Keeper Limit
	 * If there is a need to discard Keepers, the discardKeepers function is called
	 * If the current rule is not a basic rule, then a new card is created and added to the discard pile
	 * This is done because the rule card object is lost after assigning it's value to the game's state
	 * This can later be refactored out when the game supports more types of rules
	 */
	public void setKeeperLimit(int keeperLimit) {
		if(this.keeperLimit != null) {
			addToDiscardPile(new KeeperLimit(this.keeperLimit));
		}
		System.out.println("Keeper Limit updated to " + keeperLimit + "\n");
		if(this.keeperLimit == null || this.keeperLimit < keeperLimit) {
			discardKeepers(keeperLimit);
		}
		this.keeperLimit = keeperLimit;
	}
	
	public void setGoal(Goal goal) {
		addToDiscardPile(this.currentGoal);
		this.currentGoal = goal;
		System.out.println("Goal updated to: ");
		System.out.println(goal);
		System.out.println();
	}
	
	protected void resetRules() {
		this.playLimit = 1;
		this.handLimit = null;
		this.drawLimit = 1;
		this.keeperLimit = null;
	}
	
	/**
	 * 
	 * This the where the game loop runs
	 * Cards are drawn till the Draw Limit
	 * The user can choose which card to play
	 * The card will then perform it's own action
	 * All limits are verified at the end of the turn to make sure that all players complies with them
	 */
	public void play() {	
		while(!isThereAWinner) {
			System.out.println("*****" + this.players.get(currentPlayer) + "'s turn starts*****\n");
			//draw till Draw Limit, check if draw is possible each time
			drawCards(this.drawLimit);
			
			//play till Play Limit
			for(int i = 0; i < this.playLimit; ++i) {
				System.out.println(this.players.get(currentPlayer) + "'s turn to play...");
				//check if user is out of cards. If so, turn ends
				if(this.players.get(currentPlayer).getHandSize() == 0) {
					System.out.println("No more cards to play. Turn has ended");
					break;
				}
				
				//print menu
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
				
				//print cards and also the option to step back
				this.players.get(this.currentPlayer).viewhand();
				System.out.println((this.players.get(this.currentPlayer).getHandSize()+1) + ". Go back\n");
				int cardNumber = InputManager.getIntergerInput("Choose a card to play or go back to previous menu...", 1, this.players.get(currentPlayer).getHandSize()+1, "Please choose a valid card number...");			
				
				if(cardNumber == this.players.get(this.currentPlayer).getHandSize()+1) {
					i--;
					System.out.println();
					continue;
				}
				Card playedCard = this.players.get(currentPlayer).play(cardNumber-1);
				System.out.println("Played: ");
				System.out.println(playedCard);
				System.out.println();
				
				//Let the card do it's action. Adding to discard pile is handled there
				playedCard.cardAction(this);
				
				if(isThereAWinner) {
					return;
				}
			}
			
			//check Hand Limit, ignore case null
			while(this.handLimit != null && this.players.get(currentPlayer).getHandSize() > this.handLimit) {
				System.out.println(this.players.get(currentPlayer) + ", You have more cards than the Hand Limit. Please discard " +  (this.players.get(currentPlayer).getHandSize() - this.handLimit) + " cards...\n");
				this.discardPile.add(this.players.get(currentPlayer).discardCard());
			}
			//check Keeper Limit, ignore case null
			while(this.keeperLimit != null && this.players.get(currentPlayer).getKeeperSize() > this.keeperLimit) {
				System.out.println(this.players.get(currentPlayer) + ", You have more Keepers than the Keeper Limit. Please discard " +  (this.players.get(currentPlayer).getKeeperSize() - this.keeperLimit) + " keepers...\n");
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
	protected void drawCards(int drawLimit) {
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
	 * @param handLimit is the updated Hand Limit when a rule is changed
	 * All players except the current have to discard cards if they have too many on their hand
	 */
	private void discardHand(int handLimit) {
		System.out.println("\nHand Limit has changed. All players except current have to comply...\n");
		for(Player p:this.players) {
			if(p == this.players.get(currentPlayer)) {
				continue;
			}
			if(p.getHandSize() > handLimit) {
				System.out.println(p + "'s turn to discard cards from hand...");
			}
			for(int i = 0; i < p.getHandSize() - handLimit; ++i) {
				System.out.println("You have more cards than the Hand Limit. Please discard " +  (p.getHandSize() - handLimit) + " cards...\n");
				this.discardPile.add(p.discardCard());
			}
		}
		System.out.println("New Hand Limit applies to all players except current\n");
	}
	
	/**
	 * 
	 * @param keeperLimit is the updated Keeper Limit when the rule is changed
	 * All players except the current have to discard keepers immediately if they have too many Keepers in play
	 */
	private void discardKeepers(int keeperLimit) {
		System.out.println("\nKeeper Limit has changed. All players except current have to comply...\n");
		for(Player p:this.players) {
			if(p == this.players.get(currentPlayer)) {
				continue;
			}
			if(p.getKeeperSize() > keeperLimit) {
				System.out.println(p + "'s turn to discard keepers...");
			}
			for(int i = 0; i < p.getKeeperSize() - keeperLimit; ++i) {
				System.out.println("You have more cards than the Keeper Limit. Please discard " +  (p.getKeeperSize() - keeperLimit) + " Keepers...\n");
				this.discardPile.add(p.discardKeeper());
			}
		}
		System.out.println("New Keeper Limit applies to all players except the current\n");
	}
	
	public void addToDiscardPile(Card card) {
		this.discardPile.add(card);
	}
	
	private void viewKeepersInPlay() {
		System.out.println("Keepers in play:");
		for(Player p:this.players) {
			System.out.println(p + ":");
			p.viewKeepers();
		}
	}
	
	/**
	 * Checks if any player has won by comparing keepers against goal
	 * Multi goal support can be added here as an extension 
	 */
	public void checkWinner() {
		for(Player p:this.players) {
			//check if goal requirements matches keeper name of the player
			if(this.currentGoal != null && this.currentGoal.compareTo(p.getKeepers()) == 0) {
				this.isThereAWinner = true;
				System.out.println(p + " has won the game!\n");
				return;
			}
		}
	}
	
	/**
	 * This function will get called in two cases.
	 * 1. When the deck is empty
	 * 2. WHen the "Empty the Trash" Action is played where the discard pile is added to the deck and shuffled
	 * Both cases are handled by this.deck.addAll()
	 */
	protected void resetDiscardPile(){
		this.deck.addAll(this.discardPile);
		Collections.shuffle(this.deck);
		this.discardPile = new ArrayList<>();
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
		viewGoals();
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
