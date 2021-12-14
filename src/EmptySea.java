/**
 * Considered a type of Ship so that the Ocean's ship 2D array can consist of
 * EmptySea references for empty tiles and proper ships for tiles with ships
 * actually inside of them.
 * 
 * @author harry
 */
public class EmptySea extends Ship {

	/**
	 * Sets the inherited length variable and initializes the hit array, based on
	 * the size of this Empty Sea (1 tile).
	 */
	public EmptySea() {
		this.length = 1;
		for (int i = 0; i < this.length; i++) {
			this.hit[i] = false;
		}
	}

	/**
	 * Since an EmptySea is empty by definition, shooting at one will always be a
	 * miss.
	 * 
	 * @param row    the row of the shot
	 * @param column the column of the shot
	 * @return false always, since nothing will be hit.
	 */
	@Override
	public boolean shootAt(int row, int column) {
		return false;
	}

	/**
	 * Since an EmptySea is empty by definition, it is not possible that one can be
	 * sunk.
	 * 
	 * @return false always, since nothing will be hit.
	 */
	@Override
	public boolean isSunk() {
		return false;
	}

	/**
	 * Returns a single character String to use in the Ocean's print method. This
	 * method should return "x" if the ship has been sunk, and "S" if it has not yet
	 * been sunk. This method can only be used to print out locations in the ocean
	 * that have been shot at; it should not be used to print locations that have
	 * not been the target of a shot yet.
	 * 
	 * @return "x" if this ship has been sunk, and "S" otherwise.
	 */
	@Override
	public String toString() {
		return "-";
	}

	/**
	 * @return "empty", indicating that this is an Empty sea tile.
	 */
	public String getShipType() {
		return "empty";
	}
}
