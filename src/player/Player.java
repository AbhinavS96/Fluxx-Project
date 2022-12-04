package player;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

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
		
	}

	public void draw(Card card) {
		
	}
	
	public void addKeeper(Card card) {
		this.keepers.add(card);
	}
	
	public Card discardKeeper() {
		
	}
	
	Card discardCard() {
		
	}
	
	public void viewhand() {
		
	}
	
	public void viewKeepers() {
		
	}

}
