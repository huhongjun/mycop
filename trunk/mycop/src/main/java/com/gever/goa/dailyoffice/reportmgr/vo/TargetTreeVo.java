package com.gever.goa.dailyoffice.reportmgr.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 *
 * <p>Title:Ŀ�����Ŀ¼�� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TargetTreeVo extends BaseTreeVO implements VOInterface{
    public TargetTreeVo() {
    }
    public void setOtherProperty(String[] values) {
        super.setSrc(
            "/dailyoffice/reportmgr/treetarget.do?action=doTreeData&#38;nodeid=" +
            this.getNodeid());
        //���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/reportmgr/listtarget.do?nodeid=" +
                        this.getNodeid());
        //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setTarget("middle");
        //����target
    }

}
