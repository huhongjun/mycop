/*
 * 创建日期 2004-10-19
 */
package com.gever.sysman.privilege.dao.impl.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.connection.ConnectionUtil;
import com.gever.jdbc.database.dialect.Global;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.dao.impl.DefaultUserDAO;
import com.gever.sysman.privilege.model.I_User;


/**
 * @author Hu.Walker
 */
public class OracleUserDAO extends DefaultUserDAO {
//    private static String GET_USERS = "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEV,STATUS,VALIDDATE,NAME,GENDER,CODE FROM T_USER order by USERNAME";
//    private static String GET_Role_USERS =
//        "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEV,STATUS,VALIDDATE,NAME,GENDER FROM T_USER WHERE ID IN " +
//        "(SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";
    
    private static SQLFactory sqlFactory = PrivilegeFactory.getInstance().getSQLFactory();
    
//    private static String GET_USER_ByNAME="SELECT * FROM T_USER WHERE USERNAME=?";
    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.UserDAO#getUsers(int, int)
     */
    public Collection getUsers(int start, int count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        I_User user = null;
        String sql = Global.getDialect().getLimitString(sqlFactory.get("GET_USERS"));
        
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start, 2, start + count);
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user = PrivilegeFactory.getInstance().createUser();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserType(rs.getString("USERTYPE"));
                user.setLevel(rs.getString("LEV"));
                user.setStatus(rs.getString("STATUS"));
                user.setValidDate(rs.getString("VALIDDATE"));
                user.setName(rs.getString("NAME"));
                user.setGender(rs.getString("GENDER"));
                user.setCode(rs.getString("CODE"));

