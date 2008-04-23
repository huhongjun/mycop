package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


public class CardcaseTreeVO extends BaseTreeVO implements VOInterface{
  public CardcaseTreeVO() {
  }
  public void setOtherProperty(String[] values){
    super.setAction("/dailyoffice/tools/listCardcase.do?nodeid=" + getNodeid()+"&#38;nodename="+getNodename());
    super.setIsfolder("0");
    super.setTarget("cardmiddle");
  }
}
