package com.gever.util;

import java.sql.Timestamp;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import com.gever.util.NumberUtil;

/**
 * <p>Title: ���ڲ�����</p>
 * <p>Description: �ṩ���ڡ�ʱ�䡢���ڵķֽ���ϼ��Ӽ����㹦��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE��OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DateUtil {

    //���Գ�������
    private static final String ENGLISH = "english";
    private static final String CHINESE = "chinese";

    //���ڳ�������
    private static final String ESUNDAY = "Sunday";
    private static final String CSUNDAY = "������";
    private static final String EMONDAY = "Monday";
    private static final String CMONDAY = "����һ";
    private static final String ETUESDAY = "Tuesday";
    private static final String CTUESDAY = "���ڶ�";
    private static final String EWEDNESDAY = "Wednesday";
    private static final String CWEDNESDAY = "������";
    private static final String ETHURSDAY = "Thursday";
    private static final String CTHURSDAY = "������";
    private static final String EFRIDAY = "Friday";
    private static final String CFRIDAY = "������";
    private static final String ESATURDAY = "Saturday";
    private static final String CSATURDAY = "������";
    private static final String EDEFAULT = "Dimness";
    private static final String CDEFAULT = "����";

    private DateUtil() {
    }

    /**
     * �õ���ǰ�������ڵ�����»���
     * @param strDate �ַ��������ڣ���ʽ��2004-05-20��
     * @param intFlag int ����(0������,1������,2������)
     * @return int �ɹ��������յ�����ֵ��ʧ�ܣ�-1
     */
    private static int getNumberOfDate(String strDate, int intFlag) {
        StringTokenizer st = new StringTokenizer(strDate, "-");
        //�ݴ���
        if (st.countTokens() != 3) {
            return 0;
        }
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        if (intFlag == 0) {
            return year;
        } else if (intFlag == 1) {
            return month;
        } else if (intFlag == 2) {
            return day;
        } else {
            return -1;
        }
    }

    /**
     * ��õ�ǰ�ַ������͵�����ʱ��ֵ����ʽ��2004-05-20 00:00:00��
     * @return������ʱ��ֵ
     */
    public static String getCurrentDateTime() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(0, 19);
    }

    /**
     * ��õ�ǰ�ַ������͵�����ֵ����ʽ��2004-05-20��
     * @return������ֵ
     */
    public static String getCurrentDate() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(0, 10);
    }

    /**
     * ��õ�ǰ�ַ������͵�ʱ��ֵ
     * @return��ʱ��ֵ����ʽΪ"00:00:00"��
     */
    public static String getCurrentTime() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(11, 19);
    }

    /**
     * �����ĳ����������������
     * @param intWeek����������ֵ
     * @param language���������ͣ�Ŀǰֻ֧�����ĺ�Ӣ�ģ�
     * @return����ĳ����������������
     */
    private static String selectWeek(int intWeek, String language) {
        String week = CDEFAULT;

        switch (intWeek) {
            case 1:
                if (language.equals(ENGLISH)) {
                    week = EMONDAY;
                } else if (language.equals(CHINESE)) {
                    week = CMONDAY;
                }
                break;
            case 2:
                if (language.equals(ENGLISH)) {
                    week = ETUESDAY;
                } else if (language.equals(CHINESE)) {
                    week = CTUESDAY;
                }
                break;
            case 3:
                if (language.equals(ENGLISH)) {
                    week = EWEDNESDAY;
                } else if (language.equals(CHINESE)) {
                    week = CWEDNESDAY;
                }
                break;
            case 4:
                if (language.equals(ENGLISH)) {
                    week = ETHURSDAY;
                } else if (language.equals(CHINESE)) {
                    week = CTHURSDAY;
                }
                break;
            case 5:
                if (language.equals(ENGLISH)) {
                    week = EFRIDAY;
                } else if (language.equals(CHINESE)) {
                    week = CFRIDAY;
                }
                break;
            case 6:
                if (language.equals(ENGLISH)) {
                    week = ESATURDAY;
                } else if (language.equals(CHINESE)) {
                    week = CSATURDAY;
                }
                break;
            case 0:
                if (language.equals(ENGLISH)) {
                    week = ESUNDAY;
                } else if (language.equals(CHINESE)) {
                    week = CSUNDAY;
                }
                break;
            default:
                if (language.equals(ENGLISH)) {
                    week = EDEFAULT;
                } else if (language.equals(CHINESE)) {
                    week = CDEFAULT;
                }
        }

        return week;
    }

    /**
     * ��ø������ڵ�����
     * @param calendar�����и������ڵ�Calendar����
     * @return���ɹ����������ڡ�ʧ�ܣ�-1
     */
    public static int getWeek(Calendar calendar) {
        if (calendar == null)
            return -1;

        return calendar.get(calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * ��ø������ڵ���������
     * @param calendar�����и������ڵ�Calendar����
     * @return���ɹ����������ڡ�ʧ�ܣ�null
     */
    public static String getChineseWeek(Calendar calendar) {
        if (calendar == null)
            return null;

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * ��ø������ڵ�Ӣ������
     * @param calendar�����и������ڵ�Calendar����
     * @return��Ӣ������
     */
    public static String getEnglishWeek(Calendar calendar) {
        if (calendar == null)
            return null;

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * ��õ�ǰ����
     * @return����������
     */
    public static String getCurrentWeek() {
        int week = -1;
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        week = c.get(c.DAY_OF_WEEK) - 1;
        return (String.valueOf(week));
    }

    /**
     * ��õ�ǰ��������
     * @return����������
     */
    public static String getCurrentChineseWeek() {
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * ��õ�ǰӢ������
     * @return��Ӣ������
     */
    public static String getCurrentEnglishWeek() {
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * ʵ���������ӹ���
     * @param strDate���������ڣ���ʽ��2004-05-20��
     * @param dateNumber����������ֵ
     * @return���ɹ������Ӻ������ֵ����ʽ��2004-05-20����ʧ�ܣ�null
     */
    public static String getDateSum(String strDate, int dateNumber) {
        Calendar c = null;
        try {
            c = NumberUtil.stringToCalendar(strDate);
            if (c == null)
                return null;

            c.add(c.DATE, dateNumber);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            StringBuffer sumDate = new StringBuffer();
            sdf.format(c.getTime(), sumDate, new FieldPosition(0));
            return sumDate.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ʵ�����ڼ��ٹ���
     * @param strDate���������ڣ���ʽ��2004-05-20��
     * @param dateNumber�����ڼ���ֵ
     * @return���ɹ������ٺ������ֵ����ʽ��2004-05-20����ʧ�ܣ�null
     */
    public static String getDateSubtract(String strDate, int dateNumber) {
        try {
            dateNumber = Integer.parseInt("-" + String.valueOf(dateNumber));
            return DateTimeUtils.getDateSum(strDate, dateNumber);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * ʵ�������������
     * @param strBeginDate����Ϊ�����������ڣ���ʽ��2004-05-20��
     * @param strEndDate����Ϊ���������ڣ���ʽ��2004-05-20��
     * @return���ɹ������ڲ�ֵ��ʧ�ܣ�0
     */
    public static long getDateSubtract(String strBeginDate, String strEndDate) {
        String strTime = "00:00:00";
        strBeginDate += " " + strTime;
        strEndDate += " " + strTime;
        Date beginDate = null;
        Date endDate = null;
        long subtractDate = 0;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pp1 = new ParsePosition(0);
            ParsePosition pp2 = new ParsePosition(0);
            beginDate = formatter.parse(strBeginDate, pp1);
            endDate = formatter.parse(strEndDate, pp2);
            subtractDate = (beginDate.getTime() - endDate.getTime()) /
                (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }

        return subtractDate;
    }

}