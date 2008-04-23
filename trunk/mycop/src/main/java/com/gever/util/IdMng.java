package com.gever.util;

import java.util.Date;


/**
 * <p>Title: 序列号生成器</p>
 * <p>Description: [年][日][分钟][用户id][随机数]，用于生成文件路径、文件名、表记录序列号等。</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class IdMng {
    public IdMng() {
    }

    public static String getModuleID(String userId) {
        int iUserId = NumberUtil.stringToInt(userId,0);
        Date d = new Date();
        StringBuffer sb = new StringBuffer();
        sb.append( 1900+d.getYear()).append(toValue(d.getMonth() + 1));
        sb.append(toValue(d.getDate())).append(toValue(d.getHours()));
        sb.append(toValue(d.getMinutes())).append(toValue(d.getSeconds()));
        sb.append(iUserId).append(Math.round(Math.random() * 100));
        return sb.toString();

    }

    public static String getModuleID(String userId, int number) {
        return getModuleID(userId) + number;

    }

    private static String toValue(int value) {
        String strRet = String.valueOf(value);
        if (value < 10) {
            strRet = "0" + Integer.toString(value);
        }
        return strRet;

    }
    //获取随机数
    private String getRandom() {
        int i = 0, t = 0;
        double j = 1;

        String rand = new String("");
        String tmp;
        for (i = 0; i < 4; i++) {
            j = Math.random();
            tmp = new String( (new Double(j)).toString());
            t = tmp.indexOf(".", 0);
            tmp = tmp.substring(t + 1, tmp.length());
            rand += tmp;
        }

        while (rand.length() < 64) {
            rand += "0";
        }
        if (rand.length() > 64) {
            rand = rand.substring(0, 64);
        }
        return rand;
    }

}