package com.gever.goa.dailyoffice.smsmgr.dao;

import com.gever.exception.DefaultException;
import java.util.List;

/**
 * <p>Title: 短信产品接口</p>
 * <p>Description: 包括 连接,关闭,发送,定时发送</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface SmsProduct {
    /**
     * 发送短消息
     * @param arySms 需发送手机的队列
     * @return 错误讯息列表
     * @throws GeneralException
     */
    public String sendSms(List arySms) throws DefaultException;

    /**
     * 接收短消息
     * @return 接收短信的列表
     * @throws GeneralException
     */
    public boolean inceptSms() throws DefaultException;

    /**
     * 接收短消息
     * @return 接收短信的列表
     * @throws GeneralException
     */
    public void sendingSms() throws DefaultException;

    /**
     * 初始化手机通讯连接
     * @return 成功与否
     */
    public boolean initConnection();

    /**
     * 关闭手机通讯连接
     * @return 成功与否
     */

    public boolean close();

    /**
     * 得到发送错误的列表
     * @return 错误讯息列表
     * @throws GeneralException
     */
    public List getErrorResult() throws DefaultException;

}