package org.sakaiproject.topicstore.tool.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBLocalConnection {

	private static Log logger = LogFactory.getLog(DBLocalConnection.class);

	private static String driverClass;

	private static String url;

	private static String userName;

	private static String passWord;
	
	static {
		try {
			getMvendor();
			Class.forName(driverClass);
			logger.info("*******Class.forName()*******");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public static void getMvendor() {
		Properties m_props = getProperties("linkcon.properties");
		driverClass = m_props.getProperty("DRIVER_CLASS");
		url = m_props.getProperty("URL");
		userName = m_props.getProperty("USERNAME");
		passWord = m_props.getProperty("PASSWORD");
	}

	public static Properties getProperties(String propertyFileName) {
		String sakaiHomePath = getSakaiHomePath();
		Properties m_props = null;
		try {
			File f = new File(sakaiHomePath + propertyFileName);
			if (f.exists()) {
				m_props = new Properties();
				m_props.load(new FileInputStream(f));
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage() + t);
		}
		return m_props == null ? null : m_props;
	}
	
//	 find a path to sakai files on the app server - if not set, set it
	public static String getSakaiHomePath() {
		String sakaiHomePath = System.getProperty("sakai.home");
		if (sakaiHomePath == null) {
			String catalina = getCatalina();
			if (catalina != null) {
				sakaiHomePath = catalina + File.separatorChar + "sakai"
						+ File.separatorChar;
			}
			return sakaiHomePath;
		}
		return sakaiHomePath;
	}

	/**
	 * Check the environment for catalina's base or home directory.
	 * 
	 * @return Catalina's base or home directory.
	 */
	protected static String getCatalina() {
		String catalina = System.getProperty("catalina.base");
		if (catalina == null) {
			catalina = System.getProperty("CATALINA.HOME");
		}
		return catalina;
	}

	/**
	 * 
	 */
	public DBLocalConnection() {
		logger.info("*******Class.forName*******");
	}

	/**
	 * getConnection
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * Close Connection
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (null != conn) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
}
