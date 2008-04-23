/*
 * 创建日期 2004-10-25
 */
package com.gever.web.menusetup.dao.impl.oracle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.web.homepage.vo.UserMenuVO;
import com.gever.web.menusetup.dao.impl.DefaultMenuSetupDao;

/**
 * @author Hu.Walker
 */
public class OracleMenuSetupDao extends DefaultMenuSetupDao {

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupDao#resetMenus(java.lang.String)
     */
    public boolean resetMenus(String userId) throws DAOException {
        StringBuffer sbSql = new StringBuffer();
        boolean bRet = true;
        try {
            helper.begin();
            sbSql.append(" SELECT nodeid,flag FROM T_USER_MENU WHERE EMPID=");
            sbSql.append("'").append(userId).append("'");
            UserMenuVO vo = new UserMenuVO();
            List aryData = (ArrayList)helper.queryByListVo(sbSql.toString(),vo);
                        
            sbSql.setLength(0);
            sbSql.append(" DELETE FROM T_USER_MENU WHERE EMPID=");
            sbSql.append("'").append(userId).append("'");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
            sbSql.append("        PARENTID,LINK, ISFOLDER ,ORDERID) ");
            sbSql.append("SELECT '").append(userId).append("'AS EMPID,");
            sbSql.append("    CAST(a.id AS CHAR(10)),a.NAME, ");
            sbSql.append("     CAST (a.parent_id AS CHAR(20)),a.link as link,");
            sbSql.append("      case when (Select COUNT(*) AS CNT  ");
            sbSql.append("      FROM T_RESOURCE b WHERE a.id<>b.id ");
            sbSql.append("      AND b.parent_id=a.id ) >0 then '1' else '0' ");
            sbSql.append("     end AS ISFOLDER,A.ID AS ORDERID ");
            sbSql.append(" FROM T_RESOURCE A ");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(" SELECT nodeid,flag FROM T_USER_MENU WHERE EMPID=");
            sbSql.append("'").append(userId).append("'");
            vo = new UserMenuVO();
            List aryResult = (ArrayList) helper.queryByListVo(sbSql.toString(),
                vo);

            //根据比对,赋值给权限
           StringBuffer sbWhere = new StringBuffer();
           sbSql.setLength(0);
           sbSql.append(" UPDATE T_USER_MENU SET FLAG='1' WHERE EMPID ='");
           sbSql.append(userId).append("'");
           sbSql.append(" AND (");

           int iCount = 0;
           for (int idx = 0; idx <aryResult.size(); idx++){
               UserMenuVO svo = new UserMenuVO();
               svo = (UserMenuVO)aryResult.get(idx);
               for(int row = 0; row <aryData.size(); row++){
                   UserMenuVO dvo = new UserMenuVO();
                   dvo = (UserMenuVO)aryData.get(row);
                   if (dvo.getNodeid().equals(svo.getNodeid()) &&
                      "1".equals(dvo.getFlag())){
                       sbWhere.append(" trim(nodeid) = '").append(svo.getNodeid());
                       sbWhere.append("' OR");
                       iCount++;
                       break;
                   }
               }
           }

           String strWhere = sbWhere.toString();
           if (iCount <=0 ){
               //容错处理
               bRet = false;
               sbSql.append(" 1<>1)");
           } else {
               //去掉 最后面那个or
               sbSql.append(strWhere.substring(0 ,strWhere.length()-2));
               sbSql.append(")");
           }
           helper.execUpdate(sbSql.toString());
           helper.commit(); //事实提交

        } catch (DefaultException e) {
            helper.rollback();
            bRet = false;
            throw new DAOException("ORG_MenuSetup_004", e);

        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                bRet = false;
                throw new DAOException("ORG_MenuSetup_004", e);
            }
        }
        return bRet;

    }

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupDao#queryNextMenus(java.lang.String, java.lang.String)
     */
    public Collection queryNextMenus(String userId, String nodeId) throws DAOException {
        Collection nextMenu = new ArrayList();
        StringBuffer sbSql = new StringBuffer();

        sbSql.append("SELECT NodeID,NodeName,ParentID,Link, ");
        sbSql.append("       IsFolder,IsHided,isMenu,OrderID,memo ");
        sbSql.append("FROM T_USER_MENU ");
        sbSql.append("WHERE (EmpID=?) AND trim(parentid) = ?  AND  flag='1' and isMenu='1' order by OrderID");
        String[] arySql = new String[2];
        arySql[0] = sbSql.toString();
        arySql[1] = "String,String";
        String[] values = new String[2];
        values[0] = userId;
        values[1] = nodeId;
        UserMenuVO vo = new UserMenuVO();

        //查寻数据
        try {
            nextMenu = (ArrayList) helper.query(arySql, values, vo,
                                                DataTypes.RS_LIST_VO);
        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_002", e);
        }
        return nextMenu;

    }

}
