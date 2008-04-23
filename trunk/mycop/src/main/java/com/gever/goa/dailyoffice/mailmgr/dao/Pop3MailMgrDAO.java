package com.gever.goa.dailyoffice.mailmgr.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;

/**
 * <p>Title:外部邮件管理</p>
 * <p>Description: 外部邮件管理</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface Pop3MailMgrDAO {

    /**
     * 连接远程邮件服务器
     * @return 成功与否,true:为成功,false为失败
     */
    public boolean mailConnection(); //连接邮件服务器

    /**
     * 存储邮件到服务器
     * @return 成功与否
     */
    public boolean saveMails(); //存储邮件

    /**
     * 发送邮件
     * @param vo 邮件表映射类
     * @param aryAttch 附件细项清单
     * @return 成功与否
     */
    public String sendMails(MailVO vo, List aryAttch); //发送邮件

    /**
     * 下载服务器邮件接收邮件
     * @param aryList 所有的pop3的帐号
     * @return 成功与否,true为真,false为假
     */
    public String downLoadEmails(List aryList); //下载服务器邮件

    /**
     * 删除远程服务器的邮件
     * @param aryList pop3邮件帐号清单
     * @return 成功与否 :true为真,false为假
     */
    public List deleteMails(List aryList); //删除远程服务器的邮件

    /**
     * 统计远程服务器的邮件个数
     * @param aryList pop3邮件帐号清单
     * @return 成功与否 :true为真,false为假
     */
    public List countEmails(List aryList); //得到邮件处理数

    public void setServerPath(String serverPath);

    public String sendMails(HashMap mailMap, MailVO info, ArrayList aryList);

}
