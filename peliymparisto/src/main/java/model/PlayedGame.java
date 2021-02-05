package model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The Object which holds information about a played game, has JPA annotations for hibernate to use
 * @author Aki Koppinen
 * @version 1.0 06.02.2021
 */
@Entity
@Table(name="played_game")
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
	private int creditChange;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date playedOn;
	
	public PlayedGame() {
		super();
	}
	
	public PlayedGame(int p1, int p2, String gt, int pw, int cc) {
		super();
		this.player1 = p1;
		this.player2 = p2;
		this.gametype = gt;
		this.winner = pw;
		this.creditChange = cc;
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

	public int getCreditChange() {
		return creditChange;
	}

	public void setCreditChange(int creditChange) {
		this.creditChange = creditChange;
	}

	public Date getPlayedOn() {
		return playedOn;
	}

	public void setPlayedOn(Date playedOn) {
		this.playedOn = playedOn;
	}

	public String toString() {
		return this.id + " " + this.player1 + " " + this.player2 + " " + this.gametype + " " + this.winner + " " + this.creditChange + " " + this.playedOn;
	}

}
