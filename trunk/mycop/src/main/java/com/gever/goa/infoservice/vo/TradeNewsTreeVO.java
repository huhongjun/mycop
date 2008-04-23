//:TradeNewsTreeVO.java
package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title:TradeNewsTreeVO</p>
 * <p>Description:行业新闻树的VO</p>
 * <p>Copyright: 2004-11-25</p>
 * <p>Company:天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TradeNewsTreeVO extends BaseTreeVO implements VOInterface {
    public void setOtherProperty(String[] values) {
        super.setAction("/infoservice/tradenewslist.do?paraFlag=5&#38;nodeid=" +
            this.getNodeid());
        super.setSrc(
            "/infoservice/tradenewstree.do?action=doTreeData&#38;nodeid=" +
            this.getNodeid());
        super.setTarget("middle");
    }

    public static void main(String[] args) {
    }
}
