import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShipTest {
    private Ocean ocean;

    public void InitialOcean() {
        ocean = new Ocean();
    }

    @Test
    public void testGetBowColumn() {
        InitialOcean();
        Ship ship = new Battleship();
        ship.setBowColumn(0);
        assertEquals(ship.getBowColumn(), 0);
    }

    @Test
    public void testGetBowRow() {
        InitialOcean();
        Ship ship = new Battleship();
        ship.setBowRow(0);
        assertEquals(ship.getBowRow(), 0);
    }

    @Test
    public void testIsHorizontal() {
        InitialOcean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);
        assertTrue(ship.isHorizontal());
    }

    @Test
    public void testGetLength() {
        InitialOcean();
        Ship battleship = new Battleship();
        Ship cruiser = new Cruiser();
        Ship destroyer = new Destroyer();
        Ship submarine = new Submarine();
        assertEquals(battleship.getLength(), 4);
        assertEquals(cruiser.getLength(), 3);
        assertEquals(destroyer.getLength(), 2);
        assertEquals(submarine.getLength(), 1);
    }

    @Test
    public void testGetShipType() {
        InitialOcean();
        Ship battleship = new Battleship();
        Ship cruiser = new Cruiser();
        Ship destroyer = new Destroyer();
        Ship submarine = new Submarine();
        Ship emptySea = new EmptySea();
        assertTrue(battleship.getShipType().equals("Battleship"));
        assertTrue(cruiser.getShipType().equals("Cruiser"));
        assertTrue(destroyer.getShipType().equals("Destroyer"));
        assertTrue(submarine.getShipType().equals("Submarine"));
        assertTrue(emptySea.getShipType().equals("empty"));
    }

    @Test
    public void testIsSunk() {
        InitialOcean();
        Ship battleship = new Battleship();
        battleship.placeShipAt(0, 0, true, ocean);
        ocean.shootAt(0, 0);
        ocean.shootAt(0, 1);
        ocean.shootAt(0, 2);
        assertTrue(!battleship.isSunk());
        ocean.shootAt(0, 3);
        assertTrue(battleship.isSunk());
        Ship empty = new EmptySea();
        assertTrue(!empty.isSunk());
    }

    @Test
    public void testShootAt() {
        InitialOcean();
        Ship battleship = new Battleship();
        battleship.placeShipAt(2, 3, true, ocean);
        assertTrue(!battleship.shootAt(4, 5));
        assertTrue(!battleship.shootAt(3, 3));
        assertTrue(!battleship.shootAt(2, 9));
        assertTrue(battleship.shootAt(2, 3));
        ocean.shootAt(2, 4);
        ocean.shootAt(2, 5);
        ocean.shootAt(2, 6);
        assertTrue(!battleship.shootAt(2, 6));
        InitialOcean();
        Ship battleship2 = new Battleship();
        battleship2.placeShipAt(2, 3, false, ocean);
        assertTrue(!battleship2.shootAt(4, 5));
        assertTrue(!battleship2.shootAt(2, 4));
        assertTrue(!battleship2.shootAt(9, 3));
        assertTrue(battleship2.shootAt(2, 3));
        ocean.shootAt(3, 3);
        ocean.shootAt(4, 3);
        ocean.shootAt(5, 3);
        assertTrue(!battleship2.shootAt(5, 3));
        Ship emptySea = new EmptySea();
        assertTrue(!emptySea.shootAt(6, 4));

    }

    @Test
    public void testToString() {
        InitialOcean();
        Ship destroyer = new Destroyer();
        destroyer.placeShipAt(0, 0, false, ocean);
        ocean.shootAt(0, 0);
        assertTrue(destroyer.toString().equals("S"));
        ocean.shootAt(1, 0);
        assertTrue(destroyer.toString().equals("x"));
        Ship emptySea = new EmptySea();
        assertTrue(emptySea.toString().equals("-"));
    }

    @Test
    public void testOkToPlaceShipAt() {
        InitialOcean();
        Ship battleship = new Battleship();
        Ship submarine = new Submarine();
        Ship cruiser = new Cruiser();
        assertTrue(battleship.okToPlaceShipAt(8, 0, true, ocean));
        assertTrue(!battleship.okToPlaceShipAt(0, 8, true, ocean));
        battleship.placeShipAt(3, 4, true, ocean);
        submarine.placeShipAt(5, 6, false, ocean);
        assertTrue(!cruiser.okToPlaceShipAt(3, 8, false, ocean));
        assertTrue(!cruiser.okToPlaceShipAt(4, 4, false, ocean));
        assertTrue(!cruiser.okToPlaceShipAt(4, 3, false, ocean));
        assertTrue(!cruiser.okToPlaceShipAt(5, 6, true, ocean));
        assertTrue(!cruiser.okToPlaceShipAt(5, 6, false, ocean));
        assertTrue(cruiser.okToPlaceShipAt(5, 4, false, ocean));
    }

    @Test
    public void testPlaceShipAt() {
        InitialOcean();
        Ship destroyer = new Destroyer();
        destroyer.placeShipAt(0, 8, true, ocean);
        assertTrue(ocean.ships[0][8] instanceof Destroyer);
        InitialOcean();
        Ship destroyer2 = new Destroyer();
        destroyer2.placeShipAt(0, 9, true, ocean);
        assertTrue(!(ocean.ships[0][9] instanceof Destroyer));
    }
}
