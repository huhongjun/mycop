package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.MarriageDao;
import com.gever.goa.admin.vo.MarriageVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 婚姻程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MarrDaoImpl
    extends BaseDAO
    implements MarriageDao {
  private static String QUERY_SQL =
      "SELECT marriage_code,marriage,remarks from sys_marriage";
   private String QUER_SQL="select count(*) as cnt from sys_marriage where marriage_code='";
    private String UPDATE_SQL = "update sys_marriage set ";
  public MarrDaoImpl ( String dbData ) {
    super( dbData );
  }

  public String getPageSql () {
    return QUERY_SQL;
  }

  public int insert ( VOInterface vo ) throws DefaultException {

    SQLHelper helper = new DefaultSQLHelper( dbData ); ;
    helper.setAutoID( false );
    if ( isRepeat( ( MarriageVO ) vo ) ) {
      throw new DefaultException( "pk" );
    }
    else {
      int iRet = helper.insert( vo );
      return iRet;
    }

  }

  private boolean isRepeat ( com.gever.goa.admin.vo.MarriageVO vo ) throws
      DefaultException {
    String sql =
       QUER_SQL +vo.getMarriage_code() + "'";
    SQLHelper helper = new DefaultSQLHelper( dbData ); ;
    boolean bRet = false;
    Map map = ( HashMap ) helper.query( sql,
                                        com.gever.jdbc.sqlhelper.DataTypes.
                                        RS_SINGLE_MAP );
    if ( com.gever.util.NumberUtil.stringToLong( ( String ) map.get( "cnt" ) ) >
         0 ) {
      bRet = true;

    }
    return bRet;

  }
  public int update(VOInterface vo) throws DefaultException {
      SQLHelper helper = new DefaultSQLHelper(dbData);
     MarriageVO marriageVO = (MarriageVO) vo;
      UPDATE_SQL += "marriage_code='" + marriageVO.getMarriage_code() + "',"
                  + "marriage='" + marriageVO.getMarriage() + "',"
                   +"remarks='"+marriageVO.getRemarks()+"'"
                   +"where marriage_code='"+marriageVO.getMarriage_code_bak()+"'";
               if (isRepeat( (MarriageVO) vo)) {
                 throw new DefaultException("PK");
               }

               if (checkUpdate(vo) != 1) {
                 throw new DefaultException("sys_mod_002");
      }
      try{
          return helper.execUpdate(UPDATE_SQL);
      } catch(DefaultException e){
          throw new DefaultException("sys_mod_001", e);
      }

}

}
