package com.gever.goa.dailyoffice.reportmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.reportmgr.dao.TargetDao;
import com.gever.goa.dailyoffice.reportmgr.vo.TargetTreeVo;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description:目标管理实现类 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TargetDaoImpl extends BaseDAO implements TargetDao {

    private static String QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
                        + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
                        + "audit_flag,audit_opinion,check_flag,"
                        + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE";

    private static String TREE_SQL = "SELECT  info_type as nodeid, info_type as nodename,  "
                        +" case when ( Select count(*) from IS_DOTYPE b "
                        +" where b.parent_type=a.info_type )>0  then 1 else 0 end as isfolder from is_dotype a where 1=1 ";

    public TargetDaoImpl(String dbData) {
        super(dbData);
    }
    /**
     * 返回SQL语句
     * @return String
     */
    public String getPageSql() {
                return QUERY_SQL;
    }

    /**
       *重载BaseDAO中更新的方法
       * @param vo VOInterface
       * @throws DefaultException
       * @return int
       *  add by lihaidong
       */
      public int update(VOInterface vo) throws DefaultException {
          if(!super.isOracle()){
              return super.update(vo);
          }
          int row=0;
          SQLHelper helper =new DefaultSQLHelper(this.dbData);
          try{
              helper.begin();
              helper.setAutoID(false);
              helper.delete(vo);
              row = helper.insert(vo);
          }catch(DefaultException e){
              helper.rollback();
              throw new DefaultException("更新出错");
          }finally{
              helper.end();
          }
          return row;

      }



    /**
     * getTreeData
     *
     * @param nodedid String
     * @return List
     */
    public List getTreeData(String nodeid) throws DefaultException {
        List treeData = new ArrayList();
                String strWhere = "";
                strWhere=" and parent_type='"+nodeid +"'";
                System.out.println("The strWhere is:===="+strWhere);
                TargetTreeVo vo = new TargetTreeVo();
                SQLHelper helper = helper = new DefaultSQLHelper(dbData);
                treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
                return treeData;

    }
}
