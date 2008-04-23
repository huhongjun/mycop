/*
 * �������� 2004-10-25
 */
package com.gever.web.homepage.dao.impl.oracle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.impl.DefaultResource;
import com.gever.web.homepage.dao.impl.DefaultUserMenuDao;
import com.gever.web.homepage.vo.UserMenuVO;

/**
 * @author Hu.Walker
 */
public class OracleUserMenuDao extends DefaultUserMenuDao {
	/**
	 * ���û����¸��Ĺ�Ȩ��ʱ � 2004-12-01 �޸� ���ò˵�ʱ�����û��Զ���˵�����
	 * 
	 * @param userID
	 *            ��ǰ�û�ID
	 * @param resourceList
	 *            ��ǰ�û���Ӧ��Դ����
	 * @return ���ò˵��Ƿ�ɹ�
	 * @throws DefaultException
	 */
	public boolean resetUserMenus(String userID, Collection resourceList)
			throws DAOException {
		boolean bRet = true;

		//log.showLog("------------start---");
		String strMethod = "resetUserMenus(String userID, Collection moduleList)\n";

		//          List aryResult = resourceList;
		StringBuffer sbSql = new StringBuffer();

		try {
			helper.begin();

			//��ͳ�Ƴ�����
			sbSql.setLength(0);
			sbSql.append(" delete from T_USER_MENU  WHERE EMPID='");
			sbSql.append(userID).append("' and (nodeid in (select cast(id as varchar2(20)) from T_RESOURCE)");
			sbSql.append(" and parentid in (select cast(parent_id as varchar2(20)) from T_RESOURCE)) ");

			int i = helper.execUpdate(sbSql.toString());

			sbSql.setLength(0);
			sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
			sbSql.append(" PARENTID,LINK,ISFOLDER,ORDERID) ");
			sbSql.append(" SELECT '").append(userID).append("' AS EMPID,");
			sbSql.append(" CAST(A.id AS CHAR(10)),A.NAME,");
			sbSql.append(" CAST (A.parent_id AS CHAR(20)),A.link as link,");
			sbSql.append(" case when (Select COUNT(*) AS CNT ");
			sbSql.append(" FROM T_RESOURCE B WHERE A.id<>B.id ");
			sbSql.append(" AND B.parent_id=A.id ) >0 then '1' else '0' ");
			sbSql.append(" end AS ISFOLDER,A.ID AS ORDERID ");
			sbSql.append(" FROM T_RESOURCE  A ");
			sbSql.append(" where cast(A.id as CHAR(10)) not in (");
			sbSql.append(" select nodeid from T_USER_MENU where empid='");
			sbSql.append(userID).append("')");

			//log.showLog("------sbSql----1-" + sbSql.toString());
			helper.execUpdate(sbSql.toString());

			//������еķ��û��Զ���˵�������
			List aryMenus = new ArrayList();
			UserMenuVO menuVo = new UserMenuVO();

			sbSql.setLength(0);
			sbSql.append("SELECT EMPID,NODEID,NODENAME,");
			sbSql.append("PARENTID,LINK, ISFOLDER ,ORDERID,");
			sbSql.append("FLAG,MEMO ");
			sbSql.append("FROM T_USER_MENU where empid='").append(userID).append("'");
			sbSql.append(" and nodeid in (select cast(id as CHAR(20)) from T_RESOURCE)");

			aryMenus = (ArrayList) helper.query(sbSql.toString(), menuVo,
					DataTypes.RS_LIST_VO);

			//���ݱȶ�,��ֵ��Ȩ��
			StringBuffer sbWhere = new StringBuffer();
			sbSql.setLength(0);
			sbSql.append(" UPDATE T_USER_MENU SET FLAG='1' WHERE EMPID ='");
			sbSql.append(userID).append("'");
			sbSql.append(" AND (");

			//log.showLog("------sbSql---3--" + sbSql.toString());
			int iCount = 0;

			if (resourceList == null) {
				resourceList = new ArrayList();
			}

			java.util.Iterator aryResult = resourceList.iterator();

			while (aryResult.hasNext()) {
				//for (int idx = 0; idx < aryResult.size(); idx++) {
				Resource vo = new DefaultResource();
				vo = (DefaultResource) aryResult.next();

				for (int row = 0; row < aryMenus.size(); row++) {
					menuVo = new UserMenuVO();
					menuVo = (UserMenuVO) aryMenus.get(row);

					if (menuVo.getNodeid().trim().equals(
							String.valueOf(vo.getId()))) {
						sbWhere.append(" nodeid = '").append(vo.getId());
						sbWhere.append("' OR");
						iCount++;

						break;
					}
				}
			}

			//log.showLog("------sbSql---4--" + sbSql.toString());
			if (iCount <= 0) {
				//�ݴ���
				bRet = false;
				sbWhere.append(" 1<> 1)");
				sbSql.append(sbWhere.toString());
			} else {
				//ȥ�� ������Ǹ�or
				sbSql.append(sbWhere.toString().substring(0,
						sbWhere.toString().length() - 2));
				sbSql.append(")");
			}

			helper.execUpdate(sbSql.toString());

			helper.commit(); //��ʵ�ύ
		} catch (DefaultException e) {
			e.printStackTrace();
			e.printStackTrace(System.err);
			helper.rollback();
			bRet = false;
			throw new DAOException("ORG_Homepage_002", e);
		} finally {
			try {
				helper.end();
			} catch (DefaultException e) {
				bRet = false;
				throw new DAOException("ORG_Homepage_002", e);
			}
		}

		return bRet;
	}

