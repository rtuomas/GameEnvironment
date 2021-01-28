package model;

import java.util.List;

/**
 * The Hand class gets the cards from the deck and works as the 
 * main component for the game, showing the user their cards.
 * @author Ville Riepponen
 * @version 0.1 28.01.2021
 */

public class Hand {
	final static int HANDSIZE = 5;
	private Card[] hand = new Card [HANDSIZE];
	private Deck currentDeck;
	
	public Hand (Deck deck){
		this.currentDeck = deck;
		fillHandWithCards();
	}
	
	private void fillHandWithCards(){
		for (int i = 0; i < HANDSIZE; i++) {
			hand[i] = currentDeck.nextCard();
		}
	}
	
	public Card[] getHand() {
		return this.hand;
	}
	
	//The player can choose which cards they want to swap to new ones once per round.
	//Selected cards would be discarded from the hand and the rest sent to swapCards().
	//swapCards() then adds the remaining cards from the deck to fill the hand again,
	//returning the old hand with the new cards.
	
	public Card[] swapCards(Card[] sentHand) {
		for (int i = sentHand.length; i < HANDSIZE; i++) {
			hand[i] = currentDeck.nextCard();
		}
		return sentHand;
	}
}
