package com.gever.sysman.organization.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.*;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.organization.model.impl.DefaultJob;
import com.gever.sysman.util.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultJobDAO implements JobDAO {
	protected SQLFactory sqlFactory = OrganizationFactory.getInstance().getSQLFactory();
        //#################libiao add 2004/12/24
        public void deleteJobDepartment(String departmentId) throws DAOException{
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM T_JOB WHERE DEPARTMENT_ID=" + departmentId;
        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_005", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_005", e);
            }
        }

        }

    public int getJobId(String departmentId) throws DAOException{
        ConnectionProvider cp = null;
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       int jobid = -1;
       String sql = "SELECT ID FROM T_JOB WHERE DEPARTMENT_ID=" +departmentId;

       try {
           cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
           conn = cp.getConnection();
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               jobid = rs.getInt("ID");
           }
       } catch (Exception e) {
           throw new DAOException("ORG_Job_017", e);
       } finally {
           try {
               ConnectionUtil.close(conn, pstmt, rs);
           } catch (Exception e) {
               throw new DAOException("ORG_Job_017", e);
           }
       }

       return jobid;

        }
        //#####################libiao add end#################3

    public void createJob(Job job) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO T_JOB(ID, NAME, DESCRIPTION,DEPARTMENT_ID) VALUES (?,?,?,?)";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            int i = 1;
            pstmt.setLong(i++, SequenceMan.nextID());
            pstmt.setString(i++, job.getName());
            pstmt.setString(i++, job.getDescription());
            pstmt.setInt(i++, job.getDepartment());

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_001", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_001", e);
            }
        }
    }

    public void updateJob(Job job) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE T_JOB SET  NAME=?, DESCRIPTION=?, DEPARTMENT_ID=? WHERE ID=?";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            int i = 1;
            pstmt.setString(i++, job.getName());
            pstmt.setString(i++, job.getDescription());
            pstmt.setInt(i++, job.getDepartment());
            pstmt.setInt(i++, job.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_002", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_002", e);
            }
        }
    }

    public void setDepartmentJob(String departmentId, String[] jobId)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        Statement stmt = null;
        String sql = "";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            stmt = conn.createStatement();

            sql = "UPDATE T_JOB SET DEPARTMENT_ID=0 WHERE DEPARTMENT_ID=" +
                departmentId;
            stmt.addBatch(sql);

            for (int i = 0; i < jobId.length; i++) {
                sql = "UPDATE T_JOB SET DEPARTMENT_ID=" + departmentId +
                    " WHERE ID=" + jobId[i];
                stmt.addBatch(sql);
            }

            stmt.executeBatch();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_003", e);
        } finally {
            try {
                ConnectionUtil.close(conn, stmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_003", e);
            }
        }
    }

    public void deleteJob(String[] jobId) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM T_JOB WHERE ID=" + jobId[0];

        for (int i = 1; i < jobId.length; i++) {
            sql += (" OR ID=" + jobId[i]);
        }

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_004", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_004", e);
            }
        }
    }

    public void deleteJobDepartment(String[] jobId, String departmentId)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM T_JOB WHERE DEPARTMENT_ID=" + departmentId +
            " AND (ID=" + jobId[0];

        for (int i = 1; i < jobId.length; i++) {
            sql += (" OR ID=" + jobId[i]);
        }

        sql += " )";

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();
            pstmt = conn.prepareStatement(sql);

