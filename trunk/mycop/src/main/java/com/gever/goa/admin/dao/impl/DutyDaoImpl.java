package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.DutyDao;
import com.gever.goa.admin.vo.DutyVO;
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

public class DutyDaoImpl
    extends BaseDAO
    implements DutyDao {
  private static String QUERY_SQL =
      "SELECT duty_code,duty_name,remarks from sys_duty";
   private String QUER_SQL="select count(*) as cnt from sys_duty where duty_code='";
   private String UPDATE_SQL="update sys_duty set ";
  public DutyDaoImpl(String dbData) {
    super(dbData);
  }



  public int insert(VOInterface vo) throws DefaultException {

    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    helper.setAutoID( false );
    if (isRepeat((DutyVO)vo)) {
      throw new DefaultException( "PK repeat!" );
    }
    else {
      int iRet = helper.insert( vo );
      return iRet;
    }
  }
  public String getPageSql() {
      return QUERY_SQL;
  }
  public int update(VOInterface vo) throws DefaultException {
    SQLHelper helper = new DefaultSQLHelper(dbData);
    DutyVO dutyVO = (DutyVO) vo;
    UPDATE_SQL += "duty_code='" + dutyVO.getDuty_code() + "',"
        + "duty_name='" + dutyVO.getDuty_name() + "',"
        + "remarks='" + dutyVO.getRemarks() + "'"
        +"where duty_code='" +dutyVO.getDuty_code_bak()+"'";
    if (isRepeat((DutyVO)vo)) {
     throw new DefaultException( "PK" );
   }

    if (checkUpdate(vo) != 1) {
      throw new DefaultException("sys_mod_002");
    }
    try {
      return helper.execUpdate(UPDATE_SQL);
        } catch(DefaultException e){
            throw new DefaultException("sys_mod_001", e);
        }

 }

  private boolean isRepeat(DutyVO vo) throws DefaultException{
    String sql = QUER_SQL +vo.getDuty_code() + "'";
    System.out.println(sql);
    SQLHelper helper = new DefaultSQLHelper(dbData); ;
    boolean bRet = false;
    Map map = (HashMap) helper.query(sql,
                                     com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {

      bRet = true;

    }
     return bRet;


  }


}
