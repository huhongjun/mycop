package com.gever.goa.dailyoffice.workreport.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
