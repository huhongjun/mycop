package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.SexDao;
import com.gever.goa.admin.vo.SexVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 职务程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SexDaoImpl
    extends BaseDAO
    implements SexDao {
  private static String QUERY_SQL =
      "SELECT sex_code,sex_name,remarks from sys_sex";
  private String QUER_SQL =
      " select count(*) as cnt from sys_sex where sex_code='";
  private String UPDATE_SQL = "update sys_sex set ";
  public SexDaoImpl(String dbData) {
    super(dbData);
  }

  public int insert(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    helper.setAutoID(false);
    if (isRepeat( (SexVO) vo)) {
      throw new DefaultException("pk");
    }
    else {
      int iRet = helper.insert(vo);
      return iRet;
    }
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  private boolean isRepeat(SexVO vo) throws DefaultException {
    String sql = QUER_SQL +
        vo.getSex_code() + "'";
    System.out.println(sql);
    boolean bRet = false;
    SQLHelper helper = new DefaultSQLHelper(dbData); ;

    Map map = (HashMap) helper.query(sql,
                                     com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
      bRet = true;
    }
    return bRet;

  }

  public int update(VOInterface vo) throws DefaultException {
    SQLHelper SqlHelper = new DefaultSQLHelper(dbData);
    SexVO sexVO = (SexVO) vo;
    UPDATE_SQL += "sex_code='" + sexVO.getSex_code() + "',"
        + "sex_name='" + sexVO.getSex_name() + "',"
        + "remarks='" + sexVO.getRemarks() + "'"
        + "where sex_code='" + sexVO.getSex_code_bak() + "'";
     if(isRepeat((SexVO)vo)){
       throw new DefaultException("PK");
     }

    if (checkUpdate(vo) != 1) {
      throw new DefaultException("sys_mod_002");
    }
    try {
      return SqlHelper.execUpdate(UPDATE_SQL);
    }
    catch (DefaultException e) {
      throw new DefaultException("sys_mod_001", e);
    }

  }

}
