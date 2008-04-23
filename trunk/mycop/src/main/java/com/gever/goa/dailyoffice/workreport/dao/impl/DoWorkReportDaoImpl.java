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
 * <p>Title:天行办公自动化平台</p>
 * <p>Description:工作汇报DoWorkReport模块的DAO IMPL  </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
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
     * 根据userid得到一个WorkReportWhereTypes对象
     */
    workReportWhereTypes = new WorkReportWhereTypes(super.userID);
    List treeData = new ArrayList();
    /**
     * 返回tree节点的集合
     */
    treeData = workReportWhereTypes.getAllWhereTypes();
    return treeData;
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
