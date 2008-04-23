package com.gever.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
     * null����ת��Ϊ�ն���
     * @param value ����
     * @return String����
     */
    public static String nullToString(Object value) {
        String strRet = (String) value;
        if (null == strRet) {
            return strRet = "";
        }
        return strRet;
    }

    /**
     * �ַ����滻����
     * Wengnb Add 2003-09-09
     * @param strSource String:�ַ���
     * @param strFrom   String:Դ�ִ�
     * @param strTo     String:�滻���ִ�
     * @return          String:�����滻����ִ����
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
     * ��"'"�滻��Ϊ"''"
     * @param strSource String:�ַ���
     * @return          String:�����滻����ִ����
     */
    public static String replaceText(String strSource) {
        return replace(strSource, "'", "''");
    }

    /**
     * �����Զ�ת������
     * @param strContent ����
     * @return �������ݽ��
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
     * ���������Զ�ת��
     * @param iPos λ��
     * @param strValue ����
     * @param isEng �Ƿ�Ӣ��
     * @return �����Ľ��
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
            if ( (int) c > 255) { //������
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
     * ������Ӣ�Ļ������,������
     * @param c �ַ�
     * @return ����ʲô
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
     * �ۼƶ��ٸ��ַ�����(һ�������ְ�������Ӣ�ĵ��㷨)
     * @param strValue ����
     * @return ���յĳ���
     */
    private static int getEngLength(String strValue) {
        char c;
        int iPos = 0;
        for (int idx = 0; idx < strValue.length(); idx++) {
            ++iPos;
            c = strValue.charAt(idx);
            if ( (int) c > 255) { //������
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
     * ��ʾ���ٸ��ַ�����,(��Ҫ��������ʱ,��Ҫ��ʾ̫�������)
     * @param strValue ����
     * @return �����Ľ��
     */
    public static String subStr(String strValue) {
        return subStr(strValue, 30);
    }

    /**
     * ��ʾ���ٸ��ַ�����,(��Ҫ��������ʱ,��Ҫ��ʾ̫�������)
     * @param strValue ����
     * @param maxWidth �����
     * @return �����Ľ��
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
            if ( (int) c > 255) { //������
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
     * ���ݷָ�������ַ���
     * 2004-07-16 cy���
     * @param list ���������ŵ�list��
     * @param s Ҫ������ַ���
     * @param a �ָ���
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
     * 2004-07-19 cy���
     * �滻����:��Ҫ���ڱ����������
     * @param strSource Դ�ִ�
     * @return String html�����ո�
     */
    public static String replaceLen(String strSource) {
        StringBuffer strRet = new StringBuffer();
        for (int idx = 0; idx <= (strSource.length() - 2); idx++)
            strRet.append("&nbsp;");
        return strRet.toString();
    }

    /**
     * 2004-07-19 cy���
     * �滻����:��Ҫ���ڱ����������
     * @param strSource Դ�ִ�
     * @return String html�����ո�
     */
    public static String replaceLen2(String strSource) {
        StringBuffer strRet = new StringBuffer();

        for (int idx = 0; idx < ( (strSource.length() - 2) * 2); idx++)
            strRet.append("&nbsp;");
        return strRet.toString();
    }
    /**
     * 2004-07-19 cy���
     * ��Ҫ���ڴ���ѡ��ĵ�ѡ��
     * @param aryData �б�����
     * @param strCheckID ��ѡȡ��ID
     * @return String strValue--checked��־
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
/**---------- ���й���� ------------------
    /**
     * ��String[]ת��Ϊ����"��������������"���ַ�����������ɾ��ʱʹ��
     * ���й�
     * @param fid fid��String[]
     * @return
     */
    public static String arrayToString(String[] fid) {
        String serperater = ",";

        return arrayToString(fid, serperater);
    }

    public static String arrayToString(String[] fid, String serperater) {
        String strfid = "";
        for (int i = 0; i < fid.length; i++) { //������ѡ��ļ�¼ɾ��
            if (i == 0)
                strfid = fid[0];
            else
                strfid += serperater + fid[i];
        }
        return strfid;
    }

    /*
     * ��String null,emptyת��Ϊ0
     * @param  str ԭ�ַ���
     * @return String
     */
    public static String emptyStringToZero(String str) {
        if (str == null || str.equals(""))
            return "0";
        return str;
    }

    /*
     * ��int 0ת��Ϊ"",�����Ϊ��,��ǿ��תΪString
     * @double zero ��
     * @return String
     */
    public static String zeroToEmptyString(int zero) {
        if (zero == 0)
            return "";
        return String.valueOf(zero);
    }

    /*
     * ��float 0ת��Ϊ"",�����Ϊ��,��ǿ��תΪString
     * @double zero ��
     * @return String
     */
    public static String zeroToEmptyString(float zero) {
        if (zero == 0f)
            return "";
        return String.valueOf(zero);
    }
    /**
     * �ֽ�����ת�� �Զ�ת����kb��Byte
     * @param strSize �����ִ�
     * @return �ֽ�ת��
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
     * ��double 0ת��Ϊ"",�����Ϊ��,��ǿ��תΪString
     * @double zero ��
     * @return String
     */
    public static String zeroToEmptyString(double zero) {
        if (zero == 0d)
            return "";
        return String.valueOf(zero);
    }

	/**
	   * ��xmlΥ���ַ�ת���������ַ�
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
	   * escapeForXML�ķ�����
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
	    * ���������ַ���ת��ΪUTF������ַ�����
	    * @param str �����ַ���
	    * @return ��UTF�������ַ�����������쳣���򷵻�ԭ�����ַ���
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
	    * ���������ַ���ת��ΪUTF������ַ�����
	    * @param str �����ַ���
	    * @return ��UTF�������ַ�����������쳣���򷵻�ԭ�����ַ���
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
