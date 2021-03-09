package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	private Player player;
	
	@BeforeEach
	public void setUp () {
		player = new Player();
	}

	@Test
	public void testId() {
		int setup = 1;
		player.setId(setup);
		int result = player.getId();
		assertEquals(setup, result, "Id setter or getter not working");
	}
	
	@Test
	public void testFirstName() {
		String setup = "Firstname";
		player.setFirstName(setup);
		String result = player.getFirstName();
		assertEquals(setup, result, "FirstName setter or getter not working");
	}
	
	@Test
	public void testLastName() {
		String setup = "Lastname";
		player.setLastName(setup);
		String result = player.getLastName();
		assertEquals(setup, result, "LastName setter or getter not working");
	}
	
	@Test
	public void tesCredits() {
		double setup = 1.5;
		player.setCredits(setup);
		double result = player.getCredits();
		assertEquals(setup, result, "Credits setter or getter not working");
	}
	
	@Test
	public void testProfileName() {
		String setup = "Profilename";
		player.setProfileName(setup);
		String result = player.getProfileName();
		assertEquals(setup, result, "ProfileName setter or getter not working");
	}
	
	@Test
	public void testEmail() {
		String setup = "Email";
		player.setEmail(setup);
		String result = player.getEmail();
		assertEquals(setup, result, "Email setter or getter not working");
	}
	
	@Test
	public void testPassword() {
		String setup = "Password";
		player.setPassword(setup);
		String result = player.getPassword();
		assertEquals(setup, result, "Password setter or getter not working");
	}
	
	@Test
	public void testCreatedOn() {
		Date setup = new Date();
		player.setCreatedOn(setup);
		Date result = player.getCreatedOn();
		assertEquals(setup, result, "CreatedOn setter or getter not working");
	}
	
	@Test
	public void testCompareToGreater() {
		player.setCredits(2.5);
		Player player2 = new Player();
		player2.setCredits(1.0);
		int result = player.compareTo(player2);
		
		assertEquals(-1, result, "When player credits are greater than player to compare, should return -1");
	}
	
	@Test
	public void testCompareToEqual() {
		player.setCredits(2.5);
		Player player2 = new Player();
		player2.setCredits(2.5);
		int result = player.compareTo(player2);
		
		assertEquals(0, result, "When player credits are equal should return 0");
	}
	
	@Test
	public void testCompareToSmaller() {
		player.setCredits(1);
		Player player2 = new Player();
		player2.setCredits(2.5);
		int result = player.compareTo(player2);
		
		assertEquals(1, result, "When player gredits are smaller than player to compare, should return 1");
	}
	
	@Test
	public void testToString() {
		Date date = new Date();
		player = new Player("Testi", "Pelaaja", "sposti", "salainen");
		player.setId(1);
		player.setCredits(5.0);
		player.setCreatedOn(date);
		player.setProfileName("profiili");
		
		String result = player.toString();
		String expected = "1 Testi Pelaaja 5.0 profiili sposti salainen " + date;
		
		assertEquals(result, expected, "toString() not correct");
	}
	
	
	

}
