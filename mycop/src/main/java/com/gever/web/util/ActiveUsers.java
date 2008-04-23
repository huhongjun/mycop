package com.gever.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gever.util.StringUtils;
import com.gever.util.log.Log;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class ActiveUsers {
    private ActiveUsers() {
    }

    public static ActiveUsers self = null; 						// 单例ActiveUsers
    private static ArrayList aryActiveUsers = new ArrayList(); 	//在线用户统计列表
    private static List msgMailData = new ArrayList(); 			//在线用户统计列表
    private static long lngMaxTime = 0l; 						//最大session时间
    private static String corpName = "";
    private static ArrayList allFunc = new ArrayList(); 		//所有的业务名
    private static String dbtype = "db2";
    /**
     * 得到当前在线人数
     * @return 在线总人数
     */
    public static int getActiveCount() {
        return aryActiveUsers.size();
    }

    /**
     * 得到当前在线统计实例
     * @param lngTime session超时最大的值
     * @return 得到当前在线统计实例
     */
    public static ActiveUsers getInstance(long lngTime) {
        if (self == null) {
            synchronized (ActiveUsers.class) {
                ActiveUsers inst = self;
                if (inst == null) {
                    synchronized (ActiveUsers.class) {
                        self = new ActiveUsers();
                    }
                }
            }
        }
        lngMaxTime = lngTime * 1000;
        return self;
    }

    /**
     * 得到当前在线统计实例
     * @return 得到当前在线统计实例
     */
    public static ActiveUsers getInstance() {
        if (self == null) {
            synchronized (ActiveUsers.class) {
                ActiveUsers inst = self;
                if (inst == null) {
                    synchronized (ActiveUsers.class) {
                        self = new ActiveUsers();
                    }
                }
            }
        }

        return self;
    }

    /**
     * 检查登录的用户是否已经被注册到活动用户线程表中
     * 同时将已经超时的线程清除。
     * @param strUserID 登录的用户线程id
     * @param curDate 登录上一次登录的时间。
     * @param ipAddress 当前客户端的IP地址
     * @return boolean -如果当前用户没有达到用户的限制数量，就返回true
     */
    public synchronized boolean checkLoginUser(
            String strUserID, String strUserName, String strDeptName,
            String curDate,
            String ipAddress) {

        //如果当前用户及其线程已经再这里注册，返回true。
        //如果没有注册，需要检验是否超过了最多用户数。
        int inum = aryActiveUsers.size();

        String strSession = new String();
        long lstart, lend;
        boolean bIsAdd = true;

        ArrayList aryTmp = new ArrayList();
        String ip = new String("");
        for (int i = 0; i < aryActiveUsers.size(); i++) {
            HashMap hm = new HashMap();
            hm = (HashMap) aryActiveUsers.get(i);

            if (((String) hm.get("session")).equalsIgnoreCase(strUserID)) {
//                hm.remove("lastdate");
//                hm.put("lastdate", curDate);
                aryActiveUsers.set(i, hm); //更新当前线程的信息。
                bIsAdd = false;

            }
            //ip = (String) hm.get("ipaddress");
            //检查IP
            /*
                         if (ip.equalsIgnoreCase(ipAddress)) {
               System.out.println("同一ip只允许一个帐户！");
               return false;
                         }*/

            //lstart = ( (Date) hm.get("lastdate")).getTime();
            //lend = curDate.getTime();

            //如果超时，添加到需要删除的线程组中。

            //if (lend > (lstart + lngMaxTime)) {
            //aryActiveUsers.remove(i);
            //i--;
            //}

        }

        //如果是新线程，添加到线程数组中。
        if (bIsAdd == true) {
            if (!("0").equals(strUserID)){
                HashMap hmtemp = new HashMap();
                hmtemp.put("session", strUserID);
                hmtemp.put("username", strUserName);
                hmtemp.put("deptname", strDeptName);
                hmtemp.put("lastdate", curDate);
                hmtemp.put("ipaddress", ipAddress);

                aryActiveUsers.add(hmtemp);
            }
        }

        return true;
    }

    /**
     * 清除指定的线程登录标记。
     * @param strUserID --需要被清除的线程。
     */
    public void removeLogin(String strUserID) {
        // System.out.println("------删除前---有" +aryActiveUsers.size()               );
        for (int i = 0; i < aryActiveUsers.size(); i++) {
            HashMap hm = new HashMap();
            hm = (HashMap) aryActiveUsers.get(i);

            if (((String) hm.get("session")).trim().equalsIgnoreCase(
                    strUserID)) {
                aryActiveUsers.remove(i);
                break;
            }
        }
        //  System.out.println("------删除成功---还剩下" +aryActiveUsers.size()               );
    }

    /**
     * 得到公司名称
     * @param strCorpName 公司名称
     */
    public void setCorpName(String strCorpName) {
        corpName = strCorpName;
    }

    /**
     * 得到公司名称
     * @return 公司名称
     */
    public String getCorpName() {
        return corpName;
    }

    public void setAryFunc(ArrayList aryFunc) {
        this.allFunc = aryFunc;
    }

    public void setMsgMailData(List msgMailData) {
        this.msgMailData = msgMailData;
    }

    public List getMsgMailData() {
        return msgMailData;
    }

    Log log = Log.getInstance(ActiveUsers.class);

    /**
     * 得到邮件短信数
     * @param userId 当前用户ID
     * @return 当前邮件对象
     */
    public Map getNoReadData(String userId) {
        Map map = new HashMap();
        userId = StringUtils.nullToString(userId);
        int iCount = msgMailData.size();
        for (int idx = 0; idx < iCount; idx++) {
            map = new HashMap();
            map = ((Map) msgMailData.get(idx));
            if (userId.equals(map.get("id").toString())) {
                log.showLog(this.getClass().getName()+"->用户:"+userId+"已有邮件数" + (String) map.get("mailcnt"));
                log.showLog(this.getClass().getName()+"->用户:"+userId+"已有短信数" +  (String) map.get("msgcnt"));
                break;
            }
        }
        return map;
    }


    /**
     * @return 返回 dbtype。
     */
    public String getDbtype() {
        return dbtype;
    }

    /**
     * @param dbtype 要设置的 dbtype。
     */
    public void setDbtype(String dbtype) {
        ActiveUsers.dbtype = dbtype;
    }

    public boolean isOracle() {
        boolean bRet = false;
        if ("oracle".equalsIgnoreCase(dbtype)) {
            bRet = true;
        }
        return bRet;
    }

    public boolean isDB2() {
        boolean bRet = false;
        if ("db2".equalsIgnoreCase(dbtype)) {
            bRet = true;
        }
        return bRet;
    }

    /**
     * 得到在线用户的方法
     * @return Collection
     */
    public Collection getOnlineUsers() {
        return aryActiveUsers;
    }

}
