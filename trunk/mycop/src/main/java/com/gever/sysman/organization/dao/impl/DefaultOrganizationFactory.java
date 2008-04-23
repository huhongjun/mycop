package com.gever.sysman.organization.dao.impl;

import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.organization.model.impl.*;

public class DefaultOrganizationFactory
    extends OrganizationFactory {

    public Department createDepartment() {
        return new DefaultDepartment();
    }

    public Job createJob() {
        return new DefaultJob();
    }

    public User createUser() {
        return new DefaultUser();
    }

    public UserDepartment createUserDepartment(){
        return new DefaultUserDepartment();
    }

    public UserJob createUserJob(){
        return new DefaultUserJob();
    }

    public DepartmentDAO getDepartmentDAO() {
        return new DefaultDepartmentDAO();
    }

    public JobDAO getJobDAO() {
        return new DefaultJobDAO();
    }

    public UserDAO getUserDAO() {
        return new DefaultUserDAO();
    }

    public UserDepartmentDAO getUserDepartmentDAO(){
        return new DefaultUserDepartmentDAO();
    }

    public UserJobDAO getUserJobDAO(){
        return new DefaultUserJobDAO();
    }

    /* £¨·Ç Javadoc£©
     * @see com.gever.sysman.organization.dao.OrganizationFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new DefaultSQLFactory();
    }
    public  DepOrderDAO getDepOrderDAO(){
      return new DepOrderDAOIMP();

    }

}
