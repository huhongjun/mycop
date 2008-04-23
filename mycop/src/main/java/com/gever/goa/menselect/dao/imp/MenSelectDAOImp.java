package com.gever.goa.menselect.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gever.exception.DefaultException;
import com.gever.goa.menselect.dao.MenSelectDAO;
import com.gever.goa.menselect.vo.ConstCodeVO;
import com.gever.goa.menselect.vo.MenSelectVO;
import com.gever.goa.util.DepartmentStructure;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.api.OrganizationUtil;

public class MenSelectDAOImp extends BaseDAO implements MenSelectDAO {
	public MenSelectDAOImp(String dbData) {
		super(dbData);
	}

	private String userId = "";

	private static String GET_USER_SQL = "Select ID,name from T_USER ORDER BY NAME";

	private static String GET_DEPT_SQL = "select ID,name,parent_id from T_DEPARTMENT ";

	private String GET_ADDRESS_LIST_SQL = "Select group_name,member from "
			+ " IS_ADDRESS_LIST where type_flag = 0 "
			+ " and and user_code  = '" + userId + "'";

	private static String QRY_CARD_SQL = "Select a.customer,a.e_mail,c.type_code,c.type_name "
			+ " from DO_CARDCASE as a left join (select b.user_code, "
			+ " b.type_name,b.type_code,t_user.name from DO_CARDCASE_type as b "
			+ " left join t_user on t_user.id =b.user_code ) as c "
			+ " on a.type_code = c.type_code "
			+ " where 1=1 and length(a.e_mail)>0 and a.type_code='-1' or c.user_code= ";

	private String GET_THEAM_SQL = "SELECT SERIAL_NUM,FNAME FROM ARC_PARAM WHERE FTYPE=0";

	public static String WEB_SPACE = "&nbsp;&nbsp;";

	OrganizationUtil sysmnguitl = new OrganizationUtil();

