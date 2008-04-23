package com.gever.goa.dailyoffice.mailmgr.dao;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �ʼ�����</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailInfoDAO {

    /**
     * ����
     * @param vo ��ǰ�ʼ�����vo����
     * @return ��ǰ�ʼ�����vo����
     */
    public int insert(VOInterface vo) throws DefaultException;

    /**
     * ����
     * @param vo ��ǰ�ʼ�����vo����
     * @return ��ǰ�ʼ�����vo����
     */
    public int update(VOInterface vo) throws DefaultException;

    /**
     * ��Ѱ
     * @param vo ��ǰ�ʼ�����vo����
     * @return ��ǰ�ʼ�����vo����
     */

    public VOInterface queryByPK(VOInterface vo) throws DefaultException;

    public MailInfoVO getMailInfoByUserId(String userId) throws DefaultException;

    public long queryByCount(VOInterface searchVO) throws DefaultException;


}
