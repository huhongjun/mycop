package com.gever.goa.dailyoffice.worklog.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.dao.PersonMonthTargetDao;
import com.gever.goa.dailyoffice.worklog.vo.PersonMonthTargetTreeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * Title: 人月目标Dao接口实现类
 * Description: 人月目标Dao接口实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public class PersonMonthTargetDaoImpl
    extends BaseDAO
    implements PersonMonthTargetDao {
    private static String QUERY_SQL = "SELECT SERIAL_NUM, USER_CODE, CURRENT_YEAR, CURRENT_MONTH, CURRENT_WORK, TARGET_TYPE, "
      +" DEPT_CODE, POST_CODE, CREATE_DATE, AUDITOR, AUDIT_FLAG, AUDIT_DATE, AUDIT_OPINION, "
      +"  TARGET_CONTENT FROM DO_PERSONAL_TARGET a left join t_user b on a.user_code=b.id ";

  private static String TREE_SQL = " Select id as nodeid,name as nodename,case " +
        "when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE " +
        "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 " +
        "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";

    /**
     * 设置ORACLE SQL语句
     */
    public void setOracleSQL(){
     QUERY_SQL="SELECT SERIAL_NUM, USER_CODE, CURRENT_YEAR, CURRENT_MONTH, CURRENT_WORK, TARGET_TYPE, "
         + " DEPT_CODE, POST_CODE, CREATE_DATE, AUDITOR, AUDIT_FLAG, AUDIT_DATE, AUDIT_OPINION, "
         +" TARGET_CONTENT FROM DO_PERSONAL_TARGET a, t_user b  where a.user_code=b.id(+) ";
   }


//QUERY_SQL--定义翻页时用的查询语句
  public PersonMonthTargetDaoImpl(String dbData) {
    super(dbData);
  }

  public String getPageSql() {
    return QUERY_SQL;
  }

  /**
   *
   * @param nodeid String
   * @param curDeptId String
   * @throws DefaultException
   * @return List
   */
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

    PersonMonthTargetTreeVO vo = new PersonMonthTargetTreeVO();
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
        List treeData = new ArrayList();
        String strWhere = "";
        if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //判断0--根结点
            strWhere = " and parent_id = 0 "; //获取第一级的目录节点
        } else {
            strWhere = " and parent_id =" + nodeid; //否则获取拥有父节点的那些节点
        }
        PersonMonthTargetTreeVO vo = new PersonMonthTargetTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData); ;
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
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
