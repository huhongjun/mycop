package com.gever.goa.dailyoffice.worklog.vo;

import java.util.ArrayList;
import java.util.List;

import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 团队日志设置中的vo对象</p>
 * <p>Description: 是DO_TEAM_LOG的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TeamWorkLogSetVO extends BaseVO implements VOInterface {
    public TeamWorkLogSetVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE--用户代码
    private String team_type = ""; // TEAM_TYPE--团队类型
    private String team_member = ""; // TEAM_MEMBER--团队成员
    private String fdate ="";
    private String fweek ="";
    private List listMens = new ArrayList();

    private ArrayList deptUserList; //部门用户列表
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int team_type_col = 2; // TEAM_TYPE相对应的列数
    private static final int team_member_col = 3; // TEAM_MEMBER相对应的列数
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

    public String getTeam_type() {
        return this.team_type;
    }

    public void setTeam_type(String team_type) {
        this.team_type = team_type;
    }

    public String getTeam_member() {
        return this.team_member;
    }

    public void setTeam_member(String team_member) {
        this.team_member = team_member;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("team_type".equalsIgnoreCase(name) == true) {
            return this.team_type;
        } else if ("team_member".equalsIgnoreCase(name) == true) {
            return this.team_member;
        } else if ("fdate".equalsIgnoreCase(name) == true) {
            return this.fdate;
        }  else if ("fweek".equalsIgnoreCase(name) == true) {
            return this.fweek;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("team_type".equalsIgnoreCase(name) == true) {
            this.team_type = value;
        } else if ("team_member".equalsIgnoreCase(name) == true) {
            this.team_member = value;
        } else if ("fweek".equalsIgnoreCase(name) == true) {
            this.fweek = value;
        } else if ("fdate".equalsIgnoreCase(name) == true) {
            this.fdate = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("team_type".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("fweek".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "DO_TEAM_LOG";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "user_code,team_type,team_member,";
    }

    public String getAllFields() {
        return "serial_num,user_code,team_type,team_member,date";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.team_type = values[team_type_col];
        this.team_member = values[team_member_col];
    }

    public void setOtherProperty(String[] values) {
      if (!StringUtils.isNull(this.fdate) ||  this.fdate.length() > 10) {
        this.fdate = this.fdate.substring(0, 10);
       }
        this.fweek = DateTimeUtils.getWeek(this.fdate,0);
    }

    public ArrayList getDeptUserList() {
        return deptUserList;
    }

    public void setDeptUserList(ArrayList deptUserList) {
        this.deptUserList = deptUserList;
    }
    public String getFdate() {
        return fdate;
    }
    public String getFweek() {
        return fweek;
    }
    public void setFdate(String fdate) {
        this.fdate = fdate;
    }
    public void setFweek(String fweek) {
        this.fweek = fweek;
    }
    public List getListMens() {
        return listMens;
    }
    public void setListMens(List listMens) {
        this.listMens = listMens;
    }
}
