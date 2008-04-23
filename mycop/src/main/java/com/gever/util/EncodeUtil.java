package com.gever.util;

import java.lang.String;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: ��������Ԫ�ַ�ת����</p>
 * <p>Description: ֧�����¼������Ļ���ת����UNICODE/GBK/native��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class EncodeUtil {

    //�ַ�����������
    static public final String GBK = "GB2312";
    static public final String UNICODE = "UTF-8";

    /**
     * �������ַ���ת���� Unicode �ַ���
     * @param n�������ַ���
     * @return��Unicode�ַ���
     */
    public static String nativeToUnicode(String n) {
        String returnValue = null;
        try {
            if (n != null) {
                returnValue = java.net.URLEncoder.encode(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return returnValue;
        }
        return returnValue;
    }

    /**
     * �� Unicode �ַ���ת���������ַ���
     * @param u��Unicode�ַ���
     * @return�������ַ���
     */
    public static String unicodeToNative(String u) {
        String n = null;
        try {
            if (u != null) {
                n = java.net.URLDecoder.decode(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return u;
        }
        return n;
    }

    /**
     * �� Unicode �ַ���ת��Ϊ GBK �ַ���
     * @param u UnicodeԴ�ַ���
     * @return���ɹ���GBK�ַ�����ʧ�ܣ�ԭ�����ַ���
     */
    public static String unicodeToGBK(String u) {
        String g = null;
        if (u != null) {
            try {
                byte[] bytes = u.getBytes(UNICODE);
                g = new String(bytes, GBK);
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
                return u;
            }
        }
        return g;
    }

    /**
     * �� GBK �ַ�ת��Ϊ Unicode �ַ�
     * @param gbk��GBKԴ�ַ���
     * @return���ɹ���Unicode�ַ�����ʧ�ܣ�ԭ�����ַ���
     */
    public static String GBKToUnicode(String gbk) {
        String unicode = null;
        if (gbk != null) {
            try {
                byte[] bytes = gbk.getBytes(GBK);
                unicode = new String(bytes, UNICODE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return gbk;
            }
        }
        return unicode;
    }

}