package com.gever.goa.admin.vo;


import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 学历Knowledge中的vo对象</p>
 * <p>Description: 是sys_knowledge的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class KnowVO extends BaseVO implements VOInterface {
    public KnowVO(){
    }
    private String knowledge_code = ""; // KNOWLEDGE_CODE
    private String knowledge_name = ""; // KNOWLEDGE_NAME
    private String remarks = ""; // REMARKS
    private String knowledge_code_bak="";
    private static final int knowledge_code_col = 0; // KNOWLEDGE_CODE相对应的列数
    private static final int knowledge_name_col = 1; // KNOWLEDGE_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    public String getKnowledge_code() {
        return this.knowledge_code;
    }
    public void setKnowledge_code(String knowledge_code) {
        this.knowledge_code = knowledge_code;
    }
    public String getKnowledge_name() {
        return this.knowledge_name;
    }
    public void setKnowledge_name(String knowledge_name) {
        this.knowledge_name = knowledge_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("knowledge_code".equalsIgnoreCase(name) == true) {
             return  this.knowledge_code;
        } else if ("knowledge_name".equalsIgnoreCase(name) == true) {
             return  this.knowledge_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("knowledge_code".equalsIgnoreCase(name) == true) {
             this.knowledge_code = value ;
        } else if ("knowledge_name".equalsIgnoreCase(name) == true) {
             this.knowledge_name = value ;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             this.remarks = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "sys_knowledge";
    }
    public String getPkFields() {
        return "knowledge_code,";
    }
    public String getModifyFields() {
        return "knowledge_name,remarks,";
    }
    public String getAllFields() {
        return "knowledge_code,knowledge_name,remarks,";
    }
    public void setValues(String[] values) {
        this.knowledge_code = values[knowledge_code_col];
        this.knowledge_name = values[knowledge_name_col];
        this.remarks = values[remarks_col];
    }
    public void setOtherProperty(String[] values) {
      knowledge_code_bak=knowledge_code;
    }
    public String getKnowledge_code_bak() {
    return knowledge_code_bak;
  }

  public void setKnowledge_code_bak(String knowledge_code_bak) {
    this.knowledge_code_bak = knowledge_code_bak;
  }


}
