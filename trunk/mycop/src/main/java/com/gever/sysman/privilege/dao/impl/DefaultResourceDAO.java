package com.gever.sysman.privilege.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.privilege.dao.*;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.util.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultResourceDAO implements ResourceDAO {
    //    private static String ADD_RS =
    //        "INSERT INTO T_RESOURCE (ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE)" +
    //        "VALUES(?,?,?,?,?,?,?,?)";
    //    private static String UPDATE_RS = "UPDATE T_RESOURCE SET " +
    //        "ID=?,Name=?,Description=?,ResourceType=?,Parent_Id=?,LINK=?,TARGET=? ,CODE=? WHERE ID=?";
    //    private static String DEL_RS = "DELETE FROM T_RESOURCE WHERE ID=?";
    //    private static String GET_RS_ALL =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE " +
    //        " FROM  T_RESOURCE";
    //    private static String GET_RS_ByName =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE " +
    //        " FROM  T_RESOURCE WHERE Name=?";
    //    private static String GET_RS_ByCode =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE " +
    //        " FROM  T_RESOURCE WHERE Code=?";
    //
    //    private static String GET_RS =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE " +
    //        " FROM  T_RESOURCE WHERE ID=?";
    //    private static String FIND_RSID_ByName = "SELECT ID FROM  T_RESOURCE WHERE Name=?";
    //    private static String LIST_RS = "SELECT ID,Name,Description,ResourceType,Parent_Id ,LINK,TARGET,CODE FROM  T_RESOURCE";
    //    private static String GET_ROOT =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,Parent_Id,LINK,TARGET,CODE " +
    //        " FROM  T_RESOURCE WHERE Parent_Id=-1";
    //    private static String GET_CHILDS =
    //        "SELECT T1.ID,T1.Name,T2.Name as parentName,T1.Description,T1.ResourceType,T1.Parent_Id,T1.LINK,T1.TARGET,T1.CODE" +
    //        " FROM T_RESOURCE as T1, T_RESOURCE as T2 WHERE T1.Parent_Id=? and T1.Parent_Id = T2.ID order by T1.CODE";
    //    private static String GET_CHILDS_cn =
    //        "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE,(select count(ID) from T_RESOURCE where parent_id=T.id) as childnum " +
    //        " FROM  T_RESOURCE T WHERE Parent_Id=?";
    //    private static String GET_RSNUM = "SELECT COUNT(ID) FROM T_RESOURCE";
    //	private static String GET_RS_CHILDNUM = "SELECT COUNT(ID) FROM T_RESOURCE WHERE PARENT_ID=?";
    //    private static String GET_OPTION_ByRS = "SELECT ID,Name,Description,Resource_ID  FROM  T_RESOPERATION WHERE  Resource_ID=?";
    //    private static String DEL_OPTION_ByRS = "DELETE FROM T_RESOPERATION WHERE Resource_ID==? AND NAME=?";
    //    private static String ADD_OPTION = "INSERT INTO T_RESOPERATION (ID,Name,Description,Resource_ID,CODE) VALUES(?,?,?,?,?) ";
    //    private static String GET_RES_ISFOLDER =
    //        "SELECT a.id ,a.NAME,a.parent_id ,case when " +
    //        " (Select COUNT(*) AS CNT  FROM T_RESOURCE b WHERE a.id<>b.id AND b.parent_id=a.id ) >0 then '1' " +
    //        " else '0' end AS ISFOLDER FROM T_RESOURCE as A order by a.parent_id";
    private static SQLFactory sqlFactory = PrivilegeFactory.getInstance()
                                                           .getSQLFactory();

    public DefaultResourceDAO() {
    }

    public Resource findResourceByID(long id) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Resource res = PrivilegeFactory.getInstance().createResource();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS"));
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (target.trim().length() > 0)) {
                    res.setTarget(target.charAt(0));
                }
            }

            return res;
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

    public Collection findResourceByIDs(long[] ids) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Collection result = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = "SELECT ID,Name,Description,ResourceType,Parent_Id,LINK,TARGET,CODE FROM T_RESOURCE where";

            if ((ids == null) || (ids.length == 0)) {
                return result;
            }

            for (int i = 0; i < ids.length; i++) {
                if (i == 0) {
                    sql = sql + " id = ?";
                } else {
                    sql = sql + " or id = ?";
                }
            }

            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < ids.length; i++) {
                pstmt.setLong(i + 1, ids[i]);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Resource res = DefaultPrivilegeFactory.getInstance()
                                                      .createResource();

                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (target.trim().length() > 0)) {
                    res.setTarget(target.charAt(0));
                }

                result.add(res);
            }

            return result;
        } catch (Exception e) {
        	e.printStackTrace();
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

    public Resource findResourceByName(String name) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Resource res = PrivilegeFactory.getInstance().createResource();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS_ByName"));
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(rs.getLong("ID"));
                res.setName("Name");
                res.setDescription("Description");
                res.setResType("ResourceType");
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }
            }

            return res;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_002", e);
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

    public Resource findResourceByCode(String code) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Resource res = PrivilegeFactory.getInstance().createResource();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS_ByName"));
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                res.setId(rs.getLong("ID"));
                res.setName("Name");
                res.setDescription("Description");
                res.setResType("ResourceType");
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }
            }

            return res;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_002", e);
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

    public void deleteResource(Resource resource) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("DEL_RS"));
            pstmt.setString(1, String.valueOf(resource.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("PRV_RES_003", e);
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

    public long findResourceIDByName(String name) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long id = -1;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("FIND_RSID_ByName"));
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getLong("ID");
            }

            return id;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_004", e);
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

    public Collection getResourcesTree() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection ress = new ArrayList();
        Resource res = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RES_ISFOLDER"));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setIsFolder(rs.getString("ISFOLDER"));

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_005", e);
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

    public Collection getResources() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection ress = new ArrayList();
        Resource res = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS_ALL"));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_006", e);
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

    public Resource getRootResource() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_ROOT"));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }
            }

            return res;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_007", e);
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

    public int getResourceCount() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rscount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RSNUM"));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rscount = Integer.parseInt(rs.getString(1));
            }

            return rscount;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_008", e);
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

    public int getChildResourceCount(String resid) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rscount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS_CHILDNUM"));
            pstmt.setString(1, resid);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rscount = Integer.parseInt(rs.getString(1));
            }

            return rscount;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_008", e);
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

    public void updateResource(Resource resource) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rscount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("UPDATE_RS"));
            pstmt.setString(1, String.valueOf(resource.getId()));
            pstmt.setString(2, resource.getName());
            pstmt.setString(3, resource.getDescription());
            pstmt.setString(4, resource.getResType());
            pstmt.setString(5, String.valueOf(resource.getParentID()));
            pstmt.setString(6, resource.getLink());
            pstmt.setString(7, String.valueOf(resource.getTarget()));
            pstmt.setString(8, String.valueOf(resource.getCode()));
            pstmt.setString(9, String.valueOf(resource.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("PRV_RES_009", e);
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
     * @see com.gever.privilege.dao.ResourceDAO#getResources(int, int)
     */
    public Collection getResources(int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;
        Collection ress = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(sqlFactory.get(
                        "LIST_RS"));
            pstmt = conn.prepareStatement(sql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start,
                    2, start + count);

            //            pstmt.setLong(1, start);
            //            pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_010", e);
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

    //==========================================================
    //胡勇添加，增加JSP视图列表排序功能
    private String[] orderby;
    public void setOrderby(String[] s){ this.orderby = s; }
    //==========================================================
    public Collection getChileResources(String resid, int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;
        Collection ress = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String sql = Global.getDialect().getLimitString(sqlFactory.get(
                        "GET_CHILDS"));
            //==========================================================
            //胡勇添加，增加JSP视图列表排序功能
            sql = OrderList.getInstance().formatSQL(orderby,sql);
            //==========================================================
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, resid);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 2, start,
                    3, start + count);

            //			pstmt.setLong(2, start);
            //			pstmt.setLong(3, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setParentName(rs.getString("parentName"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_010", e);
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
     * @see com.gever.privilege.dao.ResourceDAO#findResource(java.lang.String, int, int)
     */
    public Collection findResource(String searchQuery, int start, int count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection ress = new ArrayList();
        Resource res = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String searchsql = Global.getDialect().getLimitString(searchQuery);
            pstmt = conn.prepareStatement(searchsql);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start,
                    2, start + count);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_011", e);
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
     * @see com.gever.privilege.dao.ResourceDAO#getChildren(com.gever.privilege.model.Resource)
     */
    public Collection getChildren(Resource resource) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;
        Collection ress = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_CHILDS"));
            pstmt.setString(1, String.valueOf(resource.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_012", e);
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

    public Collection getOperations(Resource resource)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;
        Collection ress = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_OPTION_ByRS"));
            pstmt.setString(1, String.valueOf(resource.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("Resource_ID"));
                res.setLink(rs.getString("LINK"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_013", e);
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

    public void removeOperation(Resource resource, Operation operation)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("DEL_OPTION_ByRS"));
            pstmt.setString(1, String.valueOf(operation.getResourceID()));
            pstmt.setString(2, operation.getName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("PRV_RES_014", e);
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

    public void createResource(Resource resource) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rscount = 0;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("ADD_RS"));

            int i = 1;
            pstmt.setString(i++, String.valueOf(SequenceMan.nextID(2)));
            pstmt.setString(i++, resource.getName());
            pstmt.setString(i++, resource.getDescription());
            pstmt.setString(i++, resource.getResType());
            pstmt.setString(i++, String.valueOf(resource.getParentID()));
            pstmt.setString(i++, resource.getLink());
            pstmt.setString(i++, String.valueOf(resource.getTarget()));
            pstmt.setString(i++, String.valueOf(resource.getCode()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("PRV_RES_015", e);
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

    public Collection getChilds(String id) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Resource res = null;
        Collection ress = new ArrayList();

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_CHILDS_cn"));
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setChildNum(rs.getInt("childnum"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);
            }

            return ress;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_016", e);
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

    public Collection getResourcesAndOperations() throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map map = new HashMap();
        Collection ress = new ArrayList();
        Collection ressAndoprs = new ArrayList(2);
        Resource res = null;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sqlFactory.get("GET_RS_ALL"));
            rs = pstmt.executeQuery();

            OperationDAO operationDAO = PrivilegeFactory.getInstance()
                                                        .getOperationDAO();

            while (rs.next()) {
                res = PrivilegeFactory.getInstance().createResource();
                res.setId(rs.getLong("ID"));
                res.setName(rs.getString("Name"));
                res.setDescription(rs.getString("Description"));
                res.setResType(rs.getString("ResourceType"));
                res.setParentID(rs.getLong("Parent_Id"));
                res.setLink(rs.getString("LINK"));
                res.setCode(rs.getString("CODE"));

                String target = rs.getString("TARGET");

                if ((target != null) && (!"".equals(target))) {
                    res.setTarget(rs.getString("TARGET").charAt(0));
                }

                ress.add(res);

                String resourceID = Long.toString(res.getId());
                map.put(resourceID,
                    operationDAO.getOperationsByResid(resourceID));
            }

            ressAndoprs.add(ress);
            ressAndoprs.add(map);

            return ressAndoprs;
        } catch (Exception e) {
            throw new DAOException("PRV_RES_006", e);
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
