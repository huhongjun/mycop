package com.gever.goa.dailyoffice.bbs.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.vo.UserVO;


public interface BBSDAO {
  public final static String TOPBOARD_TREE_SQL
      ="SELECT serial_num as nodeid,tboard_name as nodename,icon_file,tboard_note,tboard_state,'1' as isfolder from DO_BBS_TOPBOARD where  tboard_state=1";
  public final static String SBOARD_TREE_SQL
      ="SELECT serial_num as nodeid,sboard_name as nodename,icon_file,sboard_note,sboard_state,'0' as isfolder from DO_BBS_SBOARD where sboard_state=1 ";

  public final static String TOPICLIST_SQL
      ="SELECT serial_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,awoke_flag from DO_BBS_TOPICLIST where 1=1 ";

  public List getTreeData(String sql)throws DefaultException;
  public void setQuest_Sql(String sql) throws DefaultException;
  public UserVO getBBSUserByUserCode(UserVO vo) throws DefaultException;

}
