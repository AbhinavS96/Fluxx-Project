package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cards.Card;
import cards.DrawLimit;
import cards.Goal;
import cards.HandLimit;
import cards.Keeper;
import cards.KeeperLimit;
import cards.PlayLimit;
import player.Player;

/**
 * @author Abhinav
 *
 */

public class Game {
	
	private int playLimit, handLimit, drawLimit, keeperLimit;
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
	 * The limits are set as per the basic rules. -1 represents that the rule is not in play;
	 * The discard pile is initially empty. Current player is set to 0.
	 */	
	public Game(int numberOfPlayers) {	
		
		this.isThereAWinner = false;
		
		this.playLimit = 1;
		this.handLimit = -1;
		this.drawLimit = 1;
		this.keeperLimit = -1;
		this.currentGoal = null;
		
		this.deck = new ArrayList<Card>();
		initializeCards();
		Collections.shuffle(this.deck);
		this.discardPile = new ArrayList<Card>();
		
		initializePlayers(numberOfPlayers);
	}
	
	/**
	 * Function that creates all the cards and adds them to the deck
	 */
	private void initializeCards(){
		//add cards to the deck.
		//goals
		//reference for Arrays.asList: https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/
		this.deck.add(new Goal("The Appliances", new ArrayList<String>(Arrays.asList("Television", "The Toaster"))));
		this.deck.add(new Goal("The Bakery", new ArrayList<String>(Arrays.asList("Bread", "Cookies"))));
		this.deck.add(new Goal("The eye of the Beholder", new ArrayList<String>(Arrays.asList("Love", "The Eye"))));
		this.deck.add(new Goal("Bed Time", new ArrayList<String>(Arrays.asList("Sleep", "Time"))));
		this.deck.add(new Goal("Can't Buy Me Love", new ArrayList<String>(Arrays.asList("Money", "Love"))));
		this.deck.add(new Goal("Chocolate Cookies", new ArrayList<String>(Arrays.asList("Chocolate", "Cookies"))));
		this.deck.add(new Goal("Chocolate Milk", new ArrayList<String>(Arrays.asList("Chocolate", "Milk"))));
		this.deck.add(new Goal("Chocolate Extravaganza", new ArrayList<String>(Arrays.asList("Chocolate", "The Party"))));
		
		//keepers
		this.deck.add(new Keeper("Television"));
		this.deck.add(new Keeper("The Toaster"));
		this.deck.add(new Keeper("Bread"));
		this.deck.add(new Keeper("Cookies"));
		this.deck.add(new Keeper("Love"));
		this.deck.add(new Keeper("The Eye"));
		this.deck.add(new Keeper("Sleep"));
		this.deck.add(new Keeper("Time"));
		this.deck.add(new Keeper("Money"));
		this.deck.add(new Keeper("Love"));
		this.deck.add(new Keeper("Chocolate"));
		this.deck.add(new Keeper("Milk"));
		this.deck.add(new Keeper("The Party"));
		
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

	public void setKeeperLimit(int keeperLimit) {
		this.keeperLimit = keeperLimit;
	}
	
	public void setGoal(Goal goal) {
		this.currentGoal = goal;
	}
	
	/**
	 * 
	 * This the where the game loop runs
	 * Cards are drawn till the draw limit
	 * The user can choose which card to play
	 * The card will then perform it's own action
	 * All limits are verified at the end of the turn to make sure that everyone complies
	 * 
	 */
	public void play() {	
		while(!isThereAWinner) {
			//draw till draw limit, check if draw is possible each time
			System.out.println(this.players.get(currentPlayer) + " draws");
			drawCards(this.drawLimit);
			
			System.out.println(this.players.get(currentPlayer) + " plays");
			//play till play limit
			for(int i = 0; i < this.playLimit; ++i) {
				//check if user is out of cards. If so, turn ends
				if(this.players.get(currentPlayer).getHandSize() == 0) {
					System.out.println("No more cards to play. Turn has ended");
					break;
				}
				
				this.players.get(this.currentPlayer).viewhand();
				
				Scanner sc = new Scanner(System.in);
				int cardNumber = 0;
				while(true) {
					try {
						System.out.println("choose a card to play");
						cardNumber = sc.nextInt();
						if(cardNumber < 1 || cardNumber > this.players.get(currentPlayer).getHandSize()) {
							System.out.println("Please choose a valid card number...");
							continue;
						}
						break;
					}
//					InputMismatchException reference: https://stackoverflow.com/questions/38830142/how-to-handle-invalid-input-when-using-scanner-nextint
					catch (InputMismatchException e) {
						System.out.println("Invalid input... Please try again.");
					}
					catch (Exception e) {
						System.out.println("Something went wrong... Please try again.");
						System.out.println(e);
					}
				}				
				Card playedCard = this.players.get(currentPlayer).play(cardNumber-1);
				//Let the card do it's action
				playedCard.cardAction(this);
				//add the played card to the discard pile
				this.discardPile.add(playedCard);
				if(isThereAWinner) {
					sc.close();
					return;
				}
			}
			//check hand limit ignore case -1
			while(this.handLimit != -1 && this.players.get(currentPlayer).getHandSize() > this.handLimit) {
				System.out.println("aaa");
				this.discardPile.add(this.players.get(currentPlayer).discardCard());
			}
			//check keeper limit ignore case -1
			while(this.keeperLimit != -1 && this.players.get(currentPlayer).getKeeperSize() > this.keeperLimit) {
				this.discardPile.add(this.players.get(currentPlayer).discardKeeper());
			}
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
		System.out.println("drawing " + drawLimit + " cards");
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
		for(Player p:this.players) {
			if(p == this.players.get(currentPlayer)) {
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
			if(this.currentGoal.compareTo(p.getKeepers()) == 0) {
				this.isThereAWinner = true;
				System.out.println(p + " has won the game!");
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
	 * 
	 */
	public void viewRules() {
		System.out.println("Current rules:");
		System.out.println("play limit: " + this.playLimit);
		System.out.println("draw limit: " + this.drawLimit);
		System.out.println("hand limit: " + this.handLimit);
		System.out.println("keeper limit: " + this.keeperLimit);
	}
	
	/**
	 * 
	 * This function outputs the current goal of the game
	 * 
	 */
	public void viewGoals() {
		if(this.currentGoal == null) {
			System.out.println("No goal set");
			return;
		}
		
		System.out.println(this.currentGoal);
	}
	
	public Player getCurrentPlayer() {
		return this.players.get(currentPlayer);
	}
}
