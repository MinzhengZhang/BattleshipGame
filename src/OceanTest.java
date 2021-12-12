import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OceanTest {
    private Ocean ocean;

    public void InitialOcean(){
        ocean = new Ocean();
    }

    @Test
    public void testIsOccupied(){
        InitialOcean();
        assertTrue(!ocean.isOccupied(1,1));
    }
    @Test
    public void testShootAt(){
        InitialOcean();
    }
    @Test
    public void testGetHitCount(){
        InitialOcean();
    }

    @Test
    public void testGetShipsSunk(){
        InitialOcean();
        assertEquals(0,ocean.getShipsSunk());

    }

    @Test
    public void  testIsGameOver(){
        InitialOcean();
    }




}
