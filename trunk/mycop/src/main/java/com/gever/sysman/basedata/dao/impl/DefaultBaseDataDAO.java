package com.gever.sysman.basedata.dao.impl;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import com.gever.sysman.basedata.DB2Command;
import com.gever.sysman.basedata.dao.BaseDataDAO;

public class DefaultBaseDataDAO
    implements BaseDataDAO {

  /**
   * 从xml文件解析出要执行的导出命令
   * @param cmdxmlfile
   * @return
   * @throws Exception
   */
  public String getCommand(String cmdxmlfile,
                           String databasexmlfile,
                           String exportFileName,
                           boolean exportAllDB,
                           boolean exportStructure) throws Exception {
    String isCurrent = "false";
    String dbtype = null;
    String command = null;
    String alldb = null;
    String structure = null;
    String tablenames = "";
    String dbname = getDB(databasexmlfile);
    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder, cmdxmlfile);
    Element root = (Element) doc.getRootElement();
    Collection dbCol = root.getChildren("db");
    Iterator i = dbCol.iterator();
    while (i.hasNext()) {
      Element dbs = (Element) i.next();
      isCurrent = dbs.getAttributeValue("current");
      dbtype = dbs.getAttributeValue("type");
      //根据配置为current的数据库读出导出命令
      if (isCurrent != null && isCurrent.equalsIgnoreCase("true")) {
        Collection commandCol = dbs.getChildren("command");
        i = commandCol.iterator();
        while (i.hasNext()) {
          Element comm = (Element) i.next();
          //根据alldb,structure条件组合得到导出命令
          alldb = comm.getAttributeValue("alldb");
          structure = comm.getAttributeValue("structure");
          if (alldb.equalsIgnoreCase(String.valueOf(exportAllDB)) &&
              structure.equalsIgnoreCase(String.valueOf(exportStructure))) {
            command = comm.getText();
            //生成最终要执行的命令
            Collection tables = getTables(databasexmlfile);
            StringBuffer sb = new StringBuffer();
            Iterator it = tables.iterator();
            //得到表名字符串
            while (it.hasNext()) {
              if (dbtype.equalsIgnoreCase("oracle")) {
                sb.append(it.next().toString()).append(",");
              }
              else {
                sb.append(it.next().toString()).append(" ");
              }
            }
            if (tables.size() > 0) {
              tablenames = sb.toString().substring(0,
                  sb.toString().length() - 1);
            }
            command = command.replaceAll("@@dbname", dbname);
            command = command.replaceAll("@@tablenames", tablenames);
            String filename = ( exportFileName.replaceAll(
                "\\\\", "\\\\\\\\"));
            command = command.replaceAll("@@filename", filename);
          }
        }
      }
    }
    return command;
  }
/*
  public String getImportCommand(String cmdxmlfile, String databasexmlfile) throws
      Exception {
    String command = null;
    String isCurrent = "false";
    Map map = getDB(databasexmlfile);
    String ddlfile = (String) map.get("exportfile");
    ddlfile = ddlfile.replaceAll("\\\\", "\\\\\\\\");
    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder, cmdxmlfile);
    Element root = doc.getRootElement();
    Element importElement = root.getChild("import");
    Collection dbCol = importElement.getChildren("db");
    Iterator it = dbCol.iterator();
    while (it.hasNext()) {
      Element dbcmd = (Element) it.next();
      isCurrent = dbcmd.getAttributeValue("current");
      if (isCurrent != null && isCurrent.equals("true")) {
        command = dbcmd.getChild("command").getText();
        command = command.replaceAll("@@filename", ddlfile);
      }
    }
    return command;
  }
*/
  /**
   * 解析xml获取数据库名与要导出的文件名
   * @param databasexmlfile
   * @return
   * @throws Exception
   */
  public String getDB(String databasexmlfile) throws Exception {
    String dbname = null;
    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder, databasexmlfile);
    Element root = doc.getRootElement();
    Element database = root.getChild("database");
    dbname = database.getAttributeValue("name");
    return dbname;
  }

  /**
   * 从xml文件中解析出要导出的数据库表
   * @param databasexmlfile
   * @return
   * @throws Exception
   */
  public Collection getTables(String databasexmlfile) throws Exception {
    Collection tableList = new ArrayList();
    String dbname = null;
    //String exportfile = null;
    String tablename = null;
    SAXBuilder builder = new SAXBuilder();
    Document doc = buildDoc(builder, databasexmlfile);
    Element root = doc.getRootElement();
    Element database = root.getChild("database");
    dbname = database.getAttributeValue("name");
    //exportfile = database.getAttributeValue("exportfile");
    Collection tables = database.getChildren("table");
    Iterator i = tables.iterator();
    while (i.hasNext()) {
      Element table = (Element) i.next();
      tablename = table.getAttributeValue("name");
      tableList.add(tablename);
    }
    return tableList;
  }

  /**
   * 执行从xml解析出的命令，完成导出功能
   * @param command
   */
  private void executeCommand(String command) {
    try {
      Runtime.getRuntime().exec(command);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void db2Export(String[] tables, String filename,String classname) {
      try {
        if(classname == null || "".equals(classname)) {
          classname = "com.gever.sysman.basedata.impl.DB2CommandImpl";
        }
        Class c = Class.forName(classname);
        DB2Command db2command = (DB2Command) c.newInstance();
        db2command.exec(tables,filename);
      }
      catch (IllegalAccessException ex) {
      }
      catch (Exception ex) {
      }
  }

  public void export(String command) {
    executeCommand(command);
  }
/*
  private void deleteTables(String databasexmlfile) throws Exception {
    Collection tables = getTables(databasexmlfile);
    StringBuffer sb = new StringBuffer();
    Iterator it = tables.iterator();
    while (it.hasNext()) {
      sb.setLength(0);
      sb.append("DROP TABLE ").append(it.next().toString());
      try {
        executeCommand(sb.toString());
      }catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void importDdl(String command, String databasexmlfile) {
    executeCommand(command);
  }
*/
  public synchronized Document buildDoc(SAXBuilder builder, String filename) throws
      Exception {
    Document doc = null;
    File file = new File(filename);
    doc = builder.build(file);
    return doc;
  }
}