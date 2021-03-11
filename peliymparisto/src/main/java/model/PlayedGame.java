package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about a played game, has JPA annotations for hibernate to use
 * @author Aki Koppinen
 * @version 1.3 18.02.2021
 */
@Entity
@Table(name="playedgame")
public class PlayedGame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column
	private int player1;
	@Column
	private int player2;
	@Column
	private String gametype;
	@Column
	private int winner;
	@Column
	private double creditChange;
	@Column
	private double creditAfterPlayer1;
	@Column
	private double creditAfterPlayer2;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date playedOn;
	
	public PlayedGame() {
		super();
	}
	
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
	
	public PlayedGame(double credits, Date playedOn) {
		super();
		this.creditAfterPlayer1 = credits;
		this.playedOn = playedOn;
	}
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayer1() {
		return player1;
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer2() {
		return player2;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	public String getGametype() {
		return gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public double getCreditChange() {
		return creditChange;
	}

	public void setCreditChange(double creditChange) {
		this.creditChange = creditChange;
	}

	public Date getPlayedOn() {
		return playedOn;
	}

	public void setPlayedOn(Date playedOn) {
		this.playedOn = playedOn;
	}

	public double getCreditAfterPlayer1() {
		return creditAfterPlayer1;
	}

	public void setCreditAfterPlayer1(double creditAfterPlayer1) {
		this.creditAfterPlayer1 = creditAfterPlayer1;
	}

	public double getCreditAfterPlayer2() {
		return creditAfterPlayer2;
	}

	public void setCreditAfterPlayer2(double creditAfterPlayer2) {
		this.creditAfterPlayer2 = creditAfterPlayer2;
	}

	public String toString() {
		return this.id + " " + this.player1 + " " + this.player2 + " " + this.gametype + " " + this.winner + 
				" " + this.creditChange + " " + this.creditAfterPlayer1 + " " + this.creditAfterPlayer2 + " " + this.playedOn;
	}

}
