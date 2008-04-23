package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:</p>
 * <p>Description:IsDoTypeTreeVO.java</p>
 * <p>Copyright:Copyright (c)2004-8-19</p>
 * <p>Company:ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsDoTypeTreeVO extends BaseTreeVO implements VOInterface {
    public IsDoTypeTreeVO() {

    }

    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/typelist.do?paraFlag=" + this.getMemo()
                + "&#38;nodeid=" + this.getNodeid());
        if (this.getIsfolder().equals("1")) {
            super
                    .setSrc("/infoservice/typetree.do?action=doTreeData&#38;nodeid="
                            + this.getNodeid()
                            + "&#38;paraFlag="
                            + this.getMemo());
        }

        super.setTarget("middle");
    }
}