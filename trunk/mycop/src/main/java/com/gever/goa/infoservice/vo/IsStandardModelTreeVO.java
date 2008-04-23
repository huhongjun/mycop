package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title:</p>
 * <p>Description:IsStandardModelTreeVO.java</p>
 * <p>Copyright:Copyright (c)2004-9-2</p>
 * <p>Company:广州天行科技有限公司</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsStandardModelTreeVO extends BaseTreeVO implements VOInterface {
    public IsStandardModelTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        String memo = super.getMemo();
        String action;

        if ((memo != null) && memo.equals("2")) {
            action = "/infoservice/templatelist.do?nodeid=13&#38;infotype=" +
                this.getNodeid();
        } else if ((memo != null) && memo.equals("3")) {
            action = "/infoservice/templatelist.do?nodeid=14&#38;infotype=" +
                this.getNodeid();
        } else if ((memo != null) && memo.equals("4")) {
            action = "/infoservice/templatelist.do?nodeid=15&#38;infotype=" +
                this.getNodeid();
        } else if ((memo != null) && memo.equals("5")) {
            action = "/infoservice/templatelist.do?nodeid=16&#38;infotype=" +
                this.getNodeid();
        } else {
            action = "/infoservice/templatelist.do?nodeid=17&#38;infotype=" +
                this.getNodeid();
        }

        super.setAction(action);
        super.setSrc(
            "/infoservice/templatetree.do?action=doTreeData&#38;nodeid=" +
            this.getNodeid());
        super.setTarget("middle");
    }
}
