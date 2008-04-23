package com.gever.sysman.basedata.dao;

import java.util.*;

public interface BaseDataDAO {
  /**
   * 从指定xml文件中获得所有导入、导出基础数据的数据库命令
   * @param cmdxmlfile(命令配置文件位置)
   * @param databasexmlfile(数据库配置文件位置)
   * @param exportAllDB(是否导出整个数据库)
   * @param exportStructure(是否导出表结构)
   * @return
   * @throws Exception
   */
  public String getCommand(String cmdxmlfile,
                           String databasexmlfile,
                           String exportFileName,
                           boolean exportAllDB,
                           boolean exportStructure) throws Exception;

 // public Collection getCommands(String xmlfilename) throws Exception;
 /**
  * 执行本地命令，到处ddl
  * @param command（解析xml得到的导出命令）
  */
 public void export(String command);
 public void db2Export(String[] tables, String filename, String classname);
 //public void importDdl(String command, String databasexmlfile);
 public String getDB(String databasexmlfile) throws Exception;
 public Collection getTables(String databasexmlfile) throws Exception;
 //public String getImportCommand(String cmdxmlfile, String databasexmlfile) throws Exception;
}