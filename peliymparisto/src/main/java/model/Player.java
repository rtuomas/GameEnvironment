package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about the player, has JPA annotations for hibernate to use
 * @author Aki Koppinen, Tuomas Rajala
 * @version 2.6 18.02.2021
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
	
	public Player(String fn, String ln, double credits) {
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
	
  public void alterCredits(Double change){
    credits += change;
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
