package com.gever.goa.dailyoffice.message.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

public interface MessageDao {


  public String getPageSql();

  public int insert(VOInterface vo) throws DefaultException;

  public int update(VOInterface vo) throws DefaultException;

  public List queryAll(VOInterface vo) throws DefaultException;

  public long queryByCount(String where) throws DefaultException;

  public List queryByPage(String where, long start, long howMany) throws
      DefaultException;

  public List queryByPage(VOInterface vo, long start, long howMany) throws
      DefaultException;

  public int updateReadFlag(VOInterface vo) throws DefaultException;

  public long queryByCount(VOInterface vo) throws DefaultException;

  public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

  /**
   * 发送短消息;
   * @param user_code String 发送人id
   * @param send_time String 发送时间
   * @param content String 消息内容
   * @param receiver String 接收人id
   * @param web_url String
   * @throws Exception
   * @return int
   */
  public int sendMessage(String user_code, String send_time, String content,
                         String receiver, String web_url,String memo) throws Exception;


  public int sendMessage(String user_code, String send_time, String content,
                        String receiver, String web_url) throws Exception;


  //为备忘录和日程安排的公共接口
  /**
   *
   * @param user_code String 发送人id
   * @param send_time String 发送时间
   * @param content String 消息内容
   * @param receiver String 接收人id
   * @param web_url String
   * @param model_id String 模块号 1是工作安排 2 是备忘录
   * @param mserial_num String 模块内的序列号 如备忘录的主键
   * @throws Exception
   * @return int
   */
  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num) throws Exception;

  /**
   * 更新短消息发送时间和内容
   * @param send_time String 发送时间 为""则不修改
   * @param content String 内容 为""则不修改
   * @param model_id String 模块号 1是工作安排 2 是备忘录
   * @param mserial_num String 模块内的序列号 如备忘录的主键
   * @throws DefaultException
   * @return int
   */
  public int updateMessageInfo(String send_time, String content,
                               String model_id,
                               String mserial_num) throws DefaultException;

  public int deleteMessageInfo(String model_id,
                               String mserial_num) throws DefaultException;
  /**
   * 给主页的公共接口
   * @param user_id String传递过来的用户名
   * @throws DefaultException
   * @return List
   */
  public List toListByUserId(String user_id) throws DefaultException;

  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num,String memo) throws Exception;

public List toListByUserIds(String user_ids) throws DefaultException;
}
