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
		System.out.println("Input X coordinate:");
		int x = scnr.nextInt();
		System.out.println("Input Y coordinate:");
		int y = scnr.nextInt();
		int[] result = { x, y };
		return result;
	}
}
