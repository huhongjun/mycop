package com.gever.basedata.dao;

import java.util.ArrayList;
import java.util.List;

import com.gever.sysman.basedata.dao.BaseDataDAO;
import com.gever.sysman.basedata.dao.BaseDataDAOFactory;

import junit.framework.TestCase;

public class BaseDataDAOTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BaseDataDAOTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDBExport() throws Exception{
	    String basedataxmlfile = "testDir\\WEB-INF\\basedata-config.xml";
	    String databasexmlfile = "testDir\\WEB-INF\\database-config.xml";
	    String filename = "db-export.ddl";
	    String exportFilePath = "testDir\\db-export\\";
	    String exportFileName = exportFilePath+filename;
	    boolean exportAllDB = true;
	    boolean exportStructure = true;
	    String command = null;
	    BaseDataDAO dao = BaseDataDAOFactory.getBaseDataDAO();
	    String dbname = dao.getDB(databasexmlfile);
	    List tl = (ArrayList)dao.getTables(databasexmlfile);
	    String[] tables = new String[tl.size()];
	    tl.toArray(tables);

	    //获得导出ddl的命令
	    command = dao.getCommand(basedataxmlfile, databasexmlfile, exportFileName, exportAllDB,exportStructure);
	    String[] arr = command.split(";");
	    if(arr.length > 1){
	     for(int i = 0;i < arr.length;i++){
	        command = arr[i].trim();
	        int idx = command.indexOf("java");
	        //执行命令 （多条命令）
	        if (idx == 0) {
	          String classname = command.substring(4, command.length()).trim();
	          if (exportAllDB) {
	            dao.db2Export(null, exportFilePath+dbname+"_java.ddl", classname);
	          }
	          else {
	            dao.db2Export(tables, exportFilePath+dbname+"_java.ddl", classname);
	          }
	       }
	        else {
	          dao.export(command);
	        }
	     }
	    }else{
	      //执行命令
	      if (command.trim().indexOf("java") == 0) {
	        if (exportAllDB) {
	          dao.db2Export(null, exportFileName,null);
	        }
	        else {
	          dao.db2Export(tables, exportFileName,null);
	        }
	      }
	      else {
	        dao.export(command);
	      }
	    }
	}

}
