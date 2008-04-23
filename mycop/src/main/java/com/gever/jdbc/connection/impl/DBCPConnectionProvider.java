package com.gever.jdbc.connection.impl;

import org.apache.commons.dbcp.BasicDataSource;
import com.gever.exception.DefaultException;
import com.gever.jdbc.connection.*;
import com.gever.util.log.Log;

import java.sql.Connection;
import java.util.Properties;

/**
 * A connection provider that uses an Apache commons DBCP connection pool.
 * @see ConnectionProvider
 *
 */
public class DBCPConnectionProvider
    implements ConnectionProvider {

  private BasicDataSource dbs;

  private static final Log log = Log.getInstance(DBCPConnectionProvider.class);

  public Connection getConnection() throws DefaultException {
    try {
      return dbs.getConnection();
    }
    catch (Exception ex) {
      throw new DefaultException(ex);
    }
  }

  public void configure(Properties props) throws DefaultException {
    String jdbcDriverClass = props.getProperty(ConnectionParam.DRIVER);
    String jdbcUrl = props.getProperty(ConnectionParam.URL);
    //Properties connectionProps = ConnectionProviderFactory.getConnectionProperties(props);

    log.showLog("DBCP using driver: " + jdbcDriverClass + " at URL: " + jdbcUrl,
                Log.TYPE_INFO);
    //log.showLog("Connection properties: " + connectionProps,Log.TYPE_INFO );

    if (jdbcDriverClass == null) {
      log.showLog("No JDBC Driver class was specified by property " +
                  ConnectionParam.DRIVER, Log.TYPE_WARN);
    }

    dbs = new BasicDataSource();
    try {
      dbs.setDriverClassName(props.getProperty(ConnectionParam.DRIVER));
      if (props.get(ConnectionParam.URL) == null) {
        throw new DefaultException("db pool prop's RUL is null ");
      }

      dbs.setUrl(props.getProperty(ConnectionParam.URL));
      if (props.get(ConnectionParam.USER) == null) {
        throw new DefaultException("db pool prop's user is null ");
      }
      dbs.setUsername(props.getProperty(ConnectionParam.USER));
      if (props.get(ConnectionParam.PASSWORD) != null) {
        dbs.setPassword(props.getProperty(ConnectionParam.PASSWORD));
      }
      if (props.getProperty(ConnectionParam.MAXACTIVE) != null) {
        dbs.setMaxActive(Integer.parseInt(props.getProperty(ConnectionParam.MAXACTIVE,"10")));
      }
      if (props.get(ConnectionParam.MAXCONN) != null) {
        dbs.setMaxIdle(Integer.parseInt(props.getProperty(ConnectionParam.MAXCONN,"20" )));
      }
      if (props.get(ConnectionParam.MINCONN) != null) {
        dbs.setMinIdle(Integer.parseInt( props.getProperty(ConnectionParam.MINCONN,"1")));
      }
      if (props.get(ConnectionParam.WAITTIME) != null) {
        dbs.setMaxWait(Long.parseLong(props.getProperty(ConnectionParam.WAITTIME,"3000")));
      }
      if ("1".equals(props.getProperty(ConnectionParam.MAMAGETYPE,"0"))) {
        dbs.setRemoveAbandoned(true);
      }else{
        dbs.setRemoveAbandoned(false);
      }
      if("1".equals(props.getProperty(ConnectionParam.PSTYPE ,"0"))){
        dbs.setPoolPreparedStatements(true);
        dbs.setMaxOpenPreparedStatements(Integer.parseInt(props.getProperty(ConnectionParam.PSCOUNT ,"0")));
      }else{
         dbs.setPoolPreparedStatements(false);
       }
    }
    catch (Exception e) {
      log.showLog("could not instantiate DBCP connection pool", Log.TYPE_ERROR);
      throw new DefaultException("Could not instantiate DBCP connection pool",
                                 e);
    }
  }

  /**
   * @see com.gever.jdbc.connection.ConnectionProvider#close()
   */
  public void close() throws DefaultException {
    try {
      if(dbs!=null){
        dbs.close();
        dbs = null;
      }
    }
    catch (Exception e) {
      throw new DefaultException("could not close DBCP pool", e);
    }
  }

}