	//    public boolean resetUserMenus(String userID, Collection resourceList)
	// throws DAOException {
	//        boolean bRet = true;
	//        //log.showLog("------------start---");
	//        String strMethod =
	//            "resetUserMenus(String userID, Collection moduleList)\n";
	//// List aryResult = resourceList;
	//
	//        StringBuffer sbSql = new StringBuffer();
	//
	//        try {
	//            helper.begin();
	//            //��ͳ�Ƴ�����
	//            sbSql.setLength(0);
	//           sbSql.append(" delete from T_USER_MENU WHERE EMPID ='");
	//           sbSql.append(userID).append("' ");
	//           helper.execUpdate(sbSql.toString());
	//           
	//           sbSql.setLength(0);
	//
	//            sbSql.append(" SELECT COUNT(*) AS CNT FROM T_USER_MENU where EMPid='");
	//            sbSql.append(userID).append("'");
	//
	//            Map map = (HashMap) helper.query(sbSql.toString(),
	//                                             DataTypes.RS_SINGLE_MAP);
	//            long lngCnt = NumberUtil.stringToLong( (String) map.get("cnt"), 0);
	//            
	//            //log.showLog("------sbSql-----" + sbSql.toString());
	//            //���С�ڻ���0��ʱ��,��Ҫ����Դ���ȡ����
	//
	//            if (lngCnt <= 0l) {
	//                sbSql.setLength(0);
	//                sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
	//                sbSql.append(" PARENTID,LINK, ISFOLDER ,ORDERID) ");
	//                sbSql.append("SELECT '").append(userID).append("'AS EMPID,");
	//                sbSql.append(" CAST(A.id AS CHAR(10)),A.NAME, ");
	//                sbSql.append(
	//                    " CAST (A.parent_id AS CHAR(20)),A.link as link,");
	//                sbSql.append(" case when (Select COUNT(*) AS CNT ");
	//                sbSql.append(" FROM T_RESOURCE B WHERE A.id<>B.id ");
	//                sbSql.append(
	//                    " AND B.parent_id=A.id ) >0 then '1' else '0' ");
	//                sbSql.append(" end AS ISFOLDER,A.ID AS ORDERID ");
	//                sbSql.append(" FROM T_RESOURCE A ");
	//                //log.showLog("------sbSql----1-" + sbSql.toString());
	//                helper.execUpdate(sbSql.toString());
	//
	//            } else {
	//                sbSql.setLength(0);
	//                sbSql.append(" UPDATE T_USER_MENU SET FLAG='0' WHERE EMPID ='");
	//                sbSql.append(userID).append("' and isFolder='0' ");
	//                helper.execUpdate(sbSql.toString());
	//            }
	//
	//            //������еĲ˵�������
	//            List aryMenus = new ArrayList();
	//            UserMenuVO menuVo = new UserMenuVO();
	//
	//            sbSql.setLength(0);
	//            sbSql.append("SELECT EMPID,NODEID,NODENAME,");
	//            sbSql.append(" PARENTID,LINK, ISFOLDER ,ORDERID, ");
	//            sbSql.append(" FLAG,MEMO ");
	//            sbSql.append("FROM T_USER_MENU where
	// empid='").append(userID).append("'");
	//
	//            aryMenus = (ArrayList) helper.query(sbSql.toString(), menuVo,
	//                                                DataTypes.RS_LIST_VO);
	//            
	//            //���ݱȶ�,��ֵ��Ȩ��
	//            StringBuffer sbWhere = new StringBuffer();
	//            sbSql.setLength(0);
	//            sbSql.append(" UPDATE T_USER_MENU SET FLAG='1' WHERE EMPID ='");
	//            sbSql.append(userID).append("'");
	//            sbSql.append(" AND (");
	//            //log.showLog("------sbSql---3--" + sbSql.toString());
	//            int iCount = 0;
	//            if (resourceList == null){
	//                resourceList = new ArrayList();
	//            }
	//            java.util.Iterator aryResult = resourceList.iterator();
	//            while(aryResult.hasNext()){
	//
	//            //for (int idx = 0; idx < aryResult.size(); idx++) {
	//                Resource vo = new DefaultResource();
	//                vo = (DefaultResource) aryResult.next();
	//                for (int row = 0; row < aryMenus.size(); row++) {
	//                    menuVo = new UserMenuVO();
	//                    menuVo = (UserMenuVO) aryMenus.get(row);
	//
	//                    if (menuVo.getNodeid().trim().equals(String.valueOf(vo.getId()))) {
	//                        sbWhere.append(" trim(nodeid) = '").append(vo.getId());
	//                        sbWhere.append("' OR");
	//                        iCount++;
	//                        break;
	//                    }
	//                }
	//            }
	//
	//            //log.showLog("------sbSql---4--" + sbSql.toString());
	//            
	//            if (iCount <= 0) {
	//                //�ݴ���
	//                bRet = false;
	//                sbWhere.append(" 1<> 1)");
	//                sbSql.append(sbWhere.toString());
	//            } else {
	//                //ȥ�� ������Ǹ�or
	//                sbSql.append(sbWhere.toString().substring(0,
	//                    sbWhere.toString().length() - 2));
	//                sbSql.append(")");
	//            }
	//            
	//            int result = helper.execUpdate(sbSql.toString());
	//
	//            helper.commit(); //��ʵ�ύ
	//
	//        } catch (DefaultException e) {
	//            e.printStackTrace();
	//          e.printStackTrace(System.err);
	//            helper.rollback();
	//            bRet = false;
	//            throw new DAOException("ORG_Homepage_002", e);
	//        } finally {
	//            try {
	//                helper.end();
	//            } catch (DefaultException e) {
	//                bRet = false;
	//                throw new DAOException("ORG_Homepage_002", e);
	//            }
	//
	//        }
	//        return bRet;
	//
	//    }
}