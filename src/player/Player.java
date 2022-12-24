package player;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
		this.name = "Player " + (playerCount+1);
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
	
	/**
	 * 
	 * @param cardNumber is the index of the card to be removed
	 * This is where the play action is made by the player
	 * @return the card that was played by the user
	 * 
	 */
	public Card play(int cardNumber) {
		return this.hand.remove(cardNumber);
	}
	
	/**
	 * 
	 * @param card the card to be added to the users hand
	 * 
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * 
	 * @param card is the card that the user got from the top of the deck 
	 * This is where the draw action is completed. 
	 * 
	 */
	public void draw(Card card) {
		System.out.println("Card drawn: ");
		System.out.println(card);
		this.hand.add(card);
	}
	
	public void addKeeper(Card card) {
		this.keepers.add(card);
	}
	
	public List<Card> getKeepers(){
		return this.keepers;
	}
	
	/**
	 * 
	 * user can view and delete a keeper
	 * @return card that is removed. It gets added to the discard pile
	 * 
	 */
	public Card discardKeeper() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("choose a keeper to remove");
		for(int i = 0; i < this.keepers.size(); ++i) {
			System.out.println(i + this.keepers.get(i).toString());
		}
		int cardNumber;
		while(true) {
			try {
				System.out.println("choose a keeper to discard");
				cardNumber = sc.nextInt();
				if(cardNumber < 1 || cardNumber > this.keepers.size()) {
					System.out.println("Please choose a valid card number...");
					continue;
				}
				break;
			}
			//InputMismatchException reference: https://stackoverflow.com/questions/38830142/how-to-handle-invalid-input-when-using-scanner-nextint
			catch (InputMismatchException e) {
				System.out.println("Invalid input... Please try again.");
			}
			catch (Exception e) {
				System.out.println("Something went wrong... Please try again.");
			}
		}	
		sc.close();
		return this.keepers.remove(cardNumber);
	}
	
	
	/**
	 * 
	 * user can view and delete a card on hand
	 * @return card that is removed. It gets added to the discard pile
	 * 
	 */
	public Card discardCard() {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < this.hand.size(); ++i) {
			System.out.println((i+1) + this.hand.get(i).toString());
		}
		//need to validate input here
		int cardNumber;
		while(true) {
			try {
				System.out.println("choose a card to discard");
				cardNumber = sc.nextInt();
				if(cardNumber < 1 || cardNumber > this.hand.size()) {
					System.out.println("Please choose a valid card number...");
					continue;
				}
				break;
			}
			//InputMismatchException reference: https://stackoverflow.com/questions/38830142/how-to-handle-invalid-input-when-using-scanner-nextint
			catch (InputMismatchException e) {
				System.out.println("Invalid input... Please try again.");
			}
			catch (Exception e) {
				System.out.println("Something went wrong... Please try again.");
			}
		}	
		sc.close();
		System.out.println("Discarding " + this.hand.get(cardNumber-1));
		return this.hand.remove(cardNumber-1);		
	}
	
	public void viewhand() {
		if(this.hand.size() == 0) {
			System.out.println("No cards on hand");
			return;
		}
		
		System.out.println("Card in hand are:");
		for(int i = 0; i < this.hand.size(); ++i) {
			System.out.println((i+1) + ". " + this.hand.get(i));
		}
	}
	
	public void viewKeepers() {
		if(this.hand.size() == 0) {
			System.out.println("No keepers in play");
			return;
		}
		
		System.out.println("Card in hand are:");
		for(int i = 0; i < this.keepers.size(); ++i) {
			System.out.println((i+1) + ". " + this.keepers.get(i));
		}
	}
	
	public String toString() {
		return this.name;
	}
}
