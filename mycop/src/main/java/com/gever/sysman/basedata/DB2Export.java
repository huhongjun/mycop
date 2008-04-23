package com.gever.sysman.basedata;

import java.sql.*;
import java.util.*;

/*
 * 创建日期 2004-12-2
 * @author Hu.Walker
 *
 */
public class DB2Export {
  Connection conn = null;

  public DB2Export() {
  }

  public DB2Export(Connection c) {
    conn = c;
  }

  /**
   * 指定连接
   * @param conn
   */
  public void setConnection(Connection c) {
    conn = c;
  }

  /**
   * 获取数据库中除系统模式“SYSIBM”“SYSCAT”“SYSFUN”“SYSSTAT”“NULLID”外的用户模式
   * @return
   * @throws SQLException
   */
  public String[] getSchemas() throws SQLException {
    ArrayList schemasList = new ArrayList();
    String[] schemas = null;
    Statement s = null;
    ResultSet rs = null;
    String select = "SELECT RTRIM(SCHEMANAME) FROM SYSCAT.SCHEMATA WHERE SCHEMANAME <> 'SYSIBM' AND SCHEMANAME <> 'SYSCAT' AND SCHEMANAME <> 'SYSFUN' AND SCHEMANAME <> 'SYSSTAT' AND SCHEMANAME <> 'NULLID'";

    s = conn.createStatement();
    rs = s.executeQuery(select);

    while (rs.next()) {
      schemasList.add(rs.getString(1));
    }

    schemas = new String[schemasList.size()];
    schemasList.toArray(schemas);

    return schemas;
  }

  /**
   * 获取指定模式的所有表（不含模式前缀）
   * @param schema      模式名。注意：如果没加双引号，则通按大写处理
   * @return
   * @throws SQLException
   */
  public String[] getTables(String schema) throws SQLException {
    String schema2 = null;
    String[] tables = null;
    Statement s = null;
    ResultSet rs = null;
    ArrayList tablesList = new ArrayList();

    //进行双引号及大小写处理
    if (schema.startsWith("\"") && schema.endsWith("\"")) {
      schema2 = schema.substring(1, schema.length() - 1);
    }
    else {
      schema2 = schema.toUpperCase();
    }

    //此处搞的这么复杂主要是为表按是否有外键进行排序，没有的排在前面
    String select =
        "SELECT A.TABNAME FROM ((SELECT TABSCHEMA, TABNAME, TABSCHEMA||'.'||TABNAME AS MY_COL FROM SYSCAT.TABLES) AS A LEFT OUTER JOIN (SELECT DISTINCT TABSCHEMA||'.'||TABNAME AS MY_COL FROM SYSCAT.REFERENCES) AS B ON A.MY_COL = B.MY_COL) WHERE A.TABSCHEMA = '" +
        schema2 + "' ORDER BY B.MY_COL DESC";

    s = conn.createStatement();
    rs = s.executeQuery(select);

    while (rs.next()) {
      tablesList.add(rs.getString(1));
    }

    tables = new String[tablesList.size()];
    tablesList.toArray(tables);

    return tables;
  }

  /**
   * 获取数据库中所有用户表
   * @param withDoubleQuotationMarks  模式、表是否加双引号
   * @return
   * @throws SQLException
   */
  public String[] getTablesWithSchema(boolean withDoubleQuotationMarks) throws
      SQLException {
    String[] schemas = null;
    String[] tables = null;
    String[] tablesWithSchema = null;
    ArrayList tablesWithSchemaList = new ArrayList();

    schemas = getSchemas();

    for (int i = 0; i < schemas.length; i++) {
      tables = getTables("\"" + schemas[i] + "\"");

      for (int j = 0; j < tables.length; j++) {
        if (withDoubleQuotationMarks) {
          tablesWithSchemaList.add("\"" + schemas[i] + "\".\"" +
                                   tables[j] + "\"");
        }
        else {
          tablesWithSchemaList.add(schemas[i] + "." + tables[j]);
        }
      }
    }

    tablesWithSchema = new String[tablesWithSchemaList.size()];
    tablesWithSchemaList.toArray(tablesWithSchema);

    return tablesWithSchema;
  }

