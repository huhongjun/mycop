package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title:邮箱管理部门树 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailboxTreeMgrVO extends BaseTreeVO implements VOInterface{
    public MailboxTreeMgrVO() {
    }
    public void setOtherProperty(String[] values) {
      super.setSrc("/dailyoffice/mailmgr/mailboxmgr/mailboxtreemgr.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
      //设置点击父节点后显示其子菜单
      super.setAction("/dailyoffice/mailmgr/mailboxmgr/mailboxmgrlist.do?nodeid=" + this.getNodeid());
      //设置点击叶子节点连接到列表页面并且将部门ID传过去
      super.setTarget("middle");
      //设置target
  }


}