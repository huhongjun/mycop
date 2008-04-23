package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title: </p>
 * <p>Description:版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:天行软件 </p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DeptreeVO extends BaseTreeVO implements VOInterface {
    public void setOtherProperty(String[] values) {
    	super.setAction("");
        //        super.setAction("/infoservice/deptinfolist.do?paraFlag=3&#38;nodeid="
        //        + this.getNodeid());
        //        super.setSrc("/infoservice/deptinfolist.do?paraFlag=3&#38;nodeid="
        //        + this.getNodeid());
        super.setSrc(
            "/infoservice/deptinfotree.do?action=doTreeData&#38;deptid=" +
            this.getNodeid());
        super.setTarget("middle");
    }
}
