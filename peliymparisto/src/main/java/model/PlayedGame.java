package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about a played game, has JPA annotations for hibernate to use
 * @author Aki Koppinen
 * @version 1.4 04.05.2021
 */
@Entity
@Table(name="playedgame")
public class PlayedGame {
	
	/** id for the played game*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	/** id for the player 1*/
	@Column
	private int player1;
	/** id for the player 2*/
	@Column
	private int player2;
	/** Played game's type as a String*/
	@Column
	private String gametype;
	/** Played game's winner's id*/
	@Column
	private int winner;
	/** Amount of credits gained or lost after the game*/
	@Column
	private double creditChange;
	/** Amount of credits for player 1 after the game*/
	@Column
	private double creditAfterPlayer1;
	/** Amount of credits for player 2 after the game*/
	@Column
	private double creditAfterPlayer2;
	/** Timestamp which tells when the game finished, created automatically by the database*/
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date playedOn;
	
	/**
	 * Super constructor for hibernate
	 */
	public PlayedGame() {
		super();
	}
	
	/**
	 * Normal constructor
	 * @param p1
	 * @param p2
	 * @param gt
	 * @param pw
	 * @param cc
	 * @param cap1
	 * @param cap2
	 */
	public PlayedGame(int p1, int p2, String gt, int pw, double cc, double cap1, double cap2) {
		super();
		this.player1 = p1;
		this.player2 = p2;
		this.gametype = gt;
		this.winner = pw;
		this.creditChange = cc;
		this.creditAfterPlayer1 = cap1;
		this.creditAfterPlayer2 = cap2;
	}
	
	/**
	 * Constructor with only credits and playedOn
	 * @param credits
	 * @param playedOn
	 */
	public PlayedGame(double credits, Date playedOn) {
		super();
		
		this.creditAfterPlayer1 = credits;
		this.playedOn = playedOn;
	}
	
	/**
	 * Statistics constructor
	 * @param credits
	 * @param playedOn
	 */
	public PlayedGame(Player player1, 
	                    Player player2, 
	                    String gameType, 
	                    Player winner, 
	                    double creditChange
	                   ){
	    super();
	    this.player1 = player1.getId();
	    this.player2 = player2.getId();
	    this.gametype = gameType;
	    this.winner = winner.getId();
	    this.creditChange = creditChange;
	    this.creditAfterPlayer1 = player1.getCredits();
	    this.creditAfterPlayer2 = player2.getCredits();
	}
	
	/**
	 * @return id of played game
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets id of played game
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return player 1 id
	 */
	public int getPlayer1() {
		return player1;
	}
	
	/**
	 * sets id of player 1
	 * @param player1
	 */
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}
	
	/**
	 * @return player 2 id
	 */
	public int getPlayer2() {
		return player2;
	}
	
	/**
	 * sets id of player 2
	 * @param player2
	 */
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}
	
	/**
	 * @return gametype as String
	 */
	public String getGametype() {
		return gametype;
	}
	
	/**
	 * sets gametype of the played game
	 * @param gametype
	 */
	public void setGametype(String gametype) {
		this.gametype = gametype;
	}
	
	/**
	 * @return winner's id
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * sets winner of the played game
	 * @param winner
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	/**
	 * @return gredit change after the game
	 */
	public double getCreditChange() {
		return creditChange;
	}
	
	/**
	 * sets changed credits
	 * @param creditChange
	 */
	public void setCreditChange(double creditChange) {
		this.creditChange = creditChange;
	}
	
	/**
	 * @return date of the playedgame
	 */
	public Date getPlayedOn() {
		return playedOn;
	}
	
	/**
	 * sets time the game finished
	 * @param playedOn
	 */
	public void setPlayedOn(Date playedOn) {
		this.playedOn = playedOn;
	}
	
	/**
	 * @return player 1's new amount of credits
	 */
	public double getCreditAfterPlayer1() {
		return creditAfterPlayer1;
	}
	
	/**
	 * sets player 1's credits to a new amount
	 * @param creditAfterPlayer1
	 */
	public void setCreditAfterPlayer1(double creditAfterPlayer1) {
		this.creditAfterPlayer1 = creditAfterPlayer1;
	}
	
	/**
	 * @return player 2's new amount of credits
	 */
	public double getCreditAfterPlayer2() {
		return creditAfterPlayer2;
	}
	
	/**
	 * sets player 1's credits to a new amount
	 * @param creditAfterPlayer2
	 */
	public void setCreditAfterPlayer2(double creditAfterPlayer2) {
		this.creditAfterPlayer2 = creditAfterPlayer2;
	}
	
	/**
	 * All information as a String with spaces in between
	 */
	public String toString() {
		return this.id + " " + this.player1 + " " + this.player2 + " " + this.gametype + " " + this.winner + 
				" " + this.creditChange + " " + this.creditAfterPlayer1 + " " + this.creditAfterPlayer2 + " " + this.playedOn;
	}

}
