package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 民族Unit中的vo对象</p>
 * <p>Description: 是的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class UnitVO extends BaseVO implements VOInterface {
    public UnitVO(){
    }
    private String unit_code = ""; // UNIT_CODE
    private String unit_name = ""; // UNIT_NAME
    private String phone = ""; // PHONE
    private String fax = ""; // FAX
    private String e_mail = ""; // E_MAIL
    private String home_page = ""; // HOME_PAGE
    private String remark = ""; // REMARK
    private String unit_type = ""; // UNIT_TYPE
    private String unit_code_bak = "";
    private static final int unit_code_col = 0; // UNIT_CODE相对应的列数
    private static final int unit_name_col = 1; // UNIT_NAME相对应的列数
    private static final int phone_col = 2; // PHONE相对应的列数
    private static final int fax_col = 3; // FAX相对应的列数
    private static final int e_mail_col = 4; // E_MAIL相对应的列数
    private static final int home_page_col = 5; // HOME_PAGE相对应的列数
    private static final int remark_col = 6; // REMARK相对应的列数
    private static final int unit_type_col = 7; // UNIT_TYPE相对应的列数
      private static final int unit_code_bak_col = 7; // UNIT_TYPE相对应的列数
    public String getUnit_code() {
        return this.unit_code;
    }
    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }
    public String getUnit_name() {
        return this.unit_name;
    }
    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return this.fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getE_mail() {
        return this.e_mail;
    }
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
    public String getHome_page() {
        return this.home_page;
    }
    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getUnit_type() {
        return this.unit_type;
    }
    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }
    public String getValue(String name) {
        if ("unit_code".equalsIgnoreCase(name) == true) {
             return  this.unit_code;
        } else if ("unit_name".equalsIgnoreCase(name) == true) {
             return  this.unit_name;
        } else if ("phone".equalsIgnoreCase(name) == true) {
             return  this.phone;
        } else if ("fax".equalsIgnoreCase(name) == true) {
             return  this.fax;
        } else if ("e_mail".equalsIgnoreCase(name) == true) {
             return  this.e_mail;
        } else if ("home_page".equalsIgnoreCase(name) == true) {
             return  this.home_page;
        } else if ("remark".equalsIgnoreCase(name) == true) {
             return  this.remark;
        } else if ("unit_type".equalsIgnoreCase(name) == true) {
             return  this.unit_type;
           } else if ("unit_code_bak".equalsIgnoreCase(name) == true) {
             return  this.unit_code_bak;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("unit_code".equalsIgnoreCase(name) == true) {
             this.unit_code = value ;
        } else if ("unit_name".equalsIgnoreCase(name) == true) {
             this.unit_name = value ;
        } else if ("phone".equalsIgnoreCase(name) == true) {
             this.phone = value ;
        } else if ("fax".equalsIgnoreCase(name) == true) {
             this.fax = value ;
        } else if ("e_mail".equalsIgnoreCase(name) == true) {
             this.e_mail = value ;
        } else if ("home_page".equalsIgnoreCase(name) == true) {
             this.home_page = value ;
        } else if ("remark".equalsIgnoreCase(name) == true) {
             this.remark = value ;
        } else if ("unit_type".equalsIgnoreCase(name) == true) {
             this.unit_type = value ;
           } else if ("unit_code_bak".equalsIgnoreCase(name) == true) {
             this.unit_code_bak = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("unit_type".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "sys_unit_info";
    }
    public String getPkFields() {
        return "unit_code,";
    }
    public String getModifyFields() {
        return "unit_name,phone,fax,e_mail,home_page,remark,unit_type,";
    }
    public String getAllFields() {
        return "unit_code,unit_name,phone,fax,e_mail,home_page,remark,unit_type,";
    }
    public void setValues(String[] values) {
        this.unit_code = values[unit_code_col];
        this.unit_name = values[unit_name_col];
        this.phone = values[phone_col];
        this.fax = values[fax_col];
        this.e_mail = values[e_mail_col];
        this.home_page = values[home_page_col];
        this.remark = values[remark_col];
        this.unit_type = values[unit_type_col];
        this.unit_code_bak=values[unit_code_bak_col];
    }
    public void setOtherProperty(String[] values) {
    }
  public String getUnit_code_bak() {
    return unit_code_bak;
  }
  public void setUnit_code_bak(String unit_code_bak) {
    this.unit_code_bak = unit_code_bak;
  }
}