  /**
   * 获取指定表的列名及其对应数据类型
   * @param table      表名（可含模式前缀）。注意：如果没加双引号，则通按大写处理
   * @return
   * @throws SQLException
   */
  public Column[] getColumns(String table) throws SQLException {
    int pos;
    int rowCount = 0;
    Statement s = null;
    ResultSet rs = null;
    String schema = null;
    String table2 = null;
    ArrayList columnsList = new ArrayList();
    Column column;
    Column[] columnsNameAndType = null;

    s = conn.createStatement();

    //分离出模式和表名
    pos = table.indexOf(".");

    if (pos >= 0) {
      schema = table.substring(0, pos);
      table2 = table.substring(pos + 1);
    }
    else {
      //取连接的当前模式
      //rs = s.executeQuery("SELECT CURRENT SCHEMA FROM SYSIBM.SYSDUMMY1");
      rs = s.executeQuery("values(current schema)");

      while (rs.next()) {
        schema = rs.getString(1);
      }

      table2 = table;
    }

    //去除模式及表名的双引号及进行大小写处理
    if (schema.startsWith("\"") && schema.endsWith("\"")) {
      schema = schema.substring(1, schema.length() - 1);
    }
    else {
      schema = schema.toUpperCase();
    }

    if (table2.startsWith("\"") && table2.endsWith("\"")) {
      table2 = table2.substring(1, table2.length() - 1);
    }
    else {
      table2 = table2.toUpperCase();
    }

    //查询出指定模式及表名对应的列名及其数据类型
    rs = s.executeQuery(
        "SELECT COLNAME, TYPENAME, CODEPAGE, IDENTITY, GENERATED FROM SYSCAT.COLUMNS WHERE TABSCHEMA = '" +
        schema + "' AND TABNAME = '" + table2 + "' ORDER BY COLNO ASC");

    while (rs.next()) {
      rowCount++;
      column = new Column();
      column.colName = rs.getString(1);
      column.typeName = rs.getString(2);
      column.codePage = rs.getInt(3);
      column.identity = rs.getString(4);
      column.generated = rs.getString(5);

      columnsList.add(column);
    }

    if (rowCount == 0) {
      throw new SQLException("找不到表 " + schema + "." + table2);
    }

    columnsNameAndType = new Column[columnsList.size()];
    columnsList.toArray(columnsNameAndType);

    return columnsNameAndType;
  }

  /**
   * 构建指定表的Insert语句
   * @param table       表名（可含模式前缀）。注意：如果没加双引号，则通按大写处理
   * @return
   * @throws SQLException
   */
  public String[] buildInsertStatement(String table)
      throws SQLException {
      return buildInsertStatement(table, false, ";", true, true);
  }

