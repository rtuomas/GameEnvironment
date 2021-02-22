package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;


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
		System.out.println("Hand tested");
		assertEquals("1H, 2H, 3H, 4H, 5H", hand.toString());
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
	void testSwapCards() {
		Card[] handToSwap = hand.getHand();
		handToSwap[0] = null;
		handToSwap[3] = null;
		//hand.swapCards(handToSwap);
		System.out.println("Swap cards tested");
		System.out.println(hand.toString());
		assertEquals("6H, 2H, 3H, 7H, 5H", hand.getHand());
		//SwapCards works, but implementing this to work with view might be a problem
		
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
		System.out.println(hand.toString() + " " + rounds + " rounds, set");
		assertTrue(hand.isSet());
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
		System.out.println(hand.toString() + " " + rounds + " rounds, two pairs");
		assertTrue(hand.isTwoPairs());
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
		System.out.println(hand.toString() + " " + rounds + " rounds, fullhouse");
		assertTrue(hand.isFullHouse());
	}

}