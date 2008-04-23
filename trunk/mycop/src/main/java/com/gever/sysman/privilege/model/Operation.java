package com.gever.sysman.privilege.model;


import java.io.Serializable;

import com.gever.sysman.privilege.util.Extensible;


/**
资源操作信息接口
 */
public interface Operation extends Extensible, Serializable, Cloneable {
    /**
    取操作Id
    @return long
    @roseuid 40BA835E036B
     */
    public long getId();

    /**
    设置操作Id
    @param id
    @roseuid 40BA835E037A
     */
    public void setId(long id);

    /**
    取操作名称
    @return java.lang.String
    @roseuid 40BA835E037C
     */
    public String getName();

    /**
    设置操作名称
    @param name
    @roseuid 40BA835E038A
     */
    public void setName(String name);

    /**
    取操作描述
    @return java.lang.String
    @roseuid 40BA835E0399
     */
    public String getDescription();

    /**
    设置操作描述
    @param description
    @roseuid 40BA835E039A
     */
    public void setDescription(String description);

    /**
    取操作所在的资源
    @return com.gever.sysman.privilege.model.Resource
    @roseuid 40BA83AD0157
     */
    public Resource getResource();

    /**
    设置操作所在的资源
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
