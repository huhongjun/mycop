package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;
/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowTreeVO extends BaseTreeVO implements VOInterface{
  public WorldwindowTreeVO() {
  }
  public void setOtherProperty(String[] values){
    super.setAction("/dailyoffice/tools/listWorldwindow.do?nodeid=" + getNodeid());
    super.setIsfolder("0");
    super.setTarget("middle");
  }
}
