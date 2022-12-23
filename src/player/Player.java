package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cards.Card;
/**
 *
 * @author abhinav
 *
 */

public class Player {
	private String name;
	private int id;
	private List<Card> hand;
	private List<Card> keepers;
	
	public Player(int playerCount) {
		this.name = "Player " + playerCount;
		this.id = playerCount;
		this.hand = new ArrayList<>();
		this.keepers = new ArrayList<>();
	}

	public int getId() {
		return this.id;
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
	
	public Card play(int cardNumber) {
		/**
		 * This is where the play action is made by the player
		 */
		return this.hand.remove(cardNumber);
	}

	public void draw(Card card) {
		/**
		 * This is where the draw action is completed. 
		 */
		//print which card is picked by the user
		System.out.println("Card drawn: ");
		System.out.println(card);
		//add the cards to his hand
		this.hand.add(card);
	}
	
	public void addKeeper(Card card) {
		this.keepers.add(card);
	}
	
	public List<Card> getKeepers() {
		return this.keepers;
	}
	
	public Card discardKeeper() {
		/**
		 * user should be able to view and delete one or more keepers
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a keeper to remove");
		for(int i = 0; i < this.keepers.size(); ++i) {
			System.out.println(i + this.keepers.get(i).toString());
		}
		//need to validate input here
		int input = sc.nextInt();
		//maybe also display the 
		return this.keepers.remove(input);
	}
	
	public Card discardCard() {
		/**
		 * user should be able to view and delete one or more cards on hand
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a card to remove");
		for(int i = 0; i < this.hand.size(); ++i) {
			System.out.println(i + this.keepers.get(i).toString());
		}
		//need to validate input here
		int input = sc.nextInt();
		//maybe also display the 
		return this.keepers.remove(input);		
	}
	
	public void viewhand() {
		if(this.hand.size() == 0) {
			System.out.println("No cards on hand");
			return;
		}
		
		System.out.println("Card in hand are:");
		for(Card i:this.hand) {
			System.out.println(i);
		}
	}
	
	public void viewKeepers() {
		if(this.hand.size() == 0) {
			System.out.println("No keepers in play");
			return;
		}
		
		System.out.println("Card in hand are:");
		for(Card i:this.keepers) {
			System.out.println(i);
		}
	}

}
