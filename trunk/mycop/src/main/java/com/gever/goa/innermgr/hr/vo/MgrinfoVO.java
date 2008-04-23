package com.gever.goa.innermgr.hr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: MgrinfoVO�е�vo����</p>
 * <p>Description: ��extmgr_permition��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5  ��������:
 */
public class MgrinfoVO extends BaseVO implements VOInterface {
    public MgrinfoVO(){
    }
    private String id ; // ID
    private String name; // NAME
    private String tag ; // TAG
    private static final int id_col = 0; // ID���Ӧ������
    private static final int name_col = 1; // NAME���Ӧ������
    private static final int tag_col = 2; // TAG���Ӧ������
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
             return  this.id;
        } else if ("name".equalsIgnoreCase(name) == true) {
             return  this.name;
        } else if ("tag".equalsIgnoreCase(name) == true) {
             return  this.tag;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
             this.id = value ;
        } else if ("name".equalsIgnoreCase(name) == true) {
             this.name = value ;
        } else if ("tag".equalsIgnoreCase(name) == true) {
             this.tag = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
             colType = "string";
        } else if ("tag".equalsIgnoreCase(name) == true) {
             colType = "string";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "extmgr_permition";
    }
    public String getPkFields() {
        return "id,";
    }
    public String getModifyFields() {
        return "id,name,tag,";
    }
    public String getAllFields() {
        return "id,name,tag,";
    }
    public void setValues(String[] values) {
        this.id = values[id_col];
        this.name = values[name_col];
        this.tag = values[tag_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
