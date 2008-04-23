package com.gever.goa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gever.exception.DefaultException;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.struts.action.BaseAction;
import com.gever.util.StringUtils;

/**
 * <p>Title: �������õ��ĳ��ñ���ͨ�÷�����</p>
 * <p>Description: �������õ��ĳ��ñ���ͨ�÷�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public final class ConstantSet extends BaseDAO {
    public ConstantSet() {
    }

    //��ȡ��ǰ�û�ID-��Ҫ������ʱ���б�ҳ���ȡ��ǰ�û����ж���
    public static String CurUserID = "";

    //Ŀ�����
    public final static String YearTargetType = "4";
    public final static String YearSumType = "5";
    public final static String MonthTargetType = "2";
    public final static String MonthSumType = "3";
    public final static String WeekTargetType = "1";

    //����Ŀ��
    public final static String PersonYearTargetType = "3";
    public final static String PersonMonthTargetType = "2";
    public final static String PersonWeekTargetType = "1";

    //�����̶�����ģ���Ӧnodeid
    public final static int WorkLogTemplate = 1;
    public final static int PersonWeekTargetTemplate = 2;
    public final static int PersonMonthTargetTemplate = 3;
    public final static int PersonYearTargetTemplate = 4;
    public final static int WeekTargetTemplate = 5;
    public final static int MonthTargetTemplate = 6;
    public final static int MonthSumTemplate = 7;
    public final static int YearTargetTemplate = 8;
    public final static int YearSumTemplate = 9;
    public final static int FiveYearLayoutTemplate = 10;
    public final static int WorkReportTemplate = 11;
    public final static int ReportTargetTemplate=15;

    /**
     * �õ������õ���Ӧ��Ŀ������
     * @param targetType String
     * @return String
     * add by lihaidong
     */

    public static String judgeTargetType(String targetType){
        String tempTargetType="";
        if(targetType.equalsIgnoreCase(WeekTargetType)){
            tempTargetType=WeekTargetType;
        }else if(targetType.equalsIgnoreCase(MonthTargetType)){
            tempTargetType=MonthSumType;
        }else if(targetType.equalsIgnoreCase(MonthSumType)){
            tempTargetType=MonthSumType;
        }else if(targetType.equalsIgnoreCase(YearTargetType)){
            tempTargetType=YearTargetType;
        }else if(targetType.equalsIgnoreCase(YearSumType)){
            tempTargetType=YearSumType;
        }
        return tempTargetType;
    }

    /**
     * ͨ����ǰ��¼����˺���������״̬����ʾ�������״̬������
     * @param audit_flag ���״̬
     * @param check_flag ����״̬
     * @return String ���ܵ��������״��
     * @throws DefaultException
     */
    public static String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException {
        String retAuditCheckName = "";
        //�������
        //audit_flag-----0��ʾδ���,1��ʾ��ͨ�����,2��ʾδͨ�����
        //check_flag-----0��ʾδ����,1��ʾ��ͨ������,2��ʾδͨ������
        //�������ͨ�������޸�,������ͨ��������˿����޸�
        int audit_check_flag = 0;
        if ("0".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 0; //δ���
        }
        if ("1".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 1; //���ͨ��δ����
        }
        if ("2".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 2; //���δͨ��
        }
        if ("1".equals(audit_flag) && "1".equals(check_flag)) {
            audit_check_flag = 3; //����ͨ��
        }
        if ("1".equals(audit_flag) && "2".equals(check_flag)) {
            audit_check_flag = 4; //����δͨ��
        }
        switch (audit_check_flag) {
            case 0:
                retAuditCheckName = "δ���";
                break;
            case 1:
                retAuditCheckName = "���ͨ��δ����";
                break;
            case 2:
                retAuditCheckName = "���δͨ��";
                break;
            case 3:
                retAuditCheckName = "����ͨ��";
                break;
            case 4:
                retAuditCheckName = "����δͨ��";
                break;
            default:
                retAuditCheckName = "";
                break;
        }
        return retAuditCheckName;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ��ɾ��Ȩ��--ֻ�д����˲���ɾ��Ȩ��
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @return int 1----ӵ��ɾ��Ȩ��--0--��ӵ��ɾ��Ȩ��
     * @throws DefaultException
     */
    public static int judgeDelTag(String curUser, String user_code) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ���޸�Ȩ��--ֻ�д����˲����޸�Ȩ��,����Ҫ��֤û��ͨ�����
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @param audit_flag ���״̬
     * @return int 1----ӵ���޸�Ȩ��--0--��ӵ���޸�Ȩ��
     * @throws DefaultException
     */
    public static int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code) && "0".equals(audit_flag) || "2".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ�����Ȩ��--ֻ�е�ǰ��Ϊ�����ʱ�ž������Ȩ�ޣ�����Ҫ��֤��˺�������û��ͨ��
     * @param curUser ��ǰ�û�ID
     * @param auditor �����ID
     * @param audit_flag ���״̬
     * @param check_flag ����״̬
     * @return int 1----ӵ�����Ȩ��--0--��ӵ�����Ȩ��
     * @throws DefaultException
     */
    public static int judgeAuditTag(String curUser, String auditor, String audit_flag, String check_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(auditor) && "0".equals(audit_flag) && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ������Ȩ��--ֻ�е�ǰ��Ϊ������ʱ�ž�������Ȩ�ޣ�����Ҫ��֤�����ͨ��������û��ͨ��
     * @param curUser ��ǰ�û�ID
     * @param checker ������ID
     * @param audit_flag ���״̬
     * @param check_flag ����״̬
     * @return int 1----ӵ�����Ȩ��--0--��ӵ�����Ȩ��
     * @throws DefaultException
     */
    public static int judgeCheckTag(String curUser, String checker, String audit_flag, String check_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(checker) && "1".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ����׼Ȩ��--ֻ�е�ǰ��Ϊ��׼��ʱ�ž�����׼Ȩ�ޣ�����Ҫ��֤��׼��û�п�ʼ
     * @param curUser ��ǰ�û�ID
     * @param approve ��׼��ID
     * @param approve_flag ��׼״̬
     * @return int 1----ӵ����׼Ȩ��--0--��ӵ����׼Ȩ��
     * @throws DefaultException
     */
    public static int judgeApproveTag(String curUser, String approve, String approve_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(approve) && "0".equals(approve_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ��Ǵ�����--ֻ�д����˲����޸ĺ�ɾ��Ȩ��-����ֻ���ڸ���Ŀ����-�������
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @return int 1----ӵ���޸�ɾ��Ȩ��--0--��ӵ���޸�ɾ��Ȩ��
     * @throws DefaultException
     */
    public static int judgeCreatorTag(String curUser, String user_code,String auditor,String audit_flag) throws DefaultException {
        int tag = 0;
        if ((curUser.equals(user_code)||curUser.equals(auditor))&&!audit_flag.equals("1")) {
            tag = 1;
        }
        return tag;
    }
    /**
     * �ж����ܣ����£�����Ŀ�����Ȩ��
     * @param curUser String
     * @param auditor String
     * @param audit_flag String
     * @return 0 ��ʾû�����Ȩ�ޣ�1��ʾ�����Ȩ��
     */
    public static int judgeAuditFlag(String curUser,String auditor,String audit_flag){
        int auditFlag=0;
        if(curUser.equals(auditor)&&!"1".equals(audit_flag)){
            auditFlag=1;
        }
        return auditFlag;
    }

    /**
     * ����Ŀ������д���˺����������������жϵ�ǰ���Ƿ�����ڲ鿴ҳ������ʾ�޸İ�ť
     * ���ⴴ���˲����޸�Ȩ��,����˺�������Ҳ�����޸�Ȩ��
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @return int 1----ӵ���޸�ɾ��Ȩ��--0--��ӵ���޸�ɾ��Ȩ��
     * @throws DefaultException
     */
    public static int judgeEditTagForView(String curUserID, String make_person, String check_flag, String checker,
                                          String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag)) {
            //�����ǰ�û����Ǵ����ˣ��������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ��������,������û��ͨ����������Ҳû��ͨ��,������˿��޸�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,������ͨ����,����û��ͨ���Ļ�,�������˿��޸�
            tag = 1;
        } else if (curUserID.equals(make_person) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ��������Ҳͨ���ˣ���������û��ͨ������ʱҲӦ�������û��޸�-����һ������
            //���û��Լ��ύ���Լ���˺���������һ�����������趨Ϊ����-���������Ļ���Ҫ������ѡ����ԱȨ��
            tag = 1;
        }

        return tag;
    }

    /**
     * ��Ϊ����ʱҪ��ı�Ϊ����Դ��鿴ҳ���ϵ��޸İ�ť
     * �ж������������ǰ���Ǵ����ˣ�����ʾ�޸İ�ť
     * �����ǰ�˲��Ǵ����ˣ����ж��Ƿ�Ϊ����˻�������
     * �����ǰ�˲��Ǵ����ˣ���������˻������ˣ�����������ʣ�����ʾ��˻�������ť
     * flag=0-------------��Ӧ��ť����ʾ
     * flag=1-------------��Ӧ�޸İ�ť
     * flag=2-------------��Ӧ��˰�ť
     * flag=3-------------��Ӧ������ť
     * ���ⴴ���˲����޸�Ȩ��,����˺�������Ҳ�����޸�Ȩ��
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @return int 3-----ӵ������Ȩ��----2----ӵ�����Ȩ��---1----ӵ���޸�Ȩ��--0--��ӵ���޸�Ȩ��
     * @throws DefaultException
     */
    public static int judgeEditTagForViewOfNew(String curUserID, String make_person, String check_flag, String checker,
                                               String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag) && !curUserID.equals(auditor)) {
            //�����ǰ�û����Ǵ����ˣ����ҵ�ǰ�û����������,�������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ��������,������û��ͨ����������Ҳû��ͨ��,������˿��޸�
            tag = 2;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,������ͨ����,����û��ͨ���Ļ�,�������˿��޸�
            tag = 3;
        } else if (curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag)) {
            //�����ǰ�û����Ǵ����ˣ������������,�������û��ͨ���ˣ���ʱҲӦ�������û����-����һ������
            //���û��Լ��ύ���Լ���˺���������һ�����������趨Ϊ����-���������Ļ���Ҫ������ѡ����ԱȨ��
            tag = 2;
        } else if (curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ�������������,�������Ҳͨ���ˣ���������û��ͨ������ʱӦ�������û�����-����һ������
            //���û��Լ��ύ���Լ���˺���������һ�����������趨Ϊ����-���������Ļ���Ҫ������ѡ����ԱȨ��
            tag = 3;
        }

        return tag;
    }
    /**
     * �ж��Ƿ�����޸�
     * @param curUser
     * @param make_person
     * @param auditor
     * @param checker
     * @param audit_flag
     * @return
     * @throws DefaultException
     */
    public static int  judgeModify(String curUser,String make_person,String auditor,String checker, String audit_flag,String check_flag) throws DefaultException{
        int modify_flag=0;
        if(curUser.equals(make_person)&&"0".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }else if(curUser.equals(auditor)&& "0".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }else if(curUser.equals(checker)&&"1".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }
        return modify_flag;
    }
    /**
     * �ж��Ƿ���ʾȡ����ˣ�ȡ��������ť
     * 1 ��ʾ ��ʾȡ����˰�ť��2��ʾ��ʾ������ť��0��ʾ����ʾ
     * @param curUser
     * @param auditor
     * @param checker
     * @param audit_flag
     * @param check_flag
     * @return
     */
    public static int judgeIsCancel(String curUser,String auditor,String checker,String audit_flag,String check_flag){
        int cancel_flag=0;
        if(curUser.equals(auditor)&&"1".equals(audit_flag)&&"0".equals(check_flag)){
            cancel_flag=1;
        }else if(curUser.equals(checker)&&"1".equals(audit_flag)&&"1".equals(check_flag)){
            cancel_flag=2;
        }
        /*else if(curUser.equals(auditor)&&curUser.equals(checker)&&"1".equals(check_flag)&&"1".equals(audit_flag)){
            cancel_flag=2;
        }else if(curUser.equals(auditor)&&curUser.equals(checker)&&"0".equals(check_flag)&&"1".equals(audit_flag)){
            cancel_flag=1;
        }*/
        return cancel_flag;
    }

    /**
     * ����Ŀ������д���˺����������������жϵ�ǰ���Ƿ�������޸��޸�ҳ������ʾ��ģ��
     * ���ⴴ���˲����޸�Ȩ��,����˺�������Ҳ�����޸�Ȩ��
     * ҵ������Ϊ����˺��������ǿ����޸��ύ������Ŀ��ģ���
     * ʵ�����Ҿ�������������ǲ����޸ģ���Ϊ����Ϊ���һ���Ļ�
     * ��ͬ��Ļ��ʹ��ȥ���ǲ���Ҫ�޸ĵ�-���ﻹ����Ϊ������Ҳ���޸�ģ��
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @param auditor �����
     * @param audit_flag ��˱�־
     * @param checker ������
     * @param check_flag ������־
     * @return int 1----ӵ���޸�ɾ��Ȩ��--0--��ӵ���޸�ɾ��Ȩ��
     * @throws DefaultException
     */
    public static int judgeReadOnlyTemplateTag(String curUserID, String user_code, String check_flag, String checker,
                                               String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(user_code)) {
            //�����ǰ�û����Ǵ����ˣ��������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(auditor)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ��������,������û��ͨ����������Ҳû��ͨ��,������˿��޸�
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(checker) && "1".equals(audit_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,������ͨ����,����û��ͨ���Ļ�,�������˿��޸�
            tag = 1;
        } else if (curUserID.equals(user_code) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ��������Ҳͨ���ˣ���������û��ͨ������ʱҲӦ�������û��޸�-����һ������
            //���û��Լ��ύ���Լ���˺���������һ�����������趨Ϊ����-���������Ļ���Ҫ������ѡ����ԱȨ��
            tag = 1;
        }

        return tag;
    }

    /**
     * ��������滮�д����������������жϵ�ǰ���Ƿ�������޸��޸�ҳ������ʾ��ģ��
     * ���ⴴ���˲����޸�Ȩ��,������Ҳ�����޸�Ȩ��
     * ҵ������Ϊ�������ǿ����޸��ύ������Ŀ��ģ���
     * ʵ�����Ҿ�������������ǲ����޸ģ���Ϊ����Ϊ���һ���Ļ�
     * ��ͬ��Ļ��ʹ��ȥ���ǲ���Ҫ�޸ĵ�-���ﻹ����Ϊ������Ҳ���޸�ģ��
     * @param curUser ��ǰ�û�
     * @param user_code ������
     * @param approve ������
     * @param approve_flag ������־
     * @return int 1----ӵ���޸�ɾ��Ȩ��--0--��ӵ���޸�ɾ��Ȩ��
     * @throws DefaultException
     */
    public static int judgeFiveYearLayoutReadOnlyTemplateTag(String curUserID, String user_code, String approve_flag,
        String approve) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(user_code)) {
            //�����ǰ�û����Ǵ����ˣ���������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(approve)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,�������û��ͨ���Ļ�,�������˿��޸�
            tag = 1;
        }
        return tag;
    }

    /**
     * �ж�Ŀ���Ƿ��Ѿ����������ݿ���
     * @param year--��ǰ��д�����
     * @param month--��ǰ��д���¶�
     * @param week--��ǰ��д����
     * @param type--��ǰ��Ŀ������
     * @param deptcode--��ǰ����д�����ڲ���
     * @param usercode--��ǰ��д��
     * @return String ���Ӧ���ַ���-1-���ݿ����Ѿ����ڸ���
     * @throws DefaultException
     */
    public String queryTargetIsExist(String year, String month, String week, String type, String deptcode,
                                     String usercode) throws
        Exception {
        String existFlag = "0";
        /*
        System.out.println("-----------The current year is---------"+year);
        System.out.println("-----------The current month is---------"+month);
        System.out.println("-----------The current week is-----------"+week);
        */

        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //SQLHelper helper = new DefaultSQLHelper(super.dbData);
        StringBuffer sbSql = new StringBuffer();
        BaseAction bAction=new BaseAction(); //��DB2��ORACLE�жԿձ�ʾ�Ĳ�һ��������Ҫ����BaseAction�еķ���
        sbSql.append("select SERIAL_NUM from DO_TARGET_MANAGE where ");
        if(StringUtils.isNull(year)){
          sbSql.append(bAction.getIsNull("CURRENT_YEAR"));
        }else{
          sbSql.append("CURRENT_YEAR="+ "'"+ year +"'");
        }
        if(StringUtils.isNull(month)){
          sbSql.append(" and ").append(bAction.getIsNull("CURRENT_MONTH"));
        }else{
          sbSql.append("and CURRENT_MONTH="+"'"+month+"'");
        }
        if(StringUtils.isNull(week)){
          sbSql.append(" and ").append(bAction.getIsNull("CURRENT_WORK"));
        }else{
          sbSql.append(" and CURRENT_WORK="+"'"+week+"'");
        }
        sbSql.append("and TARGET_TYPE='" + type + "' ");
        sbSql.append("and DEPT_CODE='" + deptcode + "' ");
        sbSql.append("and USER_CODE=" + Integer.parseInt(usercode) + " ");
        System.out.println("-------------The query sql is ---------"+sbSql.toString());
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                existFlag = "1"; //1--------���ڸ���
                //throw new DefaultException("���Ѿ���д�˸�������������ˣ���ѡ��༭���");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            //System.out.println("ConstantSet queryTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return existFlag;
    }

    /**
     * �ж�����滮�Ƿ����
     * @param curYear String
     * @param dept String
     * @return String
     */
    public String queryFiveYearsTargetIsExist(String curYear,String dept) throws
      DefaultException, SQLException {
       String existFlag="0";
       ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
           dbData);
       Connection conn = cp.getConnection();
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       //SQLHelper helper = new DefaultSQLHelper(super.dbData);
       StringBuffer sbSql = new StringBuffer();
       sbSql.append("select SERIAL_NUM from do_fiveyear_layout ");
       sbSql.append("CURRENT_YEAR=").append(curYear);
       rs=pstmt.executeQuery(sbSql.toString());
       if(rs.next()){
         existFlag="1";
       }
       rs.close();
       pstmt.close();
       conn.close();
       return existFlag;
     }




    /**
     * �жϸ���Ŀ���Ƿ��Ѿ����������ݿ���
     * @param year--��ǰ��д�����
     * @param month--��ǰ��д���¶�
     * @param week--��ǰ��д����
     * @param type--��ǰ��Ŀ������
     * @param deptcode--��ǰ����д�����ڲ���
     * @param usercode--��ǰ��д��
     * @return String ���Ӧ���ַ���-1-���ݿ����Ѿ����ڸ���
     * @throws DefaultException
     */
    public String queryPersonTargetIsExist(String year, String month, String week, String type, String deptcode,
                                           String usercode) throws
        Exception {

        String existFlag = "0";
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //SQLHelper helper = new DefaultSQLHelper(super.dbData);
        StringBuffer sbSql = new StringBuffer();
        sbSql.append("select SERIAL_NUM from DO_PERSONAL_TARGET where CURRENT_YEAR='" + year + "' ");
        sbSql.append("and CURRENT_MONTH='" + month + "' ");
        sbSql.append("and CURRENT_WORK='" + week + "' ");
        sbSql.append("and TARGET_TYPE=" + type + " ");
        sbSql.append("and DEPT_CODE='" + deptcode + "' ");
        sbSql.append("and USER_CODE=" + Integer.parseInt(usercode) + " ");
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                existFlag = "1"; //1--------���ڸ���
                //throw new DefaultException("���Ѿ���д�˸�������������ˣ���ѡ��༭���");
            }
        } catch (Exception e) {
           // System.out.println("ConstantSet queryPersonTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return existFlag;
    }

    /**
     * ���ڹ��Ĺ������жϵ�ǰ�����Ƿ���
     * @param serial_number ��ǰ����ϵ�к�
     * @return int 1----�Ѱ��--0--δ���
     * @throws DefaultException
     */
    public int judgeDocCheckTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT CHECK_FLAG FROM AM_SEND_MANAGE ");
        sbSql.append("WHERE CHECK_FLAG = 1 "); //����־Ϊ1
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }



    /**
     * ���ڹ��Ĺ������жϵ�ǰ�����Ƿ��ѵǼ�
     * @param serial_number ��ǰ����ϵ�к�
     * @return int 1----�ѵǼ�--0--δ�Ǽ�
     * @throws DefaultException
     */
    public int judgeDocRegTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT REGISTER_DATE FROM AM_SEND_MANAGE ");
        sbSql.append("WHERE REGISTER_DATE is not null "); //����Ǽ����ڴ���˵���ѵǼ�
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * ���ڹ��Ĺ������жϵ�ǰ�����Ƿ���
     * author ������
     * @param serial_number ��ǰ����ϵ�к�
     * @return int 1----�Ѱ��--0--δ���
     * @throws DefaultException
     */
    public int judgDocCheckTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT CHECK_FLAG FROM AM_RECEIVE_MANAGE ");
        sbSql.append("WHERE CHECK_FLAG = 1 "); //����־Ϊ1
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
               if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �������Ĺ������жϵ�ǰ�����Ƿ��ѵǼ�
     * author ������
     * @param serial_number ��ǰ����ϵ�к�
     * @return int 1----�ѵǼ�--0--δ�Ǽ�
     * @throws DefaultException
     */

    public int judgDocRegTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT REGISTER_DATE FROM AM_RECEIVE_MANAGE ");
        sbSql.append("WHERE REGISTER_DATE is not null "); //����Ǽ����ڴ���˵���ѵǼ�
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");

        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * ���ڹ��Ĺ������жϵ�ǰ�˶Ըù����Ƿ�����޸ĺ�ɾ��Ȩ��
     * �ж�����--δ���,�Ǵ������Լ���δ������ת
     * @param curUserID ��ǰ��½�û�ID��
     * @param make_person ��ǰ���Ĵ�����ID��
     * @param check_flag ��ǰ�����Ƿ����־-0-δ���
     * @param process_id ��ǰ�����Ƿ�������̵Ķ�Ӧ����ID
     * @return int 1----���޸�ɾ��--0--�����޸�ɾ��
     * @throws DefaultException
     */
    public int judgeDocEditDeleteTag(String curUserID, String make_person, String check_flag, String process_id) throws
        DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && "0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     * ����Ŀ��������жϵ�ǰ�˶Ըü�¼�Ƿ�����޸ĺ�ɾ��Ȩ��
     * �ж�����--��ǰ���Ǵ������Լ�,����δ������˻������δͨ��-check_flag��Ϊ1,
     * ������Ϊ���δͨ��Ҳ�����ô����������޸�
     * ��Ϊ���ｫ��˺������������޸�ҳ�洦��
     * ����������ȷ������һ�������Ա����һ�������Ա�ύ�����ļ�¼�����޸�Ȩ��
     * ���������������Ҳ�����޸�Ȩ��
     * ������ȷ�������������ͨ����,����˲����ٴ����
     * ������ȷ���������������ͨ����,�����˲����ٴ�����
     * @param curUserID ��ǰ��½�û�ID��
     * @param make_person ��ǰĿ���滮������ID��
     * @param check_flag ������־
     * @param checker ������
     * @param audit_flag ��˱�־
     * @param auditor �����
     * @return int 1----���޸�ɾ��--0--�����޸�ɾ��
     * @throws DefaultException
     */
    public static int judgeTargetEditDeleteTag(String curUserID, String make_person, String check_flag, String checker,
                                               String audit_flag, String auditor) throws
        DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag)) {
            //�����ǰ�û����Ǵ����ˣ��������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ��������,������û��ͨ����������Ҳû��ͨ��,������˿��޸�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,������ͨ����,����û��ͨ���Ļ�,�������˿��޸�
            tag = 1;
        } else if (curUserID.equals(make_person) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //�����ǰ�û����Ǵ����ˣ��������Ҳͨ���ˣ���������û��ͨ������ʱҲӦ�������û��޸�-����һ������
            //���û��Լ��ύ���Լ���˺���������һ�����������趨Ϊ����-���������Ļ���Ҫ������ѡ����ԱȨ��
            tag = 1;
        }
        return tag;
    }

    /**
     * ��������滮���жϵ�ǰ�˶Ը�����滮�Ƿ�����޸ĺ�ɾ��Ȩ��
     * @param curUserID ��ǰ��½�û�ID��
     * @param make_person ��ǰ����滮������ID��
     * @param approve_flag ��ǰ����滮�Ƿ�����ͨ����־-1-ͨ��-0-δͨ��
     * @param process_id ��ǰ�����Ƿ�������̵Ķ�Ӧ����ID
     * @return int 1----���޸�ɾ��--0--�����޸�ɾ��
     * @throws DefaultException
     */

    public static int judgeFiveYearLayoutEditDeleteTag(String curUserID, String make_person, String approve_flag,
        String approver) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(approve_flag)) {
            //�����ǰ�û����Ǵ����ˣ���������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,�������û��ͨ��,�������˿��޸�
            tag = 1;
        }
        return tag;
    }
    /**
     *
     * @param curUser
     * @param make_person
     * @param approver
     * @param approv_flag
     * @return
     * @throws DefalutException
     */
    public static int judgeFiveYearModifyFlag(String curUser,String make_person,String approver,String approve_flag)throws DefaultException{
        int modify_flag=0;
        if(curUser.equals(make_person)&&"0".equals(approve_flag)&&!curUser.equals(approver)){
            modify_flag=1;
        }else if(!curUser.equals(make_person)&& curUser.equals(approver)&&"0".equals(approve_flag)){
            modify_flag=1;
        }else if(curUser.equals(make_person)&&curUser.equals(approver)&&"0".equals(approve_flag)){
            modify_flag=1;
        }
        return modify_flag;
    }
    /**
     * �ж�����滮�У��û��Ƿ�ȡ������
     * 2 ��ʾȡ������
     * @param curUser
     * @param approver
     * @param approve_flag
     * @return
     */
    public static int judgeFiveYearIsCancel(String curUser,String approver,String approve_flag){
        int isCancel_flag=0;
        if(curUser.equals(approver)&&"1".equals(approve_flag)){
            isCancel_flag=2;
        }
        return isCancel_flag;
    }

    /**
     * ��������滮�鿴���жϵ�ǰ�˶Ը�����滮��ʾ�޸Ļ���������ť
     * @param curUserID ��ǰ��½�û�ID��
     * @param make_person ��ǰ����滮������ID��
     * @param approve_flag ��ǰ����滮�Ƿ�����ͨ����־-1-ͨ��-0-δͨ��
     * @param process_id ��ǰ�����Ƿ�������̵Ķ�Ӧ����ID
     * @return int 2---������------1----���޸�ɾ��--0--�����޸�ɾ��
     * @throws DefaultException
     */

    public static int judgeFiveYearLayoutEditDeleteTagOfNew(String curUserID, String make_person, String approve_flag,
        String approver) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //�����ǰ�û����Ǵ����ˣ�������������,��������û��ͨ������ô��ǰ���ǿ����޸ĵ�
            tag = 1;//---��ʾ�޸İ�ť
        } else if (!curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //�����ǰ�û����Ǵ����ˣ���ô��Ҫ�жϵ�ǰ���Ƿ���������,�������û��ͨ��,�������˿��޸�
            tag = 2;//---��ʾ������ť
        } else if (curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //�����ǰ�û����Ǵ����ˣ����ҵ�ǰ��Ҳ�������ˣ���������û��ͨ��,��ô��ǰ��������Ȩ��
            tag = 2;//---��ʾ������ť
        }
        return tag;
    }

    /**
     * ���ڹ��Ĺ������жϵ�ǰ�˶Ըù����Ƿ�����޸ĺ�ɾ��Ȩ��
     * author:������
     * �ж�����--δ���,�Ǵ������Լ���δ������ת
     * @param curUserID ��ǰ��½�û�ID��
     * @param make_person ��ǰ���Ĵ�����ID��
     * @param check_flag ��ǰ�����Ƿ����־-0-δ���
     * @param process_id ��ǰ�����Ƿ�������̵Ķ�Ӧ����ID
     * @return int 1----���޸�ɾ��--0--�����޸�ɾ��
     * @throws DefaultException
     */

    public int judgDocEditDeleteTag(String check_flag, String process_id) throws DefaultException {
        int tag = 0;
        if ("0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     *
     * @param curUserID String�ж��ǹ����ύ�������޸�
     * @param user_code String
     * @param approve_flag String
     * @param approve String
     * @throws DefaultException������
     * @return int
     */
    public int judgeReceiveView(String check_flag, String process_id) throws DefaultException {
        int tag = 0;
        if ("0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �ӵ�λ��Ϣ���л�ȡ��λ����-�������Զֻ��һ����¼
     * @return ��λ����
     * @throws Exception
     */
    public String getUnitName() throws Exception {
        String unitName = "";
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("select UNIT_NAME from SYS_UNIT_INFO ");
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                unitName = rs.getString("UNIT_NAME");
            }
        } catch (Exception e) {
           // System.out.println("ConstantSet queryPersonTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return unitName;
    }

}
