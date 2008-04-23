package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.targetmng.dao.YearTargetDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: ���Ŀ��Dao�ӿ�ʵ���� Description: ���Ŀ��Dao�ӿ�ʵ���� Copyright: Copyright (c) 2004
 * Company: �������
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class YearTargetDaoImpl
    extends BaseDAO
    implements YearTargetDao {
    private static String QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
        + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
        + "audit_flag,audit_opinion,check_flag,"
        + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
        + "a left join t_user b on a.user_code=b.id where 1=1 ";

    //QUERY_SQL--���巭ҳʱ�õĲ�ѯ���
    public YearTargetDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    public void setOracleSQL() {
        QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
            + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
            + "audit_flag,audit_opinion,check_flag,"
            + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
            + "a, t_user b  where a.user_code=b.id(+) ";
    }

    /**
     *����BaseDAO�и��µķ���
     * @param vo VOInterface
     * @throws DefaultException
     * @return int
     *  add by lihaidong
     */
    public int update(VOInterface vo) throws DefaultException {
        if (!super.isOracle()) {
            System.out.println("start db2 sql....");
            return super.update(vo);
        }
        System.out.println("start oracle sql ....");
        int row = 0;
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        try {
            helper.begin();
            //System.out.println("The serial_num is :"+ vo.getValue("serial_Num"));
            helper.setAutoID(false);
            helper.delete(vo);
            row = helper.insert(vo);
            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
            throw new DefaultException("���³���");
        }
        finally {
            helper.end();
        }
        return row;

    }

    /**
     * ��˲���
     *
     * @param isPass
     *            �Ƿ�ͨ�����-0-δͨ�����-1-��ͨ�����
     * @param audit_date
     *            ���ʱ��
     * @throws DefaultException
     */
    /*
     * public void doCheck(String isPass,String audit_date) throws
     * DefaultException {
     * System.out.println("-----------------------doCheck--------------");
     * SQLHelper helper = new DefaultSQLHelper(dbData); //StringBuffer sbSql =
     * new StringBuffer(); String sql = ""; String shzt =
     * "0";//���״̬��0��ʾδ���,1��ʾ��ͨ�����,2��ʾδͨ����� if("1".equals(isPass)){//��������ͨ�� shzt =
     * "1";//�����״̬��Ϊ1 }else if("0".equals(isPass)){//�������˲�ͨ�� shzt =
     * "2";//�����״̬��Ϊ2 } sql = "UPDATE TARGETMAIN SET AUDIT_FLAG=?,AUDIT_DATE=?
     * WHERE SERIAL_NUM=? "; //sbSql.append("UPDATE TARGETMAIN SET
     * SPZT=?,SHSJ=?,SHR=? WHERE BH=? "); }
     */
    /**
     * �жϵ�ǰ���Ƿ�ӵ��ɾ��Ȩ��--ֻ�д����˲���ɾ��Ȩ��
     *
     * @param curUser
     *            ��ǰ�û�
     * @param user_code
     *            ������
     * @return int 1----ӵ��ɾ��Ȩ��--0--��ӵ��ɾ��Ȩ��
     * @throws DefaultException
     */
    public int judgeDelTag(String curUser, String user_code) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ���޸�Ȩ��--ֻ�д����˲����޸�Ȩ��,����Ҫ��֤û��ͨ�����
     *
     * @param curUser
     *            ��ǰ�û�
     * @param user_code
     *            ������
     * @param audit_flag
     *            ���״̬
     * @return int 1----ӵ���޸�Ȩ��--0--��ӵ���޸�Ȩ��
     * @throws DefaultException
     */
    public int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code) && "0".equals(audit_flag)
            || "2".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ�����Ȩ��--ֻ�е�ǰ��Ϊ�����ʱ�ž������Ȩ�ޣ�����Ҫ��֤��˺�������û��ͨ��
     *
     * @param curUser
     *            ��ǰ�û�ID
     * @param auditor
     *            �����ID
     * @param audit_flag
     *            ���״̬
     * @param check_flag
     *            ����״̬
     * @return int 1----ӵ�����Ȩ��--0--��ӵ�����Ȩ��
     * @throws DefaultException
     */
    public int judgeAuditTag(String curUser, String auditor, String audit_flag,
                             String check_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(auditor) && "0".equals(audit_flag)
            && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * �жϵ�ǰ���Ƿ�ӵ������Ȩ��--ֻ�е�ǰ��Ϊ������ʱ�ž�������Ȩ�ޣ�����Ҫ��֤�����ͨ��������û��ͨ��
     *
     * @param curUser
     *            ��ǰ�û�ID
     * @param checker
     *            ������ID
     * @param audit_flag
     *            ���״̬
     * @param check_flag
     *            ����״̬
     * @return int 1----ӵ�����Ȩ��--0--��ӵ�����Ȩ��
     * @throws DefaultException
     */
    public int judgeCheckTag(String curUser, String checker, String audit_flag,
                             String check_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(checker) && "1".equals(audit_flag)
            && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * ͨ����ǰ��¼����˺���������״̬����ʾ�������״̬������ ע�⣺�÷��������Ƶ���ConstantSet����ȥʵ����
     *
     * @param audit_flag
     *            ���״̬
     * @param check_flag
     *            ����״̬ConstantSet
     * @return String ���ܵ��������״��
     * @throws DefaultException
     */
    public String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException {
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

}
