package com.gever.sysman.basedata;

import java.sql.*;
import java.util.*;

/*
 * �������� 2004-12-2
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
   * ָ������
   * @param conn
   */
  public void setConnection(Connection c) {
    conn = c;
  }

  /**
   * ��ȡ���ݿ��г�ϵͳģʽ��SYSIBM����SYSCAT����SYSFUN����SYSSTAT����NULLID������û�ģʽ
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
   * ��ȡָ��ģʽ�����б�����ģʽǰ׺��
   * @param schema      ģʽ����ע�⣺���û��˫���ţ���ͨ����д����
   * @return
   * @throws SQLException
   */
  public String[] getTables(String schema) throws SQLException {
    String schema2 = null;
    String[] tables = null;
    Statement s = null;
    ResultSet rs = null;
    ArrayList tablesList = new ArrayList();

    //����˫���ż���Сд����
    if (schema.startsWith("\"") && schema.endsWith("\"")) {
      schema2 = schema.substring(1, schema.length() - 1);
    }
    else {
      schema2 = schema.toUpperCase();
    }

    //�˴������ô������Ҫ��Ϊ���Ƿ��������������û�е�����ǰ��
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
   * ��ȡ���ݿ��������û���
   * @param withDoubleQuotationMarks  ģʽ�����Ƿ��˫����
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
   * ��ȡָ��������������Ӧ��������
   * @param table      �������ɺ�ģʽǰ׺����ע�⣺���û��˫���ţ���ͨ����д����
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

    //�����ģʽ�ͱ���
    pos = table.indexOf(".");

    if (pos >= 0) {
      schema = table.substring(0, pos);
      table2 = table.substring(pos + 1);
    }
    else {
      //ȡ���ӵĵ�ǰģʽ
      //rs = s.executeQuery("SELECT CURRENT SCHEMA FROM SYSIBM.SYSDUMMY1");
      rs = s.executeQuery("values(current schema)");

      while (rs.next()) {
        schema = rs.getString(1);
      }

      table2 = table;
    }

    //ȥ��ģʽ��������˫���ż����д�Сд����
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

    //��ѯ��ָ��ģʽ��������Ӧ������������������
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
      throw new SQLException("�Ҳ����� " + schema + "." + table2);
    }

    columnsNameAndType = new Column[columnsList.size()];
    columnsList.toArray(columnsNameAndType);

    return columnsNameAndType;
  }

  /**
   * ����ָ�����Insert���
   * @param table       �������ɺ�ģʽǰ׺����ע�⣺���û��˫���ţ���ͨ����д����
   * @return
   * @throws SQLException
   */
  public String[] buildInsertStatement(String table)
      throws SQLException {
      return buildInsertStatement(table, false, ";", true, true);
  }

  /**
   * ����ָ�����Insert��䣬��ָ�������
   * @param table                     Ҫ����Insert���ı������ɺ�ģʽǰ׺����ע�⣺���û��˫���ţ���ͨ����д����
   * @param includecolName            �Ƿ��������
   * @param delimiter                 ÿ��Insert���Ķ����
   * @param withDoubleQuotationMarks  ģʽ�������Ƿ��˫����
   * @param withSchema                ����ǰ�Ƿ��ģʽ
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

      //�������������ӵ����ŵ���������
      typeName1.add("BIGINT");
      typeName1.add("DECIMAL");
      typeName1.add("DOUBLE");
      typeName1.add("INTEGER");
      typeName1.add("REAL");
      typeName1.add("SMALLINT");

      //�����������ӵ����ŵ���������
      typeName2.add("CHARACTER");
      typeName2.add("CLOB");
      typeName2.add("DATE");
      typeName2.add("LONG VARCHAR");
      typeName2.add("TIME");
      typeName2.add("TIMESTAMP");
      typeName2.add("VARCHAR");

      //��δ������ͨ�������������������
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

      //��ģʽ���������д�Сд��˫���Ŵ���
      //�����ģʽ�ͱ���
      pos = table.indexOf(".");

      if (pos >= 0) {
          schema = table.substring(0, pos);
          table2 = table.substring(pos + 1);
      } else {
          //ȡ���ӵĵ�ǰģʽ
          rs = s.executeQuery("VALUES (CURRENT SCHEMA)");

          while (rs.next()) {
              schema = rs.getString(1);
          }

          table2 = table;
      }

      //��ģʽ���������д�Сд��˫���Ŵ���
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

      //ȡ��ָ���������������������
      columns = getColumns(table);

      //���˵�������
      for (int i = 0; i < columns.length; i++) {
          if (!(columns[i].identity.equals("Y") &&
                  columns[i].generated.equals("A"))) {
              tmp.add(columns[i]);
          }
      }

      columns = new Column[tmp.size()];
      tmp.toArray(columns);

      //�������������磺��col_1,col_2...����"col_1","col_2"...����
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
          //�������ֵ�����磺��1,'ABC','2002-02-02'...����
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

                      //��������ͱ�����Ϊ��FOR BIT DATA��������Ҫ��ֵǰ��ӡ�x��
                      if ((columns[i].typeName.equals("CHARACTER")) &&
                              (columns[i].codePage == 0)) {
                          value = "x" + value;
                      }

                      values = values + "," + value;
                  }
              } else if (typeName3.indexOf(columns[i].typeName) >= 0) {
                  values = values + ",null";
              } else {
                  throw new SQLException("�Ҳ����������͡���" + table + " ���������ͣ�" +
                      columns[i].typeName);
              }

              if (values.indexOf(",") == 0) {
                  values = values.substring(1);
              }
          }

          //��װInsert���
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
   * �������ݿ��������û����Insert��䡣ע�⣺���������ܴ��ʱ�򣬻���ִ���java.lang.OutOfMemoryError  Exception in thread "main" �����˰汾��û���������⡣
   * @param includecolName         �Ƿ��������
   * @param delimiter                 ÿ��Insert���Ķ����
   * @param withDoubleQuotationMarks  ģʽ�������Ƿ��˫����
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
