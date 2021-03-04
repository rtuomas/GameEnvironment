package model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



class HandTest {
	
	Deck deck = new Deck();
	Hand hand = new Hand(deck);
	Card[] cards = new Card[5];
	Card card;
	
	/*
	@BeforeEach
	void setUp() {
		deck.shuffle();
	}
	*/

	@Test
	void testHand() {
		System.out.println("Hand tested " + hand.toString());
		boolean handTest = false;
		if(hand != null) {
			handTest = true;
		}
		assertTrue(handTest);
	}
	
	@Test
	void testSortedHand() {
		deck.shuffle();
		hand = new Hand(deck);
		Card[] sortedHand = hand.getSortedHand();
		boolean sorted = false;
		for(int i = 0; i<4; i++) {
			if(sortedHand[i].getValue() > sortedHand[i+1].getValue()) {
				sorted = false;
				break;
			}
			if(i == 3) {
				sorted = true;
			}
		}
		System.out.println("Sorted hand tested");
		System.out.println(hand.toString());
		assertTrue("Hand is not sorted", sorted);
	}
	
	
	@Test
	void testSet() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isSet());
		System.out.println(hand.toString() + " " + rounds + " rounds, three of a kind. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isSet());
		assertEquals(2.5, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testAcePair() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isAcePair());
		System.out.println(hand.toString() + " " + rounds + " rounds, ace pair. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isAcePair());
		assertEquals(1.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testNoWin() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(hand.getScore().getMultiplier() != 0);
		System.out.println(hand.toString() + " " + rounds + " rounds, no win. Multiplier: " + hand.getScore().getMultiplier());
		assertEquals(0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testStraight() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isStraight());
		System.out.println(hand.toString() + " " + rounds + " rounds, straight. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isStraight());
		assertEquals(3.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testFlush() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isFlush());
		System.out.println(hand.toString() + " " + rounds + " rounds, flush. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isFlush());
		assertEquals(3.5, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void test4s() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.is4s());
		System.out.println(hand.toString() + " " + rounds + " rounds, four of a kind. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.is4s());
		assertEquals(7.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testStraightFlush() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isStraightFlush());
		System.out.println(hand.toString() + " " + rounds + " rounds, straight flush. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isStraightFlush());
		assertEquals(10.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	@Test
	void testTwoPairs() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isTwoPairs());
		System.out.println(hand.toString() + " " + rounds + " rounds, two pairs. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isTwoPairs());
		assertEquals(2.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}
	
	@Test
	void testFullHouse() {
		int rounds = 0;
		do {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand(deck);
			rounds++;
		} while(!hand.isFullHouse());
		System.out.println(hand.toString() + " " + rounds + " rounds, fullhouse. Multiplier: " + hand.getScore().getMultiplier());
		assertTrue(hand.isFullHouse());
		assertEquals(5.0, hand.getScore().getMultiplier(), "Multiplier wrong");
	}

}