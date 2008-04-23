package com.gever.goa.dailyoffice.smsmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �ֻ����Ź���</p>
 * <p>Description:�ֻ����Ź��� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SMSCapacityDAO {

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

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;

    /**
     * ͳ�Ƶ�ǰ������
     * @param vo ��ǰvo����
     * @return ͳ������
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;

    /**
     * �õ�Ĭ��
     * @return Ĭ�ϵ�VO
     * @throws DefaultException
     */
    public SMSCapacityVO queryDefaultCapacity() throws DefaultException;


    /**
     * ���������˵Ķ�������
     * @param smsNumber ��������
     * @return �޸ĳɹ����ٱ�
     * @throws DefaultException
     */
    public int updateAll(int smsNumber,int type) throws DefaultException;

    /*
     * ����ѡ����Ա�Ķ�������:ע���ж�ʱ�̷��͵Ĺ���
     * @param aryData ѡ�������
     * @param smsNumber ��������
     * @return �޸ĳɹ����ٱ�
     * @throws DefaultException
     */
    public int update(String[] aryUser, String[] timing, int smsNumber,
                      int type) throws     DefaultException;

    /**
     * ����Ա���������������
     * @param userid �û�id
     * @return ����Ա���������������
     * @throws DefaultException
     */
    public SMSCapacityVO queryCapacityByUserId(String userid) throws
        DefaultException;

    public List getTreeData(String nodeid) throws DefaultException ;

    /**
    * �޸Ĺ�˾����
    * @param vo ��ǰvo����
    * @return �ɹ����¶��ٱ�,-1Ϊʧ��
    * @throws DefaultException
    */
   public int updateCorpName(VOInterface vo) throws DefaultException;
   /**
     * �õ���ǰ��˾����
     * @return ��˾����
     * @throws DefaultException
     */
    public String queryCorpName() throws DefaultException ;


}