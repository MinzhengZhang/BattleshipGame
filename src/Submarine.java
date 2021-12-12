/**
 * A Ship with a length of one tile.
 * 
 * @author harry
 */
public class Submarine extends Ship {

	/**
	 * Sets the inherited length variable and initializes the hit array, based on
	 * the size of this ship (1 tile).
	 */
	public Submarine() {
		this.length = 1;
		for (int i = 0; i < this.length; i++) {
			this.hit[i] = false;
		}
	}

	/**
	 * @return "Submarine", indicating that this is a Submarine.
	 */
	public String getShipType() {
		return "Submarine";
	}
}
