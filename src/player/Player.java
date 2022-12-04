package player;

import java.util.ArrayList;
import java.util.List;

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
	
	public Player(String name, int id){
		this.name = name;
		this.id = id;
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
		
	}
	
	Card discardCard() {
		
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
