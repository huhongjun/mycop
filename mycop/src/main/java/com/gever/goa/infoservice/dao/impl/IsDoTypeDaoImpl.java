package com.gever.goa.infoservice.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsDoTypeDao;
import com.gever.goa.infoservice.vo.IsDoTypeTreeVO;
import com.gever.goa.infoservice.vo.IsDoTypeVO;
import com.gever.goa.infoservice.vo.IsInfoManageVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * <p>
 * Title: 信息类型设置DAO实现类
 * </p>
 * <p>
 * Description: KOBE OFFICE 版权所有，违者必究！
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: KOBE OFFICE
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */
public class IsDoTypeDaoImpl extends BaseDAO implements IsDoTypeDao {
	// 查询语句
	private static StringBuffer querySQL = new StringBuffer();

	// 类型设置动态树的查询
	private static StringBuffer treeSQL = new StringBuffer();

	// 更新数据语句
	private static String UPDATE_SQL1 = " update is_dotype set ";

	private static String UPDATE_SQL2 = " update is_dotype set ";

	// 取得类型级别SQL
	private static String GETTYPELEVER_SQL = " select typelevel from is_dotype where ";
	private static String GETREMARK_SQL = " select remark from is_dotype where ";

	// 按模块类别分类SQL
	private static String QUERYBYMODULEFLAG = " select info_type from is_dotype where ";

	// 数据重复查询
	private static String REPEAT_SQL = " SELECT count(*) as cnt FROM is_dotype WHERE ";

	static {
		querySQL.append(" SELECT ");
		querySQL.append(" IS_DOTYPE.INFO_TYPE, ");
		querySQL.append(" IS_DOTYPE.TYPELEVEL, ");
		querySQL.append(" IS_DOTYPE.PARENT_TYPE, ");
		querySQL.append(" IS_DOTYPE.MODULEFLAG, ");
		querySQL.append(" IS_DOTYPE.REMARK ");
		querySQL.append(" FROM ");
		querySQL.append(" IS_DOTYPE ");
		querySQL.append(" WHERE ");
		querySQL.append(" 1=1 ");
	}

	static {
		treeSQL.append(" SELECT ");
		treeSQL.append(" info_type as nodeid, remark as nodename, ");
		treeSQL.append(" case when ");
		treeSQL.append("( Select count(*) from IS_DOTYPE b ");
		treeSQL.append(" where b.parent_type=a.info_type )>0 ");
		treeSQL.append(" then 1 else 0 end as isfolder, ");
		treeSQL.append(" moduleflag as memo");
		treeSQL.append(" from is_dotype a where 1=1 ");
	}

	public IsDoTypeDaoImpl(String dbData) {
		super(dbData);
	}

	/**
	 * 连接oracle数据库SQL
	 */
	public void setOracleSQL() {
	}

	// 取得类型设置树的数据
	public List getTreeData(String paraFlag, String nodeid)
			throws DefaultException {
		List treeData = new ArrayList();
		IsDoTypeTreeVO VO = new IsDoTypeTreeVO();
		SQLHelper helper = helper = new DefaultSQLHelper(dbData);
		String sql = treeSQL.toString();

		if (!StringUtils.isNull(nodeid)) {
			sql += (" and parent_type='" + nodeid + "'");
		} else {
			sql += " and ( parent_type='' or parent_type is null )";
		}

		sql += (" and moduleflag='" + paraFlag + "'");
		treeData = (List) helper.queryByListVo(sql, VO);

		return treeData;
	}

	public String getPageSql() {
		return querySQL.toString();
	}

	/**
	 * 重载
	 */
	public int insert(VOInterface VO) throws DefaultException {
		IsDoTypeVO vo = (IsDoTypeVO) VO;
		SQLHelper helper = new DefaultSQLHelper(dbData);

		if (checkInsert(vo) != 1) {
			throw new DefaultException("goa_sys_001");
		}

		String typeLevel;
		String parentType = vo.getParent_type();

		if (parentType.equals("")) {
			typeLevel = "1";
		} else {
			String parentlevel = this.getTypeLevel(parentType, helper);
			typeLevel = String.valueOf(Integer.parseInt(parentlevel) + 1);
		}

		vo.setTypelevel(typeLevel);
		helper.setAutoID(false);

		// 得到VO异常，抛出PK REPEAT!
		if (isRepeat((IsDoTypeVO) vo)) {
			throw new DefaultException("PK repeat!");
		} else {
			int iRet = helper.insert(vo);

			return iRet;
		}

		// int iRet = 0;
		//
		// try {
		// iRet = helper.insert(vo);
		// } catch (Exception e) {
		// // TODO: handle exception
		// throw new DefaultException("类别名称重复，请重新输入", e);
		// }
		//
		// return iRet;
	}

