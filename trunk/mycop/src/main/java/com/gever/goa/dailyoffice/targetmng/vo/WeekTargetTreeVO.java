package com.gever.goa.dailyoffice.targetmng.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ��Ŀ���е����ṹvo����</p>
 * <p>Description: ����Ŀ�����ṹvo��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WeekTargetTreeVO extends BaseTreeVO implements VOInterface {
    public WeekTargetTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/targetmng/treeweektarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/targetmng/listweektarget.do?nodeid=" + this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setTarget("middle");
        //����target
    }

}
