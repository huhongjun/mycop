package com.gever.jdbc.connection.impl;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import com.gever.util.log.Log;
import com.gever.exception.DefaultException ;
import com.gever.jdbc.connection.ConnectionParam;
import com.gever.jdbc.connection.ConnectionProvider;

public class JNDIConnectionProvider
    implements ConnectionProvider {
//  Connection conn = null;
  private DataSource ds = null;
  private String user = "";
  private String pass = "";
  private static final Log log = Log.getInstance(DBCPConnectionProvider.class);

  public void configure(Properties props) throws
      DefaultException {

    try {
      String jndiName = props.getProperty(ConnectionParam.JNDI_NAME);
      if (jndiName == null) {
        String msg = "datasource JNDI name was not specified by property " +
            ConnectionParam.JNDI_NAME;
        log.showLog(msg);
        throw new DefaultException(msg);
      }
      user = props.getProperty(ConnectionParam.USER);
      pass = props.getProperty(ConnectionParam.PASSWORD);
      Properties prop = new Properties();
      InitialContext ctx = getInitialContext(prop);
      ds = (DataSource) ctx.lookup(jndiName);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new DefaultException(ex);
    }
  }

  /**
   *
   * @return
   * @throws NamingException
   */
  private InitialContext getInitialContext(Properties props) throws javax.
      naming.NamingException {

    //Hashtable hash=new Hashtable();
    log.showLog("JNDI InitialContext properties:" + props);
    try {
      return (props.size() == 0) ?
          new InitialContext() :
          new InitialContext(props);
    }
    catch (NamingException e) {
      throw e;
    }
  }

  /**
   * Grab a connection
   * @return a JDBC connection
   * @throws SQLException
   */
  public java.sql.Connection getConnection() throws
      DefaultException {
    try {
      if (user != null && pass != null) {
        return ds.getConnection(user, pass);
      }
      return ds.getConnection();
    }
    catch (Exception ex) {
      throw new DefaultException(ex);
    }

  }

  /**
   * Dispose of a used connection.
   * @param conn a JDBC connection
   * @throws SQLException
   */
  public void close() throws DefaultException {
    try {
      ds = null;
    }
    catch (Exception ex) {
      throw new DefaultException(
          "cannot close JNDI Connection.");
    }

  }

}