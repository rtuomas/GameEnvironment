package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Game state observable to help user interface components to react differently during game states.
 * @author Tatu Nordström
 * @version 1.0 5.5.2021
 */
public class GSObservable {
	/** Support for bound properties  */
	private PropertyChangeSupport support;
	/** Current game state*/
	private String gameState;
	
	/**
	 * Default constructor, initialaizes PropertyChangeSupport.
	 */
	public GSObservable () {
		support = new PropertyChangeSupport(this);
	}
	
	/**
	 * Adds PropertyChangeListener to the listener list.
	 * @param pcl listener to be added
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Removes PropertyChangeListener from the listener list.
	 * @param pcl listener to be removed
	 */
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	/**
	 * Reports game state update for listeners.
	 * @param state game state
	 */
	public void setGameState(String state) {
		support.firePropertyChange("gameState", this.gameState, state);
		this.gameState = state;
	}
}