//            System.out.println(sql);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("ORG_Job_005", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_005", e);
            }
        }
    }

    public Collection findJob(String searchQuery, long start, long count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        Job job = null;

        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT ");
//        sql.append(" T_JOB.ID AS ID, ");
//        sql.append(" T_JOB.NAME AS NAME, ");
//        sql.append(" T_JOB.DESCRIPTION AS DESCRIPTION, ");
//        sql.append(" T_JOB.DEPARTMENT_ID AS DEPARTMENT_ID, ");
//        sql.append(" T_DEPARTMENT.NAME AS DEPARTMENT_NAME ");
//        sql.append(" FROM ");
//        sql.append(" T_JOB ");
//        sql.append(
//            " LEFT JOIN T_DEPARTMENT ON(T_JOB.DEPARTMENT_ID=T_DEPARTMENT.ID) ");
//        sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_JOB"));
		sql.append(" ");
        sql.append(searchQuery);
        sql.append(" ORDER BY T_JOB.NAME ASC, T_DEPARTMENT.NAME ASC ");

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

                job = OrganizationFactory.getInstance().createJob();
                job.setId(rs.getInt("ID"));
                job.setName(rs.getString("NAME"));
                job.setDescription(rs.getString("DESCRIPTION"));
                job.setDepartment(rs.getInt("DEPARTMENT_ID"));
                job.setDepartmentName(rs.getString("DEPARTMENT_NAME"));

                list.add(job);
            }
        } catch (Exception e) {
            throw new DAOException("ORG_Job_006", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_006", e);
            }
        }

        return list;
    }

    //==========================================================
    //胡勇添加，增加JSP视图列表排序功能
    private String[] orderby;
    public void setOrderby(String[] s){ this.orderby = s; }
    //==========================================================
    public Collection findJobsForPage(String searchQuery, long start, long count)
        throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        Job job = null;

        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT ");
