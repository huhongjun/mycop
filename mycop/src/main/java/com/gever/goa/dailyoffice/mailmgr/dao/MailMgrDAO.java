package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;


/**
 * <p>Title: �ʼ�����ӿ�</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailMgrDAO {

    /**
     * �����ʼ�
     * @param vo ��ǰ��д�ʼ�����Ϣ
     * @param attchList �����б�
     * @param actionType ��������
     * @return ���ͺ����Ϣ
     * @throws DefaultException
     */
    public Map sendMail(MailVO vo, List attchList , int actionType) throws DefaultException;

    /**
     * ��ѯ�ռ���
     * @param curMailDir ��ǰ�ʼ���
     * @param mailType �ʼ�����(0����,1���ڲ�,2���ⲿ)
     * @return �ʼ��б�
     * @throws DefaultException
     */
    public List queryByAll(String curMailDir ,int mailType) throws DefaultException;

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
     *
     * @param isInnerMail �Ƿ�Ϊ�ڲ��ʼ�
     * @return ��ǰ�ʼ�����
     * @throws DefaultException
     */
    public MailVO queryMailCount(boolean isInnerMail) throws DefaultException;

    /**
     * ��������ѯ�ʼ�
     * @param vo ��ǰ�ʼ�����
     * @return ��ǰ�ʼ�����
     * @throws DefaultException
     */
    public MailVO queryByPk(MailVO vo)throws DefaultException;

    /**
     * ���ʼ�
     * @param vo ��ǰ�ʼ�����
     * @return �����б�
     * @throws DefaultException
     */
    public List openMail(MailVO vo)throws DefaultException;

    /**
     * �����ʼ�
     * @param mailId ��ǰѡ����ʼ�
     * @param curMailDir ��ǰ�ʼ���
     * @return ���Ƴɹ����ٱ�
     * @throws DefaultException
     */
    public int moveMails(String[] mailId, String curMailDir)throws DefaultException;

    /**
     * ɾ���ʼ�������Ͱ����
     * @param mailId ��ǰѡ����ʼ�
     * @return �Ƿ���Ƴɹ�
     * @throws DefaultException
     */
    public int deleteMails(String[] mailId)throws DefaultException;

    /**
    * ����ɾ���ʼ�
    * @param mailId ��ǰѡ����ʼ�
    * @return �Ƿ���Ƴɹ�
    * @throws DefaultException
    */
    public int removeMails(String[] mailId, String realPath)throws DefaultException;

    /**
     * ��ԭ�ʼ�
     * @param mailId ��ǰѡ����ʼ�
     * @return ��ԭ���ٷ��ʼ�
     * @throws DefaultException
     */
    public int revertMails(String[] mailId)throws DefaultException;

    /**
     * ��ԭ�����ʼ�
     * @param mailId ��ǰѡ����ʼ�
     * @return ��ԭ���ٷ��ʼ�
     * @throws DefaultException
     */
    public int revertAllMails(String userId) throws DefaultException;

    public int removeAllGarbageMails(String userId,String realPath) throws DefaultException;

    public MailVO getMailinfoByPk(String serialNum) throws DefaultException;

    public List setAttachListFromSender(MailVO mailVo) throws DefaultException;

    public MailVO getReplyMailInfoByActionType(String serialNum,int ActionType) throws DefaultException;

    public MailVO getReplyMailInfoByActionType(int ActionType, MailVO mailVo) throws DefaultException;

    public boolean isSavaOwnFileOutOfCapacity (MailVO mailVO,MailErrorInfoVO errorVO) throws DefaultException;

    public boolean insertMail(MailVO mailVO, String userId, SQLHelper helper) throws DefaultException;

    public void setAttachList(List attachList);

    public void setPageNumber(String pageNumber);

    public List queryUnReadMailByUser(String userId) throws DefaultException;

    public void setPop3ConfigVo(Pop3ConfigVO pop3ConfigVo);

    public void setServerPath(String serverPath);

    public List queryUnReadMailByUsers(String userIds) throws DefaultException ;

}
