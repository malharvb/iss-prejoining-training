/*
 * Program to demonstrate conditional branching statements such as if-elseif-else and switch statements
 */
package javapractice;

import org.apache.logging.log4j.*;

import java.util.Scanner;

public class ConditionalBranchingDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(ConditionalBranchingDemo.class.getName());

	public static void main(String[] args) {

		// Code to determine whether a number is even or odd using if-else statements
		Scanner inputReader = new Scanner(System.in);

		fileLogger.trace("Enter a number: ");

		int inputInt = inputReader.nextInt();

		fileLogger.trace(inputInt);

		checkEvenOdd(inputInt);

		inputReader.nextLine();

		// Code to get the current quarter based on the given month as input
		fileLogger.trace("Enter month: ");

		String month = inputReader.nextLine();

		fileLogger.trace(month);

		getCurrentQuarter(month);

		inputReader.close();
	}

	public static void checkEvenOdd(int number) {
		if (number < 0) {
			fileLogger.trace("The number " + number + " is negative.");
		} else if (number % 2 == 0) {
			fileLogger.trace("The number " + number + " is even.");
		} else {
			fileLogger.trace("The number " + number + " is odd.");
		}
	}

	public static void getCurrentQuarter(String month) {
		String quarterLabel = null;
		switch (month) {
		case "jan":
		case "feb":
		case "mar":
			quarterLabel = "Quarter 1";
			break;
		case "apr":
		case "may":
		case "jun":
			quarterLabel = "Quarter 2";
			break;
		case "jul":
		case "aug":
		case "sep":
			quarterLabel = "Quarter 3";
			break;
		case "oct":
		case "nov":
		case "dec":
			quarterLabel = "Quarter 4";
			break;
		default:
			quarterLabel = "Unknown quarter";
		}
		;

		fileLogger.trace("Current quarter is " + quarterLabel);
	}
}
