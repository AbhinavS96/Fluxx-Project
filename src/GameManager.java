import InputManager.InputManager;
import game.Game;
import game.SimplifiedFluxx;
import game.StandardFluxx;

/**
 * 
 * @author abhinav
 * This is the driver class that manages everything.
 * The number of players and the type of the game is taken as an input from the user
 * Game credits: https://www.looneylabs.com/
 * 
 */

public class GameManager {
	
	private Game game;
	
	/**
	 * 
	 * @param numberOfPlayers input from the user giving the number of players in the game
	 */
	public GameManager(int numberOfPlayers, int gameType) {
		switch(gameType) {
		case 1: 
			this.game = new SimplifiedFluxx(numberOfPlayers);
			break;
		case 2:
			this.game = new StandardFluxx(numberOfPlayers);
		}
	}
	
	public void startGame() {
		this.game.play();
	}
	
	/**
	 * This function displays a help menu to the user before starting the game
	 */
	public static void howToPlay() {
		System.out.println("********** How to Play **********\n");
		System.out.println("Fluxx games are all about change. \nThe game begins with just a couple of simple rules, then becomes more complex little by little as additional rule cards are played. \nAt first there will be no way to win - that's what Goal cards are for. \nEach Goal has a different victory condition, but they all require you to collect special cards called Keepers. \nAs soon as someone's collection of Keepers matches the current Goal, they win!");
		System.out.println("More info: https://www.looneylabs.com/sites/default/files/literature/Fluxx5.0Rules.pdf");
		System.out.println("To play, follow the menu items and input the matching number to choose an option.");
		System.out.println("\n*********************************\n");
	}
	
	/**
	 * 
	 * @param args not used
	 * This is where the game starts. Takes the game type and number of players as an input from the user
	 * Then it starts the chosen version. Only Simplified Fluxx is completely implemented
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to Simplified Fluxx!!\n");
		InputManager.initializeInputManager();
		
		//start menu
		boolean exitProgram = false;
		while(!exitProgram) {
			System.out.println("***********Main Menu***********\n");
			System.out.println("1. Start a new game");
			System.out.println("2. How to play");
			System.out.println("3. Exit");
			int choice = InputManager.getIntergerInput("Please choose an option from the menu...", 1, 3, "Invalid input... Please try again.");
			switch(choice) {
			case 1: 
				System.out.println("\nGame Type");
				System.out.println("1. Simplified Fluxx");
				System.out.println("2. Standard Fluxx");
				int gameType = InputManager.getIntergerInput("Choose the game type", 1, 2, "Invalid input... Please try again.");
				int numberOfPlayers = InputManager.getIntergerInput("Choose the number of players (2-6)", 2, 6, "Invalid input... Please try again.");
				GameManager gameManager = new GameManager(numberOfPlayers, gameType);
				System.out.println("\n*******Starting the game!*******\n");
				gameManager.startGame();
				break;
			case 2:
				GameManager.howToPlay();
				break;
			case 3:
				exitProgram = true;
			}
		}
		System.out.println("Exiting game. Thanks for playing...");
		InputManager.closeInputManager();
	}

}
