package com.gever.util.crypto;

import java.io.*;
import java.security.*;

/**
 * �����ࣻ�ṩ����ĵ����͵���ȹ���
 * @author Hu.Walker
 * @version 0.9
 */

public class Util {

  public Util() {
  }

  /**
   * ����Ϣ��ǩ��������һ���ļ���
   * @param message ��Ϣ
   * @param signature ǩ���ֽ�����
   * @param filename �ļ���
   * @throws FileNotFoundException ָ�����ļ�������
   * @throws IOException ִ���ļ��������ʱ����
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
   * ����Կ�Ա��浽�ļ���
   * @param keys ��Կ�Զ���
   * @param prikeyFile ˽Կ�����Ӧ���ļ�����
   * @param pubkeyFile ��Կ�����Ӧ���ļ�����
   * @throws FileNotFoundException ָ�����ļ�·��������
   * @throws IOException ִ���ļ�д��ʱ����
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
   * ���ļ��ж�ȡ��Կ
   * @param keyFile ��Կ�ļ�����·��
   * @return ��Կ
   * @throws java.lang.ClassNotFoundException ����ת��ʱ����
   * @throws FileNotFoundException ָ���ļ�·��������
   * @throws IOException ִ���ļ�������ʱ����
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
   * ���ļ��ж�ȡ˽Կ
   * @param keyFile ˽Կ�ļ�����·��
   * @return ˽Կ
   * @throws java.lang.ClassNotFoundException ����ת��ʱ����
   * @throws FileNotFoundException ָ���ļ�·��������
   * @throws IOException ִ���ļ�������ʱ����
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
   * �����󱣴浽�ļ�
   * @param obj Ҫ����Ķ���
   * @param filename ָ���ı����ļ�·��
   * @throws FileNotFoundException ָ�����ļ�·��������
   * @throws IOException ִ���ļ�д��������
   */
  public static void saveObject2File(Object obj, String filename) throws
      FileNotFoundException, IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new FileOutputStream(filename));
    out.writeObject(obj);
    out.close();
  }

  /**
   * ���ļ���ȡ����
   * @param filename ָ�����ļ�·��
   * @return ���ļ���ȡ�Ķ���
   * @throws java.lang.ClassNotFoundException ����ת������
   * @throws FileNotFoundException ָ���ļ�·��������
   * @throws IOException ִ���ļ�������ʧ��
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
   * ���ļ���ȡ����
   * @param file ָ�����ļ�����
   * @return ���ļ���ȡ�Ķ���
   * @throws java.lang.ClassNotFoundException ����ת������
   * @throws FileNotFoundException ָ���ļ�·��������
   * @throws IOException ִ���ļ�������ʧ��
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
   * ���ֽ�����ת�����ַ���
   * ������ʾ��ӡ
   * @param b Ҫת�����ֽ�����
   * @return ת������ַ���,��ʮ�����Ƹ�ʽ��ʾ
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
   * �ֽ��������ݱȽ�
   * @param a �ֽ�����һ
   * @param b �ֽ������
   * @return boolean,������ֽ���������ֵ��ͬ,�򷵻�true,���򷵻�false
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