import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {

	}

	private boolean isValidInput(int n) {
		if (n >= 0 && n <= 9) {
			return true;
		} else {
			return false;
		}
	}

	private int[] getInput() {
		Scanner scnr = new Scanner(System.in);
		int x;
		int y;
		while (true) {
			System.out.println("Input X coordinate (0 - 9):");
			try {
				x = scnr.nextInt();
				if (this.isValidInput(x)) {
					break;
				} else {
					System.out.println("Not valid number, please input again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Not valid number, please input again.");
				this.getInput();
			}
		}

		while (true) {
			System.out.println("Input Y coordinate (0 - 9):");
			try {
				y = scnr.nextInt();
				if (this.isValidInput(y)) {
					break;
				} else {
					System.out.println("Not valid number, please input again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Not valid number, please input again.");
				this.getInput();
			}
		}
		int[] result = { x, y };
		return result;
	}
}
