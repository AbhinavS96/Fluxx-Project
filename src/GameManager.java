
import java.util.InputMismatchException;
import java.util.Scanner;

import game.Game;

public class GameManager {
	
	private Game game;
	
	public GameManager(int numberOfPlayers) {
		this.game = new Game(numberOfPlayers);
	}
	
	public void startGame() {
		this.game.play();
	}
	
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
		
		GameManager gameManager = new GameManager(numberOfPlayers);
		System.out.println("Starting the game!");
		gameManager.startGame();
	}

}
