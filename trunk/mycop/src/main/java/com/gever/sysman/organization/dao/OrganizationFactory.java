package com.gever.sysman.organization.dao;

import com.gever.config.Environment;
import com.gever.sysman.organization.model.*;

/**
组织关系管理工厂类
 */
public abstract class OrganizationFactory
{
   /**
   @directed directed
   @supplierRole instance
    */
   private static OrganizationFactory factory = null;

   /**
   获取OrganizationFactory具体实例.
   @return
   @roseuid 40B6A8890271
    */
   public static synchronized OrganizationFactory getInstance(){
       String organizationFactoryClassName = null;

       if (factory == null) {
           try {
               organizationFactoryClassName = Environment.getProperty(
                       "Sysman.OrganizationFactoryClassName");

               if ((organizationFactoryClassName == null) ||
                       "".equals(organizationFactoryClassName)) {
                   organizationFactoryClassName = "com.gever.sysman.organization.dao.impl.DefaultOrganizationFactory";
               }

               Class c = Class.forName(organizationFactoryClassName);
               factory = (OrganizationFactory) c.newInstance();
           } catch (Exception e) {
               System.err.println("Failed to load PrivilegeFactory class " +
                   organizationFactoryClassName);
               e.printStackTrace();

               return null;
           }
       }

       return factory;
    }

   public abstract Department createDepartment();

   public abstract Job createJob();

   public abstract User createUser();

   public abstract UserDepartment createUserDepartment();

   public abstract UserJob createUserJob();

   /**
   获取DepartmentDAO,用于管理Department.
   @return
   @roseuid 40B6A88902CE
    */
   public abstract DepartmentDAO getDepartmentDAO();

   /**
      获取JobDAO,用于管理Job.
      @return
      @roseuid 40B6A889031C
    */
   public abstract JobDAO getJobDAO();

   /**
       获   获取UserDAO,用户管理User.
   @return
   @roseuid 40B6A88902EE
    */
   public abstract UserDAO getUserDAO();

   public abstract UserDepartmentDAO getUserDepartmentDAO();

   public abstract UserJobDAO getUserJobDAO();

   public abstract SQLFactory getSQLFactory();
   public abstract DepOrderDAO getDepOrderDAO();
}
