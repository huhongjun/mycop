package com.gever.sysman.privilege.model;


import java.io.Serializable;

import java.util.Collection;
import java.util.Map;

import com.gever.sysman.privilege.util.Extensible;


/**
��Դ�ӿ�
 */
public interface Resource extends Extensible, Serializable, Cloneable {
    /**
     * ������Դ��ID
     * @param id
     */
    public void setParentID(long id);

    public void addParent(Resource parent);

	public String getIsFolder();

	/**
	 * @param string
	 */
	public void setIsFolder(String f);

    /**
     * ��ȡ��Դ��ID
     * @return
     */
    public long getParentID();

    /**
    ȡ��ԴId
    @return long
    @roseuid 40BA84AC0222
     */
    public long getId();

    /**
    ������ԴId
    @param id
    @roseuid 40BA84AC0232
     */
    public void setId(long id);

    /**
    ȡ��Դ����
    @return java.lang.String
    @roseuid 40BA84AC0242
     */
    public String getName();

    /**
    ������Դ����
    @param name
    @roseuid 40BA84AC0251
     */
    public void setName(String name);

    /**
    ȡ��Դ����
    @return java.lang.String
    @roseuid 40BA84AC0253
     */
    public String getDescription();

    /**
    ������Դ����
    @param description
    @roseuid 40BA84AC0261
     */
    public void setDescription(String description);

    /**
    ȡ��Դ��ǰ�Ĳ�����Ϣ
    @return java.util.Set
    @roseuid 40BA84AC0271
     */
    public Collection getOperations();

    /**
    ������Դ��ǰ�Ĳ�����Ϣ
    @param operations
    @roseuid 40BA84AC0280
     */
    public void setOperations(Collection operations);

    /**
     * add Operation
     * @param operation
     */
    public void addOperation(Operation operation);

	public void addChild(Resource child);
    /**
     * remove Operation
     * @param operation
     */
    public void removeOperation(Operation operation);

    /**
     * ��ȡParent ��Դ
     * @return
     */
    public Resource getParent();

    /**
     * ����Parent ��Դ
     * @param parent
     */
    public void setParent(Resource parent);

    /**
     * ��ȡsub Resouces
     * @return
     */
    public Collection getChildren();

    /**
     * ����sub Resources
     * @param children
     */
    public void setChildren(Collection children);

    public String toString();

    public boolean equals(Object obj);

    public int hashCode();

    /**
     * ��ȡ��չ����
     * @return
     */
    public Map getProperties();

    /**
     * ������չ����
     * @param properties
     */
    public void setProperties(Map properties);

    /**
     * ����key��ȡ��չ����
     * @param key
     * @return
     */
    public String getProperty(String key);

    /**
     * ������չ����
     * @param key ����key
     * @param value ����value
     */
    public void setProperty(String key, String value);

    public String getResType();

	/**
	 * @return
	 */
	public String getLink() ;

	/**
	 * @return
	 */
	public char getTarget();

	/**
	 * @param string
	 */
	public void setLink(String string);

	/**
	 * @param c
	 */
	public void setTarget(char c);

    public void setChildNum(int cn);
    public int getChildNum();

    public void setOrderId(int orderid);  //libiao add2004-11-30
    public int getOrderID(); //libiao add2004-11-30
    /**
     * @param string
     */
    public void setResType(String string);

    public Collection getAllOptions();

    public void setAllOptions(Collection options);
  public String getCode();
  public void setCode(String code);
  public String getParentName();
  public void setParentName(String parentName);


}
