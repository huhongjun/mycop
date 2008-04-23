package com.gever.sysman.privilege.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.RoleDAO;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.model.RolePermission;
import com.gever.sysman.util.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class DefaultRoleDAO implements RoleDAO {
    private static String GET_Role_Permission = "SELECT ROLE_ID,RESOURCE_ID,RESOPERATION_ID FROM T_ROLE_PERMISSION WHERE ROLE_ID=?";
    private static String ADD_Role_Permission = "INSERT INTO T_ROLE_PERMISSION (ROLE_ID,RESOURCE_ID,RESOPERATION_ID) VALUES(?,?,?)";
    private static String DEL_Role_Permission = "DELETE FORM T_ROLE_PERMISSION WHERE ROLE_ID=? AND RESOURCE_ID=? AND RESOPERATION_ID=?";
    private static String UPDATE_Role_Permission = "UPDATE T_ROLE_PERMISSION SET ROLE_ID=?,RESOURCE_ID=?,RESOPERATION_ID=? ";
    private static String ADD_ROLE = "INSERT INTO T_ROLE (ID,Name,Description) VALUES(?,?,?) ";
    private static String UPDATE_ROLE = "UPDATE T_ROLE SET Name=?,Description=? WHERE ID=?";
    private static String DEL_ROLE = "DELETE FROM T_ROLE WHERE ID=?";
    private static String DEL_ROLES = "DELETE FROM T_ROLE WHERE 1=2 ";
    private static String DEL_User_ROLES="DELETE FROM T_USER_ROLE WHERE USER_ID=? AND (1=2 ";
    private static String DEL_ALL_User_ROLES="DELETE FROM T_USER_ROLE WHERE USER_ID=?  ";
    private static String GET_ROLE = "SELECT ID,Name,Description FROM T_ROLE WHERE ID=?";
    private static String LIST_ROLE = "SELECT ID,Name,Description FROM T_ROLE order by Name";
    private static String GET_COUNT = "SELECT COUNT(ID) FROM T_ROLE ";
    private static String ADD_USER = "INSERT INTO T_USER_ROLE (USER_ID,ROLE_ID) VALUES(?,?)";
    private static String REMOVE_USER = "DELETE FROM T_USER_ROLE WHERE USER_ID=? AND ROLE_ID=?";
	private static String GET_UserRole = "SELECT ID,NAME,Description,RoleType FROM T_ROLE WHERE ID IN(SELECT ROLE_ID FROM T_USER_ROLE WHERE USER_ID=?)";
	private static String GET_UserRoleByID = "SELECT ID,NAME,Description,RoleType FROM T_ROLE WHERE 1=2 ";
	private static String ADD_UserRole = "INSERT INTO T_USER_ROLE (USER_ID,ROLE_ID) VALUES(?,?)";

    //获取某角色包含的用户
    private static String GET_USER =
        "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender" +
        " FROM T_USER WHERE ID IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";
    private static String FIND_ROLE_ByName = "SELECT ID,Name,Description FROM T_ROLE WHERE Name=? ";
    private static String IF_CONTAIN = "SELECT COUNT(USER_ID) FROM T_USER_ROLE WHERE USER_ID=? AND ROLE_ID=?";
	private static String GET_RoleCOUNT="SELECT COUNT(ROLE_ID) FROM T_USER_ROLE WHERE USER_ID=?";
	private static String GET_OtherUserRole="SELECT ID,NAME,Description,RoleType FROM T_ROLE WHERE ID NOT IN(SELECT ROLE_ID FROM T_USER_ROLE WHERE USER_ID=?)";
	private static String GET_OtherRoleCOUNT="SELECT COUNT(ID) FROM T_ROLE WHERE ID NOT IN (SELECT ROLE_ID FROM T_USER_ROLE WHERE USER_ID=?)";

    public DefaultRoleDAO() {
    }

    public void createRole(Role role) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(ADD_ROLE);
            pstmt.setString(1, String.valueOf(SequenceMan.nextID(0)));
            pstmt.setString(2, role.getName());
            pstmt.setString(3, role.getDescription());
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_001", e);
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
     * 为用户增加用户角色
     */
	public void createUserRole(String roleid,String userid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(ADD_ROLE);
		    pstmt.setString(1,userid);
		    pstmt.setString(2,roleid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_002", e);
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
    * 获取当前用户还剩下的可供选择的角色
    */
	public Collection getOtherRolesByID(String id,long start,long count) throws DAOException{
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		Role role = null;

		try {
			String sql = Global.getDialect().getLimitString(GET_OtherUserRole);
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 2, (int)start, 3, (int)(start + count));
//			pstmt.setLong(2, start);
//			pstmt.setLong(3, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = PrivilegeFactory.getInstance().createRole();
				role.setId(rs.getLong("ID"));
				role.setName(rs.getString("Name"));
				role.setDescription(rs.getString("Description"));
				role.setRoleType(rs.getString("RoleType"));
				result.add(role);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_003", e);
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
	 * 当前用户还剩下的的可供选择的较色的数量
	 */
	  public int getOtherRoleCount(String id) throws DAOException{
		ConnectionProvider cp = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 Collection result = new ArrayList();
		 int user_rolecount = 0;

		 try {
			 cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			 conn = cp.getConnection();
			 pstmt = conn.prepareStatement(GET_OtherRoleCOUNT);
			 pstmt.setString(1,id);
			 rs = pstmt.executeQuery();

			 if (rs.next()) {
				 user_rolecount = rs.getInt(1);
			 }

			 return user_rolecount;
		 } catch (Exception e) {
			throw new DAOException("PRV_ROLE_004", e);
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
	 * 获取当前用户还剩下的可供选择的角色
	 */
	 public Collection getOtherRolesByID(String id) throws DAOException{
		 ConnectionProvider cp = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 Collection result = new ArrayList();
		 Role role = null;

		 try {
			 cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			 conn = cp.getConnection();
			 pstmt = conn.prepareStatement(GET_OtherUserRole);
			 pstmt.setString(1, id);
			 rs = pstmt.executeQuery();

			 while (rs.next()) {
				 role = PrivilegeFactory.getInstance().createRole();
				 role.setId(rs.getLong("ID"));
				 role.setName(rs.getString("Name"));
				 role.setDescription(rs.getString("Description"));
				 role.setRoleType(rs.getString("RoleType"));
				 result.add(role);
			 }

			 return result;
		 } catch (Exception e) {
			throw new DAOException("PRV_ROLE_005", e);
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

    public Collection getResources(Role role) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_Role_Permission);
            pstmt.setString(1, String.valueOf(role.getId()));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                RolePermission rp = PrivilegeFactory.getInstance()
                                                           .createRolePermission();
                rp.setRoleid(rs.getLong("ROLE_ID"));
                rp.setResopid(rs.getLong("RESOPERATION_ID"));
                rp.setResourceid(rs.getLong("RESOURCE_ID"));
                result.add(rp);
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_006", e);
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

    public void addResources(Role role, Collection resources)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Iterator result = resources.iterator();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(ADD_Role_Permission);

            while (result.hasNext()) {
                pstmt.setString(1, String.valueOf(role.getId()));
                pstmt.setString(2, role.getName());
                pstmt.setString(3, role.getDescription());
                rs = pstmt.executeQuery();
            }
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_007", e);
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

    public void removeResource(Role role, Resource resource)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        Iterator iopt = resource.getOperations().iterator();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(DEL_Role_Permission);
            pstmt.setString(1, String.valueOf(role.getId()));
            pstmt.setString(2, String.valueOf(resource.getId()));

            while (iopt.hasNext()) {
                Operation opt = (Operation) iopt.next();
                pstmt.setString(1, String.valueOf(opt.getId()));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_008", e);
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

    public Role findRoleByID(long id) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Role role = PrivilegeFactory.getInstance().createRole();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_ROLE);
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();
		    if (rs.next()) {
                role.setId(rs.getInt("ID"));
                role.setName(rs.getString("Name"));
                role.setDescription(rs.getString("Description"));
            }

            return role;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_009", e);
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

    public Role findRoleByName(String name) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Role role = PrivilegeFactory.getInstance().createRole();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(FIND_ROLE_ByName);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                role.setId(rs.getInt("ID"));
                role.setName(rs.getString("Name"));
                role.setDescription(rs.getString("Description"));
            }

            return role;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_0010", e);
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

	public boolean haveRoleName(String rolename) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Role role = PrivilegeFactory.getInstance().createRole();

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(FIND_ROLE_ByName);
			pstmt.setString(1, rolename);
			rs = pstmt.executeQuery();

			if (rs.next())
              return true;
            else
			return false;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_011", e);
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



    public void deleteRolesByIds(String[] ids) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        StringBuffer delsql=new StringBuffer(DEL_ROLES);
         if (ids!=null){
        	for (int i=0;i<ids.length;i++){
        		delsql.append(" or ID="+ids[i]);
        	}
        }
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(delsql.toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_012", e);
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
	public void deleteUserRolesByIds(String uid,String[] ids) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer delsql=new StringBuffer(DEL_User_ROLES);
		if (ids!=null){
			for (int i=0;i<ids.length;i++){
				delsql.append(" or Role_ID="+ids[i]);
			}
		}
		delsql.append(")");
		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(delsql.toString());
			pstmt.setString(1,uid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_013", e);
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

        public void deleteUserRolesByIds(String uid) throws DAOException {
                    ConnectionProvider cp = null;
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    StringBuffer delsql=new StringBuffer(DEL_ALL_User_ROLES);

                    try {
                            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
                            conn = cp.getConnection();
                            pstmt = conn.prepareStatement(delsql.toString());
                            pstmt.setString(1,uid);
                            pstmt.executeUpdate();
                    } catch (Exception e) {
                            throw new DAOException("PRV_ROLE_013", e);
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

    public void deleteRole(Role role) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(DEL_ROLE);
            pstmt.setString(1, String.valueOf(role.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_014", e);
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

    public void updateRole(Role role) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(UPDATE_ROLE);
            pstmt.setString(1, role.getName());
            pstmt.setString(2, role.getDescription());
			pstmt.setString(3, String.valueOf(role.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_015", e);
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

    public long findRoleIDByName(String name) throws DAOException {
        return findRoleByName(name).getId();
    }

    public Collection getRoles() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(LIST_ROLE);
            rs = pstmt.executeQuery();

            while (rs.next()) {
				Role role = PrivilegeFactory.getInstance().createRole();
                role.setId(rs.getInt("ID"));
                role.setName(rs.getString("Name"));
                role.setDescription(rs.getString("Description"));
                result.add(role);
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_016", e);
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

    public int getRoleCount() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        int rolecount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_COUNT);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rolecount = rs.getInt(1);
            }

            return rolecount;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_017", e);
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

    /*
     * @see com.gever.privilege.dao.RoleDAO#getRoles(int, int)
     */
    //==========================================================
    //胡勇添加，增加JSP视图列表排序功能
    private String[] orderby;
    public void setOrderby(String[] s){ this.orderby = s; }
    //==========================================================
    public Collection getRoles(long start, long count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(LIST_ROLE);
            //==========================================================
            //胡勇添加，增加JSP视图列表排序功能
            sql = OrderList.getInstance().formatSQL(orderby,sql);
            //==========================================================
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, (int)start, 2, (int)(start + count));
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Role role = PrivilegeFactory.getInstance().createRole();
                role.setId(rs.getInt("ID"));
                role.setName(rs.getString("Name"));
                role.setDescription(rs.getString("Description"));
                result.add(role);
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_018", e);
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

    /*
     * @see com.gever.privilege.dao.RoleDAO#findRole(java.lang.String, int, int)
     */
    public Collection findRole(String searchQuery, int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(searchQuery);
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, (int)start, 2, (int)(start + count));
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_019", e);
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
     *获取某角色的所有用户，分页显示
     */
    public Collection getUsers(Role role, int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(GET_USER);
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 2, (int)start, 3, (int)(start + count));
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);

            pstmt.setString(1, String.valueOf(role.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
				I_User iuser = PrivilegeFactory.getInstance().createUser();
                iuser.setId(rs.getInt("ID"));
                iuser.setUserName(rs.getString("USERNAME"));
                iuser.setPassword(rs.getString("PASSWORD"));
                iuser.setUserType("USERTYPE");
                iuser.setLevel("Level");
                iuser.setStatus("Status");
                iuser.setValidDate("Validdate");
                iuser.setName("Name");
                iuser.setCode("Code");
                iuser.setGender("Gender");
                result.add(iuser);
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_020", e);
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
     *获取某角色的所有用户
     */
    public Collection getUsers(Role role) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(GET_USER);
            pstmt.setString(1, String.valueOf(role.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
				I_User iuser = PrivilegeFactory.getInstance().createUser();
                iuser.setId(rs.getInt("ID"));
                iuser.setUserName(rs.getString("USERNAME"));
                iuser.setPassword(rs.getString("PASSWORD"));
                iuser.setUserType("USERTYPE");
                iuser.setLevel("Level");
                iuser.setStatus("Status");
                iuser.setValidDate("Validdate");
                iuser.setName("Name");
                iuser.setCode("Code");
                iuser.setGender("Gender");
                result.add(iuser);
            }

            return result;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_021", e);
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

    /*
     * @see com.gever.privilege.dao.RoleDAO#contain(com.gever.privilege.model.Role, com.gever.privilege.model.I_User)
     */
    public boolean contain(Role role, I_User user) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isContain = false;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(IF_CONTAIN);
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, String.valueOf(role.getId()));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                isContain = true;
            }

            return isContain;
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_022", e);
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

    /*
     * @see com.gever.privilege.dao.RoleDAO#addUsers(com.gever.privilege.model.Role, java.util.Collection)
     */
    public void addUsers(Role role, Collection users) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        Iterator users_i = users.iterator();
        I_User iuser = PrivilegeFactory.getInstance().createUser();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(ADD_USER);
            
            while (users_i.hasNext()) {
                iuser = (I_User) users_i.next();
                pstmt.setString(1, String.valueOf(iuser.getId()));
                pstmt.setString(2, String.valueOf(role.getId()));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_023", e);
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

	/*
	  * @see com.gever.sysman.privilege.dao.UserDAO#addRoles(com.gever.sysman.organization.model.User, java.util.Set)
	  */
	 public void addRoles(User user, Collection roles) throws DAOException {
		 ConnectionProvider cp = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 Iterator iroles = roles.iterator();

		 try {
			 cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			 conn = cp.getConnection();
			 pstmt = conn.prepareStatement(ADD_UserRole);

			 while (iroles.hasNext()) {
				 Role role = (Role) iroles.next();
				 pstmt.setString(1, String.valueOf(user.getId()));
				 pstmt.setString(2, String.valueOf(role.getId()));
				 pstmt.executeUpdate();
			 }
		 } catch (Exception e) {
			throw new DAOException("PRV_ROLE_024", e);
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


    /*
     * @see com.gever.privilege.dao.RoleDAO#removeUser(com.gever.privilege.model.Role, com.gever.privilege.model.I_User)
     */
    public void removeUser(Role role, I_User user) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(REMOVE_USER);
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, String.valueOf(role.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
			throw new DAOException("PRV_ROLE_025", e);
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
	 * 根据用户ID获取用户的所有角色
	 */
	public Collection getRolesByID(String id, long start, long count)
		throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		Role role = null;

		try {
			String sql = Global.getDialect().getLimitString(GET_UserRole);
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 2, (int)start, 3, (int)(start + count));
//			pstmt.setLong(2, start);
//			pstmt.setLong(3, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = PrivilegeFactory.getInstance().createRole();
				role.setId(rs.getLong("ID"));
				role.setName(rs.getString("Name"));
				role.setDescription(rs.getString("Description"));
				role.setRoleType(rs.getString("RoleType"));
				result.add(role);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_026", e);
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
	 *根据用户id获取所有用户的角色
	 *
	 */
	public Collection getRolesByID(String id)
		throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		Role role = null;

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_UserRole);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = PrivilegeFactory.getInstance().createRole();
				role.setId(rs.getLong("ID"));
				role.setName(rs.getString("Name"));
				role.setDescription(rs.getString("Description"));
				role.setRoleType(rs.getString("RoleType"));
				result.add(role);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_027", e);
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
	 * 根据角色ID数组获取相关的角色集合
	 */
	public Collection getRolesByID(String[] roleids) throws DAOException{
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		Role role = null;

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			StringBuffer sbsql=new StringBuffer(GET_UserRoleByID);
			for (int i=0;i<roleids.length;i++) {
				sbsql.append(" or ID=");
				sbsql.append(roleids[i]);
			}
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = PrivilegeFactory.getInstance().createRole();
				role.setId(rs.getLong("ID"));
				role.setName(rs.getString("Name"));
				role.setDescription(rs.getString("Description"));
				role.setRoleType(rs.getString("RoleType"));
				result.add(role);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_028", e);
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


	/*
	 * @see com.gever.sysman.privilege.dao.UserDAO#getRoles(com.gever.sysman.organization.model.User)
	 */
	public Collection getRoles(User user) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		Role role = null;

		try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(GET_UserRole);
			pstmt.setString(1, String.valueOf(user.getId()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				role = PrivilegeFactory.getInstance().createRole();
				role.setId(rs.getLong("ID"));
				role.setName(rs.getString("Name"));
				role.setDescription(rs.getString("Description"));
				role.setRoleType(rs.getString("RoleType"));
				result.add(role);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_029", e);
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

	public int getUserRoleCount(String id) throws DAOException{
		ConnectionProvider cp = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 Collection result = new ArrayList();
		 int user_rolecount = 0;

		 try {
			 cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
			 conn = cp.getConnection();
			 pstmt = conn.prepareStatement(GET_RoleCOUNT);
			 pstmt.setString(1,id);
			 rs = pstmt.executeQuery();

			 if (rs.next()) {
				 user_rolecount = rs.getInt(1);
			 }

			 return user_rolecount;
		 } catch (Exception e) {
			throw new DAOException("PRV_ROLE_030", e);
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


}
