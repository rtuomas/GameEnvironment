package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
	
	// for testinks
	public Hand (Card [] h) {
		this.hand = h;
		this.sortedHand = this.hand;
		sortSortedHand();
	}

	public HandValue getScore () {
		if(isStraightFlush())
			return HandValue.STRAIGHT_FLUSH;
		else if(is4s())
			return HandValue.FOUR_OF_A_KIND;
		else if(isFullHouse())
			return HandValue.FULL_HOUSE;
		else if(isFlush())
			return HandValue.FLUSH;
		else if(isStraight())
			return HandValue.STRAIGHT;
		else if(isSet())
			return HandValue.THREE_OF_A_KIND;
		else if(isTwoPairs())
			return HandValue.TWO_PAIRS;
		else if (isAcePair())
			return HandValue.ACE_PAIR;
		else return HandValue.NO_WIN;
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
	
	//meibi change checkers private?
	
	public boolean isAcePair() {	
		return(sortedHand[0].getValue() == 1 && sortedHand[1].getValue() == 1 && sortedHand[2].getValue() != 1 && sortedHand[3].getValue() != 1 && sortedHand[4].getValue() != 1);	
	}

	public boolean isTwoPairs() {
		boolean isTwoPairs = false;
		if(sortedHand[0].getValue() == sortedHand[1].getValue() && sortedHand[2].getValue() == sortedHand[3].getValue()) {
			isTwoPairs = true;
		} else if(sortedHand[0].getValue() == sortedHand[1].getValue() && sortedHand[3].getValue() == sortedHand[4].getValue()) {
			isTwoPairs = true;
		} else if(sortedHand[1].getValue() == sortedHand[2].getValue() && sortedHand[3].getValue() == sortedHand[4].getValue()) {
			isTwoPairs = true;
		} else {
			isTwoPairs = false;
		}

		return isTwoPairs;
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

	public boolean isStraight(){
		return allCardsAreConsecutiveIn(valueSortedHand());
	}

	public boolean isFlush(){
		return firstAndLastCardAreSameSuitIn(suitSortedHand());
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

	public boolean is4s() {

		boolean a1, a2;

		a1 = sortedHand[0].getValue() == sortedHand[1].getValue() &&
				sortedHand[1].getValue() == sortedHand[2].getValue() &&
				sortedHand[2].getValue() == sortedHand[3].getValue();

		a2 = sortedHand[1].getValue() == sortedHand[2].getValue() &&
				sortedHand[2].getValue() == sortedHand[3].getValue() &&
				sortedHand[3].getValue() == sortedHand[4].getValue();

		return( a1 || a2 );
	}
	
	public boolean isStraightFlush(){
		return isStraight() && isFlush();
	}



	public Card[] getHand() {
		return this.hand;
	}

	public Card[] getSortedHand() {
		return this.sortedHand;
	}
	
  public boolean wins(){
    return this.getScore() != HandValue.NO_WIN;
  }
  
  public double worth(){
    return this.getScore().getMultiplier();
  }

	//The player can choose which cards they want to swap to new ones once per round.
	//Selected cards would be discarded from the hand and the rest sent to swapCards().
	//swapCards() then adds the remaining cards from the deck to fill the hand again,
	//returning the old hand with the new cards.

//	public void swapCards(Card[] sentHand) {
//		for(int i = 0; i < HANDSIZE; i++) {
//			if(hand[i] == null) {
//				hand[i] = currentDeck.nextCard();
//			}
//		}
//	}
	
	public Card[] swapCards (ArrayList<Integer> indexes) {
		for(int i : indexes) {
			this.hand[i] = currentDeck.nextCard();
		}
		return this.hand;
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


	private Card[] valueSortedHand(){
		sortedHand = hand.clone();
		Arrays.sort(sortedHand,Comparator.comparing(Card::getValue));
		return sortedHand;
	}

	private Card[] suitSortedHand(){
		sortedHand = hand.clone();
		Arrays.sort(sortedHand,Comparator.comparing(Card::getSuit));
		return sortedHand;
	}

	private boolean allCardsAreConsecutiveIn(Card[] hand){
		boolean straight = true;
		int start = 1;
		if(possiblyAceHighStraight()){
			start = 2;
		}
		for(int i=start;i<hand.length;i++){
			if(cardsAreNotConsecutive(hand[i],hand[i-1])){
				straight = false;
				break;
			}
		}
		return straight;
	}

	private boolean possiblyAceHighStraight(){
		return hasAce() && secondValueIs10();
	}

	private boolean hasAce(){
		return sortedHand[0].getValue() == 1;
	}

	private boolean secondValueIs10(){
		return sortedHand[1].getValue() == 10;
	}

	private boolean cardsAreNotConsecutive(Card first, Card second){
		return first.getValue() - second.getValue() != 1;
	}

	private boolean firstAndLastCardAreSameSuitIn(Card[] hand){
		return hand[0].getSuit() == hand[hand.length-1].getSuit();
	}
}