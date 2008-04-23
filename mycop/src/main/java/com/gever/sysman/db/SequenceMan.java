package com.gever.sysman.db;

import com.gever.config.Constants;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;

/**
 * 创建日期：(2003-5-26 9:06:42)
 * @author：陈正发
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SequenceMan {
    private static Log logger = LogFactory.getLog(SequenceMan.class);

    private static final String LOAD_ID = "SELECT id FROM GEVERID WHERE idType=?";
    private static final String UPDATE_ID = "UPDATE GEVERID SET id=? WHERE idType=? AND id=?";

    /**
     * <pre>
     * 为了尽量减少数据库的读写次数,不是每取一个ID后就更新数据库,而是INCREMENT次之后再更新数据库.
     * INCREMENT不能太大,不然服务器重新启动时,将造成ID浪费,可以根据需求更改INCREMENT.
     * maxID = currentID + INCREMENT
     * if (currentID >= maxID) {
     *    currentID = id(从数据库获取);
     *    id = id + INCREMENT;
     *    更新数据库(更新id).
     *    maxID = id;
     * } else {
     *    返回currentID;
     *    currentID++;
     * }
     * </pre>
     */
    private static final int INCREMENT = 15;

    /**
     * 数据库连接池.
     */
    private static final String connectionPool = "gbase";

    /**
     * 可以为不同的表设置分别设置一个SequenceManager.
     */
    private static SequenceMan[] managers;

    static {
        managers = new SequenceMan[5];

        for (int i = 0; i < managers.length; i++) {
            managers[i] = new SequenceMan(i);
        }
    }

    private int type;
    private long currentID;
    private long maxID;

    /**
     * DbSequenceManager构造函数.
     * @param type SequenceManager类型
     */
    private SequenceMan(int type) {
        this.type = type;
        currentID = 0L;
        maxID = 0L;
    }

    /**
     * 获取type类型的next ID.
     *
     * @param type SequenceManager类型,对应于Table GEVERID中的idType.
     * @return 获取type类型的next ID.
     */
    public static long nextID(int type) {
        return managers[type].nextUniqueID();
    }

    /**
     * 多表共用一个SequenceManager,默认返回第一个SequenceManager的next ID.
     */
    public static long nextID() {
        return managers[0].nextUniqueID();
    }

    /**
     * 返回next Unique ID.
     */
    public synchronized long nextUniqueID() {
        if (!(currentID < maxID)) {
            // Get next block -- make 5 attempts at maximum.
            getNextBlock(5);
        }

        long id = currentID;
        currentID++;

        return id;
    }

    /**
     * 获取next可用的ID block. 算法如下:
     * <ol>
     *  <li> 从相关的数据库记录中获取 currentID.
     *  <li> 将从数据库获取的id增长INCREMENT.
     *  <li> 更新数据库记录.
     *  <li> 如果更新失败,重复执行第一步.
     * </ol>
     */
    private void getNextBlock(int count) {
        logger.info("type " + type + ":SequenceManager.getNextBlock()");

        if (count == 0) {
            System.err.println("最后一次尝试获取ID block失败.");
            logger.fatal("最后一次尝试获取ID block失败.");

            return;
        }

        boolean success = false;
        ConnectionProvider cp = null;
        Connection conn = null;
      
       PreparedStatement pstmt = null;

        try {
			cp = ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
            conn = cp.getConnection();

            // 从数据库中获取current ID.
            pstmt = conn.prepareStatement(LOAD_ID);
            pstmt.setInt(1, type);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                logger.error("获取current ID失败. 不存在类型" + type + "的记录.");
                throw new SQLException("获取current ID失败. 不存在类型" + type + "的记录.");
            }

            long currentID = rs.getLong(1);
            pstmt.close();

            long newID = currentID + INCREMENT;
            pstmt = conn.prepareStatement(UPDATE_ID);
            pstmt.setLong(1, newID);
            pstmt.setInt(2, type);
            pstmt.setLong(3, currentID);

            //检查更新是否成功.
            success = pstmt.executeUpdate() == 1;

            //更新成功,可用的ID block为:currentID~maxID.
            if (success) {
                this.currentID = currentID;
                this.maxID = newID;
            }
        } catch (Exception sqle) {
            logger.error(sqle.getMessage());
            sqle.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //更新失败
        if (!success) {
            logger.warn(count + ":更新next ID block失败,重新尝试...");
            System.err.println(count + ":更新next ID block失败,重新尝试...");

            // 尝试重新执行getNextBlock(int).
            try {
                Thread.currentThread().sleep(75);
            } catch (InterruptedException ie) {
            }

            getNextBlock(count - 1);
        }
    }
}
