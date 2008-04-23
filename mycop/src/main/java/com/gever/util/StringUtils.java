package com.gever.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class StringUtils {
    static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
	static final char[] AMP_ENCODE = "&amp;".toCharArray();
	static final char[] LT_ENCODE = "&lt;".toCharArray();
	private static final char[] GT_ENCODE = "&gt;".toCharArray();

	private StringUtils() {
    }

    /**
     * null对象转换为空对象
     * @param value 对象
     * @return String对象
     */
    public static String nullToString(Object value) {
        String strRet = (String) value;
        if (null == strRet) {
            return strRet = "";
        }
        return strRet;
    }

    /**
     * 字符串替换方法
     * Wengnb Add 2003-09-09
     * @param strSource String:字符串
     * @param strFrom   String:源字串
     * @param strTo     String:替换的字串
     * @return          String:最终替换后的字串结果
     */
    public static String replace(String strSource, String strFrom, String strTo) {
        if (strSource == null)
            return "";

        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos = 0;

        while ( (intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;
        return strDest;
    }

    /**
     * Wengnb Add 2003-09-09
     * 将"'"替换成为"''"
     * @param strSource String:字符串
     * @return          String:最终替换后的字串结果
     */
    public static String replaceText(String strSource) {
        return replace(strSource, "'", "''");
    }

    /**
     * 处理自动转行内容
     * @param strContent 内容
     * @return 处理内容结果
     */
    public static String procContent(String strContent) {
        String strRet = "";

        if (getEngLength(strContent) <= maxLength)
            return strContent;

        char c;
        ArrayList ary = new ArrayList();
        boolean bEng = true;
        boolean bPreEng = true;
        StringBuffer strBuf = new StringBuffer();
        HashMap hmap = new HashMap();

        for (int idx = 0; idx < strContent.length(); idx++) {
            c = strContent.charAt(idx);
            bEng = (isWhat(c) > 0) ? false : true;

            if ( (bEng != bPreEng && idx > 0) ||
                (idx == strContent.length() - 1)) {
                hmap = new HashMap();
                if (idx == strContent.length() - 1)
                    strBuf.append(c);
                hmap.put("content", strBuf.toString());
                hmap.put("isEng", String.valueOf(bPreEng));

                ary.add(hmap);
                hmap = null;

                strBuf.setLength(0);
            }

            strBuf.append(c);
            bPreEng = bEng;
        }

        strBuf.setLength(0);

        String strCur = "";
        int curWidth = 0;
        int iPos = 0;

        for (int idx = 0; idx < ary.size(); idx++) {
            hmap = new HashMap();
            hmap = (HashMap) ary.get(idx);
            strCur = nullToString(hmap.get("content"));
            bEng = nullToString(hmap.get("isEng")).equalsIgnoreCase("true") ? true : false;
            curWidth = getEngLength(strCur);

            if (curWidth + iPos >= maxLength) {
                strBuf.append(procWord(iPos, strCur, bEng));
                iPos = curPos;
            } else {
                strBuf.append(strCur);
                iPos += curWidth;
            }
            hmap = null;
        }
        return strBuf.toString();

    }

    private final static int NUM_ENG = 0;
    private final static int OPERTAND = 1;
    private final static int CHINESE = 2;
    private static int curPos = 0;
    private static int maxLength = 74;

    private static boolean isEng(char c) {
        if ( (c >= 65 && c < 91) || (c >= 97 && c < 123)) {
            return true;
        } else if (c >= 48 && c <= 57) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 处理中文自动转行
     * @param iPos 位置
     * @param strValue 内容
     * @param isEng 是否英文
     * @return 处理后的结果
     */
    private static String procWord(int iPos, String strValue, boolean isEng) {
        char c;
        int maxWidth = 0;
        maxWidth = maxLength;
        StringBuffer strBuf = new StringBuffer();
        if (isEng == true && iPos > 0) {
            strBuf.append("\n");
            iPos = 0;
        }

        for (int idx = 0; idx < strValue.length(); idx++) {
            ++iPos;
            c = strValue.charAt(idx);
            if (c == '\n' || c == '\r')
                iPos = 0;
            if ( (int) c > 255) { //中文字
                ++iPos;
            }
            strBuf.append(c);
            if (iPos >= maxWidth) {
                iPos = 0;
                strBuf.append("\n");
            }
        }
        curPos = iPos;
        return strBuf.toString();
    }

    /**
     * 属于哪英文或标点符号,中文字
     * @param c 字符
     * @return 属于什么
     */
    private static int isWhat(char c) {
        if ( (c >= 65 && c < 91) || (c >= 97 && c < 123)) {
            return NUM_ENG;
        } else if (c >= 48 && c <= 57) {
            return NUM_ENG;
        } else if (c <= 255 && c > 0) {
            return OPERTAND;
        } else {
            return CHINESE;
        }
    }

    /**
     * 累计多少个字符长度(一个中文字按照两个英文的算法)
     * @param strValue 内容
     * @return 最终的长度
     */
    private static int getEngLength(String strValue) {
        char c;
        int iPos = 0;
        for (int idx = 0; idx < strValue.length(); idx++) {
            ++iPos;
            c = strValue.charAt(idx);
            if ( (int) c > 255) { //中文字
                ++iPos;
            }
        }
        return iPos+1;
    }

    public static String filter(String value) {
        if (value == null)
            return null;
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++)
            switch (content[i]) {
                case 60: // '<'
                    result.append("&lt;");
                    break;

                case 62: // '>'
                    result.append("&gt;");
                    break;

                case 38: // '&'
                    result.append("&amp;");
                    break;

                case 34: // '"'
                    result.append("&quot;");
                    break;

                default:
                    result.append(content[i]);
                    break;
            }

        return result.toString();
    }

    public static String unFilter(String value) {
        if (value == null)
            return null;
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++)
            switch (content[i]) {
                case '\n': // '<'
                    result.append("<BR>");
                   // System.out.println("----n---");
                    break;

//                case '\r': // '>'
//                    System.out.println("----r---");
//                    result.append("<BR>");
//                    break;

                case 32: // '&'
                    result.append("&nbsp;");
                    break;

                default:
                    result.append(content[i]);
                    break;
            }

        return result.toString();
    }

    /**
     * 显示多少个字符出来,(主要用于链接时,不要显示太多的内容)
     * @param strValue 内容
     * @return 处理后的结果
     */
    public static String subStr(String strValue) {
        return subStr(strValue, 30);
    }

    /**
     * 显示多少个字符出来,(主要用于链接时,不要显示太多的内容)
     * @param strValue 内容
     * @param maxWidth 最大宽度
     * @return 处理后的结果
     */
    public static String subStr(String strValue, int maxWidth) {
        int length = getEngLength(strValue);

        if (length <= maxWidth)
            return strValue;
        int iPos = 0;
        char c;
        StringBuffer strBuf = new StringBuffer();
        for (int idx = 0; idx < strValue.length(); idx++) {
            c = strValue.charAt(idx);
            ++iPos;
            if ( (int) c > 255) { //中文字
                ++iPos;
            }
            strBuf.append(c);
            if (iPos >= (maxWidth - 3)) {
                strBuf.append("...");
                break;
            }

        }
        return "<span title='" + strValue + "'>" + strBuf.toString() +
            "</span>";

    }
    /**
     * 根据分格符分离字符串
     * 2004-07-16 cy添加
     * @param list 分离结果串放到list中
     * @param s 要分离的字符串
     * @param a 分隔符
     */
    public static void separate(ArrayList list, String s, String a) {
        if (list == null) {
            return;
        }
        if (s.indexOf(a) < 0) {
            if (!s.equalsIgnoreCase("")) {
                list.add(s);
            }
        } else if (s.indexOf(a) == 0) {
            if (!s.equalsIgnoreCase(a)) {
                separate(list, s.substring(1), a);
            }
        } else {
            list.add(s.substring(0, s.indexOf(a)));
            separate(list, s.substring(s.indexOf(a)), a);
        }
    }
    /**
     * 2004-07-19 cy添加
     * 替换方法:主要用于表格缩进处理
     * @param strSource 源字串
     * @return String html缩进空格
     */
    public static String replaceLen(String strSource) {
        StringBuffer strRet = new StringBuffer();
        for (int idx = 0; idx <= (strSource.length() - 2); idx++)
            strRet.append("&nbsp;");
        return strRet.toString();
    }

    /**
     * 2004-07-19 cy添加
     * 替换方法:主要用于表格缩进处理
     * @param strSource 源字串
     * @return String html缩进空格
     */
    public static String replaceLen2(String strSource) {
        StringBuffer strRet = new StringBuffer();

        for (int idx = 0; idx < ( (strSource.length() - 2) * 2); idx++)
            strRet.append("&nbsp;");
        return strRet.toString();
    }
    /**
     * 2004-07-19 cy添加
     * 主要用于处理被选择的单选框
     * @param aryData 列表数据
     * @param strCheckID 被选取的ID
     * @return String strValue--checked标志
     */

    public static String getChecked(List aryData, String strCheckID) {
        String strValue = "";
        String strTmp = "";
        for (int idx = 0; idx < aryData.size(); idx++) {
            strTmp = (String) aryData.get(idx);
            if (strCheckID.equals(strTmp)) {
                strValue = "checked";
                break;
            }
        }
        return strValue;
    }

    public static boolean isNull(String value){
        if ("".equals(value) || null == value) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){
 //   System.out.println((int)'\r');
   // System.out.println((int)'\n');
   // System.out.println((int)' ');
    }
/**---------- 董中光添加 ------------------
    /**
     * 将String[]转换为形如"…，…，…，…"的字符串，用来在删除时使用
     * 董中光
     * @param fid fid的String[]
     * @return
     */
    public static String arrayToString(String[] fid) {
        String serperater = ",";

        return arrayToString(fid, serperater);
    }

    public static String arrayToString(String[] fid, String serperater) {
        String strfid = "";
        for (int i = 0; i < fid.length; i++) { //将所有选择的记录删除
            if (i == 0)
                strfid = fid[0];
            else
                strfid += serperater + fid[i];
        }
        return strfid;
    }

    /*
     * 将String null,empty转化为0
     * @param  str 原字符串
     * @return String
     */
    public static String emptyStringToZero(String str) {
        if (str == null || str.equals(""))
            return "0";
        return str;
    }

    /*
     * 将int 0转化为"",如果不为零,则强制转为String
     * @double zero 零
     * @return String
     */
    public static String zeroToEmptyString(int zero) {
        if (zero == 0)
            return "";
        return String.valueOf(zero);
    }

    /*
     * 将float 0转化为"",如果不为零,则强制转为String
     * @double zero 零
     * @return String
     */
    public static String zeroToEmptyString(float zero) {
        if (zero == 0f)
            return "";
        return String.valueOf(zero);
    }
    /**
     * 字节数字转换 自动转换加kb或Byte
     * @param strSize 数字字串
     * @return 字节转换
     */
    public static String sizeToKB(String strSize) {
        long lngSize = 0l;
        lngSize = SumUtils.strToLong(strSize);
        StringBuffer sBufSize = new StringBuffer();
        if (lngSize < 1024)
            return sBufSize.append(lngSize).append("Byte").toString();
        strSize = SumUtils.sum(strSize + "/1024");
        if (SumUtils.strToDouble(strSize) > 1000)
            strSize = SumUtils.format(strSize, "0,000.0");
        else
            strSize = SumUtils.format(strSize, "0.0");
        sBufSize.append(strSize).append("KB");
        return (sBufSize.toString());
    }

    /*
     * 将double 0转化为"",如果不为零,则强制转为String
     * @double zero 零
     * @return String
     */
    public static String zeroToEmptyString(double zero) {
        if (zero == 0d)
            return "";
        return String.valueOf(zero);
    }

	/**
	   * 将xml违法字符转换成其他字符
	   * @param string .
	   * @return string
	   */
	  public static final String escapeForXML(String string) {
	      if (string == null) {
	          return null;
	      }
	      char ch;
	      int i=0;
	      int last=0;
	      char[] input = string.toCharArray();
	      int len = input.length;
	      StringBuffer out = new StringBuffer((int)(len*1.3));
	      for (; i < len; i++) {
	          ch = input[i];
	          if (ch > '>') {
	              continue;
	          } else if (ch == '<') {
	              if (i > last) {
	                  out.append(input, last, i - last);
	              }
	              last = i + 1;
	              out.append(LT_ENCODE);
	          } else if (ch == '&') {
	              if (i > last) {
	                  out.append(input, last, i - last);
	              }
	              last = i + 1;
	              out.append(AMP_ENCODE);
	          } else if (ch == '"') {
	              if (i > last) {
	                  out.append(input, last, i - last);
	              }
	              last = i + 1;
	              out.append(QUOTE_ENCODE);
	          }
	      }
	      if (last == 0) {
	          return string;
	      }
	      if (i > last) {
	          out.append(input, last, i - last);
	      }
	      return out.toString();
	  }

	/**
	   * escapeForXML的反过程
	   *
	   * @param string
	   * @return  string
	   */
	  public static final String unescapeFromXML(String string) {
	      string = StringUtils.replace(string, "&lt;", "<");
	      string = StringUtils.replace(string, "&gt;", ">");
	      string = StringUtils.replace(string, "&quot;", "\"");
	      return StringUtils.replace(string, "&amp;", "&");
	  }

	/**
	    * 将给定的字符串转换为UTF编码的字符串。
	    * @param str 输入字符串
	    * @return 经UTF编码后的字符串，如果有异常，则返回原编码字符串
	    */
	   public static String toUTF8(String str){
	    String retVal = str;
	    /*try{
	     retVal = new String(str.getBytes("ISO-8859-1"),"GB2312");
	    } catch(Exception e){
	
	    }*/
	    return retVal;
	   }

	/**
	    * 将给定的字符串转换为UTF编码的字符串。
	    * @param str 输入字符串
	    * @return 经UTF编码后的字符串，如果有异常，则返回原编码字符串
	    */
	   public static String toGBK(String str){
	
	    String retVal = str;
	    try{
	
	     retVal = new String(str.getBytes(), "GB2312");
	    } catch(Exception e){
	
	    }
	
	    return retVal;
	   }
}
