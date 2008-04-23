package com.gever.goa.dailyoffice.mailmgr.dao;

/**
 * <p>Title: 邮件常量类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailConstant {

    //选择邮件的类型
    public final static int INNER_MAIL = 1;      //是否为内部邮件
    public final static int POP3_MAIL = 2;       //pop3为邮件
    public final static int SAVE_POST_MAIL = 4;  //是否保存到寄件夹
    public final static int RETO_ME_MAIL = 8;    //是否回复邮件给我
    public final static int RE_MAIL = 16;        //是否为回复邮件
    public final static int FW_MAIL = 32;       //是否为转发邮件
    public final static int ADD_SIGN = 128;       //是否添加个人签名
    public final static int SAVE_ONLY_MAIL = 256;  //是否为保存草稿
    public final static int RE_MAIL_WITH_ORIGINAL_MAIL = 512;//回复邮件是否附带原文
    public final static int RE_MAIL_TO_ALL = 1024;//是否给所有人回信

    //发送类型

    public final static int ALL_TYPE = 0;    //所有邮件类型
    public final static int INNER_TYPE = 1;  //内部邮件
    public final static int POP3_TYPE = 2;   //外部邮件

    //外部邮件的一些参数
    public final static int DELETE_ACTION = 1; //删除动作
    public final static int INSERT_ACTION = 2; //新增动作
    public final static int SAVE_ACTION = 3;   //新增动作
    public final static int COUNT_ACTION = 4;  //统计动作

    //邮件路径
    public static final String MAIL_PATH = "\\dailyoffice\\mailmgr\\mailbox\\mail_";

    //基础邮件夹
    public static final String DIR_RECIEVE_FOLDER = "0";
    public static final String DIR_SENDING_FOLDER = "1";
    public static final String DIR_DRAFT_FOLDER = "2";
    public static final String DIR_GARBAGE_FOLDER = "3";

    //邮件紧急级别
    public final static String NORMAL_MAIL = "0";//一般邮件
    public final static String URNGENCE_MAIL = "1";//紧急邮件
    public final static String VERY_NORMAL_MAIL = "2";//最普通邮件

}
