/*
 * Program to understand working of various access modifiers in Java
 */
package javapractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// No modifier or Package private classes can only be accessed in their own package

class CarPartModifierDemo {
	
	// Private variables can only be accessed in their class
    private double price;
    
    // Protected variables can be accessed in their class, package and subclass in their package or different package
    protected String manufacturer;
    
    // Public variables can be accessed from anywhere
    public String partNumber;

    // No modifier or Package private variables can be only accessed from their own package
    String partName;
    
    // Getter / Setter Methods are used to handle variables in a robust manner
    // These types of methods are the only way to modify private variables
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        roundPrice();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    // Protected methods can be accessed in their class, package and subclass in their package or different package
    protected void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Private methods are only accessible from inside their class
    private void roundPrice() {
    	this.price = Math.round(this.price);
    }

}

// Public classes can be accessed from own package and also other packages

public class AccessModifierDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(AccessModifierDemo.class.getName());
	
	public static void main(String[] args) {
		
		CarPartModifierDemo engine = new CarPartModifierDemo();
		
		// Operations that cannot be performed
		// fileLogger.info(engine.price);
		// fileLogger.info(engine.roundPrice());
		
		// Accessing private variables using getter and setter methods
		engine.setPrice(5000.25);
		fileLogger.info("Rounded price is " + engine.getPrice());
		
	}

}
