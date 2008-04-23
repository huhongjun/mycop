//:ManageSystemTreeVO.java

package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:ManageSystemTreeVO</p>
 * <p>Description:�����ƶ�����VO</p> 
 * <p>Copyright: 2004-11-25</p>
 * <p>Company:�������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class ManageSystemTreeVO extends BaseTreeVO implements VOInterface{
	public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/managesystemlist.do?paraFlag=6&#38;nodeid="
                + this.getNodeid());
        super.setSrc("/infoservice/managesystemtree.do?action=doTreeData&#38;nodeid="
                + this.getNodeid());
        super.setTarget("middle");
    }

	public static void main(String[] args) {
	}
}
