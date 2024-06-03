/*
 * Program to demonstrate exception handling and custom exception creation
 */

package javapractice;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// Below exception is defined to occur when employee id is greater than the number of employees or negative

class EmpIdOutOfBoundsException extends Exception {
	
	static final long serialVersionUID = 1000;
	
	public String toString() {
		return "Employee ID is incorrect. Valid range of employee ID is from 1 to 50";
	}
}

public class ExceptionDemo {
	
	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(ExceptionDemo.class.getName());
	
	public static void main(String[] args) {
		Scanner inputReader = new Scanner(System.in);
		
		fileLogger.info("Enter employee id: ");
		
		int empId = inputReader.nextInt();
		
		fileLogger.info(empId);
		
		try {
			isEmpIdValid(empId);
		} catch(EmpIdOutOfBoundsException exOb) {
			fileLogger.info(exOb);
		}
		
		inputReader.close();
	}
	
	public static void isEmpIdValid(int empId) throws EmpIdOutOfBoundsException{
		if (empId > 50 || empId < 1) {
			throw new EmpIdOutOfBoundsException();
		}
	}
}
