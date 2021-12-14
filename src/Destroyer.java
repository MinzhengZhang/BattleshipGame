/**
 * A Ship with a length of two tiles.
 *
 * @author harry
 */
public class Destroyer extends Ship {

    /**
     * Sets the inherited length variable and initializes the hit array, based on
     * the size of this ship (2 tiles).
     */
    public Destroyer() {
        this.length = 2;
        for (int i = 0; i < this.length; i++) {
            this.hit[i] = false;
        }
    }

    /**
     * @return "Destroyer", indicating that this is a Destroyer.
     */
    public String getShipType() {
        return "Destroyer";
    }
}
