package com.gever.util.sysinfo;

/**
 * <p>Title: ������Ϣ</p>
 * <p>Desc: �̷����ܿռ䡢���ÿռ䡢���ÿռ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author 
 * @version 1.0
 */

public class DiskInfoView {
  String MYFILE;//�̷�
  double TOTALSPACE;//�����ܿռ�
  double USEDSPACE;//���ÿռ�
  double FREESPACE;//���ÿռ�
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