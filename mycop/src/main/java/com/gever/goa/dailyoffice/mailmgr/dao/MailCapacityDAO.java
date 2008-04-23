package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �������</p>
 * <p>Description: �������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailCapacityDAO {
    /**
     * ��ѯ�ռ���
     * @param curMailDir ��ǰ�ʼ���
     * @param mailType �ʼ�����(0����,1���ڲ�,2���ⲿ)
     * @return �ʼ��б�
     * @throws DefaultException
     */
    public List queryByAll(String curMailDir, int mailType) throws
        DefaultException;

    /**
     * ��ѯ�ʼ�ͳ����
     * @param vo ��ǰ�ʼ���
     * @return ͳ����
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     *
     * @param vo ��ǰ�ʼ�vo����
     * @param start ��ʼ
     * @param howMany ��ѯ���ٱ�
     * @return ��ǰҳ���ʼ��б�
     * @throws DefaultException
     */
    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;


    /**
     * ��Ĭ�ϵ��ֽ�
     * @return ��Ĭ�ϵ��ֽ�
     */
    public double getDefaultSize();

    /**
     * �޸�ѡ����ٱ�����
     * @param dblSize ����
     * @param aryUserId ѡ���û�
     * @return �޸Ķ��ٱ�
     * @throws DefaultException
     */
    public int update(double dblSize ,String[] aryUserId)throws DefaultException;

    /**
     * �޸�����
     * @param dblSize ����
     * @return �޸Ķ��ٱ�
     * @throws DefaultException
     */
    public int setAllCapacity(double dblSize,String deptId) throws DefaultException;

    public boolean isOutOfCapacity(MailCapacityVO vo, long mailSize) throws DefaultException;
    public List getMailCapacityByUser(String userIds) throws DefaultException;
    public void setStrWhere(String strWhere);
    public List getMailCapacity() throws DefaultException;
    /**add by lihaidong
     * �������������Ϣ
     * @param vo
     * @return
     * @throws DefaultException
     */
    public int multiInsert(VOInterface vo) throws DefaultException;
    /**
     * �����û����õ�������Ϣ
     * @param vo
     * @return
     * @throws DefaultException
     */
    public List getCapactiryByUser(String userName) throws DefaultException;
}