//        sql.append(" T_JOB.ID AS ID, ");
//        sql.append(" T_JOB.NAME AS NAME, ");
//        sql.append(" T_JOB.DESCRIPTION AS DESCRIPTION, ");
//        sql.append(" T_JOB.DEPARTMENT_ID AS DEPARTMENT_ID, ");
//        sql.append(" T_DEPARTMENT.NAME AS DEPARTMENT_NAME ");
//        sql.append(" FROM ");
//        sql.append(" T_JOB ");
//        sql.append(
//            " LEFT JOIN T_DEPARTMENT ON(T_JOB.DEPARTMENT_ID=T_DEPARTMENT.ID) ");
//        sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_JOBS_FOR_PAGE"));
		sql.append(" ");
        sql.append(searchQuery);
        sql.append(" ORDER BY T_JOB.NAME ASC, T_DEPARTMENT.NAME ASC ");

        try {
            cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            String strSQL = Global.getDialect().getLimitString(sql.toString());
            //==========================================================
            //胡勇添加，增加JSP视图列表排序功能
            strSQL = OrderList.getInstance().formatSQL(orderby,strSQL);
            //==========================================================
            pstmt = conn.prepareStatement(strSQL);
            pstmt = Global.getDialect().setStatementPageValue(pstmt, 1,
                    (int) start, 2, (int) (start + count));

            //      pstmt.setLong(1, start);
            //      pstmt.setLong(2, start + count);
            rs = pstmt.executeQuery();

            for (int i = 1; rs.next(); i++) {
                job = OrganizationFactory.getInstance().createJob();
                job.setId(rs.getInt("ID"));
                job.setName(rs.getString("NAME"));
                job.setDescription(rs.getString("DESCRIPTION"));
                job.setDepartment(rs.getInt("DEPARTMENT_ID"));
                job.setDepartmentName(rs.getString("DEPARTMENT_NAME"));

                list.add(job);
            }
        } catch (Exception e) {
            throw new DAOException("ORG_Job_020", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_020", e);
            }
        }

        return list;
    }

    public Job findJobByID(long id) throws DAOException {
        try {
            Collection collection = this.findJob(" T_JOB.ID=" + id, 0, 0);

			if(!collection.isEmpty()) return (Job) collection.iterator().next();
			return  new DefaultJob();
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_007", e);
        }
    }

    public Collection findJobByName(String name) throws DAOException {
        try {
            return this.findJob(" T_JOB.NAME='" + name + "'", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_008", e);
        }
    }

    public Collection getJobs(long start, long count) throws DAOException {
        try {
            return this.findJob(" 0=0 ", start, count);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_009", e);
        }
    }

    public Collection getJobs() throws DAOException {
        try {
            return this.findJob(" 0=0 ", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_010", e);
        }
    }

    public Job findJobDeparment(int id, int department)
        throws DAOException {
        try {
            Collection collection = this.findJob(" T_JOB.ID=" + id +
                    " AND T_JOB.DEPARTMENT_ID=" + department, 0, 0);

            if(!collection.isEmpty()) return (Job) collection.iterator().next();
            else return new DefaultJob();
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_011", e);
        }
    }

    public Collection getJobDepartments() throws DAOException {
        try {
            return this.findJob(" ((T_JOB.DEPARTMENT_ID IS NOT NULL) OR (T_JOB.DEPARTMENT_ID <> 0)) ",
                0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_012", e);
        }
    }

    public Collection getJobDepartments(long start, long count)
        throws DAOException {
        try {
            return this.findJob(" ((T_JOB.DEPARTMENT_ID IS NOT NULL) OR (T_JOB.DEPARTMENT_ID <> 0)) ",
                start, count);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_013", e);
        }
    }

    public Collection getUsers(Job job, long start, long count)
        throws DAOException {
        try {
            return OrganizationFactory.getInstance().getUserDAO().findUser(" ID IN (SELECT ID FROM T_JOB_PERSON WHERE JOB_ID=" +
                job.getId() + ")", start, count);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_014", e);
        }
    }

    public Collection getUsers(Job job) throws DAOException {
        try {
            return OrganizationFactory.getInstance().getUserDAO().findUser(" ID IN (SELECT ID FROM T_JOB_PERSON WHERE JOB_ID=" +
                job.getId() + ")", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_015", e);
        }
    }

    public Collection getUsersByDept(Job job) throws DAOException {
        try {
            return OrganizationFactory.getInstance().getUserDAO().findUser(" ID IN (SELECT ID FROM T_JOB_PERSON WHERE JOB_ID=" +
                job.getId() +
                ") and ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID=" +
                job.getDepartment() + ")", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_015", e);
        }
    }

    public Collection getUnDistributeUsers(Job job) throws DAOException {
        try {
            return OrganizationFactory.getInstance().getUserDAO().findUser(" ID NOT IN (SELECT ID FROM T_JOB_PERSON WHERE JOB_ID=" +
                job.getId() + ")", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_016", e);
        }
    }

    public Collection getUnDistributeUsersByDept(Job job)
        throws DAOException {
        try {
            return OrganizationFactory.getInstance().getUserDAO().findUser(" ID NOT IN (SELECT ID FROM T_JOB_PERSON WHERE JOB_ID=" +
                job.getId() +
                ") AND ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID=" +
                job.getDepartment() + ")", 0, 0);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_020", e);
        }
    }

    private int getJobCount(String searchQuery) throws DAOException {
        ConnectionProvider cp = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = -1;
        String sql = "SELECT COUNT(ID) AS COUNT FROM T_JOB WHERE " +
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
            throw new DAOException("ORG_Job_017", e);
        } finally {
            try {
                ConnectionUtil.close(conn, pstmt, rs);
            } catch (Exception e) {
                throw new DAOException("ORG_Job_017", e);
            }
        }

        return count;
    }

    public int getJobCount() throws DAOException {
        try {
            return this.getJobCount(" 0=0 ");
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_018", e);
        }
    }

    public void removeUser(Job job, User user) throws DAOException {
        try {
            int[] userId = new int[1];
            userId[0] = user.getId();

            int[] jobId = new int[1];
            userId[0] = job.getId();
            OrganizationFactory.getInstance().getUserJobDAO().deleteUserJob(userId,
                jobId);
        } catch (DAOException e) {
            throw new DAOException("ORG_Job_019", e);
        }
    }

    public List findJobsByDepartmentID(long departmentID)
        throws DAOException {
        return null;
    }
}
