package com.gever.goa.dailyoffice.targetmng.vo;

import com.gever.goa.util.ConstantSet;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.Codes;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: Target�е�vo����</p>
 * <p>Description: ��Target��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0  ��������:
 */

public class TargetVO extends BaseVO implements VOInterface {
    public TargetVO() {
    }

    private String serial_num = ""; // SERIAL_NUM--���

    //��"��+��+��+ʱ��(Сʱ�������)+�û�����" ��2004062912305600004liu
    private String current_year = ""; // CURRENT_YEAR--��ǰ��
    private String current_month = ""; // CURRENT_MONTH--��ǰ��
    private String current_work = ""; // CURRENT_WORK--��ǰ��
    private String target_type = ""; // TARGET_TYPE--Ŀ������

    //1��ʾ��Ŀ�꣬2��ʾ�¶�Ŀ�꣬3��ʾ�¶��ܽᣬ4���Ŀ�꣬5��ʾ����ܽ�
    private String dept_code = ""; // DEPT_CODE--���Ŵ���
    private String user_code = ""; // USER_CODE--�û�����
    private String create_date = ""; // CREATE_DATE--��������
    private String auditor = ""; // AUDITOR--�����--���û������
    private String audit_date = ""; // AUDIT_DATE--�������
    private String audit_flag = ""; // AUDIT_FLAG--�Ƿ�ͨ�����

    //0��ʾδ���,1��ʾ��ͨ�����,2��ʾδͨ�����
    private String audit_opinion = ""; // AUDIT_OPINION--������
    private String check_flag = ""; // CHECK_FLAG--�Ƿ�ͨ�����

    //0��ʾδ���,1��ʾ��ͨ��,2��ʾδͨ������
    private String checker = ""; // CHECKER--������--���û�����
    private String check_date = ""; // CHECK_DATE--��������
    private String check_opinion = ""; /// CHECK_OPINION--�������
    private byte[] target_content; // TARGET_CONTENT--Ŀ������Ӧ�����ݣ�����ģ�塣
    private String audit_check_name; //�Զ����ֶ�-���б�����ʾ�������״̬����
    private String dept_name; //�б�����ʾ�Ĳ�������
    private String edit_delete_flag; //�������б����жϵ�ǰ�˶Ըü�¼�Ƿ�����޸ĺ�ɾ��Ȩ��
    private static final int serial_num_col = 0; // SERIAL_NUM���Ӧ������
    private static final int current_year_col = 1; // CURRENT_YEAR���Ӧ������
    private static final int current_month_col = 2; // CURRENT_MONTH���Ӧ������
    private static final int current_work_col = 3; // CURRENT_WORK���Ӧ������
    private static final int target_type_col = 4; // TARGET_TYPE���Ӧ������
    private static final int dept_code_col = 5; // DEPT_CODE���Ӧ������
    private static final int user_code_col = 6; // USER_CODE���Ӧ������
    private static final int create_date_col = 7; // CREATE_DATE���Ӧ������
    private static final int auditor_col = 8; // AUDITOR���Ӧ������
    private static final int audit_date_col = 9; // AUDIT_DATE���Ӧ������
    private static final int audit_flag_col = 10; // AUDIT_FLAG���Ӧ������
    private static final int audit_opinion_col = 11; // AUDIT_OPINION���Ӧ������
    private static final int check_flag_col = 12; // CHECK_FLAG���Ӧ������
    private static final int checker_col = 13; // CHECKER���Ӧ������
    private static final int check_date_col = 14; // CHECK_DATE���Ӧ������
    private static final int check_opinion_col = 15; // CHECK_OPINION���Ӧ������
    private static final int target_content_col = 16; // TARGET_CONTENT���Ӧ������
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

