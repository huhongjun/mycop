package com.gever.sysman.privilege.model;


import java.io.Serializable;

import com.gever.sysman.privilege.util.Extensible;


/**
��Դ������Ϣ�ӿ�
 */
public interface Operation extends Extensible, Serializable, Cloneable {
    /**
    ȡ����Id
    @return long
    @roseuid 40BA835E036B
     */
    public long getId();

    /**
    ���ò���Id
    @param id
    @roseuid 40BA835E037A
     */
    public void setId(long id);

    /**
    ȡ��������
    @return java.lang.String
    @roseuid 40BA835E037C
     */
    public String getName();

    /**
    ���ò�������
    @param name
    @roseuid 40BA835E038A
     */
    public void setName(String name);

    /**
    ȡ��������
    @return java.lang.String
    @roseuid 40BA835E0399
     */
    public String getDescription();

    /**
    ���ò�������
    @param description
    @roseuid 40BA835E039A
     */
    public void setDescription(String description);

    /**
    ȡ�������ڵ���Դ
    @return com.gever.sysman.privilege.model.Resource
    @roseuid 40BA83AD0157
     */
    public Resource getResource();

    /**
    ���ò������ڵ���Դ
    @param resource
    @roseuid 40BA83C4003E
     */
    public void setResource(Resource resource);

    public long getResourceID();

    public void setResourceID(long resourceid);

    public boolean isOpt();

    /**
     * @param b
     */
    public void setOpt(boolean b);

	public boolean isRolePerm();

	/**
	 * @param b
	 */
	public void setRolePerm(boolean b);
  public String getCode();
  public void setCode(String code);

  public String getResourceName();
  public void setResourceName(String resourceName);
}
