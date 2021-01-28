package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The player class of the game. Player has many options to choose from. It controls what to do.
 * @author Tuomas Rajala
 * @version 1.0 26.01.2021
 */
public class Player {
	//Players name
	private String name;
	//Players cash
	private int cash;
	//Players bet (starting with 1.00)
	private double bet;
	
	//List taken from Veikkaus JokeriPokeri game
	private Double[] betTable = {0.1, 0.2, 0.4, 0.5, 0.6, 0.8, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 10.0};
	
	public Player(String name, int cash) {
		this.name = name;
		this.cash = cash;
		this.bet = betTable[6]; //Default for 1.0 € bet
	}
	
	public void play() {
		
	}
	
	/**
	 * Increases the bet by certain ammount
	 */
	public void increaseBet() {
		if(this.bet!=10.0) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)+1];
		}
	}
	
	/**
	 * Decreases the bet by certain ammount
	 */
	public void decreaseBet() {
		if(this.bet!=0.1) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)-1];
		}
	}
	
	
	public void holdCards() {
		
	}
	
	public void doubleWin() {
		
	}
	
	public void collectWin() {
		
	}
}
