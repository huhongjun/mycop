//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\sysman\\privilege\\model\\Role.java

package com.gever.sysman.privilege.model;

import java.io.Serializable;
import java.util.Set;

import com.gever.sysman.privilege.util.Extensible;

/**
权限管理-角色
 */
public interface Role extends Serializable, Cloneable, Extensible 
{
   
   /**
   获取角色ID
   @return
   @roseuid 40B6D9E00178
    */
   public long getId();
   
   /**
   设置角色ID
   @param id
   @roseuid 40B6D9E00186
    */
   public void setId(long id);
   
   /**
   获取角色名
   @return
   @roseuid 40B6D9E00196
    */
   public String getName();
   
   /**
   设置角色名
   @param name
   @roseuid 40B6D9E00197
    */
   public void setName(String name);
   
   /**
   获取角色描述
   @return
   @hibernate.property
   @roseuid 40B6D9E001A6
    */
   public String getDescription();
   
   /**
   设置角色描述
   @param description
   @roseuid 40B6D9E001B5
    */
   public void setDescription(String description);
   
   /**
   取拥有本角色的所有用户列表
   @return java.util.Set
   @roseuid 40B6DB94001F
    */
   public Set getUsers();
   
   /**
   @param users
   @roseuid 40B6DBE7035B
    */
   public void setUsers(Set users);
   
   /**
   @param user
   @roseuid 40B6DBEB03D8
    */
   public void addUser(I_User user);
   
   /**
   @param user
   @roseuid 40B6DBEE033C
    */
   public void removeUser(I_User user);
   
   /**
   取角色所拥有的对资源的操作信息列表
   注：角色拥有的对资源的操作是资源本身所拥有的操作集合的子集
   @return java.util.Set
   @roseuid 40BC31950109
    */
   public Set getResources();
   
   /**
   设置角色所拥有的对资源的操作信息列表
   @param resources
   @roseuid 40BC3195010A
    */
   public void setResources(Set resources);
   
   public void setRoleType(String roletype);
   public String getRoleType();
}
