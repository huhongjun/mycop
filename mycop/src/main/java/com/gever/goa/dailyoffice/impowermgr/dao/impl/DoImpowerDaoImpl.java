package com.gever.goa.dailyoffice.impowermgr.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: 授权管理Dao接口实现类
 * Description: 授权管理Dao接口实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class DoImpowerDaoImpl
    extends BaseDAO
    implements DoImpowerDao {
  private static String QUERY_SQL = "SELECT SERIAL_NUM,CREATE_TIME,DO_IMPOWER.ACCEPTER AS ACCEPTER,DO_IMPOWER.PAYER AS PAYER,BEGIN_TIME,END_TIME,COMMENTS,NOTICE,CONTENT,SEND_FLAG,B.NAME AS ACCEPTERNAME,C.NAME AS PAYERNAME FROM DO_IMPOWER " +
      " LEFT OUTER JOIN T_USER AS B ON DO_IMPOWER.ACCEPTER=cast(b.id as char(20)) " +
      " LEFT OUTER JOIN T_USER as C ON DO_IMPOWER.PAYER=cast(c.id as char(20)) " +
      " WHERE 1=1 ";

//QUERY_SQL--定义翻页时用的查询语句
  public DoImpowerDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public void setOracleSQL() {
    QUERY_SQL =
        "SELECT SERIAL_NUM,CREATE_TIME,DO_IMPOWER.ACCEPTER AS ACCEPTER,DO_IMPOWER.PAYER AS PAYER,BEGIN_TIME,END_TIME,COMMENTS,NOTICE,SEND_FLAG,B.NAME AS ACCEPTERNAME,C.NAME AS PAYERNAME,CONTENT FROM DO_IMPOWER,T_USER  B,T_USER C " +
        " WHERE 1=1 " +
        " AND cast(DO_IMPOWER.ACCEPTER as integer)=B.ID(+) " +
        " AND cast(DO_IMPOWER.PAYER as integer)=C.ID(+)";
  }

  /**
   * 翁乃彬新增
   * @todo 因为oracle的不同,所以先删除,后新增
   * @param vo 当前vo对象
   * @return 修改的记录数
   * @throws DefaultException
   */
   public int update(VOInterface vo) throws DefaultException {
       //如果不是oracle
       if (!super.isOracle()){
           return super.update(vo);
       }

       //如果是oracle的时候处理
       SQLHelper helper = new DefaultSQLHelper(dbData); ;
       int iRet = -1;
       try {
           helper.begin();
           helper.delete(vo);
           helper.setAutoID(false);
           iRet =  helper.insert(vo);
           helper.commit();
       } catch (DefaultException e) {
           helper.rollback();
           throw new DefaultException(e);
       } finally {
           helper.end();
       }
       return iRet;

   }
}
