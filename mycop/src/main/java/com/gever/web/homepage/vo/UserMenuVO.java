package com.gever.web.homepage.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 菜单中的vo对象</p>
 * <p>Description: 是t_user_menu的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */

public class UserMenuVO extends BaseVO implements VOInterface {
    public UserMenuVO(){
    }
    private String empid = ""; // EMPID
    private String nodeid = ""; // NODEID
    private String nodename = ""; // NODENAME
    private String parentid = ""; // PARENTID
    private String link = ""; // LINK
    private String isfolder = ""; // ISFOLDER
    private String ishided = ""; // ISHIDED
    private String orderid = ""; // ORDERID
    private String ismenu = ""; // ISMENU
    private String flag = ""; // FLAG
    private String memo = ""; // MEMO
    private String pathname = "";
    private String linkmode= "";
    private String nextnodenum = "0";
    private String level="0";
    private String nextlevel="0";

    private String sumnum="0";
    private String hidenum="0";
    private static final int empid_col = 0; // EMPID相对应的列数
    private static final int nodeid_col = 1; // NODEID相对应的列数
    private static final int nodename_col = 2; // NODENAME相对应的列数
    private static final int parentid_col = 3; // PARENTID相对应的列数
    private static final int link_col = 4; // LINK相对应的列数
    private static final int isfolder_col = 5; // ISFOLDER相对应的列数
    private static final int ishided_col = 6; // ISHIDED相对应的列数
    private static final int orderid_col = 7; // ORDERID相对应的列数
    private static final int ismenu_col = 8; // ISMENU相对应的列数
    private static final int flag_col = 9; // FLAG相对应的列数
    private static final int memo_col = 10; // MEMO相对应的列数
    private static final int linkmode_col = 10; // MEMO相对应的列数
    private static final int nextnodenum_col = 11;
    private static final int level_col = 12;
    private static final int nextlevel_col = 12;
    private static final int hidelevel_col = 12;
    public String getEmpid() {
        return this.empid;
    }
    public void setEmpid(String empid) {
        this.empid = empid;
    }
    public String getNodeid() {
        return this.nodeid;
    }
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
    public String getNodename() {
        return this.nodename;
    }
    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
    public String getParentid() {
        return this.parentid;
    }
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getIsfolder() {
        return this.isfolder;
    }
    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }
    public String getIshided() {
        return this.ishided;
    }
    public void setIshided(String ishided) {
        this.ishided = ishided;
    }
    public String getOrderid() {
        return this.orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public String getIsmenu() {
        return this.ismenu;
    }
    public void setIsmenu(String ismenu) {
        this.ismenu = ismenu;
    }
    public String getFlag() {
        return this.flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getValue(String name) {
        if ("empid".equalsIgnoreCase(name) == true) {
             return  this.empid;
        } else if ("nodeid".equalsIgnoreCase(name) == true) {
             return  this.nodeid;
        } else if ("nodename".equalsIgnoreCase(name) == true) {
             return  this.nodename;
        } else if ("parentid".equalsIgnoreCase(name) == true) {
             return  this.parentid;
        } else if ("link".equalsIgnoreCase(name) == true) {
             return  this.link;
        } else if ("isfolder".equalsIgnoreCase(name) == true) {
             return  this.isfolder;
        } else if ("ishided".equalsIgnoreCase(name) == true) {
             return  this.ishided;
        } else if ("orderid".equalsIgnoreCase(name) == true) {
             return  this.orderid;
        } else if ("ismenu".equalsIgnoreCase(name) == true) {
             return  this.ismenu;
        } else if ("flag".equalsIgnoreCase(name) == true) {
             return  this.flag;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             return  this.memo;
         } else if ("linkmode".equalsIgnoreCase(name) == true) {
             return this.linkmode;
         } else if ("nextnodenum".equalsIgnoreCase(name) == true) {
             return this.nextnodenum;
         } else if ("level".equalsIgnoreCase(name) == true) {
             return this.level;
         }  else if ("nextlevel".equalsIgnoreCase(name) == true) {
             return this.nextlevel;
         }
else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("empid".equalsIgnoreCase(name) == true) {
             this.empid = value ;
        } else if ("nodeid".equalsIgnoreCase(name) == true) {
             this.nodeid = value ;
        } else if ("nodename".equalsIgnoreCase(name) == true) {
             this.nodename = value ;
        } else if ("parentid".equalsIgnoreCase(name) == true) {
             this.parentid = value ;
        } else if ("link".equalsIgnoreCase(name) == true) {
             this.link = value ;
        } else if ("isfolder".equalsIgnoreCase(name) == true) {
             this.isfolder = value ;
        } else if ("ishided".equalsIgnoreCase(name) == true) {
             this.ishided = value ;
        } else if ("orderid".equalsIgnoreCase(name) == true) {
             this.orderid = value ;
        } else if ("ismenu".equalsIgnoreCase(name) == true) {
             this.ismenu = value ;
        } else if ("flag".equalsIgnoreCase(name) == true) {
             this.flag = value ;
        } else if ("memo".equalsIgnoreCase(name) == true) {
            this.memo = value;
        } else if ("linkmode".equalsIgnoreCase(name) == true) {
            this.linkmode = value;
        } else if ("nextnodenum".equalsIgnoreCase(name) == true) {
            this.nextnodenum = value;
        } else if ("level".equalsIgnoreCase(name) == true) {
            this.level = value;
        } else if ("nextlevel".equalsIgnoreCase(name) == true) {
            this.nextlevel = value;
        } else {
            return;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("empid".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else if ("orderid".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "T_USER_MENU";
    }
    public String getPkFields() {
        return "empid,nodeid,";
    }
    public String getModifyFields() {
        return "nodename,parentid,link,linkmode,isfolder,ishided,orderid,ismenu,flag,memo,";
    }
    public String getAllFields() {
        return "empid,nodeid,nodename,parentid,link,linkmode,isfolder,ishided,orderid,ismenu,flag,memo,nextnodenum";
    }
    public void setValues(String[] values) {
        this.empid = values[empid_col];
        this.nodeid = values[nodeid_col];
        this.nodename = values[nodename_col];
        this.parentid = values[parentid_col];
        this.link = values[link_col];
        this.isfolder = values[isfolder_col];
        this.ishided = values[ishided_col];
        this.orderid = values[orderid_col];
        this.ismenu = values[ismenu_col];
        this.flag = values[flag_col];
        this.memo = values[memo_col];
        this.linkmode = values[linkmode_col];
        this.nextnodenum = values[nextnodenum_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getPathname() {
        return pathname;
    }
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
    public String getLinkmode() {
        return linkmode;
    }
    public void setLinkmode(String linkmode) {
        this.linkmode = linkmode;
    }
    public String getNextnodenum() {
        return nextnodenum;
    }
    public void setNextNodeNum(String nextnodenum) {
        this.nextnodenum = nextnodenum;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getNextlevel() {
        return nextlevel;
    }
    public void setNextlevel(String nextlevel) {
        this.nextlevel = nextlevel;
    }
    public String getSumnum() {
        return sumnum;
    }
    public void setSumnum(String sumnum) {
        this.sumnum = sumnum;
    }
    public String getHidenum() {
        return hidenum;
    }
    public void setHidenum(String hidenum) {
        this.hidenum = hidenum;
    }
}
