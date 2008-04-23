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

public class LawTreeVO
    extends BaseTreeVO
    implements VOInterface {
    public LawTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/lawlist.do?paraFlag=1&#38;nodeid=" +
                        this.getNodeid());
        super.setSrc("/infoservice/lawtree.do?action=doTreeData&#38;nodeid=" +
                        this.getNodeid());
        super.setTarget("middle");
    }
}