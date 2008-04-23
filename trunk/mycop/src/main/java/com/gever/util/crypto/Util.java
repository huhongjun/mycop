package com.gever.util.crypto;

import java.io.*;
import java.security.*;

/**
 * 工具类；提供对象的导出和导入等功能
 * @author Hu.Walker
 * @version 0.9
 */

public class Util {

  public Util() {
  }

  /**
   * 把信息和签名保存在一个文件中
   * @param message 信息
   * @param signature 签名字节数组
   * @param filename 文件名
   * @throws FileNotFoundException 指定的文件不存在
   * @throws IOException 执行文件输入输出时出错
   */
  public static void saveSignedMessage(String message, byte[] signature,
                                String filename) throws FileNotFoundException,
      IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
        filename));
    out.writeObject(message);
    out.writeObject(signature);
    out.close();
  }

  /**
   * 将密钥对保存到文件中
   * @param keys 密钥对对象
   * @param prikeyFile 私钥保存对应的文件名称
   * @param pubkeyFile 公钥保存对应的文件名称
   * @throws FileNotFoundException 指定的文件路径不存在
   * @throws IOException 执行文件写入时出错
   */
  public static void saveKeyPair(KeyPair keys, String prikeyFile, String pubkeyFile) throws
      FileNotFoundException, IOException {
    if (keys == null) {
      System.out.println("Null keypair! nothing saved!");
      return;
    }
    PublicKey pubkey = keys.getPublic();
    PrivateKey prikey = keys.getPrivate();
    saveObject2File(prikey, prikeyFile);
    saveObject2File(pubkey, pubkeyFile);
  }

  /**
   * 从文件中读取公钥
   * @param keyFile 公钥文件所在路径
   * @return 公钥
   * @throws java.lang.ClassNotFoundException 类型转换时出错
   * @throws FileNotFoundException 指定文件路径不存在
   * @throws IOException 执行文件读操作时出错
   */
  public static PublicKey getPublicKeyFromFile(String keyFile) throws
      ClassNotFoundException, FileNotFoundException, IOException {
    if (keyFile == null || keyFile.trim().length() == 0) {
      System.out.println("No key file name specified! nothing readed!");
      return null;
    }
    PublicKey mypubkey = (PublicKey) readObjectFromFile(keyFile);
    return mypubkey;
  }

  /**
   * 从文件中读取私钥
   * @param keyFile 私钥文件所在路径
   * @return 私钥
   * @throws java.lang.ClassNotFoundException 类型转换时出错
   * @throws FileNotFoundException 指定文件路径不存在
   * @throws IOException 执行文件读操作时出错
   */
  public static PrivateKey getPrivateKeyFromFile(String keyFile) throws
      ClassNotFoundException, FileNotFoundException, IOException {
    if (keyFile == null || keyFile.trim().length() == 0) {
      System.out.println("No key file name specified! nothing readed!");
      return null;
    }
    PrivateKey myprikey = (PrivateKey) readObjectFromFile(keyFile);
    return myprikey;
  }

  /**
   * 将对象保存到文件
   * @param obj 要保存的对象
   * @param filename 指定的保存文件路径
   * @throws FileNotFoundException 指定的文件路径不存在
   * @throws IOException 执行文件写操作出错
   */
  public static void saveObject2File(Object obj, String filename) throws
      FileNotFoundException, IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new FileOutputStream(filename));
    out.writeObject(obj);
    out.close();
  }

  /**
   * 从文件读取对象
   * @param filename 指定的文件路径
   * @return 从文件获取的对象
   * @throws java.lang.ClassNotFoundException 类型转换出错
   * @throws FileNotFoundException 指定文件路径不存在
   * @throws IOException 执行文件读操作失败
   */
  public static Object readObjectFromFile(String filename) throws
      ClassNotFoundException, FileNotFoundException, IOException {
    ObjectInputStream in =
        new ObjectInputStream(new FileInputStream(filename));
    Object obj = in.readObject();
    in.close();
    return obj;
  }

  /**
   * 从文件读取对象
   * @param file 指定的文件对象
   * @return 从文件获取的对象
   * @throws java.lang.ClassNotFoundException 类型转换出错
   * @throws FileNotFoundException 指定文件路径不存在
   * @throws IOException 执行文件读操作失败
   */
  public static Object readObjectFromFile(File file) throws ClassNotFoundException,
      FileNotFoundException, IOException {
    ObjectInputStream in =
        new ObjectInputStream(new FileInputStream(file));
    Object obj = in.readObject();
    in.close();
    return obj;
  }

  /**
   * 将字节数组转换成字符串
   * 用作显示打印
   * @param b 要转换的字节数组
   * @return 转换后的字符串,以十六进制格式表示
   */
  public static String byte2hex(byte[] b) {
    if (b == null) {
      return "";
    }
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      }
      else {
        hs = hs + stmp;
      }
      if (n < b.length - 1) {
        hs = hs + "-";
      }
    }
    return hs.toUpperCase();
  }

  /**
   * 字节数组内容比较
   * @param a 字节数组一
   * @param b 字节数组二
   * @return boolean,如果两字节数组所有值相同,则返回true,否则返回false
   */
  public static boolean byteArrayEquals(byte[] a, byte[] b) {
    if (a == null || b == null) {
      return false;
    }
    if (a.length != b.length) {
      return false;
    }
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }

}