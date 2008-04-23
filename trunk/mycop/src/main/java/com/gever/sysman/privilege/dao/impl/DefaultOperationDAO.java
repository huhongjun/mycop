package com.gever.sysman.privilege.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.RolePermission;
import com.gever.sysman.privilege.model.UserPermission;
import com.gever.sysman.util.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @version 1.0
 */
public class DefaultOperationDAO implements OperationDAO {
    private static String GET_O_BYID =
        "SELECT ID,Name,Description,Resource_ID,CODE FROM T_RESOPERATION " +
        " WHERE ID=?";
    private static String GET_O_BYCODE="SELECT ID,Name,Description,Resource_ID,CODE FROM T_RESOPERATION " +
        " WHERE CODE=?";
    private static String UPDATE_O = "UPDATE T_RESOPERATION SET Name=?," +
        "Description=?,Resource_ID=?,CODE=? WHERE ID=?";
    private static String DEL_O = "DELETE FROM T_RESOPERATION WHERE ID=?";
    private static String DEL_OS = "DELETE FROM T_RESOPERATION WHERE 1=2";
    private static String ADD_O =
        "INSERT INTO T_RESOPERATION(ID,Name,Description,Resource_ID,CODE) " +
        " VALUES(?,?,?,?,?)";
    private static String GETCOUNT = "SELECT COUNT(ID) FROM T_RESOPERATION";
    private static String LISTALL_O_ByRsID = "SELECT ID,Name,Description,Resource_ID,CODE FROM T_RESOPERATION WHERE Resource_ID=?";
    private static String LISTALL_O = "SELECT T_RESOPERATION.ID,T_RESOPERATION.Name,T_RESOURCE.Name as resourceName,T_RESOPERATION.Description,T_RESOPERATION.Resource_ID,T_RESOPERATION.CODE FROM T_RESOPERATION, T_RESOURCE where T_RESOPERATION.Resource_ID = T_RESOURCE.ID order by T_RESOURCE.Name";
    private static String GET_OPT_BY_RESID = "SELECT T_RESOPERATION.ID,T_RESOPERATION.Name,T_RESOURCE.Name as resourceName,T_RESOPERATION.Description,T_RESOPERATION.Resource_ID,T_RESOPERATION.CODE FROM T_RESOPERATION, T_RESOURCE WHERE T_RESOPERATION.Resource_ID=? and T_RESOURCE.ID = T_RESOPERATION.Resource_ID";
    private static String FIND_O = "SELECT ID,Name,Description,Resource_ID,CODE FROM T_RESOPERATION";
    private static String GET_OPERATIONSET_SQL = "SELECT ID,Name,Description,Resource_ID,CODE FROM T_RESOPERATION";
    private static String GET_RES_ByRoleid = "SELECT TP.Role_ID,TP.Resource_ID,TP.ResOperation_id ,T.code CODE FROM T_ROLE_PERMISSION TP,T_RESOPERATION T where T.id=TP.ResOperation_id and TP.Role_ID=?";
    private static String GET_Perm_ByUserid = "SELECT TP.User_ID User_ID,TP.Resource_ID Resource_ID,TP.ResOperation_id ResOperation_id,ro.code ocode, r.code as rcode  "+
    " FROM T_USER_PERMISSION TP,T_USER T,T_RESOPERATION ro,T_RESOURCE r where TP.resource_id=r.id and TP.resoperation_id=ro.id and TP.User_ID=T.ID AND TP.User_ID=?";
    private static String GET_USER_ROLE_PERM =
		 "select distinct TP.resource_id ,TP.resoperation_id,ro.code ocode, r.code as rcode from T_ROLE_PERMISSION TP,T_RESOPERATION ro,T_RESOURCE r "+
		 " where TP.resource_id=r.id and TP.resoperation_id=ro.id and TP.role_id in (select role_id from T_USER_ROLE where user_id=?)";


