package com.gever.goa.dailyoffice.mailmgr.dao;

/**
 * <p>Title: �ʼ�������</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailConstant {

    //ѡ���ʼ�������
    public final static int INNER_MAIL = 1;      //�Ƿ�Ϊ�ڲ��ʼ�
    public final static int POP3_MAIL = 2;       //pop3Ϊ�ʼ�
    public final static int SAVE_POST_MAIL = 4;  //�Ƿ񱣴浽�ļ���
    public final static int RETO_ME_MAIL = 8;    //�Ƿ�ظ��ʼ�����
    public final static int RE_MAIL = 16;        //�Ƿ�Ϊ�ظ��ʼ�
    public final static int FW_MAIL = 32;       //�Ƿ�Ϊת���ʼ�
    public final static int ADD_SIGN = 128;       //�Ƿ���Ӹ���ǩ��
    public final static int SAVE_ONLY_MAIL = 256;  //�Ƿ�Ϊ����ݸ�
    public final static int RE_MAIL_WITH_ORIGINAL_MAIL = 512;//�ظ��ʼ��Ƿ񸽴�ԭ��
    public final static int RE_MAIL_TO_ALL = 1024;//�Ƿ�������˻���

    //��������

    public final static int ALL_TYPE = 0;    //�����ʼ�����
    public final static int INNER_TYPE = 1;  //�ڲ��ʼ�
    public final static int POP3_TYPE = 2;   //�ⲿ�ʼ�

    //�ⲿ�ʼ���һЩ����
    public final static int DELETE_ACTION = 1; //ɾ������
    public final static int INSERT_ACTION = 2; //��������
    public final static int SAVE_ACTION = 3;   //��������
    public final static int COUNT_ACTION = 4;  //ͳ�ƶ���

    //�ʼ�·��
    public static final String MAIL_PATH = "\\dailyoffice\\mailmgr\\mailbox\\mail_";

    //�����ʼ���
    public static final String DIR_RECIEVE_FOLDER = "0";
    public static final String DIR_SENDING_FOLDER = "1";
    public static final String DIR_DRAFT_FOLDER = "2";
    public static final String DIR_GARBAGE_FOLDER = "3";

    //�ʼ���������
    public final static String NORMAL_MAIL = "0";//һ���ʼ�
    public final static String URNGENCE_MAIL = "1";//�����ʼ�
    public final static String VERY_NORMAL_MAIL = "2";//����ͨ�ʼ�

}
