package model;

import java.util.Arrays;

/**
 * This Singleton class consists the player with name and cash. Only one is made in the beginning.
 * @author Tuomas Rajala
 * @version 2.2 28.01.2021
 */
public class Player {
	
	// static variable player of type Singleton 
    private static Player player = null; 
    
	//Players name
	private String name;
	//Players cash
	private int cash;
	
	/**
	 * Constructor when player is registered
	 * @param name Players name
	 * @param cash Players cash
	 */
	private Player(String name, int cash) {
		this.name = name;
		this.cash = cash;
	}
	
	/**
	 * This static method creates the singleton class only once.
	 * @param name Players name
	 * @param cash Players class
	 * @return Player class
	 */
	public static Player getInstance(String name, int cash) { 
        if (player == null) 
        	player = new Player(name, cash); 
  
        return player; 
    }
	
	/**
	 * Constructor when player is not registered.
	 * @param name Players name
	 */
	public Player(String name) {
		this.name = name;
		this.cash = 100;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public String toString() {
		return this.name + ", " + getCash() + " points.";
	}
}
