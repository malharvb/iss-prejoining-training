package javapractice;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

// Polymorphism: Interfaces allow each class which implements its methods to have their own internal workings 
// In this way interfaces provide "one interface, many forms"

interface Item {
	public double getPrice();
	public void setPrice(double price);
}

// Encapsulation: Restricting access to variables by using appropriate access modifiers in the CarPart class 
// Abstraction: Complexity is hidden by only allowing interaction with certain methods and use of abstract class

abstract class CarPart implements Item {
	
	// Instance Variables
	private String partName;
	private double price;

	// Custom constructor
	public CarPart(String partName, double price) {
		this.partName = partName;
		this.price = price;
	}

	// Getter and Setter methods
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Abstract method to be implemented by derived classes
	public abstract void getPartUseInfo(Logger fileLogger);
}

// Inheritance: Extending already existing classes to make use of their variables and methods by inheriting them
// Inheritance is also used to group classes in a hierarchical manner

class Engine extends CarPart {
	private int horsepower;

	public Engine(String partName, double price, int horsepower) {
		super(partName, price);
		this.horsepower = horsepower;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	// Overriding the abstract method
	public void getPartUseInfo(Logger fileLogger) {
		fileLogger.info("Usage Info: The engine converts fuel into mechanical power");
	}
}

class Tire extends CarPart {
	private int size;

	public Tire(String partName, double price, int size) {
		super(partName, price);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	// Method overloading
	public void setSize(int size) {
		this.size = size;
	}

	public void setSize(double size) {
		this.size = (int) Math.round(size);
	}

	// Overriding the abstract method
	public void getPartUseInfo(Logger fileLogger) {
		fileLogger.info("Usage Info: The tire allows the vehicle to move");
	}
}

class Battery extends CarPart {
	private int capacity;

	public Battery(String partName, double price, int capacity) {
		super(partName, price);
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Overriding the abstract method
	public void getPartUseInfo(Logger fileLogger) {
		fileLogger.info("Usage Info: The battery stores and supplies electrical energy");
	}
}


public class OOPConceptsDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(OOPConceptsDemo.class.getName());
	
	public static void main(String[] args) {
		CarPart engine = new Engine("Diesel Engine", 100000, 400);
		Tire tire = new Tire("MRF Tire", 2000, 17);
		CarPart battery = new Battery("Exide Battery", 5000, 600);

		// Polymorphism:
		
		// Method overloading in action (compile-time polymorphism)
		tire.setSize(18);
		fileLogger.info("Tire size set to " + tire.getSize());

		tire.setSize(19.75);
		fileLogger.info("Tire size set to " + tire.getSize());
		
		
		// Method overriding in action using dynamic method dispatch (run-time polymorphism)
		CarPart[] parts = { engine, tire, battery };

		for (CarPart part : parts) {
			fileLogger.info("Part: " + part.getPartName() + ", Price: Rs. " + part.getPrice());
			part.getPartUseInfo(fileLogger);
		}

	}

}
