package com.gever.sysman.organization.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.*;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.SQLFactory;
import com.gever.sysman.organization.dao.UserJobDAO;
import com.gever.sysman.organization.model.UserJob;
import com.gever.sysman.organization.model.impl.DefaultUserJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultUserJobDAO implements UserJobDAO {
	protected SQLFactory sqlFactory = OrganizationFactory.getInstance().getSQLFactory();
	
    public void createUserJob(UserJob userJob) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO T_JOB_PERSON(ID, JOB_ID) VALUES (?,?)";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            int i = 1;
            pstmt.setInt(i++, userJob.getUser());
            pstmt.setInt(i++, userJob.getJob());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_001", e);
            }
        }
    }

    public void createUserJob(int[] userId, int[] jobId)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        Statement stmt = null;
        String sql = "";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            stmt = conn.createStatement();

            for (int i = 0; i < userId.length; i++) {
                sql = "INSERT INTO T_JOB_PERSON(ID, JOB_ID) VALUES (" +
                    userId[i] + "," + jobId[i] + ")";
                stmt.addBatch(sql);
            }
			
//			System.err.println("job sql:" + sql.toString());
            stmt.executeBatch();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_002", e);
        } finally {
            try {
                ConnectionUtil.close(conn, stmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_002", e);
            }
        }
    }

    public void updateUserJob(UserJob userJob, int userUpdate, int jobUpdate)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE T_JOB_PERSON SET ID=?, JOB_ID=? WHERE ID=? AND JOB_ID=?";
		
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            int i = 1;
            pstmt.setInt(i++, userJob.getUser());
            pstmt.setInt(i++, userJob.getJob());
            pstmt.setInt(i++, userUpdate);
            pstmt.setInt(i++, jobUpdate);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_002", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_002", e);
            }
        }
    }

    public void deleteUserJob(int[] userId, int[] jobId)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM T_JOB_PERSON WHERE (ID=" + userId[0] +
            " AND JOB_ID=" + jobId[0] + ")";

        for (int i = 1; i < userId.length; i++) {
            sql += (" OR (ID=" + userId[i] + " AND JOB_ID=" + jobId[i] + ")");
        }
//		System.err.println("job sql:" + sql.toString());
		
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_003", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_003", e);
            }
        }
    }

    public void deleteUserJobByJob(int jobId) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM T_JOB_PERSON WHERE JOB_ID=" + jobId + "";
//		System.err.println("job sql:" + sql.toString());

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

//            System.out.println(sql);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_003", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_003", e);
            }
        }
    }

    public void deleteUserJobByUser(int userId) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM T_JOB_PERSON WHERE ID=" + userId + "";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_003", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_003", e);
            }
        }
    }

    // 批量删除接口
    public void deleteUserJobByUser(String[] userId) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM T_JOB_PERSON WHERE ID=" + Integer.parseInt(userId[0]) + "";
        for(int i=1;i<userId.length;i++){
        	sql += " OR ID=" + Integer.parseInt(userId[i]);
        }

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_003", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_003", e);
            }
        }
    }
    
    public Collection findUserJob(String searchQuery, long start, long count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        UserJob userJob = null;
        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT ");
//        sql.append(" T_JOB_PERSON.ID AS ID, ");
//        sql.append(" T_JOB_PERSON.JOB_ID AS JOB_ID, ");
//        sql.append(" T_USER.USERNAME AS USERNAME, ");
//        sql.append(" T_JOB.NAME AS JOBNAME ");
//        sql.append(" FROM ");
//        sql.append(" T_JOB_PERSON ");
//        sql.append(" LEFT JOIN T_USER ON(T_JOB_PERSON.ID=T_USER.ID) ");
//        sql.append(" LEFT JOIN T_JOB ON(T_JOB_PERSON.JOB_ID=T_JOB.ID) ");
//        sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_USER_JOB"));
        sql.append(" " + searchQuery);
        sql.append(" ORDER BY USERNAME ASC, JOBNAME ASC ");
        
//		System.err.println("job sql:" + sql.toString());

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            pstmt = conn.prepareStatement(sql.toString());

            rs = pstmt.executeQuery();

            for (int i = 1; rs.next(); i++) {
                if ((start != 0) && (i < start)) {
                    continue;
                }

                if ((count != 0) && (i >= count)) {
                    break;
                }

                userJob = OrganizationFactory.getInstance().createUserJob();
                userJob.setUser(rs.getInt("ID"));
                userJob.setJob(rs.getInt("JOB_ID"));
                userJob.setUserName(rs.getString("USERNAME"));
                userJob.setJobName(rs.getString("JOBNAME"));

                list.add(userJob);
            }
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_004", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_004", e);
            }
        }

        return list;
    }

    public Collection findUserJobsForPage(String searchQuery, long start,
        long count) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        UserJob userJob = null;
        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT ");
