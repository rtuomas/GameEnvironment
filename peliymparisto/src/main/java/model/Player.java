package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about the player, has JPA annotations for hibernate to use
 * @author Aki Koppinen, Tuomas Rajala
 * @version 2.5 09.02.2021
 */
@Entity
@Table(name="player")
public class Player implements Comparable<Player>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private double credits;
	@Column
	private String profileName;
	@Column (unique = true)
	private String email;
	@Column
	private String password;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	public Player() {
		super();
	}
	
	public Player(String fn, String ln, String email, String pw) {
		super();
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
		this.password = pw;
		this.credits = 100.0;
		this.profileName = "Player";
	}
	
	public Player(String fn, String ln, int credits) {
		super();
		this.firstName = fn;
		this.lastName = ln;
		this.credits = credits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String toString() {
		return this.id + " " + this.firstName + " " + this.lastName + " " + this.credits + " " + this.profileName + " " + this.email + " " + this.password + " " + this.createdOn;
	}

	@Override
	public int compareTo(Player p) {
		if(credits==p.credits) return 0;
		if(credits>p.credits) return -1;
		return 1;
	}
	
}

/*

package model;

import java.util.Arrays;


 * This Singleton class consists the player with name and cash. Only one is made in the beginning.
 * @author Tuomas Rajala
 * @version 2.2 28.01.2021

public class Player {
	
	// static variable player of type Singleton 
    private static Player player = null; 
    
	//Players name
	private String name;
	//Players cash
	private int cash;
	

	 * Constructor when player is registered
	 * @param name Players name
	 * @param cash Players cash

	private Player(String name, int cash) {
		this.name = name;
		this.cash = cash;
	}
	

	 * This static method creates the singleton class only once.
	 * @param name Players name
	 * @param cash Players class
	 * @return Player class

	public static Player getInstance(String name, int cash) { 
        if (player == null) 
        	player = new Player(name, cash); 
  
        return player; 
    }
	

	 * Constructor when player is not registered.
	 * @param name Players name

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

*/