	public ArrayList getUsersInfo() {
		ArrayList result = new ArrayList();
		SQLHelper helper = new DefaultSQLHelper(dbData);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = helper.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_USER_SQL);
			while (rs.next()) {
				MenSelectVO menInfo = new MenSelectVO();
				menInfo.setId(rs.getString(1));
				menInfo.setName(rs.getString(2));
				menInfo.setDeptid(getUserRelateInfo("T_DEPARTMENT_PERSON",
						"department_id", rs.getString(1), conn));
				menInfo.setJobid(getUserRelateInfo("T_JOB_PERSON", "job_id", rs
						.getString(1), conn));
				result.add(menInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefaultException ex) {
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return result;
	}

	public ArrayList getUsersInfo(boolean isRa, String org) {
		ArrayList result = new ArrayList();
		SQLHelper helper = new DefaultSQLHelper(dbData);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String filterStr = "";
		String sql = "";
		if (isRa == false) {
			filterStr = "not";
			// sql = "SELECT a.ID,a.NAME FROM T_USER a, "
			// + "(select x.id from t_department_person x where ( " + filterStr
			// + " exists (select y.id from sm_org y where
			// y.id=x.department_id))"
			// + ")z "
			// + "where z.id=a.id "
			// + "order by a.USERNAME";
			// ----------------修改人员选择重复
			sql = "SELECT distinct a.ID,a.NAME FROM T_USER a, "
					+ "(select x.id from t_department_person x where ( "
					+ filterStr
					+ " exists (select y.id from sm_org y where y.id=x.department_id))"
					+ ")z " + "where z.id=a.id ";

		} else {
			sql = "SELECT a.ID,a.NAME FROM T_USER a, "
					+ "(select x.id from t_department_person x where ( "
					+ " exists (select y.id from sm_org y where y.id=x.department_id))"
					+ "and x.department_id = " + org + ")z "
					+ "where z.id=a.id " + "order by a.USERNAME";
		}
		log.showLog("========> getUsersInfo query sql: " + sql);
		try {
			conn = helper.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MenSelectVO menInfo = new MenSelectVO();
				menInfo.setId(rs.getString(1));
				menInfo.setName(rs.getString(2));
				menInfo.setDeptid(getUserRelateInfo("T_DEPARTMENT_PERSON",
						"department_id", rs.getString(1), conn));
				menInfo.setJobid(getUserRelateInfo("T_JOB_PERSON", "job_id", rs
						.getString(1), conn));
				result.add(menInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefaultException ex) {
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return result;
	}

	public ArrayList getCardUsersInfo() throws DefaultException {
		ArrayList result = new ArrayList();
		SQLHelper helper = new DefaultSQLHelper(dbData);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = helper.getConnection();
			stmt = conn.createStatement();
			// System.out.println("==== man sql===" + QRY_CARD_SQL +
			// super.userID);
			rs = stmt.executeQuery(QRY_CARD_SQL + super.userID);
			while (rs.next()) {
				MenSelectVO menInfo = new MenSelectVO();
				menInfo.setId(rs.getString(2));
				menInfo.setName(rs.getString(1));
				menInfo.setDeptid(rs.getString(3));
				menInfo.setJobid(rs.getString(4));
				result.add(menInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return result;
	}

	private String getUserRelateInfo(String tableName, String colName,
			String id, Connection conn) throws DefaultException, SQLException {
		String result = "";
		// SQLHelper helper = new DefaultSQLHelper(dbData);
		// Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String[] temp = null;
		String querySql = "SELECT " + colName + " " + " FROM " + tableName
				+ " " + " WHERE (id = " + id + ")";
		String countSql = "SELECT count(*) as cnt " + " FROM " + tableName
				+ " " + " WHERE (id = " + id + ")";

		// conn = helper.getConnection();
		stmt = conn.createStatement();
		rs2 = stmt.executeQuery(countSql);
		int count = 0;
		if (rs2.next()) {
			count = rs2.getInt(1);
		}
		// System.out.println("--- querySql is " + querySql);

		int i = 0;
		// rs.last();
		// temp = new String[rs.getRow()];
		// rs.first();
		temp = new String[count];

		rs = stmt.executeQuery(querySql);
		while (rs.next()) {
			temp[i] = rs.getString(1);
			i++;
		}
		result = arrayToString(temp);

		if (rs2 != null) {
			rs2.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}

		return result;
	}

	/**
	 * 将String[]转换为形如"…，…，…，…"的字符串，用来在删除时使用
	 * 
	 * @param fid
	 *            fid的String[]
	 * @return
	 */
	public static String arrayToString(String[] fid) {
		String serperater = ",";

		return arrayToString(fid, serperater);
	}

	public static String arrayToString(String[] fid, String serperater) {
		String strfid = "";
		for (int i = 0; i < fid.length; i++) { // 将所有选择的记录删除
			if (i == 0)
				strfid = fid[0];
			else
				strfid += serperater + fid[i];
		}
		return strfid;
	}

	/**
	 * 下拉框获取数据 linhs
	 * 
	 * @param sTable
	 *            表名
	 * @param sCode
	 *            序号列
	 * @param sContent
	 *            内容列
	 * @param sCondition
	 *            条件
	 * @return 列表
	 * @throws GeneralException
	 */
	public ArrayList getConstCode(String sTable, String sCode, String sContent,
			String sCondition) {
		Connection conn = null;
		SQLHelper helper = new DefaultSQLHelper(dbData);
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String sql = "select " + sCode + "," + sContent + " from " + sTable
				+ " where " + sCondition; // + " order by " + sCode;
		// System.out.println("--- sql is " + sql);
		try {
			try {
				conn = helper.getConnection();
			} catch (DefaultException ex) {
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ConstCodeVO view = new ConstCodeVO();
				view.setCode(rs.getString(1));
				view.setContent(rs.getString(2));
				list.add(view);
			}
			// return list;
		} catch (SQLException e) {
			System.out.print("Error: SQLException -------");
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (java.sql.SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			return list;
		}
	}

	public ArrayList getConstCodeBySql(String sql, int level) {
		Connection conn = null;
		SQLHelper helper = new DefaultSQLHelper(dbData);
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		// System.out.println("---- sql is " + sql);
		try {
			try {
				conn = helper.getConnection();
			} catch (DefaultException ex) {
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ConstCodeVO view = new ConstCodeVO();
				view.setCode(rs.getString(1));
				view.setContent(rs.getString(2));
				view.setParentCode(rs.getString(3));
				view.setCodeLevel(level);
				list.add(view);
			}
			// return list;
		} catch (SQLException e) {
			System.out.print("Error: SQLException -------");
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			return list;
		}
	}

	/**
	 * 按照树状进行排序
	 * 
	 * @param sql
	 *            查询语句
	 * @param firstCondition
	 *            初始条件
	 * @param secondCondition
	 *            后续条件
	 * @return
	 */
	public ArrayList getTreeOrderInfo(String sql, String firstCondition,
			String secondCondition) {
		ArrayList list = null;
		int levelNumber = 0;
		list = this.getConstCodeBySql(sql + firstCondition, 0);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				ConstCodeVO vo = (ConstCodeVO) list.get(i);
				ArrayList childList = null;
				childList = this.getConstCodeBySql(sql + secondCondition
						+ vo.getCode(), vo.getCodeLevel() + 1);
				if (!childList.isEmpty()) {
					list.addAll(list.indexOf(vo) + 1, childList);
				}
			}
		}
		return list;
	}

	/**
	 * 初始化页面数组的方法
	 * 
	 * @param list
	 *            需要转换的 list 包含 ConstCodeView
	 * @param ArrayName
	 *            转换出来后数组的名称
	 * @return
	 */
	public String initInfoArray(ArrayList list, String ArrayName) {
		String userInfo = "";
		String initArray = "	var " + ArrayName + " = new Array();\n";
		for (int i = 0; i < list.size(); i++) {
			ConstCodeVO tempVO = (ConstCodeVO) list.get(i);
			userInfo += "	" + ArrayName + "[" + i + "] = new dept(\""
					+ getSpacesByLevel(tempVO.getCodeLevel(), false)
					+ tempVO.getContent() + "\",\"" + tempVO.getCode()
					+ "\");\n";
		}
		// System.out.println("--- " + ArrayName + " is : \n" + initArray +
		// userInfo);
		return initArray + userInfo;
	}

	public static String getSpacesByLevel(int level, boolean filter) {
		String spaces = "";
		if (!filter) {
			WEB_SPACE = "  ";
		} else {
			WEB_SPACE = "&nbsp;&nbsp;";
		}
		// System.out.println("--- filter is " + filter + " --- webspace is " +
		// WEB_SPACE);
		for (int i = 0; i < level; i++) {
			spaces += WEB_SPACE;
		}
		return spaces;
	}

	public String getDeptInfoArray() {
		String arrayName = "deptInfoArray";
		String firstCondition = " where parent_id = 0";
		String secondCondition = " where parent_id = ";
		ArrayList deptList = getTreeOrderInfo(GET_DEPT_SQL, firstCondition,
				secondCondition);
		return initInfoArray(deptList, arrayName);
	}

	public ArrayList getDeptArray() throws DefaultException {
		ArrayList deptList = (ArrayList) sysmnguitl.getDepartmentStructure();
		ArrayList result = new ArrayList();
		for (int i = 0; i < deptList.size(); i++) {
			DepartmentStructure tempVO = (DepartmentStructure) deptList.get(i);
			ConstCodeVO codeView = new ConstCodeVO();
			codeView.setCode(String.valueOf(tempVO.getDepartment().getId()));
			codeView.setContent(getSpacesByLevel(tempVO.getLayerNumber(), true)
					+ tempVO.getDepartment().getName());
			result.add(codeView);
		}
		return result;
	}

	public ArrayList getAddressList(String userId) {
		ArrayList result = new ArrayList();
		result = getConstCode("IS_ADDRESS_LIST", "member", "group_name",
				" type_flag = 0 and user_code = " + userId);
		return result;
	}

	public String getTheamArray() throws DefaultException {
		String arrayName = "TheamArray";
		Connection conn = null;
		SQLHelper helper = new DefaultSQLHelper(dbData);
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		try {
			conn = helper.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(this.GET_THEAM_SQL);
			while (rs.next()) {
				ConstCodeVO view = new ConstCodeVO();
				view.setCode(rs.getString(1));
				view.setContent(rs.getString(2));
				list.add(view);
			}
			// return list;
		} catch (SQLException e) {
			System.out.print("Error: SQLException -------");
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			return initTheamArray((ArrayList) list, arrayName);
		}

	}

	public String initTheamArray(ArrayList list, String ArrayName) {
		String theamInfo = "";
		String initArray = "	var " + ArrayName + " = new Array();\n";
		for (int i = 0; i < list.size(); i++) {

			ConstCodeVO paramVo = (ConstCodeVO) list.get(i);

			theamInfo += "	" + ArrayName + "[" + i + "] = new dept(\""
					+ paramVo.getContent() + "\",\"" + paramVo.getCode()
					+ "\");\n";
		}

		return initArray + theamInfo;
	}

	public void setOracleSQL() {
		QRY_CARD_SQL = "Select a.customer,a.e_mail,c.type_code,c.type_name "
				+ " from DO_CARDCASE a, "
				+ " (select b.user_code, b.type_name,b.type_code,t_user.name from DO_CARDCASE_type b,t_user"
				+ " where  t_user.id = b.user_code(+) ) c "
				+ " where a.type_code = c.type_code(+) and length(a.e_mail)>0 and a.type_code='-1' "
				+ " union "
				+ " Select a.customer,a.e_mail,c.type_code,c.type_name "
				+ " from DO_CARDCASE a,"
				+ " (select b.user_code, b.type_name,b.type_code,t_user.name from DO_CARDCASE_type b,t_user "
				+ " where  t_user.id = b.user_code(+) ) c    "
				+ " where a.type_code = c.type_code(+) and length(a.e_mail)>0 and c.user_code= ";
	}

}
