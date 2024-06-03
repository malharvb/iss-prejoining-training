/*
 * Class to implement User Data Access Object for isolating database operations from business logic
 */
package serverauth.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.mindrot.jbcrypt.BCrypt;

import serverauth.ErrorLogger;
import serverauth.model.UserModel;


public class UserDao {
	
	// Function to obtain MySQL database connection
	public static Connection connect() throws SQLException {
		final Properties properties = new Properties();

		try (InputStream input = UserDao.class.getClassLoader().getResourceAsStream("configure.properties")) {
			if (input == null) {
				ErrorLogger.log("Unable to find config.properties");
			}

			// Load the properties file
			properties.load(input);
		} catch (IOException e) {
			ErrorLogger.log("IOException occured");
		}
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			var jdbcUrl = properties.getProperty("db.url");
			var user = properties.getProperty("db.username");
			var password = properties.getProperty("db.password");

			return DriverManager.getConnection(jdbcUrl, user, password);

		} catch (SQLException | ClassNotFoundException ex) {
			ErrorLogger.log(ex.getMessage());
			return null;
		}
	}
	
	
	// Function to add new user to the database
	public static UserModel addUser(String email, String password) throws SQLException {
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		String sql = "INSERT INTO user_info(acc_email, acc_password) VALUES (?,?)";

		try (var conn = connect();
			 var stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, email);
			stmt.setString(2, hashedPassword);

			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			throw ex;
		}

		UserModel user = new UserModel(email, password);
		return user;
	}
	
	// Function to get already existing user from the database
	public static UserModel getUser(String email) throws SQLException {
		String sql = "SELECT acc_password FROM user_info WHERE acc_email = ?";
		String password = null;
		try (var conn = connect();
			 var stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, email);
			var results = stmt.executeQuery(); 
			
			while (results.next()) {
				password = results.getString("acc_password");
			}
			
		} catch (SQLException ex) {
			throw ex;
		}

		UserModel user = new UserModel(email, password);
		return user;
	}
}
