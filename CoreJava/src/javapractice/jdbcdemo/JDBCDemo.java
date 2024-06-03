/*
 * Demonstration of custom JDBC classes in main function
 */
package javapractice.jdbcdemo;

import java.sql.Date;
import java.sql.SQLException;

public class JDBCDemo {
	public static void main(String[] args) {
		try (var connection = MySQLConnection.connect()) {
			JDBCLogger.info("Connected to the MySQL database");
		} catch (SQLException ex) {
			JDBCLogger.info(ex.getMessage());
		}
		
		Candidate.getAll();
		
		Candidate.changeEmail(1, "amit.sharma2002@gmail.com");

		int id = Candidate.add("Malhar", "Kajale", Date.valueOf("2003-01-17"), "malhar.kajale@gmail.com", "(91) 970004000");
		JDBCLogger.info("The inserted candidate id is " + id);
	
		JDBCLogger.info(Candidate.getSkills(2).toString());
	}
}