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

public class DeptInfoTreeVO extends BaseTreeVO implements VOInterface {
    public DeptInfoTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/deptinfolist.do?paraFlag=3&#38;nodeid="
        + this.getNodeid()+"&#38;deptid="+this.getMemo());

        super.setSrc("/infoservice/deptinfotree.do?action=doTreeData&#38;nodeid="
        + this.getNodeid()+"&#38;deptid="+this.getMemo());

        super.setTarget("middle");
    }
}