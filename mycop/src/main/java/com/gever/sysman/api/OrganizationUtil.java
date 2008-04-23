package com.gever.sysman.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.gever.config.Constants;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.goa.util.DepartmentStructure;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.connection.ConnectionUtil;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.organization.dao.DepartmentDAO;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.Department;
import com.gever.sysman.organization.model.Job;
import com.gever.sysman.organization.model.User;
import com.gever.util.StringUtils;

/**
 * <p>Title: 人员-组织-岗位工具类</p>
 * <p>Description: 类似ICSS的org API</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author 
 * @version
 */

public class OrganizationUtil extends BaseDAO {
	//查询是否是子部门的语句
	private static final String SON_DEPT_SQL = " Select " +
	"id,case when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE " +
	"T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 " +
	"then '1' else '0' end as issondept from T_DEPARTMENT where  1=1 ";
	//查询部门ID
	private static final String DEPT_SQL="select id from t_department where 1=1";
	
	public OrganizationUtil() {
	}
	
	/**
	 * 通过用户ID获得用户所在部门的部门ID
	 * @param userID 用户ID
	 * @return 用户所在部门的部门ID字符串，Eg:1,2,3,
	 * @throws DefaultException
	 */
	public String getDepartmentIDsByUser(int userID) throws DefaultException {
		
		String strDepartmentIDs = "";
		
		User user = OrganizationFactory.getInstance().createUser();
		user.setId(userID);
		Collection coll = OrganizationFactory.getInstance().getUserDAO().
		getDepartments(user);
		Iterator it = coll.iterator();
		Department department = OrganizationFactory.getInstance().
		createDepartment();
		
		while (it.hasNext()) {
			department = (Department) it.next();
			strDepartmentIDs += department.getId() + ",";
		}
		
		return strDepartmentIDs;
	}
	
	/**
	 * 通过用户ID获得用户所在部门的部门名称
	 * @param userID 用户ID
	 * @return 用户所在部门的部门名称字符串(部门名称之间以空格分隔)，Eg:开发部 技术部 行财部
	 * @throws DefaultException
	 */
	public String getDepartmentNamesByUser(int userID) throws DefaultException {
		
		String strDepartmentNames = "";
		
		User user = OrganizationFactory.getInstance().createUser();
		user.setId(userID);
		Collection coll = OrganizationFactory.getInstance().getUserDAO().
		getDepartments(user);
		Iterator it = coll.iterator();
		Department department = OrganizationFactory.getInstance().
		createDepartment();
		
		while (it.hasNext()) {
			department = (Department) it.next();
			strDepartmentNames += department.getName() + " ";
		}
		strDepartmentNames.trim();
		
		return strDepartmentNames;
	}
	
	/**
	 * 通过用户ID获得用户所在岗位的岗位ID
	 * @param userID 用户ID
	 * @return 用户所在岗位的岗位ID字符串
	 * @throws DefaultException
	 */
	public String getJobIDsByUser(int userID) throws DefaultException {
		
		String strJobIDs = "";
		
		User user = OrganizationFactory.getInstance().createUser();
		user.setId(userID);
		Collection coll = OrganizationFactory.getInstance().getUserDAO().
		getJobs(user);
		Iterator it = coll.iterator();
		Job job = OrganizationFactory.getInstance().createJob();
		
		while (it.hasNext()) {
			job = (Job) it.next();
			strJobIDs += job.getId() + ",";
		}
		
		return strJobIDs;
	}
	
	/**
	 * 通过用户ID获得用户所在岗位的岗位名称
	 * @param userID 用户ID
	 * @return 用户所在岗位的岗位名称字符串(岗位名称之间以空格分隔)，Eg:项目经理 系统分析员 程序员
	 * @throws DefaultException
	 */
	public String getJobNamesByUser(int userID) throws DefaultException {
		
		String strJobNames = "";
		
		User user = OrganizationFactory.getInstance().createUser();
		user.setId(userID);
		Collection coll = OrganizationFactory.getInstance().getUserDAO().
		getJobs(user);
		Iterator it = coll.iterator();
		Job job = OrganizationFactory.getInstance().createJob();
		
		while (it.hasNext()) {
			job = (Job) it.next();
			strJobNames += job.getName() + " ";
		}
		strJobNames.trim();
		
		return strJobNames;
	}
	
