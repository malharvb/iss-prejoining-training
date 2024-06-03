/*
 * Program to demonstrate working on one and two dimensional arrays
 */
package javapractice;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraysDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(ArraysDemo.class.getName());
	
	public static void main(String[] args) {
		
		// Array declaration
		int[] demoArr;
		
		// Array initialization
		demoArr = new int[5];
		
		// Given a 1D array of weights iterate over it to find the average
		int[] weights = {50, 65, 70, 55, 40};

		float avgWeight = computeAverage(weights);
		
		fileLogger.info("Original weights: " + Arrays.toString(weights));
		fileLogger.info("The average weight is " + avgWeight + " kgs");
		
		// Given a 2D array square each element of the array
		int[][] originalNumbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		
		int[][] squaredNumbers = computeSquare(originalNumbers);
		
		fileLogger.info("Original numbers: " + Arrays.deepToString(originalNumbers));
		fileLogger.info("The squared numbers are " + Arrays.deepToString(squaredNumbers));

	}

	private static float computeAverage(int[] weights) {
		int sum = 0;
		
		for (int weight: weights) {
			sum += weight;
		}
		
		return (float) sum / weights.length;
	}
	
	private static int[][] computeSquare(int[][] originalNumbers) {
		
		int rowLength = originalNumbers.length;
		
		int columnLength = originalNumbers[0].length;
		
		int[][] squaredNumbers = new int[rowLength][columnLength];
		
		for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
			for (int columnIndex = 0; columnIndex < columnLength; columnIndex++) {
				squaredNumbers[rowIndex][columnIndex] = (int) Math.pow(originalNumbers[rowIndex][columnIndex], 2);
			}
		}
		
		return squaredNumbers;
	}
	
}
