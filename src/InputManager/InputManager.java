package InputManager;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author abhinav
 * This class manages the input from the user and does the error handling
 */
public class InputManager {
	private static Scanner scanner;
	
	public static void initializeInputManager() {
		InputManager.scanner = new Scanner(System.in);
	}
	public static void closeInputManager() {
		InputManager.scanner.close();
	}
	
	public static int getIntergerInput(String displayText, int lowerLimit, int upperLimit, String errorMessage) {
		int response;
		while(true) {
			try {
				System.out.println(displayText);
				response = scanner.nextInt();
				if(response < lowerLimit || response > upperLimit) {
					System.out.println("Please choose a number between " + lowerLimit + " and " + upperLimit + "...");
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
		return response;
	}
}
