package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokerGameEngineTest {
private PokerGameEngine gameEngine;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void testBet() {
		
		assertEquals(1.0, PokerGameEngine.getBet());
		assertEquals(1.5, PokerGameEngine.increaseBet());
		assertEquals(2.0, PokerGameEngine.increaseBet());
		assertEquals(3.0, PokerGameEngine.increaseBet());
		assertEquals(4.0, PokerGameEngine.increaseBet());
		assertEquals(5.0, PokerGameEngine.increaseBet());
		assertEquals(10.0, PokerGameEngine.increaseBet());
		assertEquals(10.0, PokerGameEngine.increaseBet());
		assertEquals(5.0, PokerGameEngine.decreaseBet());
		assertEquals(4.0, PokerGameEngine.decreaseBet());
		assertEquals(3.0, PokerGameEngine.decreaseBet());
		assertEquals(2.0, PokerGameEngine.decreaseBet());
		assertEquals(1.5, PokerGameEngine.decreaseBet());
		assertEquals(1.0, PokerGameEngine.decreaseBet());
		assertEquals(0.8, PokerGameEngine.decreaseBet());
		assertEquals(0.6, PokerGameEngine.decreaseBet());
		assertEquals(0.5, PokerGameEngine.decreaseBet());
		assertEquals(0.4, PokerGameEngine.decreaseBet());
		assertEquals(0.2, PokerGameEngine.decreaseBet());
		assertEquals(0.1, PokerGameEngine.decreaseBet());
		assertEquals(0.1, PokerGameEngine.decreaseBet());
		
	}


}
