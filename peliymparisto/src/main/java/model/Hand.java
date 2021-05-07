package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * The Hand class gets the cards from the deck and works as the 
 * main component for the game, showing the user their cards.
 * @author Ville Riepponen
 * @version 1.0 7.5.2021
 */

public class Hand{
	/** The final handsize which is always 5 */
	final static int HANDSIZE = 5;
	/** Array with the size of a hand to hold the current hand  */
	private Card[] hand = new Card [HANDSIZE];
	/** Array to include the sorted hand for internal methods */
	private Card[] sortedHand = new Card [HANDSIZE];
	/** Deck object to get the cards for the hand */
	private Deck currentDeck;

	/** 
	 * Constructor, takes a deck and fills the hand with cards and get a sorted hand for internal methods 
	 * @param deck : The source of cards for the hand
	 */
	public Hand (Deck deck){
		this.currentDeck = deck;
		fillHandWithCards();
		this.sortedHand = this.hand;
		sortSortedHand();
	}
	
	// for testinks
	/** 
	 * Alternative constructor, for getting cards for the hand as a parameter 
	 * @param h : Array of cards for the hand
	 */
	public Hand (Card [] h) {
		this.hand = h;
		this.sortedHand = this.hand;
		sortSortedHand();
	}

	/** 
	 * A Method to get the value of the hand to determine which outcome to output
	 * @return an enum with the corresponding result from the end hand
	 */
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

	/** 
	 * A method to fill the hand with a deck that has been given
	 */
	private void fillHandWithCards(){
		for (int i = 0; i < HANDSIZE; i++) {
			hand[i] = currentDeck.nextCard();
		}
	}

	/** 
	 * A method to sort the internal hand in order to check outcomes from the hand easier
	 */
	private void sortSortedHand() {
		Arrays.sort(sortedHand, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return c1.getValue() - c2.getValue();
			}
		});
	}
	
	//meibi change checkers private?
	/** 
	 * A method to check for an ace pair
	 * @return boolean for the combination
	 */
	public boolean isAcePair() {	
		return(sortedHand[0].getValue() == 1 && sortedHand[1].getValue() == 1 && sortedHand[2].getValue() != 1 && sortedHand[3].getValue() != 1 && sortedHand[4].getValue() != 1);	
	}

	/** 
	 * A method to check for two pairs
	 * @return boolean for the combination
	 */
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

	/** 
	 * A method to check for a set or also known as three of a kind
	 * @return boolean for the combination
	 */
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

	/** 
	 * A method to check for a straight using a separate method
	 * @return boolean for the combination from another method
	 */
	public boolean isStraight(){
		return allCardsAreConsecutiveIn(valueSortedHand());
	}

	/** 
	 * A method to check for a flush using a separate method
	 * @return boolean for the combination from another method
	 */
	public boolean isFlush(){
		return firstAndLastCardAreSameSuitIn(suitSortedHand());
	}

	/** 
	 * A method to check for a full house
	 * @return boolean for the combination
	 */
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

	/** 
	 * A method to check for a four of a kind
	 * @return boolean for the combination
	 */
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
	
	/** 
	 * A method to check for a straight flush
	 * @return boolean for the combination
	 */
	public boolean isStraightFlush(){
		return isStraight() && isFlush();
	}


	/** 
	 * A getter for the hand
	 * @return an array of cards
	 */
	public Card[] getHand() {
		return this.hand;
	}

	/**
	 * A getter for the sorted hand
	 * @return an array of sorted cards
	 */
	public Card[] getSortedHand() {
		return this.sortedHand;
	}
	
	/**
	 * A method to check for a win
	 * @return a boolean of true for a win and false for a no win
	 */
	public boolean wins(){
		return this.getScore() != HandValue.NO_WIN;
	}
	
	/**
	 * A method to get the multiplier value for the hand
	 * @return a double as a multiplier
	 */
	public double worth(){
		return this.getScore().getMultiplier();
	}
	
	/**
	 * A method to swap cards at specific indexes
	 * @param indexes : an arraylist of integers to determine which cards to swap from which indexes
	 * @return an array of cards with the swapped cards
	 */
	public Card[] swapCards (ArrayList<Integer> indexes) {
		for(int i : indexes) {
			this.hand[i] = currentDeck.nextCard();
		}
		return this.hand;
	}

	/**
	 * A default method for getting a string version of the hand
	 * @return a string with the hand stringified
	 */
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


	/**
	 * 
	 * @return
	 */
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

	/**
	 * A method to check if all the cards are consecutive in the hand
	 * @param hand : an array of cards
	 * @return a boolean for the consecutiveness
	 */
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

	/**
	 * A method to check for an ace high straight where the ace would count as the value 14 instead of 1
	 * @return a boolean for the possibility
	 */
	private boolean possiblyAceHighStraight(){
		return hasAce() && secondValueIs10();
	}

	/**
	 * A method to check for an ace in the hand
	 * @return a boolean for the ace
	 */
	private boolean hasAce(){
		return sortedHand[0].getValue() == 1;
	}

	/**
	 * A method to check if the second card has a value of 10 in the sorted hand which would mean the rest of the hand could be a straight
	 * @return a boolean for the value 10 in second slot
	 */
	private boolean secondValueIs10(){
		return sortedHand[1].getValue() == 10;
	}

	/**
	 * A method to check if the given cards are consecutive
	 * @param first : a card to compare to
	 * @param second : a card to compare with
	 * @return a boolean with the result
	 */
	private boolean cardsAreNotConsecutive(Card first, Card second){
		return first.getValue() - second.getValue() != 1;
	}

	
	/**
	 * A method to check for suits in the hand
	 * @param hand : an array of cards
	 * @return a boolean with the result
	 */
	private boolean firstAndLastCardAreSameSuitIn(Card[] hand){
		return hand[0].getSuit() == hand[hand.length-1].getSuit();
	}
}