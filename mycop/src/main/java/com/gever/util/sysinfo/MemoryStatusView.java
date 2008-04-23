package com.gever.util.sysinfo;

/**
 * <p>Title: 内存信息</p>
 * <p>Desc: 物理内存总数、可用的物理内存、虚拟内存总数、可用的虚拟内存</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */
public class MemoryStatusView {
    long length = 0;
    long memoryLoad = 0;
    long totalPhys = 0; //物理内存总数
    long availPhys = 0; ////可用的物理内存
    long totalPageFile = 0;
    long availPageFile = 0;
    long totalVirtual = 0; //虚拟内存总数
    long availVirtual = 0; //可用的虚拟内存

    public MemoryStatusView(long[] lArray) {
        if (lArray.length != 8)
            return;
        init(lArray);
    }

    private void init(long[] lArray) {
        setLength(lArray[0]);
        setMemoryLoad(lArray[1]);
        setTotalPhys(lArray[2]);
        setAvailPhys(lArray[3]);
        setTotalPageFile(lArray[4]);
        setAvailPageFile(lArray[5]);
        setTotalVirtual(lArray[6]);
        setAvailVirtual(lArray[7]);
    }

    public long getAvailPageFile() {
        return availPageFile;
    }

    public void setAvailPageFile(long availPageFile) {
        this.availPageFile = availPageFile;
    }

    public long getAvailPhys() {
        return availPhys;
    }

    public void setAvailPhys(long availPhys) {
        this.availPhys = availPhys;
    }

    public long getAvailVirtual() {
        return availVirtual;
    }

    public void setAvailVirtual(long availVirtual) {
        this.availVirtual = availVirtual;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getMemoryLoad() {
        return memoryLoad;
    }

    public void setMemoryLoad(long memoryLoad) {
        this.memoryLoad = memoryLoad;
    }

    public long getTotalPageFile() {
        return totalPageFile;
    }

    public void setTotalPageFile(long totalPageFile) {
        this.totalPageFile = totalPageFile;
    }

    public long getTotalPhys() {
        return totalPhys;
    }

    public void setTotalPhys(long totalPhys) {
        this.totalPhys = totalPhys;
    }

    public long getTotalVirtual() {
        return totalVirtual;
    }

    public void setTotalVirtual(long totalVirtual) {
        this.totalVirtual = totalVirtual;
    }

}