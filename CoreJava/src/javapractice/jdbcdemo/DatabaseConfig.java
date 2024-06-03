/*
 * Program to get config variables from configure.properties file
 */
package javapractice.jdbcdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

	private static final Properties properties = new Properties();

	
	// Since variables are static it is more fitting to use static block instead of contructors for initialization
	static {
		try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("configure.properties")) {
			if (input == null) {
				JDBCLogger.info("Unable to find config.properties");
			}

			// Load the properties file
			properties.load(input);
		} catch (IOException ex) {
			JDBCLogger.info("IOException occured");
		}
	}

	public static String getDbUrl() {
		return properties.getProperty("db.url");
	}

	public static String getDbUsername() {
		return properties.getProperty("db.username");
	}

	public static String getDbPassword() {
		return properties.getProperty("db.password");
	}
}