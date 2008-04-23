package com.gever.goa.dailyoffice.targetmng.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.targetmng.dao.WeekTargetDao;
import com.gever.goa.dailyoffice.targetmng.vo.WeekTargetTreeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * Title: 周目标Dao接口实现类 Description: 周目标Dao接口实现类 Copyright: Copyright (c) 2004
 * Company: 天行软件
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class WeekTargetDaoImpl
    extends BaseDAO
    implements WeekTargetDao {
  private static String QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
      + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
      + "audit_flag,audit_opinion,check_flag,"
      + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
      + "a left join t_user b on a.user_code=b.id where 1=1 ";

  private static String TREE_SQL = " Select id as nodeid,name as nodename,case "
      + "when (Select COUNT(*) AS CNT from T_DEPARTMENT  b  WHERE "
      + "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 "
      + "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";


  public void setOracleSQL(){
      QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
     + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
     + "audit_flag,audit_opinion,check_flag,"
     + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
     + "a, t_user b  where a.user_code=b.id(+) ";
  }

  /**
   *重载BaseDAO中更新的方法
   * @param vo VOInterface
   * @throws DefaultException
   * @return int
   *  add by lihaidong
   */
  public int update(VOInterface vo) throws DefaultException {
    if (!super.isOracle()) {
      return super.update(vo);
    }
    int row = 0;
    SQLHelper helper = new DefaultSQLHelper(this.dbData);
    try {
      helper.begin();
      helper.setAutoID(false);
      helper.delete(vo);
      row = helper.insert(vo);
      helper.commit();
    } catch (DefaultException e) {
      helper.rollback();
      throw new DefaultException("更新出错");
    }
    finally {
      helper.end();
    }
    return row;

  }

  /**
   * 封装order by 语句
   * @return String
   */
  protected String getQuerySQLByOrderBy() {
    StringBuffer sbSql = new StringBuffer();
    if ("current_month".equalsIgnoreCase(super.getOrderColumn())) {
      sbSql.append(this.getPageSql()).append(" ").append(super.getSqlWhere());
      sbSql.append(" order by cast(").append(super.getOrderColumn()).append(" as int )");
      sbSql.append(" ").append(super.getOrderType());
      return sbSql.toString();
    } else {
      return super.getQuerySQLByOrderBy();
    }

  }

  //QUERY_SQL--定义翻页时用的查询语句
  public WeekTargetDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  public List getTreeData(String nodeid,String curDeptId) throws DefaultException {
        System.out.println("---------------------getTreeDate In Impl---------------------------");
        List treeData = new ArrayList();
        String strWhere = "";
        if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //判断0--根结点
          if("0".equals(nodeid)){
            strWhere = " and parent_id = 0 and id=" + curDeptId; //获取第一级的目录节点
          }else{
            strWhere=" and id="+curDeptId;
          }
        } else {
            strWhere = " and parent_id =" + nodeid; //否则获取拥有父节点的那些节点
        }

        WeekTargetTreeVO vo = new WeekTargetTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
        return treeData;
    }

    /**
     *
     * @param nodeid String
     * @throws DefaultException
     * @return List
     */
    public List getTreeData(String nodeid) throws DefaultException {
        System.out.println("----------getTreeDate In Impl-----------------");
        List treeData = new ArrayList();
        String strWhere = "";
        if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //判断0--根结点
            strWhere = " and parent_id = 0 "; //获取第一级的目录节点
        } else {
            strWhere = " and parent_id =" + nodeid; //否则获取拥有父节点的那些节点
        }
        WeekTargetTreeVO vo = new WeekTargetTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
        return treeData;
    }
}
