package com.gever.goa.dailyoffice.bbs.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;


public interface BBSMngDAO {

  public final static String TOPBOARD_QUERY_SQL =
        "SELECT serial_num,tboard_name,icon_file,tboard_note,tboard_state,master from DO_BBS_TOPBOARD where 1=1 ";
    public final static String SBOARD_QUERY_SQL =
          "SELECT serial_num,tboard_serial,sboard_name,sboard_master,icon_file,sboard_note,sboard_state from DO_BBS_SBOARD where 1=1 ";
    public final static String BBSUSER_QUERY_SQL =
          "SELECT bbs_user_code,nickname,user_icon,last_log_time,user_state,user_code,sex_code,e_mail,home_page from DO_BBS_USER where 1=1 ";


   public void setQuest_Sql(String sql) throws DefaultException;
   public int delBySelect(String[] aryPk, VOInterface vo)throws DefaultException;
   public String getPageSql();
   public int insert(VOInterface vo)throws DefaultException;
   public int update(VOInterface vo) throws DefaultException;
   public List queryAll(VOInterface vo)throws DefaultException;
   public List queryByPage(VOInterface vo, long start, long howMany)throws DefaultException;
   public long queryByCount(VOInterface vo)throws DefaultException;
   public int copy(String[] keyValue,VOInterface vo)throws DefaultException;
   public void setSqlWhere(String where) ;
}
