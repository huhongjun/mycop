package com.gever.util.sysinfo;


/**
 * <p>Title: 运行时环境信息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class RunTime {
    public RunTime() {

    }

    /**
     * 获取内存总数
     * @return
     */
    public long getTotalMemory() {
        long ret = 0;
        Runtime rt = Runtime.getRuntime();
        ret = rt.totalMemory();
        return ret;
    }

    /**
     * 获取可用内存数
     * @return
     */
    public long getFreeMemory() {
        long ret = 0;
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        ret = rt.freeMemory();
        return ret;
    }

    /**
     * 获取操作系统名称
     * @return
     */
    public String getOsName() {
        String ret = "";
        ret = System.getProperty("os.name");
        return ret;
    }

    /**
     * 获取操作系统版本
     * @return
     */
    public String getOsVersion() {
        String ret = "";
        ret = System.getProperty("os.version");
        return ret;
    }

}