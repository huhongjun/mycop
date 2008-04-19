package com.zhjedu.util;

import java.util.ArrayList;

public class PageObject {

  private int totalRecord;
  private int totalPage;
  private int pageSize = 20;
  private int currentPage ;
  private ArrayList datas = null;

  public void setCurrentPage(int i) {
    this.currentPage = i;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setTotalRecord(int i) {
    this.totalRecord = i;
  }

  public int getTotalRecord() {
    return this.totalRecord;
  }

  public int getTotalPage() {
    return this.totalPage;
  }

  public ArrayList getDatas() {
    return this.datas;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setTotalPage(int i) {
    this.totalPage = i;
  }

  public void setDatas(ArrayList list) {
    this.datas = list;
  }

  public void setPageSize(int i) {
    this.pageSize = i;
  }
  public PageObject() {
  }

  public PageObject(ArrayList list, int _totalRecord, int _totalPage,
                    int _pageSize) {
    this.datas = list;
    this.totalRecord = _totalRecord;
    this.totalPage = _totalPage;
    this.pageSize = _pageSize;
  }

}