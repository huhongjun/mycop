package com.gever.goa.dailyoffice.bbs.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSDAO;
import com.gever.goa.dailyoffice.bbs.vo.SBoardTreeVO;
import com.gever.goa.dailyoffice.bbs.vo.TopBoardTreeVO;
import com.gever.goa.dailyoffice.bbs.vo.UserVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.BaseTreeVO;

public class BBSDAOImpl extends BaseDAO implements BBSDAO{
  private String quest_sql="";
  private String SELECT_SQL="select bbs_user_code,nickname,user_icon,last_log_time,user_state,user_code,sex_code,e_mail,home_page from DO_BBS_user where 1=1";

  public BBSDAOImpl(String db) {
    super(db);
  }

  public void setQuest_Sql(String sql)throws DefaultException{
    quest_sql=sql;
  }
  //Extend baseDAO'mothed getPageSQL
  public String getPageSql() {
        return quest_sql;
    }



  public List getTreeData(String sql) throws DefaultException{
        List treeData = new ArrayList();
        BaseTreeVO vo;
        if(sql.equals(this.TOPBOARD_TREE_SQL ) )
            vo= new TopBoardTreeVO();
        else
           vo=new SBoardTreeVO();
        SQLHelper helper =  new DefaultSQLHelper(super.dbData);
        treeData = (ArrayList) helper.queryByListVo(sql, vo);
        return treeData;
  }

 public UserVO getBBSUserByUserCode(UserVO vo) throws DefaultException{
        String sql="";
        sql=SELECT_SQL+" and user_code="+vo.getUser_code();
        SQLHelper helper =  new DefaultSQLHelper(super.dbData);
        return (UserVO)helper.queryByVo(sql,vo);
  }

}
