package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

public class TopBoardTreeVO extends BaseTreeVO implements VOInterface {
  public TopBoardTreeVO() {
  }
  public void setOtherProperty(String[] values) {
         super.setAction("/dailyoffice/bbs/sboard.do?topid="+ this.getNodeid());
         super.setIsfolder("1");
         super.setTarget("middle");
         super.setSrc("/dailyoffice/bbs/main.do?action=doSTreeData&#38;nodeid=" + this.getNodeid());
     }

}