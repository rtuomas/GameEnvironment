package model;

public class PlayerRanking {
	
	private int rank;
	private String fn, ln;
	private double credits;
	
	public PlayerRanking(int rank, String fn, String ln, double credits) {
		this.rank = rank;
		this.fn = fn;
		this.ln = ln;
		this.credits = credits;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}
}
