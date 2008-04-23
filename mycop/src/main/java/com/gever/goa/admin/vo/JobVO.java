package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 职称Job中的vo对象</p>
 * <p>Description: 是sys_job的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class JobVO extends BaseVO implements VOInterface {
    public JobVO(){
    }
    private String job_code = ""; // JOB_CODE
    private String job_name = ""; // JOB_NAME
    private String remarks = ""; // REMARKS
    private String job_code_bak="";
    private static final int job_code_col = 0; // JOB_CODE相对应的列数
    private static final int job_name_col = 1; // JOB_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    public String getJob_code() {
        return this.job_code;
    }
    public void setJob_code(String job_code) {
        this.job_code = job_code;
    }
    public String getJob_name() {
        return this.job_name;
    }
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("job_code".equalsIgnoreCase(name) == true) {
             return  this.job_code;
        } else if ("job_name".equalsIgnoreCase(name) == true) {
             return  this.job_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("job_code".equalsIgnoreCase(name) == true) {
             this.job_code = value ;
        } else if ("job_name".equalsIgnoreCase(name) == true) {
             this.job_name = value ;
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
        return "sys_job";
    }
    public String getPkFields() {
        return "job_code,";
    }
    public String getModifyFields() {
        return "job_name,remarks,";
    }
    public String getAllFields() {
        return "job_code,job_name,remarks,";
    }
    public void setValues(String[] values) {
        this.job_code = values[job_code_col];
        this.job_name = values[job_name_col];
        this.remarks = values[remarks_col];
    }
    public void setOtherProperty(String[] values) {
      job_code_bak=job_code;
    }
    public String getJob_code_bak() {
     return job_code_bak;
   }

   public void setJob_code_bak(String job_code_bak) {
     this.job_code_bak = job_code_bak;
   }

}