  /**
   * 构建指定表的Insert语句，可指定定界符
   * @param table                     要生成Insert语句的表名（可含模式前缀）。注意：如果没加双引号，则通按大写处理
   * @param includecolName            是否包含列名
   * @param delimiter                 每条Insert语句的定界符
   * @param withDoubleQuotationMarks  模式、表、列是否加双引号
   * @param withSchema                表名前是否加模式
   * @return
   * @throws SQLException
   */
  public String[] buildInsertStatement(String table, boolean includecolName,
      String delimiter, boolean withDoubleQuotationMarks, boolean withSchema)
      throws SQLException {
      int pos;
      String select = null;
      String schema = null;
      String values = null;
      String table2 = null;
      String colNames = new String();
      String insertStatement = null;
      String[] insertStatements = null;
      Object value = null;
      Statement s = null;
      ResultSet rs = null;
      ArrayList tmp = new ArrayList();
      ArrayList typeName1 = new ArrayList();
      ArrayList typeName2 = new ArrayList();
      ArrayList typeName3 = new ArrayList();
      ArrayList insertStatementsList = new ArrayList();
      Column[] columns = null;

      //插入语句中无需加单引号的数据类型
      typeName1.add("BIGINT");
      typeName1.add("DECIMAL");
      typeName1.add("DOUBLE");
      typeName1.add("INTEGER");
      typeName1.add("REAL");
      typeName1.add("SMALLINT");

      //插入语句中需加单引号的数据类型
      typeName2.add("CHARACTER");
      typeName2.add("CLOB");
      typeName2.add("DATE");
      typeName2.add("LONG VARCHAR");
      typeName2.add("TIME");
      typeName2.add("TIMESTAMP");
      typeName2.add("VARCHAR");

      //尚未能用普通插入语句插入的数据类型
      typeName3.add("BOOLEAN");
      typeName3.add("BLOB");
      typeName3.add("DBCLOB");
      typeName3.add("DATALINK");
      typeName3.add("GRAPHIC");
      typeName3.add("LONG VARGRAPHIC");
      typeName3.add("REFERENCE");
      typeName3.add("VARGRAPHIC");

      if (delimiter == null) {
          delimiter = "";
      }

      s = conn.createStatement();

      //对模式及表名进行大小写及双引号处理
      //分离出模式和表名
      pos = table.indexOf(".");

      if (pos >= 0) {
          schema = table.substring(0, pos);
          table2 = table.substring(pos + 1);
      } else {
          //取连接的当前模式
          rs = s.executeQuery("VALUES (CURRENT SCHEMA)");

          while (rs.next()) {
              schema = rs.getString(1);
          }

          table2 = table;
      }

      //对模式及表名进行大小写及双引号处理
      if (schema != null) {
          if (!(schema.startsWith("\"") && schema.endsWith("\""))) {
              schema = schema.toUpperCase();

              if (withDoubleQuotationMarks) {
                  schema = "\"" + schema + "\"";
              }
          }
      }

      if (!(table2.startsWith("\"") && table2.endsWith("\""))) {
          table2 = table2.toUpperCase();

          if (withDoubleQuotationMarks) {
              table2 = "\"" + table2 + "\"";
          }
      }

      //取得指定表的列名及其数据类型
      columns = getColumns(table);

      //过滤掉自增列
      for (int i = 0; i < columns.length; i++) {
          if (!(columns[i].identity.equals("Y") &&
                  columns[i].generated.equals("A"))) {
              tmp.add(columns[i]);
          }
      }

      columns = new Column[tmp.size()];
      tmp.toArray(columns);

      //构造列名（形如：“col_1,col_2...”或“"col_1","col_2"...”）
      for (int i = 0; i < columns.length; i++) {
          if (withDoubleQuotationMarks) {
              colNames = colNames + ",\"" + columns[i].colName + "\"";
          } else {
              colNames = colNames + "," + columns[i].colName;
          }
      }

      if (colNames.indexOf(",") == 0) {
          colNames = colNames.substring(1);
      }

      select = "SELECT " + colNames + " FROM " + table;

      try {
          rs = s.executeQuery(select);
      } catch (SQLException e) {
          throw new SQLException(e.getMessage() + "\n" + select);
      }

      while (rs.next()) {
          //构造插入值（形如：“1,'ABC','2002-02-02'...”）
          values = new String();

          for (int i = 0; i < columns.length; i++) {
              if (typeName1.indexOf(columns[i].typeName) >= 0) {
                  value = rs.getObject(i + 1);

                  if (value == null) {
                      value = "null";
                  }

                  values = values + "," + value;
              } else if (typeName2.indexOf(columns[i].typeName) >= 0) {
                  value = rs.getString(i + 1);

                  if (value == null) {
                      value = "null";
                      values = values + "," + value;
                  } else {
                      value = value.toString().replaceAll("'", "''");
                      value = "'" + value + "'";

                      //如果列类型被定义为“FOR BIT DATA”，则需要在值前面加“x”
                      if ((columns[i].typeName.equals("CHARACTER")) &&
                              (columns[i].codePage == 0)) {
                          value = "x" + value;
                      }

                      values = values + "," + value;
                  }
              } else if (typeName3.indexOf(columns[i].typeName) >= 0) {
                  values = values + ",null";
              } else {
                  throw new SQLException("找不到数据类型。表：" + table + " 列数据类型：" +
                      columns[i].typeName);
              }

              if (values.indexOf(",") == 0) {
                  values = values.substring(1);
              }
          }

          //组装Insert语句
          if (includecolName) {
              if (withSchema) {
                  insertStatement = "INSERT INTO " + schema + "." + table2 +
                      " (" + colNames + ") VALUES (" + values + ")" +
                      delimiter;
              } else {
                  insertStatement = "INSERT INTO " + table2 + " (" +
                      colNames + ") VALUES (" + values + ")" + delimiter;
              }
          } else {
              if (withSchema) {
                  insertStatement = "INSERT INTO " + schema + "." + table2 +
                      " VALUES (" + values + ")" + delimiter;
              } else {
                  insertStatement = "INSERT INTO " + table2 + " VALUES (" +
                      values + ")" + delimiter;
              }
          }

          insertStatementsList.add(insertStatement);
      }

      insertStatements = new String[insertStatementsList.size()];
      insertStatementsList.toArray(insertStatements);

      return insertStatements;
  }

  /**
   * 构建数据库中所有用户表的Insert语句。注意：当数据量很大的时候，会出现错误“java.lang.OutOfMemoryError  Exception in thread "main" ”，此版本暂没解决这个问题。
   * @param includecolName         是否包含列名
   * @param delimiter                 每条Insert语句的定界符
   * @param withDoubleQuotationMarks  模式、表、列是否加双引号
   * @return
   * @throws SQLException
   */
  public String[] buildAllInsertStatement(String[] tables,
                                          boolean includecolName,
                                          String delimiter,
                                          boolean withDoubleQuotationMarks,
                                          boolean withSchema) throws
      SQLException {
    String[] insertStatements = null;
    String[] allInsertStatements = null;
    ArrayList allInsertStatementsList = new ArrayList();

    if(tables == null) {
      tables = getTablesWithSchema(true);
    }

    for (int i = 0; i < tables.length; i++) {
      insertStatements = buildInsertStatement(tables[i], includecolName,
                                              delimiter,
                                              withDoubleQuotationMarks,
                                              withSchema);

      for (int j = 0; j < insertStatements.length; j++) {
        allInsertStatementsList.add(insertStatements[j]);
      }
    }

    allInsertStatements = new String[allInsertStatementsList.size()];
    allInsertStatementsList.toArray(allInsertStatements);

    return allInsertStatements;
  }
}
