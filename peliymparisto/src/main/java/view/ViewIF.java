package view;

import java.util.ArrayList;

/**
 * The interface for the GUI. The Controller handles GUI related traffic through this interface.
 * @author ---
 * @version 1.0 26.01.2021
 */
public interface ViewIF {
	
	//getters and setters for transporting requests and responses between GUI and game engines
	/**
	 * Sends bet size through controller to model.
	 * @return double bet size.
	 */
	public double getBet();
	/**
	 * Sends player name through controller to model.
	 * @return string player name.
	 */
	public String getPlayer();
	/**
	 * Shows the cards in view.
	 * @param cards ArrayList<String> dealed cards.
	 */
	public void setCards(ArrayList<String> cards);
	/**
	 * Display game score in view.
	 * @param score string game score.
	 */
	public void setScore(String score);
	/**
	 * Sets credits in view after game.
	 * @param credits double credit amount.
	 */
	public void setCredits(double credits);
	
}
