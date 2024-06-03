/*
 * Program for demonstrating variables and data types
 * 
 */
package javapractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VariableDataTypeDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(VariableDataTypeDemo.class.getName());

	public static void main(String[] args) {
		
		// 1. Primitive Data Types
		
		// 1.1 Numbers
		
		// 1.1.a. Integer type	
		long demoLong = 9000000; // 64 bit
		int demoInteger = 8000; // 32 bit
		short demoShort = 4000; // 16 bit
		byte demoByte = 100; // 8 bit
		
		// 1.1.b. Floating point type
		float demoFloat = 10.24f; // 64 bit
		double demoDouble = 55.256; // 32 bit
		
		// 1.2. Character
		char demoChar = 'm'; // 16 bit Unicode
		char demoEscapedChar = '\n';
		
		// 1.3. Boolean
		boolean demoBool = true; // or false
		
		// 2. Scope of variables
		
		if (demoInteger == 8000) {
			int scopedInteger = 900;
		}
		
		// scopedInteger = 200;
		// Cannot access scopedInteger as its lifetime is limited to the if block 
		
		// 3. Type conversion - Widening
		// In the expression below 128 is within byte range and also within int range 
		// Hence can be automatically type converted
		
		int typeConvertedInt = 128;
		
		// 4. Type casting
		
		// 4.1. Narrowing
		byte typeCastedByte = (byte) demoInteger; // typeCastedByte = demoIntger % 256 (Range of byte)
		
		// 4.2. Truncating
		int typeCastedInt = (int) demoDouble; // Remove floating point and narrow if double value if required
		
		// 5. Non-Primitive Data Types (Reference)
		
		// Objects, arrays and enumerations are some reference type variables in Java
		// Strings are objects in Java, although they are immutable in nature
		String demoStr = new String("Hello Malhar");
		
		String demoStrLiteral = "Hello Malhar";
		
		// The equals method is used to compare two strings. The equalsIgnoreCase can also be used.
		// Using == results in comparison of the String references in memory 
		if (demoStr.equals(demoStrLiteral)) {
			fileLogger.info("The two strings " + demoStr + " and " + demoStrLiteral + " are equal in value");
		}
		
		// The charAt method returns the char at the given index
		fileLogger.info("The character at index 6 is " + demoStr.charAt(6));
		
		// The split method is used to split a string into substrings based on the provided regular expression
		String[] substrings = demoStr.split(" ");
		
		fileLogger.info("The substrings in " + demoStr + " by splitting at blank spaces:");
		
		for (String substring: substrings) {
			fileLogger.info(substring);
		}
		
		// Arrays and their uses have been demonstrated in another file
	}
}
