package com.gever.sysman.log.vo;


import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: Log中的vo对象</p>
 * <p>Description: 是tblsyslog的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:2004/06/07
 */
public class LogVO extends BaseVO implements VOInterface {
    public LogVO(){
    }
    private String id = ""; // ID
    private String otime = ""; // OTIME
    private String username = ""; // USERNAME
    private String module = ""; // MODULE
    private String ipaddress = ""; // IPADDRESS
    private String action = ""; // ACTION
    private String filterlevel = ""; // FILTERLEVEL
    private String type = ""; // TYPE
    private String memo = ""; // MEMO
    private String endDate = ""; // 不在数据表中的属性
    private String beginDate = ""; // 不在数据表中的属性
    private static final int id_col = 0; // ID相对应的列数
    private static final int otime_col = 1; // OTIME相对应的列数
    private static final int username_col = 2; // USERNAME相对应的列数
    private static final int module_col = 3; // MODULE相对应的列数
    private static final int ipaddress_col = 4; // IPADDRESS相对应的列数
    private static final int action_col = 5; // ACTION相对应的列数
    private static final int filterlevel_col = 6; // FILTERLEVEL相对应的列数
    private static final int type_col = 7; // TYPE相对应的列数
    private static final int memo_col = 8; // MEMO相对应的列数
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOtime() {
        return this.otime;
    }
    public void setOtime(String otime) {
        this.otime = otime;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getModule() {
        return this.module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public String getIpaddress() {
        return this.ipaddress;
    }
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public String getAction() {
        return this.action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getFilterlevel() {
        return this.filterlevel;
    }
    public void setFilterlevel(String filterlevel) {
        this.filterlevel = filterlevel;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getBeginDate() {
        return this.beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
             return  this.id;
        } else if ("otime".equalsIgnoreCase(name) == true) {
             return  this.otime;
        } else if ("username".equalsIgnoreCase(name) == true) {
             return  this.username;
        } else if ("module".equalsIgnoreCase(name) == true) {
             return  this.module;
        } else if ("ipaddress".equalsIgnoreCase(name) == true) {
             return  this.ipaddress;
        } else if ("action".equalsIgnoreCase(name) == true) {
             return  this.action;
        } else if ("filterlevel".equalsIgnoreCase(name) == true) {
             return  this.filterlevel;
        } else if ("type".equalsIgnoreCase(name) == true) {
             return  this.type;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             return  this.memo;
        } else if ("endDate".equalsIgnoreCase(name) == true) {
             return  this.endDate;
        } else if ("beginDate".equalsIgnoreCase(name) == true) {
             return  this.beginDate;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
             this.id = value ;
        } else if ("otime".equalsIgnoreCase(name) == true) {
             this.otime = value ;
        } else if ("username".equalsIgnoreCase(name) == true) {
             this.username = value ;
        } else if ("module".equalsIgnoreCase(name) == true) {
             this.module = value ;
        } else if ("ipaddress".equalsIgnoreCase(name) == true) {
             this.ipaddress = value ;
        } else if ("action".equalsIgnoreCase(name) == true) {
             this.action = value ;
        } else if ("filterlevel".equalsIgnoreCase(name) == true) {
             this.filterlevel = value ;
        } else if ("type".equalsIgnoreCase(name) == true) {
             this.type = value ;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             this.memo = value ;
        } else if ("endDate".equalsIgnoreCase(name) == true) {
             this.endDate = value ;
        } else if ("beginDate".equalsIgnoreCase(name) == true) {
             this.beginDate = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else if ("otime".equalsIgnoreCase(name) == true) {
             colType = "String";
        } else if ("filterlevel".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("type".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "T_SYSTEM_LOG";
    }
    public String getPkFields() {
        return "id,";
    }
    public String getModifyFields() {
        return "otime,username,module,ipaddress,action,filterlevel,type,memo,";
    }
    public String getAllFields() {
        return "id,otime,username,module,ipaddress,action,filterlevel,type,memo,";
    }
    public void setValues(String[] values) {
        this.id = values[id_col];
        this.otime = values[otime_col];
        this.username = values[username_col];
        this.module = values[module_col];
        this.ipaddress = values[ipaddress_col];
        this.action = values[action_col];
        this.filterlevel = values[filterlevel_col];
        this.type = values[type_col];
        this.memo = values[memo_col];
    }
    public void setOtherProperty(String[] values) {
        if (! (this.otime == null || "".equals(this.otime) ||
               "null".equals(this.otime))) {
        if (this.otime.length()>19)
            this.otime = this.otime.substring(0,19);
        }
    }
}
