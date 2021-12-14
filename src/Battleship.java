/**
 * A Ship with a length of four tiles.
 *
 * @author harry
 */
public class Battleship extends Ship {

    /**
     * Sets the inherited length variable and initializes the hit array, based on
     * the size of this ship (4 tiles).
     */
    public Battleship() {
        this.length = 4;
        for (int i = 0; i < this.length; i++) {
            this.hit[i] = false;
        }
    }

    /**
     * @return "Battleship", indicating that this is a Battleship.
     */
    public String getShipType() {
        return "Battleship";
    }
}
