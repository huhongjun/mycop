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
 * <p>Title: 邮件管理接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MailMgrDAO {

    /**
     * 发送邮件
     * @param vo 当前填写邮件的信息
     * @param attchList 附件列表
     * @param actionType 动作类型
     * @return 发送后的信息
     * @throws DefaultException
     */
    public Map sendMail(MailVO vo, List attchList , int actionType) throws DefaultException;

    /**
     * 查询收件夹
     * @param curMailDir 当前邮件夹
     * @param mailType 邮件类型(0所有,1是内部,2是外部)
     * @return 邮件列表
     * @throws DefaultException
     */
    public List queryByAll(String curMailDir ,int mailType) throws DefaultException;

    /**
     * 查询邮件统计数
     * @param vo 当前邮件夹
     * @return 统计数
     * @throws DefaultException
     */
    public long queryByCount(VOInterface vo) throws DefaultException;
    /**
     *
     * @param vo 当前邮件vo对象
     * @param start 开始
     * @param howMany 查询多少笔
     * @return 当前页的邮件列表
     * @throws DefaultException
     */
    public List queryByPage(VOInterface vo, long start, long howMany) throws
         DefaultException;

    /**
     *
     * @param isInnerMail 是否为内部邮件
     * @return 当前邮件对象
     * @throws DefaultException
     */
    public MailVO queryMailCount(boolean isInnerMail) throws DefaultException;

    /**
     * 按主键查询邮件
     * @param vo 当前邮件对象
     * @return 当前邮件对象
     * @throws DefaultException
     */
    public MailVO queryByPk(MailVO vo)throws DefaultException;

    /**
     * 打开邮件
     * @param vo 当前邮件对象
     * @return 附件列表
     * @throws DefaultException
     */
    public List openMail(MailVO vo)throws DefaultException;

    /**
     * 搬移邮件
     * @param mailId 当前选择的邮件
     * @param curMailDir 当前邮件夹
     * @return 搬移成功多少笔
     * @throws DefaultException
     */
    public int moveMails(String[] mailId, String curMailDir)throws DefaultException;

    /**
     * 删除邮件到垃圾桶当中
     * @param mailId 当前选择的邮件
     * @return 是否搬移成功
     * @throws DefaultException
     */
    public int deleteMails(String[] mailId)throws DefaultException;

    /**
    * 真正删除邮件
    * @param mailId 当前选择的邮件
    * @return 是否搬移成功
    * @throws DefaultException
    */
    public int removeMails(String[] mailId, String realPath)throws DefaultException;

    /**
     * 还原邮件
     * @param mailId 当前选择的邮件
     * @return 还原多少封邮件
     * @throws DefaultException
     */
    public int revertMails(String[] mailId)throws DefaultException;

    /**
     * 还原所有邮件
     * @param mailId 当前选择的邮件
     * @return 还原多少封邮件
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
