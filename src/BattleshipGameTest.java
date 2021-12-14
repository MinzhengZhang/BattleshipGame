import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BattleshipGameTest {
	static InputStream originalIn;
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		originalIn = System.in;
	}

	@AfterEach
	void tearDown() throws Exception {
		System.in.close();
		System.setIn(originalIn);
	}


	@Test
	public void testGetCoordinate(){
		BattleshipGame game = new BattleshipGame();
		String input = "5";

		InputStream stringData = new ByteArrayInputStream(input.getBytes());

		System.setIn(stringData);


		assertEquals(5,game.getCoordinate()[1]);


	}


}
