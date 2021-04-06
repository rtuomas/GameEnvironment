package view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javafx.scene.control.Button;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class OBSbuttonTest {
	
//	private GSObservable observable;
//	private GSObserverBtn observer;
//	private Button btn;
//	
//	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
//	@Test
//    public void testStart() {
//		observable = new GSObservable();
//		btn = new Button();
//		observer = new GSObserverBtn(btn);
//		observable.addPropertyChangeListener(observer);
//		observable.setGameState("start");
//		assertEquals(true, btn.isDisabled(),"button should be disabled");
//    }
//	
//	@Test
//	public void testWin() {
//		observable = new GSObservable();
//		btn = new Button();
//		btn.setDisable(true);
//		observer = new GSObserverBtn(btn);
//		observable.addPropertyChangeListener(observer);
//		observable.setGameState("win");
//		assertEquals(false, btn.isDisabled(),"button should not be disabled");
//	}
}
