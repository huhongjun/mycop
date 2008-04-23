package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

public class SBoardTreeVO extends BaseTreeVO implements VOInterface {
  public SBoardTreeVO() {
  }

  public void setOtherProperty(String[] values) {
         super.setAction("/dailyoffice/bbs/listTopic.do?sboardID="+ this.getNodeid());
         super.setIsfolder("0");
         super.setTarget("middle");
     }


}
