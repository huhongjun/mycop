package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �ռ���ӿ�</p>
 * <p>Description: ����ɾ��,��ѯ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsInBoxDAO {
    /**
     * ɾ�����е�����
     * @param aryPk ��������
     * @param vo ����vo
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int delBySelect(String[] aryPk,VOInterface vo) throws
        DefaultException;

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
     *
     *
     * @return �������������б�
     * @throws DefaultException
     */

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * ͳ�Ƶ�ǰ������
     * @param vo ��ǰvo����
     * @return ͳ������
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    public int updateReadFlag(VOInterface vo) throws DefaultException;
}