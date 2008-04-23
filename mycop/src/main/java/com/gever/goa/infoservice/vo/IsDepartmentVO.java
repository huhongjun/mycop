package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description:版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:天行软件 </p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsDepartmentVO extends BaseVO implements VOInterface {
    public IsDepartmentVO() {
    }

    private String id = ""; // ID
    private String code = ""; // CODE
    private String name = ""; // NAME
    private String description = ""; // DESCRIPTION
    private String departmenttype = ""; // DEPARTMENTTYPE
    private String parent_id = ""; // PARENT_ID
    private static final int id_col = 0; // ID相对应的列数
    private static final int code_col = 1; // CODE相对应的列数
    private static final int name_col = 2; // NAME相对应的列数
    private static final int description_col = 3; // DESCRIPTION相对应的列数
    private static final int departmenttype_col = 4; // DEPARTMENTTYPE相对应的列数
    private static final int parent_id_col = 5; // PARENT_ID相对应的列数

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
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

    public String getDepartmenttype() {
        return this.departmenttype;
    }

    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }

    public String getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
            return this.id;
        } else if ("code".equalsIgnoreCase(name) == true) {
            return this.code;
        } else if ("name".equalsIgnoreCase(name) == true) {
            return this.name;
        } else if ("description".equalsIgnoreCase(name) == true) {
            return this.description;
        } else if ("departmenttype".equalsIgnoreCase(name) == true) {
            return this.departmenttype;
        } else if ("parent_id".equalsIgnoreCase(name) == true) {
            return this.parent_id;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
            this.id = value;
        } else if ("code".equalsIgnoreCase(name) == true) {
            this.code = value;
        } else if ("name".equalsIgnoreCase(name) == true) {
            this.name = value;
        } else if ("description".equalsIgnoreCase(name) == true) {
            this.description = value;
        } else if ("departmenttype".equalsIgnoreCase(name) == true) {
            this.departmenttype = value;
        } else if ("parent_id".equalsIgnoreCase(name) == true) {
            this.parent_id = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("parent_id".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "T_DEPARTMENT";
    }

    public String getPkFields() {
        return "id,";
    }

    public String getModifyFields() {
        return "code,name,description,departmenttype,parent_id,";
    }

    public String getAllFields() {
        return "id,code,name,description,departmenttype,parent_id,";
    }

    public void setValues(String[] values) {
        this.id = values[id_col];
        this.code = values[code_col];
        this.name = values[name_col];
        this.description = values[description_col];
        this.departmenttype = values[departmenttype_col];
        this.parent_id = values[parent_id_col];
    }

    public void setOtherProperty(String[] values) {
    }
}