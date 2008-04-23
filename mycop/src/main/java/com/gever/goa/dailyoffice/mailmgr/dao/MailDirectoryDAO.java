package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailDirectoryDAO {
    /**
      * �����ʼ���
      * ���������з������������׳��쳣������������
      * @param vo ��ǰ�ʼ�����vo����
      * @return ��ǰ�ʼ�����vo����
      */
     public int insert(VOInterface vo) throws DefaultException;

     /**
      * �޸��ʼ���
      * �޸Ĺ����з������������׳��쳣���޸Ĳ�����
      * @param vo ��ǰ�ʼ�����vo����
      * @return ��ǰ�ʼ�����vo����
      */
     public int update(VOInterface vo) throws DefaultException;

     /**
      * ����������Ѱ�ʼ���
      * @param vo ��ǰ�ʼ�����vo����
      * @return ��ǰ�ʼ�����vo����
      */

     public VOInterface queryByPK(VOInterface vo) throws DefaultException;

     /**
      * ��ѯ�����ʼ��ʼ���
      * @param vo ��ǰ�ʼ���
      * @return ��ѯ�����ʼ�
      * @throws DefaultException
      */

     public List queryAll(VOInterface vo) throws DefaultException;

     /**
      * ��ѯ�ʼ�ͳ����
      * @param vo ��ǰ�ʼ���
      * @return ͳ����
      * @throws DefaultException
      */
     public long queryByCount(VOInterface vo) throws DefaultException;

     public List queryAllMailDir(String userId) throws DefaultException;

     public MailDirectoryVO queryMailDirByUserAndDirId(String userId,
         String mailDirId) throws DefaultException;

     public List queryAllExceptCurrentDir(String mailDirId, String userID)
         throws DefaultException;
     public List queryDirectoryByUserId(String userId) throws DefaultException;

     public MailDirectoryVO queryBySerialNum(String serialNum) throws DefaultException;

}
