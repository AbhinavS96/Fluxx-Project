package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import cards.Action;
import cards.DrawLimit;
import cards.Goal;
import cards.HandLimit;
import cards.Keeper;
import cards.KeeperLimit;
import cards.PlayLimit;

/**
 * 
 * @author abhinav
 * This is the implementation of Standard Fluxx
 * It has more cards implemented and a few actions as well.
 *
 */

public class StandardFluxx extends Game{

	public StandardFluxx(int numberOfPlayers) {
		super(numberOfPlayers);
	}

	/**
	 * Standard Fluxx implements more cards than Simplified fluxx, but not all of them
	 * Some Actions are implemented to showcase their working
	 */
	@Override
	protected void initializeCards() {
		//Add cards to the deck.
		//Goal cards
		//reference for Arrays.asList: https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/
		this.deck.add(new Goal("The Appliances", new ArrayList<String>(Arrays.asList("Television", "The Toaster"))));
		this.deck.add(new Goal("The Bakery", new ArrayList<String>(Arrays.asList("Bread", "Cookies"))));
		this.deck.add(new Goal("The eye of the Beholder", new ArrayList<String>(Arrays.asList("Love", "The Eye"))));
		this.deck.add(new Goal("Bed Time", new ArrayList<String>(Arrays.asList("Sleep", "Time"))));
		this.deck.add(new Goal("Can't Buy Me Love", new ArrayList<String>(Arrays.asList("Money", "Love"))));
		this.deck.add(new Goal("Chocolate Cookies", new ArrayList<String>(Arrays.asList("Chocolate", "Cookies"))));
		this.deck.add(new Goal("Chocolate Milk", new ArrayList<String>(Arrays.asList("Chocolate", "Milk"))));
		this.deck.add(new Goal("Chocolate Extravaganza", new ArrayList<String>(Arrays.asList("Chocolate", "The Party"))));
		this.deck.add(new Goal("Cookies and Cream", new ArrayList<String>(Arrays.asList("Cookies", "Ice Cream"))));
		this.deck.add(new Goal("Day Dreams", new ArrayList<String>(Arrays.asList("The Sun", "Dreams"))));
		this.deck.add(new Goal("Dreamland", new ArrayList<String>(Arrays.asList("Sleep", "Dreams"))));
		this.deck.add(new Goal("Great Theme Song", new ArrayList<String>(Arrays.asList("Television", "Music"))));
		this.deck.add(new Goal("Hearts and Minds", new ArrayList<String>(Arrays.asList("Love", "The Brain"))));
		this.deck.add(new Goal("Hot Fudge Sundae", new ArrayList<String>(Arrays.asList("Chocolate", "Ice Cream"))));
		this.deck.add(new Goal("Lullaby", new ArrayList<String>(Arrays.asList("Sleep", "Music"))));
		this.deck.add(new Goal("Milk and Cookies", new ArrayList<String>(Arrays.asList("Milk", "Cookies"))));
		this.deck.add(new Goal("Milk Shake", new ArrayList<String>(Arrays.asList("Milk", "Ice Cream"))));
		this.deck.add(new Goal("The Mind's Eye", new ArrayList<String>(Arrays.asList("The Brain", "The Eye"))));
		this.deck.add(new Goal("Night and Day", new ArrayList<String>(Arrays.asList("The Moon", "The Sun"))));
		this.deck.add(new Goal("Party Time", new ArrayList<String>(Arrays.asList("The Party", "Time"))));
		this.deck.add(new Goal("Pizza Party", new ArrayList<String>(Arrays.asList("Pizza", "The Party"))));
		this.deck.add(new Goal("Rocket Science", new ArrayList<String>(Arrays.asList("The Rocket", "The Brain"))));
		this.deck.add(new Goal("Rocket to the Moon", new ArrayList<String>(Arrays.asList("The Rocket", "The Moon"))));
		this.deck.add(new Goal("Squishy Chocolate", new ArrayList<String>(Arrays.asList("The Sun", "Chocolate"))));
		this.deck.add(new Goal("Thick-Crust Pizza", new ArrayList<String>(Arrays.asList("Bread", "Pizza"))));
		this.deck.add(new Goal("Time is Money", new ArrayList<String>(Arrays.asList("Money", "Time"))));
		this.deck.add(new Goal("Toast", new ArrayList<String>(Arrays.asList("Bread", "The Toaster"))));
		this.deck.add(new Goal("Turn it Up", new ArrayList<String>(Arrays.asList("The Party", "Music"))));
		this.deck.add(new Goal("Winning the Lotterry", new ArrayList<String>(Arrays.asList("Dreams", "Money"))));
		
		//Keeper cards
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
		this.deck.add(new Keeper("Ice Cream"));
		this.deck.add(new Keeper("The Sun"));
		this.deck.add(new Keeper("Dreams"));
		this.deck.add(new Keeper("Music"));
		this.deck.add(new Keeper("The Brain"));
		this.deck.add(new Keeper("The Moon"));
		this.deck.add(new Keeper("Pizza"));
		this.deck.add(new Keeper("The Rocket"));
		this.deck.add(new Keeper("Money"));
		
		//Rule cards
		//Draw Limit
		this.deck.add(new DrawLimit(2));
		this.deck.add(new DrawLimit(3));
		this.deck.add(new DrawLimit(4));
		this.deck.add(new DrawLimit(5));
		//Play Limit
		this.deck.add(new PlayLimit(2));
		this.deck.add(new PlayLimit(3));
		this.deck.add(new PlayLimit(4));
		//Hand Limit
		this.deck.add(new HandLimit(1));
		this.deck.add(new HandLimit(2));
		this.deck.add(new HandLimit(3));
		this.deck.add(new HandLimit(4));
		//Keeper Limit
		this.deck.add(new KeeperLimit(2));
		this.deck.add(new KeeperLimit(3));
		this.deck.add(new KeeperLimit(3));
		
		//Actions
		this.deck.add(new Action("Jackpot!", "Draw 3 extra cards", (game) -> {
			game.drawCards(3);
		}));
		this.deck.add(new Action("Empty the Trash", "Start a new discard pile with this card,\n"
				+ "and the rest of the discard pile is shuffled back into the draw pile", (game) -> {
			game.resetDiscardPile();
		}));
		this.deck.add(new Action("Rules Reset", "Reset to basic rules.\n"
				+ "Discard all New Rule cards and leave only the Basic Rules in play.\n"
				+ "The current goal is not discarded.", (game) -> {
			game.resetRules();
		}));
		
		//Shuffle the deck
		Collections.shuffle(this.deck);
	}
}
