package InputManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {
	private static Scanner scanner;
	
	public static void initializeInputManager() {
		InputManager.scanner = new Scanner(System.in);
	}
	public static void closeInputManager() {
		InputManager.scanner.close();
	}
	
	public static int getIntergerInput(String displayText, int lowerLimit, int upperLimit, String errorMessage) {
		int numberOfPlayers;
		while(true) {
			try {
				System.out.println(displayText);
				numberOfPlayers = scanner.nextInt();
				if(numberOfPlayers < lowerLimit || numberOfPlayers > upperLimit) {
					System.out.println("Please choose a number between" + lowerLimit + " and " + upperLimit + "...");
					continue;
				}
				break;
			}
			//InputMismatchException reference: https://stackoverflow.com/questions/38830142/how-to-handle-invalid-input-when-using-scanner-nextint
			catch (InputMismatchException e) {
				System.out.println(errorMessage);
				//removing the wrong the input from the scanner
				scanner.next();
			}
			catch (Exception e) {
				System.out.println("Something went wrong... Please try again.");
			}
		}
		return numberOfPlayers;
	}
}
