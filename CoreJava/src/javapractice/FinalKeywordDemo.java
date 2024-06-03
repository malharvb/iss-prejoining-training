/*
 * Program to demonstrate the three uses of final keyword
 */

package javapractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 1. Final class cannot be inherited by other classes
final class CarPartsFinal {
	// 2. Final variable can only be assigned once
	// Here final variable name is in camelCase as it is not static
	public final String partName = "Engine";

	// 3. Final method cannot be overridden
	public final void displayPartDetails(Logger fileLogger) {
		fileLogger.info("Part Name: " + partName);
	}
}

// Trying to subclass CarParts would cause a compilation error
// class EngineParts extends CarPartsFinal {
//	 public EngineParts(String partName) {
//	 	super(partName);
//	 }
//
//	 // Trying to override displayPartDetails would cause a compilation error
//	 public void displayPartDetails(Logger fileLogger) {
//	 	fileLogger.info("This is an engine part.");
//	 }
// }

public class FinalKeywordDemo {
	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(FinalKeywordDemo.class.getName());

	public static void main(String[] args) {
		CarPartsFinal part = new CarPartsFinal();
		part.displayPartDetails(fileLogger);

		// Trying to change the final variable would cause a compilation error
		// part.partName = "Transmission";

	}

}
