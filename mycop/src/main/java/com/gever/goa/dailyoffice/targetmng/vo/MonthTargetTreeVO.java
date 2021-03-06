package com.gever.goa.dailyoffice.targetmng.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 月度目标中的树结构vo对象</p>
 * <p>Description: 是月度目标树结构vo的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MonthTargetTreeVO extends BaseTreeVO implements VOInterface {
    public MonthTargetTreeVO() {
    }

    public void setOtherProperty(String[] values) {
        super.setSrc("/dailyoffice/targetmng/treemonthtarget.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
        //设置点击父节点后显示其子菜单
        super.setAction("/dailyoffice/targetmng/listmonthtarget.do?nodeid=" + this.getNodeid());
        //设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setTarget("middle");
        //设置target
    }

}
