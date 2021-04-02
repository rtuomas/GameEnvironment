package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Button;

public class GSObserverBtn implements PropertyChangeListener {
	
	private Button btn;
	
	public GSObserverBtn (Button btn) {
		this.btn = btn;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch((String) evt.getNewValue()) {
			case "start": disableButton();
				break;
			case "win" : enableButton();
				break;
		}
	}
	
	private void disableButton () {
		btn.setDisable(true);
	}
	
	private void enableButton () {
		btn.setDisable(false);
	}
}
