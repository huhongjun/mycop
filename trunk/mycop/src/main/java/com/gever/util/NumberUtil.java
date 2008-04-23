package com.gever.util;

import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * <p>Title: 字符数据类型转换类</p>
 * <p>Description: 提供将字符型数据转换成其它数据类型功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Gever Tech</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 将字符型数据转换成整型数据
     * @param strData　需要被转换的字符串
     * @param defaultValue　转换失败时的返回值
     * @return　成功：整型转换结果　失败：defaultValue
     */
    public static int stringToInt(String strData, int defaultValue) {
        int i = defaultValue;
        if (strData == null || strData.equals("")) {
            i = defaultValue;
        } else {
            try {
                i = Integer.parseInt(strData);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                return defaultValue;
            }
        }

        return i;
    }

    /**
     * 将字符型数据转换成整型数据
     * @param s　需要被转换的字符串
     * @return　成功：整型转换结果　失败：0
     */
    public static int stringToInt(String strData) {
        return NumberUtil.stringToInt(strData, 0);
    }

    /**
     * 将字符型数据转换成长整型数据
     * @param strData　需要被转换的字符串
     * @param defaultValue　转换失败时的返回值
     * @return　成功：长整型转换结果　失败：defaultValue
     */
    public static long stringToLong(String strData, long defaultValue) {
        long l = defaultValue;
        if (strData == null || strData.equals("")) {
            return defaultValue;
        } else {
            try {
                l = Long.parseLong(strData);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                return defaultValue;
            }
        }
        return l;
    }

    /**
     * 将字符型数据转换成长整型数据
     * @param s　需要被转换的字符串
     * @return　成功：长整型转换结果　失败：0
     */
    public static long stringToLong(String strData) {
        return NumberUtil.stringToLong(strData, 0);
    }

    /**
     * 将字符型数据转换成浮点型数据
     * @param s　需要被转换的字符串
     * @param defaultValue　转换失败时的返回值
     * @return　成功：浮点型转换结果　失败：defaultValue
     */
    public static float stringToFloat(String strData, float defaultValue) {
        float f = defaultValue;
        if (strData == null || strData.equals("")) {
            f = defaultValue;
        } else {
            try {
                f = Float.parseFloat(strData);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                return defaultValue;
            }
        }
        return f;
    }

    /**
     * 将字符型数据转换成浮点型数据
     * @param s　需要被转换的字符串
     * @return　成功：浮点型转换结果　失败：0
     */
    public static float stringToFloat(String strData) {
        return NumberUtil.stringToFloat(strData, 0);
    }

    /**
     * 将字符型数据转换成双精度类型数据
     * @param strData　需要被转换的字符串
     * @param defaultValue　转换失败时的返回值
     * @return　成功：双精度类型转换结果　失败：defaultValue
     */
    public static double stringToDouble(String strData, double defaultValue) {
        double d = defaultValue;
        if (strData == null || strData.equals("")) {
            d = defaultValue;
        } else {
            try {
                d = Double.parseDouble(strData);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                return defaultValue;
            }
        }
        return d;
    }

    /**
     * 将字符型数据转换成双精度类型数据
     * @param s　需要被转换的字符串
     * @return　成功：双精度类型转换结果　失败：0
     */
    public static double stringToDouble(String strData) {
        return NumberUtil.stringToDouble(strData, 0);
    }

    /**
     * 把"2003-08-08"格式的日期转化成Calandar
     * @param s String类型的日期
     * @return 成功：转换结果Calendar对象　失败：null
     */
    public static Calendar stringToCalendar(String strDate) {
        if (strDate == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(strDate, new ParsePosition(0)));
            return c;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * 把"2003-08-08"格式的日期转化成Date
     * @param s String类型的日期
     * @return 成功：转换结果Date对象　失败：null
     */
    public static Date stringToDate(String strDate) {
        if (strDate == null)
            return null;

        StringTokenizer st = new StringTokenizer(strDate, "-");
        Date d = null;
        try {
            if (st.countTokens() == 3) {
                int year = Integer.parseInt(st.nextToken()) - 1900;
                int month = Integer.parseInt(st.nextToken()) - 1;
                int day = Integer.parseInt(st.nextToken());
                d = new Date(year, month, day);
            }
        } catch (NoSuchElementException e) {
            //if there are no more tokens in this tokenizer's string.
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            return d;
        }
    }
}