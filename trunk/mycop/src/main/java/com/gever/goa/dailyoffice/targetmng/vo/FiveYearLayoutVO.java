package com.gever.goa.dailyoffice.targetmng.vo;

import com.gever.goa.util.ConstantSet;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.organization.model.impl.DefaultUser;
import com.gever.util.Codes;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: FiveYearLayout中的vo对象</p>
 * <p>Description: 是FiveYearLayout的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0  创建日期:
 */

public class FiveYearLayoutVO extends BaseVO implements VOInterface {
    public FiveYearLayoutVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String current_year = ""; //当前年度
    private String user_code = ""; // USER_CODE--用户代码
    private String create_date = ""; // CREATE_DATE--创建日期
    private String approve = ""; // APPROVE--批准人
    private String approve_date = ""; // APPROVE_DATE--批准日期
    private byte[] content; // CONTENT--五年规划内容
    private String approve_flag = ""; // APPROVE_FLAG--批准标志
    private String approve_name = ""; //批准状态中文显示
    private String user_name = ""; //创建人中文名显示
    private String approver_name = ""; //批准人中文名显示
    private String edit_delete_flag; //用来在列表中判断当前人对该记录是否具有修改和删除权限
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int current_year_col = 1; // CURRENT_YEAR相对应的列数
    private static final int user_code_col = 2; // USER_CODE相对应的列数
    private static final int create_date_col = 3; // CREATE_DATE相对应的列数
    private static final int approve_col = 4; // APPROVE相对应的列数
    private static final int approve_date_col = 5; // APPROVE_DATE相对应的列数
    private static final int content_col = 6; // CONTENT相对应的列数
    private static final int approve_flag_col = 7; // APPROVE_FLAG相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getCurrent_year() {
        return this.current_year;
    }

    public void setCurrent_year(String current_year) {
        this.current_year = current_year;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getApprove() {
        return this.approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprove_date() {
        return this.approve_date;
    }

    public void setApprove_date(String approve_date) {
        this.approve_date = approve_date;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getApprove_flag() {
        return this.approve_flag;
    }

    public void setApprove_flag(String approve_flag) {
        this.approve_flag = approve_flag;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
            return this.current_year;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("approve".equalsIgnoreCase(name) == true) {
            return this.approve;
        } else if ("approve_date".equalsIgnoreCase(name) == true) {
            return this.approve_date;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return "";
        } else if ("approve_flag".equalsIgnoreCase(name) == true) {
            return this.approve_flag;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
            this.current_year = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("approve".equalsIgnoreCase(name) == true) {
            this.approve = value;
        } else if ("approve_date".equalsIgnoreCase(name) == true) {
            this.approve_date = value;
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = Codes.decode(value.toCharArray());
        } else if ("approve_flag".equalsIgnoreCase(name) == true) {
            this.approve_flag = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("approve_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("content".equalsIgnoreCase(name) == true) {
            colType = "bytes";
        } else if ("approve_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "DO_FIVEYEAR_LAYOUT";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "current_year,user_code,create_date,approve,approve_date,content,approve_flag,";
    }

    public String getAllFields() {
        return "serial_num,current_year,user_code,create_date,approve,approve_date,content,approve_flag,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.current_year = values[current_year_col];
        this.user_code = values[user_code_col];
        this.create_date = values[create_date_col];
        this.approve = values[approve_col];
        this.approve_date = values[approve_date_col];
        this.content = Codes.decode(values[content_col].toCharArray());
        this.approve_flag = values[approve_flag_col];
    }

    public void setOtherProperty(String[] values) {
    	//转ORACLE加
    	if(!StringUtils.isNull(this.create_date) && this.create_date.length()>10){
    		this.create_date=this.create_date.substring(0,10);
    	}
    	if(!StringUtils.isNull(this.approve_date)&& this.approve_date.length()>10){
    		this.approve_date=this.approve_date.substring(1,10);
    	}
        try {
            UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
            if (!"".equals(this.approve) && this.approve != null) {
                User userApprove = new DefaultUser();
                userApprove = userDao.findUserByID(Integer.parseInt(this.approve));
                this.approver_name = userApprove.getName();
            }
            if (!"".equals(this.user_code) && this.user_code != null) {
                User userName = new DefaultUser();
                userName = userDao.findUserByID(Integer.parseInt(this.user_code));
                this.user_name = userName.getName();
            }
            if ("0".equals(this.approve_flag)) {
                this.approve_name = "未批准";
            } else if ("1".equals(this.approve_flag)) {
                this.approve_name = "已批准";
            } else if ("2".equals(this.approve_flag)) {
                this.approve_name = "批准未通过";
            } else {
                this.approve_name = "未批准";
            }
            int fiveYearLayoutEditDeleteFlag = 0;
            ConstantSet cs = new ConstantSet();
            String curUserID = ConstantSet.CurUserID; //取得当前用户
            fiveYearLayoutEditDeleteFlag = cs.judgeFiveYearLayoutEditDeleteTag(curUserID,this.user_code,this.approve_flag,this.approve);
            if (fiveYearLayoutEditDeleteFlag == 1) { //如果可修改删除
                this.edit_delete_flag = "1";
            } else if (fiveYearLayoutEditDeleteFlag == 0) {
                this.edit_delete_flag = "0";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getApprove_name() {
        return approve_name;
    }

    public void setApprove_name(String approve_name) {
        this.approve_name = approve_name;
    }

    public String getApprover_name() {
        return approver_name;
    }

    public void setApprover_name(String approver_name) {
        this.approver_name = approver_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEdit_delete_flag() {
        return edit_delete_flag;
    }

    public void setEdit_delete_flag(String edit_delete_flag) {
        this.edit_delete_flag = edit_delete_flag;
    }
}
