package model;

public class TextMain {

	public static void main(String[] args) {
		Card card = new Card (1,12);
		System.out.println(card.toString());
		
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand(deck);
		
		
		Card cards [] = hand.getHand();
		for(Card i : cards) {
			System.out.println(i.toString());
		}
	}
}
