package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about the player, has JPA annotations for hibernate to use
 * @author Aki Koppinen, Tuomas Rajala
 * @version 2.7 04.05.2021
 */
@Entity
@Table(name="player")
public class Player implements Comparable<Player>{
	
	/** id for the player*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** player's first name*/
	@Column
	private String firstName;
	/** player's last name*/
	@Column
	private String lastName;
	/** player's credits*/
	@Column
	private double credits;
	/** player's profile name*/
	@Column
	private String profileName;
	/** player's email, only one unique value allowed in database*/
	@Column (unique = true)
	private String email;
	/** player's password*/
	@Column
	private String password;
	/** time when player profile was created, automatically given in database*/
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	/**
	 * Super constructor for hibernate
	 */
	public Player() {
		super();
	}
	
	/**
	 * Normal constructor
	 * @param fn
	 * @param ln
	 * @param email
	 * @param pw
	 */
	public Player(String fn, String ln, String email, String pw) {
		super();
		this.firstName = fn;
		this.lastName = ln;
		this.email = email;
		this.password = pw;
		this.credits = 100.0;
		this.profileName = "Player";
	}
	
	/**
	 * Constructor with only first name, last name and credits
	 * @param fn
	 * @param ln
	 * @param credits
	 */
	public Player(String fn, String ln, double credits) {
		super();
		this.firstName = fn;
		this.lastName = ln;
		this.credits = credits;
	}
	
	/**
	 * @return player's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets new id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return player's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets new first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return player's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets new last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return player's credits
	 */
	public double getCredits() {
		return credits;
	}
	
	/**
	 * Sets new amount of current credits
	 * @param credits
	 */
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	/**
	 * @return player's profile name
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * Sets new profile name
	 * @param profileName
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return player's email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets new email
	 * @param emai
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return player's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets new password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return time when player's profile was created
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets new creation time
	 * @param createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	/**
	 * Adds the change to player's current credits
	 * 
	 * Change can be positive or negative
	 * @param change
	 */
	public void alterCredits(Double change){
	    credits += change;
	}
	
	/**
	 * All information about the player as a String with spaces in between
	 */
	public String toString() {
		return this.id + " " + this.firstName + " " + this.lastName + " " + this.credits + " " + this.profileName + " " + this.email + " " + this.password + " " + this.createdOn;
	}
	
	/**
	 * Comparison method
	 */
	@Override
	public int compareTo(Player p) {
		if(credits==p.credits) return 0;
		if(credits>p.credits) return -1;
		return 1;
	}
	
}
