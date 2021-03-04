package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Card;

class CardTest {
	
	Card card = new Card(Card.DIAMOND, 11); // Diamond 11
	
	@Test
	void testGetSuit() {
		assertEquals(1, card.getSuit(), "Wrong suit");
	}
	
	@Test
	void testGetValue() {
		assertEquals(11, card.getValue(), "Wrong value");
	}
	
	@Test
	@DisplayName("toString returns correct card image filename")
	void testToString () {
		assertEquals("11D", card.toString(), "toString() doesn't work");
	}

}
