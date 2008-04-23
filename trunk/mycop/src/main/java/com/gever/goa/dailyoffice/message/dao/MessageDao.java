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
   * ���Ͷ���Ϣ;
   * @param user_code String ������id
   * @param send_time String ����ʱ��
   * @param content String ��Ϣ����
   * @param receiver String ������id
   * @param web_url String
   * @throws Exception
   * @return int
   */
  public int sendMessage(String user_code, String send_time, String content,
                         String receiver, String web_url,String memo) throws Exception;


  public int sendMessage(String user_code, String send_time, String content,
                        String receiver, String web_url) throws Exception;


  //Ϊ����¼���ճ̰��ŵĹ����ӿ�
  /**
   *
   * @param user_code String ������id
   * @param send_time String ����ʱ��
   * @param content String ��Ϣ����
   * @param receiver String ������id
   * @param web_url String
   * @param model_id String ģ��� 1�ǹ������� 2 �Ǳ���¼
   * @param mserial_num String ģ���ڵ����к� �籸��¼������
   * @throws Exception
   * @return int
   */
  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num) throws Exception;

  /**
   * ���¶���Ϣ����ʱ�������
   * @param send_time String ����ʱ�� Ϊ""���޸�
   * @param content String ���� Ϊ""���޸�
   * @param model_id String ģ��� 1�ǹ������� 2 �Ǳ���¼
   * @param mserial_num String ģ���ڵ����к� �籸��¼������
   * @throws DefaultException
   * @return int
   */
  public int updateMessageInfo(String send_time, String content,
                               String model_id,
                               String mserial_num) throws DefaultException;

  public int deleteMessageInfo(String model_id,
                               String mserial_num) throws DefaultException;
  /**
   * ����ҳ�Ĺ����ӿ�
   * @param user_id String���ݹ������û���
   * @throws DefaultException
   * @return List
   */
  public List toListByUserId(String user_id) throws DefaultException;

  public int sendMessageInfo(String user_code, String send_time, String content,
                             String receiver, String web_url, String model_id,
                             String mserial_num,String memo) throws Exception;

public List toListByUserIds(String user_ids) throws DefaultException;
}
