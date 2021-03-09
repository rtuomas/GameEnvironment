package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRankingTest {
	
	private PlayerRanking ranking;
	
	@BeforeEach
	public void setUp () {
		ranking = new PlayerRanking(1,"a",1.0);
	}
	
	
	@Test
	public void testRank() {
		int setup = 3;
		ranking.setRank(setup);
		int result = ranking.getRank();
		assertEquals(setup, result, "Rank setter or getter not working");
	}
	
	@Test
	public void testProfileName() {
		String setup = "Profilename";
		ranking.setProfileName(setup);
		String result = ranking.getProfileName();
		assertEquals(setup, result, "ProfileName setter or getter not working");
	}
	
		
	@Test
	public void testCredits() {
		double setup = 3.5;
		ranking.setCredits(setup);
		double result = ranking.getCredits();
		assertEquals(setup, result, "Credits setter or getter not working");
	}
	

}
