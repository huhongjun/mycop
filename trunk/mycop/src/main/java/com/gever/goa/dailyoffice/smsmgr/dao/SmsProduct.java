package com.gever.goa.dailyoffice.smsmgr.dao;

import com.gever.exception.DefaultException;
import java.util.List;

/**
 * <p>Title: ���Ų�Ʒ�ӿ�</p>
 * <p>Description: ���� ����,�ر�,����,��ʱ����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsProduct {
    /**
     * ���Ͷ���Ϣ
     * @param arySms �跢���ֻ��Ķ���
     * @return ����ѶϢ�б�
     * @throws GeneralException
     */
    public String sendSms(List arySms) throws DefaultException;

    /**
     * ���ն���Ϣ
     * @return ���ն��ŵ��б�
     * @throws GeneralException
     */
    public boolean inceptSms() throws DefaultException;

    /**
     * ���ն���Ϣ
     * @return ���ն��ŵ��б�
     * @throws GeneralException
     */
    public void sendingSms() throws DefaultException;

    /**
     * ��ʼ���ֻ�ͨѶ����
     * @return �ɹ����
     */
    public boolean initConnection();

    /**
     * �ر��ֻ�ͨѶ����
     * @return �ɹ����
     */

    public boolean close();

    /**
     * �õ����ʹ�����б�
     * @return ����ѶϢ�б�
     * @throws GeneralException
     */
    public List getErrorResult() throws DefaultException;

}