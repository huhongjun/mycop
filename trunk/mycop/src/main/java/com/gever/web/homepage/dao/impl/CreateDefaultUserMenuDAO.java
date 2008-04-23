package com.gever.web.homepage.dao.impl;

import com.gever.config.Constants;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.web.homepage.dao.CreateDefaultMenuDAO;

/**
 * @author Hu.Walker
 * @desc 为指定用户创建默认菜单
 * 
 */
public class CreateDefaultUserMenuDAO implements CreateDefaultMenuDAO {
  private static final String DEFAULT_USER_ID = "-1";
  protected SQLHelper helper = null;
    protected String dbData = "gdp";
    protected static final String PATH_SPLIT = "-->";

  public CreateDefaultUserMenuDAO() {
        this.dbData = Constants.DATA_SOURCE;
        helper = new DefaultSQLHelper(dbData);
    }

    public CreateDefaultUserMenuDAO(String dbData) {
        this.dbData = dbData;
        helper = new DefaultSQLHelper(dbData);
    }


  private void copyMenu(String defaultId, String userId) throws Exception {
    /*
    StringBuffer updateSql = new StringBuffer();
    updateSql.append("update T_USER_MENU set empId = '").append(userId);
    updateSql.append("' where empId = '").append(defaultId).append("'");
    */
   helper.execUpdate("delete from T_USER_MENU where empid='"+userId+"'");
    StringBuffer copySql = new StringBuffer();
    copySql.append("INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,PARENTID,");
    copySql.append("LINK,LINKMODE,ISFOLDER,ISHIDED,ORDERID,ISMENU,FLAG,MEMO) ");
    copySql.append("SELECT '").append(userId).append("' AS EMPID,NODEID,NODENAME,PARENTID,");
    copySql.append("LINK,LINKMODE,ISFOLDER,ISHIDED,ORDERID,ISMENU,FLAG,MEMO");
    copySql.append(" FROM T_USER_MENU WHERE EMPID = '").append(defaultId).append("'");

    helper.execUpdate(copySql.toString());

  }

  public void copyDefaultMenu(String userId) {
    try {
      this.copyMenu(DEFAULT_USER_ID, userId);
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}