	/**
	 * 通过用户ID字符串获得相对应的用户名称字符串
	 * @param strUserID 用户ID字符串(用户ID之间以逗号分隔)，Eg:1,2，3，
	 * @return 用户名称字符串(用户名称之间以空格分隔)，Eg:屈波 谌毅 董中光
	 * @throws DefaultException
	 */
	public String getUserNamesByUserdIDs(String strUserID) throws
	DefaultException {
		//String userCode;
		if (strUserID == null) {
			return "";
		}
		if (strUserID.equals("")) {
			return "";
		}
		if (strUserID.startsWith(",")) {
			strUserID = strUserID.substring(1, strUserID.length());
		}
		if (strUserID.endsWith(",")) {
			strUserID = strUserID.substring(0, strUserID.length() - 1);
		}
		
		String userName = "";
		SQLHelper helper = new DefaultSQLHelper(super.dbData);
		String sql = "select name from t_user where id in (" + strUserID + ")";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = helper.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				userName += rs.getString(1) + " ";
			}
			rs.close();
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DefaultException(e);
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw new DefaultException(e);
			}
		}
		return userName;
		
	}
	
	/*
	 public String getUserNamesByUserdIDs(String strUserID) throws
	 DefaultException {
	 String userNames = "";
	 String[] arrayUser = strUserID.split(",");
	 for (int i = 0; i < arrayUser.length; i++) {
	 User user = OrganizationFactory.getInstance().createUser();
	 UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
	 user = userDAO.findUserByID(Long.parseLong(arrayUser[i]));
	 userNames += user.getName() + " ";
	 }
	 userNames = userNames.trim();
	 return userNames;
	 }
	 */
	//------------------------董中光添加方法 begin ----------------------
	public String getUserNamesByUserdIDs(String strUserId, String seperator) throws
	DefaultException {
		String resultStr = "";
		
		if (strUserId == null || strUserId.length() == 0) {
			return "";
		}
		else {
			String serperator = ","; //???
			strUserId = removeLastSeperator(strUserId, serperator);
		}
		if("".equals(strUserId)){
			return "";
		}
		SQLHelper sqlHelper = new DefaultSQLHelper(super.dbData);
		String querySql = "Select name from T_USER where T_USER.id in (" +
		strUserId + ")";
		//System.out.println("---- querySql is " + querySql);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = sqlHelper.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySql);
			while (rs.next()) {
				resultStr = resultStr + rs.getString(1) + seperator;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			sqlHelper.end();
		}
		if (resultStr.length() > 0) {
			resultStr = removeLastSeperator(resultStr, seperator);
		}
		//System.out.println("--------- result name str is " + resultStr);
		return resultStr;
	}
	
	private String removeLastSeperator(String strUserId, String serperator) {
		int pos = strUserId.lastIndexOf(serperator);
		while (pos == strUserId.length() - 1) {
			if (pos == 0) {
				return "";
			}
			strUserId = strUserId.substring(0, pos);
			pos = strUserId.lastIndexOf(serperator);
		}
		return strUserId;
	}
	
	//------------------------董中光添加方法 end ----------------------
	/**
	 * 获得组织的部门体系结构
	 * @return 含有层级关系的组织部门结构
	 * @throws DefaultException
	 */
	public Collection getDepartmentStructure() throws DefaultException {
		
		Collection collection = new ArrayList();
		
		Collection tempColl = null;
		Iterator tempIt = null;
		Department tempDept = null;
		
		DepartmentDAO tempDeptDAO = OrganizationFactory.getInstance().
		getDepartmentDAO();
		DepartmentStructure departmentStructure = null;
		tempColl = tempDeptDAO.getRootDepartment();
		tempIt = tempColl.iterator();
		
		while (tempIt.hasNext()) {
			departmentStructure = new DepartmentStructure();
			tempDept = (Department) tempIt.next();
			departmentStructure.setDepartment(tempDept);
			departmentStructure.setLayerNumber(1);
			collection.add(departmentStructure);
			
			collection = this.getSubDepartmentStructure(collection, tempDept, 1);
		}
		
		return collection;
	}
	
	/**
	 * 获得指定部门的子部门体系结构
	 * @param collection 组织部门结构
	 * @param layerNumber 部门层级数
	 * @return 组织部门结构
	 * @throws DefaultException
	 */
	private Collection getSubDepartmentStructure(Collection collection,
			Department department,
			int layerNumber) throws
			DefaultException {
		
		Collection tempColl = new ArrayList();
		Iterator tempIt = null;
		Department tempDept = null;
		
		DepartmentDAO departmentDAO = OrganizationFactory.getInstance().
		getDepartmentDAO();
		DepartmentStructure departmentStructure = null;
		tempColl = this.getSubDepartments(department);
		tempIt = tempColl.iterator();
		
		while (tempIt.hasNext()) {
			departmentStructure = new DepartmentStructure();
			tempDept = (Department) tempIt.next();
			departmentStructure.setDepartment(tempDept);
			departmentStructure.setLayerNumber(layerNumber + 1);
			collection.add(departmentStructure);
			
			collection = this.getSubDepartmentStructure(collection, tempDept,
					layerNumber + 1);
		}
		
		return collection;
	}
	
	public Collection findDepartment(String searchQuery, long start, long count) throws
	DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		Department dept = null;
		String querySql = "SELECT A.ID, A.CODE, A.NAME, A.DESCRIPTION, A.DEPARTMENTTYPE, A.PARENT_ID, B.NAME AS PARENT_NAME, C.CHILD AS CHILDNUM "
			+ " FROM T_DEPARTMENT A, T_DEPARTMENT B,(select parent_id,count(*) as child from T_DEPARTMENT  group by parent_id ) C WHERE (A.PARENT_ID=B.ID) AND A.ID=C.parent_id AND ";
		StringBuffer sql = new StringBuffer();
		sql.append(querySql);
		sql.append(" ");
		sql.append(searchQuery);
		sql.append(" ORDER BY A.CODE ASC, A.NAME ASC, B.NAME ASC ");
		log.showLog("=======> query dept sql: " + sql.toString());
		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				if (start != 0L && (long) i < start)
					continue;
				if (count != 0L && (long) i >= count)
					break;
				dept = OrganizationFactory.getInstance().createDepartment();
				dept.setId(rs.getInt("ID"));
				dept.setCode(rs.getString("CODE"));
				dept.setName(rs.getString("NAME"));
				dept.setDescription(rs.getString("DESCRIPTION"));
				dept.setDepartmentType(rs.getString("DEPARTMENTTYPE"));
				dept.setParentDepartment(rs.getInt("PARENT_ID"));
				dept.setParentDepartmentName(rs.getString("PARENT_NAME"));
				dept.setChildNum(rs.getInt("CHILDNUM"));
				list.add(dept);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_Depart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_004", e);
			}
		}
		return list;
	}
	
	public Collection getSubDepartments(Department department) throws
	DAOException {
		try {
			return this.findDepartment(" A.PARENT_ID=" + department.getId(), 0,
					0);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException("ORG_Depart_010", e);
		}
		
	}
	
	/**
	 * 获得组织所有用户ID字符串
	 * @return 组织所有用户ID字符串(ID之间以逗号分隔)，Eg:1,2,3,
	 * @throws DefaultException
	 */
	public String getAllUserIDs() throws
	DefaultException {
		
		String strUserIDs = "";
		
		Collection coll = OrganizationFactory.getInstance().getUserDAO().
		getUsers();
		Iterator it = coll.iterator();
		User user = null;
		while (it.hasNext()) {
			user = (User) it.next();
			strUserIDs += user.getId() + ",";
		}
		
		return strUserIDs;
	}
	
	/**
	 * 通过用户ID获得用户所属部门的所有用户ID字符串
	 * @param userID 用户ID
	 * @return 用户所属部门的所有用户ID字符串
	 * @throws DefaultException
	 */
	public String getUserIDsForDepartmentOfUserByUser(int userID) throws
	DefaultException {
		
		String strUserIDs = ",";
		
		User user = OrganizationFactory.getInstance().createUser();
		user.setId(userID);
		
		Collection departmentCollection = OrganizationFactory.getInstance().
		getUserDAO().getDepartments(user);
		Iterator departmentIterator = departmentCollection.iterator();
		Department department = null;
		
		Collection userCollection = null;
		Iterator userIterator = null;
		DepartmentDAO departmentDAO = OrganizationFactory.getInstance().
		getDepartmentDAO();
		
		while (departmentIterator.hasNext()) {
			department = (Department) departmentIterator.next();
			//System.out.println("belong to department:" + department.getName());
			userCollection = departmentDAO.getUsers(department);
			userIterator = userCollection.iterator();
			while (userIterator.hasNext()) {
				user = (User) userIterator.next();
				if (userID != user.getId() &&
						strUserIDs.indexOf("," + user.getId() + ",") == -1) {
					strUserIDs += user.getId() + ",";
				}
			}
		}
		
		return strUserIDs;
	}
	
	/**
	 * 通过当前用户名查找用户代码
	 * @param name 用户名称
	 * @return 用户ID
	 * @throws DefaultException
	 * CY ADD 2004-08-20
	 */
	public int getUserIdByUserName(String name) throws Exception {
		int userId = -1; //name同名时如何处理？
		/*
		 SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
		 StringBuffer sbSql = new StringBuffer();
		 sbSql.append("select ID from T_USER where NAME='" + name + "' ");
		 ArrayList ary = (ArrayList)sqlHelper.query(sbSql.toString(),DataTypes.RS_LIST_MAP);
		 if(ary.size()>0){
		 String userCode = ary.get(0).toString();
		 System.out.println("=---------------userCode====="+userCode);
		 }
		 */
		
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn = cp.getConnection();
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sbSql = new StringBuffer(); ;
		sbSql.append("select ID from T_USER where NAME='" + name + "' ");
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("ID");
			}
		}
		catch (Exception e) {
			// System.out.println("ConstantSet queryPersonTargetIsExist error::" + e.getMessage());
		}
		finally {
			ConnectionUtil.close(conn, pstmt, rs);
		}
		return userId;
	}
	
	/**
	 * 通过当前用户中文名名查找用户代码-用逗号分开
	 * 用来处理相同中文名用户的情况
	 * @param name 用户名称-可能只是名字的一部分,比如：李
	 * @return 用户IDs
	 * @throws Exception
	 * CY ADD 2004-08-29
	 */
	public String getUserIdsByUserName(String name) throws Exception {
		String userIds = "";
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn = cp.getConnection();
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sbSql = new StringBuffer(); ;
		sbSql.append("select ID from T_USER where NAME like '%" + name + "%' ");
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userIds += rs.getInt("ID") + ",";
				// System.out.println("-----------userIds-----------" + userIds);
			}
		}
		catch (Exception e) {
			// System.out.println("getUserIdsByUserName error::" + e.getMessage());
		}
		finally {
			ConnectionUtil.close(conn, pstmt, rs);
		}
		
		return userIds;
	}
	
	/**
	 * 通过当前用户ID查找用户级别
	 * 用来处理相同中文名用户的情况
	 * @param userid 用户名称-可能只是名字的一部分,比如：李
	 * @return 用户级别
	 * @throws Exception
	 * CY ADD 2004-09-15
	 */
	public String getUserLevelByUserID(String userid) throws Exception {
		String userLevel = "";
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn = cp.getConnection();
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sbSql = new StringBuffer(); ;
		sbSql.append("select LEVEL from T_USER where id =" +
				Integer.parseInt(userid) + " ");
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userLevel = rs.getString("level");
			}
		}
		catch (Exception e) {
			//  System.out.println("getUserLevelByUserID error::" + e.getMessage());
		}
		finally {
			ConnectionUtil.close(conn, pstmt, rs);
		}
		return userLevel;
	}
	
	/**
	 * 通过单个部门ID查找单个部门名称
	 * @param name 部门ID
	 * @return 部门中文名称
	 * @throws Exception
	 * CY ADD 2004-08-25
	 */
	public String getDeptNameByDeptID(String deptID) throws Exception {
		String deptName = "";
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn = cp.getConnection();
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sbSql = new StringBuffer(); ;
		sbSql.append("select NAME from T_DEPARTMENT where ID=" + deptID + " ");
		try {
			pstmt = conn.prepareStatement(sbSql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				deptName = rs.getString("NAME");
			}
		}
		catch (Exception e) {
			//  System.out.println("ConstantSet queryPersonTargetIsExist error::" +
			//                  e.getMessage());
		}
		finally {
			ConnectionUtil.close(conn, pstmt, rs);
		}
		
		return deptName;
	}
	
