package player;

import java.util.ArrayList;
import java.util.List;

import InputManager.InputManager;
import cards.Card;
/**
 *
 * @author abhinav
 * This class manages all the actions of a player
 *
 */

public class Player {
	/**
	 * name is the user name assigned to a player
	 * hand is the list of cards that the user has in each turn. It is updated as the game goes by
	 * keepers is the list of keepers that the user has in play  
	 */
	private final String name;
	private List<Card> hand;
	private List<Card> keepers;
	
	/**
	 * 
	 * @param playerCount is the index of the player in the game
	 * index+1 is appended to construct the player name
	 */
	public Player(int playerCount) {
		this.name = "Player " + (playerCount+1);
		this.hand = new ArrayList<>();
		this.keepers = new ArrayList<>();
	}
	
	public int getHandSize() {
		return this.hand.size();
	}
	
	public int getKeeperSize() {
		return this.keepers.size();
	}

	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param cardNumber is the index of the card to be removed
	 * This is where the play action is made by the player
	 * @return the card that was played by the user
	 */
	public Card play(int cardNumber) {
		return this.hand.remove(cardNumber);
	}
	
	/**
	 * 
	 * @param card the card to be added to the users hand
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * 
	 * @param card is the card that the user got from the top of the deck 
	 * This is where the draw action is completed. 
	 */
	public void draw(Card card) {
		System.out.println("Card drawn: ");
		System.out.println(card);
		System.out.println();
		this.hand.add(card);
	}
	
	public void addKeeper(Card card) {
		this.keepers.add(card);
		System.out.println("Keeper added: ");
		System.out.println(card);
		System.out.println();
	}
	
	public List<Card> getKeepers(){
		return this.keepers;
	}
	
	/**
	 * 
	 * user can view and delete a keeper
	 * @return card that is removed. It gets added to the discard pile
	 */
	public Card discardKeeper() {
		viewKeepers();
		int cardNumber = InputManager.getIntergerInput("choose a keeper to discard", 1, this.keepers.size(), "Please choose a valid card number...");
		System.out.println("Discarding " + this.hand.get(cardNumber-1));
		return this.keepers.remove(cardNumber);
	}
	
	
	/**
	 * 
	 * user can view and delete a card on hand
	 * @return card that is removed. It gets added to the discard pile
	 */
	public Card discardCard() {
		viewhand();
		int cardNumber = InputManager.getIntergerInput("Choose a card to discard", 1, this.hand.size(), "Please choose a valid card number...");
		System.out.println("Discarding " + this.hand.get(cardNumber-1));
		return this.hand.remove(cardNumber-1);		
	}
	
	public void viewhand() {
		if(this.hand.size() == 0) {
			System.out.println("No cards on hand\n");
			return;
		}
		
		System.out.println("Cards in hand are:");
		for(int i = 0; i < this.hand.size(); ++i) {
			System.out.println((i+1) + ". " + this.hand.get(i));
			System.out.println();
		}
	}
	
	public void viewKeepers() {
		if(this.keepers.size() == 0) {
			System.out.println("No keepers in play\n");
			return;
		}
		
		System.out.println("Keepers in hand are:");
		for(int i = 0; i < this.keepers.size(); ++i) {
			System.out.println((i+1) + ". " + this.keepers.get(i) + "\n");
		}
		System.out.println();
	}
	
	public String toString() {
		return this.name;
	}
}
