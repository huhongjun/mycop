package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;

/**
 * <p>Title:�ⲿ�ʼ�����</p>
 * <p>Description: �ⲿ�ʼ�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Pop3MailMgrDAO {

    /**
     * ����Զ���ʼ�������
     * @return �ɹ����,true:Ϊ�ɹ�,falseΪʧ��
     */
    public boolean mailConnection(); //�����ʼ�������

    /**
     * �洢�ʼ���������
     * @return �ɹ����
     */
    public boolean saveMails(); //�洢�ʼ�

    /**
     * �����ʼ�
     * @param vo �ʼ���ӳ����
     * @param aryAttch ����ϸ���嵥
     * @return �ɹ����
     */
    public String sendMails(MailVO vo, List aryAttch); //�����ʼ�

    /**
     * ���ط������ʼ������ʼ�
     * @param aryList ���е�pop3���ʺ�
     * @return �ɹ����,trueΪ��,falseΪ��
     */
    public String downLoadEmails(List aryList); //���ط������ʼ�

    /**
     * ɾ��Զ�̷��������ʼ�
     * @param aryList pop3�ʼ��ʺ��嵥
     * @return �ɹ���� :trueΪ��,falseΪ��
     */
    public List deleteMails(List aryList); //ɾ��Զ�̷��������ʼ�

    /**
     * ͳ��Զ�̷��������ʼ�����
     * @param aryList pop3�ʼ��ʺ��嵥
     * @return �ɹ���� :trueΪ��,falseΪ��
     */
    public List countEmails(List aryList); //�õ��ʼ�������

    public void setServerPath(String serverPath);

    public String sendMails(HashMap mailMap, MailVO info, ArrayList aryList);

}
