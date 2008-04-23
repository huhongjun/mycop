package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 信息类型设置VO类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsDoTypeVO
    extends BaseVO
    implements VOInterface {
    public IsDoTypeVO() {
    }

    private String info_type = ""; // INFO_TYPE
    private String typelevel = ""; // TYPELEVEL
    private String parent_type = ""; // PARENT_TYPE
    private String moduleflag = ""; //MODULEFLAG
    private String remark = ""; // REMARK
    private String old_info_type = "";
    private static final int info_type_col = 0; // INFO_TYPE相对应的列数
    private static final int typelevel_col = 1; // TYPELEVEL相对应的列数
    private static final int parent_type_col = 2; // PARENT_TYPE相对应的列数
    private static final int moduleflag_col = 3; //MODULEFLAG对应的列数
    private static final int remark_col = 4; // REMARK相对应的列数
    private static final int old_info_type_col = 4;
    public String getInfo_type() {
        return this.info_type;
    }

    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }

    public String getTypelevel() {
        return this.typelevel;
    }

    public void setTypelevel(String typelevel) {
        this.typelevel = typelevel;
    }

    public String getParent_type() {
        return this.parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getModuleflag() {
        return moduleflag;
    }

    public void setModuleflag(String moduleflag) {
        this.moduleflag = moduleflag;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValue(String name) {
        if ("info_type".equalsIgnoreCase(name) == true) {
            return this.info_type;
        } else if ("typelevel".equalsIgnoreCase(name) == true) {
            return this.typelevel;
        } else if ("parent_type".equalsIgnoreCase(name) == true) {
            return this.parent_type;
        } else if ("moduleflag".equalsIgnoreCase(name) == true) {
            return this.moduleflag;
        } else if ("remark".equalsIgnoreCase(name) == true) {
            return this.remark;
        } else if ("old_info_type".equalsIgnoreCase(name) == true) {
            return this.old_info_type;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("info_type".equalsIgnoreCase(name) == true) {
            this.info_type = value;
        } else if ("typelevel".equalsIgnoreCase(name) == true) {
            this.typelevel = value;
        } else if ("parent_type".equalsIgnoreCase(name) == true) {
            this.parent_type = value;
        } else if ("moduleflag".equalsIgnoreCase(name) == true) {
            this.moduleflag = value;
        } else if ("remark".equalsIgnoreCase(name) == true) {
            this.remark = value;
        } else if ("old_info_type".equalsIgnoreCase(name) == true) {
            this.old_info_type = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("typelevel".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "IS_DOTYPE";
    }

    public String getPkFields() {
        return "info_type,";
    }

    public String getModifyFields() {
        return "typelevel,parent_type,moduleflag,remark,";
    }

    public String getAllFields() {
        return "info_type,typelevel,parent_type,moduleflag,remark,";
    }

    public void setValues(String[] values) {
        this.info_type = values[info_type_col];
        this.typelevel = values[typelevel_col];
        this.parent_type = values[parent_type_col];
        this.moduleflag = values[moduleflag_col];
        this.remark = values[remark_col];
    }

    public void setOtherProperty(String[] values) {
        this.old_info_type = this.info_type;
    }

    public String getOld_info_type() {
        return old_info_type;
    }

    public void setOld_info_type(String old_info_type) {
        this.old_info_type = old_info_type;
    }
}
