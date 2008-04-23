package com.gever.goa.dailyoffice.impowermgr.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.impowermgr.dao.DoImpowerDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: ��Ȩ����Dao�ӿ�ʵ����
 * Description: ��Ȩ����Dao�ӿ�ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
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

//QUERY_SQL--���巭ҳʱ�õĲ�ѯ���
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
   * ���˱�����
   * @todo ��Ϊoracle�Ĳ�ͬ,������ɾ��,������
   * @param vo ��ǰvo����
   * @return �޸ĵļ�¼��
   * @throws DefaultException
   */
   public int update(VOInterface vo) throws DefaultException {
       //�������oracle
       if (!super.isOracle()){
           return super.update(vo);
       }

       //�����oracle��ʱ����
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
