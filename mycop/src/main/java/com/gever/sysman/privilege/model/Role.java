//Source file: E:\\lichm\\MyWorks\\GDP\\������\\GDP����\\Sysman\\src\\com\\gever\\sysman\\privilege\\model\\Role.java

package com.gever.sysman.privilege.model;

import java.io.Serializable;
import java.util.Set;

import com.gever.sysman.privilege.util.Extensible;

/**
Ȩ�޹���-��ɫ
 */
public interface Role extends Serializable, Cloneable, Extensible 
{
   
   /**
   ��ȡ��ɫID
   @return
   @roseuid 40B6D9E00178
    */
   public long getId();
   
   /**
   ���ý�ɫID
   @param id
   @roseuid 40B6D9E00186
    */
   public void setId(long id);
   
   /**
   ��ȡ��ɫ��
   @return
   @roseuid 40B6D9E00196
    */
   public String getName();
   
   /**
   ���ý�ɫ��
   @param name
   @roseuid 40B6D9E00197
    */
   public void setName(String name);
   
   /**
   ��ȡ��ɫ����
   @return
   @hibernate.property
   @roseuid 40B6D9E001A6
    */
   public String getDescription();
   
   /**
   ���ý�ɫ����
   @param description
   @roseuid 40B6D9E001B5
    */
   public void setDescription(String description);
   
   /**
   ȡӵ�б���ɫ�������û��б�
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
   ȡ��ɫ��ӵ�еĶ���Դ�Ĳ�����Ϣ�б�
   ע����ɫӵ�еĶ���Դ�Ĳ�������Դ������ӵ�еĲ������ϵ��Ӽ�
   @return java.util.Set
   @roseuid 40BC31950109
    */
   public Set getResources();
   
   /**
   ���ý�ɫ��ӵ�еĶ���Դ�Ĳ�����Ϣ�б�
   @param resources
   @roseuid 40BC3195010A
    */
   public void setResources(Set resources);
   
   public void setRoleType(String roletype);
   public String getRoleType();
}
