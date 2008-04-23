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

    public static ActiveUsers self = null; 						// ����ActiveUsers
    private static ArrayList aryActiveUsers = new ArrayList(); 	//�����û�ͳ���б�
    private static List msgMailData = new ArrayList(); 			//�����û�ͳ���б�
    private static long lngMaxTime = 0l; 						//���sessionʱ��
    private static String corpName = "";
    private static ArrayList allFunc = new ArrayList(); 		//���е�ҵ����
    private static String dbtype = "db2";
    /**
     * �õ���ǰ��������
     * @return ����������
     */
    public static int getActiveCount() {
        return aryActiveUsers.size();
    }

    /**
     * �õ���ǰ����ͳ��ʵ��
     * @param lngTime session��ʱ����ֵ
     * @return �õ���ǰ����ͳ��ʵ��
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
     * �õ���ǰ����ͳ��ʵ��
     * @return �õ���ǰ����ͳ��ʵ��
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
     * ����¼���û��Ƿ��Ѿ���ע�ᵽ��û��̱߳���
     * ͬʱ���Ѿ���ʱ���߳������
     * @param strUserID ��¼���û��߳�id
     * @param curDate ��¼��һ�ε�¼��ʱ�䡣
     * @param ipAddress ��ǰ�ͻ��˵�IP��ַ
     * @return boolean -�����ǰ�û�û�дﵽ�û��������������ͷ���true
     */
    public synchronized boolean checkLoginUser(
            String strUserID, String strUserName, String strDeptName,
            String curDate,
            String ipAddress) {

        //�����ǰ�û������߳��Ѿ�������ע�ᣬ����true��
        //���û��ע�ᣬ��Ҫ�����Ƿ񳬹�������û�����
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
                aryActiveUsers.set(i, hm); //���µ�ǰ�̵߳���Ϣ��
                bIsAdd = false;

            }
            //ip = (String) hm.get("ipaddress");
            //���IP
            /*
                         if (ip.equalsIgnoreCase(ipAddress)) {
               System.out.println("ͬһipֻ����һ���ʻ���");
               return false;
                         }*/

            //lstart = ( (Date) hm.get("lastdate")).getTime();
            //lend = curDate.getTime();

            //�����ʱ����ӵ���Ҫɾ�����߳����С�

            //if (lend > (lstart + lngMaxTime)) {
            //aryActiveUsers.remove(i);
            //i--;
            //}

        }

        //��������̣߳���ӵ��߳������С�
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
     * ���ָ�����̵߳�¼��ǡ�
     * @param strUserID --��Ҫ��������̡߳�
     */
    public void removeLogin(String strUserID) {
        // System.out.println("------ɾ��ǰ---��" +aryActiveUsers.size()               );
        for (int i = 0; i < aryActiveUsers.size(); i++) {
            HashMap hm = new HashMap();
            hm = (HashMap) aryActiveUsers.get(i);

            if (((String) hm.get("session")).trim().equalsIgnoreCase(
                    strUserID)) {
                aryActiveUsers.remove(i);
                break;
            }
        }
        //  System.out.println("------ɾ���ɹ�---��ʣ��" +aryActiveUsers.size()               );
    }

    /**
     * �õ���˾����
     * @param strCorpName ��˾����
     */
    public void setCorpName(String strCorpName) {
        corpName = strCorpName;
    }

    /**
     * �õ���˾����
     * @return ��˾����
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
     * �õ��ʼ�������
     * @param userId ��ǰ�û�ID
     * @return ��ǰ�ʼ�����
     */
    public Map getNoReadData(String userId) {
        Map map = new HashMap();
        userId = StringUtils.nullToString(userId);
        int iCount = msgMailData.size();
        for (int idx = 0; idx < iCount; idx++) {
            map = new HashMap();
            map = ((Map) msgMailData.get(idx));
            if (userId.equals(map.get("id").toString())) {
                log.showLog(this.getClass().getName()+"->�û�:"+userId+"�����ʼ���" + (String) map.get("mailcnt"));
                log.showLog(this.getClass().getName()+"->�û�:"+userId+"���ж�����" +  (String) map.get("msgcnt"));
                break;
            }
        }
        return map;
    }


    /**
     * @return ���� dbtype��
     */
    public String getDbtype() {
        return dbtype;
    }

    /**
     * @param dbtype Ҫ���õ� dbtype��
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
     * �õ������û��ķ���
     * @return Collection
     */
    public Collection getOnlineUsers() {
        return aryActiveUsers;
    }

}
