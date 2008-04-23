package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ���Ź�����</p>
 * <p>Description:��������,�޸�,ɾ��,��ѯ�ȶ��� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsMgrDAO {
    /**
     * ɾ����ѡ������
     * @param idValues ��������
     * @param types ������
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */

    public int delBySelect(String[] idValues, String[] types) throws DefaultException;
    /**
     * �õ���ҳ��sql���
     * @return sql���
     */
    public String getPageSql();

    /**
     * ��ѯ����
     * @param vo ��ǰvo����
     * @return �������������б�
     * @throws DefaultException
     */

    public List queryAll(VOInterface vo) throws DefaultException;

    /**
     * ��ҳ��ѯ
     * @param vo ��ǰvo����
     * @param start ��ʼ
     * @param howMany ���ٱ�����
     * @return �������������б�
     * @throws DefaultException
     */

    public List queryByPage(  VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * ͳ�Ƶ�ǰ������
     * @param vo ��ǰvo����
     * @return ͳ������
     * @throws DefaultException
     */
     public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     * �������ű�
     * @param msgList ��Ϣ�б�
     * @return  �����ɹ����ٱ�
     * @throws DefaultException
     */
    public int insert(List msgList) throws DefaultException;

    /**
     * ������ʱ���ű�
     * @param msgList ��Ϣ�б�
     * @return  �����ɹ����ٱ�
     * @throws DefaultException
     */
    public int insertTiming(List msgList) throws DefaultException;
    /**
     * �õ���ǰ�û��ķ��͵��������
     * @param strUserId ��ǰ�û�
     * @param strWhere �������
     * @return ���ͳ����Ϣ
     * @throws DefaultException
     */
    public Map getCurUserSendNumber(String strUserId, String strWhere) throws
        DefaultException ;
    /**
     * ��userid,�����ǰ���е���Ƭ��Ϣ
     * @param userId �û���
     * @return ��Ƭ�б�
     * @throws DefaultException
     */
    public List queryCardByUserID(String userId) throws DefaultException;

    /**
     * @param userId �û���
     * ��userid,�����ǰ���е���Ƭ��Ϣ
     * @return ��Ƭ���б�
     * @throws DefaultException
     */
    public List queryCardTypeByUserID(String userId) throws DefaultException;

    /**
     * ��ѯ��Ա
     * @return ��Ա�б�
     * @throws DefaultException
     */
    public List querySmsUsers() throws DefaultException ;

    /**
     * ��������ѯ
     * @param id ����id
     * @param type ������
     * @return vo����
     * @throws DefaultException
     */
    public OutBoxVO queryByPk(String id, String type) throws DefaultException;

}