package com.gever.goa.dailyoffice.workreport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.workreport.dao.DoWorkReportDao;
import com.gever.goa.dailyoffice.workreport.util.WorkReportWhereTypes;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * <p>Title:���а칫�Զ���ƽ̨</p>
 * <p>Description:�����㱨DoWorkReportģ���DAO IMPL  </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DoWorkReportDaoImpl
    extends BaseDAO
    implements DoWorkReportDao {
  private String pageSql;
  private WorkReportWhereTypes workReportWhereTypes;
  public DoWorkReportDaoImpl(String dbData) {
    super(dbData);
    if (super.isOracle()) {
      this.pageSql = WorkReportWhereTypes.QueryAll_Oracle;
    }
    else {
      this.pageSql = WorkReportWhereTypes.QueryAll_DB2;
    }
  }

  public String getPageSql() {
    return pageSql;
  }

  public List getTreeData() throws DefaultException {
    /**
     * ����userid�õ�һ��WorkReportWhereTypes����
     */
    workReportWhereTypes = new WorkReportWhereTypes(super.userID);
    List treeData = new ArrayList();
    /**
     * ����tree�ڵ�ļ���
     */
    treeData = workReportWhereTypes.getAllWhereTypes();
    return treeData;
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
