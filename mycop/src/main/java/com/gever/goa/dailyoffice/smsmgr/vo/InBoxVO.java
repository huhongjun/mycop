package com.gever.goa.dailyoffice.smsmgr.vo;



import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: SmsInBox中的vo对象</p>
 * <p>Description: 是INBOX的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class InBoxVO extends BaseVO implements VOInterface {
    public InBoxVO(){
    }
    private String id = ""; // ID
    private String mbno = ""; // MBNO
    private String msg = ""; // MSG
    private String arrivedate = ""; // ARRIVEDATE
    private String arrivetime = ""; // ARRIVETIME
    private String commport = ""; // COMMPORT
    private String username;   //       相对应的人员
    private static final int id_col = 0; // ID相对应的列数
    private static final int mbno_col = 1; // MBNO相对应的列数
    private static final int msg_col = 2; // MSG相对应的列数
    private static final int arrivedate_col = 3; // ARRIVEDATE相对应的列数
    private static final int arrivetime_col = 4; // ARRIVETIME相对应的列数
    private static final int commport_col = 5; // COMMPORT相对应的列数
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getArrivedate() {
        return this.arrivedate;
    }
    public void setArrivedate(String arrivedate) {
        this.arrivedate = arrivedate;
    }
    public String getArrivetime() {
        return this.arrivetime;
    }
    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }
    public String getCommport() {
        return this.commport;
    }
    public void setCommport(String commport) {
        this.commport = commport;
    }
    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
             return  this.id;
        } else if ("mbno".equalsIgnoreCase(name) == true) {
             return  this.mbno;
        } else if ("msg".equalsIgnoreCase(name) == true) {
             return  this.msg;
        } else if ("arrivedate".equalsIgnoreCase(name) == true) {
             return  this.arrivedate;
        } else if ("arrivetime".equalsIgnoreCase(name) == true) {
             return  this.arrivetime;
        } else if ("commport".equalsIgnoreCase(name) == true) {
            return this.commport;
        } else if ("username".equalsIgnoreCase(name) == true) {
            return this.username;
        } else {
            return "";
        }
    }
    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
             this.id = value ;
        } else if ("mbno".equalsIgnoreCase(name) == true) {
             this.mbno = value ;
        } else if ("msg".equalsIgnoreCase(name) == true) {
             this.msg = value ;
        } else if ("arrivedate".equalsIgnoreCase(name) == true) {
             this.arrivedate = value ;
        } else if ("arrivetime".equalsIgnoreCase(name) == true) {
             this.arrivetime = value ;
        } else if ("commport".equalsIgnoreCase(name) == true) {
             this.commport = value ;
         } else if ("username".equalsIgnoreCase(name) == true) {
             this.username = value;
         } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "INBOX";
    }
    public String getPkFields() {
        return "id,";
    }
    public String getModifyFields() {
        return "mbno,msg,arrivedate,arrivetime,commport,";
    }
    public String getAllFields() {
        return "id,mbno,msg,arrivedate,arrivetime,commport,username,";
    }
    public void setValues(String[] values) {
        this.id = values[id_col];
        this.mbno = values[mbno_col];
        this.msg = values[msg_col];
        this.arrivedate = values[arrivedate_col];
        this.arrivetime = values[arrivetime_col];
        this.commport = values[commport_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
