package com.gever.goa.dailyoffice.smsmgr.vo;



import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: SMSCapacity中的vo对象</p>
 * <p>Description: 是SMSCapacity的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class SMSCapacityVO extends BaseVO implements VOInterface {
    public SMSCapacityVO(){
    }
    private String userid = ""; // USERID
    private String numbers = ""; // NUMBER
    private String username;   //       相对应的人员
    private String acl = ""; // ACL
    private String scope = ""; // SCOPE
    private String bz = ""; // BZ
    private String status = ""; // BZ
    private static final int userid_col = 0; // USERID相对应的列数
    private static final int numbers_col = 1; // NUMBER相对应的列数
    private static final int acl_col = 2; // ACL相对应的列数
    private static final int scope_col = 3; // SCOPE相对应的列数
    private static final int bz_col = 4; // BZ相对应的列数
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getNumbers() {
        return this.numbers;
    }
    public void setNumber(String numbers) {
        this.numbers = numbers;
    }
    public String getAcl() {
        return this.acl;
    }
    public void setAcl(String acl) {
        this.acl = acl;
    }
    public String getScope() {
        return this.scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getValue(String name) {
        if ("userid".equalsIgnoreCase(name) == true) {
             return  this.userid;
        } else if ("numbers".equalsIgnoreCase(name) == true) {
             return  this.numbers;
        } else if ("acl".equalsIgnoreCase(name) == true) {
             return  this.acl;
        } else if ("scope".equalsIgnoreCase(name) == true) {
             return  this.scope;
        } else if ("bz".equalsIgnoreCase(name) == true) {
             return  this.bz;
         } else if ("username".equalsIgnoreCase(name) == true) {
             return this.username;
         } else if ("status".equalsIgnoreCase(name) == true) {
             return this.status;
         }else {
             return "";
         }
    }
    public void setValue(String name, String value) {
        if ("userid".equalsIgnoreCase(name) == true) {
             this.userid = value ;
        } else if ("numbers".equalsIgnoreCase(name) == true) {
             this.numbers = value ;
        } else if ("acl".equalsIgnoreCase(name) == true) {
             this.acl = value ;
        } else if ("scope".equalsIgnoreCase(name) == true) {
             this.scope = value ;
         } else if ("bz".equalsIgnoreCase(name) == true) {
             this.bz = value;
         } else if ("username".equalsIgnoreCase(name) == true) {
             this.username = value;
         } else if ("status".equalsIgnoreCase(name) == true) {
             this.status = value;
         } else {
             return;
         }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("userid".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("number".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("acl".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "SMSCapacity";
    }
    public String getPkFields() {
        return "userid,";
    }
    public String getModifyFields() {
        return "numbers,acl,scope,bz,";
    }
    public String getAllFields() {
        return "userid,numbers,acl,scope,bz,username,status,";
    }
    public void setValues(String[] values) {
        this.userid = values[userid_col];
        this.numbers = values[numbers_col];
        this.acl = values[acl_col];
        this.scope = values[scope_col];
        this.bz = values[bz_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
