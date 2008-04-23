package com.gever.goa.dailyoffice.worklog.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ������Ŀ���е����ṹvo����</p>
 * <p>Description: �Ǹ�����Ŀ�����ṹvo��ӳ��,�������е���������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PersonWeekTargetTreeVO extends BaseTreeVO implements VOInterface {
    public PersonWeekTargetTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/worklog/treepersonweektarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/worklog/listpersonweektarget.do?nodeid=" + this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setTarget("middle");
        //����target
    }

}
