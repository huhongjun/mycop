package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class OrgInfoTreeVO extends BaseTreeVO implements VOInterface {
    public OrgInfoTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/orginfolist.do?paraFlag=2&#38;nodeid="
                + this.getNodeid());
        super.setSrc("/infoservice/orginfotree.do?action=doTreeData&#38;nodeid="
                + this.getNodeid());
        super.setTarget("middle");
    }
}