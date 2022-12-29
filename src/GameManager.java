import InputManager.InputManager;
import game.Game;

/**
 * 
 * @author abhinav
 * This is the driver class that manages everything. Ideally, Game should be an abstract parent class
 * with different game types as children. Based on the input, the game can be initialized.
 * In this implementation, that is not done and by default, simplified fluxx is implemented.
 * Game credits: https://www.looneylabs.com/
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
	
	public static void howToPlay() {
		System.out.println("********** How to Play **********\n");
		System.out.println("Fluxx games are all about change. \nThe game begins with just a couple of simple rules, then becomes more complex little by little as additional rule cards are played. \nAt first there will be no way to win - that's what Goal cards are for. \nEach Goal has a different victory condition, but they all require you to collect special cards called Keepers. \nAs soon as someone's collection of Keepers matches the current Goal, they win!");
		System.out.println("More info: https://www.looneylabs.com/sites/default/files/literature/Fluxx5.0Rules.pdf");
		System.out.println("\n*********************************\n");
	}
	
	/**
	 * 
	 * @param args not used
	 * This is where the game starts. Repeatedly asks for valid input -the number of players
	 * Then it starts Simplified Fluxx
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
				//There could also be an option to choose the type of Fluxx
				int numberOfPlayers = InputManager.getIntergerInput("Choose the number of players (2-6)", 2, 6, "Invalid input... Please try again.");
				//When multiple games are implemented a second argument could be an object of the child Game type
				GameManager gameManager = new GameManager(numberOfPlayers);
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
