package com.gever.goa.dailyoffice.targetmng.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * Title: ���Ŀ��Dao�ӿ�
 * Description: ���Ŀ��Dao�ӿ�
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public interface YearTargetDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException; //ɾ��

    public String getPageSql(); //��ҳ

    public int insert(VOInterface vo) throws DefaultException; //����

    public int update(VOInterface vo) throws DefaultException; //�޸�

    public List queryAll(VOInterface vo) throws DefaultException; //��ѯ����

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException; //��ҳ��ѯ

    public long queryByCount(VOInterface vo) throws DefaultException; //������ѯ

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //����

    public int judgeDelTag(String curUser, String user_code) throws DefaultException;
    //�жϵ�ǰ���Ƿ�ӵ��ɾ��Ȩ��--ֻ�д����˲���ɾ��Ȩ��
    public int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException;
    //�жϵ�ǰ���Ƿ�ӵ���޸�Ȩ��--ֻ�д����˲����޸�Ȩ��,����Ҫ��֤û��ͨ�����
    public int judgeAuditTag(String curUser, String auditor, String audit_flag, String check_flag) throws DefaultException;
    //�жϵ�ǰ���Ƿ�ӵ�����Ȩ��--ֻ�е�ǰ��Ϊ�����ʱ�ž������Ȩ�ޣ�����Ҫ��֤��˺�������û��ͨ��
    public int judgeCheckTag(String curUser, String checker, String audit_flag, String check_flag) throws DefaultException;
    //�жϵ�ǰ���Ƿ�ӵ������Ȩ��--ֻ�е�ǰ��Ϊ������ʱ�ž�������Ȩ�ޣ�����Ҫ��֤�����ͨ��������û��ͨ��
    public String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException;
    //ͨ����ǰ��¼����˺���������״̬����ʾ�������״̬������
}
