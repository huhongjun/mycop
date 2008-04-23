package com.gever.sysman.organization.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.organization.model.UserJob;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE¡¡OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface UserJobDAO {
    public void createUserJob(UserJob userJob) throws DAOException;

    public void createUserJob(int[] userId, int[] jobId) throws
        DAOException;

    public void updateUserJob(UserJob userJob, int userUpdate, int jobUpdate) throws
        DAOException;

    public void deleteUserJob(int[] userId, int[] jobId) throws DAOException;

    public void deleteUserJobByJob( int jobId) throws DAOException;

    public void deleteUserJobByUser( int userId) throws DAOException;
    
    public void deleteUserJobByUser(String[] userId) throws DAOException;

    public Collection findUserJob(String searchQuery, long start, long count) throws
        DAOException;

    public Collection findUserJobsForPage(String searchQuery, long start, long count) throws
        DAOException;

    public Collection findUserJobs() throws DAOException;

    public Collection findUserJobs(long start, long count) throws DAOException;

    public Collection findUserJobsForPage(long start, long count) throws        DAOException ;

    public Collection findUserJobByUser(int userId) throws DAOException;

    public Collection findUserJobByJob(int jobId) throws DAOException;

    public UserJob findUserJobByUserByJob(int userId, int jobId) throws DAOException;

    public long getUserJobCount(String searchQuery) throws DAOException;

    public long getUserJobCount() throws DAOException;
}