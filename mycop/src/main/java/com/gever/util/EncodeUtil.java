package com.gever.util;

import java.lang.String;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: 多语言字元字符转换类</p>
 * <p>Description: 支持如下几种类别的互相转换：UNICODE/GBK/native）</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class EncodeUtil {

    //字符集常量定义
    static public final String GBK = "GB2312";
    static public final String UNICODE = "UTF-8";

    /**
     * 将中文字符串转换成 Unicode 字符串
     * @param n　中文字符串
     * @return　Unicode字符串
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
     * 将 Unicode 字符串转换成中文字符串
     * @param u　Unicode字符串
     * @return　中文字符串
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
     * 将 Unicode 字符串转换为 GBK 字符串
     * @param u Unicode源字符串
     * @return　成功：GBK字符串　失败：原输入字符串
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
     * 将 GBK 字符转换为 Unicode 字符
     * @param gbk　GBK源字符串
     * @return　成功：Unicode字符串　失败：原输入字符串
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