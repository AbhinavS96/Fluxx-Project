package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import cards.DrawLimit;
import cards.Goal;
import cards.HandLimit;
import cards.Keeper;
import cards.KeeperLimit;
import cards.PlayLimit;

/**
 * 
 * @author abhinav
 * This is the implementation of the Simplified Fluxx code
 *
 */
public class SimplifiedFluxx extends Game {

	public SimplifiedFluxx(int numberOfPlayers) {
		super(numberOfPlayers);
	}
	
	/**
	 * This is a basic collection of cards to show the game in action
	 * Standard fluxx has a larger deck of cards
	 */
	@Override
	protected void initializeCards(){
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
		
		//Rule cards
		//Draw Limit
		this.deck.add(new DrawLimit(2));
		this.deck.add(new DrawLimit(3));
		//Play Limit
		this.deck.add(new PlayLimit(2));
		this.deck.add(new PlayLimit(3));
		//Hand Limit
		this.deck.add(new HandLimit(1));
		this.deck.add(new HandLimit(2));
		//Keeper Limit
		this.deck.add(new KeeperLimit(2));
		this.deck.add(new KeeperLimit(3));
		
		//Shuffle the deck
		Collections.shuffle(this.deck);
	}
}
