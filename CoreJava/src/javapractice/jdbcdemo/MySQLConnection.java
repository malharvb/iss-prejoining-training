/*
 * Program to create a connection using BatabaseConfig variables
 */
package javapractice.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static Connection connect() throws SQLException {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			var jdbcUrl = DatabaseConfig.getDbUrl();
			var user = DatabaseConfig.getDbUsername();
			var password = DatabaseConfig.getDbPassword();

			return DriverManager.getConnection(jdbcUrl, user, password);

		} catch (SQLException | ClassNotFoundException ex) {
			JDBCLogger.info(ex.getMessage());
			return null;
		}
	}
}
