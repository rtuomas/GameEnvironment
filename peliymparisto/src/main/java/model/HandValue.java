package model;
/**
 * Enum to determine hand values.
 * @author Tatu Nordström
 *
 */
public enum HandValue {
	ACE_PAIR(2.0),
	TWO_PAIRS(4.0),
	THREE_OF_A_KIND(6.0),
	STRAIGHT(12.0),
	FLUSH(16.0),
	FULL_HOUSE(18.0),
	FOUR_OF_A_KIND(40.0),
	STRAIGHT_FLUSH(100.0),
	NO_WIN(0);

	

	private double multiplier;
	/**
	 * Getter for hand multiplier.
	 * @return double multiplier amount.
	 */
	public double getMultiplier () {
		return this.multiplier;
	}
	/**
	 * Constructor. 
	 * @param multiplier determines how valuable hand is.
	 */
	private HandValue (double multiplier) {
		this.multiplier = multiplier;
	}
}