package com.gever.vo;


/**
 * <p>Title: Tree中的vo对象</p>
 * <p>Description: 匹配XMLLoadTree的数据结构</p>
 * 	
 *  说明：huhj，抽取的XLoadTree文档，可能和本框架中使用的有所不同
 *  1.XLoadTree The xml format
 *		The only valid element in the xml file is the tree item. A tree item can contain zero, one or more tree items.
 *	2.Attributes
 *		There are five valid attributes that you can provide on a tree item.
 *		Name 	Description
 *		text 	Required. The text label for the tree item.
 *		xmlSrc 	Optional. The source for the xml file to load when expanded.
 *		action 	Optional. The action (uri) associated with the tree item.
 *		icon 	Optional. The icon image to use for this item. In case this item is a folder this will be used when the folder is closed.
 *		openIcon 	Optional. The icon image to use for this item when it is opened. This is only used for folder items that are opened/expanded.
 * 
 */

public class BaseTreeVO extends BaseVO implements VOInterface { // ID
    private String src = ""; 		// 源
    private String nodename = ""; 	// text，节点显示文本
    private String target = ""; 	// target，action的frame 目标窗口
    private String nodeid = ""; 	// 用于向Action的toList传递nodeid参数，作为右边list querySQL的where条件
    private String action = ""; 	// action,
    private String memo = "";		// ?
    private String icon_file ="";	// ?
    private String isfolder; 		// isfolder,

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue(String name) {
        if ("nodeid".equalsIgnoreCase(name) == true) {
            return this.nodeid;
        } else if ("nodename".equalsIgnoreCase(name) == true) {
            return this.nodename;
        } else if ("action".equalsIgnoreCase(name) == true) {
            return this.action;
        } else if ("src".equalsIgnoreCase(name) == true) {
            return this.src;
        } else if ("target".equalsIgnoreCase(name) == true) {
            return this.target;
        } else if ("memo".equalsIgnoreCase(name) == true) {
            return this.memo;
        } else if ("isfolder".equalsIgnoreCase(name) == true) {
            return this.isfolder;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
            return this.icon_file;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("nodeid".equalsIgnoreCase(name) == true) {
            this.nodeid = value;
        } else if ("nodename".equalsIgnoreCase(name) == true) {
            this.nodename = value;
        } else if ("action".equalsIgnoreCase(name) == true) {
            this.action = value;
        } else if ("src".equalsIgnoreCase(name) == true) {
            this.src = value;
        } else if ("target".equalsIgnoreCase(name) == true) {
            this.target = value;
        } else if ("memo".equalsIgnoreCase(name) == true) {
            this.memo = value;
        } else if ("isfolder".equalsIgnoreCase(name) == true) {
            this.isfolder = value;
        } else if ("icon_file".equalsIgnoreCase(name) == true) {
            this.icon_file = value;
        } else {
            return;
        }
    }

    public void setValues(String[] values) {

    }

    public String getColType(String name) {
        return "String";
    }

    public String getTableName() {
        return "";
    }

    public String getPkFields() {
        return "nodeid,";
    }

    public String getModifyFields() {
        return "nodename,action,src,target,memo,";
    }

    public String getAllFields() {
        return "nodeid,nodename,action,src,target,memo,";
    }

    public void setOtherProperty(String[] values) {

    }

    public String getIsfolder() {
        return isfolder;
    }

    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getIcon_file() {
        return icon_file;
    }
    public void setIcon_file(String icon_file) {
        this.icon_file = icon_file;
    }

}
