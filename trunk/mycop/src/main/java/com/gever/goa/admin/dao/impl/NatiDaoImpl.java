package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.NationDao;
import com.gever.goa.admin.vo.NationVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 民族程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class NatiDaoImpl
    extends BaseDAO
    implements NationDao {
  private static String QUERY_SQL =
      "SELECT national_code,national_name,remarks from sys_national";
  private String QUER_SQL =
      "select count(*) as cnt from sys_national where national_code='";
  private String UPDATE_SQL = "update sys_national set  ";
  public NatiDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public int insert(VOInterface vo) throws DefaultException {

    SQLHelper helper = new DefaultSQLHelper(dbData);
    helper.setAutoID(false);
    if (isRepeat( (NationVO) vo)) {
      throw new DefaultException("PK repeat!");
    }
    else {
      int iRet = helper.insert(vo);
      return iRet;
    }
  }

  private boolean isRepeat(com.gever.goa.admin.vo.NationVO vo) throws
      DefaultException {
    String sql = QUER_SQL + vo.getNational_code() + "'";
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
    NationVO nationVO = (NationVO) vo;
    UPDATE_SQL += "national_code='" + nationVO.getNational_code() + "',"
        + "national_name='" + nationVO.getNational_name() + "',"
        + "remarks='" + nationVO.getRemarks() + "' where national_code='"
        + nationVO.getNational_code_bak() + "'"; ;
    if (checkUpdate(vo) != 1) {
      throw new DefaultException("sys_mod_002");
    }
    if (isRepeat( (NationVO) vo)) {
      throw new DefaultException("PK");
    }

    try {
      return helper.execUpdate(UPDATE_SQL);
    }
    catch (DefaultException e) {
      throw new DefaultException("sys_mod_001", e);
    }

  }

}
