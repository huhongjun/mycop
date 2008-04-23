package com.gever.util.sysinfo;

/**
 * <p>Title: 磁盘信息</p>
 * <p>Desc: 盘符、总空间、已用空间、可用空间</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author 
 * @version 1.0
 */

public class DiskInfoView {
  String MYFILE;//盘符
  double TOTALSPACE;//磁盘总空间
  double USEDSPACE;//已用空间
  double FREESPACE;//可用空间
  public double getFREESPACE() {
    return FREESPACE;
  }
  public String getMYFILE() {
    return MYFILE;
  }
  public double getTOTALSPACE() {
    return TOTALSPACE;
  }
  public double getUSEDSPACE() {
    return USEDSPACE;
  }
  public void setFREESPACE(double FREESPACE) {
    this.FREESPACE = FREESPACE;
  }
  public void setMYFILE(String MYFILE) {
    this.MYFILE = MYFILE;
  }
  public void setTOTALSPACE(double TOTALSPACE) {
    this.TOTALSPACE = TOTALSPACE;
  }
  public void setUSEDSPACE(double USEDSPACE) {
    this.USEDSPACE = USEDSPACE;
  }
}