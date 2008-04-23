package com.gever.sysman.log.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */
import java.sql.*;
import com.gever.jdbc.connection.*;

public class IdManager {
  /**
   * DbSequenceManager���캯��.
   * @param type SequenceManager����
   */
  public IdManager(int type) {
    this.type = type;
    currentID = 0l;
    maxID = 0l;

  }

  //private static Logger logger;
  /*static {
          BasicConfigurator.configure();
          logger = Logger.getLogger(SequenceManager.class);
     }
   */
  private static final String LOAD_ID =
      "SELECT id FROM T_SYSTEM_ID WHERE idType=?";
  private static final String UPDATE_ID =
      "UPDATE T_SYSTEM_ID SET id=? WHERE idType=? AND id=?";

  private int type;
  private long currentID;
  private long maxID;

  /**
   * <pre>
   * Ϊ�˾����������ݿ�Ķ�д����,����ÿȡһ��ID��͸������ݿ�,����INCREMENT��֮���ٸ������ݿ�.
   * INCREMENT����̫��,��Ȼ��������������ʱ,�����ID�˷�,���Ը����������INCREMENT.
   * maxID = currentID + INCREMENT
   * if (currentID >= maxID) {
   *    currentID = id(�����ݿ��ȡ);
   *    id = id + INCREMENT;
   *    �������ݿ�(����id).
   *    maxID = id;
   * } else {
   *    ����currentID;
   *    currentID++;
   * }
   * </pre>
   */
  private static final int INCREMENT = 15;

  /**
   * ���ݿ����ӳ�.
   */
 // private static final String connectionPool = "gbase";

  /**
   * ����Ϊ��ͬ�ı����÷ֱ�����һ��SequenceManager.
   */
  private static IdManager[] managers;
  static {
    managers = new IdManager[28];
    for (int i = 0; i < managers.length; i++) {
      managers[i] = new IdManager(i);
    }
  }

  /**
   * ��ȡtype���͵�next ID.
   *
   * @param type SequenceManager����,��Ӧ��Table T_system_id�е�idType.
   * @return ��ȡtype���͵�next ID.
   */
  public static long nextID(int type) {
    long id = managers[type].nextUniqueID();
    return id;
  }

  /**
   * �����һ��SequenceManager,Ĭ�Ϸ��ص�һ��SequenceManager��next ID.
   */
  public static long nextID() {
    return managers[0].nextUniqueID();
  }

  /**
   * ����next Unique ID.
   */
  public synchronized long nextUniqueID() {
    if (! (currentID < maxID)) {
      // Get next block -- make 5 attempts at maximum.
      getNextBlock(5);
    }
    long id = currentID;
    currentID++;
    return id;
  }

  /**
   * ��ȡnext���õ�ID block. �㷨����:
   * <ol>
   *  <li> ����ص����ݿ��¼�л�ȡ currentID.
   *  <li> �������ݿ��ȡ��id����INCREMENT.
   *  <li> �������ݿ��¼.
   *  <li> �������ʧ��,�ظ�ִ�е�һ��.
   * </ol>
   */
  private void getNextBlock(int count) {
    //logger.info("type " + type + ":SequenceManager.getNextBlock()");
    if (count == 0) {
      System.err.println("���һ�γ��Ի�ȡID blockʧ��.");
      //logger.fatal("���һ�γ��Ի�ȡID blockʧ��.");
      return;
    }
    boolean success = false;
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      //conn = ConnectionFactory.getConnection();

      com.gever.jdbc.connection.ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(
          "gdp");
      conn = cp.getConnection();

      // �����ݿ��л�ȡcurrent ID.
      pstmt = conn.prepareStatement(LOAD_ID);
      pstmt.setInt(1, type);
      ResultSet rs = pstmt.executeQuery();
      if (!rs.next()) {
        //logger.error("��ȡcurrent IDʧ��. ����������" + type + "�ļ�¼.");
        throw new SQLException("��ȡcurrent IDʧ��. ����������" + type + "�ļ�¼.");
      }
      long currentID = rs.getLong(1);
      pstmt.close();

      long newID = currentID + INCREMENT;
      pstmt = conn.prepareStatement(UPDATE_ID);
      pstmt.setLong(1, newID);
      pstmt.setInt(2, type);
      pstmt.setLong(3, currentID);
      //�������Ƿ�ɹ�.
      success = pstmt.executeUpdate() == 1;
      //���³ɹ�,���õ�ID blockΪ:currentID~maxID.
      if (success) {
        this.currentID = currentID;
        this.maxID = newID;
      }
    }
    catch (Exception sqle) {
      //logger.error(sqle.getMessage());
      //sqle.printStackTrace();
    }
    finally {
      try {
        pstmt.close();
      }
      catch (Exception e) { //e.printStackTrace();
      }
      try {
        //DBConnectionManager.getInstance().freeConnection(connectionPool,conn);
        conn.close();
      }
      catch (Exception e) {
        //e.printStackTrace();
      }
    }
    //����ʧ��
    if (!success) {
      //logger.warn(count + ":����next ID blockʧ��,���³���...");
      System.err.println(count + ":����next ID blockʧ��,���³���...");
      // ��������ִ��getNextBlock(int).
      try {
        Thread.currentThread().sleep(75);
      }
      catch (InterruptedException ie) {}
      getNextBlock(count - 1);
    }
  }

}
