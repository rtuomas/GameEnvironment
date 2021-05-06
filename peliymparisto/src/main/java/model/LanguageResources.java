package model;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageResources {
	
	private Locale locale;
	private ResourceBundle rb;

    private static final LanguageResources INSTANCE = new LanguageResources();

    private LanguageResources() {
    	locale = new Locale("en", "US");
    	rb = ResourceBundle.getBundle("properties/TextResources", locale);
    }

    public static LanguageResources getInstance() {
        return INSTANCE;
    }
	
    public void setLanguage(String lang, String country) {
    	
    	locale = new Locale(lang, country);
    	Locale.setDefault(locale);
    	rb.clearCache();
		rb = ResourceBundle.getBundle("properties/TextResources", locale);
    }
    
    public ResourceBundle getLanguage() {
    	return rb;
    }
	
}
