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
 * <p>Title: 日期操作类</p>
 * <p>Description: 提供日期、时间、星期的分解组合及加减运算功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DateUtil {

    //语言常量定义
    private static final String ENGLISH = "english";
    private static final String CHINESE = "chinese";

    //星期常量定义
    private static final String ESUNDAY = "Sunday";
    private static final String CSUNDAY = "星期日";
    private static final String EMONDAY = "Monday";
    private static final String CMONDAY = "星期一";
    private static final String ETUESDAY = "Tuesday";
    private static final String CTUESDAY = "星期二";
    private static final String EWEDNESDAY = "Wednesday";
    private static final String CWEDNESDAY = "星期三";
    private static final String ETHURSDAY = "Thursday";
    private static final String CTHURSDAY = "星期四";
    private static final String EFRIDAY = "Friday";
    private static final String CFRIDAY = "星期五";
    private static final String ESATURDAY = "Saturday";
    private static final String CSATURDAY = "星期六";
    private static final String EDEFAULT = "Dimness";
    private static final String CDEFAULT = "不详";

    private DateUtil() {
    }

    /**
     * 得到当前给定日期的年或月或日
     * @param strDate 字符串型日期（格式：2004-05-20）
     * @param intFlag int 类型(0代表年,1代表月,2代表日)
     * @return int 成功：年月日的整型值　失败：-1
     */
    private static int getNumberOfDate(String strDate, int intFlag) {
        StringTokenizer st = new StringTokenizer(strDate, "-");
        //容错处理
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
     * 获得当前字符串类型的日期时间值（格式：2004-05-20 00:00:00）
     * @return　日期时间值
     */
    public static String getCurrentDateTime() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(0, 19);
    }

    /**
     * 获得当前字符串类型的日期值（格式：2004-05-20）
     * @return　日期值
     */
    public static String getCurrentDate() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(0, 10);
    }

    /**
     * 获得当前字符串类型的时间值
     * @return　时间值（格式为"00:00:00"）
     */
    public static String getCurrentTime() {
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        return t.toString().substring(11, 19);
    }

    /**
     * 获得用某种语言描述的星期
     * @param intWeek　整型星期值
     * @param language　语言类型（目前只支持中文和英文）
     * @return　用某种语言描述的星期
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
     * 获得给定日期的星期
     * @param calendar　含有给定日期的Calendar对象
     * @return　成功：整型星期　失败：-1
     */
    public static int getWeek(Calendar calendar) {
        if (calendar == null)
            return -1;

        return calendar.get(calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获得给定日期的中文星期
     * @param calendar　含有给定日期的Calendar对象
     * @return　成功：中文星期　失败：null
     */
    public static String getChineseWeek(Calendar calendar) {
        if (calendar == null)
            return null;

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * 获得给定日期的英文星期
     * @param calendar　含有给定日期的Calendar对象
     * @return　英文星期
     */
    public static String getEnglishWeek(Calendar calendar) {
        if (calendar == null)
            return null;

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * 获得当前星期
     * @return　整型日期
     */
    public static String getCurrentWeek() {
        int week = -1;
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        week = c.get(c.DAY_OF_WEEK) - 1;
        return (String.valueOf(week));
    }

    /**
     * 获得当前中文星期
     * @return　中文星期
     */
    public static String getCurrentChineseWeek() {
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * 获得当前英文星期
     * @return　英文星期
     */
    public static String getCurrentEnglishWeek() {
        Calendar c = NumberUtil.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * 实现日期增加功能
     * @param strDate　参照日期（格式：2004-05-20）
     * @param dateNumber　日期增加值
     * @return　成功：增加后的日期值（格式：2004-05-20）　失败：null
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
     * 实现日期减少功能
     * @param strDate　参照日期（格式：2004-05-20）
     * @param dateNumber　日期减少值
     * @return　成功：减少后的日期值（格式：2004-05-20）　失败：null
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
     * 实现日期相减功能
     * @param strBeginDate　作为被减数的日期（格式：2004-05-20）
     * @param strEndDate　作为减数的日期（格式：2004-05-20）
     * @return　成功：日期差值　失败：0
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