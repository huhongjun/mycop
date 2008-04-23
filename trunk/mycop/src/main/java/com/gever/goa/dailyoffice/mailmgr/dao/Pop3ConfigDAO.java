package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title: </p>
 * <p>Description:Pop3 DAO�ӿ� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Pop3ConfigDAO {
    /**
     * ����
     * @param vo ��ǰPOP3�ʼ�vo����
     * @return ���������
     */
    public int insert(VOInterface vo) throws DefaultException;

    /**
     * ����
     * @param vo ��ǰPOP3�ʼ�vo����
     * @return ���µ�����
     */
    public int update(VOInterface vo) throws DefaultException;
    /**
     * ɾ��
     * @param vo ��ǰPOP3�ʼ�vo����
     * @return  ɾ��������
     * @throws DefaultException
     */

    public int delete(VOInterface vo) throws DefaultException;

    /**
     * ��Ѱ
     * @param vo ��ǰPOP3�ʼ�vo����
     * @return ��ǰPOP3�ʼ�vo����
     */

    public VOInterface queryByPK(VOInterface vo) throws DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public List getPop3ConfigByUserId(String userId) throws DefaultException;

    public List queryListByPK(String[] serialNums) throws DefaultException;

    public Pop3ConfigVO queryPop3ConfigInfoByPK(String serialNum) throws DefaultException;
}
