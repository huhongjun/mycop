/*
 * 功能描述 权限映射模块的数据库操作
 * 创建日期 2004-11-25 10:54:14
 */
package com.gever.sysman.privilege.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gever.config.Constants;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.sysman.privilege.dao.PermissionMapDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.model.PermissionMap;


/**
 * @author Hu.Walker
 */
public class DefaultPermissionMapDAO implements PermissionMapDAO {
    private static SQLFactory sqlFactory = PrivilegeFactory.getInstance()
                                                           .getSQLFactory();
    public final static int ROOT_NODE = 1;
    public final static int CONFIG_NODE = 2;
    public final static int ACTION_PATH = 3;

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#clearDataBase()
     */
    public void clearDataBase(int node, String actionPath, Collection list)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
        } catch (DefaultException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        switch (node) {
        case ROOT_NODE:
            clearDBRoot(conn, list);

            break;

        case CONFIG_NODE:
            clearDBConfig(actionPath, conn, list);

            break;

        case ACTION_PATH:
            clearDBAction(actionPath, list, conn);
        }
    }

    // 清理表中的CONFIG冗余数据
    private void clearDBRoot(Connection con, Collection cfgList)
        throws DAOException {
        Connection conn = con;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();

        if ((cfgList == null) || (cfgList.size() < 1)) {
            return;
        }

        sql.append("DELETE FROM T_ACTION_OPT_MAP WHERE ACTION not like '");

        boolean isFirst = true;

        for (Iterator action = cfgList.iterator(); action.hasNext();) {
            if (isFirst) {
                sql.append((String) action.next());
                sql.append("%'");
                isFirst = false;
            }

            sql.append(" AND ACTION not like '");
            sql.append((String) action.next());
            sql.append("%' ");
        }

        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("PRI_MAP_001", DAOException.ERROR, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("PRI_MAP_001", DAOException.ERROR, ex);
            }
        }
    }

    // 清理表中的ACTIONPATH冗余数据
    private void clearDBConfig(String config, Connection con,
        Collection actionList) throws DAOException {
        Connection conn = con;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();

        if ((actionList == null) || (actionList.size() < 1)) {
            return;
        }

        if ((config == null) || "".equals(config)) {
            config = "config/";
        }

        sql.append("DELETE FROM T_ACTION_OPT_MAP WHERE ACTION like '");
        sql.append(config);
        sql.append("/%' AND ACTION != '");

        boolean isFirst = true;

        for (Iterator action = actionList.iterator(); action.hasNext();) {
            if (isFirst) {
                sql.append(config);
                sql.append((String) action.next());
                sql.append("'");
                isFirst = false;
            }

            sql.append(" AND ACTION != '");
            sql.append(config);
            sql.append((String) action.next());
            sql.append("' ");
        }

        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("PRI_MAP_002", DAOException.ERROR, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("PRI_MAP_002", DAOException.ERROR, ex);
            }
        }
    }

    // 清理表中的方法冗余数据
    private void clearDBAction(String actionPath, Collection methodList,
        Connection con) throws DAOException {
        Connection conn = con;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();

        if ((methodList == null) || (methodList.size() < 1)) {
            return;
        }

        sql.append("DELETE FROM T_ACTION_OPT_MAP WHERE ACTION = '");
        sql.append(actionPath);
        sql.append("'");

        for (Iterator iter = methodList.iterator(); iter.hasNext();) {
            sql.append(" AND METHOD != '");
            sql.append((String) iter.next());
            sql.append("' ");
        }

        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("PRI_MAP_003", DAOException.ERROR, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                throw new DAOException("PRI_MAP_003", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#createPermissionMap(com.gever.sysman.privilege.model.PermissionMap)
     */
    public void createPermissionMap(PermissionMap map)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            pstmt = conn.prepareStatement(sqlFactory.get("MAP_CREATE"));

            int i = 1;
            pstmt.setString(i++, map.getActionPath());
            pstmt.setString(i++, map.getMethodName());
            pstmt.setString(i++, map.getResCode());
            pstmt.setString(i++, map.getResOpCode());

            pstmt.executeUpdate();
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_004", DAOException.ERROR, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_004", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#findPermissionMapByID(java.lang.Long)
     */
    public PermissionMap findPermissionMapByID(long id)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PermissionMap perMap = null;
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("MAP_FINDBYID"));
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                perMap = priviFactory.createPermissionMap();
                perMap.setId(rs.getLong("ID"));
                perMap.setActionPath(rs.getString("ACTION"));
                perMap.setMethodName(rs.getString("METHOD"));
                perMap.setResCode(rs.getString("RES_CODE"));
                perMap.setResOpCode(rs.getString("RES_OPT_CODE"));

                return perMap;
            }

            return null;
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_005", DAOException.ERROR, ex);
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
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_005", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#findPermissionMapByPath(java.lang.String)
     */
    public List findPermissionMapByPath(String actionPath)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        long id;
        String action = null;
        String tmpAction = null;
        String method = null;
        String res_code = null;
        String res_opt_code = null;
        PermissionMap perMap = null;
        List methodList = new ArrayList();
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("MAP_FINDBYPATH"));
            pstmt.setString(1, actionPath);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                id = rs.getLong("ID");
                action = rs.getString("ACTION");
                method = rs.getString("METHOD");
                res_code = rs.getString("RES_CODE");
                res_opt_code = rs.getString("RES_OPT_CODE");

                perMap = priviFactory.createPermissionMap();
                perMap.setId(id);
                perMap.setActionPath(action);
                perMap.setMethodName(method);
                perMap.setResCode(res_code);
                perMap.setResOpCode(res_opt_code);

                methodList.add(perMap);
            }

            if (methodList.size() > 0) {
                return methodList;
            }

            return null;
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_006", DAOException.ERROR, ex);
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
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_006", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#getAllMap()
     */
    public Map getAllMap() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        long id;
        String action = null;
        String tmpAction = null;
        String method = null;
        String res_code = null;
        String res_opt_code = null;
        PermissionMap perMap = null;
        Map methodMap = new HashMap();
        Map actionMap = new HashMap();
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("MAP_GETALL"));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                id = rs.getLong("ID");
                action = rs.getString("ACTION");
                method = rs.getString("METHOD");
                res_code = rs.getString("RES_CODE");
                res_opt_code = rs.getString("RES_OPT_CODE");

                perMap = priviFactory.createPermissionMap();
                perMap.setId(id);
                perMap.setActionPath(action);
                perMap.setMethodName(method);
                perMap.setResCode(res_code);
                perMap.setResOpCode(res_opt_code);

                if ((tmpAction != null) && !action.equals(tmpAction)) {
                    actionMap.put(tmpAction, methodMap);
                    methodMap = new HashMap();
                }

                methodMap.put(method, perMap);

                tmpAction = action;
            }

            actionMap.put(tmpAction, methodMap);

            return actionMap;
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_007", DAOException.ERROR, ex);
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
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_007", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#updatePermissionMap(com.gever.sysman.privilege.model.PermissionMap)
     */
    public void updatePermissionMap(PermissionMap map)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "MAP_UPDATE_ID";
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            if (map.getId() < 1) {
                sql = "MAP_UPDATE_ACT";
            }

            pstmt = conn.prepareStatement(sqlFactory.get(sql));

            int i = 1;
            pstmt.setString(i++, map.getResCode());
            pstmt.setString(i++, map.getResOpCode());
            pstmt.setString(i++, map.getActionPath());
            pstmt.setString(i++, map.getMethodName());

            if (sql.equals("MAP_UPDATE_ID")) {
                pstmt.setLong(i++, map.getId());
            }

            pstmt.executeUpdate();
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_008", DAOException.ERROR, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_008", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#haveActionMethod(com.gever.sysman.privilege.model.PermissionMap)
     */
    public boolean haveActionMethod(PermissionMap map)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        PermissionMap perMap = null;
        String sql = "MAP_ISHAVE_ACT";
        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            if (map.getId() > 0) {
                sql = "MAP_ISHAVE_ID";
            }

            pstmt = conn.prepareStatement(sqlFactory.get(sql));

            if (sql.equals("MAP_ISHAVE_ID")) {
                pstmt.setLong(1, map.getId());
            } else {
                pstmt.setString(1, map.getActionPath());
                pstmt.setString(2, map.getMethodName());
            }

            rs = pstmt.executeQuery();

            rs.next();

            if (rs.getInt("COUN") > 0) {
                return true;
            }

            return false;
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_009", DAOException.ERROR, ex);
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
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_009", DAOException.ERROR, ex);
            }
        }
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PermissionMapDAO#exportDDL(java.lang.String)
     */
    public String exportDDL(String action) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        StringBuffer ddl = new StringBuffer();

        PrivilegeFactory priviFactory = PrivilegeFactory.getInstance();

        if ("/".equals(action)) {
            action = "%";
        }

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("MAP_EXPORT"));

            pstmt.setString(1, action);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ddl.append(
                    "INSERT INTO T_ACTION_OPT_MAP (ACTION,METHOD,RES_CODE,RES_OPT_CODE) VALUES('");
                ddl.append(rs.getString("ACTION"));
                ddl.append("','");
                ddl.append(rs.getString("METHOD"));
                ddl.append("','");
                ddl.append(rs.getString("RES_CODE"));
                ddl.append("','");
                ddl.append(rs.getString("RES_OPT_CODE"));
                ddl.append("');");
                ddl.append(" \n");
            }

            return ddl.toString().substring(0, ddl.toString().length() - 1);
        } catch (Exception ex) {
            throw new DAOException("PRI_MAP_010", DAOException.ERROR, ex);
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
            } catch (Exception ex) {
                throw new DAOException("PRI_MAP_010", DAOException.ERROR, ex);
            }
        }
    }
}
