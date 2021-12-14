/**
 * Ship is the abstract class for all of the ships and sea tiles that will make
 * up the game of Battleship. Ships of all kinds are always considered to be
 * facing up or to the left, meaning that any portion of the ship that is not
 * the bow will be at a higher numbered row or column than the bow.
 *
 * @author harry
 */
public abstract class Ship {

    /**
     * The column (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowColumn;

    /**
     * The row (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowRow;

    /**
     * hit is an array of four booleans telling whether that part of the ship has
     * been hit.
     */
    protected boolean[] hit;

    /**
     * true if the ship occupies a single row, false otherwise.
     */
    protected boolean horizontal;

    /**
     * The number of tiles occupied by the ship.
     */
    protected int length;

    /**
     * Creates a ship
     */
    public Ship() {
        this.hit = new boolean[4];
    }

    /**
     * @return the column of the bow (front) of the ship.
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * @param bowColumn the bowColumn to set
     */
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    /**
     * @return the row of the bow (front) of the ship.
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * @param bowRow the bowRow to set
     */
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    /**
     * @return true if this boat is horizontal (facing left), false otherwise.
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @param horizontal the horizontal to set.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * @return the length of the ship.
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the String representing the type of this ship.
     */
    public abstract String getShipType();

    /**
     * Returns true if this ship has been sunk and false otherwise.
     *
     * @return true if every part of the ship has been hit, and false otherwise.
     */
    public boolean isSunk() {
        for (int i = 0; i < this.length; i++) {
            if (!this.hit[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * If a part of this ship occupies this coordinate, and if the ship hasn't been
     * sunk, mark the part of the ship at that coordinate as "hit".
     *
     * @param row    the row of the shot
     * @param column the column of the shot
     * @return true if this ship hasn't been sunk and a part of this ship occupies
     * the given row and column and false otherwise.
     */
    public boolean shootAt(int row, int column) {
        if (row != this.bowRow && column != this.bowColumn) {
            return false;
        }
        if (this.isSunk()) {
            return false;
        }
        if (this.isHorizontal()) {
            if (row != this.bowRow) {
                return false;
            } else if (Math.abs(column - this.bowColumn) > this.length - 1) {
                return false;
            }
            this.hit[Math.abs(column - this.bowColumn)] = true;
        } else {
            if (column != this.bowColumn) {
                return false;
            } else if (Math.abs(row - this.bowRow) > this.length - 1) {
                return false;
            }
            this.hit[Math.abs(row - this.bowRow)] = true;
        }
        return true;
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
    public String toString() {
        if (this.isSunk()) {
            return "x";
        } else {
            return "S";
        }
    }

    /**
     * Determines whether or not this is represents a valid placement configuration
     * for this Ship in this Ocean. Ship objects in an Ocean must not overlap other
     * Ship objects or touch them vertically, horizontally, or diagonally.
     * Additionally, the placement cannot be such that the Ship would extend beyond
     * the extents of the 2D array in which it is placed. Calling this method should
     * not actually change either the Ship or the provided Ocean.
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     * @return true if it is valid to place this ship of this length in this
     * location with this orientation, and false otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (this.isOutOfBound(row, column, horizontal) || this.isHorizontalAdjacent(row, column, horizontal, ocean)
                || this.isVerticalAdjacent(row, column, horizontal, ocean)
                || this.isDiagonalAdjacent(row, column, horizontal, ocean)) {
            return false;
        }
        if (horizontal) {
            for (int i = 0; i < this.length; i++) {
                if (ocean.isOccupied(row, column + i)) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < this.length; i++) {
                if (ocean.isOccupied(row + i, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determine if the ship going to be placed will be out of the ocean.
     *
     * @param row        the candidate row to place the ship.
     * @param column     the candidate column to place the ship.
     * @param horizontal whether or not to have the ship facing to the left.
     * @return true if the ship is within the ocean, false otherwise.
     */
    private boolean isOutOfBound(int row, int column, boolean horizontal) {
        if (horizontal) {
            if (9 - column < this.length - 1) {
                return true;
            }
        } else {
            if (9 - row < this.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the ship going to be placed will be horizontally adjacent to
     * another ship in the ocean
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     * @return true if the ship will be horizontally adjacent to another ship in the
     * ocean, false otherwise
     */
    private boolean isHorizontalAdjacent(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            if (ocean.isOccupied(row, Math.max(0, column - 1))
                    || ocean.isOccupied(row, Math.min(9, column + this.length))) {
                return true;
            }
        } else {
            for (int i = row; i < Math.min(9, row + this.length); i++) {
                if (ocean.isOccupied(i, Math.max(0, column - 1)) || ocean.isOccupied(i, Math.min(9, column + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine if the ship going to be placed will be vertically adjacent to
     * another ship in the ocean
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     * @return true if the ship will be vertically adjacent to another ship in the
     * ocean, false otherwise
     */
    private boolean isVerticalAdjacent(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            for (int i = column; i < Math.min(9, column + this.length); i++) {
                if (ocean.isOccupied(Math.max(0, row - 1), i) || ocean.isOccupied(Math.min(9, row + 1), i)) {
                    return true;
                }
            }
        } else {
            if (ocean.isOccupied(Math.max(0, row - 1), column)
                    || ocean.isOccupied(Math.min(9, row + this.length), column)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the ship going to be placed will be diagonally adjacent to
     * another ship in the ocean
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     * @return true if the ship will be diagonally adjacent to another ship in the
     * ocean, false otherwise
     */
    private boolean isDiagonalAdjacent(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            if (ocean.isOccupied(Math.max(row - 1, 0), Math.max(column - 1, 0))
                    || ocean.isOccupied(Math.min(row + 1, 9), Math.max(column - 1, 0))
                    || ocean.isOccupied(Math.max(row - 1, 0), Math.min(column + this.length, 9))
                    || ocean.isOccupied(Math.min(row + 1, 9), Math.min(column + this.length, 9))) {
                return true;
            }
        } else {
            if (ocean.isOccupied(Math.max(row - 1, 0), Math.max(column - 1, 0))
                    || ocean.isOccupied(Math.max(row - 1, 0), Math.min(column + 1, 9))
                    || ocean.isOccupied(Math.min(row + this.length, 9), Math.max(column - 1, 0))
                    || ocean.isOccupied(Math.min(row + this.length, 9), Math.min(column + 1, 9))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Puts the Ship in the Ocean. This will give values to the bowRow, bowColumn,
     * and horizontal instance variables in the Ship. This should also place a
     * reference to this Ship in each of the one or more locations (up to four) in
     * the corresponding Ocean array this Ship is being placed in. Each of the
     * references placed in the Ocean will be identical since it is not possible to
     * refer to a "part" of a ship, only the whole ship.
     *
     * @param row        the candidate row to place the ship
     * @param column     the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean      the Ocean in which this ship might be placed
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (this.okToPlaceShipAt(row, column, horizontal, ocean)) {
            this.bowRow = row;
            this.bowColumn = column;
            this.horizontal = horizontal;
            if (horizontal) {
                for (int i = 0; i < this.length; i++) {
                    ocean.ships[row][column + i] = this;
                }
            } else {
                for (int i = 0; i < this.length; i++) {
                    ocean.ships[row + i][column] = this;
                }
            }
        }
    }
}
