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
	String name;
	int id;
	List<Card> hand;
	List<Card> keepers;
	
	public Player(int playerCount) {
		this.name = "Player " + playerCount;
		this.id = playerCount;
		this.hand = new ArrayList<>();
		this.keepers = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public Card play() {
		/**
		 * This is where the play action is made by the player
		 */
	}

	public void draw(Card card) {
		/**
		 * This is where the draw action is completed. 
		 */
	}
	
	public void addKeeper(Card card) {
		this.keepers.add(card);
	}
	
	public Card discardKeeper() {
		/**
		 * user should be able to view and delete one or more keepers
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a keeper to remove");
		for(int i = 0; i < this.keepers.size(); ++i) {
			System.out.println(i + this.keepers.get(i).getCardName());
		}
		//need to validate input here
		int input = sc.nextInt();
		//maybe also display the 
		this.keepers.remove(input);
	}
	
	Card discardCard() {
		/**
		 * user should be able to view and delete one or more cards on hand
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a card to remove");
		for(int i = 0; i < this.hand.size(); ++i) {
			System.out.println(i + this.keepers.get(i).getCardName());
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
			System.out.println(i.getCardName());
		}
	}
	
	public void viewKeepers() {
		if(this.hand.size() == 0) {
			System.out.println("No keepers in play");
			return;
		}
		
		System.out.println("Card in hand are:");
		for(Card i:this.keepers) {
			System.out.println(i.getCardName());
		}
	}

}
