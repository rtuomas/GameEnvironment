package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GSObservable {
	
	private PropertyChangeSupport support;
	private String gameState;
	
	public GSObservable () {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	public void setGameState(String state) {
		support.firePropertyChange("gameState", this.gameState, state);
		this.gameState = state;
	}
}
