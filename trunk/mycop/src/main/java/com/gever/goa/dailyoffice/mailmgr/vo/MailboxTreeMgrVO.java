package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title:����������� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailboxTreeMgrVO extends BaseTreeVO implements VOInterface{
    public MailboxTreeMgrVO() {
    }
    public void setOtherProperty(String[] values) {
      super.setSrc("/dailyoffice/mailmgr/mailboxmgr/mailboxtreemgr.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
      //���õ�����ڵ����ʾ���Ӳ˵�
      super.setAction("/dailyoffice/mailmgr/mailboxmgr/mailboxmgrlist.do?nodeid=" + this.getNodeid());
      //���õ��Ҷ�ӽڵ����ӵ��б�ҳ�沢�ҽ�����ID����ȥ
      super.setTarget("middle");
      //����target
  }


}