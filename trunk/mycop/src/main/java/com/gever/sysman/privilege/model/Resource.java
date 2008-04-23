package com.gever.sysman.privilege.model;


import java.io.Serializable;

import java.util.Collection;
import java.util.Map;

import com.gever.sysman.privilege.util.Extensible;


/**
资源接口
 */
public interface Resource extends Extensible, Serializable, Cloneable {
    /**
     * 设置资源父ID
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
     * 获取资源父ID
     * @return
     */
    public long getParentID();

    /**
    取资源Id
    @return long
    @roseuid 40BA84AC0222
     */
    public long getId();

    /**
    设置资源Id
    @param id
    @roseuid 40BA84AC0232
     */
    public void setId(long id);

    /**
    取资源名称
    @return java.lang.String
    @roseuid 40BA84AC0242
     */
    public String getName();

    /**
    设置资源名称
    @param name
    @roseuid 40BA84AC0251
     */
    public void setName(String name);

    /**
    取资源描述
    @return java.lang.String
    @roseuid 40BA84AC0253
     */
    public String getDescription();

    /**
    设置资源描述
    @param description
    @roseuid 40BA84AC0261
     */
    public void setDescription(String description);

    /**
    取资源当前的操作信息
    @return java.util.Set
    @roseuid 40BA84AC0271
     */
    public Collection getOperations();

    /**
    设置资源当前的操作信息
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
     * 获取Parent 资源
     * @return
     */
    public Resource getParent();

    /**
     * 设置Parent 资源
     * @param parent
     */
    public void setParent(Resource parent);

    /**
     * 获取sub Resouces
     * @return
     */
    public Collection getChildren();

    /**
     * 设置sub Resources
     * @param children
     */
    public void setChildren(Collection children);

    public String toString();

    public boolean equals(Object obj);

    public int hashCode();

    /**
     * 获取扩展属性
     * @return
     */
    public Map getProperties();

    /**
     * 设置扩展属性
     * @param properties
     */
    public void setProperties(Map properties);

    /**
     * 根据key获取扩展属性
     * @param key
     * @return
     */
    public String getProperty(String key);

    /**
     * 设置扩展属性
     * @param key 属性key
     * @param value 属性value
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