//	public static void main(String[] args) throws DefaultException {
//	SystemMngUtil systemMngUtil1 = new SystemMngUtil();
	
//	System.out.println("DeptIDs:"+systemMngUtil1.getDepartmentIDsByUser(345));
//	System.out.println("DeptNames:"+systemMngUtil1.getDepartmentNamesByUser(345));
//	System.out.println("JobIDs:"+systemMngUtil1.getJobIDsByUser(345));
//	System.out.println("JobNames:"+systemMngUtil1.getJobNamesByUser(345));
//	System.out.println("UserNames:"+systemMngUtil1.getUserNamesByUserdIDs("345,8001,"));
//	System.out.println("strAllUserIDs:"+systemMngUtil1.getAllUserIDs());
//	System.out.println("strAllUserIDs:" +
//	systemMngUtil1.
//	getUserIDsForDepartmentOfUserByUser(345));
//	Collection coll=systemMngUtil1.getDepartmentStructure();
//	Iterator it=coll.iterator();
//	DepartmentStructure ds = new DepartmentStructure();
//	while(it.hasNext()){
//	ds=(DepartmentStructure)it.next();
//	System.out.println("dept:"+ds.getDepartment().getName()+" layerNumber:"+ds.getLayerNumber());
//	}
//	}
	/**
	 * 根据用户ID判断该用户是否存在
	 * @param id
	 * @return
	 */
	public boolean checkUser(int id) throws DefaultException,
	SQLException {
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn = cp.getConnection();
		Statement st = conn.createStatement();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USERNAME FROM  T_USER where id=");
		sb.append(id);
		ResultSet rs = st.executeQuery(sb.toString());
		if (rs.next()) {
			return true;
		}
		else {
			return false;
		}
		//SQLHelper helper=new DefaultSQLHelper();
		
	}
	
	/**黎海冬 加
	 * 递归算法
	 * 根据部门ID得到所有的子部门ID
	 * @param deptid String
	 * @return String
	 */
	
	
	public String getDepartmentSonIdById(String deptid) throws DefaultException,
	SQLException {
		String sonDeptId="";//存所有的子部门ID
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn=cp.getConnection();
		StringBuffer sb=new StringBuffer();
		String deptId="";
		sb.append(DEPT_SQL);
		sb.append(" and parent_id=").append(deptid);
		Statement st=conn.createStatement();
		System.out.println("The sql is:========"+sb.toString());
		ResultSet rs=st.executeQuery(sb.toString());
		while (rs.next()) {
			deptId=rs.getString("id");
			StringBuffer sql=new StringBuffer();
			sql.append(SON_DEPT_SQL);
			sql.append(" and t_department.id=");
			sql.append(deptId);
			System.out.println("The sql is:"+sql.toString());
			Statement stt=conn.createStatement();
			ResultSet res=stt.executeQuery(sql.toString());
			while(res.next()){
				String tmpDept=res.getString("issondept");
				if(tmpDept.equals("1")){
					this.getDepartmentSonIdById(res.getString("id"));
				}
			}
			sonDeptId=sonDeptId+deptId+",";
			res.close();
			stt.close();
		}
		rs.close();
		st.close();
		conn.close();
		
		
		return sonDeptId;
	}
	
	/**
	 * 判断是否为根节点
	 * @param deptId String
	 * @return boolean
	 */
	public boolean isFolder(String deptId) throws DefaultException, SQLException {
		if("0".equals(deptId)||StringUtils.isNull(deptId)){
			return false;
		}
		
		ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
				dbData);
		Connection conn=cp.getConnection();
		Statement st=conn.createStatement();
		StringBuffer sbSql=new StringBuffer();
		sbSql.append(SON_DEPT_SQL);
		sbSql.append(" and t_department.id=").append(deptId);
		ResultSet rs= st.executeQuery(sbSql.toString());
		boolean retVal=false;
		while (rs.next()) {
			String cnt=rs.getString("issondept");
			if(Integer.parseInt(cnt) > 0){
				retVal=true;
			}else{
				retVal=false;
			}
		}
		return retVal;
	}
	
	public static void main(String[] args) throws SQLException, DefaultException {
		OrganizationUtil util=new OrganizationUtil();
		System.out.println("thie sondept is:"+ util.getDepartmentSonIdById("1098"));
	}
}
