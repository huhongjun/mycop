package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:客户资料树VO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0  创建日期:2004-8-11
 */
public class IsCustomerTreeVO extends BaseTreeVO implements VOInterface {

	public IsCustomerTreeVO() {
	}
	public void setOtherProperty(String[] values) {
		super.setAction(
			"/infoservice/customerlist.do?status="
				+ this.getNodeid());
		super.setIsfolder("0");
		super.setTarget("middle");
	}
}
