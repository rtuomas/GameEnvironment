package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The Hand class gets the cards from the deck and works as the 
 * main component for the game, showing the user their cards.
 * @author Ville Riepponen
 * @version 0.1 28.01.2021
 */

public class Hand{
	final static int HANDSIZE = 5;
	private Card[] hand = new Card [HANDSIZE];
	private Card[] sortedHand = new Card [HANDSIZE];
	private Deck currentDeck;
	
	public Hand (Deck deck){
		this.currentDeck = deck;
		fillHandWithCards();
		this.sortedHand = this.hand;
		sortSortedHand();
	}
	
	private void fillHandWithCards(){
		for (int i = 0; i < HANDSIZE; i++) {
			hand[i] = currentDeck.nextCard();
		}
	}
	
	
	
	private void sortSortedHand() {
		Arrays.sort(sortedHand, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return c1.getValue() - c2.getValue();
			}
		});
	}
	
	
	
	public Card[] getHand() {
		return this.hand;
	}
	
	public Card[] getSortedHand() {
		return this.sortedHand;
	}
	
	//The player can choose which cards they want to swap to new ones once per round.
	//Selected cards would be discarded from the hand and the rest sent to swapCards().
	//swapCards() then adds the remaining cards from the deck to fill the hand again,
	//returning the old hand with the new cards.
	
	public void swapCards(Card[] sentHand) {
		for (int i = 0; i < HANDSIZE; i++) {
			if(hand[i] == null) {
				hand[i] = currentDeck.nextCard();
			}
		}
	}
	
	public String toString() {
		String tempString = "";
		for(int i = 0; i < HANDSIZE; i++) {
			if(i != 4) {
				tempString += hand[i].toString() + ", ";
			} else {
				tempString += hand[i].toString();
			}
			
		}
		return tempString;
	}
	
	public boolean isSet() {
		boolean isSet = false;
		int c1 = sortedHand[0].getValue(), 
				c2 = sortedHand[1].getValue(), 
				c3 = sortedHand[2].getValue(), 
				c4 = sortedHand[3].getValue(), 
				c5 = sortedHand[4].getValue();
		if(c1 == c2 && c2 == c3 && c4 != c1 && c5 != c1 && c3 != c4) {
			isSet = true;
		} else if(c2 == c3 && c3 == c4 && c1 != c2 && c5 != c2 && c1 != c5) {
			isSet = true;
		} else if(c3 == c4 && c4 == c5 && c1 != c3 && c2 != c3 && c1 != c2) {
			isSet = true;
		} else {
			isSet = false;
		}
		
		return isSet;
	}
	
	public boolean isFullHouse() {
		boolean isFullHouse = false;
		int c1 = sortedHand[0].getValue(), 
				c2 = sortedHand[1].getValue(), 
				c3 = sortedHand[2].getValue(), 
				c4 = sortedHand[3].getValue(), 
				c5 = sortedHand[4].getValue();
		if(c1 == c2 && c2 == c3 && c4 == c5) {
			isFullHouse = true;
		} else if(c1 == c2 && c3 == c4 && c4 == c5) {
			isFullHouse = true;
		} else {
			isFullHouse = false;
		}
		
		return isFullHouse;
	}
	
	public boolean isTwoPairs() {
		boolean isTwoPairs = false;
		if(hand[0].getValue() == hand[1].getValue() && hand[2].getValue() == hand[3].getValue()) {
			isTwoPairs = true;
		} else if(hand[0].getValue() == hand[1].getValue() && hand[3].getValue() == hand[4].getValue()) {
			isTwoPairs = true;
		} else if(hand[1].getValue() == hand[2].getValue() && hand[3].getValue() == hand[4].getValue()) {
			isTwoPairs = true;
		} else {
			isTwoPairs = false;
		}
		
		return isTwoPairs;
	}
}