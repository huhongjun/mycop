package com.zhjedu.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * �����з�װһЩ���õ��ַ���������
 * ���з������Ǿ�̬����������Ҫ���ɴ����ʵ����
 * Ϊ�������ɴ����ʵ�������췽��������Ϊprivate���͵ġ�
 * @since  0.1
 */

public class StringUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private StringUtil() {
  }

  private static final BitSet allowed_query;
  private static MessageDigest digest = null;

  static
    {
        allowed_query = new BitSet(256);
        for(int i = 48; i <= 57; i++)
        {
            allowed_query.set(i);
        }

        for(int i = 97; i <= 122; i++)
        {
            allowed_query.set(i);
        }

        for(int i = 65; i <= 90; i++)
        {
            allowed_query.set(i);
        }

        allowed_query.set(45);
        allowed_query.set(95);
        allowed_query.set(46);
        allowed_query.set(33);
        allowed_query.set(126);
        allowed_query.set(42);
        allowed_query.set(39);
        allowed_query.set(40);
        allowed_query.set(41);
    }

  /**
   * �˷������������ַ���sourceʹ��delim����Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @param delim ���ʵķָ��ַ���
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����飬
   *         ���delimΪnull��ʹ�ö�����Ϊ�ָ��ַ�����
   * @since  0.1
   */
  public static String[] split(String source, String delim) {
    String[] wordLists;
    if (source == null) {
      wordLists = new String[1];
      wordLists[0] = source;
      return wordLists;
    }
    if (delim == null) {
      delim = ",";
    }
    StringTokenizer st = new StringTokenizer(source, delim);
    int total = st.countTokens();
    wordLists = new String[total];
    for (int i = 0; i < total; i++) {
      wordLists[i] = st.nextToken();
    }
    return wordLists;
  }

  /**
   * �˷������������ַ���sourceʹ��delim����Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @param delim ���ʵķָ��ַ�
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����顣
   * @since  0.2
   */
  public static String[] split(String source, char delim) {
    return split(source, String.valueOf(delim));
  }

  /**
   * �˷������������ַ���sourceʹ�ö��Ż���Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����顣
   * @since  0.1
   */
  public static String[] split(String source) {
    return split(source, ",");
  }

  /**
   * ѭ����ӡ�ַ������顣
   * �ַ�������ĸ�Ԫ�ؼ���ָ���ַ��ָ�������ַ������Ѿ�����ָ���ַ������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param delim �ָ���
   * @param out ��ӡ���������
   * @since  0.4
   */
  public static void printStrings(String[] strings, String delim,
                                  OutputStream out) {
    try {
      if (strings != null) {
        int length = strings.length - 1;
        for (int i = 0; i < length; i++) {
          if (strings[i] != null) {
            if (strings[i].indexOf(delim) > -1) {
              out.write( ("\"" + strings[i] + "\"" + delim).getBytes());
            }
            else {
              out.write( (strings[i] + delim).getBytes());
            }
          }
          else {
            out.write("null".getBytes());
          }
        }
        if (strings[length] != null) {
          if (strings[length].indexOf(delim) > -1) {
            out.write( ("\"" + strings[length] + "\"").getBytes());
          }
          else {
            out.write(strings[length].getBytes());
          }
        }
        else {
          out.write("null".getBytes());
        }
      }
      else {
        out.write("null".getBytes());
      }
      out.write(Constants.LINE_SEPARATOR.getBytes());
    }
    catch (IOException e) {

    }
  }

  /**
   * ѭ����ӡ�ַ������鵽��׼�����
   * �ַ�������ĸ�Ԫ�ؼ���ָ���ַ��ָ�������ַ������Ѿ�����ָ���ַ������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param delim �ָ���
   * @since  0.4
   */
  public static void printStrings(String[] strings, String delim) {
    printStrings(strings, delim, System.out);
  }

  /**
   * ѭ����ӡ�ַ������顣
   * �ַ�������ĸ�Ԫ�ؼ��Զ��ŷָ�������ַ������Ѿ��������������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param out ��ӡ���������
   * @since  0.2
   */
  public static void printStrings(String[] strings, OutputStream out) {
    printStrings(strings, ",", out);
  }

  /**
   * ѭ����ӡ�ַ������鵽ϵͳ��׼�����System.out��
   * �ַ�������ĸ�Ԫ�ؼ��Զ��ŷָ�������ַ������Ѿ��������������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @since  0.2
   */
  public static void printStrings(String[] strings) {
    printStrings(strings, ",", System.out);
  }

  /**
   * ���ַ����еı���ʹ��values�����е����ݽ����滻��
   * �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
   * @param prefix ����ǰ׺�ַ���
   * @param source ��������ԭ�ַ���
   * @param values �滻�õ��ַ�������
   * @return �滻����ַ�����
   *         ���ǰ׺Ϊnull��ʹ�á�%����Ϊǰ׺��
   *         ���source����valuesΪnull����values�ĳ���Ϊ0�򷵻�source��
   *         ���values�ĳ��ȴ��ڲ����ĸ����������ֵ�������ԣ�
   *         ���values�ĳ���С�ڲ����ĸ��������������в�����ʹ�����һ��ֵ�����滻��
   * @since  0.2
   */
  public static String getReplaceString(String prefix, String source,
                                        String[] values) {
    String result = source;
    if (source == null || values == null || values.length < 1) {
      return source;
    }
    if (prefix == null) {
      prefix = "%";
    }

    for (int i = 0; i < values.length; i++) {
      String argument = prefix + Integer.toString(i + 1);
      int index = result.indexOf(argument);
      if (index != -1) {
        String temp = result.substring(0, index);
        if (i < values.length) {
          temp += values[i];
        }
        else {
          temp += values[values.length - 1];
        }
        temp += result.substring(index + 2);
        result = temp;
      }
    }
    return result;
  }

  /**
   * ���ַ����еı������ԡ�%��Ϊǰ��������֣�ʹ��values�����е����ݽ����滻��
   * �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
   * @param source ��������ԭ�ַ���
   * @param values �滻�õ��ַ�������
   * @return �滻����ַ���
   * @since  0.1
   */
  public static String getReplaceString(String source, String[] values) {
    return getReplaceString("%", source, values);
  }

  /**
   * �ַ����������Ƿ����ָ�����ַ�����
   * @param strings �ַ�������
   * @param string �ַ���
   * @param caseSensitive �Ƿ��Сд����
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean contains(String[] strings, String string,
                                 boolean caseSensitive) {
    for (int i = 0; i < strings.length; i++) {
      if (caseSensitive == true) {
        if (strings[i].equals(string)) {
          return true;
        }
      }
      else {
        if (strings[i].equalsIgnoreCase(string)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * �ַ����������Ƿ����ָ�����ַ�������Сд���С�
   * @param strings �ַ�������
   * @param string �ַ���
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean contains(String[] strings, String string) {
    return contains(strings, string, true);
  }

  /**
   * �����ִ�Сд�ж��ַ����������Ƿ����ָ�����ַ�����
   * @param strings �ַ�������
   * @param string �ַ���
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean containsIgnoreCase(String[] strings, String string) {
    return contains(strings, string, false);
  }

  /**
   * ���ַ�������ʹ��ָ���ķָ����ϲ���һ���ַ�����
   * @param array �ַ�������
   * @param delim �ָ�����Ϊnull��ʱ��ʹ��""��Ϊ�ָ�������û�зָ�����
   * @return �ϲ�����ַ���
   * @since  0.4
   */
  public static String combineStringArray(String[] array, String delim) {
    int length = array.length - 1;
    if (delim == null) {
      delim = "";
    }
    StringBuffer result = new StringBuffer(length * 8);
    for (int i = 0; i < length; i++) {
      result.append(array[i]);
      result.append(delim);
    }
    result.append(array[length]);
    return result.toString();
  }

  /**
   * ��ָ���ַ������뵽����ÿ���ַ�����ͷ����
   * @param array
   * @param headString
   */
  public static String[] insertHeadToArray(String[] array, String headString) {
      StringBuffer sb;
    for (int i=0;i<array.length;i++) {
        sb = new StringBuffer(headString.length()+16);
        sb.append(headString);
        sb.append(File.separator);
        sb.append(array[i]);
        array[i] = sb.toString();
    }
    return array;
  }

  /**
   * ȥ�����еĻس���
   * @param source
   * @param String
   */
  public static String replaceEnter(String source) {
      return source.replace('\n',' ');
  }
  
  /**
   * ת�����еĻس�ΪHTML�еķ��С�
   * @param source
   * @param String
   */
  public static String replaceEnterToBr(String source) {
      return source.replaceAll("\n","<br />");
  }
  /**
   * ���ַ�������ת����Base64�ĸ�ʽ
   * @param data String
   * @return String
   */
  public static String encodeBase64(String data)
  {
      byte bytes[] = null;
      try
      {
          bytes = data.getBytes("ISO-8859-1");
      }
      catch(UnsupportedEncodingException uee)
      {
          System.out.println(uee.getMessage());
      }
      return encodeBase64(bytes);
  }

  /**
   * ��btye��������ת����Base64�ĸ�ʽ
   * @param data byte[]
   * @return String
   */
  public static String encodeBase64(byte data[])
  {
      int len = data.length;
      StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
      for(int i = 0; i < len; i++)
      {
          int c = data[i] >> 2 & 0x3f;
          ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
          c = data[i] << 4 & 0x3f;
          if(++i < len)
          {
              c |= data[i] >> 4 & 0xf;
          }
          ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
          if(i < len)
          {
              c = data[i] << 2 & 0x3f;
              if(++i < len)
              {
                  c |= data[i] >> 6 & 3;
              }
              ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
          } else
          {
              i++;
              ret.append('=');
          }
          if(i < len)
          {
              c = data[i] & 0x3f;
              ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
          } else
          {
              ret.append('=');
          }
      }

      return ret.toString();
  }

  /**
   * ��Base64�ĸ�ʽ�ַ������ݽ��ԭʼ����
   * @param data String
   * @return String
   */
  public static String decodeBase64(String data)
  {
      byte bytes[] = null;
      try
      {
          bytes = data.getBytes("ISO-8859-1");
      }
      catch(UnsupportedEncodingException uee)
      {
    	  System.out.println(uee.getMessage());
      }
      return decodeBase64(bytes);
  }

  /**
   * ��Base64�ĸ�ʽbtye�������ݽ��ԭʼ���� 
   * @param data byte[]
   * @return String
   */
  public static String decodeBase64(byte data[])
  {
      int len = data.length;
      StringBuffer ret = new StringBuffer((len * 3) / 4);
      for(int i = 0; i < len; i++)
      {
          int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
          i++;
          int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
          c = c << 2 | c1 >> 4 & 3;
          ret.append((char)c);
          if(++i < len)
          {
              c = data[i];
              if(61 == c)
              {
                  break;
              }
              c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(c);
              c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
              ret.append((char)c1);
          }
          if(++i >= len)
          {
              continue;
          }
          c1 = data[i];
          if(61 == c1)
          {
              break;
          }
          c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(c1);
          c = c << 6 & 0xc0 | c1;
          ret.append((char)c);
      }

      return ret.toString();
  }

  /**
   * ��ָ��URL�ַ�������ָ���ı���ת��
   * @param original ָ����URL�ַ���
   * @param charset ָ���ı��뷽ʽ
   * @return ת�����URL
   * @throws UnsupportedEncodingException
   */
  public static String URLEncode(String original, String charset)
      throws UnsupportedEncodingException
  {
      if(original == null)
      {
          return null;
      }
      byte octets[];
      try
      {
          octets = original.getBytes(charset);
      }
      catch(UnsupportedEncodingException error)
      {
          throw new UnsupportedEncodingException();
      }
      StringBuffer buf = new StringBuffer(octets.length);
      for(int i = 0; i < octets.length; i++)
      {
          char c = (char)octets[i];
          if(allowed_query.get(c))
          {
              buf.append(c);
          } else
          {
              buf.append('%');
              byte b = octets[i];
              char hexadecimal = Character.forDigit(b >> 4 & 0xf, 16);
              buf.append(Character.toUpperCase(hexadecimal));
              hexadecimal = Character.forDigit(b & 0xf, 16);
              buf.append(Character.toUpperCase(hexadecimal));
          }
      }

      return buf.toString();
    }

    /**
     * ��ϣ����
     * @param data ԭʼ����
     * @return ��ϣ�������
     */
    public static final synchronized String hash(String data)
    {
        if(digest == null)
        {
            try
            {
                digest = MessageDigest.getInstance("MD5");
            }
            catch(NoSuchAlgorithmException nsae)
            {
            	System.out.println("Failed to load the MD5 MessageDigest. LMS will be unable to function normally."+nsae.getMessage());
            }
        }
        try
        {
            digest.update(data.getBytes("utf-8"));
        }
        catch(UnsupportedEncodingException e)
        {
        	System.out.println(e.getMessage());
        }
        return encodeHex(digest.digest());
    }

    /**
     *
     * @param bytes byte[]
     * @return String
     */
    public static final String encodeHex(byte bytes[])
    {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for(int i = 0; i < bytes.length; i++)
        {
            if((bytes[i] & 0xff) < 16)
            {
                buf.append("0");
            }
            buf.append(Long.toString(bytes[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     *
     * @param hex String
     * @return byte[]
     */
    public static final byte[] decodeHex(String hex)
    {
        char chars[] = hex.toCharArray();
        byte bytes[] = new byte[chars.length / 2];
        int byteCount = 0;
        for(int i = 0; i < chars.length; i += 2)
        {
            int newByte = 0;
            newByte |= hexCharToByte(chars[i]);
            newByte <<= 4;
            newByte |= hexCharToByte(chars[i + 1]);
            bytes[byteCount] = (byte)newByte;
            byteCount++;
        }

        return bytes;
    }

    /**
     * ��16λ�ַ�ת�����ֽ���
     * @param ch 16λ�ַ�
     * @return �ֽ�
     */
    private static final byte hexCharToByte(char ch)
    {
        switch(ch)
        {
        case 48: // '0'
            return 0;

        case 49: // '1'
            return 1;

        case 50: // '2'
            return 2;

        case 51: // '3'
            return 3;

        case 52: // '4'
            return 4;

        case 53: // '5'
            return 5;

        case 54: // '6'
            return 6;

        case 55: // '7'
            return 7;

        case 56: // '8'
            return 8;

        case 57: // '9'
            return 9;

        case 97: // 'a'
            return 10;

        case 98: // 'b'
            return 11;

        case 99: // 'c'
            return 12;

        case 100: // 'd'
            return 13;

        case 101: // 'e'
            return 14;

        case 102: // 'f'
            return 15;

        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        default:
            return 0;
        }
    }

    /**
     * �����ļ�����չ��
     * @param filename �ļ���
     * @param suffix �µ���չ��
     * @return �µ��ļ���
     */
    public static String changeFileNameSuffixTo(String filename, String suffix)
    {
        int dotPos = filename.lastIndexOf('.');
        if(dotPos != -1)
        {
            return filename.substring(0, dotPos + 1) + suffix;
        } else
        {
            return filename;
        }
    }

    /**
     * ת��javascript�еķ���
     * @param source ����
     * @return ת����Ĵ���
     */
    public static String escapeJavaScript(String source)
    {
        source = substitute(source, "\\", "\\\\");
        source = substitute(source, "\"", "\\\"");
        source = substitute(source, "'", "\\'");
        source = substitute(source, "\r\n", "\\n");
        source = substitute(source, "\n", "\\n");
        return source;
    }

    /**
     * �����ַ���ΪPattern,��һ��������ʽ��Ϊһ����������ʽ
     * @param source ������ʽ�ַ���
     * @return ת������ַ���
     */
    public static String escapePattern(String source)
    {
        if(source == null)
        {
            return null;
        }
        StringBuffer result = new StringBuffer(source.length() * 2);
        for(int i = 0; i < source.length(); i++)
        {
            char ch = source.charAt(i);
            switch(ch)
            {
            case 92: // '\\'
                result.append("\\\\");
                break;

            case 47: // '/'
                result.append("\\/");
                break;

            case 36: // '$'
                result.append("\\$");
                break;

            case 94: // '^'
                result.append("\\^");
                break;

            case 46: // '.'
                result.append("\\.");
                break;

            case 42: // '*'
                result.append("\\*");
                break;

            case 43: // '+'
                result.append("\\+");
                break;

            case 124: // '|'
                result.append("\\|");
                break;

            case 63: // '?'
                result.append("\\?");
                break;

            case 123: // '{'
                result.append("\\{");
                break;

            case 125: // '}'
                result.append("\\}");
                break;

            case 91: // '['
                result.append("\\[");
                break;

            case 93: // ']'
                result.append("\\]");
                break;

            case 40: // '('
                result.append("\\(");
                break;

            case 41: // ')'
                result.append("\\)");
                break;

            default:
                result.append(ch);
                break;
            }
        }

        return new String(result);
    }

    /**
     * ��text�ı��е�attribute���Գ�ȡ����,���з��ص�map�а�������Ԫ��,һ���Ǽ�ֵtext,һ���Ǽ�ֵvalue.���ص�text��ֵ�е�ֵ�����ٰ���attribute
     * @param text text�ı�
     * @param attribute text�е�����
     * @param defValue Ĭ������ֵ
     * @return ���з��ص�map�а�������Ԫ��,һ���Ǽ�ֵtext,һ���Ǽ�ֵvalue
     */
    public static Map extendAttribute(String text, String attribute, String defValue)
    {
        Map retValue = new HashMap();
        retValue.put("text", text);
        retValue.put("value", "'" + defValue + "'");
        if(text != null && text.toLowerCase().indexOf(attribute.toLowerCase()) >= 0)
        {
            String quotation = "'";
            int pos1 = text.toLowerCase().indexOf(attribute.toLowerCase());
            int pos2 = text.indexOf(quotation, pos1);
            int test = text.indexOf("\"", pos1);
            if(test > -1 && (pos2 == -1 || test < pos2))
            {
                quotation = "\"";
                pos2 = test;
            }
            int pos3 = text.indexOf(quotation, pos2 + 1);
            String newValue = quotation + defValue + text.substring(pos2 + 1, pos3 + 1);
            String newText = text.substring(0, pos1);
            if(pos3 < text.length())
            {
                newText = newText + text.substring(pos3 + 1);
            }
            retValue.put("text", newText);
            retValue.put("value", newValue);
        }
        return retValue;
    }

    /**
     * ��HTML�е�body������ȡ����
     * @param content HTML�ı�����
     * @return HTML�е�body����
     */
    public static String extractHtmlBody(String content)
    {
        Matcher startMatcher = C_BODY_START_PATTERN.matcher(content);
        Matcher endMatcher = C_BODY_END_PATTERN.matcher(content);
        int start = 0;
        int end = content.length();
        if(startMatcher.find())
        {
            start = startMatcher.end();
        }
        if(endMatcher.find(start))
        {
            end = endMatcher.start();
        }
        return content.substring(start, end);
    }

    /**
     * �õ�XML�ļ�ͷ����ʾ�ı��뷽ʽ
     * @param content XML�ı�����
     * @return ���뷽ʽ
     */
    public static String extractXmlEncoding(String content)
    {
        String result = null;
        Matcher xmlHeadMatcher = C_XML_HEAD_REGEX.matcher(content);
        if(xmlHeadMatcher.find())
        {
            String xmlHead = xmlHeadMatcher.group();
            Matcher encodingMatcher = C_XML_ENCODING_REGEX.matcher(xmlHead);
            if(encodingMatcher.find())
            {
                String encoding = encodingMatcher.group();
                int pos1 = encoding.indexOf('=') + 2;
                String charset = encoding.substring(pos1, encoding.length() - 1);
                if(Charset.isSupported(charset))
                {
                    result = charset;
                }
            }
        }
        return result;
    }

    /**
     * ת������ʱ�䳤��,������ʱ���ĺ�����ת����ʱ����
     * @param runtime ����ʱ��
     * @return ת������ַ���(��ʽΪ00:00:00)
     */
    public static String formatRuntime(long runtime)
    {
        long seconds = (runtime / 1000L) % 60L;
        long minutes = (runtime / 60000L) % 60L;
        long hours = (runtime / 0x36ee80L) % 24L;
        long days = runtime / 0x5265c00L;
        StringBuffer strBuf = new StringBuffer();
        if(days > 0L)
        {
            if(days < 10L)
            {
                strBuf.append('0');
            }
            strBuf.append(days);
            strBuf.append(':');
        }
        if(hours < 10L)
        {
            strBuf.append('0');
        }
        strBuf.append(hours);
        strBuf.append(':');
        if(minutes < 10L)
        {
            strBuf.append('0');
        }
        strBuf.append(minutes);
        strBuf.append(':');
        if(seconds < 10L)
        {
            strBuf.append('0');
        }
        strBuf.append(seconds);
        return strBuf.toString();
    }

    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param value �ַ���
     * @return �жϽ��
     */
    public static boolean isEmpty(String value)
    {
        return value == null || value.length() == 0;
    }

    /**
     * �ж��ַ���ȥ���հ��Ƿ�Ϊ��
     * @param value �ַ���
     * @return �жϽ��
     */
    public static boolean isEmptyOrWhitespaceOnly(String value)
    {
        return isEmpty(value) || value.trim().length() == 0;
    }

    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param value �ַ���
     * @return �жϽ��
     */
    public static boolean isNotEmpty(String value)
    {
        return value != null && value.length() != 0;
    }
    
    /**
     * �ж��ַ���ȥ���հ��Ƿ�Ϊ��
     * @param value �ַ���
     * @return �жϽ��
     */
    public static boolean isNotEmptyOrWhitespaceOnly(String value)
    {
        return value != null && value.trim().length() > 0;
    }

    /**
     * �ж�className�Ƿ����java��������
     * @param className ����
     * @return �жϽ��
     */
    public static boolean isValidJavaClassName(String className)
    {
        if(isEmpty(className))
        {
            return false;
        }
        int length = className.length();
        boolean nodot = true;
        for(int i = 0; i < length; i++)
        {
            char ch = className.charAt(i);
            if(nodot)
            {
                if(ch == '.')
                {
                    return false;
                }
                if(Character.isJavaIdentifierStart(ch))
                {
                    nodot = false;
                } else
                {
                    return false;
                }
                continue;
            }
            if(ch == '.')
            {
                nodot = true;
                continue;
            }
            if(Character.isJavaIdentifierPart(ch))
            {
                nodot = false;
            } else
            {
                return false;
            }
        }

        return true;
    }

   /**
    * ���ݷָ����ָ��ַ������ַ�������
    * @param source �ַ���
    * @param delimiter �ָ���
    * @return �ַ�������
    */
    public static String[] splitAsArray(String source, char delimiter)
    {
        List result = splitAsList(source, delimiter);
        return (String[])result.toArray(new String[result.size()]);
    }

   /**
    * ���ݷָ����ָ��ַ������ַ�������
    * @param source �ַ���
    * @param delimiter �ָ���
    * @return �ַ�������
    */
    public static String[] splitAsArray(String source, String delimiter)
    {
        List result = splitAsList(source, delimiter);
        return (String[])result.toArray(new String[result.size()]);
    }

    /**
     * ���ݷָ����ָ��ַ�����List����
     * @param source �ַ���
     * @param delimiter �ָ���
     * @return �ַ�������
     */
    public static List splitAsList(String source, char delimiter)
    {
        return splitAsList(source, delimiter, false);
    }

    /**
     * ���ݷָ����ָ��ַ�����List����
     * @param source �ַ���
     * @param delimiter �ָ���
     * @return �ַ�������
     */
    public static List splitAsList(String source, String delimiter)
    {
        return splitAsList(source, delimiter, false);
    }
    
    /**
     * ���ݷָ����ָ��ַ�����List����
     * @param source �ַ���
     * @param delimiter �ָ���
     * @param trim �Ƿ�ȥ���հ�
     * @return �ַ�������
     */
    public static List splitAsList(String source, char delimiter, boolean trim)
    {
        List result = new ArrayList();
        int index = 0;
        for(int next = source.indexOf(delimiter); next != -1; next = source.indexOf(delimiter, index))
        {
            String item = source.substring(index, next);
            if(trim)
            {
                result.add(item.trim());
            } else
            {
                result.add(item);
            }
            index = next + 1;
        }

        if(trim)
        {
            result.add(source.substring(index).trim());
        } else
        {
            result.add(source.substring(index));
        }
        return result;
    }

    /**
     * ���ݷָ����ָ��ַ�����List����
     * @param source �ַ���
     * @param delimiter �ָ���
     * @param trim �Ƿ�ȥ���հ�
     * @return �ַ�������
     */
    public static List splitAsList(String source, String delimiter, boolean trim)
    {
        int len = delimiter.length();
        if(len == 1)
        {
            return splitAsList(source, delimiter.charAt(0), trim);
        }
        List result = new ArrayList();
        int index = 0;
        for(int next = source.indexOf(delimiter); next != -1; next = source.indexOf(delimiter, index))
        {
            String item = source.substring(index, next);
            if(trim)
            {
                result.add(item.trim());
            } else
            {
                result.add(item);
            }
            index = next + len;
        }

        if(trim)
        {
            result.add(source.substring(index).trim());
        } else
        {
            result.add(source.substring(index));
        }
        return result;
    }

    /**
     * ����content�����ַ�����serarchString���ַ���,���Ұ����滻��replaceItem�ַ���������
     * @param content �����ַ���
     * @param searchString Ҫ�������ַ���
     * @param replaceItem Ҫ�滻�ɵ��ַ���
     * @return �������ַ���
     */
    public static String substitute(String content, String searchString, String replaceItem)
    {
        if(content == null)
        {
            return null;
        }
        int stringLength = content.length();
        int findLength;
        if(searchString == null || (findLength = searchString.length()) == 0)
        {
            return content;
        }
        if(replaceItem == null)
        {
            replaceItem = "";
        }
        int replaceLength = replaceItem.length();
        int length;
        if(findLength == replaceLength)
        {
            length = stringLength;
        } else
        {
            int count = 0;
            int end;
            for(int start = 0; (end = content.indexOf(searchString, start)) != -1; start = end + findLength)
            {
                count++;
            }

            if(count == 0)
            {
                return content;
            }
            length = stringLength - count * (findLength - replaceLength);
        }
        int start = 0;
        int end = content.indexOf(searchString, start);
        if(end == -1)
        {
            return content;
        }
        StringBuffer sb = new StringBuffer(length);
        for(; end != -1; end = content.indexOf(searchString, start))
        {
            sb.append(content.substring(start, end));
            sb.append(replaceItem);
            start = end + findLength;
        }

        end = stringLength;
        sb.append(content.substring(start, end));
        return sb.toString();
    }

    /**
     * ��֤��Դ�ļ����Ƿ���Ϲ淶
     * @param �ļ���
     * @return ���ؽ��
     */
    public static boolean validateResourceName(String name)
    {
        if(name == null)
        {
            return false;
        }
        int l = name.length();
        if(l == 0)
        {
            return false;
        }
        if(name.length() != name.trim().length())
        {
            return false;
        }
        for(int i = 0; i < l; i++)
        {
            char ch = name.charAt(i);
            switch(ch)
            {
            case 47: // '/'
                return false;

            case 92: // '\\'
                return false;

            case 58: // ':'
                return false;

            case 42: // '*'
                return false;

            case 63: // '?'
                return false;

            case 34: // '"'
                return false;

            case 62: // '>'
                return false;

            case 60: // '<'
                return false;

            case 124: // '|'
                return false;
            }
            if(Character.isISOControl(ch))
            {
                return false;
            }
            if(!Character.isDefined(ch))
            {
                return false;
            }
        }

        return true;
    }

    public static String replaceNum(int num){
		String Num = "һ�����������߰˾�ʮ";
		
		if(num <= 10){
			return Num.substring(num, num + 1);
		}else{
			Num = (num + "").replaceAll("1", "һ");
			Num = (num + "").replaceAll("2", "��");
			Num = (num + "").replaceAll("3", "��");
			Num = (num + "").replaceAll("4", "��");
			Num = (num + "").replaceAll("5", "��");
			Num = (num + "").replaceAll("6", "��");
			Num = (num + "").replaceAll("7", "��");
			Num = (num + "").replaceAll("8", "��");
			Num = (num + "").replaceAll("9", "��");
			return Num;
		}
  }
    
	/**
	 * �ṩС��λ�������봦��
	 * @param d ��Ҫ������������� 
	 * @param scale С���������λ
	 * @return ���������Ľ��
	 */
    public static double round(double d, int scale) {
        long temp=1;
        for (int i=scale; i>0; i--) {
                temp*=10;
        }
        d*=temp;
        long dl=Math.round(d);
        return (double)(dl)/temp;
}
    
	public static String uuid() {
		return UUIDHexGenerator.getInstance().generate();
	}
    
    public static void main(String argv[]){

    }
    
    
    
    public static final String C_BODY_END_REGEX = "<\\s*/\\s*body[^>]*>";
    public static final String C_BODY_START_REGEX = "<\\s*body[^>]*>";
    public static final String C_LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String C_TABULATOR = "  ";
    private static final Pattern C_BODY_END_PATTERN = Pattern.compile(C_BODY_END_REGEX, 2);
    private static final Pattern C_BODY_START_PATTERN = Pattern.compile(C_BODY_START_REGEX, 2);
    private static final long C_DAYS = 0x5265c00L;
    private static final long C_HOURS = 0x36ee80L;
    private static final long C_MINUTES = 60000L;
    private static final long C_SECONDS = 1000L;
    private static final Pattern C_XML_ENCODING_REGEX = Pattern.compile("encoding\\s*=\\s*[\"'].+[\"']", 2);
    private static final Pattern C_XML_HEAD_REGEX = Pattern.compile("<\\s*\\?.*\\?\\s*>", 2);
    private static String m_contextReplace;
    private static String m_contextSearch;

}

class UUIDHexGenerator {

	private String sep = "";

	private static final int IP;

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private static UUIDHexGenerator uuidgen = new UUIDHexGenerator();

	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	public static UUIDHexGenerator getInstance() {
		return uuidgen;
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	protected int getJVM() {
		return JVM;
	}

	protected synchronized short getCount() {
		if (counter < 0) {
			counter = 0;
		}
		return counter++;
	}

	protected int getIP() {
		return IP;
	}

	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	public String generate() {
		return new StringBuffer(36)
				.append(format(getIP()))
				.append(sep)
				.append(format(getJVM()))
				.append(sep)
				.append(format(getHiTime()))
				.append(sep)
				.append(format(getLoTime()))
				.append(sep)
				.append(format(getCount()))
				.toString();
	}
}
