/*
 * Custom error logger class implementation using log4j
 */
package serverauth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErrorLogger {
	
	private static Logger fileLogger = LogManager.getLogger(ErrorLogger.class.getName());
	
	public static void log(String msg) {
		fileLogger.error(msg);
	}
}
