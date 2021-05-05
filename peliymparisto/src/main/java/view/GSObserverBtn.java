package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;
/**
 * Game state observable button, listens changes in GSObservable and modifyes buttons based on game state.
 * @author Tatu Nordström
 * @version 1.0 5.5.2021
 */
public class GSObserverBtn implements PropertyChangeListener {
	/**Observable button*/
	private Button btn;
	
	/**
	 * Default constructor.
	 * @param btn
	 */
	public GSObserverBtn (Button btn) {
		this.btn = btn;
	}
	
	/**	{@inheritDoc} */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch((String) evt.getNewValue()) {
			case "start": disableButton();
				break;
			case "win" : enableButton();
				break;
		}
	}
	
	/**
	 * Disables button. 
	 */
	private void disableButton () {
		btn.setDisable(true);
	}
	
	/**
	 * Enables button.
	 */
	private void enableButton () {
		btn.setDisable(false);
	}
}
