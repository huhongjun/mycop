package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.KnowDao;
import com.gever.goa.admin.vo.KnowVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 学历程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class KnowDaoImpl
    extends BaseDAO
    implements KnowDao {
  private static String QUERY_SQL =
      "SELECT knowledge_code,knowledge_name,remarks from sys_knowledge";
  private String QUER_SQL =
      "select count(*) as cnt from sys_knowledge where knowledge_code='";
  private String UPDATE_SQL = "update sys_knowledge set ";
  public KnowDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public int insert(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    helper.setAutoID(false);
    if (isRepeat((KnowVO)vo)) {
        throw new DefaultException("PK repeat!");
      }
      else{
        int iRet = helper.insert(vo);
      return iRet;
      }
  }
  private boolean isRepeat(KnowVO vo) throws DefaultException{
    String sql = QUER_SQL +vo.getKnowledge_code() + "'";
    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    boolean bRet = false;
    Map map = (HashMap) helper.query(sql,
                                     com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
      bRet = true;
    }
    return bRet;
  }
  public int update(VOInterface vo) throws DefaultException {
      SQLHelper helper = new DefaultSQLHelper(dbData);
      KnowVO knowVO = (KnowVO) vo;
      UPDATE_SQL += "knowledge_code='" + knowVO.getKnowledge_code() + "',"
                  + "Knowledge_name='" + knowVO.getKnowledge_name() + "',"
                   +"remarks='"+knowVO.getRemarks()+"'"
                   +"where knowledge_code= '"+knowVO.getKnowledge_code_bak()+"'";
               if (isRepeat( (KnowVO) vo)) {
                 throw new DefaultException("PK");
               }

               if (checkUpdate(vo) != 1) {
                 throw new DefaultException("sys_mod_002");
               }
               try {
                 return helper.execUpdate(UPDATE_SQL);
               }
               catch (DefaultException e) {
                 throw new DefaultException("sys_mod_001", e);
               }

             }

}
