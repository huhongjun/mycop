package com.gever.goa.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.UnitDao;
import com.gever.goa.admin.vo.UnitVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 单位程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UnitDaoImpl
    extends BaseDAO
    implements UnitDao {
  private static String QUERY_SQL =
      "SELECT unit_code,unit_name,phone,fax,e_mail,home_page,remark,unit_type from sys_unit_info";
   private String QUER_SQL=" select count(*) as cnt from sys_unit_info ";
   private String UPDATE_SQL="update sys_unit_info set ";
   private String QUER1_SQL="select count(*) as cnt from sys_unit_info where unit_code='";
  public UnitDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

 public int update(VOInterface vo) throws DefaultException {
   SQLHelper helper = new DefaultSQLHelper(dbData);
   UnitVO unitVO=(UnitVO)vo;
   UPDATE_SQL+="unit_code='"+unitVO.getUnit_code()+"',"
       +"unit_name='"+unitVO.getUnit_name()+"',"
       +"phone='"+unitVO.getPhone()+"',"
       +"fax='"+unitVO.getFax()+"',"
       +"e_mail='"+unitVO.getE_mail()+"',"
       +"home_page='"+unitVO.getHome_page()+"',"
       +"unit_type="+unitVO.getUnit_type()+","
       +"remark='"+unitVO.getRemark()+"'";


        if (checkUpdate(vo) != 1) {
            throw new DefaultException("sys_mod_002");
        }

        try{
            return helper.execUpdate(UPDATE_SQL);
        } catch(DefaultException e){
            throw new DefaultException("sys_mod_001", e);
        }

 }
  public int insert(VOInterface vo) throws DefaultException {
      SQLHelper helper = new DefaultSQLHelper(dbData);


     Map map = (HashMap) helper.query(QUER_SQL,
                                    com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
   if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0){
   throw new DefaultException("sys_no_insert");
   }

     helper.setAutoID(false);
     if (isRepeat((UnitVO)vo)) {
         throw new DefaultException("PK repeat!");
       }
       else{
         int iRet = helper.insert(vo);
       return iRet;
       }


   }


  private boolean isRepeat(UnitVO vo) throws DefaultException{
    String sql = QUER1_SQL + vo.getUnit_code() + "'";
    SQLHelper helper = new DefaultSQLHelper(dbData);
    boolean bRet = false;
    Map map = (HashMap) helper.query(sql,
                                     com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);
    if (com.gever.util.NumberUtil.stringToLong( (String) map.get("cnt")) > 0) {
      bRet = true;
    }
    return bRet;

  }
}
