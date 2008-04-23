/*
 * �������� Ȩ��ӳ��ӿ�
 * �������� 2004-11-25 10:23:33
 */
package com.gever.sysman.privilege.model;


import java.io.Serializable;

import com.gever.sysman.privilege.util.Extensible;


/**
 * @author Hu.Walker
 */
public interface PermissionMap extends Extensible, Serializable, Cloneable {
    /**
     * ȡ�����ݿ�Ψһ��ʶ
     * @return
     */
    public long getId();

    /**
     * ����ID
     * @return
     */
    public void setId(long id);
    /**
     * ����Action��ӳ��·��
     * @param actionPath
     */
    public void setActionPath(String actionPath);

    /**
     * ȡ��Action��ӳ��·��
     * @return
     */
    public String getActionPath();

    /**
     * ����Action��ӵ�еķ�������
     * @param methodName
     */
    public void setMethodName(String methodName);

    /**
     * ȡ��Action��ӵ�еķ�������
     * @return
     */
    public String getMethodName();

    /**
     * ������Դ����
     * @param resCode
     */
    public void setResCode(String resCode);

    /**
     * ȡ����Դ����
     * @return
     */
    public String getResCode();

    /**
     * ���ø���Դ��ӵ�еĲ�������
     * @param resOpCode
     */
    public void setResOpCode(String resOpCode);

    /**
     * ȡ�ø���Դ��ӵ�еĲ�������
     * @return
     */
    public String getResOpCode();
}
