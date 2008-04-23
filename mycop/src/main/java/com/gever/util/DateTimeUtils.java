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
 * <p>Title: ���ں�ʱ��ͨ�÷�����</p>
 * <p>Description: ���ں�ʱ��ͨ�÷�����</p>
 * <p>�ṩ���ڡ�ʱ�䡢���ڵķֽ���ϼ��Ӽ�����ȹ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DateTimeUtils {
    public DateTimeUtils() {
    }

    //���Գ�������
    public static final String ENGLISH = "english";
    public static final String CHINESE = "chinese";

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
    * ��õ�ǰ�ַ������͵����ֵ����ʽ��2004��
    * @return�����ֵ
    */
   public static String getCurrentYear() {
       Date d = new Date();
       Timestamp t = new Timestamp(d.getTime());
       return t.toString().substring(0, 4);
   }

   /**
   * ��õ�ǰ�ַ������͵����ֵ����ʽ��2004��
   * @return�����ֵ
   */
  public static String getCurrentMonth() {
      Date d = new Date();
      Timestamp t = new Timestamp(d.getTime());
      return t.toString().substring(5, 7);
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
     * ��ø������ڵ�����
     * @param calendar�����и������ڵ�Calendar����
     * @return���ɹ����������ڡ�ʧ�ܣ�-1
     */
    public static int getWeek(Calendar calendar) {
        if (calendar == null) {
            return -1;
        }

        return calendar.get(calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * ��ø������ڵ���������
     * @param calendar�����и������ڵ�Calendar����
     * @return���ɹ����������ڡ�ʧ�ܣ�null
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
     * ��ø������ڵ�Ӣ������
     * @param calendar�����и������ڵ�Calendar����
     * @return��Ӣ������
     */
    public static String getEnglishWeek(Calendar calendar) {
        if (calendar == null) {
            return null;
        }

        int intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * ��õ�ǰ����
     * @return����������
     */
    public static String getCurrentWeek() {
        int week = -1;
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        week = c.get(c.WEEK_OF_MONTH);
        //System.out.println("The current week is:***"+week);
        return (String.valueOf(week));
    }

    /**
     * ��õ�ǰ��������
     * @return����������
     */
    public static String getCurrentChineseWeek() {
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, CHINESE);
    }

    /**
     * ��õ�ǰӢ������
     * @return��Ӣ������
     */
    public static String getCurrentEnglishWeek() {
        Calendar c = SumUtils.stringToCalendar(DateTimeUtils.getCurrentDate());
        int intWeek = c.get(c.DAY_OF_WEEK) - 1;
        return DateTimeUtils.selectWeek(intWeek, ENGLISH);
    }

    /**
     * �õ���ǰ����(1��ʾ����һ,2Ϊ���ڶ�,�Դ�����)
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
     * �õ���ǰ����(������һ,���ڶ�,�Դ�����
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
        intWeek = calendar.get(calendar.DAY_OF_WEEK) - 1; //��ȡ���ܵĵڼ���
        //�����������Ϊÿ�ܵ�һ�죬���Լ�һ
       // System.out.println("-----------intWeek----------------" + intWeek);
        calendar = null; //����
        if (!toChinese) {
            return (String.valueOf(intWeek));
        }

        switch (intWeek) {
            case 1:
                strRet = "����һ";
                break;
            case 2:
                strRet = "���ڶ�";
                break;
            case 3:
                strRet = "������";
                break;
            case 4:
                strRet = "������";
                break;
            case 5:
                strRet = "������";
                break;
            case 6:
                strRet = "������";
                break;
            case 0:
                strRet = "������";
                break;
            default:
                strRet = "����";
                break;
        }
        return strRet;
    }

    /**
     * ȡ�����Minutes�������������ڸ�ʽ��2004-8-2 07:20:00��
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
     * �õ���Ӧ���¼�������� ,
     * ��:getDate("2003-03-01",2)�������"2003-03-03"
     * @param strDate  ָ��������
     * @param intDays ����
     * @return String ��������
     */
    public String getDate(String strDate, int intDays) {
        StringTokenizer st = new java.util.StringTokenizer(strDate, "-");
        String strNewYear = new String("");
        String strNewMonth = new String("");
        String strNewDay = new String("");

        if (st.countTokens() != 3) { // �ݴ���-����ָ�����Ϊ3��˵����������
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        calendar.set(year, month - 1, day);
        calendar.add(calendar.DAY_OF_MONTH, intDays); //��һ������
        // �õ���
        strNewYear = String.valueOf(calendar.get(calendar.YEAR));
        // �õ���
        strNewMonth = (calendar.get(calendar.MONTH) + 1 < 10) ? "0" + String.valueOf(calendar.get(calendar.MONTH) + 1) :
            String.valueOf(calendar.get(calendar.MONTH) + 1);
        // �õ���
        strNewDay = (calendar.get(calendar.DAY_OF_MONTH) < 10) ?
            "0" + String.valueOf(calendar.get(calendar.DAY_OF_MONTH)) :
            String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        strDate = strNewYear + "-" + strNewMonth + "-" + strNewDay;
        // �õ���ǰ����Ϊ���ڼ�
        return strDate;
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

    /**
     * �õ����ڲ�
     * Wengnb Add 2003-09-09
     * ��getDateDiff("2004-02-01" ,"2004-03-01")��������29��(��Ϊ����)
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
        //�ݴ���
        if (iBeginYear > iEndYear) {
            return 0;
        }
        for (int i = iBeginYear; i < iEndYear; i++) {
            now.set(i, 11, 31);
            intTmp += now.get(now.DAY_OF_YEAR);
        }
        now.set(iBeginYear, getDateNumber(strBeginDate, 1) - 1, getDateNumber(strBeginDate, 2));
        int iStartDays = now.get(now.DAY_OF_YEAR); //�õ���ʼ�������xxxx-01-01�����ڵ����ڵ�������
        now.set(iEndYear, getDateNumber(strEndDate, 1) - 1, getDateNumber(strEndDate, 2));
        int iEndDays = now.get(now.DAY_OF_YEAR);
        intDays = intTmp + iEndDays - iStartDays;
        return intDays;
    }

    /**
     * �õ���ǰ�������ڵ�����»���(�ú����Ǹ��Ǹ������õ�)
     * Wengnb Add 2003-09-09
     * @param strDate String :"2003-08-01"
     * @param intFlag int: ����(0������,1������,2������)
     * @return int
     */
    private static int getDateNumber(String strDate, int intFlag) {
        StringTokenizer st = new StringTokenizer(strDate, "-");
        //�ݴ���
        if (st.countTokens() != 3) {
            return 0;
        }
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        if (intFlag == 0) { //�õ���ǰ��
            return year;
        } else if (intFlag == 1) { //�õ���ǰ��
            return month;
        } else { //�õ���ǰ��
            return day;
        }
    }

    /**
     * ͨ����ǰ���ڻ�ȡ�ĵ�ǰ���,���������ݿ�����Ϊ����ʹ��
     * ��Ȼֻ������ÿ���Ӧһ����¼�������
     * eg: 2003-09-09 �õ�20031009
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
     * �õ������ǵ�ǰ�µĵڼ���
     * CY Add 2004-08-04'
     * @return String
     */
    public static String getWeekNumOfThisMonth() {
        String weekOfMonth = "";
        Calendar calendar = Calendar.getInstance();
        String curDate = DateTimeUtils.getCurrentDate(); //ȡ�õ�ǰ����--��Ӧ��������
    //    System.out.println("-------------curDate-----------" + curDate);
        int year = Integer.parseInt(curDate.substring(0, 4));
        int month = Integer.parseInt(curDate.substring(5, 7));
        int day = Integer.parseInt(curDate.substring(8, 10));
       // System.out.println("----+++++----" + String.valueOf(year) + "-" + String.valueOf(month) + "-" +
       //                    String.valueOf(day));
        calendar.set(year, month, day);
        weekOfMonth = String.valueOf(calendar.WEEK_OF_MONTH);
        //ͨ��WEEK_OF_MONTH��ȡ�ĵ�ǰ��ӵ�м������ڵ���ֵ�����Ի���Ҫת��ȡ�õ�ǰ�ǵڼ��ܲ���
      //  System.out.println("----------weekOfMonth-----------" + weekOfMonth);
        return weekOfMonth;
    }

    /**
     * �õ���ǰ���������ڼ�
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

        if (st.countTokens() != 3) { // �ݴ���
            return "";
        }
        java.util.Calendar calendar = null;
        calendar = java.util.Calendar.getInstance();
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        calendar.set(year, month - 1, day);
        calendar.add(calendar.DAY_OF_MONTH, intDays); //��һ������
        // �õ���
        strNewYear = String.valueOf(calendar.get(calendar.YEAR));
        // �õ���
        strNewMonth = (calendar.get(calendar.MONTH) + 1 < 10) ?
            "0" + String.valueOf(calendar.get(calendar.MONTH) + 1) :
            String.valueOf(calendar.get(calendar.MONTH) + 1);
        // �õ���
        strNewDay = (calendar.get(calendar.DAY_OF_MONTH) < 10) ?
            "0" + String.valueOf(calendar.get(calendar.DAY_OF_MONTH)) :
            String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        strDate = strNewYear + "-" + strNewMonth + "-" + strNewDay;
        // �õ���ǰ����Ϊ���ڼ�
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

    // add by ��Է�� for get mask("yyyymmdd") date
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
