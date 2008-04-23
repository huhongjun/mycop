package com.gever.util;

import java.sql.Timestamp;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import com.gever.web.util.ActiveUsers;

/**
 * <p>Title: 日期和时间通用方法类</p>
 * <p>Description: 日期和时间通用方法类</p>
 * <p>提供日期、时间、星期的分解组合及加减运算等功能</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DateTimeUtils {
    public DateTimeUtils() {
    }

    //语言常量定义
    public static final String ENGLISH = "english";
    public static final String CHINESE = "chinese";

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
    * 获得当前字符串类型的年的值（格式：2004）
    * @return　年的值
    */
   public static String getCurrentYear() {
       Date d = new Date();
       Timestamp t = new Timestamp(d.getTime());
       return t.toString().substring(0, 4);
   }

   /**
   * 获得当前字符串类型的年的值（格式：2004）
   * @return　年的值
   */
  public static String getCurrentMonth() {
      Date d = new Date();
      Timestamp t = new Timestamp(d.getTime());
      return t.toString().substring(5, 7);
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
    public static String selectWeek(int intWeek, String language) {
        String week = CDEFAULT;
        //System.out.println("--------------intWeek--------------"+intWeek);
        switch (intWeek) {
            case 1:
                if (language.equals(ENGLISH)) {
                    week = EMONDAY;
                    //System.out.println("-----------1 english week----------"+week);
                } else if (language.equals(CHINESE)) {
                    week = CMONDAY;
                    //System.out.println("-----------1 chinese week----------"+week);
                }
                break;
            case 2:
                if (language.equals(ENGLISH)) {
                    week = ETUESDAY;
                } else if (language.equals(CHINESE)) {
                    week = CTUESDAY;
                    //System.out.println("-----------2 chinese week----------"+week);
                }
                break;
            case 3:
                if (language.equals(ENGLISH)) {
                    week = EWEDNESDAY;
                } else if (language.equals(CHINESE)) {
                    week = CWEDNESDAY;
                    //System.out.println("-----------3 chinese week----------"+week);
                }
                break;
            case 4:
                if (language.equals(ENGLISH)) {
                    week = ETHURSDAY;
                } else if (language.equals(CHINESE)) {
                    week = CTHURSDAY;
                    //System.out.println("-----------4 chinese week----------"+week);
                }
                break;
            case 5:
                if (language.equals(ENGLISH)) {
                    week = EFRIDAY;
                } else if (language.equals(CHINESE)) {
                    week = CFRIDAY;
                    //System.out.println("-----------5 chinese week----------"+week);
                }
                break;
            case 6:
                if (language.equals(ENGLISH)) {
                    week = ESATURDAY;
                } else if (language.equals(CHINESE)) {
                    week = CSATURDAY;
                    //System.out.println("-----------6 chinese week----------"+week);
                }
                break;
            case 0:
                if (language.equals(ENGLISH)) {
                    week = ESUNDAY;
                } else if (language.equals(CHINESE)) {
                    week = CSUNDAY;
                    //System.out.println("-----------7 chinese week----------"+week);
                }
                break;
            default:
                if (language.equals(ENGLISH)) {
                    week = EDEFAULT;
                } else if (language.equals(CHINESE)) {
                    week = CDEFAULT;
                    //System.out.println("-----------default chinese week----------"+week);
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
        if (calendar == null) {
            return -1;
        }

        return calendar.get(calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获得给定日期的中文星期
     * @param calendar　含有给定日期的Calendar对象
     * @return　成功：中文星期　失败：null
     */
    public static String getChineseWeek(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        //System.out.println("---------------intWeek--------------"+intWeek);
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * 获得给定日期的英文星期
     * @param calendar　含有给定日期的Calendar对象
     * @return　英文星期
     */
    public static String getEnglishWeek(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * 获得当前星期
     * @return　整型日期
     */
    public static String getCurrentWeek() {
        int week = -1;
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        week = c.get(c.WEEK_OF_MONTH);
        //System.out.println("The current week is:***"+week);
        return (String.valueOf(week));
    }

    /**
     * 获得当前中文星期
     * @return　中文星期
     */
    public static String getCurrentChineseWeek() {
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * 获得当前英文星期
     * @return　英文星期
     */
    public static String getCurrentEnglishWeek() {
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * 得到当前星期(1表示星期一,2为星期二,以此类推)
     * Wengnb Add 2003-09-09'
     * @param calendar Calendar
     * @return int
     */
    public static int getCurWeek(Calendar calendar) {
        return calendar.get(calendar.DAY_OF_WEEK) - 1;

    }

    /**
     * Wengnb Add 2003-09-09'
     *
     * @return
     */
    public static int getCurWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * Wengnb Add 2003-09-09'
     *
     * @return
     */
    public static String getCurWeek(boolean toChinese) {
        Calendar calendar = Calendar.getInstance();
        return getCurWeek(calendar, true);
    }

    /**
     * 得到当前星期(星期期一,星期二,以此类推
     * Wengnb Add 2003-09-09
     * @param calendar Calendar
     * @param toChinese boolean
     * @return String
     */
    public static String getCurWeek(Calendar calendar, boolean toChinese) {

        String strRet = "";
        int intWeek = 0;
      //  System.out.println("-------calendar.date----------" + calendar.DATE);
       // System.out.println("-------calendar.DayOfWeek-----" + calendar.DAY_OF_WEEK);
       // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1; //获取本周的第几天
        //外国是星期天为每周第一天，所以减一
       // System.out.println("-----------intWeek----------------" + intWeek);
        calendar = null; //回收
        if (!toChinese) {
            return (String.valueOf(intWeek));
        }

        switch (intWeek) {
            case 1:
                strRet = "星期一";
                break;
            case 2:
                strRet = "星期二";
                break;
            case 3:
                strRet = "星期三";
                break;
            case 4:
                strRet = "星期四";
                break;
            case 5:
                strRet = "星期五";
                break;
            case 6:
                strRet = "星期六";
                break;
            case 0:
                strRet = "星期日";
                break;
            default:
                strRet = "不详";
                break;
        }
        return strRet;
    }

    /**
     * 取得相差Minutes的日期数，日期格式（2004-8-2 07:20:00）
     * @param strDateTime String
     * @param addMinutes int
     * @return String
     */
    public static String getDateTimeByAddMinute(String strDateTime,
                                                int addMinutes) {

        try {
            SimpleDateFormat sdf = null;
            if(strDateTime.length()<17)
            sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
            else
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            StringBuffer sumDate = new StringBuffer();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(strDateTime, new ParsePosition(0)));
            calendar.add(Calendar.MINUTE, addMinutes);
            sdf.format(calendar.getTime(), sumDate, new FieldPosition(0));
            return sumDate.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到相应的下几天的日期 ,
     * 例:getDate("2003-03-01",2)结果返回"2003-03-03"
     * @param strDate  指定的日期
     * @param intDays 数字
     * @return String 最终日期
     */
    public String getDate(String strDate, int intDays) {
        StringTokenizer st = new java.util.StringTokenizer(strDate, "-");
        String strNewYear = new String("");
        String strNewMonth = new String("");
        String strNewDay = new String("");

        if (st.countTokens() != 3) { // 容错处理-如果分割数不为3，说明日期有误
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        calendar.set(year, month - 1, day);
        calendar.add(calendar.DAY_OF_MONTH, intDays); //下一个日期
        // 得到年
        strNewYear = String.valueOf(calendar.get(calendar.YEAR));
        // 得到月
        strNewMonth = (calendar.get(calendar.MONTH) + 1 < 10) ? "0" + String.valueOf(calendar.get(calendar.MONTH) + 1) :
            String.valueOf(calendar.get(calendar.MONTH) + 1);
        // 得到日
        strNewDay = (calendar.get(calendar.DAY_OF_MONTH) < 10) ?
            "0" + String.valueOf(calendar.get(calendar.DAY_OF_MONTH)) :
            String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        strDate = strNewYear + "-" + strNewMonth + "-" + strNewDay;
        // 得到当前日期为星期几
        return strDate;
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
            c = SumUtils.stringToCalendar(strDate);
            if (c == null) {
                return null;
            }
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

    /**
     * 得到日期差
     * Wengnb Add 2003-09-09
     * 如getDateDiff("2004-02-01" ,"2004-03-01")结果会等于29天(因为润年)
     * @param beginDate String: "2003-09-01"
     * @param endDate String :'2003-09-01'
     * @return int
     */
    public static int getDateDiff(String strBeginDate, String strEndDate) {
        int intDays = 0;
        Calendar now = Calendar.getInstance();
        int iBeginYear = getDateNumber(strBeginDate, 0);
        int iEndYear = getDateNumber(strEndDate, 0);
        int intTmp = 0;
        //容错处理
        if (iBeginYear > iEndYear) {
            return 0;
        }
        for (int i = iBeginYear; i < iEndYear; i++) {
            now.set(i, 11, 31);
            intTmp += now.get(now.DAY_OF_YEAR);
        }
        now.set(iBeginYear, getDateNumber(strBeginDate, 1) - 1, getDateNumber(strBeginDate, 2));
        int iStartDays = now.get(now.DAY_OF_YEAR); //得到开始年份日期xxxx-01-01至现在的日期的总天数
        now.set(iEndYear, getDateNumber(strEndDate, 1) - 1, getDateNumber(strEndDate, 2));
        int iEndDays = now.get(now.DAY_OF_YEAR);
        intDays = intTmp + iEndDays - iStartDays;
        return intDays;
    }

    /**
     * 得到当前给定日期的年或月或日(该函数是给那个日期用的)
     * Wengnb Add 2003-09-09
     * @param strDate String :"2003-08-01"
     * @param intFlag int: 类型(0代表年,1代表月,2代表日)
     * @return int
     */
    private static int getDateNumber(String strDate, int intFlag) {
        StringTokenizer st = new StringTokenizer(strDate, "-");
        //容错处理
        if (st.countTokens() != 3) {
            return 0;
        }
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        if (intFlag == 0) { //得到当前年
            return year;
        } else if (intFlag == 1) { //得到当前月
            return month;
        } else { //得到当前日
            return day;
        }
    }

    /**
     * 通过当前日期获取的当前编号,可以在数据库中作为主键使用
     * 当然只能用在每天对应一条记录的情况下
     * eg: 2003-09-09 得到20031009
     * @return String
     */

    public static String getNowBh() {
        java.util.Date dd = new java.util.Date();
        int yy = dd.getYear() + 1900;
        int mm = dd.getMonth() + 1;
        int day = dd.getDate();
        String strmm = "";
        String strday = "";
        if (mm < 10) {
            strmm = "0" + Integer.toString(mm);
        } else {
            strmm = Integer.toString(mm);
        }
        if (day < 10) {
            strday = "0" + Integer.toString(day);
        } else {
            strday = Integer.toString(day);
        }
        int strHours = dd.getHours();
        int strMinutes = dd.getMinutes();
        return yy + strmm + strday ;//+ strHours + strMinutes
    }

    /**
     * 得到现在是当前月的第几周
     * CY Add 2004-08-04'
     * @return String
     */
    public static String getWeekNumOfThisMonth() {
        String weekOfMonth = "";
        Calendar calendar = Calendar.getInstance();
        String curDate = DateTimeUtils.getCurrentDate(); //取得当前日期--对应创建日期
    //    System.out.println("-------------curDate-----------" + curDate);
        int year = Integer.parseInt(curDate.substring(0, 4));
        int month = Integer.parseInt(curDate.substring(5, 7));
        int day = Integer.parseInt(curDate.substring(8, 10));
       // System.out.println("----+++++----" + String.valueOf(year) + "-" + String.valueOf(month) + "-" +
       //                    String.valueOf(day));
        calendar.set(year, month, day);
        weekOfMonth = String.valueOf(calendar.WEEK_OF_MONTH);
        //通过WEEK_OF_MONTH获取的当前月拥有几个星期的数值，所以还需要转化取得当前是第几周才行
      //  System.out.println("----------weekOfMonth-----------" + weekOfMonth);
        return weekOfMonth;
    }

    /**
     * 得到当前日期是星期几
     * CY Add 2004-08-28'
     * @return String
     */

    public static String getWeek(String strDate, int intDays) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(strDate,
            "-");
        String retDate = new String("");
        String strWeek = new String("");
        String strNewYear = new String("");
        String strNewMonth = new String("");
        String strNewDay = new String("");

        if (st.countTokens() != 3) { // 容错处理
            return "";
        }
        java.util.Calendar calendar = null;
        calendar = java.util.Calendar.getInstance();
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        calendar.set(year, month - 1, day);
        calendar.add(calendar.DAY_OF_MONTH, intDays); //下一个日期
        // 得到年
        strNewYear = String.valueOf(calendar.get(calendar.YEAR));
        // 得到月
        strNewMonth = (calendar.get(calendar.MONTH) + 1 < 10) ?
            "0" + String.valueOf(calendar.get(calendar.MONTH) + 1) :
            String.valueOf(calendar.get(calendar.MONTH) + 1);
        // 得到日
        strNewDay = (calendar.get(calendar.DAY_OF_MONTH) < 10) ?
            "0" + String.valueOf(calendar.get(calendar.DAY_OF_MONTH)) :
            String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        strDate = strNewYear + "-" + strNewMonth + "-" + strNewDay;
        // 得到当前日期为星期几
        String myWeek = getCurWeek(calendar, true);

        return myWeek;
    }

    public static String toDate(String strDate) {
        ActiveUsers au = ActiveUsers.getInstance();
        String strRet = strDate;
        if (au.isOracle()) {
            strRet = "to_date('" + strRet + "','yyyy-mm-dd')";
        }else {
            strRet = "'" + strDate + "'";
        }

        return strRet;
    }

    public static String toDateTime(String strDate) {
        ActiveUsers au = ActiveUsers.getInstance();
        String strRet = strDate;
        if (au.isOracle()) {
            strRet = "to_date('" + strRet + "','yyyy-mm-dd hh24:mi:ss')";
        } else {
            strRet = "'" + strDate + "'";
        }
        return strRet;
    }

    // add by 陈苑文 for get mask("yyyymmdd") date
     public static String getMaskDate() {
    Calendar cal = Calendar.getInstance();
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyyMMdd");
    return String.valueOf(formatter.format(cal.getTime()));
     }
    //

    public static String getChineseDayOfWeekByDate(String currentDate) {
      Calendar c = SumUtils.stringToCalendar(currentDate);
       int intWeek = c.get(c.DAY_OF_WEEK) - 1;

       String date = DateTimeUtils.selectWeek(intWeek,DateTimeUtils.CHINESE);
      return date;
    }

}
