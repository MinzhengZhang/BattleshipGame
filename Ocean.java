
/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 * 
 * @author harry
 *
 */
import java.util.Random;

public class Ocean implements OceanInterface {

	/**
	 * A 10x10 2D array of booleans, which can be used to quickly determine the
	 * location given whether has been fired.
	 */
	private boolean[][] firedField;

	/**
	 * A 10x10 2D array of Ships, which can be used to quickly determine which ship
	 * is in any given location.
	 */
	protected Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	protected int shotsFired;

	/**
	 * The number of times a shot hit a ship. If the user shoots the same part of a
	 * ship more than once, every hit is counted, even though the additional "hits"
	 * don't do the user any good.
	 */
	protected int hitCount;

	/**
	 * The number of ships totally sunk.
	 * 
	 */
	protected int shipsSunk;

	/**
	 * Creates an "empty" ocean, filling every space in the <code>ships</code> array
	 * with EmptySea objects. Should also initialize the other instance variables
	 * appropriately.
	 */
	public Ocean() {
		ships = new Ship[10][10];
		firedField = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = (Ship) new EmptySea();
				firedField[i][j] = false;
			}
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Larger ships
	 * must be placed before smaller ones to avoid cases where it may be impossible
	 * to place the larger ships.
	 * 
	 * @see java.util.Random
	 */
	public void placeAllShipsRandomly() {

		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				Ship battleship = new Battleship();
				placeShipRandomly(battleship);
			}
			if (i < 2) {
				Ship cruiser = new Cruiser();
				placeShipRandomly(cruiser);
			}
			if (i < 3) {
				Ship destroyer = new Destroyer();
				placeShipRandomly(destroyer);
			}
			Ship submarine = new Submarine();
			placeShipRandomly(submarine);
		}
	}

	/**
	 * Place one ship randomly on the ocean.
	 *
	 *
	 *
	 * @see java.util.Random
	 */
	private void placeShipRandomly(Ship s) {
		Random r = new Random();
		int x = r.nextInt(10);
		int y = r.nextInt(10);
		boolean horizontal = r.nextBoolean();
		while (!s.okToPlaceShipAt(x, y, horizontal, this)) {
			x = r.nextInt();
			y = r.nextInt();
			horizontal = r.nextBoolean();
		}
		s.placeShipAt(x, y, horizontal, this);
	}

	/**
	 * Checks if this coordinate is not empty; that is, if this coordinate does not
	 * contain an EmptySea reference.
	 * 
	 * @param row    the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return {@literal true} if the given location contains a ship, and
	 *         {@literal false} otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		if (ships[row][column] instanceof EmptySea) {
			return false;
		}
		return true;
	}

	/**
	 * Fires a shot at this coordinate. This will update the number of shots that
	 * have been fired (and potentially the number of hits, as well). If a location
	 * contains a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 * 
	 * @param row    the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return {@literal true} if the given location contains an afloat ship (not an
	 *         EmptySea), {@literal false} if it does not.
	 */
	public boolean shootAt(int row, int column) {
		firedField[row][column] = true;
		shotsFired += 1;
		Ship target = ships[row][column];
		if (target instanceof EmptySea) {
			return false;
		}

		else {
			if (target.shootAt(row, column)) {
				hitCount += 1;
				return true;
			}
			return false;
		}

	}

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.hitCount;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	@Override
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}.
	 */
	@Override
	public boolean isGameOver() {
		return shipsSunk == 10;
	}

	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instance variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 * 
	 * @return the 10x10 array of ships.
	 */
	@Override
	public Ship[][] getShipArray() {

		return ships;

	}

	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the
	 * left edge of the array, and column numbers should be displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be
	 * 0, 0.
	 * <ul>
	 * <li>Use 'S' to indicate a location that you have fired upon and hit a (real)
	 * ship</li>
	 * <li>'-' to indicate a location that you have fired upon and found nothing
	 * there</li>
	 * <li>'x' to indicate a location containing a sunken ship</li>
	 * <li>'.' (a period) to indicate a location that you have never fired
	 * upon.</li>
	 * </ul>
	 * 
	 * This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 * 
	 */
	@Override
	public void print() {
		for (int i = 0; i <= 10; i++) {
			for (int j = 0; j <= 10; j++) {
				if (i == 0 && j < 10) {
					System.out.println(j + " ");
					continue;
				} else if (i == 0 && j == 10) {
					continue;
				}
				if (j == 0 && i < 10) {
					System.out.println(i + " ");
					continue;
				} else if (j == 0 && i == 10) {
					continue;
				}
				if (firedField[i - 1][j - 1] == false) {
					System.out.print(".");
				} else if (ships[i - 1][j - 1] instanceof EmptySea) {
					System.out.print("-");
				} else {
					System.out.print(ships[i - 1][j - 1]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}