package com.gever.goa.dailyoffice.targetmng.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �¶��ܽ��е����ṹvo����</p>
 * <p>Description: ���¶��ܽ����ṹvo��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MonthSumTreeVO extends BaseTreeVO implements VOInterface {
    public MonthSumTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/targetmng/treemonthsum.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/targetmng/listmonthsum.do?nodeid=" + this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setTarget("middle");
        //����target
    }

}
