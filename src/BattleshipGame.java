import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleshipGame {

	/**
	 * check user's input whether or not is valid
	 * @return {@literal true} if user's input is valid, otherwise
	 *         {@literal false}.
	 */
	private boolean isValidInput(int n) {
		if (n >= 0 && n <= 9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get user's input
	 * @return one integer that represent x coordinate or y coordinate
	 */
	private int getInput() {
		while (true) {
			Scanner scnr = new Scanner(System.in);
			int input;
			try {
				input = scnr.nextInt();
				if (this.isValidInput(input)) {
					return input;
				} else {
					System.out.println("Not valid number, please input again.");
					return getInput();
				}
			} catch (InputMismatchException e) {
				System.out.println("Not valid number, please input again.");
				return getInput();
			}
		}
	}

	/**
	 * Get coordinate of ship by using getInput helper method
	 * @return the coordinate of ship by a int array
	 */
	private int[] getCoordinate() {
		int x;
		int y;
		System.out.println("Input row coordinate (0 - 9):");
		x = getInput();
		System.out.println("Input column coordinate (0 - 9):");
		y = getInput();
		int[] coordinate = { x, y };
		return coordinate;
	}


	/**
	 * Game starter for BattleshipGame, automatically run the game after
	 * run the starter()
	 *
	 */
	public void starter() {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		while (!ocean.isGameOver()) {
			ocean.print();
			System.out.println("Please input the coordinate");
			int[] coordinate = getCoordinate();
			int x = coordinate[0];
			int y = coordinate[1];

			ocean.shootAt(x, y);
		}
		ocean.print();
		System.out.println("Game over! your total score is " + ocean.getShotsFired());
	}

	public static void main(String[] args) {
		BattleshipGame bg = new BattleshipGame();
		bg.starter();
	}

}