    public String getAuditor() {
        return this.auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAudit_date() {
        return this.audit_date;
    }

    public void setAudit_date(String audit_date) {
        this.audit_date = audit_date;
    }

    public String getAudit_flag() {
        return this.audit_flag;
    }

    public void setAudit_flag(String audit_flag) {
        this.audit_flag = audit_flag;
    }

    public String getAudit_opinion() {
        return this.audit_opinion;
    }

    public void setAudit_opinion(String audit_opinion) {
        this.audit_opinion = audit_opinion;
    }

    public String getCheck_flag() {
        return this.check_flag;
    }

    public void setCheck_flag(String check_flag) {
        this.check_flag = check_flag;
    }

    public String getChecker() {
        return this.checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheck_date() {
        return this.check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getCheck_opinion() {
        return this.check_opinion;
    }

    public void setCheck_opinion(String check_opinion) {
        this.check_opinion = check_opinion;
    }

    public byte[] getTarget_content() {
        return this.target_content;
    }

    public void setTarget_content(byte[] target_content) {
        this.target_content = target_content;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
            return this.current_year;
        } else if ("current_month".equalsIgnoreCase(name) == true) {
            return this.current_month;
        } else if ("current_work".equalsIgnoreCase(name) == true) {
            return this.current_work;
        } else if ("target_type".equalsIgnoreCase(name) == true) {
            return this.target_type;
        } else if ("dept_code".equalsIgnoreCase(name) == true) {
            return this.dept_code;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("auditor".equalsIgnoreCase(name) == true) {
            return this.auditor;
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
            return this.audit_date;
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
            return this.audit_flag;
        } else if ("audit_opinion".equalsIgnoreCase(name) == true) {
            return this.audit_opinion;
        } else if ("check_flag".equalsIgnoreCase(name) == true) {
            return this.check_flag;
        } else if ("checker".equalsIgnoreCase(name) == true) {
            return this.checker;
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            return this.check_date;
        } else if ("check_opinion".equalsIgnoreCase(name) == true) {
            return this.check_opinion;
        } else if ("target_content".equalsIgnoreCase(name) == true) {
            return "";
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("current_year".equalsIgnoreCase(name) == true) {
            this.current_year = value;
        } else if ("current_month".equalsIgnoreCase(name) == true) {
            this.current_month = value;
        } else if ("current_work".equalsIgnoreCase(name) == true) {
            this.current_work = value;
        } else if ("target_type".equalsIgnoreCase(name) == true) {
            this.target_type = value;
        } else if ("dept_code".equalsIgnoreCase(name) == true) {
            this.dept_code = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("auditor".equalsIgnoreCase(name) == true) {
            this.auditor = value;
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
            this.audit_date = value;
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
            this.audit_flag = value;
        } else if ("audit_opinion".equalsIgnoreCase(name) == true) {
            this.audit_opinion = value;
        } else if ("check_flag".equalsIgnoreCase(name) == true) {
            this.check_flag = value;
        } else if ("checker".equalsIgnoreCase(name) == true) {
            this.checker = value;
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            this.check_date = value;
        } else if ("check_opinion".equalsIgnoreCase(name) == true) {
            this.check_opinion = value;
        } else if ("target_content".equalsIgnoreCase(name) == true) {
            this.target_content = Codes.decode(value.toCharArray());
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
        } else if ("audit_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("audit_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("check_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("target_content".equalsIgnoreCase(name) == true) {
            colType = "bytes";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "DO_TARGET_MANAGE";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "current_year,current_month,current_work,target_type,dept_code,user_code,create_date,auditor,audit_date,audit_flag,audit_opinion,check_flag,checker,check_date,check_opinion,target_content,";
    }

    public String getAllFields() {
        return "serial_num,current_year,current_month,current_work,target_type,dept_code,user_code,create_date,auditor,audit_date,audit_flag,audit_opinion,check_flag,checker,check_date,check_opinion,target_content,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.current_year = values[current_year_col];
        this.current_month = values[current_month_col];
        this.current_work = values[current_work_col];
        this.target_type = values[target_type_col];
        this.dept_code = values[dept_code_col];
        this.user_code = values[user_code_col];
        this.create_date = values[create_date_col];
        this.auditor = values[auditor_col];
        this.audit_date = values[audit_date_col];
        this.audit_flag = values[audit_flag_col];
        this.audit_opinion = values[audit_opinion_col];
        this.check_flag = values[check_flag_col];
        this.checker = values[checker_col];
        this.check_date = values[check_date_col];
        this.check_opinion = values[check_opinion_col];
        this.target_content = Codes.decode(values[target_content_col].toCharArray());
    }

    public void setOtherProperty(String[] values) {
    	if(!StringUtils.isNull(this.create_date)&& this.create_date.length()>10){
    		this.create_date=this.create_date.substring(0,10);
    	}
    	if(!StringUtils.isNull(this.audit_date)&& this.audit_date.length()>10){
    		this.audit_date=this.audit_date.substring(0,10);
    	}
    	if(!StringUtils.isNull(this.check_date)&& this.check_date.length()>10){
    		this.check_date=this.check_date.substring(0,10);
    	}
        try {
            int targetEditDeleteFlag = 0;
            ConstantSet cs = new ConstantSet();
            String curUserID = ConstantSet.CurUserID; //ȡ�õ�ǰ�û�
            targetEditDeleteFlag = cs.judgeTargetEditDeleteTag(curUserID,this.user_code,this.check_flag,this.checker,this.audit_flag,this.auditor);
            if (targetEditDeleteFlag == 1) { //������޸�ɾ��
                this.edit_delete_flag = "1";
            } else if (targetEditDeleteFlag == 0) {
                this.edit_delete_flag = "0";
            }
            OrganizationUtil systemMngUtil = new OrganizationUtil();
            if (!"".equals(this.user_code) && this.user_code != null) {
                this.dept_name = systemMngUtil.getDepartmentNamesByUser(Integer.parseInt(this.user_code));
            }
            if (!"".equals(this.audit_flag) && !"".equals(this.check_flag)) {
                this.audit_check_name = ConstantSet.getAuditCheckName(this.audit_flag, this.check_flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAudit_check_name() {
        return audit_check_name;
    }

    public void setAudit_check_name(String audit_check_name) {
        this.audit_check_name = audit_check_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getEdit_delete_flag() {
        return edit_delete_flag;
    }

    public void setEdit_delete_flag(String edit_delete_flag) {
        this.edit_delete_flag = edit_delete_flag;
    }
}
