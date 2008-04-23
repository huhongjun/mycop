package com.gever.goa.dailyoffice.smsmgr.vo;


import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: OutBox中的vo对象</p>
 * <p>Description: 是OutBox的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class OutBoxVO extends BaseVO implements VOInterface {
    public OutBoxVO(){
    }
    private String id = ""; // ID
    private String expresslevel = ""; // EXPRESSLEVEL
    private String sn = ""; // SN
    private String username = ""; // USERNAME
    private String mbno = ""; // MBNO
    private String msg = ""; // MSG
    private String senddate = ""; // SENDDATE
    private String sendtime = ""; // SENDTIME
    private String smstype = ""; // SMSTYPE
    private String type = "";
    private String sendingdate = "";
    private static final int id_col = 0; // ID相对应的列数
    private static final int expresslevel_col = 1; // EXPRESSLEVEL相对应的列数
    private static final int sn_col = 2; // SN相对应的列数
    private static final int username_col = 3; // USERNAME相对应的列数
    private static final int mbno_col = 4; // MBNO相对应的列数
    private static final int msg_col = 5; // MSG相对应的列数
    private static final int senddate_col = 6; // SENDDATE相对应的列数
    private static final int sendtime_col = 7; // SENDTIME相对应的列数
    private static final int smstype_col = 8; // SMSTYPE相对应的列数
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getExpresslevel() {
        return this.expresslevel;
    }
    public void setExpresslevel(String expresslevel) {
        this.expresslevel = expresslevel;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMbno() {
        return this.mbno;
    }
    public void setMbno(String mbno) {
        this.mbno = mbno;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getSenddate() {
        return this.senddate;
    }
    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }
    public String getSendtime() {
        return this.sendtime;
    }
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }
    public String getSmstype() {
        return this.smstype;
    }
    public void setSmstype(String smstype) {
        this.smstype = smstype;
    }
    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
             return  this.id;
        } else if ("expresslevel".equalsIgnoreCase(name) == true) {
             return  this.expresslevel;
        } else if ("sn".equalsIgnoreCase(name) == true) {
             return  this.sn;
        } else if ("username".equalsIgnoreCase(name) == true) {
             return  this.username;
        } else if ("mbno".equalsIgnoreCase(name) == true) {
             return  this.mbno;
        } else if ("msg".equalsIgnoreCase(name) == true) {
             return  this.msg;
        } else if ("senddate".equalsIgnoreCase(name) == true) {
             return  this.senddate;
        } else if ("sendtime".equalsIgnoreCase(name) == true) {
             return  this.sendtime;
        } else if ("smstype".equalsIgnoreCase(name) == true) {
             return  this.smstype;
         } else if ("type".equalsIgnoreCase(name) == true) {
             return this.type;
         } else if ("sendingdate".equalsIgnoreCase(name) == true) {
             return this.sendingdate;
         } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
             this.id = value ;
        } else if ("expresslevel".equalsIgnoreCase(name) == true) {
             this.expresslevel = value ;
        } else if ("sn".equalsIgnoreCase(name) == true) {
             this.sn = value ;
        } else if ("username".equalsIgnoreCase(name) == true) {
             this.username = value ;
        } else if ("mbno".equalsIgnoreCase(name) == true) {
             this.mbno = value ;
        } else if ("msg".equalsIgnoreCase(name) == true) {
             this.msg = value ;
        } else if ("senddate".equalsIgnoreCase(name) == true) {
             this.senddate = value ;
        } else if ("sendtime".equalsIgnoreCase(name) == true) {
             this.sendtime = value ;
        } else if ("smstype".equalsIgnoreCase(name) == true) {
             this.smstype = value ;
         } else if ("type".equalsIgnoreCase(name) == true) {
             this.type = value;
         } else if ("sendingdate".equalsIgnoreCase(name) == true) {
             this.sendingdate = value;
         } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else if ("expresslevel".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "OutBox";
    }
    public String getPkFields() {
        return "id,";
    }
    public String getModifyFields() {
        return "expresslevel,sn,username,mbno,msg,senddate,sendtime,smstype,sendingdate,";
    }
    public String getAllFields() {
        return "id,expresslevel,sn,username,mbno,msg,senddate,sendtime,smstype,type,sendingdate,";
    }
    public void setValues(String[] values) {
        this.id = values[id_col];
        this.expresslevel = values[expresslevel_col];
        this.sn = values[sn_col];
        this.username = values[username_col];
        this.mbno = values[mbno_col];
        this.msg = values[msg_col];
        this.senddate = values[senddate_col];
        this.sendtime = values[sendtime_col];
        this.smstype = values[smstype_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSendingdate() {
        return sendingdate;
    }
    public void setSendingdate(String sendingdate) {
        this.sendingdate = sendingdate;
    }
}
