package model;

public class PlayerRanking {
	
	private int rank;
	private String profileName;
	private double credits;
	
	public PlayerRanking(int rank, String profileName, double credits) {
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
}
