package model;


public class Card {
	
    final static String[] SUITS = {"Heart", "Diamond", "Club", "Spade"};
    final static int HEART = 0, DIAMOND = 1, CLUB = 2, SPADE = 3;
    
    private int value;            			
    private int suit;

    public Card (int suit, int value) {
    	this.suit = suit;
    	this.value = value;
    }
    
    public int getSuit () {
    	return suit;
    }
    
    public int getValue () {
    	return value;
    }

    public String toString () {
    	return (SUITS[suit] + " " + value);
    }
}
