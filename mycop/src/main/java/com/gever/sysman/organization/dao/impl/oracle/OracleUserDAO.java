/*
 * 创建日期 2004-10-19
 */
package com.gever.sysman.organization.dao.impl.oracle;

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
import com.gever.sysman.organization.dao.SQLFactory;
import com.gever.sysman.organization.dao.impl.DefaultUserDAO;
import com.gever.sysman.organization.model.User;

/**
 * @author Hu.Walker
 */
public class OracleUserDAO extends DefaultUserDAO {
    private static SQLFactory sqlFactory = OrganizationFactory.getInstance().getSQLFactory();
    
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
