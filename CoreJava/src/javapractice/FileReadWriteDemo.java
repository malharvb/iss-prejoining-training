/*
 * Program to demonstrate reading and writing files in Java
 */
package javapractice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class SparePart {
	private String partNumber;
	private String name;
	private double price;

	public SparePart(String name, String partNumber, double price) {
		this.name = name;
		this.partNumber = partNumber;
		this.price = price;
	}

	public String toString() {
		return name + "," + partNumber + "," + price;
	}

	public static SparePart fromStringtoObject(String str) {
		String[] parts = str.split(",");
		return new SparePart(parts[0], parts[1], Double.parseDouble(parts[2]));
	}
}

public class FileReadWriteDemo {

	// Logging setup
	private static Logger fileLogger = LogManager.getLogger(FileReadWriteDemo.class.getName());

	private static final String FILE_PATH = "./outputs/carparts.txt";

	// Write objects into files as comma separated values
	public static void writeSparePartsToFile(List<SparePart> spareParts) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (SparePart part : spareParts) {
				writer.write(part.toString());
				writer.newLine();
			}
		}
	}

	// Read comma separated values into objects
	public static List<SparePart> readSparePartsFromFile() throws IOException {
		List<SparePart> spareParts = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				spareParts.add(SparePart.fromStringtoObject(line));
			}
		}
		return spareParts;
	}

	public static void main(String[] args) {
		List<SparePart> spareParts = new ArrayList<>();
		spareParts.add(new SparePart("BP001", "Brake Pad", 3500));
		spareParts.add(new SparePart("OF002", "Oil Filter", 12000));
		spareParts.add(new SparePart("AF003", "Air Filter", 3000));

		try {
			writeSparePartsToFile(spareParts);

			List<SparePart> readSpareParts = readSparePartsFromFile();
			for (SparePart part : readSpareParts) {
				fileLogger.info(part);
			}
		} catch (IOException e) {
			fileLogger.info("IOException occured");
		}
	}
}
