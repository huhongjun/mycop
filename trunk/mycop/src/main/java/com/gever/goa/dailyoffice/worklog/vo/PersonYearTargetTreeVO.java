package com.gever.goa.dailyoffice.worklog.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 个人年度目标中的树结构vo对象</p>
 * <p>Description: 是个人年度目标树结构vo的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PersonYearTargetTreeVO extends BaseTreeVO implements VOInterface {
    public PersonYearTargetTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/worklog/treepersonyeartarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //设置点击父节点后显示其子菜单
        super.setAction("/dailyoffice/worklog/listpersonyeartarget.do?nodeid=" + this.getNodeid());
        //设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setTarget("middle");
        //设置target
    }

}
