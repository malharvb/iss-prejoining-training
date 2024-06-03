package javapractice.jdbcdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCLogger {

	private static Logger fileLogger = LogManager.getLogger(JDBCDemo.class.getName());
	
	public static void info(String msg) {
		fileLogger.info(msg);
	}
	
}
