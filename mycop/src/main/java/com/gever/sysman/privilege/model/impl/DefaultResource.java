package com.gever.sysman.privilege.model.impl;

import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


//import net.sf.hibernate.*;
//import net.sf.hibernate.type.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class DefaultResource implements Resource {
    private long id;
    private String name;
    private String description;
    private Resource parent;
    private Resource child;
    private String tables;
    private long parentID;
    private String parentName;
    private String resType;
    private String link;
    private char target;
    private int childNum;
   private int orderID;//libiao add 2004-11-31
    private String isFolder;
    private Collection operations=new ArrayList();
    private Collection allOptions=new ArrayList();
    private Collection children = new ArrayList();
    private Collection privileges = new ArrayList();
    private Map properties = new HashMap();
  private String code;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Resource getParent() {
        return parent;
    }

	public Resource getChild() {
		  return this.child;
	  }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    public Collection getChildren() {
        return children;
    }

    public void setChildren(Collection children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection getPrivileges() {
        return this.privileges;
    }

    public String getTables() {
        return this.tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public void setPrivileges(Collection privileges) {
        this.privileges = privileges;
    }

    public String toString() {
        return "[DefaultResource id=" + id + " name=" + name + "]";
    }

    public boolean equals(Object obj) {
        if ((obj != null) && obj instanceof Resource) {
            if (this.getId() == ((Resource) obj).getId()) {
                return true;
            }
        }

        return false;
    }

    public int hashCode() {
        return new Long(id).hashCode();
    }

    /*
     * @see com.gever.sysman.privilege.model.Resource#getProperties()
     */
    public Map getProperties() {
        return properties;
    }

    /*
     * @see com.gever.sysman.privilege.model.Resource#getProperty(java.lang.String)
     */
    public String getProperty(String key) {
        return (String) properties.get(key);
    }

    /*
     * @see com.gever.sysman.privilege.model.Resource#setProperties(java.util.Map)
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }

    /*
     * @see com.gever.sysman.privilege.model.Resource#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public void addOperation(Operation operation) {
    }

    public void removeOperation(Operation operation) {
    }

    /**
     * @return
     */
    public Collection getOperations() {
        return operations;
    }

    /**
     * @param set
     */
    public void setOperations(Collection set) {
        operations = set;
    }
	/**
	 * @return
	 */
	public long getParentID() {
		return parentID;
	}

	/**
	 * @param l
	 */
	public void setParentID(long pid) {
		parentID = pid;
	}

	/**
	 * @return
	 */
	public String getResType() {
		return resType;
	}

	/**
	 * @param string
	 */
	public void setResType(String restype) {
		resType = restype;
	}


	/**
	 * @return
	 */
	public Collection getAllOptions() {
		return allOptions;
	}

	/**
	 * @param collection
	 */
	public void setAllOptions(Collection collection) {
		allOptions = collection;
	}

	/**
	 * @return
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @return
	 */
	public char getTarget() {
		return target;
	}

	/**
	 * @param string
	 */
	public void setLink(String string) {
		link = string;
	}

	/**
	 * @param c
	 */
	public void setTarget(char c) {
		target = c;
	}

	public void addParent(Resource parent){
		if (this.parent==null){
			this.parent=parent;
			return;
		}
		this.parent.addParent(parent);
	}

	public void addChild(Resource child){
		if (this.child==null){
			this.child=child;
			return;
		}
		this.child.addChild(child);
	}

	/**
	 * @return
	 */
	public int getChildNum() {
		return childNum;
	}

	/**
	 * @param i
	 */
	public void setChildNum(int i) {
		childNum = i;
	}

	/**
	 * @return
	 */
	public String getIsFolder() {
		return isFolder;
	}

	/**
	 * @param string
	 */
	public void setIsFolder(String string) {
		isFolder = string;
	}
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    public void setOrderId(int orderid){
      this.orderID=orderid;
    }//libiao add2004-11-30

    public int getOrderID(){
      return orderID;
    }//libiao add 2004--11-30

}
