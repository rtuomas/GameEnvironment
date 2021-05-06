package model;

import java.util.Date;

/**
 * Additional class for played game table on statistics needed to create
 * because of hibernates stiffness.
 * @author Tuomas Rajala
 * @version 2.3 10.03.2021
 */
public class PlayedGameTableView {
	
	private int id;
	private String creditAfter, creditChange;
	private Date playedOn;
	private String winloss;

	public PlayedGameTableView(int id, String creditAfter, String creditChange, Date playedOn, String winloss2) {
		this.id = id;
		this.creditAfter = creditAfter;
		this.creditChange = creditChange;
		this.playedOn = playedOn;
		this.winloss = winloss2;
		
	}

	public int getRank() {
		return id;
	}

	public void setRank(int id) {
		this.id = id;
	}

	public String getCreditAfter() {
		return creditAfter;
	}

	public void setCreditAfter(String creditAfter) {
		this.creditAfter = creditAfter;
	}

	public Date getPlayedOn() {
		return playedOn;
	}

	public void setPlayedOn(Date playedOn) {
		this.playedOn = playedOn;
	}

	public String getWinloss() {
		return winloss;
	}

	public void setWinloss(String winloss) {
		this.winloss = winloss;
	}

	public String getCreditChange() {
		return creditChange;
	}

	public void setCreditChange(String creditChange) {
		this.creditChange = creditChange;
	}


}