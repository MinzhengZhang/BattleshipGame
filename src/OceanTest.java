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
        Ship s = new Battleship();
        s.placeShipAt(4,4,false,ocean );
        assertTrue(ocean.isOccupied(4,4));
        assertTrue(ocean.isOccupied(5,4));
        assertTrue(!ocean.isOccupied(6,2));
    }

    @Test
    public void testPlaceShipRandomly(){
        InitialOcean();
        ocean.placeAllShipsRandomly();
        Ship[][] ships = ocean.getShipArray();
        int count = 0;
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                if(!(ships[i][j]instanceof EmptySea)){
                    count +=1;
                }
            }
        }
        assertEquals(20,count);
    }



    @Test
    public void testShootAt(){
        InitialOcean();
        Ship s = new Battleship();
        s.placeShipAt(2,3,true,ocean );
        assertTrue(ocean.shootAt(2,3));
        assertTrue(ocean.shootAt(2,4));
        assertTrue(ocean.shootAt(2,5));
        assertTrue(ocean.shootAt(2,6));
        assertTrue(!ocean.shootAt(2,6));
        assertTrue(!ocean.shootAt(1,3));
    }

    @Test
    public void testGetShotsFired() {
        InitialOcean();
        Ship s = new Destroyer();
        s.placeShipAt(2,3,true,ocean );
        assertEquals(0,ocean.getShotsFired());
        ocean.shootAt(2,3);
        assertEquals(1,ocean.getShotsFired());
        ocean.shootAt(2,3);
        assertEquals(2,ocean.getShotsFired());

    }


    @Test
    public void testGetHitCount(){
        InitialOcean();
        assertEquals(0,ocean.getHitCount());
        Ship s = new Cruiser();
        s.placeShipAt(6,3,true,ocean);
        ocean.shootAt(6,3);
        assertEquals(1,ocean.getHitCount());
        ocean.shootAt(6,4);
        assertEquals(2,ocean.getHitCount());
    }

    @Test
    public void testGetShipsSunk(){
        InitialOcean();
        assertEquals(0,ocean.getShipsSunk());
        Ship s = new Submarine();
        s.placeShipAt(0,0,true,ocean);
        ocean.shootAt(0,0);
        assertEquals(1,ocean.getShipsSunk());
        Ship s1 = new Cruiser();
        s1.placeShipAt(6,3,true,ocean);
        ocean.shootAt(6,3);
        ocean.shootAt(6,4);
        ocean.shootAt(6,5);
        assertEquals(2,ocean.getShipsSunk());
    }

    @Test
    public void  testIsGameOver(){
        InitialOcean();
        assertTrue(!ocean.isGameOver());
        ocean.placeAllShipsRandomly();
        for(int i = 0; i<10;i++){
            for(int j = 0;j<10;j++){
                ocean.shootAt(i,j);
            }
        }
        assertTrue(ocean.isGameOver());
    }




}
