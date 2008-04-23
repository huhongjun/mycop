package com.gever.sysman.privilege.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import com.gever.sysman.privilege.dao.ResOrderDAO;
import com.gever.config.Constants;
import com.gever.exception.db.DAOException;

import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;

import java.util.Collection;
import java.util.ArrayList;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.dao.SQLFactory;

/**
 * 
 * <p>
 * Title: 实现对资源排序的DAO
 * </p>
 * <p>
 * Description:实现对资源排序的DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004-11-19
 * </p>
 * <p>
 * Company: GEVER
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public class ResOrderDAOIMP implements ResOrderDAO {
	private static String GET_ORDERID = "SELECT ORDERID,PARENT_ID FROM  T_RESOURCE  WHERE ID=? ";

	private static String CHANGE_ORDERID = "UPDATE T_RESOURCE SET ORDERID=? WHERE ID=?";

	private static String CHANGE_ALLORDERID = "UPDATE T_RESOURCE SET ORDERID=ORDERID+1 WHERE ORDERID>=?  AND PARENT_ID=?";

	private static String CHANGE_ONEORDERID = "UPDATE T_RESOURCE SET ORDERID=? WHERE ID=?";

	private static String CHANGE_ALLORDERID2 = "UPDATE T_RESOURCE SET ORDERID=ORDERID-1 WHERE ORDERID<=?  AND PARENT_ID=?";

	private static String CHANGE_PARENTID = "UPDATE T_RESOURCE SET PARENT_ID=? WHERE ID=?";

	private static String GET_RESOPTIONID = "SELECT ID FROM  T_RESOPERATION WHERE ID=? ";

	private static String CHANGE_RESOPTIONID = "UPDATE T_RESOPERATION SET ORDERID=? WHERE ID=?";

	private static String CHANGE_RSOPTION_ALLORDERID = "UPDATE T_RESOPERATION SET ORDERID=ORDERID+1 WHERE ORDERID>?";

	private static String CHANGE_RSOPTION_ALLORDERID2 = "UPDATE T_RESOPERATION SET ORDERID=ORDERID-1 WHERE ORDERID>?";

	private static String CHANGE_RESOPTION_ORDERID = "UPDATE T_RESOPERATION SET ORDERID=? WHERE ID=?";

	private static String GET_OPERATIONRESID = "SELECT * FROM T_RESOPERATION WHERE Resource_ID=? order by orderid";

	private static String GET_one_OPERATIONRESID = "SELECT * FROM T_RESOPERATION WHERE id=?";

	// private static String INIT_ORDERID =
	//   "select a.id,a.orderid,a.parent_id,a.name from T_RESOURCE as a,T_RESOURCE
	// as b where a.parent_id=? and b.parent_id=? and a.orderid=b.orderid and
	// a.id!=b.id order by orderid";
	private static String UPDATA_ORDERID = " UPDATE T_RESOURCE SET ORDERID=ORDERID+1 WHERE ID!=? AND ORDERID>=?";

	// private static String INIT_OPERATION_ORDERID =
	//    "select a.id,a.orderid,a.resource_id,a.name from T_RESOPERATION as
	// a,T_RESOPERATION as b where a.resource_id=? and b.resource_id=? and
	// a.orderid=b.orderid and a.id!=b.id order by orderid";
	private static String UPDATA_OPERATION_ORDERID = " UPDATE T_RESOPERATION SET ORDERID=ORDERID+1 WHERE ID!=? AND ORDERID>=?";

	private static String GET_ResourceId = "SELECT * FROM T_RESOURCE WHERE PARENT_ID=? order by orderid";

	private static SQLFactory sqlFactory = PrivilegeFactory.getInstance()
			.getSQLFactory();

	/**
	 * 初始化T_RESOURCE表中的ORDERID
	 * 
	 * @param parentid
	 *            int
	 * @throws Exception
	 */
	public void initOrderID(int parentid) throws Exception {

		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("INIT_ORDERID"));
			//  pstmt = conn.prepareStatement(INIT_ORDERID);

			pstmt.setString(1, String.valueOf(parentid));
			pstmt.setString(2, String.valueOf(parentid));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt = conn.prepareStatement(UPDATA_ORDERID);
				pstmt.setString(1, String.valueOf(rs.getInt("id")));
				pstmt.setString(2, String.valueOf(rs.getInt("orderid")));

				pstmt.executeUpdate();
				pstmt.close();
				rs.close();
				initOrderID(parentid);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 初始化T_OPETRESOURCE表中的ORDERID
	 * 
	 * @param parentid
	 *            int
	 * @throws Exception
	 */

	public void initOperationOrderID(int parentid) throws Exception {

		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory
					.get("INIT_OPERATION_ORDERID"));
			//  pstmt = conn.prepareStatement(INIT_OPERATION_ORDERID);

			pstmt.setString(1, String.valueOf(parentid));
			pstmt.setString(2, String.valueOf(parentid));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt = conn.prepareStatement(UPDATA_OPERATION_ORDERID);
				pstmt.setString(1, String.valueOf(rs.getInt("id")));
				pstmt.setString(2, String.valueOf(rs.getInt("orderid")));
				//System.out.println(rs.getInt("id")+"==="+pstmt.toString()+"======="+rs.getInt("orderid"));

				pstmt.executeUpdate();
				pstmt.close();
				rs.close();
				initOrderID(parentid);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	//##################################以下代码功能是资源结构调整的代码，暂时没有被使用###########
	public boolean exchangeResID(int id1, int id2) throws DAOException {
		boolean result = false;
		int temporderid1, temporderid2, parentid;
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			parentid = rs.getInt("PARENT_ID");
			initOrderID(parentid);

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			//以下语句执行的时候要保证至少有一条记录
			rs.next();
			temporderid1 = rs.getInt("ORDERID");

			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid2 = rs.getInt("ORDERID");
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(CHANGE_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid2));
			pstmt.setString(2, String.valueOf(id1));
			pstmt.executeUpdate();
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(CHANGE_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid1));
			pstmt.setString(2, String.valueOf(id2));
			pstmt.executeUpdate();
			result = true;
			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_RES_001", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				rs = null;

				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean moveResID(int id1, int id2) throws DAOException {
//		System.out.println("the start mobersid start");
		int temporderid1, temporderid2, parentid1, parentid2;
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_ORDERID);//  "SELECT
													   // ORDERID,PARENT_ID FROM
													   // T_RESOURCE WHERE ID=?
													   // ";
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid1 = rs.getInt("ORDERID");
			parentid1 = rs.getInt("PARENT_ID");
			initOrderID(parentid1);

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid2 = rs.getInt("ORDERID");

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			parentid1 = rs.getInt("PARENT_ID");

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			parentid2 = rs.getInt("PARENT_ID");

			if (parentid1 == parentid2) {
				if (temporderid1 < temporderid2) {
					pstmt = conn.prepareStatement(CHANGE_ALLORDERID);// "UPDATE
																	 // T_RESOURCE
																	 // SET
																	 // ORDERID=ORDERID+1
																	 // WHERE
																	 // ORDERID>=temporderid2
																	 // AND
																	 // PARENT_ID=parentid1";
					pstmt.setString(1, String.valueOf(temporderid2));
					pstmt.setString(2, String.valueOf(parentid1));
					pstmt.executeUpdate();

					pstmt = conn.prepareStatement(CHANGE_ONEORDERID);// "UPDATE
																	 // T_RESOURCE
																	 // SET
																	 // ORDERID=temporderid2
																	 // WHERE
																	 // ID=id1";
					pstmt.setString(1, String.valueOf(temporderid2));
					pstmt.setString(2, String.valueOf(id1));
					pstmt.executeUpdate();
				} else {
					pstmt = conn.prepareStatement(CHANGE_ALLORDERID2);//"UPDATE
																	  // T_RESOURCE
																	  // SET
																	  // ORDERID=ORDERID-1
																	  // WHERE
																	  // ORDERID<=temporderid2
																	  // AND
																	  // PARENT_ID=parentid1";
					pstmt.setString(1, String.valueOf(temporderid2));
					pstmt.setString(2, String.valueOf(parentid1));
					pstmt.executeUpdate();

					pstmt = conn.prepareStatement(CHANGE_ONEORDERID);//"UPDATE
																	 // T_RESOURCE
																	 // SET
																	 // ORDERID=temporderid2
																	 // WHERE
																	 // ID=id1";
					pstmt.setString(1, String.valueOf(temporderid2));
					pstmt.setString(2, String.valueOf(id1));
					pstmt.executeUpdate();

				}
				return true;
			} else {
				System.out.println("不是同一个目录");
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DAOException("PRV_RES_001", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean exchangePID(int id1, int id2) throws DAOException {
		int parentid1, parentid2;
		ConnectionProvider cp = null;

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_ORDERID);// "SELECT
													   // ORDERID,PARENT_ID FROM
													   // T_RESOURCE WHERE ID=?
													   // ";
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			parentid1 = rs.getInt("PARENT_ID");
//			System.out.println("parentid1=" + parentid1);

			//                     pstmt = conn.prepareStatement(GET_ORDERID);
			//                     pstmt.setString(1, String.valueOf(id2));
			//                     rs = pstmt.executeQuery();
			//                     rs.next();
			//                     parentid2 = rs.getInt("PARENT_ID");
			//                     System.out.println("parentid2="+parentid2);

			//                     pstmt = conn.prepareStatement(CHANGE_PARENTID);// "UPDATE
			// T_RESOURCE SET PARENT_ID=? WHERE ID=?";
			//                     pstmt.setString(1, String.valueOf(id2));
			//                     pstmt.setString(2, String.valueOf(id1));
			//                     pstmt.executeUpdate();
			//                     System.out.println("==============5==========");
			pstmt = conn.prepareStatement(CHANGE_PARENTID);//  "UPDATE T_RESOURCE
														   // SET PARENT_ID=?
														   // WHERE ID=?";
			pstmt.setString(1, String.valueOf(id2));
			pstmt.setString(2, String.valueOf(id1));
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DAOException("PRV_RES_001", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean exchangeOptionID(int id1, int id2) throws DAOException {
		boolean result = false;
		int temporderid1, temporderid2;
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_RESOPTIONID);
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid1 = rs.getInt("ORDERID");

			pstmt = conn.prepareStatement(GET_RESOPTIONID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid2 = rs.getInt("ORDERID");

			pstmt = conn.prepareStatement(CHANGE_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid2));
			pstmt.setString(2, String.valueOf(id1));
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(CHANGE_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid1));
			pstmt.setString(2, String.valueOf(id2));
			pstmt.executeUpdate();
			result = true;
			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_RES_001", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				rs = null;

				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

	}

	public Collection getOptionResOrderId(int resid) throws Exception {
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Collection list = new ArrayList();
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
//			System.out.println("------------------resid=" + resid);
			pstmt = conn.prepareStatement(GET_OPERATIONRESID);//SELECT * FROM
															  // T_RESOPERATION
															  // WHERE
															  // Resource_ID=?
			pstmt.setString(1, String.valueOf(resid));
			rs = pstmt.executeQuery();
			PrivilegeFactory factory = PrivilegeFactory.getInstance();
			while (rs.next()) {
				Operation opt = factory.createOperation();
				opt.setId(rs.getLong("ID"));
				opt.setName(rs.getString("Name"));
				//opt.setResourceID(rs.getLong("Resource_ID"));
				//opt.setResourceName(rs.getString("resourceName"));
				opt.setDescription(rs.getString("Description"));
				opt.setCode(rs.getString("CODE"));
				list.add(opt);
			}
			//                 Iterator it = list.iterator();
			//                 while(it.hasNext()){
			//                  System.out.println(((Operation)it.next()).getName());
			//                 }
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				rs = null;

				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	public Collection getResourceId(int resid) throws Exception {
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Collection list = new ArrayList();
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			// System.out.println("------------------resid="+resid);
			pstmt = conn.prepareStatement(GET_ResourceId);//SELECT * FROM
														  // T_RESOURCE WHERE
														  // Resource_ID=?
			pstmt.setString(1, String.valueOf(resid));
			rs = pstmt.executeQuery();
			PrivilegeFactory factory = PrivilegeFactory.getInstance();
			while (rs.next()) {
				Resource opt = factory.createResource();
				opt.setId(rs.getLong("ID"));
				opt.setName(rs.getString("Name"));
				opt.setOrderId(rs.getInt("ORDERID"));

				// opt.setDescription(rs.getString("Description"));
				// opt.setCode(rs.getString("CODE"));
				list.add(opt);
			}
			//                 Iterator it = list.iterator();
			//                 while(it.hasNext()){
			//                  System.out.println(((Operation)it.next()).getName());
			//                 }
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				rs = null;

				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
		return list;
	}

	//##################################以上代码功能是资源结构调整的代码，暂时没有被使用###########
	public boolean moveOptionID(int id1, int id2) throws Exception {
		int temporderid1, temporderid2, parentid;
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_one_OPERATIONRESID);// "SELECT *
																  // FROM
																  // T_RESOPERATION
																  // WHERE
																  // id=?";
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			// System.out.println("===================1==================");
			rs.next();
			//  System.out.println("=================2============");
			temporderid1 = rs.getInt("ORDERID");
			parentid = rs.getInt("resource_id");
			// System.out.println("parentid1="+temporderid1);
			initOperationOrderID(parentid);
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(GET_one_OPERATIONRESID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid2 = rs.getInt("ORDERID");
			// System.out.println("parentid2="+temporderid2);
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(CHANGE_RESOPTION_ORDERID);//"UPDATE
																	// T_RESOPERATION
																	// SET
																	// ORDERID=?
																	// WHERE
																	// ID=?";
			pstmt.setString(1, String.valueOf(temporderid2));
			pstmt.setString(2, String.valueOf(id1));
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(CHANGE_RESOPTION_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid1));
			pstmt.setString(2, String.valueOf(id2));
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
			return false;
		}

	}

	public boolean moveResourceOrderId(int id1, int id2) throws Exception {
		int temporderid1, temporderid2, parentid;
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(GET_ORDERID);// "SELECT
													   // ORDERID,PARENT_ID FROM
													   // T_RESOURCE WHERE ID=?
													   // ";
			pstmt.setString(1, String.valueOf(id1));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid1 = rs.getInt("ORDERID");
			parentid = rs.getInt("PARENT_ID");
			initOrderID(parentid);
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(GET_ORDERID);
			pstmt.setString(1, String.valueOf(id2));
			rs = pstmt.executeQuery();
			rs.next();
			temporderid2 = rs.getInt("ORDERID");
			rs.close();
			pstmt.close();

			pstmt = conn.prepareStatement(CHANGE_ORDERID);//"UPDATE
														  // T_RESOPERATION SET
														  // ORDERID=? WHERE
														  // ID=?";
			pstmt.setString(1, String.valueOf(temporderid2));
			pstmt.setString(2, String.valueOf(id1));
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(CHANGE_ORDERID);
			pstmt.setString(1, String.valueOf(temporderid1));
			pstmt.setString(2, String.valueOf(id2));
			pstmt.executeUpdate();
			rs.close();
			conn.close();
			pstmt.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				rs = null;
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
			return false;
		}

	}

}

