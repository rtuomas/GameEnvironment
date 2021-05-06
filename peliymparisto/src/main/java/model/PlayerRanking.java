package model;

/**
 * Additional class for ranking table on statistics needed to create
 * because of hibernates stiffness.
 * @author Tuomas Rajala
 * @version 2.3 9.03.2021
 */
public class PlayerRanking {
	
	private int rank;
	private String profileName;
	private String credits;
	
	public PlayerRanking(int rank, String profileName, String credits) {
		this.rank = rank;
		this.profileName = profileName;
		this.credits = credits;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
}
