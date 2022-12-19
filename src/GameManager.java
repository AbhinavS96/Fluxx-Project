
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
		//check input for number of users -2
		//user can also choose the game type -simplified fluxx
		GameManager gameManager = new GameManager(2);
		gameManager.startGame();
	}

}
