package model;
/**
 * Card class for card games.
 * @author Tatu Nordström
 * @version 1.0 5.5.2021
 */

public class Card {
	/** String array for suits */
    final static String[] SUITS = {"H", "D", "C", "S"};
    /** final ints for suits to match array indexes */
    final static int HEART = 0, DIAMOND = 1, CLUB = 2, SPADE = 3;
    /** Card value */
    private int value;
    /** Card suit */
    private int suit;

    /*
     * Default constructor.
     */
    public Card (int suit, int value) {
    	this.suit = suit;
    	this.value = value;
    }
    
    /**
     * Getter for suit.
     * @return card suit
     */
    public int getSuit () {
    	return suit;
    }
    
    /**
     * Getter for card value. 
     * @return card value
     */
    public int getValue () {
    	return value;
    }

    /**
     * Information about card in string format.
     */
    public String toString () {
    	return (value + SUITS[suit]);
    }
}
