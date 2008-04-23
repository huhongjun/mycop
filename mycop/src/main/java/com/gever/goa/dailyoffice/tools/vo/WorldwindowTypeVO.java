package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: WorldwindowType�е�vo����</p>
 * <p>Description: ��do_info_type��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5  ��������:
 */
public class WorldwindowTypeVO extends BaseVO implements VOInterface {
    public WorldwindowTypeVO(){
    }
    private String info_type = ""; // INFO_TYPE
    private String type_name = ""; // TYPE_NAME
    private static final int info_type_col = 0; // INFO_TYPE���Ӧ������
    private static final int type_name_col = 1; // TYPE_NAME���Ӧ������
    public String getInfo_type() {
        return this.info_type;
    }
    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }
    public String getType_name() {
        return this.type_name;
    }
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    public String getValue(String name) {
        if ("info_type".equalsIgnoreCase(name) == true) {
             return  this.info_type;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
             return  this.type_name;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("info_type".equalsIgnoreCase(name) == true) {
             this.info_type = value ;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
             this.type_name = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "do_info_type";
    }
    public String getPkFields() {
        return "info_type,";
    }
    public String getModifyFields() {
        return "type_name,";
    }
    public String getAllFields() {
        return "info_type,type_name,";
    }
    public void setValues(String[] values) {
        this.info_type = values[info_type_col];
        this.type_name = values[type_name_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
