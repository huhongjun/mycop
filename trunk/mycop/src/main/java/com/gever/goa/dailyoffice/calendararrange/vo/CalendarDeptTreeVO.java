package com.gever.goa.dailyoffice.calendararrange.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description:日程管理部门树 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarDeptTreeVO extends BaseTreeVO implements VOInterface {
    public CalendarDeptTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/calendararrange/treecalendararrange.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //设置点击父节点后显示其子菜单
        super.setAction("/dailyoffice/calendararrange/editcalendarbydept.do?departmentId=" + this.getNodeid());
        //设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setTarget("middle");
        //设置target
    }
}
