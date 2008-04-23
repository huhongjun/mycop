package com.gever.goa.dailyoffice.worklog.vo;

import java.util.ArrayList;
import java.util.List;

import com.gever.util.Codes;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ������־�е�vo����</p>
 * <p>Description: ��DO_WORK_LOG��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0  ��������:2004-07-09
 */

public class WorkLogVO
    extends BaseVO
    implements VOInterface {
  public WorkLogVO() {
  }

  private String serial_num = ""; // SERIAL_NUM

  //��"��+��+��+ʱ��(Сʱ�������)+�û�����" ��2004062912305600004liu
  private String create_time = ""; // CREATE_TIME--ָ��һ���½�ʱ��
  private String dept_code = ""; // DEPT_CODE--���Ŵ��룬�벿�ű����
  private String user_code = ""; // USER_CODE--�������룬���û������
  private String fill_date = ""; // FILL_DATE--ָ���һ�θ��µ�����
  private String cur_week = ""; //����ǰ����ת��Ϊ��ǰ������ҳ������ʾ--�����ֶ�
  private byte[] log_content; // LOG_CONTENT--��־����Ӧ�����ݣ�����ģ��
  private String isTodayWorkLog; //�жϸ���־��¼�Ƿ�Ϊ�������־
  private String user_name = "";
  private String log_memos = "";
  private List sublist = new ArrayList();
  private static final int serial_num_col = 0; // SERIAL_NUM���Ӧ������
  private static final int create_time_col = 1; // CREATE_TIME���Ӧ������
  private static final int dept_code_col = 2; // DEPT_CODE���Ӧ������
  private static final int user_code_col = 3; // USER_CODE���Ӧ������
  private static final int fill_date_col = 4; // FILL_DATE���Ӧ������
  private static final int log_content_col = 5; // LOG_CONTENT���Ӧ������
  public String getSerial_num() {
    return this.serial_num;
  }

  public void setSerial_num(String serial_num) {
    this.serial_num = serial_num;
  }

  public String getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }

  public String getDept_code() {
    return this.dept_code;
  }

  public void setDept_code(String dept_code) {
    this.dept_code = dept_code;
  }

  public String getUser_code() {
    return this.user_code;
  }

  public void setUser_code(String user_code) {
    this.user_code = user_code;
  }

  public String getFill_date() {
    return this.fill_date;
  }

  public void setFill_date(String fill_date) {
    this.fill_date = fill_date;
  }

  public byte[] getLog_content() {
    return this.log_content;
  }

  public void setLog_content(byte[] log_content) {
    this.log_content = log_content;
  }

  public String getValue(String name) {
    if ("serial_num".equalsIgnoreCase(name) == true) {
      return this.serial_num;
    }
    else if ("create_time".equalsIgnoreCase(name) == true) {
      return this.create_time;
    }
    else if ("dept_code".equalsIgnoreCase(name) == true) {
      return this.dept_code;
    }
    else if ("user_code".equalsIgnoreCase(name) == true) {
      return this.user_code;
    }
    else if ("fill_date".equalsIgnoreCase(name) == true) {
      return this.fill_date;
    }
    else if ("log_content".equalsIgnoreCase(name) == true) {
      return "";
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      return user_name;
    }
    else if ("log_memos".equalsIgnoreCase(name) == true) {
      return log_memos;
    }
    else {
      return "";
    }
  }

  public void setValue(String name, String value) {
    if ("serial_num".equalsIgnoreCase(name) == true) {
      this.serial_num = value;
    }
    else if ("create_time".equalsIgnoreCase(name) == true) {
      this.create_time = value;
    }
    else if ("dept_code".equalsIgnoreCase(name) == true) {
      this.dept_code = value;
    }
    else if ("user_code".equalsIgnoreCase(name) == true) {
      this.user_code = value;
    }
    else if ("fill_date".equalsIgnoreCase(name) == true) {
      this.fill_date = value;
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      this.user_name = value;
    }
    else if ("log_content".equalsIgnoreCase(name) == true) {
      this.log_content = Codes.decode(value.toCharArray());
    }
    else if ("log_memos".equalsIgnoreCase(name) == true) {
      this.log_memos = value;
    }
    else {
      return;
    }
  }

  public String getColType(String name) {
    String colType = new String();
    if ("create_time".equalsIgnoreCase(name) == true) {
      colType = "timestamp";
    }
    else if ("user_code".equalsIgnoreCase(name) == true) {
      colType = "int";
    }
    else if ("fill_date".equalsIgnoreCase(name) == true) {
      colType = "date";
    }
    else if ("log_content".equalsIgnoreCase(name) == true) {
      colType = "bytes";
    }
    else {
      colType = "String";
    }
    return colType;
  }

  public String getTableName() {
    return "DO_WORK_LOG";
  }

  public String getPkFields() {
    return "serial_num,";
  }

  public String getModifyFields() {
    return "create_time,dept_code,user_code,fill_date,log_content,";
  }

  public String getAllFields() {
    return "serial_num,create_time,dept_code,user_code,fill_date,user_name,log_memos,log_content,";
  }

  public void setValues(String[] values) {
    this.serial_num = values[serial_num_col];
    this.create_time = values[create_time_col];
    this.dept_code = values[dept_code_col];
    this.user_code = values[user_code_col];
    this.fill_date = values[fill_date_col];

  }

  public void setOtherProperty(String[] values) {
    if (!StringUtils.isNull(this.fill_date) ||
        this.fill_date.length() > 10) {
      this.fill_date = this.fill_date.substring(0, 10);
    }

    if (! (this.fill_date == null || "".equals(this.fill_date) ||
           "null".equals(this.fill_date))) { //���жϵĻ������
      this.cur_week = DateTimeUtils.getWeek(this.fill_date, 0);
    }
    if (! (this.create_time == null || "".equals(this.create_time) ||
           "null".equals(this.create_time))) {
      if (this.create_time.length() > 19) {
      this.create_time = this.create_time.substring(0, 19);
      }
    }

    String curDate = DateTimeUtils.getCurrentDate();
    if (this.fill_date.equals(curDate)) {
      this.isTodayWorkLog = "1"; //1--�ǵ������־�������޸�
    }
    else {
      this.isTodayWorkLog = "0"; //�������޸�
    }

  }

  public String getCur_week() {
    return cur_week;
  }

  public void setCur_week(String cur_week) {
    this.cur_week = cur_week;
  }

  public String getIsTodayWorkLog() {
    return isTodayWorkLog;
  }

  public void setIsTodayWorkLog(String isTodayWorkLog) {
    this.isTodayWorkLog = isTodayWorkLog;
  }

  public List getSublist() {
    return sublist;
  }

  public void setSublist(List sublist) {
    this.sublist = sublist;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getLog_memos() {
    return log_memos;
  }

  public void setLog_memos(String log_memos) {
    this.log_memos = log_memos;
  }
}
