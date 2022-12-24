
import java.util.InputMismatchException;
import java.util.Scanner;

import game.Game;

/**
 * 
 * @author abhinav
 * This is the driver class that manages everything. Ideally, Game should be an abstract parent class
 * with different game types as children. Based on the input, the game can be initialized.
 * In this implementation, that is not done and by default, simplified fluxx is implemented.
 *
 */

public class GameManager {
	
	private Game game;
	
	/**
	 * 
	 * @param numberOfPlayers input from the user giving the number of players in the game
	 */
	public GameManager(int numberOfPlayers) {
		this.game = new Game(numberOfPlayers);
	}
	
	public void startGame() {
		this.game.play();
	}
	
	/**
	 * 
	 * @param args not used
	 * This is where the game starts. Repeatedly asks for valid input -the number of players
	 * Then it starts Simplified Fluxx
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to Simplified Fluxx!!");
		//There could also be an option to choose the type of Fluxx
		int numberOfPlayers;
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Choose the number of players (2-6)");
				numberOfPlayers = sc.nextInt();
				if(numberOfPlayers < 2 || numberOfPlayers > 6) {
					System.out.println("Please choose a number between 2 and 6...");
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
		
		//When multiple games are implemented a second argument could be an object of the child Game type
		GameManager gameManager = new GameManager(numberOfPlayers);
		System.out.println("Starting the game!");
		gameManager.startGame();
		sc.close();
	}

}
