package com.gever.goa.dailyoffice.workreport.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DoWorkReportTreeVO extends BaseTreeVO implements VOInterface{
    public DoWorkReportTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setAction("/dailyoffice/workreport/listworkreport.do?listType=" + this.getNodeid());
        super.setIsfolder("0");
        super.setTarget("middle");
    }

}
