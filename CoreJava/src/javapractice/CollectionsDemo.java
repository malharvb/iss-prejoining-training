/*
 * Program to demonstrate the usage of collections framework in Java using both primitive and reference types
 */
package javapractice;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Car implements Comparable<Car> {
	private String company;
	private String model;
	private int year;

	public Car(String company, String model, int year) {
		this.company = company;
		this.model = model;
		this.year = year;
	}

	public String getMake() {
		return company;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		return "Car: " + "company='" + company + '\'' + ", model='" + model + '\'' + ", year=" + year;
	}

	// Implement the compareTo method for ascending ordering by year
	public int compareTo(Car other) {
		// Here Integer.compare(this.year, other.year)
		if (this.year > other.year) {
			return 1;
		} else if (this.year < other.year) {
			return -1;
		} else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Car))
			return false;
		Car car = (Car) obj;
		return year == car.year && company.equals(car.company) && model.equals(car.model);
	}

	public int hashCode() {
		return Objects.hash(company, model, year);
	}
}

// Comparator for comparing cars by company
class CarMakeComparator implements Comparator<Car> {
	public int compare(Car car1, Car car2) {
		return car1.getMake().compareTo(car2.getMake());
	}
}

// Comparator for comparing cars by model
class CarModelComparator implements Comparator<Car> {
	public int compare(Car car1, Car car2) {
		return car1.getModel().compareTo(car2.getModel());
	}
}

public class CollectionsDemo {
	
	// Logging setup
	
	private static Logger fileLogger = LogManager.getLogger(CollectionsDemo.class.getName());
	
	public static void main(String[] args) {
		
		// Collections used with primitive variables
		
		// Immutable list of numbers
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		int numbersLength = numbers.size();
		int sumOfNumbers = 0;
		
		for (int curIndex = 0; curIndex < numbersLength; curIndex++) {
			sumOfNumbers += numbers.get(curIndex);
		}
		
		fileLogger.info("List of numbers: " + numbers.toString());
		fileLogger.info("Sum of numbers: " + sumOfNumbers);
		
		// Collections used with objects
		Car car1 = new Car("Maruti Suzuki", "Swift", 2018);
		Car car2 = new Car("Hyundai", "i20", 2020);
		Car car3 = new Car("Tata", "Nexon", 2019);
		Car car4 = new Car("Mahindra", "XUV500", 2021);
		Car car5 = new Car("Honda", "City", 2017);
		Car car6 = new Car("Maruti Suzuki", "Swift", 2018);

		// Collection: List: ArrayList
		// Mutable list of objects
		ArrayList<Car> carList = new ArrayList<>(Arrays.asList(car1, car2, car3, car4));

		// Collection methods
		carList.add(car5);
		
		carList.remove(car5);
		
		carList.add(car5);

		fileLogger.info("Original List:");
		for (Car car : carList) {
			fileLogger.info(car);
		}

		// Sorting the list using natural order (by year)
		Collections.sort(carList);
		fileLogger.info("List Sorted by Year:");

		// Using Iterators to loop over Collections
		Iterator<Car> listIterator = carList.iterator();
		while (listIterator.hasNext()) {
			fileLogger.info(listIterator.next());
		}

		// Sorting the list using a comparator (by company)
		Collections.sort(carList, new CarMakeComparator());
		fileLogger.info("List Sorted by Make:");
		for (Car car : carList) {
			fileLogger.info(car);
		}

		// Collection: Set: HashSet
		Set<Car> carSet = new HashSet<>(carList); 
		fileLogger.info("HashSet with Unique Cars:");
		

		// Adding an object which already exists does not modify the HashSet
		// HashSet will use the overridden equals and hashCode methods
		carSet.add(car6);
		
		for (Car car : carSet) {
			fileLogger.info(car);
		}

		HashMap<String, Car> carMap = new HashMap<>();
		carMap.put(car1.getMake(), car1);
		carMap.put(car2.getMake(), car2);
		carMap.put(car3.getMake(), car3);
		carMap.put(car4.getMake(), car4);
		carMap.put(car5.getMake(), car5);

		fileLogger.info("HashMap of Cars:");
		for (String key : carMap.keySet()) {
			fileLogger.info(key + " -> " + carMap.get(key));
		}

		// Some other use-cases of overridden equals and hashCode
		
		fileLogger.info("Car6 equals Car1: " + car6.equals(car1));
		fileLogger.info("Is Car6 in the HashSet: " + carSet.contains(car6));
	}
}

