package model;

import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Singleton resource factory for languange switching. 
 * @author Tatu Nordström
 * @version 1.0 5.5.2021
 */
public class ObservableResourceFactory {
	/**ObjectProprty for resource bundle */
	private ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();
	/** Instance of the class */
	private static ObservableResourceFactory INSTANCE;
	
	/**
	 * Private constructor.
	 */
	private ObservableResourceFactory () {}
	
	/**
	 * Creates class instance. If class is already created, returns instance.
	 * @return instance of the class
	 */
	public static ObservableResourceFactory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ObservableResourceFactory ();
		}
		return INSTANCE;
	}
	
	/**
	 * Getter for resource bundle ObjectProperty.
	 * @return ObjectProperty resource bundle
	 */
	public ObjectProperty<ResourceBundle> resourcesProperty() {
		return resources ;
	}
	
	/**
	 * Getter for ResourceBundle.
	 * @return current ResourceBundle
	 */
	public final ResourceBundle getResources() {
		return resourcesProperty().get();
	}
	
	/**
	 * Sets ObjectProperty resource bundle.
	 * @param resources to be setted
	 */
	public final void setResources(ResourceBundle resources) {
		resourcesProperty().set(resources);
	}

	/**
	 * Finds key from resources and creates StringBinding for given key.
	 * @param key to find from language resources.
	 * @return StringBinding for given key.
	 */
	public StringBinding getStringBinding(String key) {
		return new StringBinding() {
			{ bind(resourcesProperty()); }
			@Override
			public String computeValue() {
				return getResources().getString(key);
			}
		};
	}
}

