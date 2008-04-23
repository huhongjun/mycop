package com.gever.goa.dailyoffice.smsmgr.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 部门树 </p>
 * <p>Description: 部门树</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DeptTreeVO extends BaseTreeVO implements VOInterface{
    public DeptTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/smsmgr/treeleftacl.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        super.setAction("/dailyoffice/smsmgr/listsmsacl.do?nodeid=" + this.getNodeid());
        super.setTarget("middle");
    }

}