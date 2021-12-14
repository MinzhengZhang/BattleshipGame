import java.util.NoSuchElementException;
import java.util.Scanner;

public class BattleshipGame {

    /**
     * Check user's input whether or not is valid; this method will be called by
     * getInput() method.
     *
     * @param n input n is an integer, if n > 9 or < 0, n is invalid
     * @return {@literal true} if user's input is valid, otherwise {@literal false}.
     */
    private boolean isValidInput(int n) {
        if (n >= 0 && n <= 9) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get user's valid input; every time only get one valid integer to represent
     * row or column's coodinate, this method will be called by getCoordinate()
     * method.
     *
     * @return one integer that represent x coordinate or y coordinate
     */
    public int getInput() {
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
            } catch (NoSuchElementException e) {
                System.out.println("Not valid number, please input again.");
                return getInput();
            }
        }
    }

    /**
     * Get coordinate of ship by using getInput helper method
     *
     * @return the coordinate of ship by a int array
     */
    private int[] getCoordinate() {
        System.out.println("Input row coordinate (0 - 9):");
        int x = getInput();
        System.out.println("Input column coordinate (0 - 9):");
        int y = getInput();
        int[] coordinate = {x, y};
        return coordinate;
    }

    /**
     * Game starter for BattleshipGame, automatically run the game after run the
     * starter()
     */
    private void starter() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        while (!ocean.isGameOver()) {
            ocean.print();
            System.out.println("Please input the coordinate");
            int[] coordinate = getCoordinate();
            int x = coordinate[0];
            int y = coordinate[1];
            Ship target = ocean.getShipArray()[x][y];
            if (ocean.shootAt(x, y)) {
                System.out.println("hit");
                if (target.isSunk()) {
                    System.out.println("You just sunk a " + target.getShipType());
                }
            } else {
                System.out.println("miss");
            }

        }
        ocean.print();
        System.out.println("Game over! your total score is " + ocean.getShotsFired());
    }

    public static void main(String[] args) {
        BattleshipGame bg = new BattleshipGame();
        bg.starter();
    }

}