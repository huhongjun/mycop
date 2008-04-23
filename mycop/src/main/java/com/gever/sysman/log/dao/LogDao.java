package com.gever.sysman.log.dao;

import java.util.List;
import com.gever.vo.VOInterface;
import com.gever.exception.DefaultException;

/**
 * <p>Title: LogDao�ӿ�</p>
 * <p>Description: ��Ҫ�в�ѯ,ɾ��,��������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public interface LogDao {

    /**
     * ����Ľӿ�
     * @param username �û�����
     * @param module ģ����
     * @param action ����
     * @param ipAddress ip��ַ
     * @param memo ��ע
     * @param level �㼶
     * @param type ����
     * @return ���������ɹ���¼��,-1Ϊʧ��
     * @throws DefaultException
     */
    public int addLog(String username, String module, String action,
                      String ipAddress, String memo,int level, int type)  throws DefaultException;


    /**
     * ��ҳ��������ѯ
     * @param searchVo ��ѯʱ�õ�VO����
     * @param start ��ʼ���
     * @param end �������
     * @return ��¼������
     * @throws DefaultException
     */
    public List queryByPage(VOInterface searchVo, long start , long end)  throws DefaultException;
    /**
     * ͳ�Ƴ���ǰ��������
     * @param searchVo ��ѯʱ�õ�VO����
     * @return ��¼����
     */
    public long queryByCount(VOInterface searchVo) ;
    /**
     * ��ѯ���е�����
     * @return ��¼������
     * @throws DefaultException
     */
    public List queryAll()  throws DefaultException;

    /**
     * ɾ�����е�����
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
     public int delBySelect(String[] aryPk) throws DefaultException ;

    /**
     * ɾ������������
     * @param searchVo ��ѯʱ�õ�VO����
     * @return ɾ���ɹ����ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int delByCond(VOInterface searchVo) throws DefaultException;

    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s) ;
    //===============================================================
}