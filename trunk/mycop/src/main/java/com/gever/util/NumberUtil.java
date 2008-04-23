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
 * <p>Title: �ַ���������ת����</p>
 * <p>Description: �ṩ���ַ�������ת���������������͹���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Gever Tech</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class NumberUtil {

    private NumberUtil() {
    }

    /**
     * ���ַ�������ת������������
     * @param strData����Ҫ��ת�����ַ���
     * @param defaultValue��ת��ʧ��ʱ�ķ���ֵ
     * @return���ɹ�������ת�������ʧ�ܣ�defaultValue
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
     * ���ַ�������ת������������
     * @param s����Ҫ��ת�����ַ���
     * @return���ɹ�������ת�������ʧ�ܣ�0
     */
    public static int stringToInt(String strData) {
        return NumberUtil.stringToInt(strData, 0);
    }

    /**
     * ���ַ�������ת���ɳ���������
     * @param strData����Ҫ��ת�����ַ���
     * @param defaultValue��ת��ʧ��ʱ�ķ���ֵ
     * @return���ɹ���������ת�������ʧ�ܣ�defaultValue
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
     * ���ַ�������ת���ɳ���������
     * @param s����Ҫ��ת�����ַ���
     * @return���ɹ���������ת�������ʧ�ܣ�0
     */
    public static long stringToLong(String strData) {
        return NumberUtil.stringToLong(strData, 0);
    }

    /**
     * ���ַ�������ת���ɸ���������
     * @param s����Ҫ��ת�����ַ���
     * @param defaultValue��ת��ʧ��ʱ�ķ���ֵ
     * @return���ɹ���������ת�������ʧ�ܣ�defaultValue
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
     * ���ַ�������ת���ɸ���������
     * @param s����Ҫ��ת�����ַ���
     * @return���ɹ���������ת�������ʧ�ܣ�0
     */
    public static float stringToFloat(String strData) {
        return NumberUtil.stringToFloat(strData, 0);
    }

    /**
     * ���ַ�������ת����˫������������
     * @param strData����Ҫ��ת�����ַ���
     * @param defaultValue��ת��ʧ��ʱ�ķ���ֵ
     * @return���ɹ���˫��������ת�������ʧ�ܣ�defaultValue
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
     * ���ַ�������ת����˫������������
     * @param s����Ҫ��ת�����ַ���
     * @return���ɹ���˫��������ת�������ʧ�ܣ�0
     */
    public static double stringToDouble(String strData) {
        return NumberUtil.stringToDouble(strData, 0);
    }

    /**
     * ��"2003-08-08"��ʽ������ת����Calandar
     * @param s String���͵�����
     * @return �ɹ���ת�����Calendar����ʧ�ܣ�null
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
     * ��"2003-08-08"��ʽ������ת����Date
     * @param s String���͵�����
     * @return �ɹ���ת�����Date����ʧ�ܣ�null
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