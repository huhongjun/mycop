//Source file: E:\\lichm\\MyWorks\\GDP\\������\\GDP����\\Sysman\\src\\com\\gever\\sysman\\privilege\\model\\I_User.java

package com.gever.sysman.privilege.model;

import com.gever.sysman.organization.model.User;
import java.util.Set;

/**
Ȩ�޹���-�û���ϵ��
 */
public interface I_User extends User 
{
   
   /**
   ȡ�û�ӵ�е����н�ɫ��Ϣ�б�
   @return java.util.Set
   @roseuid 40B6DD62009C
    */
   public Set getRoles();
   
   /**
   �����û�ӵ�е����н�ɫ��Ϣ�б�
   @param roles
   @roseuid 40B6DD7000DA
    */
   public void setRoles(Set roles);
   
   /**
   Ϊ�û�����ָ����ɫ
   @param role
   @roseuid 40B6DD770148
    */
   public void addRole(Role role);
   
   /**
   ȡ�û���ӵ�еĶ���Դ�Ĳ�����Ϣ�б�
   ע���û�ӵ�еĶ���Դ�Ĳ�������Դ������ӵ�еĲ������ϵ��Ӽ������⣬�˷������ص���
   �û�Ȩ�޺��û���ɫȨ�������ֵĲ�����
   @return java.util.Set
   @roseuid 40BC324B0251
    */
   public Set getResources();
   
   /**
   �����û���ӵ�еĶ���Դ�Ĳ�����Ϣ�б�
   �ò�����Ӱ���û����ɫ������������Ȩ����Ϣ��
   @param resources
   @roseuid 40BC324B0261
    */
   public void setResources(Set resources);
}
