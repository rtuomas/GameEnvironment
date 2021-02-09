package model;

public enum HandValue {
	ACE_PAIR(1),
	TWO_PAIRS(2.0),
	THREE_OF_A_KIND(2.5),
	STRAIGHT(3.0),
	FLUSH(3.5),
	FULL_HOUSE(5.0),
	FOUR_OF_A_KIND(7.0),
	STRAIGHT_FLUSH(10.0);


	private double multiplier;
	
	public double getMultiplier () {
		return this.multiplier;
	}

	private HandValue (double multiplier) {
		this.multiplier = multiplier;
	}
}