//        sql.append(" T_JOB_PERSON.ID AS ID, ");
//        sql.append(" T_JOB_PERSON.JOB_ID AS JOB_ID, ");
//        sql.append(" T_USER.USERNAME AS USERNAME, ");
//        sql.append(" T_JOB.NAME AS JOBNAME ");
//        sql.append(" FROM ");
//        sql.append(" T_JOB_PERSON ");
//        sql.append(" LEFT JOIN T_USER ON(T_JOB_PERSON.ID=T_USER.ID) ");
//        sql.append(" LEFT JOIN T_JOB ON(T_JOB_PERSON.JOB_ID=T_JOB.ID) ");
//        sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_USER_JOBS_FOR_PAGE"));
        sql.append(" " + searchQuery);
        sql.append(" ORDER BY USERNAME ASC, JOBNAME ASC ");
		
//		System.err.println("job sql:" + sql.toString());
		
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String strSQL = Global.getDialect().getLimitString(sql.toString());
            pstmt = conn.prepareStatement(strSQL);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1,
                    (int) start, 2, (int) (start + count));

            //      pstmt.setLong(1, start);
            //      pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            for (int i = 1; rs.next(); i++) {
                userJob = OrganizationFactory.getInstance().createUserJob();
                userJob.setUser(rs.getInt("ID"));
                userJob.setJob(rs.getInt("JOB_ID"));
                userJob.setUserName(rs.getString("USERNAME"));
                userJob.setJobName(rs.getString("JOBNAME"));

                list.add(userJob);
            }
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_004", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_004", e);
            }
        }

        return list;
    }

    public Collection findUserJobs() throws DAOException {
        try {
            return this.findUserJob(" 0=0 ", 0, 0);
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_007", e);
        }
    }

    public Collection findUserJobs(long start, long count)
        throws DAOException {
        try {
            return this.findUserJob(" 0=0 ", start, count);
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_006", e);
        }
    }

    public Collection findUserJobsForPage(long start, long count)
        throws DAOException {
        try {
            return this.findUserJobsForPage(" 0=0 ", start, count);
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_004", e);
        }
    }

    public Collection findUserJobByUser(int userId) throws DAOException {
        try {
            return this.findUserJob(" T_JOB_PERSON.ID=" + userId, 0, 0);
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_008", e);
        }
    }

    public Collection findUserJobByJob(int jobId) throws DAOException {
        try {
            return this.findUserJob(" T_JOB_PERSON.JOB_ID=" + jobId, 0, 0);
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_009", e);
        }
    }

    public UserJob findUserJobByUserByJob(int userId, int jobId)
        throws DAOException {
        try {
            Collection collection = this.findUserJob(" T_JOB_PERSON.ID=" +
                    userId + " AND T_JOB_PERSON.JOB_ID=" + jobId, 0, 0);

            if(!collection.isEmpty()) return (UserJob) collection.iterator().next();
            else return new DefaultUserJob();
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_010", e);
        }
    }

    public long getUserJobCount(String searchQuery) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = -1;
        String sql = "SELECT COUNT(ID) AS COUNT FROM T_JOB_PERSON WHERE " +
            searchQuery;

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("COUNT");
            }
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_011", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_UserJob_011", e);
            }
        }

        return count;
    }

    public long getUserJobCount() throws DAOException {
        try {
            return this.getUserJobCount(" 0=0 ");
        } catch (Exception e) {
            throw new DAOException("ORG_UserJob_012", e);
        }
    }
}