	// 定义一个查询主键重复的方法
	private boolean isRepeat(IsDoTypeVO vo) throws DefaultException {
		String sql = REPEAT_SQL + " info_type='" + vo.getInfo_type() + "'";
		SQLHelper helper = new DefaultSQLHelper(dbData);
		boolean bRet = false;
		Map map = (HashMap) helper.query(sql,
				com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);

		if (com.gever.util.NumberUtil.stringToLong((String) map.get("cnt")) > 0) {
			bRet = true;
		}

		return bRet;
	}

	/**
	 * 删除所有的资料
	 * 
	 * @param aryPk
	 *            主键数组
	 * @param vo
	 *            当前VO对象
	 * @return 删除成功多少笔,-1为失败
	 * @throws DefaultException
	 */
	public int delBySelect(String[] aryPk, VOInterface vo)
			throws DefaultException {
		int intRet = 0;
		SQLHelper helper = helper = new DefaultSQLHelper(dbData);
		;

		try {
			helper.begin();
			String name = "";
			String value = "";
			for (int idx = 0; idx < aryPk.length; idx++) {
				vo = getInstanceVO(vo);
				StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
				StringTokenizer stkPk = new StringTokenizer(vo.getPkFields(),
						",");
				while (stk.hasMoreTokens()) {
					name = stkPk.nextToken();
					value = stk.nextToken();
					vo.setValue(name, value);
				}
				intRet += helper.delete(vo);
			}
			helper.commit();
		} catch (DefaultException e) {
			intRet = -1;
			helper.rollback();
			throw new DefaultException("PK");

		} finally {
			helper.end();
		}
		return intRet;

	}

	public int update(VOInterface VO) throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(dbData);
		IsDoTypeVO vo = (IsDoTypeVO) VO;

		String sql = UPDATE_SQL1;

		if (!"".equals(vo.getInfo_type())) {
			sql = sql + "info_type='" + vo.getInfo_type() + "'";
		}

		if (!"".equals(vo.getTypelevel())) {
			sql = sql + ",typelevel=" + vo.getTypelevel();
		}

		if (!"".equals(vo.getParent_type())) {
			sql = sql + ",parent_type='" + vo.getParent_type() + "'";
		}

		if (!"".equals(vo.getModuleflag())) {
			sql = sql + ",moduleflag='" + vo.getModuleflag() + "'";
		}

		sql = sql + ",remark='" + vo.getRemark() + "'";

		sql = sql + " where " + "info_type='" + vo.getOld_info_type() + "'";

		String sql2 = UPDATE_SQL2;

		if (!"".equals(vo.getInfo_type())) {
			sql2 = sql2 + "parent_type='" + vo.getInfo_type() + "'";
		}

		sql2 = sql2 + " where " + "parent_type='" + vo.getOld_info_type() + "'";

		// helper.execUpdate(sql2.toString());
		// 得到VO异常，抛出PK REPEAT!
		// if (isRepeat((IsDoTypeVO) vo)) {
		// throw new DefaultException("PK repeat!");
		// } else {

		helper.execUpdate(sql2.toString());

		// }

		return helper.execUpdate(sql.toString());
	}

	private String getTypeLevel(String info_type, SQLHelper helper)
			throws DefaultException {
		String sql = GETTYPELEVER_SQL + "info_type='" + info_type + "'";
		Map map = (Map) helper.query(sql, DataTypes.RS_SINGLE_MAP);
		String retStr = (String) map.get("typelevel");

		return retStr;
	}

	public String getRemark(String info_type)
			throws DefaultException {
		SQLHelper helper = new DefaultSQLHelper(dbData);
		
		String sql = GETREMARK_SQL + "info_type='" + info_type + "'";
		Map map = (Map) helper.query(sql, DataTypes.RS_SINGLE_MAP);
		String retStr = (String) map.get("remark");

		return retStr;
	}

	/**
	 * @param 类别按模块分类
	 */
	public List queryByModuleFlag(String paraFlag, VOInterface vo)
			throws DefaultException {
		List queryByModuleFlag = new ArrayList();
		IsInfoManageVO isinfomanageVO = new IsInfoManageVO();

		SQLHelper helper = helper = new DefaultSQLHelper(dbData);

		if (!StringUtils.isNull(paraFlag)) {
			String sql = QUERYBYMODULEFLAG + "moduleflag='" + paraFlag + "'";
			queryByModuleFlag = (ArrayList) helper.queryByListVo(sql,
					isinfomanageVO);
		} else {
			String sql = QUERYBYMODULEFLAG
					+ "(moduleflag='' or moduleflag is null)";
			queryByModuleFlag = (ArrayList) helper.queryByListVo(sql,
					isinfomanageVO);
		}

		return queryByModuleFlag;
	}
}
