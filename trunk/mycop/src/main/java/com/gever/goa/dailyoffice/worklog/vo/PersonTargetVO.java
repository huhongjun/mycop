package com.gever.goa.dailyoffice.worklog.vo;

import com.gever.goa.util.ConstantSet;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.impl.DefaultUser;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: 中的vo对象</p>
 * <p>Description: 是do_personal_target的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class PersonTargetVO extends BaseVO implements VOInterface {
    public PersonTargetVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE
    private String current_year = ""; // CURRENT_YEAR
    private String current_month = ""; // CURRENT_MONTH
    private String current_work = ""; // CURRENT_WORK
    private String target_type = ""; // TARGET_TYPE
    private String dept_code = ""; // DEPT_CODE
    private String user_name = ""; //创建人中文名显示
    private String edit_delete_flag = ""; //修改删除标志判断标记
    private String post_code = ""; // POST_CODE
    private String create_date = ""; // CREATE_DATE
    private String auditor = ""; // AUDITOR
    private String audit_flag = ""; // AUDIT_FLAG
    private String audit_date = ""; // AUDIT_DATE
    private String audit_opinion = ""; // AUDIT_OPINION
    private byte[] target_content ; // TARGET_CONTENT
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int current_year_col = 2; // CURRENT_YEAR相对应的列数
    private static final int current_month_col = 3; // CURRENT_MONTH相对应的列数
    private static final int current_work_col = 4; // CURRENT_WORK相对应的列数
    private static final int target_type_col = 5; // TARGET_TYPE相对应的列数
    private static final int dept_code_col = 6; // DEPT_CODE相对应的列数
    private static final int post_code_col = 7; // POST_CODE相对应的列数
    private static final int create_date_col = 8; // CREATE_DATE相对应的列数
    private static final int auditor_col = 9; // AUDITOR相对应的列数
    private static final int audit_flag_col = 10; // AUDIT_FLAG相对应的列数
    private static final int audit_date_col = 11; // AUDIT_DATE相对应的列数
    private static final int audit_opinion_col = 12; // AUDIT_OPINION相对应的列数
    private static final int target_content_col = 13; // TARGET_CONTENT相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getCurrent_year() {
        return this.current_year;
    }
    public void setCurrent_year(String current_year) {
        this.current_year = current_year;
    }
    public String getCurrent_month() {
        return this.current_month;
    }
    public void setCurrent_month(String current_month) {
        this.current_month = current_month;
    }
    public String getCurrent_work() {
        return this.current_work;
    }
    public void setCurrent_work(String current_work) {
        this.current_work = current_work;
    }
    public String getTarget_type() {
        return this.target_type;
    }
    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }
    public String getDept_code() {
        return this.dept_code;
    }
    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }
    public String getPost_code() {
        return this.post_code;
    }
    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }
    public String getCreate_date() {
        return this.create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public String getAuditor() {
        return this.auditor;
    }
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
    public String getAudit_flag() {
        return this.audit_flag;
    }
    public void setAudit_flag(String audit_flag) {
        this.audit_flag = audit_flag;
    }
    public String getAudit_date() {
        return this.audit_date;
    }
    public void setAudit_date(String audit_date) {
        this.audit_date = audit_date;
    }
    public String getAudit_opinion() {
        return this.audit_opinion;
    }
    public void setAudit_opinion(String audit_opinion) {
        this.audit_opinion = audit_opinion;
    }
    public byte[] getTarget_content() {
        return this.target_content;
    }

    public void setTarget_content(byte[] target_content) {
        this.target_content = target_content;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
             return  this.current_year;
        } else if ("current_month".equalsIgnoreCase(name) == true) {
             return  this.current_month;
        } else if ("current_work".equalsIgnoreCase(name) == true) {
             return  this.current_work;
        } else if ("target_type".equalsIgnoreCase(name) == true) {
             return  this.target_type;
        } else if ("dept_code".equalsIgnoreCase(name) == true) {
             return  this.dept_code;
        } else if ("post_code".equalsIgnoreCase(name) == true) {
             return  this.post_code;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
             return  this.create_date;
        } else if ("auditor".equalsIgnoreCase(name) == true) {
             return  this.auditor;
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
             return  this.audit_flag;
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
             return  this.audit_date;
        } else if ("audit_opinion".equalsIgnoreCase(name) == true) {
             return  this.audit_opinion;
        } else if ("target_content".equalsIgnoreCase(name) == true) {
             return "";
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
             this.current_year = value ;
        } else if ("current_month".equalsIgnoreCase(name) == true) {
             this.current_month = value ;
        } else if ("current_work".equalsIgnoreCase(name) == true) {
             this.current_work = value ;
        } else if ("target_type".equalsIgnoreCase(name) == true) {
             this.target_type = value ;
        } else if ("dept_code".equalsIgnoreCase(name) == true) {
             this.dept_code = value ;
        } else if ("post_code".equalsIgnoreCase(name) == true) {
             this.post_code = value ;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
             this.create_date = value ;
        } else if ("auditor".equalsIgnoreCase(name) == true) {
             this.auditor = value ;
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
             this.audit_flag = value ;
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
             this.audit_date = value ;
        } else if ("audit_opinion".equalsIgnoreCase(name) == true) {
             this.audit_opinion = value ;
        } else if ("target_content".equalsIgnoreCase(name) == true) {
             this.target_content = value.getBytes() ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("target_type".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("create_date".equalsIgnoreCase(name) == true) {
             colType = "date";
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
             colType = "date";
        } else if ("target_content".equalsIgnoreCase(name) == true) {
             colType = "bytes";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "do_personal_target";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "user_code,current_year,current_month,current_work,target_type,dept_code,post_code,create_date,auditor,audit_flag,audit_date,audit_opinion,target_content,";
    }
    public String getAllFields() {
        return "serial_num,user_code,current_year,current_month,current_work,target_type,dept_code,post_code,create_date,auditor,audit_flag,audit_date,audit_opinion,target_content,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.current_year = values[current_year_col];
        this.current_month = values[current_month_col];
        this.current_work = values[current_work_col];
        this.target_type = values[target_type_col];
        this.dept_code = values[dept_code_col];
        this.post_code = values[post_code_col];
        this.create_date = values[create_date_col];
        this.auditor = values[auditor_col];
        this.audit_flag = values[audit_flag_col];
        this.audit_date = values[audit_date_col];
        this.audit_opinion = values[audit_opinion_col];
        this.target_content = values[target_content_col].getBytes();
    }
    public void setOtherProperty(String[] values) {
      if(!StringUtils.isNull(this.create_date)&& this.create_date.length()>10){
              this.create_date=this.create_date.substring(0,10);
      }
      try {
            UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
            User userName = new DefaultUser();
            userName = userDao.findUserByID(Integer.parseInt(this.user_code));
            this.user_name = userName.getName();

            String curUserID = ConstantSet.CurUserID;//取得当前用户
            int creatorFlag = 0;
            creatorFlag = ConstantSet.judgeCreatorTag(curUserID, this.user_code,this.auditor,this.audit_flag);
            if (creatorFlag == 1) { //当前人是创建人时
                this.edit_delete_flag = "1"; //拥有修改删除权限
            } else if (creatorFlag == 0) {
                this.edit_delete_flag = "0"; //不拥有修改删除权限
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  public String getEdit_delete_flag() {
    return edit_delete_flag;
  }
  public void setEdit_delete_flag(String edit_delete_flag) {
    this.edit_delete_flag = edit_delete_flag;
  }
  public String getUser_name() {
    return user_name;
  }
  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }
}
