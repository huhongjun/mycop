package com.zhjedu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title: <font color=red>日期工具�?</font>
 * Description:
 * @version 1.0
 */
public class DateTimeUtil {

	private static final String format1 = "yyyy-MM-dd HH:mm:ss";

	private static final String format2 = "yyyy-MM-dd";
	
	private static final String format3 = "dd/MM/yyyy";
	
	private static final String format4 = "HH:mm:ss";

	private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat(
			format1);

	private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			format2);

	private static final SimpleDateFormat dateFormat3 = new SimpleDateFormat(
			format3);
	private static final SimpleDateFormat dateFormat4 = new SimpleDateFormat(
			format4);
	public DateTimeUtil() {
	}

	public static long getTimeStamp(String time)
			throws ParseException {
		return dateFormat1.parse(time).getTime();
	}	
	
	public static long getTimeStamp(String time, int format)
			throws ParseException {
		if (format == 1) {
			return dateFormat1.parse(time).getTime();
		} else if (format == 2) {
			return dateFormat2.parse(time).getTime();
		} 
		 else if (format == 3) {
				return dateFormat3.parse(time).getTime();
		} 
		 else if (format == 4) {
				return dateFormat4.parse(time).getTime();
		}
		else {
			return dateFormat2.parse(time).getTime();
		}
	}

	public static String getTime(long timeStamp)
		throws ParseException {
		return dateFormat1.format(new Date(timeStamp));
	}
	
	
	public static String getTime(long timeStamp, int format)
			throws ParseException {
		if (format == 1) {
			return dateFormat1.format(new Date(timeStamp));
		} else if (format == 2) {
			return dateFormat2.format(new Date(timeStamp));
		}else if (format == 3) {
			return dateFormat3.format(new Date(timeStamp));
		}
		else if (format == 4) {
			return dateFormat4.format(new Date(timeStamp));
		}
		else {
			return dateFormat2.format(new Date(timeStamp));
		}
	}
	
	public static long getTime(int days){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR,-(days));
		return now.getTimeInMillis();
	}

	public static void main(String[] argv) {
		try {
			long time = DateTimeUtil.getTimeStamp("2006-02-27", 2);
			System.out.println(time);
			System.out.println(DateTimeUtil.getTime(time, 2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String getYear(){
		 Calendar cal = Calendar.getInstance();
		 Calendar.getInstance();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		 String date = formatter.format(cal.getTime());
		 return date;
	}

	public static String getDate(){
		 Calendar cal = Calendar.getInstance();
		 Calendar.getInstance();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String date = formatter.format(cal.getTime());
		 return date;
	}
}
