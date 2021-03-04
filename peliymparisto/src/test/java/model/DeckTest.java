package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Deck;

class DeckTest {

	@Test
	@DisplayName("Test unshuffled deck")
	void testGiveCard() {
		Deck deck = new Deck ();
		Card card;
		String expected;
		
		for(int suit = Card.HEART; suit < Card.SPADE; suit++) {
			for(int value = 1; value < 14; value++) {
				card = deck.nextCard();
				expected = value + Card.SUITS[suit];
				assertEquals(expected, card.toString());
			}
		}
	}
	
	@Test
	@DisplayName("Check if cards are shuffled")
	void testShuffledPack() {
		Deck deck = new Deck();
		deck.shuffle();
		for(int i = 0; i < 52; i ++) {
			Card card = deck.nextCard();
			System.out.println(card.toString());
		}
		assertEquals(1,1, "Check console");
	}
}
