package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayedGameTest {
	
	private PlayedGame pg;
	
	@BeforeEach
	public void setUp () {
		pg = new PlayedGame();
	}

	@Test
	public void testId() {
		int setup = 5;
		pg.setId(setup);
		int result = pg.getId();
		assertEquals(setup, result, "Id setter or getter not working");
	}
	
	@Test
	public void testPlayer1() {
		int setup = 6;
		pg.setPlayer1(setup);
		int result = pg.getPlayer1();
		assertEquals(setup, result, "Player1 setter or getter not working");
	}
	
	@Test
	public void testPlayer2() {
		int setup = 3;
		pg.setPlayer2(setup);
		int result = pg.getPlayer2();
		assertEquals(setup, result, "Player2 setter or getter not working");
	}
	
	@Test
	public void testGametype() {
		String setup = "Pokeri";
		pg.setGametype(setup);
		String result = pg.getGametype();
		assertEquals(setup, result, "Gametype setter or getter not working");
	}
	
	@Test
	public void testWinner() {
		int setup = 7;
		pg.setWinner(setup);
		int result = pg.getWinner();
		assertEquals(setup, result, "Winner setter or getter not working");
	}
	
	@Test
	public void testCreditChange() {
		double setup = 1.5;
		pg.setCreditChange(setup);
		double result = pg.getCreditChange();
		assertEquals(setup, result, "CreditChange setter or getter not working");
	}
	
	@Test
	public void testPlayedOn() {
		Date setup = new Date();
		pg.setPlayedOn(setup);
		Date result = pg.getPlayedOn();
		assertEquals(setup, result, "PlayedOn setter or getter not working");
	}
	
	@Test
	public void testCreditAfterPlayer1() {
		double setup = 13.7;
		pg.setCreditAfterPlayer1(setup);
		double result = pg.getCreditAfterPlayer1();
		assertEquals(setup, result, "CreditAferPlayer1 setter or getter not working");
	}
	
	@Test
	public void testCreditAfterPlayer2() {
		double setup = 12.7;
		pg.setCreditAfterPlayer2(setup);
		double result = pg.getCreditAfterPlayer2();
		assertEquals(setup, result, "CreditAferPlayer2 setter or getter not working");
	}
	
	@Test
	public void testToString() {
		Date date = new Date();
		int id = 1;
		pg = new PlayedGame(1, 2, "Pokeri", 1, 3.5, 2.0, 3.0);
		pg.setId(id);
		pg.setPlayedOn(date);
		
		String expected = "1 1 2 Pokeri 1 3.5 2.0 3.0 " + date;
		String result = pg.toString();
		
		assertEquals(expected, result, "toString not correct");
		
		
	}
}
