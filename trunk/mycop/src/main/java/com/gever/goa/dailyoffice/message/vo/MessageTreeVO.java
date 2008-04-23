package com.gever.goa.dailyoffice.message.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * Title:接收公文树结构VO类
 * Description: 接收公文树结构vo的映射,包含表中的所有属性
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class MessageTreeVO extends BaseTreeVO implements VOInterface {
    public MessageTreeVO() {
    }

    public void setOtherProperty(String[] values) {

        super.setSrc("/dailyoffice/message/treemessage.do?nodeid=" + this.getNodeid());
//设置点击父节点后显示其子菜单
        super.setAction("/dailyoffice/message/listmessage.do?nodeid=" + this.getNodeid());
//设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setIsfolder("0");
        super.setTarget("middle");
//设置target
    }
}
