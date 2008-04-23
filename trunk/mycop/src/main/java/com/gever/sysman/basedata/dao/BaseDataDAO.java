package com.gever.sysman.basedata.dao;

import java.util.*;

public interface BaseDataDAO {
  /**
   * ��ָ��xml�ļ��л�����е��롢�����������ݵ����ݿ�����
   * @param cmdxmlfile(���������ļ�λ��)
   * @param databasexmlfile(���ݿ������ļ�λ��)
   * @param exportAllDB(�Ƿ񵼳��������ݿ�)
   * @param exportStructure(�Ƿ񵼳���ṹ)
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
  * ִ�б����������ddl
  * @param command������xml�õ��ĵ������
  */
 public void export(String command);
 public void db2Export(String[] tables, String filename, String classname);
 //public void importDdl(String command, String databasexmlfile);
 public String getDB(String databasexmlfile) throws Exception;
 public Collection getTables(String databasexmlfile) throws Exception;
 //public String getImportCommand(String cmdxmlfile, String databasexmlfile) throws Exception;
}