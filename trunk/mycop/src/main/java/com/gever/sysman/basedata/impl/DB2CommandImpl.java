package com.gever.sysman.basedata.impl;

import java.sql.*;
import java.io.*;
import com.gever.config.Constants;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.sysman.basedata.DB2Command;
import com.gever.sysman.basedata.DB2Export;

public class DB2CommandImpl
    implements DB2Command {
  public DB2CommandImpl() {
  }

  public void exec(String[] tables, String filename) {
    ConnectionProvider cp = null;
    Connection conn = null;
    String[] ddls = null;
    try {
      cp = ConnectionProviderFactory.getConnectionProvider(Constants.
          DATA_SOURCE);
      conn = cp.getConnection();
      DB2Export db2Export = new DB2Export(conn);
      if (tables == null) {
        tables = db2Export.getTablesWithSchema(true);
      }
      ddls = db2Export.buildAllInsertStatement(tables, true, ";", true, false);
      writeDdlFile(ddls, filename);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeDdlFile(String[] ddls, String filename) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new FileOutputStream(filename));
      for (int i = 0; i < ddls.length; i++) {
        pw.println(ddls[i]);
      }
      pw.close();
    }
    catch (FileNotFoundException ex) {
    }
  }
}