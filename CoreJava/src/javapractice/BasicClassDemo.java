/*
 * Program for demonstrating the working of classes and objects
 */

package javapractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class CarPartBasic {
	
	// Instance variables (Fields)
    private String partNumber;
    private String manufacturer;
    private double price;

    // Custom constructor
    public CarPartBasic(String partNumber, String manufacturer, double price) {
        this.partNumber = partNumber;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    // Getter and Setter Methods
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    // Custom Class Method (Behavior) for logging class information
    public void logPartInfo(Logger logger) {
    	logger.info("Car part number: " + partNumber);
    	logger.info("Car part manufacturer: " + manufacturer);
    	logger.info("Car part price: " + price);
    }

}


public class BasicClassDemo {
	
	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(BasicClassDemo.class.getName());

	public static void main(String[] args) {
		
		// Instantiating the class / Creating object
		CarPartBasic tire = new CarPartBasic("A100", "MRF", 2000);
		
		// Invoking custom class method using object
		tire.logPartInfo(fileLogger);

	}

}
