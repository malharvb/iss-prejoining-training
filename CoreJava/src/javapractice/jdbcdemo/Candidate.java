/*
 * Program to demonstrate various Statements provided by JDBC
 */
package javapractice.jdbcdemo;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Candidate {

	// Statement for querying
	public static void getAll() {
		String sql = "SELECT first_name, last_name, email FROM candidate LIMIT 5";

		try (var conn = MySQLConnection.connect(); 
			 var stmt = conn.createStatement(); 
			 var rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				JDBCLogger.info(
						rs.getString("first_name") + "\t" + 
						rs.getString("last_name") + "\t" + 
						rs.getString("email")
				);
			}
		} catch (SQLException ex) {
			JDBCLogger.info(ex.getMessage());
		}
	}

	// Prepared statement
	public static void changeEmail(int id, String email) {
		var sql = "UPDATE candidate SET email = ? WHERE id = ?";

		try (var conn = MySQLConnection.connect(); 
			 var stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, email);
			stmt.setInt(2, id);

			int rowAffected = stmt.executeUpdate();
			JDBCLogger.info("Row affected " + rowAffected);
		} catch (SQLException ex) {
			JDBCLogger.info(ex.getMessage());
		}
	}

	public static int add(String firstName, String lastName, Date dob, String email, String phone) {
		int id = 0;

		String sql = "INSERT INTO candidate(first_name,last_name,dob,phone,email) VALUES(?,?,?,?,?)";

		try (var conn = MySQLConnection.connect();
			 var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setDate(3, dob);
			stmt.setString(4, phone);
			stmt.setString(5, email);

			int rowAffected = stmt.executeUpdate();
			if (rowAffected == 1) {
				var rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException ex) {
			JDBCLogger.info(ex.getMessage());
		}

		return id;
	}

	// Callable Statement
	public static ArrayList<String> getSkills(int candidateId) {

		var query = "{ call get_candidate_skill(?) }";
		var skills = new ArrayList<String>();

		try (var conn = MySQLConnection.connect(); 
			 var stmt = conn.prepareCall(query);) {

			stmt.setInt(1, candidateId);

			try (var rs = stmt.executeQuery()) {
				while (rs.next()) {
					skills.add(rs.getString("skill"));
				}
			} catch (SQLException ex) {
				JDBCLogger.info(ex.getMessage());
			}

		} catch (SQLException ex) {
			JDBCLogger.info(ex.getMessage());
		}

		return skills;
	}
}