    /**
    根据id获取Operation
    @param id
    @return
    @throws com.gever.exception.db.DAOException
    */
    public Operation findOperationByID(long id) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
		PrivilegeFactory factory=PrivilegeFactory.getInstance();
        Operation op = factory.createOperation();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_O_BYID);
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                op.setId(rs.getLong("ID"));
                op.setName(rs.getString("Name"));
                op.setDescription(rs.getString("Description"));
                op.setResourceID(rs.getLong("Resource_ID"));
                op.setCode(rs.getString("CODE"));

            }

            return op;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_001", e);
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
    public Operation findOperationByCode(String code) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
                PrivilegeFactory factory=PrivilegeFactory.getInstance();
        Operation op = factory.createOperation();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_O_BYCODE);
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                op.setId(rs.getLong("ID"));
                op.setName(rs.getString("Name"));
                op.setDescription(rs.getString("Description"));
                op.setResourceID(rs.getLong("Resource_ID"));
                op.setCode(rs.getString("CODE"));

            }

            return op;
        } catch (Exception e) {
                        throw new DAOException("PRV_OPT_001", e);
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

    /**
    更新操作
    @param operation
    @throws com.gever.exception.db.DAOException
    */
    public void updateOperation(Operation operation) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            int i=1;
            pstmt = conn.prepareStatement(UPDATE_O);
            pstmt.setString(i++, operation.getName());
            pstmt.setString(i++, operation.getDescription());
            pstmt.setLong(i++, operation.getResourceID());
            pstmt.setString(i++,operation.getCode());
            pstmt.setString(i++, String.valueOf(operation.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_002", e);
        } finally {
            try {
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

    /**
    删除操作
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559003F
     */
    public void deleteOperation(Operation operation) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(DEL_O);
            pstmt.setString(1, String.valueOf(operation.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_003", e);
        } finally {
            try {
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

	/**
	 * 根据用户id获取用户权限
	 */
	public Collection getOptByUserid(long userid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Collection operations = new ArrayList();

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_Perm_ByUserid);
			pstmt.setString(1, String.valueOf(userid));

			rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
			while (rs.next()) {

				UserPermission uopt =factory.createUserPermission();
				uopt.setUser_id(rs.getLong("user_id"));
				uopt.setResource_id(rs.getLong("Resource_ID"));
				uopt.setResop_id(rs.getLong("ResOperation_id"));
                                uopt.setOpt_code(rs.getString("ocode") );
                                uopt.setRes_code(rs.getString("rcode") );
				uopt.setRolePerm(false);
				operations.add(uopt);
			}

			return operations;
		} catch (Exception e) {
			throw new DAOException("PRV_OPT_004", e);
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

    /**
     * 根据角色id获取所有角色操作
     */
    public Collection getOptByRoleid(long roleid) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection operations = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_RES_ByRoleid);
		    pstmt.setString(1, String.valueOf(roleid));

            rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
            while (rs.next()) {

                RolePermission ropt =factory.createRolePermission();
                ropt.setRoleid(rs.getLong("Role_ID"));
                ropt.setResourceid(rs.getLong("Resource_ID"));
                ropt.setResopid(rs.getLong("ResOperation_id"));
                ropt.setOpt_code(rs.getString("code") );

                operations.add(ropt);
            }

            return operations;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_005", e);
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

    /*
     * 删除操作
     * @see com.gever.sysman.privilege.dao.OperationDAO#deleteOperation(java.lang.String[])
     */
    public void deleteOperation(String[] ids) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            StringBuffer delsql = new StringBuffer(DEL_OS);

            if (ids != null) {
                for (int i = 0; i < ids.length; i++) {
                    delsql.append(" or ID=" + ids[i]);
                }
            }

            pstmt = conn.prepareStatement(delsql.toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_006", e);
        } finally {
            try {
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

    /**
    创建Operation
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55900AB
     */
    public void createOperation(Operation operation) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
          cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            int i=1;
            pstmt = conn.prepareStatement(ADD_O);
            pstmt.setString(i++, String.valueOf(SequenceMan.nextID(2)));
            pstmt.setString(i++, operation.getName());
            pstmt.setString(i++, operation.getDescription());
            pstmt.setLong(i++, operation.getResourceID());
            pstmt.setString(i++, operation.getCode());
            pstmt.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
		throw new DAOException("PRV_OPT_007", e);
        } finally {
            try {
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

    /**
    获取指定资源的Operation
    @param resource
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559010A
     */
    public Collection getOperations(Resource resource)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection operations = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(LISTALL_O_ByRsID);
            pstmt.setString(1, String.valueOf(resource.getId()));
            rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
            while (rs.next()) {
                Operation opt = factory.createOperation();
                opt.setId(rs.getLong("ID"));
                opt.setName(rs.getString("Name"));
                opt.setResourceID(rs.getLong("Resource_ID"));
                opt.setDescription(rs.getString("Description"));
                opt.setCode(rs.getString("CODE"));
                operations.add(opt);
            }

            return operations;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_008", e);
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

    /**
    获取所有的Operation
    @return
    @throws com.gever.db.DAOException
    @roseuid 40BAA5590196
     */
    //==========================================================
    //胡勇添加，增加JSP视图列表排序功能
    private String[] orderby;
    public void setOrderby(String[] s){ this.orderby = s; }
    //==========================================================
    public Collection getOperations() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection operations = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            //==========================================================
            //胡勇添加，增加JSP视图列表排序功能
            pstmt = conn.prepareStatement(OrderList.getInstance().formatSQL(orderby, LISTALL_O));
            //==========================================================
            //pstmt = conn.prepareStatement(LISTALL_O);
            rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
	        while (rs.next()) {
                Operation opt = factory.createOperation();
                opt.setId(rs.getLong("ID"));
                opt.setName(rs.getString("Name"));
                opt.setResourceID(rs.getLong("Resource_ID"));
                opt.setResourceName(rs.getString("resourceName"));
                opt.setDescription(rs.getString("Description"));
                opt.setCode(rs.getString("CODE"));
                opt.setRolePerm(false);
                operations.add(opt);
            }
            return operations;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_009", e);
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

    /**
    分页获取Operation
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55901F4
     */
    public Collection getOperations(int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection operations = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(LISTALL_O);
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start, 2, start + count);
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
            while (rs.next()) {
                Operation opt = factory.createOperation();
                opt.setId(rs.getLong("ID"));
                opt.setName(rs.getString("Name"));
                opt.setResourceID(rs.getLong("Resource_ID"));
                opt.setDescription(rs.getString("Description"));
                opt.setCode(rs.getString("CODE"));
                operations.add(opt);
            }

            return operations;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_010", e);
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
	/**
	 分页获取Operation
	 @param start
	 @param count
	 @return
	 @throws com.gever.exception.db.DAOException
	 @roseuid 40BAA55901F4
	  */
	 public Collection getOperationsByResid(String id)
		 throws DAOException {
		 ConnectionProvider cp = null;
		 Connection conn = null;
		 ResultSet rs = null;
		 PreparedStatement pstmt = null;
		 Collection operations = new ArrayList();

		 try {
			 cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			 conn = cp.getConnection();

             //==========================================================
             //胡勇添加，增加JSP视图列表排序功能
             pstmt = conn.prepareStatement(OrderList.getInstance().formatSQL(orderby, GET_OPT_BY_RESID));
             //==========================================================
			 //pstmt = conn.prepareStatement(GET_OPT_BY_RESID);
			 pstmt.setString(1,id);
			 rs = pstmt.executeQuery();
			 PrivilegeFactory factory=PrivilegeFactory.getInstance();
			 while (rs.next()) {
				 Operation opt = factory.createOperation();
				 opt.setId(rs.getLong("ID"));
				 opt.setName(rs.getString("Name"));
				 opt.setResourceID(rs.getLong("Resource_ID"));
                                 opt.setResourceName(rs.getString("resourceName"));
				 opt.setDescription(rs.getString("Description"));
                                 opt.setCode(rs.getString("CODE"));
				 operations.add(opt);
			 }

			 return operations;
		 } catch (Exception e) {
			 throw new DAOException("PRV_OPT_010", e);
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

    /**
    查询Operation
    @param searchQuery
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA5590263
     */
    public Collection findOperations(String searchQuery, int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection operations = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(searchQuery);
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start, 2, start + count);
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();
			PrivilegeFactory factory=PrivilegeFactory.getInstance();
            while (rs.next()) {
                Operation opt =factory.createOperation();
                opt.setId(rs.getLong("ID"));
                opt.setName(rs.getString("Name"));
                opt.setResourceID(rs.getLong("Resource_ID"));
                opt.setDescription(rs.getString("Description"));
                operations.add(opt);
            }

            return operations;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_011", e);
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

    /**
    获取操作总数
    @return
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55902DE
     */
    public int getOperationCount() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int rowcount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GETCOUNT);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               rowcount = rs.getInt(1);
            }

            return rowcount;
        } catch (Exception e) {
			throw new DAOException("PRV_OPT_012", e);
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

	/**
	 * 获取用户的角色权限集合（一个用户可能包含多个角色）
	 * @param uid
	 * @return
	 * @throws DAOException
	 */
	public Collection getUserRolePerm(String uid)
		throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_USER_ROLE_PERM);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			  RolePermission roleperm=PrivilegeFactory.getInstance().createRolePermission();
			  roleperm.setResopid(rs.getLong("resoperation_id"));
			  roleperm.setResourceid(rs.getLong("resource_id"));
                          roleperm.setRes_code(rs.getString("rcode") );
                          roleperm.setOpt_code(rs.getString("ocode") );
			  list.add(roleperm);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_OPT_013", e);
		} finally {
			try {
				if (rs!=null)
				rs=null;
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

}
