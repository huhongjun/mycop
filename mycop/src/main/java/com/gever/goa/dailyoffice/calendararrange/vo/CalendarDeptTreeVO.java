package com.gever.goa.dailyoffice.calendararrange.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description:�ճ̹������� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarDeptTreeVO extends BaseTreeVO implements VOInterface {
    public CalendarDeptTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/calendararrange/treecalendararrange.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/calendararrange/editcalendarbydept.do?departmentId=" + this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setTarget("middle");
        //����target
    }
}
