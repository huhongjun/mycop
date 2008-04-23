package com.gever.util.crypto;

import java.security.*;

/**
 * 信息摘要类
 * @author Hu.Walker
 * @version 0.9
 */

public class Digest {
  public Digest() {
  }

  public static final String MD5 = "MD5"; //$NON-NLS-<n>$
  public static final String SHA1 = "SHA-1";

  /**
   * <p>信息摘要</p>
   * 一个消息摘要就是一个数据块的数字指纹。即对一个任意长度的一个数据块进行计算，产生一个唯一指印。
   * @param message 要进行采样摘要的字符串信息
   * @param algorithm 摘要算法,支持MD5和SHA-1
   * @return 信息摘要字节数组
   * @throws NoSuchAlgorithmException 指定的摘要算法有误
   */
  public static byte[] getMessageDigest(String message, String algorithm) throws
      NoSuchAlgorithmException {
    MessageDigest alg = MessageDigest.getInstance(algorithm);
    // 添加要进行计算摘要的信息
    alg.update(message.getBytes());
    // 计算出摘要
    return alg.digest();
  }

  /**
   * <p>信息摘要</p>
   * 一个消息摘要就是一个数据块的数字指纹。即对一个任意长度的一个数据块进行计算，产生一个唯一指印。
   * @param message 要进行采样摘要的字节数组信息
   * @param algorithm 摘要算法,支持MD5和SHA-1
   * @return 信息摘要字节数组
   * @throws NoSuchAlgorithmException 指定的摘要算法有误
   */
  public static byte[] getMessageDigest(byte[] message, String algorithm) throws
      NoSuchAlgorithmException {
    MessageDigest alg = MessageDigest.getInstance(algorithm);
    // 添加要进行计算摘要的信息
    alg.update(message);
    // 计算出摘要
    return alg.digest();
  }

  /**
   * 按指定算法和摘要对信息进行校验
   * @param message 原信息
   * @param algorithm 摘要算法
   * @param digest 要验证的摘要
   * @return 校验结果,true=校验正确,false=校验错误
   * @throws NoSuchAlgorithmException
   */
  public static boolean verifyMessageDigest(String message, String algorithm,
                                     byte[] digest) throws
      NoSuchAlgorithmException {
    MessageDigest alg = MessageDigest.getInstance(algorithm);
    alg.update(message.getBytes());
    return MessageDigest.isEqual(digest, alg.digest());
  }

  /**
   * 按指定算法和摘要对信息进行校验
   * @param message 原字节数组信息
   * @param algorithm 摘要算法
   * @param digest 要验证的摘要
   * @return 校验结果,true=校验正确,false=校验错误
   * @throws NoSuchAlgorithmException
   */
  public static boolean verifyMessageDigest(byte[] message, String algorithm,
                                     byte[] digest) throws
      NoSuchAlgorithmException {
    MessageDigest alg = MessageDigest.getInstance(algorithm);
    alg.update(message);
    return MessageDigest.isEqual(digest, alg.digest());
  }

}