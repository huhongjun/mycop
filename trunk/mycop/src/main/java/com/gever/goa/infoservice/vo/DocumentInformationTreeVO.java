//:DocumentInformationTreeVO.java
package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title:DocumentInformationTreeVO</p>
 * <p>Description:文档资料树的VO</p>
 * <p>Copyright: 2004-11-25</p>
 * <p>Company:天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class DocumentInformationTreeVO extends BaseTreeVO implements VOInterface {
    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/docinfolist.do?paraFlag=7&#38;nodeid=" +
            this.getNodeid());
        super.setSrc(
            "/infoservice/docinfotree.do?action=doTreeData&#38;nodeid=" +
            this.getNodeid());
        super.setTarget("middle");
    }

    public static void main(String[] args) {
    }
}
