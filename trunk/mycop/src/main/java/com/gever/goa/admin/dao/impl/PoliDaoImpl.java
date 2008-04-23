package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.PolityDao;
import com.gever.goa.admin.vo.PolityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 政治程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PoliDaoImpl
    extends BaseDAO
    implements PolityDao {
  private static String QUERY_SQL =
      "SELECT polity_code,polity_name,remarks from sys_polity";
   private String QUER_SQL=" select count(*) as cnt from sys_polity where polity_code='";
  private String UPDATE_SQL="update sys_polity set ";
  public PoliDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public int insert(VOInterface vo) throws DefaultException {

    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    helper.setAutoID(false);
    if (isRepeat((PolityVO)vo)) {
      throw new DefaultException("pk");
    }else{
      int iRet = helper.insert( vo );
      return iRet;
    }
  }
  private boolean isRepeat(com.gever.goa.admin.vo.PolityVO vo) throws DefaultException{
    String sql = QUER_SQL+vo.getPolity_code() + "'";
    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    boolean bRet = false;
    Map map = (HashMap) helper.query(sql,
                                     com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
      bRet = true;


    }
    return bRet;

  }
  public int update(VOInterface vo) throws DefaultException{
    SQLHelper SqlHelper = new DefaultSQLHelper(dbData);
    PolityVO polityVO = (PolityVO) vo;
    UPDATE_SQL += "polity_code='" + polityVO.getPolity_code() + "',"
        + "polity_name='" + polityVO.getPolity_name() + "',"
        + "remarks='" + polityVO.getRemarks() + "'"+"where polity_code='"+polityVO.getPolity_code_bak()+"'";
    if (isRepeat((PolityVO)vo)) {
     throw new DefaultException("PK");
    }

    if (checkUpdate(vo) != 1) {
      throw new DefaultException("sys_mod_002");
    }
    try{
             return SqlHelper.execUpdate(UPDATE_SQL);
         } catch(DefaultException e){
             throw new DefaultException("sys_mod_001", e);
         }


  }
}
