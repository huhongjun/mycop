package com.gever.jdbc.connection.impl;


/**
 * 代码走查 2004-05-28 15：19 zhouen
 *
 */

import java.sql.*;
import java.util.Properties;

import com.gever.util.log.Log;
import com.gever.exception.DefaultException;
import com.gever.jdbc.connection.*;

public class JDBCConnectionProvider
    implements ConnectionProvider {
  private Properties param;
//  Connection conn = null;
  String driver = "";
  String url = "";
  String user = "";
  String pass = "";
  private static final Log log = Log.getInstance(DBCPConnectionProvider.class);

  public void configure(Properties props) throws
      DefaultException {
    if (props.get(ConnectionParam.DRIVER) == null) {
      String msg = "db driver is missing";
      log.showLog(msg);
      throw new DefaultException(msg);
    }
    if (props.get(ConnectionParam.URL) == null) {
      String msg = "db URL is missing";
      log.showLog(msg);
      throw new DefaultException(msg);
    }
    url = (String) props.get(ConnectionParam.URL);
    driver = (String) props.get(ConnectionParam.DRIVER);
    user = (String) props.get(ConnectionParam.USER);
    pass = (String) props.get(ConnectionParam.PASSWORD);

  }

  /**
   * Grab a connection
   * @return a JDBC connection
   * @throws com.gever.exception.DefaultException
   */
  public java.sql.Connection getConnection() throws
      DefaultException {
    try {
      Class.forName(driver).newInstance();
      return DriverManager.getConnection(url, user, pass);

    }
    catch (Exception ex) {
      throw new DefaultException(ex);
    }
  }

  /**
   * Dispose of a used connection.
   * 关闭CONN连接
   * @throws com.gever.exception.DefaultException
   */
  public void close() throws DefaultException {

  }

}