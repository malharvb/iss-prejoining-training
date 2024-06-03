/*
 * Program to demonstrate various loops
 */
package javapractice;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoopingDemo {
	
	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(LoopingDemo.class.getName());

	public static void main(String[] args) {
		
		// Code to find the sum of all the numbers in an array using for loop
		int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		int numbersLength = numbers.length;
		
		int sum = 0;
		
		for (int curIndex = 0; curIndex < numbersLength; curIndex++) {
			sum += numbers[curIndex];
		}
		
		fileLogger.trace("Array of numbers: " + Arrays.toString(numbers));
		fileLogger.trace("Calculated sum using for loop: " + sum);
		
		
		// Code to print numbers from 1 to 10 using while loop
		int currentNum = 1;
		
		fileLogger.trace("The numbers from 1 to 10: ");
		
		while (currentNum < 11) {
			fileLogger.trace(currentNum);
			
			currentNum++;
		}
		
		
	}

}
