package com.gever.goa.dailyoffice.message.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * Title:���չ������ṹVO��
 * Description: ���չ������ṹvo��ӳ��,�������е���������
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class MessageTreeVO extends BaseTreeVO implements VOInterface {
    public MessageTreeVO() {
    }

    public void setOtherProperty(String[] values) {

        super.setSrc("/dailyoffice/message/treemessage.do?nodeid=" + this.getNodeid());
//���õ�����ڵ����ʾ���Ӳ˵�
        super.setAction("/dailyoffice/message/listmessage.do?nodeid=" + this.getNodeid());
//���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
        super.setIsfolder("0");
        super.setTarget("middle");
//����target
    }
}
