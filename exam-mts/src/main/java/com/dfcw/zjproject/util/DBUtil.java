/*
 * Created on Mar 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DataBase Utilities - provide basic database access and implement DB dependent
 * process, such as driver loading, etc.
 */
public class DBUtil {

	private static DataSource ds = null;
/*
	static {
		try {
			System.out.println("start");
			InitialContext ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/connectDB");
			System.out.println(ds);

			Context ctx = new InitialContext();
			Context c = (Context)ctx.lookup("java:comp/env");
			ds = (DataSource)c.lookup("jdbc/connectDB");

		} catch (NamingException ne) {
			//Debug.log("NamingException when lookup DataSource "+ne);

			ne.printStackTrace();
		}
	}
*/
	/**
	 * get a connection for database access according to configuration for
	 * database type.
	 */
/*
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			if (ds != null) {
				conn = ds.getConnection();				
			} else {
				SQLException sqlexcep = new SQLException(
						"Can't get connection from DataSource");
				throw sqlexcep;
			}
		} catch (SQLException se) {
			//Debug.log("in [DBUtil] "+se);
			se.printStackTrace();
			throw se;
		}

		return conn;
	}
	
			

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
		    String url = Config.getProperty("db.url");
		    String username = Config.getProperty("db.username");
		    String password = Config.getProperty("db.password");			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception s) {
			s.printStackTrace();
		}
		return conn;
	}
*/
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
		    String url = Config.getProperty("db.url");
		    String username = Config.getProperty("db.username");
		    String password = Config.getProperty("db.password");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
		    String url = "jdbc:mysql://localhost:3306/ppmdb";
		    String username = "root";
		    String password = "99999999";	
		    */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception s) {
			s.printStackTrace();
		}
		return conn;
	}	
	
	
	
	
	
	
	
	
	/**
	 * Add \ at beginning of each '.
	 * 
	 * @param s
	 *            string.
	 * @return new escaped string.
	 */
	public static String escape(String input) {
		if (input == null)
			return "";

		int length = input.length();

		StringBuffer sb = new StringBuffer(length);

		for (int i = 0; i < length; i++) {
			char character = input.charAt(i);

			if (character == '\'')
				sb.append("\''");
			else
				sb.append(character);
		}
		return sb.toString();
	}
}