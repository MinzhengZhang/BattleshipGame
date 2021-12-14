import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

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

        Ship  s2 = new Battleship();
        s2.placeShipAt(5,8,false,ocean);
        Ship s3 = new Submarine();
        s3.placeShipAt(9,8,true,ocean);
        assertTrue(!ocean.isOccupied(9,8));

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
    @Test
    public void testPrint(){
        InitialOcean();
        Battleship  battleShip = new Battleship();
        battleShip.placeShipAt(0,3,true,ocean);
        Submarine submarine = new Submarine();
        ocean.shootAt(0,3);
        submarine.placeShipAt(2,5,true,ocean);
        ocean.shootAt(2,5);
        ocean.shootAt(9,9);
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        ocean.print();
        String actual = outputStream.toString();
        String expected = "\t\t0\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\n" +
                "0\t\t.\t\t.\t\t.\t\tS\t\tS\t\tS\t\tS\t\t.\t\t.\t\t.\n" +
                "\n" +
                "1\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "2\t\t.\t\t.\t\t.\t\t.\t\t.\t\tx\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "3\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "4\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "5\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "6\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "7\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "8\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\n" +
                "\n" +
                "9\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t.\t\t-\n"+"\n";
        assertEquals("S",actual.substring(44,45));
        assertEquals(expected.substring(0,30), actual.substring(0,30));
        assertEquals("x",actual.substring(120,121));
        assertEquals(expected.substring(70,71), actual.substring(70,71));
        assertEquals("-",actual.substring(actual.length()-5,actual.length()-4));
    }


}