                list.add(user);
            }
        } catch (Exception e) {
            throw new DAOException("PRV_USER_006", e);
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

        return list;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.UserDAO#getUsersForSearch(java.lang.String, int, int)
     */
    public Collection getUsersForSearch(String strSQL, int start, int count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        I_User user = null;
        String sql = Global.getDialect().getLimitString(strSQL);

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start, 2, start + count);
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user = PrivilegeFactory.getInstance().createUser();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserType(rs.getString("USERTYPE"));
                user.setLevel(rs.getString("LEV"));
                user.setStatus(rs.getString("STATUS"));
                user.setValidDate(rs.getString("VALIDDATE"));
                user.setName(rs.getString("NAME"));
                user.setGender(rs.getString("GENDER"));
                user.setCode(rs.getString("CODE"));

                list.add(user);
            }
        } catch (Exception e) {
                        //throw new DAOException("PRV_USER_006", e);
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

        return list;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.UserDAO#getUsers(java.lang.String, int, int)
     */
    public Collection getUsers(String inputSQL, int start, int count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        I_User user = null;
        String sql = Global.getDialect().getLimitString(inputSQL);
        
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start, 2, start + count);
//            pstmt.setLong(1, start);
//            pstmt.setLong(2, start + count);
                        rs = pstmt.executeQuery();

            while (rs.next()) {
                user = PrivilegeFactory.getInstance().createUser();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserType(rs.getString(4));
                user.setLevel(rs.getString("LEV"));
                user.setStatus(rs.getString(6));
                user.setValidDate(rs.getString("VALIDDATE"));
                user.setName(rs.getString("NAME"));
                user.setGender(rs.getString("GENDER"));
                user.setCode(rs.getString("CODE"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
                        throw new DAOException("PRV_USER_006", e);
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

        return list;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.UserDAO#getRoleUsers(java.lang.String, int, int)
     */
    public Collection getRoleUsers(String rid, int start, int count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        I_User user = null;
        String sql = Global.getDialect().getLimitString(sqlFactory.get("GET_Role_USERS"));

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rid);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 2, start, 3, start + count);
//            pstmt.setLong(2, start);
//            pstmt.setLong(3, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user = PrivilegeFactory.getInstance().createUser();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserType(rs.getString("USERTYPE"));
                user.setLevel(rs.getString("LEV"));
                user.setStatus(rs.getString("STATUS"));
                user.setValidDate(rs.getString("VALIDDATE"));
                user.setName(rs.getString("NAME"));
                user.setGender(rs.getString("GENDER"));

                list.add(user);
            }
        } catch (Exception e) {
            throw new DAOException("PRV_USER_007", e);
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

        return list;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.UserDAO#getRoleUsers(java.lang.String)
     */
    public Collection getRoleUsers(String rid) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        I_User user = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_Role_USERS"));
            pstmt.setString(1, rid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user = PrivilegeFactory.getInstance().createUser();
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setUserType(rs.getString("USERTYPE"));
                user.setLevel(rs.getString("LEV"));
                user.setStatus(rs.getString("STATUS"));
                user.setValidDate(rs.getString("VALIDDATE"));
                user.setName(rs.getString("NAME"));
                user.setGender(rs.getString("GENDER"));

                list.add(user);
            }
        } catch (Exception e) {
            throw new DAOException("PRV_USER_007", e);
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

        return list;
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.organization.dao.UserDAO#findUserByUserName(java.lang.String)
     */
    public User findUserByUserName(String username) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        int role_usercount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_USER_ByNAME"));
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
              User user=OrganizationFactory.getInstance().createUser();
              user.setId(rs.getInt("ID"));
              user.setUserName(rs.getString("USERNAME"));
              user.setPassword(rs.getString("PASSWORD"));
              user.setUserType(rs.getString("USERTYPE"));
              user.setLevel(rs.getString("LEV"));
              user.setStatus(rs.getString("STATUS"));
              user.setValidDate(rs.getString("VALIDDATE"));
              user.setName(rs.getString("NAME"));
              user.setCode(rs.getString("CODE"));
              user.setGender(rs.getString("GENDER"));
              return user;
             } else
             return null;
        } catch (Exception e) {
           throw new DAOException("PRV_USER_010", e);
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
    
    /* （非 Javadoc）
     * @see com.gever.sysman.organization.dao.UserDAO#findUser(java.lang.String, long, long)
     */
    public Collection findUser(String searchQuery, long start, long count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        User user = null;
        String sql = sqlFactory.get("FIND_USER") + " WHERE " +
            searchQuery;
        sql += (" ORDER BY USERNAME ASC ");

        try {
          cp = ConnectionProviderFactory.getConnectionProvider(Constants.
              DATA_SOURCE);
          conn = cp.getConnection();

          pstmt = conn.prepareStatement(sql);

          rs = pstmt.executeQuery();

          for (int i = 1; rs.next(); i++) {
            if (start != 0 && i < start) {
              continue;
            }
            if (count != 0 && i >= count) {
              break;
            }

            user = OrganizationFactory.getInstance().createUser();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setUserType(rs.getString("USERTYPE"));
            user.setLevel(rs.getString("LEV"));
            user.setStatus(rs.getString("STATUS"));
            user.setValidDate(rs.getString("VALIDDATE"));
            user.setName(rs.getString("NAME"));
            user.setCode(rs.getString("CODE"));
            user.setGender(rs.getString("GENDER"));

            list.add(user);
          }
        }
        catch (Exception e) {
          throw new DAOException("ORG_User_004", e);
        }
        finally {
          try {
            ConnectionUtil.close(conn, pstmt, rs);
          }
          catch (Exception e) {
            throw new DAOException("ORG_User_004", e);
          }
        }
        return list;
  }

    /* （非 Javadoc）
     * @see com.gever.sysman.organization.dao.UserDAO#findUsersForPage(java.lang.String, long, long)
     */
    public Collection findUsersForPage(String searchQuery, long start, long count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        User user = null;
        String sql = sqlFactory.get("FIND_USER") + " WHERE " +
            searchQuery;
        sql += (" ORDER BY USERNAME ASC ");

        try {
          cp = ConnectionProviderFactory.getConnectionProvider(Constants.
              DATA_SOURCE);
          conn = cp.getConnection();

          sql = Global.getDialect().getLimitString(sql);
//          System.err.println(sql);
          pstmt = conn.prepareStatement(sql);
          pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, (int)start, 2, (int)(start + count));
//          pstmt.setLong(1, start);
//          pstmt.setLong(2, start + count);

          rs = pstmt.executeQuery();

          for (int i = 1; rs.next(); i++) {
            user = OrganizationFactory.getInstance().createUser();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setUserType(rs.getString("USERTYPE"));
            user.setLevel(rs.getString("LEV"));
            user.setStatus(rs.getString("STATUS"));
            user.setValidDate(rs.getString("VALIDDATE"));
            user.setName(rs.getString("NAME"));
            user.setCode(rs.getString("CODE"));
            user.setGender(rs.getString("GENDER"));

            list.add(user);
          }
        }
        catch (Exception e) {
          throw new DAOException("ORG_User_004", e);
        }
        finally {
          try {
            ConnectionUtil.close(conn, pstmt, rs);
          }
          catch (Exception e) {
            throw new DAOException("ORG_User_004", e);
          }
        }
        return list;
  }

}
