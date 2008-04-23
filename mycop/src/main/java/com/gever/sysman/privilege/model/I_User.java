//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\sysman\\privilege\\model\\I_User.java

package com.gever.sysman.privilege.model;

import com.gever.sysman.organization.model.User;
import java.util.Set;

/**
权限管理-用户关系类
 */
public interface I_User extends User 
{
   
   /**
   取用户拥有的所有角色信息列表
   @return java.util.Set
   @roseuid 40B6DD62009C
    */
   public Set getRoles();
   
   /**
   设置用户拥有的所有角色信息列表
   @param roles
   @roseuid 40B6DD7000DA
    */
   public void setRoles(Set roles);
   
   /**
   为用户分配指定角色
   @param role
   @roseuid 40B6DD770148
    */
   public void addRole(Role role);
   
   /**
   取用户所拥有的对资源的操作信息列表
   注：用户拥有的对资源的操作是资源本身所拥有的操作集合的子集；另外，此方法返回的是
   用户权限和用户角色权限两部分的并集。
   @return java.util.Set
   @roseuid 40BC324B0251
    */
   public Set getResources();
   
   /**
   设置用户所拥有的对资源的操作信息列表；
   该操作不影响用户与角色关联所产生的权限信息。
   @param resources
   @roseuid 40BC324B0261
    */
   public void setResources(Set resources);
}
